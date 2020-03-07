package program;

import java.awt.Canvas;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.awt.image.DataBufferByte;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.concurrent.TimeUnit;
import javax.imageio.ImageIO;
import javax.swing.*;
import useful_methods.MyMethods;



public class Runner
	{
static boolean onJacksComputer = true;

		public static void main(String[] args) throws IOException
			{
						//ignore
						if(onJacksComputer) {
							Network.fileLocation = "C:\\Users\\JackPaul\\git\\DigitNetwork\\Nueral Network\\";
						}

				// Network Settings
			int numberOfLayers = 4;
			int[] lengthOfLayers = {784, 64, 32, 10};
			
			String dataFileName = "KylesNetwork";
			if(onJacksComputer) {
				dataFileName = "JacksNetwork";
			}
			
				// Creation of Network
			Network myFirstNetwork = new Network(numberOfLayers, lengthOfLayers, dataFileName);
			
				// The Actual Stuff
			
			ItsLearning.makeItLearn(myFirstNetwork, 4);
			
			
//			for(ImageArray i: getTrainingData(MyMethods.randomInt(1, 1000), 30)) {
//				
//				printJFrame(i.getTwoDArray(), 7);
//				System.out.println(i.getTheLabel() + ":");
//				double[] theOutput = myFirstNetwork.runTheNetwork(i.getOneDArray());
//				for(Double d: theOutput) {
//				System.out.print(d + ", ");	
//				}
//				System.out.println();
//				System.out.println();
//				
//			}
			
			

			
			
			

//			Img thepic = new Img("a6.png", 1);
//			printJFrame(thepic, 10);
//			
//OutputNeuron theOutput = new OutputNeuron();
//		
//			theOutput.setValue(2.4);
//System.out.println(theOutput.getValue());
//theOutput.setTheValue();
//System.out.println(theOutput.getValue());
//			MySQLAccess dao = new MySQLAccess();
//	        try {
//				dao.readDataBase("vogella_blog");
//			} catch (Exception e) {
//				// TODO Auto-generated catch block
//				e.printStackTrace();
//			}

//String[] commands = {"cd /usr/local/mysql/bin/;ls", "./mysql -u root password \'KCkicks07\'"};
//String logout = "\\q";
//runCommand(logout);
//	


	}

	public static double sigmoid(Double d) {
		return (1 / (1 + Math.pow(Math.E, -d)));
	}

	public static double sigmoid(int d) {
		return (1 / (1 + Math.pow(Math.E, -d)));
	}

	public static void commitToGit() throws IOException {
		if (onJacksComputer) {
			Runtime run = Runtime.getRuntime();
			run.exec("C:\\Users\\JackPaul\\git\\DigitNetwork\\Nueral Network\\NetworkCommit.bat");
		} else {
			MyMethods.runCommand("sh /Users/kyle/git/Digit-Processing/Nueral Network/commitNetwork.sh");

		}
	}

	public static double[] sigmoidLayer(Double[][] weights, Double[] previousLayerValues, Double[] biasses) {
		double[] results = new double[biasses.length];
		for (int i = 0; i < weights.length; i++) {
			Double d = 0.0;
			for (int j = 0; j < weights[i].length; j++) {
				d += weights[i][j] * previousLayerValues[j];
			}
			d += biasses[i];
			results[i] = sigmoid(d);
		}
		return results;
	}

	public static void printJFrame(Img image, int scaleFactor) {
		int[][] data = image.findImg();

		JFrame frame = new JFrame("The JFrame");
		Canvas canvas = new Drawing(data, scaleFactor);
		canvas.setSize(250, 250);
		frame.add(canvas);
		frame.pack();
		frame.setVisible(true);

	}

	public static void printJFrame(int[][] data, int scaleFactor) {

		JFrame frame = new JFrame("The JFrame");
		Canvas canvas = new Drawing(data, scaleFactor);
		canvas.setSize(250, 250);
		frame.add(canvas);
		frame.pack();
		frame.setVisible(true);

	}

	public static ArrayList<ImageArray> getTrainingData(int start, int unloadCap) {
		ArrayList<ImageArray> theFiles = new ArrayList<ImageArray>();
		BufferedReader csvReader = null;
		int counter1 = 0;
		try {
			csvReader = new BufferedReader(new FileReader("bin/resources/mnist_train.csv"));
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		String row = null;
		try {

			row = csvReader.readLine();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		while (row != null && counter1 < unloadCap + start) {
			if (counter1 >= start) {
				String[] data = row.split(",");
				int[] intArray = new int[data.length - 1];
				//
				// System.out.println(data.length -1);
				//
				for (int w = 0; w < intArray.length; w++) {
					intArray[w] = Integer.parseInt(data[w + 1]);
				}
				// do something with the data
				int counter = 0;
				int dimension = (int) Math.pow(data.length - 1, .5);
				int[][] pixels = new int[dimension][dimension];
				for (int i = 0; i < pixels.length; i++) {
					for (int j = 0; j < pixels[i].length; j++) {
						counter++;
						pixels[i][j] = Integer.parseInt(data[counter]);
					}
				}
				theFiles.add(new ImageArray(pixels, intArray, Integer.parseInt(data[0])));

			}
			counter1++;

			try {
				row = csvReader.readLine();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

		}
		try {
			csvReader.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return theFiles;
	}

//		public static ArrayList<ImageArray> unloadDigitFiles (int unloadCap) {
//			FileInputStream inImage = null;
//			FileInputStream inLabel = null;
//			
////			TheURL urlImage = new TheURL("/resources/train-images-idx3-ubyte.gz");
////			TheURL urlLabel = new TheURL("/resources/train-labels-idx1-ubyte.gz");
//
////			String inputImagePath = urlImage.getUrl().getPath();
////			String inputLabelPath = urlLabel.getUrl().getPath();
//			String inputImagePath = "bin/resources/train-images-idx3-ubyte.gz";
//			String inputLabelPath = "bin/resources/train-labels-idx1-ubyte.gz";
//System.out.println("wjkdjskd");
////			String outputPath = "CBIR_Project/images/MNIST_Database_ARGB/";
////
////			int[] hashMap = new int[10]; 
//			ArrayList<ImageArray> theFiles= new ArrayList<ImageArray>();
//
//			try {
//			    inImage = new FileInputStream(inputImagePath);
//			    inLabel = new FileInputStream(inputLabelPath);
//
//			    int magicNumberImages = (inImage.read() << 24) | (inImage.read() << 16) | (inImage.read() << 8) | (inImage.read());
//			    int numberOfImages = (inImage.read() << 24) | (inImage.read() << 16) | (inImage.read() << 8) | (inImage.read());
//			    int numberOfRows  = (inImage.read() << 24) | (inImage.read() << 16) | (inImage.read() << 8) | (inImage.read());
//			    int numberOfColumns = (inImage.read() << 24) | (inImage.read() << 16) | (inImage.read() << 8) | (inImage.read());
//			    numberOfRows  = 28;
//			    numberOfColumns = 28;
//			    int magicNumberLabels = (inLabel.read() << 24) | (inLabel.read() << 16) | (inLabel.read() << 8) | (inLabel.read());
//			    int numberOfLabels = (inLabel.read() << 24) | (inLabel.read() << 16) | (inLabel.read() << 8) | (inLabel.read());
//System.out.println(numberOfRows + " " + numberOfColumns);
////			    BufferedImage image = new BufferedImage(numberOfColumns, numberOfRows, BufferedImage.TYPE_INT_ARGB);
//			    int numberOfPixels = numberOfRows * numberOfColumns;
//			    int[][] imgPixels = new int[numberOfRows][numberOfColumns];
//
//			    for(int i = 0; i < numberOfImages && i < unloadCap; i++) {
//
//			        if(i % 100 == 0) {System.out.println("Number of images extracted: " + i);}
//
//			        for(int k = 0; k < numberOfRows; k++) {
//			        	 for(int p = 0; p < numberOfColumns; p++) {
//			        		 int gray = 255 - inImage.read();
//					           imgPixels[k][p] = 0xFF000000 | (gray<<16) | (gray<<8) | gray;	
//					            
//					        }
//			            
//			        }
//			        
//			        
////			        for(int p = 0; p < numberOfPixels; p++) {
////			        	
////			            int gray = 255 - inImage.read();
////			            imgPixels[p] = 0xFF000000 | (gray<<16) | (gray<<8) | gray;
////			        }
//
////			        image.setRGB(0, 0, numberOfColumns, numberOfRows, imgPixels, 0, numberOfColumns);
//
//			        int label = inLabel.read();
//			        theFiles.add(new ImageArray(imgPixels, label));
//
//			        
//			        
////			        hashMap[label]++;
////			        File outputfile = new File(outputPath + label + "_0" + hashMap[label] + ".png");
////
////			        ImageIO.write(image, "png", outputfile);
//			    }
//			    
//
//			} catch (FileNotFoundException e) {
//			    // TODO Auto-generated catch block
//			    e.printStackTrace();
//			} catch (IOException e) {
//			    // TODO Auto-generated catch block
//			    e.printStackTrace();
//			} finally {
//			    if (inImage != null) {
//			        try {
//			            inImage.close();
//			        } catch (IOException e) {
//			            // TODO Auto-generated catch block
//			            e.printStackTrace();
//			        }
//			    }
//			    if (inLabel != null) {
//			        try {
//			            inLabel.close();
//			        } catch (IOException e) {
//			            // TODO Auto-generated catch block
//			            e.printStackTrace();
//			        }
//			    }
//			}
//			return theFiles;
//			}

}
