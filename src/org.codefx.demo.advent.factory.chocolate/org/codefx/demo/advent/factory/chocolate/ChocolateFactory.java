package org.codefx.demo.advent.factory.chocolate;

import org.codefx.demo.advent.factory.AbstractSurpriseFactory;
import org.codefx.demo.advent.surprise.Surprise;

import java.util.Arrays;
import java.util.List;

public class ChocolateFactory extends AbstractSurpriseFactory {

	private static final List<Surprise> CHOCOLATES = Arrays.asList(
			new Chocolate("Fair Trade Chocolate Ball"),
			new Chocolate("Fair Trade Chocolate Bar"),
			new Chocolate("Fair Trade Chocolate Bonbon"),
			new Chocolate("Fair Trade Chocolate Christmas Tree")
	);

	@Override
	protected List<Surprise> surprises() {
		return CHOCOLATES;
	}

	private static final class Chocolate implements Surprise {

		private final String text;

		private Chocolate(String text) {
			this.text = text;
		}

		@Override
		public String asText() {
			return text;
		}
	}
}
