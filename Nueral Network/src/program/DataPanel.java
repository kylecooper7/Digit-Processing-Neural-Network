package program;

import java.util.Scanner;

public class DataPanel 
{
	private String[][] DataPanel;
	private int totalCycles;
	private int totalRight; 
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
		System.out.println("\nThis program requires a specific consol window size.");
		System.out.println("Please ajust the consol window size so that the displayed corners above fill the screen.");
		System.out.print("\nAfterwards press enter to continue.");
		enterToContinue = blankInput.nextLine();
	}
	
	public static void setDataPanel(Double batchCost, int numRight)
	{
		
	}
	
	public static void printDataPanel()
	{
		
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
	|  Batches: 000/000                                    00000 %  |
	|_______________________________________________________________|
	                                                                 */
}



