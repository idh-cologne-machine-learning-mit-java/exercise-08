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

	/**
	 * This is the core method to calculate entropy. Since it's only based on
	 * relative frequencies, we can calculate it without knowing the classes
	 * themselves, just using the numbers per class.
	 * 
	 * @param numberOfInstances An int array that contains one entry per class. The
	 *                          entry represents the number of instances for this
	 *                          class.
	 * @return The entropy of the given data set.
	 */
	protected double entropy(int[] numberOfInstances) {
		// to calculate relative frequency, we need the total number of instances
		int total = 0;
		// do a loop and add up the total number
		for (int i = 0; i < numberOfInstances.length; i++) {
			total += numberOfInstances[i];
		}

		// placeholder for the return value
		double ret = 0.0;

		// iterate over each class
		for (int i = 0; i < numberOfInstances.length; i++) {
			// to avoid taking the log of 0, we skip non-used classes
			// (in my implementation of information gain, they might come up)
			if (numberOfInstances[i] == 0)
				continue;

			// calculate relative frequency of this class
			double rfreq = numberOfInstances[i] / (double) total;
			ret += rfreq * log(rfreq, Math.E);
		}
		// return the return value
		return -1 * ret;
	}
}
