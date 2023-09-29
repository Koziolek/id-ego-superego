package pl.koziolekweb.idegosuperego.commons;

public interface IdGenerator<T> {

	T nextVal();

	T nextVal(T base);

	String nextValString();
}
