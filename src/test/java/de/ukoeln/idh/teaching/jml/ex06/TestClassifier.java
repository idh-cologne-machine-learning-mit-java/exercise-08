package de.ukoeln.idh.teaching.jml.ex06;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import weka.core.Instances;
import weka.core.converters.ConverterUtils.DataSource;

public class TestClassifier {

	Classifier classifier = new Classifier();
	Instances data;
	Tree results;


	@BeforeEach
	public void setup() throws Exception{
		DataSource source = new DataSource("src/resources/germancredit/train.arff");
		data = source.getDataSet();
		if(data.classIndex() == -1){
			data.setClassIndex(data.numAttributes() - 1);
		}
		System.out.println(data.numInstances());
	}

	@Test
	public void testTrain(){
		results = classifier.train(data);
		assertThrows(IllegalArgumentException.class,()->{classifier.train(null);});
		
	}

	@Test
	public void testEntropy() {
		//illegal input
		assertThrows(IllegalArgumentException.class, ()->{classifier.entropy(null);});
		
		double H = classifier.entropy(data);
		
		assertTrue(0.0 <= H && H <= 2.0);

	}

	@Test
	public void testInformationGain() {
		assertThrows(IllegalArgumentException.class, () -> {classifier.informationGain(null, 0);});
		assertThrows(IllegalArgumentException.class, () -> {classifier.informationGain(data, -1);});
		assertThrows(IllegalArgumentException.class, ()-> {classifier.informationGain(data, data.numAttributes()+1);});

		double ig = classifier.informationGain(data, 0);
		assertTrue(0.0 <= ig && ig <= 1.0);
	}

	@Test
	public void testSubsets(){
		assertThrows(NullPointerException.class, () ->{Classifier.subsets(null, 0);});
		assertThrows(IndexOutOfBoundsException.class, () -> {Classifier.subsets(data, -1);});
		assertThrows(IndexOutOfBoundsException.class, () -> {Classifier.subsets(data, data.numAttributes()+1);});

	}

	@Test
	public void testCountClasses(){
		assertThrows(NullPointerException.class, () -> {Classifier.countClasses(null);});
		

		assertEquals(data.numClasses(), Classifier.countClasses(data).length);
	}

	@Test
	public void testGetMajority(){
		assertEquals(0, Classifier.getMajority(new int[] {2,1}));
		assertEquals(0, Classifier.getMajority(new int[] {2,2}));
		assertEquals(1, Classifier.getMajority(new int[] {1,2}));
	}
}
