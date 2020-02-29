import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.nio.ByteBuffer;

public class Network {
protected int numberOfLayers;
protected int[] lengthOfLayers;
protected Nueron[][][] theNetworkArray;
static String fileLocation = "/Users/kyle/git/Digit-Processing/Nueral Network/";
	public Network(int number_Of_Layers, int[] length_Of_Layers, String dataFileName) throws IOException {
//     number of layers and length of layers includes input layer, length of layers goes input to output
	numberOfLayers = number_Of_Layers;
	lengthOfLayers = length_Of_Layers;
	File file = new File(fileLocation + dataFileName);
	if(! file.exists()) {
		newFile(file, number_Of_Layers, length_Of_Layers);
	}
	theNetworkArray = getTheNetworkArray();
	
	
	
	
}
	
	
	public static void newFile(File file, int number_Of_Layers, int[] length_Of_Layers) throws IOException {
		file.createNewFile();
		OutputStream outputStream = new FileOutputStream(file);
		int lengthOfDataFile = 1 + length_Of_Layers.length ;
		for(int i = 1; i < length_Of_Layers.length; i++) {
			lengthOfDataFile +=  length_Of_Layers[i] * ( 1 + length_Of_Layers[i - 1]);
		}
		int[] intArray = new int[lengthOfDataFile];
		intArray[0] = number_Of_Layers;
		for(int i = 1; i < length_Of_Layers.length + 1; i++) {
			intArray[i] = length_Of_Layers[i - 1];
		}
		
		int counter = 1 + length_Of_Layers.length;
		
		for(int i = 1; i < length_Of_Layers.length; i++) {
		
				for(int j = 0; j < length_Of_Layers[i]; j++) {
					int bias = 0;
					int[] weights = new int[length_Of_Layers[i-1]];
// implement initial randomization of weights and bias
					
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
	int value = (byteArray[i + 3] << (Byte.SIZE * 3));
    value |= (byteArray[i + 2] & 0xFF) << (Byte.SIZE * 2);
    value |= (byteArray[i + 1] & 0xFF) << (Byte.SIZE * 1);
    value |= (byteArray[i] & 0xFF);
    ints[counter1] = value;
    counter1 ++;
	}
    
	return ints;
}
	
	
	public void updateDataFile() {
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
		
		
		
		
		return theNetworkArray;
	}
	public void setTheNetworkArray(Nueron[][][] theNetworkArray) {
		
		
		
		
		
		this.theNetworkArray = theNetworkArray;
	}
	
	
}
