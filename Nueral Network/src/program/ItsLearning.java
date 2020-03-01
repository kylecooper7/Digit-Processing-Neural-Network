package program;

import java.io.FileOutputStream;
import java.io.OutputStream;

import useful_methods.MyMethods;

public class ItsLearning {
	static int batchSize = 50;

	public static void makeItLearn(Network net, int numOfBatches) {
		int leftOff = getLeftOffAt(); 
		for(int batch = 0; batch < numOfBatches; batch++) {
			
			
			
		for(ImageArray i: Runner.getTrainingData(batch * batchSize + leftOff, batchSize)) {
			
			System.out.println(i.getTheLabel() + ":");
			double[] theOutput = net.runTheNetwork(i.getOneDArray());
			for(Double d: theOutput) {
			System.out.print(d + ", ");	
			}
			
			
		}
		
		
		}
	}

	public static Nueron[][] backpropogate(double[] expectedData, Network net){

		
		
		return null;
	}
	
	
	public static int getLeftOffAt() {
		return 0;
		
	}
	public static void setLeftOffAt(int num) {

		
	}
	public static double[] expectedData(int num) {
		double[][] dd = {
						{1.0, 0, 0, 0, 0, 0, 0, 0, 0, 0}, 
						{0, 1.0, 0, 0, 0, 0, 0, 0, 0, 0},
						{0, 0, 1.0, 0, 0, 0, 0, 0, 0, 0},
						{0, 0, 0, 1.0, 0, 0, 0, 0, 0, 0},
						{0, 0, 0, 0, 1.0, 0, 0, 0, 0, 0},
						{0, 0, 0, 0, 0, 1.0, 0, 0, 0, 0},
						{0, 0, 0, 0, 0, 0, 1.0, 0, 0, 0},
						{0, 0, 0, 0, 0, 0, 0, 1.0, 0, 0},
						{0, 0, 0, 0, 0, 0, 0, 0, 1.0, 0},
						{0, 0, 0, 0, 0, 0, 0, 0, 0, 1.0},
						};
		return dd[num];
	}
	
	
}