package org.codefx.demo.advent;

import org.codefx.demo.advent.calendar.Calendar;
import org.codefx.demo.advent.factory.chocolate.ChocolateFactory;
import org.codefx.demo.advent.factory.quote.QuoteFactory;
import org.codefx.demo.advent.surprise.SurpriseFactory;

import java.util.List;
import java.util.Optional;
import java.util.stream.Stream;

import static java.util.stream.Collectors.toList;

public class Main {

	public static void main(String[] args) {
		List<SurpriseFactory> surpriseFactories = createSurpriseFactories();
		Calendar calendar = Calendar.createWithSurprises(surpriseFactories);
		System.out.println(calendar.asText());
	}

	private static List<SurpriseFactory> createSurpriseFactories() {
		return Stream.of(
				createChocolateFactoryIfAccessible(),
				createQuoteFactoryIfAccessible())
				.flatMap(Optional::stream)
				.collect(toList());
	}

	private static Optional<SurpriseFactory> createChocolateFactoryIfAccessible() {
		try {
			return Optional.of(new ChocolateFactory());
		} catch (NoClassDefFoundError er) {
			return Optional.empty();
		}
	}

	private static Optional<SurpriseFactory> createQuoteFactoryIfAccessible() {
		try {
			return Optional.of(new QuoteFactory());
		} catch (NoClassDefFoundError er) {
			return Optional.empty();
		}
	}

}
