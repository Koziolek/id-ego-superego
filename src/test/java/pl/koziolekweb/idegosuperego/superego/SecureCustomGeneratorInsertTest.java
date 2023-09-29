package pl.koziolekweb.idegosuperego.superego;

import pl.koziolekweb.idegosuperego.InsertTest;
import pl.koziolekweb.idegosuperego.commons.IdGenerator;

class SecureCustomGeneratorInsertTest extends InsertTest {

	@Override
	public IdGenerator<?> generator() {
		return new SecureCustomIdGenerator();
	}
}
