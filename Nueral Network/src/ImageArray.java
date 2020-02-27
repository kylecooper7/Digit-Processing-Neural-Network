
public class ImageArray {
protected int[][] twoDArray;
protected int theLabel;
public ImageArray(int[][] pixelArray, int label){
	
theLabel = label;
twoDArray = pixelArray;
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
}
