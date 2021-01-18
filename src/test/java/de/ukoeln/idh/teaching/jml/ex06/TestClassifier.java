@@ -1,7 +1,5 @@
package de.ukoeln.idh.teaching.jml.ex06;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

import org.junit.jupiter.api.Test;

import weka.core.Instances;
import weka.core.UnassignedClassException;

public class TestClassifier {

@@ -29,7 +30,7 @@ public void setup() throws FileNotFoundException, IOException {
	 * This method is used to generate a data set with a specific distribution. For
	 * calculating entropy, the number of classes and their distribution is the
	 * deciding factor. The returned data set contains 100 instances.
	 * 
	 *
	 * @param numberOfClasses The number of classes
	 * @param portions        An array giving the absolute number of each of the
	 *                        different classes. The values in the array should add
@@ -110,7 +111,7 @@ public void testInformationGain() throws IOException {
	}

	@Test
	
	public void testTrain() throws IOException {
		Instances instances = new Instances(new FileReader("src/test/resources/treetest.arff"));
		instances.setClassIndex(instances.numAttributes() - 1);
		Classifier cls = new Classifier();
@@ -123,4 +124,43 @@ public void testTrain() throws FileNotFoundException, IOException {

	}

	@Test
	public void testSubsets() throws IOException {
		Instances instances = new Instances(new FileReader("src/test/resources/treetest.arff"));

		Instances[] subset;
		// start test
		// test if subsets is not grater then the num of values of specific attribute
		for (int i = 0; i < instances.numAttributes(); i++) {
			subset = Classifier.subsets(instances, i);
			assertEquals(subset.length, instances.attribute(i).numValues());
		}

	}

	@Test
	public void testGetMajority() {
		// test if the greater is chosen
		assertEquals(0, Classifier.getMajority(new int[] {2, 1}));
		assertEquals(1, Classifier.getMajority(new int[] {1, 2}));
		// test whether the it works for more then two options
		assertEquals(3,Classifier.getMajority(new int[] {1, 2, 3, 5, 4}));
		// test if the first one is chosen when two equal.
		assertEquals(1, Classifier.getMajority(new int[] {1, 2, 2}));


	}

	@Test
	public void testCountClasses() throws IOException {
		Instances instances = new Instances(new FileReader("src/test/resources/treetest.arff"));
		// test if the functions automatically sets a the classIndex
		assertThrows(UnassignedClassException.class, () -> {
			classifier.countClasses(instances);
		});
		instances.setClassIndex(instances.numAttributes() - 1);
		// test if the functions return array equals the expected one
		assertArrayEquals(new int[] {5, 5}, Classifier.countClasses(instances));
	}

}
