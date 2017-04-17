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
				createChocolateFactoryIfPresent(),
				createQuoteFactoryIfPresent())
				.flatMap(Optional::stream)
				.collect(toList());
	}

	private static Optional<SurpriseFactory> createChocolateFactoryIfPresent() {
		if (ModuleUtils.isModulePresent("org.codefx.demo.advent.factory.chocolate"))
			return Optional.of(new ChocolateFactory());
		else
			return Optional.empty();
	}

	private static Optional<SurpriseFactory> createQuoteFactoryIfPresent() {
		if (ModuleUtils.isModulePresent("org.codefx.demo.advent.factory.quote"))
			return Optional.of(new QuoteFactory());
		else
			return Optional.empty();
	}

}
