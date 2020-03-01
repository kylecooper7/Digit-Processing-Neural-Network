import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.nio.ByteBuffer;

import useful_methods.MyMethods;

public class Network {
protected int numberOfLayers;
protected int[] lengthOfLayers;
protected int lengthOfDataFile;
protected int[] lengthData;
protected File theFile;
protected Nueron[][] theNetworkArray;
static String fileLocation = "C:\\Users\\JackPaul\\git\\Digit-Processing-Neural-Network-1\\Nueral Network\\"; 
	public Network(int number_Of_Layers, int[] length_Of_Layers, String dataFileName) throws IOException {
//     number of layers and length of layers includes input layer, length of layers goes input to output
	numberOfLayers = number_Of_Layers;
	lengthOfLayers = length_Of_Layers;
	int lengthOfDataFile2 = 1 + length_Of_Layers.length ;
	int[] lengthData2 = new int[lengthOfDataFile2];
	lengthData2[0] = number_Of_Layers;
	
	for(int i = 0; i < length_Of_Layers.length; i++) {
	lengthData2[i + 1]	= length_Of_Layers[i];
	}
	lengthData = lengthData2;
	for(int i = 1; i < length_Of_Layers.length; i++) {
		lengthOfDataFile2 +=  length_Of_Layers[i] * ( 1 + length_Of_Layers[i - 1]);
	}
	lengthOfDataFile = lengthOfDataFile2;
	
	
	theFile = new File(fileLocation + dataFileName);
	
	if(! (theFile.exists())) {
		newFile();
	}
	theNetworkArray = getTheNetworkArray();
	
	
	
}
	
	
	public void newFile() throws IOException {
		theFile.createNewFile();
		
		OutputStream outputStream = new FileOutputStream(theFile);
		
		int[] intArray = new int[lengthOfDataFile];
		intArray[0] = numberOfLayers;
		for(int i = 1; i < lengthOfLayers.length + 1; i++) {
			intArray[i] = lengthOfLayers[i - 1];
		}
		
		int counter = 1 + lengthOfLayers.length;
		
		for(int i = 1; i < lengthOfLayers.length; i++) {
		
				for(int j = 0; j < lengthOfLayers[i]; j++) {
					int bias = 1;
					
// implement initial randomization of weights and bias
					
					int[] weights = randomizeWeights(lengthOfLayers[i-1]);
					intArray[counter] = bias;
					counter++;
					for(int k = 0; k < weights.length; k++) {
					intArray[counter] = weights[k];
					counter++;
					}
				}
		}
// change from int array to byte array and outputStream.write the byte Array
		byte[] dataAsBytes = intArrayToByteArray(intArray);
		outputStream.write(dataAsBytes);
		outputStream.close();
	}
	


public int[] randomizeWeights(int lengthOfLayer) {
int[] weights = new int[lengthOfLayer];
for(int i = 0; i < lengthOfLayer; i++) {
	// implement this
	weights[i] = MyMethods.randomInt(-10, 10);
}
		return weights;
	}


public int getLengthOfDataFile() {
		return lengthOfDataFile;
	}


	public void setLengthOfDataFile(int lengthOfDataFile) {
		this.lengthOfDataFile = lengthOfDataFile;
	}


	public File getTheFile() {
		return theFile;
	}


	public void setTheFile(File theFile) {
		this.theFile = theFile;
	}


	public static String getFileLocation() {
		return fileLocation;
	}


	public static void setFileLocation(String fileLocation) {
		Network.fileLocation = fileLocation;
	}


public static byte[] intArrayToByteArray(int[] intArray) {
	byte[] bytes = new byte[intArray.length * 4];
	
	int counter1 = 0;
	for(Integer i: intArray) {
		ByteBuffer bb = ByteBuffer.allocate(4); 
		bb.putInt(i);
		byte[] beets = bb.array();
		for(byte b: beets) {
		bytes[counter1] = b;
		counter1++;
		}
	}
	return bytes;
}

public static int[] byteArrayToIntArray(byte[] byteArray) {
	int[] ints = new int[byteArray.length /4];
	int counter1 = 0;
	for(int i = 0; i < byteArray.length; i+=4) {
		byte[] bytes = new byte[4];
		bytes[0] = byteArray[i];
		bytes[1] = byteArray[i + 1];
		bytes[2] = byteArray[i + 2];
		bytes[3] = byteArray[i + 3];
		int value = ByteBuffer.wrap(bytes).getInt();
    ints[counter1] = value;
    counter1 ++;
	}
    
	return ints;
}
	
	
	public void updateDataFile(Nueron[][] theNetworkArray2) throws IOException {
		//need to implement
		int[] toTheFile = new int[lengthOfDataFile];
		
		for(int i = 0; i < lengthData.length; i++) {
			toTheFile[i] = lengthData[i];
		}
		int counter = lengthData.length;
		for(int layer = 1; layer < theNetworkArray2.length; layer++) {
			for(int nueron = 0; nueron < theNetworkArray2[layer].length; nueron++) {
				toTheFile[counter] = ((ReceptorNueron)theNetworkArray2[layer][nueron]).getBias();
				counter++;
				for(int i = 0; i < ((ReceptorNueron)theNetworkArray2[layer][nueron]).getWeights().length; i++) {
					toTheFile[counter] = ((ReceptorNueron)theNetworkArray2[layer][nueron]).getWeights()[i];
					counter++;
				}
				
				
			}
		}
		OutputStream outputStream = new FileOutputStream(theFile);
		
		outputStream.write(intArrayToByteArray(toTheFile));
		outputStream.close();
		
		
	}
	
