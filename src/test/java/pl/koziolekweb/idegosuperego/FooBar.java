package pl.koziolekweb.idegosuperego;

import org.openjdk.jmh.annotations.*;

import java.util.Optional;
import java.util.concurrent.TimeUnit;

@BenchmarkMode({Mode.AverageTime})
@OutputTimeUnit(TimeUnit.MILLISECONDS)
@Warmup(iterations = 3, time = 1, timeUnit = TimeUnit.SECONDS)
@Measurement(iterations = 5, time = 1, timeUnit = TimeUnit.SECONDS)
@Fork(1)
@State(Scope.Benchmark)
public class FooBar {

	private static final int ITERATIONS = 10_000_000;

	@Benchmark
	public void testUsingNull() {
		for (int i = 0; i < ITERATIONS; i++) {
			String value = getValue(i);
			if (value != null) {
				value.toUpperCase();
			}
		}
	}

	@Benchmark
	public void testUsingOptional() {
		for (int i = 0; i < ITERATIONS; i++) {
			Optional<String> value = getOptionalValue(i);
			value.map(String::toUpperCase);
		}
	}

	public String getValue(int i) {
		return i % 2 == 0 ? null : new String("foobar");
	}

	public Optional<String> getOptionalValue(int i) {
		return i % 2 == 0 ? Optional.empty() : Optional.of(new String("foobar"));
	}

	public static void main(String[] args) throws Exception {
		org.openjdk.jmh.Main.main(args);
	}
}