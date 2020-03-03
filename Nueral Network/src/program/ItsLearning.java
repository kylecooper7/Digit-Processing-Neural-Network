package program;

import java.io.BufferedWriter;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.OutputStream;

import useful_methods.MyMethods;

public class ItsLearning {
	static int batchSize = 50;
	
	public static void makeItLearn(Network net, int numOfBatches) throws IOException {
		int leftOff = getLeftOffAt(); 
		for(int batch = 0; batch < numOfBatches; batch++) {
			double[][] bigListOutputs = new double[batchSize][];
			double[] bigListExpected = new double[batchSize];
			int counter = 0;
			
		for(ImageArray i: Runner.getTrainingData(batch * batchSize + leftOff, batchSize)) {
			
			bigListOutputs[counter] = net.runTheNetwork(i.getOneDArray());
			bigListExpected[counter] = i.getTheLabel();
			
			counter++;
		}
		
		backpropogate(bigListExpected, bigListOutputs, net);
		
		}
		Runner.commitToGit();
		
	}

	public static Nueron[][] backpropogate(double[] expectedData, double[][] actualData, Network net){
		

		
		return null;
	}
	
	
	public static int getLeftOffAt() {
		return 0;
		
	}
	public static void setLeftOffAt(int num) throws IOException 
	{
		//var
		String file = "";
		FileWriter fileWriter;
		BufferedWriter bufferedWriter;
		
		//setFile
		if(Runner.onJacksComputer == true)
		{
			file = "JOLA.txt";
		}
		else
		{
			file = "KOLA.txt";
		}
		fileWriter = new FileWriter(file, true);
		bufferedWriter = new BufferedWriter(fileWriter);
		
		//write
		bufferedWriter.write(num);
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