package pl.koziolekweb.idegosuperego.superego;

import org.openjdk.jmh.annotations.*;
import org.openjdk.jmh.infra.Blackhole;
import pl.koziolekweb.idegosuperego.IdGeneratorTest;
import pl.koziolekweb.idegosuperego.RunConfig;
import pl.koziolekweb.idegosuperego.commons.IdGenerator;
import pl.koziolekweb.idegosuperego.superego.CustomIdGenerator;

import static org.openjdk.jmh.annotations.Mode.*;
import static pl.koziolekweb.idegosuperego.RunConfig.*;

@State(Scope.Benchmark)
//@Microbenchmark
public class CustomGeneratorTest implements IdGeneratorTest {

	private CustomIdGenerator generator = new CustomIdGenerator();

	@Benchmark
	@Threads(1)
	@BenchmarkMode(Throughput)
	@Fork(FORK)
	@Measurement(iterations = ITER, time = TIME)
	@Warmup(iterations = ITER, time = TIME)
	public void _1_singleThread(Blackhole blackhole) {
		doTest(blackhole);
	}

	@Benchmark
	@Threads(2)
	@BenchmarkMode(Throughput)
	@Fork(FORK)
	@Measurement(iterations = ITER, time = TIME)
	@Warmup(iterations = ITER, time = TIME)
	public void _2_twoThreads(Blackhole blackhole) {
		doTest(blackhole);
	}

	@Benchmark
	@Threads(24)
	@BenchmarkMode(Throughput)
	@Fork(FORK)
	@Measurement(iterations = ITER, time = TIME)
	@Warmup(iterations = ITER, time = TIME)
	public void _3__24Threads(Blackhole blackhole) {
		doTest(blackhole);
	}

	@Benchmark
	@Threads(48)
	@BenchmarkMode(Throughput)
	@Fork(FORK)
	@Measurement(iterations = ITER, time = TIME)
	@Warmup(iterations = ITER, time = TIME)
	public void _4__48Threads(Blackhole blackhole) {
		doTest(blackhole);
	}

	@Override
	public IdGenerator<?> generator() {
		return generator;
	}
}