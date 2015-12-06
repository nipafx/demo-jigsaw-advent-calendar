package org.codefx.demo.advent;

import java.util.Arrays;
import java.util.List;

public class Main {

	public static void main(String[] args) {
		List<SurpriseFactory> surpriseFactories = Arrays.asList(() -> () -> "Surprise!");
		Calendar calendar = Calendar.createWithSurprises(surpriseFactories);
		System.out.println(calendar.asText());
	}

}
