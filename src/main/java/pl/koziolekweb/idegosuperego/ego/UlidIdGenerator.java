package pl.koziolekweb.idegosuperego.ego;

import de.huxhorn.sulky.ulid.ULID;
import pl.koziolekweb.idegosuperego.commons.IdGenerator;

class UlidIdGenerator implements IdGenerator<ULID.Value> {

	private final ULID generator = new ULID();

	@Override
	public ULID.Value nextVal() {
		return generator.nextValue();
	}

	@Override
	public ULID.Value nextVal(ULID.Value base) {
		return generator.nextMonotonicValue(base);
	}

	public String nextValString(){
		return nextVal().toString();
	}
}
