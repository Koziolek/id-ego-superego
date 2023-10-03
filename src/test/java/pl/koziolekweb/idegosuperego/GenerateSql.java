package pl.koziolekweb.idegosuperego;

import lombok.SneakyThrows;
import org.junit.jupiter.api.Test;

import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.stream.Collectors;
import java.util.stream.Stream;

class GenerateSql {


	private static final int MAX_SIZE = 1000000;

	@SneakyThrows
	@Test
	void idGenerator() {
		final String c = Stream.generate(() -> "(nextval('book_id_id_seq'), 'name')")
				.limit(MAX_SIZE)
				.collect(Collectors.joining(",\n"));
		StringBuilder s = new StringBuilder("insert into ")
				.append("book_id")
				.append(" ")
				.append("(id, name) values")
				.append("\n")
				.append(c)
				.append(";");

		Files.writeString(Path.of("idg.sql"), s.toString(), StandardCharsets.UTF_8);
	}

	@SneakyThrows
	@Test
	void uuidGenerator() {
		final String c = Stream.generate(() -> "(gen_random_uuid(), 'name')")
				.limit(MAX_SIZE)
				.collect(Collectors.joining(",\n"));
		StringBuilder s = new StringBuilder("insert into ")
				.append("book_uuid")
				.append(" ")
				.append("(id, name) values")
				.append("\n")
				.append(c)
				.append(";");

		Files.writeString(Path.of("uidg.sql"), s.toString(), StandardCharsets.UTF_8);
	}
}
