package de.ukoeln.idh.teaching.jml.ex06;

import weka.core.Instance;

public class Tree {
	Tree[] children = null;
	int attributeIndex = -1;
	double prediction = Double.NaN;

	public double predict(Instance instance) {
		if (this.isLeaf()) {
			return prediction;
		} else {
			double attributeValue = instance.value(attributeIndex);
			return this.children[(int) attributeValue].predict(instance);
		}
	}

	public boolean isLeaf() {
		return children == null || children.length == 0;
	}
	@Override
	public String toString() {
		StringBuilder b = new StringBuilder();
		b.append("(");
		if (isLeaf()) {
			b.append("Class: ");
			b.append(prediction);
		} else {
			b.append("Attribute : ");
			b.append(attributeIndex);
			b.append(" ");
			for (int child = 0; child < children.length; child++) {
				b.append(" ");
				b.append(children[child].toString());
			}
		}
		b.append(")");

		return b.toString();
	}
}
