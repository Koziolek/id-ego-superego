package pl.koziolekweb.idegosuperego.ego;

import pl.koziolekweb.idegosuperego.InsertTest;
import pl.koziolekweb.idegosuperego.commons.IdGenerator;

class UuidIdGeneratorInsertTest extends InsertTest {
	@Override
	public IdGenerator<?> generator() {
		return new UuidIdGenerator();
	}
}
