package org.codefx.demo.advent.factory;

import org.codefx.demo.advent.surprise.Surprise;
import org.codefx.demo.advent.surprise.SurpriseFactory;

import java.util.List;
import java.util.Random;

public abstract class AbstractSurpriseFactory implements SurpriseFactory {

	/*
	 * The class is public but the package is only exported to selected modules,
	 * so others will not be able to access it
	 */

	private static final Random RANDOM = new Random();

	@Override
	public final Surprise create() {
		int surpriseIndex = RANDOM.nextInt(surprises().size());
		return surprises().get(surpriseIndex);
	}

	protected abstract List<Surprise> surprises();

}
