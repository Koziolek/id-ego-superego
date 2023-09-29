package pl.koziolekweb.idegosuperego.superego;

import pl.koziolekweb.idegosuperego.commons.IdGenerator;

import static java.lang.Math.abs;

abstract class AbstractCustomIdGenerator implements IdGenerator<AbstractCustomIdGenerator.CUID> {

	private static final String DIGITS = "123456789ABCDEFGHJKLMNPQRSTUVWXYZabcdefghijkmnopqrstuvwxyz";
	private static final int SIZE = 11;

	@Override
	public AbstractCustomIdGenerator.CUID nextVal() {
		byte[] id = init();
		for (int i = 0; i < SIZE; i++) {
			var index = abs(id[i] % DIGITS.length());
			id[i] = (byte) DIGITS.charAt(index);
		}
		return asId(id);
	}

	@Override
	public AbstractCustomIdGenerator.CUID nextVal(AbstractCustomIdGenerator.CUID base) {
		return nextVal();
	}

	@Override
	public String nextValString() {
		return nextVal().toString();
	}

	private CUID asId(byte[] id) {
		return new CUID(new String(id));
	}

	protected abstract void fill(byte[] id);

	private byte[] init() {
		byte[] id = new byte[SIZE];
		fill(id);
		return id;
	}

	public record CUID(String value) {
	}
}
