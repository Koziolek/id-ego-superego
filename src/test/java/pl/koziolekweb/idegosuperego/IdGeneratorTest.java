package pl.koziolekweb.idegosuperego;

import org.openjdk.jmh.infra.Blackhole;
import pl.koziolekweb.idegosuperego.commons.IdGenerator;

public interface IdGeneratorTest {

	IdGenerator<?> generator();

	default void doTest(Blackhole blackhole) {
		for (int i = 1; i < 100000; i++)
			blackhole.consume(generator().nextVal());
	}

}

