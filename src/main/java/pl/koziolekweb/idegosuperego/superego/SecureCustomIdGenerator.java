package pl.koziolekweb.idegosuperego.superego;

import lombok.SneakyThrows;

import java.security.SecureRandom;

class SecureCustomIdGenerator extends AbstractCustomIdGenerator {
	private final SecureRandom secureRandom = new SecureRandom();

	@Override
	@SneakyThrows
	protected void fill(byte[] id) {
		secureRandom.nextBytes(id);
	}

}

