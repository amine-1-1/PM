package amine.tictactoe.original;

import java.awt.BorderLayout;
import java.awt.Canvas;
import java.awt.Dimension;
import java.awt.Graphics2D;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferStrategy;
import java.awt.image.BufferedImage;
import java.awt.image.DataBufferInt;
import java.awt.Canvas;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

import amine.tictactoe.game1.Game1;
import amine.tictactoe.input1.Mouse;
@SuppressWarnings("serial")
public class TICTACTOR_original extends Canvas implements Runnable{
	public static final int WIDTH=32*3, HIEGH=32*3 ;//how big is that game
	public static float scale = 5f ;//zoom
	
	private Game1 game;
	public JFrame frame;
	private Thread thread ;
	private boolean running=false;
	Mouse mouse;
	JButton b1;
	public static BufferedImage image = new BufferedImage(WIDTH,HIEGH,BufferedImage.TYPE_INT_RGB);//same as that camera
	public static int[] pixels =((DataBufferInt) image.getRaster().getDataBuffer()).getData();
	
	
	
	public TICTACTOR_original () {
		setPreferredSize(new Dimension ((int) (WIDTH*scale) ,(int)(HIEGH*scale) ));
		frame =new JFrame();
		game=new Game1();		
		mouse = new Mouse();  
		addMouseListener(mouse);
		addMouseMotionListener(mouse);
		
		
	}
	public void start() {
		running=true;
		thread= new Thread(this,"loop");
		thread.start();
		
	}
public void stop() {
	try {
		thread.join();
	}catch(InterruptedException e) {
			e.printStackTrace();
	}
			
	}
			
    public void run() {
    	while(running) {
    		update();
        	render();
        	try {
        		thread.sleep(30);
        	}catch(InterruptedException e) {
        			e.printStackTrace();
        	}	
    	}
    	stop();
    	
	}
    public void update() {
    	game.update();
    	
    	mouse.update();
    	
    }
    public void  render() {
    	BufferStrategy bs =getBufferStrategy();
    	if (bs==null) {
    		createBufferStrategy(5);
    		return ;
    		
    	}
    	game.render();
    	
    	
    	Graphics2D g = (Graphics2D) bs.getDrawGraphics();
    	
    	g.drawImage(image, 0, 0, (int) (WIDTH*scale),(int)(HIEGH*scale), null);//to creat the lines of tictactoe
    	game.renderText(g);
    	g.dispose();
    	bs.show();   	    	
    }
	
	


public static void main(String[]args) {
	TICTACTOR_original t =new TICTACTOR_original();
     
      t.frame.add(t);
		t.frame.pack();
		t.frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		t.frame.setVisible(true);
		t.frame.setLocationRelativeTo(null);
		t.frame.setAlwaysOnTop(true);
		t.frame.setTitle("TICTACTOE:PvC");
		t.start();  
	
}
}



	     
		