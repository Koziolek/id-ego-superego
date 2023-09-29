package pl.koziolekweb.idegosuperego;


import org.openjdk.jmh.annotations.*;

import java.lang.annotation.*;

@BenchmarkMode(Mode.Throughput)
@Fork(RunConfig.FORK)
@Measurement(iterations = RunConfig.ITER, time = RunConfig.TIME)
@Warmup(iterations = RunConfig.ITER, time = RunConfig.TIME)
@Target({ElementType.METHOD, ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Inherited
public @interface RunConfig {
	int ITER = 5;
	int TIME = 5;
	int FORK = 5;
}
