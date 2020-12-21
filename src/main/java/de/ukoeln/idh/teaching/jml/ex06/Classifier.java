package de.ukoeln.idh.teaching.jml.ex06;

import weka.core.Instances;

public class Classifier {

	public Tree train(Instances instances) {
		// TODO: implement
		return null;
	};

	public double entropy(Instances instances) {
		// TODO: implement
		return 0.0;
	}

	/**
	 * calculates information gain for a individual feature
	 */
	public double informationGain(Instances instances, int attributeIndex) {
		// TODO: implement
		return 0.0;
	}

	/**
	 * This method uses the math rules that allows to change the base via
	 * mathematical operations: log(x, base) = log(x, 10) / log(base, 10). Since
	 * we're using this a few times, wrapping it in a simple function. Source:
	 * https://en.wikipedia.org/wiki/List_of_logarithmic_identities#Changing_the_base
	 * 
	 * @param a The value for which to calculate the logarithm
	 * @param b The base of the logarithm
	 * @return The logarithm of a to base b
	 */
	protected double log(double a, double b) {
		return Math.log10(a) / Math.log10(b);
	}
}
