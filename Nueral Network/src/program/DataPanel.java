package program;

import java.text.DecimalFormat;
import java.time.LocalDateTime;
import java.util.Scanner;

public class DataPanel 
{
	private static String[] inputs = new String[16];
	private static String[] DataPanel;
	private static int totalCycles;
	private static int totalRight; 
	public static String spaceBuffer = "                                   ";
	private static LocalDateTime prevTime= LocalDateTime.now();  
	private static int batchesGoneThrough = 0;
	private static int onBatch = 0;
	
	private static int updateTimeEveryNBatches = 1;
	
	public static void setScreenSize()
	{
		//variables
		int rows = 20;
		int cols = 65;
		String defaltPixel = " ";
		String boarderPixel = "#";
		String enterToContinue;
		String[][] setScreen = new String[rows][cols];
		Scanner blankInput = new Scanner(System.in);
		
		//action
		for (int r = 0; r < rows; r++)
		{
			for (int c = 0; c < cols; c++)
			{			
				setScreen [r][c] = defaltPixel;
			}
		}
		setScreen [0][0] = boarderPixel;
		setScreen [1][0] = boarderPixel;
		setScreen [0][1] = boarderPixel;
		setScreen [0][cols - 1] = boarderPixel;
		setScreen [1][cols - 1] = boarderPixel;
		setScreen [0][cols - 2] = boarderPixel;
		setScreen [rows - 1][0] = boarderPixel;
		setScreen [rows - 2][0] = boarderPixel;
		setScreen [rows - 1][1] = boarderPixel;
		setScreen [rows - 1][cols - 1] = boarderPixel;
		setScreen [rows - 2][cols - 1] = boarderPixel;
		setScreen [rows - 1][cols - 2] = boarderPixel;
		for (int r = 0; r < rows; r++)
		{
			for (int c = 0; c < cols; c++)
			{
				System.out.print(setScreen [r][c]);	
			}
			System.out.print("\n");
		}
		System.out.println("\nThis program requires a specific console window size.");
		System.out.println("Please adjust the console window size so that the displayed corners above fill the screen.");
		System.out.print("\nAfterwards press enter to continue.");
		enterToContinue = blankInput.nextLine();
		System.out.println();
		System.out.println(spaceBuffer + "                           LOADING...");
		System.out.println();
	}
	
	public static void setDataPanel(Double batchCost, int numRight)
	{
//		inputs = new String[] {"10321", "100", "09.56", "100", "229", "96.28", "12764", "89999", "56.03", "45.63", "02", "12", "16", "00122", "04000"};

		onBatch ++;
		
		
		
		if(onBatch % updateTimeEveryNBatches + 1 == 1) {
		
		String[] timeStuff= getTimeStuff();
		inputs[10] = timeStuff[0];
		inputs[11] = timeStuff[1];
		inputs[12] = timeStuff[2];
		totalCycles += ItsLearning.batchSize;
		}
		// adjust + ItsLaerning.lOA if you want place in database or just since run
		int numOfCycles = totalCycles + ItsLearning.lOA;
//		int numOfCycles = totalCycles + ItsLearning.lOA;
		
		inputs[0] = format(numOfCycles, 5);
		inputs[1] = format(ItsLearning.batchSize, 3);
		inputs[2] = format(batchCost, 5);
		inputs[3] = format(numRight, 3);
		inputs[4] = format(ItsLearning.batchSize, 3);
		Double accuracy = ((double) (numRight))/((double) ItsLearning.batchSize) * 100.00;
		inputs[5] = format(accuracy, 5);
		
		totalRight += numRight;
		inputs[6] = format(totalRight, 5);
		inputs[7] = format(numOfCycles, 5);
		Double AverageAccuracy = ((double) (totalRight))/((double) numOfCycles) * 100.00;
		inputs[8] = format(AverageAccuracy, 5);
		
		batchesGoneThrough++;
		inputs[13] = format(batchesGoneThrough, 5);
		inputs[14] = format(ItsLearning.numberOfBatches, 5);
		Double percentCompletion = ((double) (batchesGoneThrough))/((double) ItsLearning.numberOfBatches) * 100.00;
		inputs[9] = format(percentCompletion, 5);
		
		inputs[15] = makeLoadingBar(percentCompletion);
		
		
		DataPanel = new String[] {
				spaceBuffer + " _______________________________________________________________ ",
				spaceBuffer + "|                                                               |",
				spaceBuffer + "|  HDP Network Training                   Time: H-" + inputs[10] + " M-" + inputs[11] + " S-" + inputs[12] + "  |",
				spaceBuffer + "|_______________________________________________________________|",
				spaceBuffer + "|                                                               |",
				spaceBuffer + "|  Cycles:                                               " + inputs[0] + "  |",
				spaceBuffer + "|  Batch Size:                                             " + inputs[1] + "  |",
				spaceBuffer + "|  Batch Cost:                                           " + inputs[2] + "  |",
				spaceBuffer + "|                                                               |",
				spaceBuffer + "|  Accuracy:                                           " + inputs[3] + "/" + inputs[4] + "  |",
				spaceBuffer + "|                                                      " + inputs[5] + " %  |",
				spaceBuffer + "|                                                               |",
				spaceBuffer + "|  Average Accuracy:                               " + inputs[6] + "/" + inputs[7] + "  |",
				spaceBuffer + "|                                                      " + inputs[8] + " %  |",
				spaceBuffer + "|                                                               |",
				spaceBuffer + "|                                                               |",
				spaceBuffer + "|   |" + inputs[15] + "|   |",
				spaceBuffer + "|                                                               |",
				spaceBuffer + "|  Batches: " + inputs[13] + "/" + inputs[14] + "                                " + inputs[9] + " %  |",
				spaceBuffer + "|_______________________________________________________________|"};
	
	// we might want to change this, but for now we can just have it automatically print it.
	printDataPanel();
	
	}
	
