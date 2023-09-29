package pl.koziolekweb.idegosuperego.utils;

import org.junit.jupiter.api.extension.AfterEachCallback;
import org.junit.jupiter.api.extension.BeforeEachCallback;
import org.junit.jupiter.api.extension.ExtensionContext;
import org.junit.platform.commons.support.HierarchyTraversalMode;
import org.junit.platform.commons.support.ModifierSupport;

import java.lang.reflect.Field;
import java.util.IntSummaryStatistics;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.function.Predicate;

import static org.junit.platform.commons.support.AnnotationSupport.findAnnotatedFields;

public class StatsReporter implements BeforeEachCallback, AfterEachCallback {

	private final Map<String, StatsCollector> collector;

	public StatsReporter() {
		this.collector = new ConcurrentHashMap<>();
	}

	@Override
	public void afterEach(ExtensionContext context) throws Exception {
		collector.forEach((name, collector) -> System.out.println(name + " " + collector));
	}

	@Override
	public void beforeEach(ExtensionContext context) throws Exception {
		Class<?> testClass = context.getRequiredTestClass();
		Object testInstance = context.getRequiredTestInstance();
		final Predicate<Field> isNotStatic = ModifierSupport::isNotStatic;
		var restriction = isNotStatic.and(f -> f.getType() == StatsCollector.class);
		injectFields(testClass, testInstance, restriction, context);

	}

	private void injectFields(Class<?> testClass, Object testInstance,
							  Predicate<Field> predicate, ExtensionContext context) {

		findAnnotatedFields(testClass, StatsReporting.class, predicate, HierarchyTraversalMode.TOP_DOWN)
				.forEach(field -> {
					try {
						field.setAccessible(true);
						final StatsCollector value = new StatsCollector();
						field.set(testInstance, value);
						this.collector.put(testClass.getSimpleName(), value);
					} catch (Exception ex) {
						throw new RuntimeException(ex);
					}
				});
	}

	public static class StatsCollector {

		private Stats statistics;

		public void collect(Stats stats) {
			this.statistics = stats;
		}

		@Override
		public String toString() {
			return "StatsCollector{" +
					"statistics=" + statistics +
					'}';
		}
	}

	public record Stats(int threads, IntSummaryStatistics stats) {
	}
}
