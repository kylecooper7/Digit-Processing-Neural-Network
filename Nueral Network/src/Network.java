import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.nio.ByteBuffer;

import useful_methods.MyMethods;

public class Network {
protected int numberOfLayers;
protected int[] lengthOfLayers;
protected int lengthOfDataFile;
protected File theFile;
protected Nueron[][][] theNetworkArray;
static String fileLocation = "/Users/kyle/git/Digit-Processing/Nueral Network/";
	public Network(int number_Of_Layers, int[] length_Of_Layers, String dataFileName) throws IOException {
//     number of layers and length of layers includes input layer, length of layers goes input to output
	numberOfLayers = number_Of_Layers;
	lengthOfLayers = length_Of_Layers;
	int lengthOfDataFile2 = 1 + length_Of_Layers.length ;
	for(int i = 1; i < length_Of_Layers.length; i++) {
		lengthOfDataFile2 +=  length_Of_Layers[i] * ( 1 + length_Of_Layers[i - 1]);
	}
	lengthOfDataFile = lengthOfDataFile2;
	
	
	theFile = new File(fileLocation + dataFileName);
	
	if(! theFile.exists()) {
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
	weights[i] = MyMethods.randomInt(1, 1000);
}
		return null;
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
	
	
	public void updateDataFile(Nueron[][][] theNetworkArray2) {
		//need to implement
		
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
	public Nueron[][][] getTheNetworkArray() {
		Nueron[][][] theNetworkArray2 = new Nueron[numberOfLayers][][];
		// implement
		
		
		return theNetworkArray2;
	}
	public void setTheNetworkArray(Nueron[][][] theNetworkArray) {
		updateDataFile(theNetworkArray);
		
	}
	
	
}