	public static String makeLoadingBar(Double percentCompletion) {
		String theBar = "";
		int numLines = (int) ((percentCompletion / 100) * 55);
		for(int i = 0; i < numLines; i++) {
			theBar += "|";
		}
		for(int i = 0; i < 55 - numLines; i++) {
			theBar += "-";
		}
		
		return theBar;
	}

	public static String[] getTimeStuff() {
		
		String theTimeStuff[] = new String[3];
		LocalDateTime currentTime= LocalDateTime.now();  
		int hourDif = currentTime.getHour() - prevTime.getHour();
		int minDif = currentTime.getMinute() - prevTime.getMinute();
		int secDif = currentTime.getSecond() - prevTime.getSecond();
		
		int totalDif = hourDif * 3600 + minDif * 60 + secDif;
		
		int hourDif1 = totalDif / 3600;
		int minDif1 = (totalDif % 3600) / 60;
		int secDif1 = (totalDif % 3600) % 60;
		
		theTimeStuff[0] = format(hourDif1, 2);
		theTimeStuff[1] = format(minDif1, 2);
		theTimeStuff[2] = format(secDif1, 2);
		if(onBatch == 1) {
		prevTime = currentTime;
		}
		return theTimeStuff;
	}
public static String format(Integer toBeFormatted, int numberOfDigits) {
	String asString = toBeFormatted.toString();
	if(asString.length() > numberOfDigits) {
		// we might wanna change this
		asString = asString.substring(0, numberOfDigits);
	}
	else if(asString.length() < numberOfDigits) {
		String sillyString = "";
		for(int i = 0; i < numberOfDigits - asString.length(); i++) {
			sillyString += "0";
		}
		asString = sillyString + asString;
	}
	
	
	return asString;
}
public static String format(Double toBeFormatted, int numberOfDigits) {
	String pattern = "";
	for(int i = 0; i < numberOfDigits - 3; i++) {
		pattern += " ";
	}
	pattern += ".00";
	DecimalFormat decimalFormat = new DecimalFormat("00.00");
	String asString = decimalFormat.format(toBeFormatted);
	if(asString.length() > numberOfDigits) {
		// we might wanna change this
		asString = asString.substring(0, numberOfDigits);
	}
	else if(asString.length() < numberOfDigits) {
		String sillyString = "";
		for(int i = 0; i < numberOfDigits - asString.length(); i++) {
			sillyString += "0";
		}
		asString = sillyString + asString;
	}
	
	return asString;
}

	public static void printDataPanel()
	{
		System.out.println();
		for(int i = 0; i < DataPanel.length - 1; i++) {
		System.out.println(DataPanel[i]);
		}
		System.out.print(DataPanel[DataPanel.length - 1]);
	}
	
  /* _______________________________________________________________
    |                                                               |
	|  HDP Network Training                   Time: H-00 M-00 S-00  |
	|_______________________________________________________________|
	|                                                               |
	|  Cycles:                                               00000  |
	|  Batch Size:                                             000  |
	|  Batch Cost:                                           00000  |
	|                                                               |
	|  Accuracy:                                           000/000  |
	|                                                      00000 %  |
	|                                                               |
	|  Average Accuracy:                               00000/00000  |
	|                                                      00000 %  |
	|                                                               |
	|                                                               |
	|   |-------------------------------------------------------|   |
	|                                                               |
	|  Batches: 00000/00000                                00000 %  |
	|_______________________________________________________________|
	                                                                 */
}




