package pl.koziolekweb.idegosuperego.superego;

import lombok.SneakyThrows;
import pl.koziolekweb.idegosuperego.commons.IdGenerator;

import java.util.Random;

class UnsecureCustomIdGenerator extends AbstractCustomIdGenerator {
	private static final Random random = new Random();

	@Override
	@SneakyThrows
	protected void fill(byte[] id) {
		random.nextBytes(id);
	}

}

