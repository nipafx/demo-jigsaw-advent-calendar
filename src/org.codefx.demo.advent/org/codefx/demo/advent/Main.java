package org.codefx.demo.advent;

import org.codefx.demo.advent.surprises.ChocolateFactory;
import org.codefx.demo.advent.surprises.QuoteFactory;

import java.util.Arrays;
import java.util.List;

public class Main {

	public static void main(String[] args) {
		List<SurpriseFactory> surpriseFactories = Arrays.asList(
				new ChocolateFactory(),
				new QuoteFactory()
		);
		Calendar calendar = Calendar.createWithSurprises(surpriseFactories);
		System.out.println(calendar.asText());
	}

}
