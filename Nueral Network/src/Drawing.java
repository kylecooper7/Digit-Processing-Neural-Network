import java.awt.Canvas;
import java.awt.Color;
import java.awt.Graphics;
import javax.swing.*;
public class Drawing extends Canvas
	{
		protected int[][] pixeys;
		protected int scale;
		public Drawing(int[][] pixels, int scaleFactor) {
			pixeys = pixels;
			scale = scaleFactor;
		}
	
		public void paint(Graphics g) {
			
			for(int i = 0; i < pixeys.length; i++) {
				for(int j = 0; j < pixeys[i].length; j++) {
					
					Color t = new Color(pixeys[i][j]);

				g.setColor(t);
				g.fillRect(scale * j,scale * i, scale, scale);
				
				
						
					
				}
			}
				
			
			
		}

	}
