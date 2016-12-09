package org.codefx.demo.advent.factories;

import org.codefx.demo.advent.surprise.Surprise;

import java.util.Arrays;
import java.util.List;

import static java.lang.String.format;

public class QuoteFactory extends AbstractSurpriseFactory {

	private static final List<Surprise> QUOTES = Arrays.asList(
			new Quote(
					"The sky above the port was the color of television, tuned to a dead channel.",
					"William Gibson"),
			new Quote(
					"The criminal is a creative artist; detectives are just critics.",
					"Hannu Rajaniemi"),
			new Quote(
					"This is your life and it's ending one minute at a time.",
					"Narrator"),
			new Quote(
					"Flames from the lips may be produced by holding in the mouth a sponge saturated with the purest gasoline.",
					"Harry Houdini")
	);

	@Override
	protected List<Surprise> surprises() {
		return QUOTES;
	}

	private static class Quote implements Surprise {

		private final String quote;
		private final String author;

		private Quote(String quote, String author) {
			this.quote = quote;
			this.author = author;
		}

		@Override
		public String asText() {
			return format("\"%s\" (%s)", quote, author);
		}
	}

}
