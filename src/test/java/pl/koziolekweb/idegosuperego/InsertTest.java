package pl.koziolekweb.idegosuperego;

import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;
import pl.koziolekweb.idegosuperego.commons.IdGenerator;
import pl.koziolekweb.idegosuperego.utils.StatsReporter;
import pl.koziolekweb.idegosuperego.utils.StatsReporting;

import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.concurrent.*;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.assertThat;


public abstract class InsertTest {

	private int SIZE = 100000;

	@StatsReporting
	private StatsReporter.StatsCollector reporting;

	public abstract IdGenerator<?> generator();

	@ParameterizedTest
	@MethodSource("params")
	@SneakyThrows
	void insert(int threads) {
		var table = Collections.synchronizedSet(new HashSet<String>());
		final ExecutorService service = Executors.newFixedThreadPool(threads);
		final List<Future<Integer>> futures = service.invokeAll(prepareTask(table, threads));
		final List<Integer> result = futures.stream().map(this::valFromFuture).toList();
		var stats = result.stream().collect(Collectors.summarizingInt(Integer::intValue));
		reporting.collect(new StatsReporter.Stats(threads, stats));
		assertThat(table).hasSize(SIZE * threads);
	}

	private List<Callable<Integer>> prepareTask(Set<String> table, int nbr) {
		return Stream.generate(() -> prepareTask(table)).limit(nbr).toList();
	}

	private Callable<Integer> prepareTask(Set<String> table) {
		return () -> {
			var task = new InsertTask(generator());
			for (int i = 0; i < SIZE; i++) {
				task.insert(table);
			}
			return task.missed();
		};
	}

	private Integer valFromFuture(Future<Integer> f) {
		try {
			return f.get();
		} catch (InterruptedException | ExecutionException e) {
			return -1;
		}
	}

	public static IntStream params() {
		return IntStream.of(1, 2, 4, 8, 16, 24, 48);
	}

	@RequiredArgsConstructor
	class InsertTask {

		private final IdGenerator<?> generator;

		private final AtomicInteger missed = new AtomicInteger(0);

		void insert(Set<String> table) {
			boolean inserted = false;
			while (!inserted) {
				inserted = table.add(generator.nextValString());
				if (!inserted)
					missed.incrementAndGet();
			}
		}

		int missed() {
			return missed.get();
		}
	}

}
