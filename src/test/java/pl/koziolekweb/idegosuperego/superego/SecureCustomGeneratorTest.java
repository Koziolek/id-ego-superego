package pl.koziolekweb.idegosuperego.superego;

import org.openjdk.jmh.annotations.*;
import org.openjdk.jmh.infra.Blackhole;
import pl.koziolekweb.idegosuperego.IdGeneratorTest;
import pl.koziolekweb.idegosuperego.RunConfig;
import pl.koziolekweb.idegosuperego.commons.IdGenerator;

import java.util.concurrent.TimeUnit;

@State(Scope.Benchmark)
//@Microbenchmark
public class SecureCustomGeneratorTest implements IdGeneratorTest {

	private SecureCustomIdGenerator generator = new SecureCustomIdGenerator();

	@Benchmark
	@Threads(1)
	@BenchmarkMode(Mode.Throughput)
	@Fork(RunConfig.FORK)
	@Measurement(iterations = RunConfig.ITER, time = RunConfig.TIME)
	@Warmup(iterations = RunConfig.ITER, time = RunConfig.TIME)
	public void _1_singleThread(Blackhole blackhole) {
		doTest(blackhole);
	}

	@Benchmark
	@Threads(2)
	@BenchmarkMode(Mode.Throughput)
	@Fork(RunConfig.FORK)
	@Measurement(iterations = RunConfig.ITER, time = RunConfig.TIME)
	@Warmup(iterations = RunConfig.ITER, time = RunConfig.TIME)
	public void _2_twoThreads(Blackhole blackhole) {
		doTest(blackhole);
	}

	@Benchmark
	@Threads(24)
	@BenchmarkMode(Mode.Throughput)
	@Fork(RunConfig.FORK)
	@Measurement(iterations = RunConfig.ITER, time = RunConfig.TIME)
	@Warmup(iterations = RunConfig.ITER, time = RunConfig.TIME)
	public void _3__24Threads(Blackhole blackhole) {
		doTest(blackhole);
	}

	@Benchmark
	@Threads(48)
	@BenchmarkMode(Mode.Throughput)
	@Fork(RunConfig.FORK)
	@Measurement(iterations = RunConfig.ITER, time = RunConfig.TIME)
	@Warmup(iterations = RunConfig.ITER, time = RunConfig.TIME)
	public void _4__48Threads(Blackhole blackhole) {
		doTest(blackhole);
	}

	@Override
	public IdGenerator<?> generator() {
		return generator;
	}
}