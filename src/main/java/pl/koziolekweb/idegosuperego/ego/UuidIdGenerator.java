package pl.koziolekweb.idegosuperego.ego;

import pl.koziolekweb.idegosuperego.commons.IdGenerator;

import java.util.UUID;

class UuidIdGenerator implements IdGenerator<UUID> {

	@Override
	public UUID nextVal() {
		return UUID.randomUUID();
	}

	@Override
	public UUID nextVal(UUID base) {
		return nextVal();
	}

	public String nextValString() {
		return nextVal().toString();
	}
}
