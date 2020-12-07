package de.ukoeln.idh.teaching.jml.ex06;

import java.util.List;

import weka.core.Instance;

public class DecisionTree {

	Decision payload;
	List<DecisionTree> children;
	DecisionTree parent;

	public double decide(Instance instance) {
		if (this.isLeaf()) {
			return this.payload.targetClass;
		} else {
			int subtreeIndex = this.payload.decide(instance);
			return children.get(subtreeIndex).decide(instance);
		}
	}

	boolean isLeaf() {
		return false;
	}

}
