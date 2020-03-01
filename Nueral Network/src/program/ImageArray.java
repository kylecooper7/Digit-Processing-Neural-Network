package program;

public class ImageArray {
protected int[][] twoDArray;
protected int theLabel;
protected int[] oneDArray;
public ImageArray(int[][] pixelArray, int[] onedArray, int label){
	
theLabel = label;
twoDArray = pixelArray;
oneDArray = onedArray;
}
public int[][] getTwoDArray() {
	return twoDArray;
}
public void setTwoDArray(int[][] twoDArray) {
	this.twoDArray = twoDArray;
}
public int getTheLabel() {
	return theLabel;
}
public void setTheLabel(int theLabel) {
	this.theLabel = theLabel;
}
public int[] getOneDArray() {
	return oneDArray;
}
public void setOneDArray(int[] oneDArray) {
	this.oneDArray = oneDArray;
}
}
