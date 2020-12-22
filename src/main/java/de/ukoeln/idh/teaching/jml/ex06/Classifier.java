package de.ukoeln.idh.teaching.jml.ex06;

import weka.core.DenseInstance;
import weka.core.Instance;
import weka.core.Instances;

public class Classifier {

	public Tree train(Instances instances) {
		double highestGain = 0.0;
		int attributeIndexWithHighestGain = -1;
		// go over all, except the class attribute
		for (int i = 0; i < instances.numAttributes() - 1; i++) {
			// get information gain
			double ig = informationGain(instances, i);

			// find maximal IG
			if (ig > highestGain) {
				highestGain = ig;
				attributeIndexWithHighestGain = i;
			}
		}
		// base case: No further information gain to be had
		if (highestGain == 0.0) {
			// create a leaf node
			Tree tree = new Tree();
			// set its prediction to the majority of the current sub set
			tree.prediction = getMajority(countClasses(instances));
			return tree;
		} else {
			// create a non-leaf node
			Tree tree = new Tree();
			// store current attribute
			tree.attributeIndex = attributeIndexWithHighestGain;
			// create children array
			tree.children = new Tree[instances.attribute(attributeIndexWithHighestGain).numValues()];
			// split the data set into subsets
			Instances[] subsets = subsets(instances, attributeIndexWithHighestGain);
			// generate a tree for each subset
			for (int child = 0; child < tree.children.length; child++) {
				tree.children[child] = train(subsets[child]);
			}
			return tree;
		}
	};

	/**
	 * For a given data set, create subsets based on one attribute. For each value
	 * of the attribute, one subset is created to hold all instances that have this
	 * attribute value.
	 * 
	 */

	protected static Instances[] subsets(Instances instances, int attributeIndex) {
		Instances[] ret = new Instances[instances.attribute(attributeIndex).numValues()];

		for (int subsetIndex = 0; subsetIndex < instances.attribute(attributeIndex).numValues(); subsetIndex++) {
			ret[subsetIndex] = new Instances(instances, 0);
		}
		for (Instance instance : instances) {
			ret[(int) instance.value(attributeIndex)].add(new DenseInstance(instance));
		}

		return ret;
	}

	protected static int getMajority(int[] instances) {
		int majority = 0;
		int majorityIndex = 0;
		for (int i = 0; i < instances.length; i++) {
			if (instances[i] > majority) {
				majority = instances[i];
				majorityIndex = i;
			}
		}
		return majorityIndex;
	}

	protected static int[] countClasses(Instances instances) {
		int[] instanceNumbers = new int[instances.numClasses()];
		for (Instance instance : instances) {
			instanceNumbers[(int) instance.classValue()] += 1;
		}
		return instanceNumbers;
	}

	/**
	 * This method extracts frequency counts from the instances, and calls the
	 * entropy method that uses int[] as an input.
	 * 
	 * @param instances
	 * @return
	 */

	public double entropy(Instances instances) {
		return entropy(countClasses(instances));
	}

	/**
	 * calculates information gain for a individual feature
	 */
	public double informationGain(Instances instances, int attributeIndex) {
		// the number of distinct values of the attribute determines the number of sub
		// sets we consider
		int numberOfSubsets = instances.attribute(attributeIndex).numValues();

		// for each subset, we create an array that contains the number of instances
		// from each class (this corresponds to the input of the entropy(int[]) method).
		int[][] subsets = new int[numberOfSubsets][instances.numClasses()];

		// to calculate the relative weight of each subset, we need the size of each
		// subset
		int[] subsetSizes = new int[numberOfSubsets];

		// this loop iterates over the instances
		for (Instance instance : instances) {
			// we extract the feature value of the given attribute
			int subsetIndex = (int) instance.value(attributeIndex);

			// increase number of instances in the sub subset representing one class
			subsets[subsetIndex][(int) instance.classValue()] += 1;

			// increase total number of instances in subset
			subsetSizes[subsetIndex] += 1;
		}

		// calculate entropy before division
		double remainingEntropy = entropy(instances);
		for (int i = 0; i < subsets.length; i++) {
			// calculate weighted entropy for this subset
			double subEntropy = (subsetSizes[i] / (double) instances.numInstances()) * entropy(subsets[i]);

			// subtract sub entropy from total entropy
			remainingEntropy -= subEntropy;
		}

		// return the remaining entropy
		return remainingEntropy;
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
