package pl.koziolekweb.idegosuperego.id;

import pl.koziolekweb.idegosuperego.InsertTest;
import pl.koziolekweb.idegosuperego.commons.IdGenerator;

class LongIdGeneratorInsertTest extends InsertTest {
	@Override
	public IdGenerator<?> generator() {
		return new LongIdGenerator();
	}
}
