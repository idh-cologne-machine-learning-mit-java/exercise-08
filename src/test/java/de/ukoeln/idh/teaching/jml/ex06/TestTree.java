package de.ukoeln.idh.teaching.jml.ex06;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import weka.core.Instance;
import weka.core.Instances;

public class TestTree {
	Tree tree = new Tree();

	@BeforeEach
	public void setup() {

	}

	@Test
	public void testIsLeaf() {
		assertTrue(tree.isLeaf());

		tree.children = new Tree[] {};
		assertTrue(tree.isLeaf());

		tree.children = new Tree[] { new Tree(), new Tree() };
		assertFalse(tree.isLeaf());

	}

	@Test
	public void testPredict() throws FileNotFoundException, IOException {
		Instances instances = new Instances(new FileReader("src/test/resources/ig.arff"));

		Tree tree;

		tree = new Tree();
		tree.prediction = 0.0;
		assertEquals(0.0, tree.predict(instances.get(0)));
		assertEquals(0.0, tree.predict(instances.get(1)));
		assertEquals(0.0, tree.predict(instances.get(2)));
		assertEquals(0.0, tree.predict(instances.get(3)));
		assertEquals(0.0, tree.predict(instances.get(4)));

		tree = new Tree();
		tree.attributeIndex = 3;
		tree.children = new Tree[] { new Tree(), new Tree() };
		tree.children[0].prediction = 0.0;
		tree.children[1].attributeIndex = 2;
		tree.children[1].children = new Tree[] { new Tree(), new Tree() };
		tree.children[1].children[0].prediction = 1.0;
		tree.children[1].children[1].prediction = 0.0;

		Instance instance = mock(Instance.class);
		when(instance.value(3)).thenReturn(0.0);
		assertEquals(0.0, tree.predict(instance));

		when(instance.value(3)).thenReturn(1.0);
		when(instance.value(2)).thenReturn(0.0);
		assertEquals(1.0, tree.predict(instance));

		when(instance.value(2)).thenReturn(1.0);
		assertEquals(0.0, tree.predict(instance));

	}
}
