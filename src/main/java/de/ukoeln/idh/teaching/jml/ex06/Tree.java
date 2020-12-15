package de.ukoeln.idh.teaching.jml.ex06;

import weka.core.Instance;

public class Tree {
	Tree[] children;
	int attributeIndex = -1;

	public double predict(Instance instance) {
		// TODO: Implement
		// recursive case
		return 0.0;
	}

	public boolean isLeaf() {
		return false;
	}
}
