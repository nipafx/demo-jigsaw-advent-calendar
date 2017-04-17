package org.codefx.demo.advent.calendar;

import org.codefx.demo.advent.surprise.Surprise;
import org.codefx.demo.advent.surprise.SurpriseFactory;

import java.util.List;
import java.util.Random;
import java.util.function.Supplier;
import java.util.stream.IntStream;

import static java.util.stream.Collectors.joining;
import static java.util.stream.Collectors.toList;

public class Calendar {

	private final List<CalendarSheet> sheets;

	private Calendar(List<CalendarSheet> sheets) {
		this.sheets = sheets;
	}

	public static Calendar createWithSurprises(List<SurpriseFactory> surpriseFactories) {
		if (surpriseFactories.isEmpty())
			throw new IllegalArgumentException("Advent calendar can not work without at least one surprise factory.");
		return new Calendar(createSheetsWithRandomSurprises(surpriseFactories));
	}

	private static List<CalendarSheet> createSheetsWithRandomSurprises(List<SurpriseFactory> surpriseFactories) {
		Supplier<Surprise> randomSurpriseSupplier = createRandomSurpriseSupplier(surpriseFactories);
		return IntStream.rangeClosed(1, 24)
				.mapToObj(dayOfDecember -> new CalendarSheet(dayOfDecember, randomSurpriseSupplier.get()))
				.collect(toList());
	}

	private static Supplier<Surprise> createRandomSurpriseSupplier(List<SurpriseFactory> surpriseFactories) {
		Random random = new Random();
		return () -> {
			int factoryIndex = random.nextInt(surpriseFactories.size());
			SurpriseFactory factory = surpriseFactories.get(factoryIndex);
			return factory.create();
		};
	}

	public String asText() {
		return sheets.stream()
				.map(CalendarSheet::asText)
				.collect(joining("\n", "Advent Calendar\n", ""));
	}

}
