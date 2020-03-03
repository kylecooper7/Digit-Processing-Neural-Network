package program;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
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
		int lefty = 0;
		String line = null;
		
		String fileName = "KLOA.txt";
		if(Runner.onJacksComputer) {
			fileName = "JLOA.txt";
		}
		try
			{
				FileReader fileReader = new FileReader(fileName);
				BufferedReader bufferedReader = new BufferedReader(fileReader);
				
				while ((line = bufferedReader.readLine()) != null)
					{
						lefty = Integer.parseInt(line);
						
					}
				bufferedReader.close();
			} catch (FileNotFoundException ex)
			{
				System.out.println("Unable to open file '" + fileName + "'");
			} catch (IOException ex)
			{
				System.out.println("Error reading file '" + fileName + "'");
			}
		
	return lefty;
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