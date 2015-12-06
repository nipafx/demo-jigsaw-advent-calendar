package org.codefx.demo.advent.factories.impl;

import org.codefx.demo.advent.surprise.Surprise;
import org.codefx.demo.advent.surprise.SurpriseFactory;

import java.util.List;
import java.util.Random;

public abstract class AbstractSurpriseFactory implements SurpriseFactory {

	/*
	 * The class is public but the package is not being exported so it will not be accessible outside this module.
	 */

	private static final Random RANDOM = new Random();

	@Override
	public final Surprise create() {
		int surpriseIndex = RANDOM.nextInt(surprises().size());
		return surprises().get(surpriseIndex);
	}

	protected abstract List<Surprise> surprises();

}
