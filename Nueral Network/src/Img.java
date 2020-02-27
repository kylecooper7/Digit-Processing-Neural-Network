import java.awt.Canvas;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.awt.image.DataBufferByte;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.URL;

import javax.imageio.ImageIO;
import javax.swing.JFrame;

public class Img extends JFrame{
protected String imageName;
protected int digitLabel;
	public Img(String imgNam, int digLabel) {
	imageName = imgNam;
	digitLabel = digLabel;
}
int[][] findImg() {
	URL url = getClass().getResource("/resources/" + imageName);
	
	try {
		BufferedImage image = ImageIO.read(url);
		return convertTo2DWithoutUsingGetRGB(image);
//		for(int i = 0; i < convertTo2DWithoutUsingGetRGB(image).length; i++) {
//			for(int j = 0; j < convertTo2DWithoutUsingGetRGB(image)[i].length; j++) {
//			System.out.print(convertTo2DWithoutUsingGetRGB(image)[i][j] + " ");
//			}
//			System.out.println("");
//		}
	} catch (IOException e) {
		
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
	return null;
}
public String getImageName() {
	return imageName;
}
public void setImageName(String imageName) {
	this.imageName = imageName;
}
public int getDigitLabel() {
	return digitLabel;
}
public void setDigitLabel(int digitLabel) {
	this.digitLabel = digitLabel;
}
public static int[][] convertTo2DWithoutUsingGetRGB(BufferedImage image) {

    final byte[] pixels = ((DataBufferByte) image.getRaster().getDataBuffer()).getData();
    final int width = image.getWidth();
    final int height = image.getHeight();
    final boolean hasAlphaChannel = image.getAlphaRaster() != null;

    int[][] result = new int[height][width];
    if (hasAlphaChannel) {
       final int pixelLength = 4;
       for (int pixel = 0, row = 0, col = 0; pixel + 3 < pixels.length; pixel += pixelLength) {
          int argb = 0;
          argb += (((int) pixels[pixel] & 0xff) << 24); // alpha
          argb += ((int) pixels[pixel + 1] & 0xff); // blue
          argb += (((int) pixels[pixel + 2] & 0xff) << 8); // green
          argb += (((int) pixels[pixel + 3] & 0xff) << 16); // red
          result[row][col] = argb;
          col++;
          if (col == width) {
             col = 0;
             row++;
          }
       }
    } else {
       final int pixelLength = 3;
       for (int pixel = 0, row = 0, col = 0; pixel + 2 < pixels.length; pixel += pixelLength) {
          int argb = 0;
          argb += -16777216; // 255 alpha
          argb += ((int) pixels[pixel] & 0xff); // blue
          argb += (((int) pixels[pixel + 1] & 0xff) << 8); // green
          argb += (((int) pixels[pixel + 2] & 0xff) << 16); // red
          result[row][col] = argb;
          col++;
          if (col == width) {
             col = 0;
             row++;
          }
       }
    }

    return result;
 }


}
