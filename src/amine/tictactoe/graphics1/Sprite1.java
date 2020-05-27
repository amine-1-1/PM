package amine.tictactoe.graphics1;
import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;

public class Sprite1 {
	public int width,hieght;
	public int[] pixels ;
	public static Sprite1 cross1 = new Sprite1("/cross1.png");
	public static Sprite1 circrle1 = new Sprite1("/circle1.png");
	public static Sprite1 bg = new Sprite1("/bg.png");
	
	
	public Sprite1(String path) {
		try {
			BufferedImage image = ImageIO.read(Sprite1.class.getResource(path));
			width =image.getWidth();
			hieght = image.getHeight();
			pixels = image.getRGB(0, 0, width, hieght, null, 0, width);
			}catch(IOException e ) {
			e.printStackTrace();
		}
	}

}
