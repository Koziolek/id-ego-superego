package pl.koziolekweb.idegosuperego;

import org.junit.jupiter.api.AfterEach;
import org.openjdk.jmh.annotations.Scope;
import org.openjdk.jmh.annotations.State;
import org.openjdk.jmh.infra.Blackhole;
import pl.koziolekweb.idegosuperego.commons.IdGenerator;

import java.util.Collections;
import java.util.HashSet;
import java.util.Set;
import java.util.concurrent.atomic.AtomicLong;

public interface IdGeneratorCollisionCostTest {

	default void doTest(Table table, IdGenerator<?> generator) {
		for (int i = 1; i < 1000; i++) {
			boolean inserted = false;
			while (!inserted) {
				inserted = table.add(generator.nextValString());
				if (!inserted)
					table.miss();
			}
		}
	}


	@State(Scope.Group)
	class Table{
		private final Set<String> table;
		private final AtomicLong missed;

		public Table() {
			this.table = Collections.synchronizedSet(new HashSet<>());
			this.missed = new AtomicLong(0);
		}

		public boolean add(String id){
			return table.add(id);
		}

		public long miss(){
			return missed.incrementAndGet();
		}
	}

}

