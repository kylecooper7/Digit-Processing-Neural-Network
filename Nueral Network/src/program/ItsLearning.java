package program;


import java.io.BufferedWriter;
import java.io.FileOutputStream;
import java.io.FileWriter;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;

import java.io.IOException;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.util.ArrayList;

import useful_methods.MyMethods;

public class ItsLearning {
	static int batchSize = 20;
	static int numberOfBatches;
	static int lOA = 0;
	
	public static void makeItLearn(Network net, int numOfBatches) throws IOException {
		int leftOff = getLeftOffAt(); 
		lOA = leftOff;
		numberOfBatches = numOfBatches;
		ArrayList<ImageArray> allTheData = Runner.getTrainingData( leftOff, batchSize * numOfBatches);
		if(allTheData.size() < numOfBatches * batchSize) {
			leftOff = 0;
			lOA = leftOff;
			setLeftOffAt(0);
			allTheData = Runner.getTrainingData( leftOff, batchSize * numOfBatches);
		}
		for(int batch = 0; batch < numOfBatches; batch++) {
			double[][] bigListOutputs = new double[batchSize][];
			int[] bigListExpected = new int[batchSize];
			int counter = 0;
			
		for(int g = 0; g < batchSize; g++) {
			
			bigListOutputs[counter] = net.runTheNetwork(allTheData.get(batch * batchSize + g).getOneDArray());
			bigListExpected[counter] = allTheData.get(batch * batchSize + g).getTheLabel();
			
			counter++;
		}
		
		backpropogate(bigListExpected, bigListOutputs, net);
		
		}
		setLeftOffAt(leftOff + numOfBatches * batchSize);
		Runner.commitToGit();
		
	}

	public static void backpropogate(int[] expectedData, double[][] actualData, Network net) throws IOException{
		// stuff for the DataPanel
		double theSum = 0;
		int numRight = 0;
		
		for(int i = 0; i < actualData.length; i++) {
			double max = 0;
			int maxIndex = -1;
			double[] expectations = expectedData(expectedData[i]);
			for(int j = 0; j < actualData[i].length; j++) {
				if(actualData[i][j] > max) {
					max = actualData[i][j];
					maxIndex = j;
				}
				theSum += Math.pow(expectations[j] - actualData[i][j], 2);
			}
			if(maxIndex == expectedData[i]) {
				numRight++;
			}
		}
		double batchCost = theSum / (2 * batchSize);
		DataPanel.setDataPanel(batchCost, numRight);
		
		
		// stuff for the DataPanel^^^^^^^^^^^^^^^^^^^^^
		
		
		Nueron[][] theNetworkArray = net.getTheNetworkArray();
		
		
		
		for(int n = 0; n < theNetworkArray[theNetworkArray.length - 1].length; n++) {
			Double theSummy = 0.0;
			Double summy2 = 0.0;
			for(int b = 0; b < batchSize; b++) {
				summy2 += MyMethods.inverseModifiedSigmoid(actualData[b][n], Network.sigmoidModifier);
				theSummy += actualData[b][n] - expectedData(expectedData[b])[n];
			}
			Double avgZValue = summy2 / batchSize;
			Double activationError = theSummy/batchSize;
			// this could cause some problems
			((ReceptorNueron) theNetworkArray[theNetworkArray.length - 1][n]).getBpi().setError(activationError * MyMethods.modifiedSigmoidDerivative(avgZValue, Network.sigmoidModifier));
		}
		
		
		
		
		for(int layer = theNetworkArray.length - 2; layer > 0; layer--) {
			for(int nueron = 0; nueron < theNetworkArray[layer].length; nueron++) {
				Double zAv = 0.0;
				for(int b = 0; b < batchSize; b++) {
					zAv += MyMethods.inverseModifiedSigmoid(theNetworkArray[layer][nueron].getValue(), Network.sigmoidModifier);
					
				}
				zAv /= batchSize;
				
				Double sumError = 0.0;
				for(int n = 0; n < theNetworkArray[layer + 1].length; n++) {
					sumError += ((ReceptorNueron)theNetworkArray[layer + 1][n]).getWeights()[nueron] * ((ReceptorNueron)theNetworkArray[layer + 1][n]).getBpi().getError();
				}
				((ReceptorNueron)theNetworkArray[layer][nueron]).getBpi().setError(sumError * MyMethods.inverseModifiedSigmoid(zAv, Network.sigmoidModifier));
				
				
			}
		}
		
		for(int layer = 1; layer < theNetworkArray.length; layer++) {
			for(int nueron = 0; nueron < theNetworkArray[layer].length; nueron++) {
				((ReceptorNueron)theNetworkArray[layer][nueron]).getBpi().setBiasError(((ReceptorNueron)theNetworkArray[layer][nueron]).getBpi().getError());
				 
				 Double[] weightErrors = new Double[theNetworkArray[layer - 1].length];
				 for(int n = 0; n < weightErrors.length; n++) {
					 weightErrors[n] = ((ReceptorNueron)theNetworkArray[layer][nueron]).getBpi().getError() * theNetworkArray[layer - 1][n].getValue();
				 }
				 
				 ((ReceptorNueron)theNetworkArray[layer][nueron]).getBpi().setWeightError(weightErrors);
			}
		}
		
		
		
		
		
		
		net.setTheNetworkArray(theNetworkArray);
		
		
		
		
		
		
		
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
	
	
	
	public static void setLeftOffAt(Integer numby) throws IOException 
	{
		//var
		String fileName = "";

		
		
		//setFile
		if(Runner.onJacksComputer == true)
		{
			fileName = "JLOA.txt";
		}
		else
		{
			fileName = "KLOA.txt";
		}
		//FileWriter fileWriter = new FileWriter(fileName, true);
		PrintWriter writer = new PrintWriter(fileName);
        writer.print(numby.toString());
        writer.close();
		
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

	public static double modifiedSigmoidDerivative(Double d, Double modifier) {
		double sig = MyMethods.modifiedSigmoid(d, modifier);
		double diggidy = sig * (1.0 - sig);
		
		return (Double) (diggidy/modifier);
		
	}
	
}