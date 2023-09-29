package pl.koziolekweb.idegosuperego.id;

import org.junit.jupiter.api.AfterEach;
import org.openjdk.jmh.annotations.*;
import org.openjdk.jmh.infra.Blackhole;
import pl.koziolekweb.idegosuperego.IdGeneratorCollisionCostTest;
import pl.koziolekweb.idegosuperego.IdGeneratorTest;
import pl.koziolekweb.idegosuperego.RunConfig;
import pl.koziolekweb.idegosuperego.commons.IdGenerator;

import java.util.HashSet;
import java.util.Set;
import java.util.concurrent.atomic.AtomicLong;

public class LongIdGeneratorCollisionCostTest implements IdGeneratorCollisionCostTest {

	@State(Scope.Thread)
	public static class Generator {
		LongIdGenerator generator = new LongIdGenerator();
	}

	@State(Scope.Group)
	public static class T {
		private Table table = new Table();

		@TearDown
		public void tearDown() {
			System.out.println("Misses: " + table.miss());
		}
	}
//	@Benchmark
//	@Threads(1)
//	@BenchmarkMode(Mode.Throughput)
//	@Fork(RunConfig.FORK)
//	@Measurement(iterations = RunConfig.ITER, time = RunConfig.TIME)
//	@Warmup(iterations = RunConfig.ITER, time = RunConfig.TIME)
//	public void _1_singleThread(Blackhole blackhole) {
//		doTest(blackhole, table);
//	}
//
//	@Benchmark
//	@BenchmarkMode(Mode.Throughput)
//	@Fork(RunConfig.FORK)
//	@Measurement(iterations = RunConfig.ITER, time = RunConfig.TIME)
//	@Warmup(iterations = RunConfig.ITER, time = RunConfig.TIME)
//	@Group("_2_twoThreads")
//	@GroupThreads(2)
//	public void _2_twoThreads(T t, Generator generator) {
//		doTest(t.table, generator.generator);
//	}

//	@Benchmark
//	@Threads(24)
//	@BenchmarkMode(Mode.Throughput)
//	@Fork(RunConfig.FORK)
//	@Measurement(iterations = RunConfig.ITER, time = RunConfig.TIME)
//	@Warmup(iterations = RunConfig.ITER, time = RunConfig.TIME)
//	public void _3__24Threads(Blackhole blackhole) {
//		doTest(blackhole);
//	}
//
//	@Benchmark
//	@Threads(48)
//	@BenchmarkMode(Mode.Throughput)
//	@Fork(RunConfig.FORK)
//	@Measurement(iterations = RunConfig.ITER, time = RunConfig.TIME)
//	@Warmup(iterations = RunConfig.ITER, time = RunConfig.TIME)
//	public void _4__48Threads(Blackhole blackhole) {
//		doTest(blackhole);
//	}

}