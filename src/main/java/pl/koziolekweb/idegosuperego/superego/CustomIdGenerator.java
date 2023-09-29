package pl.koziolekweb.idegosuperego.superego;

import lombok.SneakyThrows;

import java.util.Random;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

class CustomIdGenerator extends AbstractCustomIdGenerator {
	private static final Random random = new Random();
	private static final Lock randomLock = new ReentrantLock();

	@Override
	@SneakyThrows
	protected void fill(byte[] id) {
		randomLock.tryLock(1, TimeUnit.SECONDS);
		random.nextBytes(id);
		randomLock.unlock();
	}

}