	public int getNumberOfLayers() {
		return numberOfLayers;
	}
	public void setNumberOfLayers(int numberOfLayers) {
		this.numberOfLayers = numberOfLayers;
	}
	public int[] getLengthOfLayers() {
		return lengthOfLayers;
	}
	public void setLengthOfLayers(int[] lengthOfLayers) {
		this.lengthOfLayers = lengthOfLayers;
	}
	public Nueron[][] getTheNetworkArray() throws IOException {
		Nueron[][] theNetworkArray2 = new Nueron[numberOfLayers][];
		for(int i = 0; i < numberOfLayers; i++) {
			theNetworkArray2[i] = new Nueron[lengthOfLayers[i] ];
		}
		// implement
		// input nuerons have value 0 by default
		for(int i = 0; i < theNetworkArray2[0].length; i++) {
			theNetworkArray2[0][i] = new InputNueron(0, 0);
		}
		
        InputStream inputStream = new FileInputStream(theFile);
		byte[] bytesRead = new byte[lengthOfDataFile * 4];
		inputStream.read(bytesRead,  0,  lengthOfDataFile * 4);
        inputStream.close();
        int[] intsRead = byteArrayToIntArray(bytesRead);
        int counter = numberOfLayers + 1;
    	for(int layer = 1; layer < theNetworkArray2.length; layer++) {
			for(int nueron = 0; nueron < theNetworkArray2[layer].length; nueron++) {
				int bias = intsRead[counter];
				counter++;
				int weights[] = new int[lengthOfLayers[layer - 1]];
				for(int i = 0; i < lengthOfLayers[layer - 1]; i++) {
					weights[i] = intsRead[counter];
					counter++;
				}
				theNetworkArray2[layer][nueron] = new ReceptorNueron(layer, 0, bias, weights);
				
				
			}
		}
	
        
        
		return theNetworkArray2;
	}
	public void setTheNetworkArray(Nueron[][] theNetworkArray) throws IOException {
		updateDataFile(theNetworkArray);
		
	}
	public void setInputValues(int[] data) {
		for(int i = 0; i < theNetworkArray[0].length; i++) {
			this.theNetworkArray[0][i].setValue(MyMethods.sigmoid(data[i]));
		}
	}
	public double[] runTheNetwork(int[] inputData) {
		
		if(inputData.length != lengthOfLayers[0]) {
			System.out.println("Your inputData Array is not the right size.");
			return null;
		}
		setInputValues(inputData);
		for(int layer = 1; layer < theNetworkArray.length; layer++) {
			for(int nueron = 0; nueron < theNetworkArray[layer].length; nueron++) {
				double sumCount = ((ReceptorNueron)theNetworkArray[layer][nueron]).getBias();
				for(int i = 0; i < lengthOfLayers[layer - 1]; i++) {
					double toBeAdded = theNetworkArray[layer - 1][i].getValue() * ((ReceptorNueron)theNetworkArray[layer][nueron]).getWeights()[i];
					
					sumCount += toBeAdded;
				}
				
				double theValue = MyMethods.sigmoid(sumCount);
			
				((ReceptorNueron)theNetworkArray[layer][nueron]).setValue(theValue);
//				System.out.print( ((ReceptorNueron)theNetworkArray[layer][nueron]).getValue()+ ", ");
			}
		}
		double[] toBeReturned = new double[lengthOfLayers[numberOfLayers - 1]];
		for(int i = 0; i < toBeReturned.length; i++) {
			toBeReturned[i] = theNetworkArray[numberOfLayers -1][i].getValue();
		}
		
		
		return toBeReturned;
		
	}
	
	
	
}
