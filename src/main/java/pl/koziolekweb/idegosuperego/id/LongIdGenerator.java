package pl.koziolekweb.idegosuperego.id;

import pl.koziolekweb.idegosuperego.commons.IdGenerator;

import java.util.concurrent.atomic.AtomicLong;

class LongIdGenerator implements IdGenerator<Long> {

	private final AtomicLong current;

	LongIdGenerator() {
		current = new AtomicLong(0);
	}

	@Override
	public Long nextVal() {
		return current.addAndGet(1);
	}

	@Override
	public Long nextVal(Long base) {
		synchronized (current) {
			current.set(base);
			return current.addAndGet(1);
		}
	}

	@Override
	public String nextValString() {
		return nextVal().intValue() + "";
	}
}
