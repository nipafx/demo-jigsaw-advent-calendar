package org.codefx.demo.advent.surprises;

import org.codefx.demo.advent.Surprise;
import org.codefx.demo.advent.SurpriseFactory;

import java.util.List;
import java.util.Random;

abstract class AbstractSurpriseFactory implements SurpriseFactory {

	private static final Random RANDOM = new Random();

	@Override
	public final Surprise create() {
		int surpriseIndex = RANDOM.nextInt(surprises().size());
		return surprises().get(surpriseIndex);
	}

	protected abstract List<Surprise> surprises();

}
