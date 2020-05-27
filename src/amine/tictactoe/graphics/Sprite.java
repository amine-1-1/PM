package amine.tictactoe.graphics;

import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;

public class Sprite {
	public int width,hieght;
	public int[] pixels ;
	public static Sprite cross1 = new Sprite("/cross1.png");
	public static Sprite circrle1 = new Sprite("/circle1.png");
	public static Sprite bg = new Sprite("/bg.png");
	
	
	public Sprite(String path) {
		try {
			BufferedImage image = ImageIO.read(Sprite.class.getResource(path));
			width =image.getWidth();
			hieght = image.getHeight();
			pixels = image.getRGB(0, 0, width, hieght, null, 0, width);
			}catch(IOException e ) {
			e.printStackTrace();
		}
	}

}


