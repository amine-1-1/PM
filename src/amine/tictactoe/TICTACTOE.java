package amine.tictactoe;

import java.awt.BorderLayout;
import java.awt.Canvas;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferStrategy;
import java.awt.image.BufferedImage;
import java.awt.image.DataBufferInt;
import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.awt.Canvas;

import javax.imageio.ImageIO;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;
import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.SwingUtilities;

import amine.tictactoe.game.Game;
import amine.tictactoe.input.Mouse;
import amine.tictactoe.original.TICTACTOR_original;
import amine.tictactoe.update.tictactoebackup;

@SuppressWarnings("serial")
public class TICTACTOE extends Canvas implements Runnable{
	public static final int WIDTH=32*3, HIEGH=32*3 ;//how big is that game
	public static float scale = 5f ;//zoom
	//private Game game;
	private Game game;
	public JFrame frame;
	private Thread thread ;
	private boolean running=false;
	Mouse mouse;
	JButton b1;
	public static BufferedImage image = new BufferedImage(WIDTH,HIEGH,BufferedImage.TYPE_INT_RGB);//same as that camera
	public static int[] pixels =((DataBufferInt) image.getRaster().getDataBuffer()).getData();
	private final static String newline = "\n";
	
	
	public TICTACTOE () {
		setPreferredSize(new Dimension ((int) (WIDTH*scale) ,(int)(HIEGH*scale) ));
		frame =new JFrame();
		game=new Game();		
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
    		createBufferStrategy(3);
    		return ;
    		
    	}
    	game.render();
    	
    	
    	Graphics2D g = (Graphics2D) bs.getDrawGraphics();
    	
    	g.drawImage(image, 0, 0, (int) (WIDTH*scale),(int)(HIEGH*scale), null);//to creat the lines of tictactoe
    	//game.renderText(g);
    	game.renderText(g);
    	g.dispose();
    	bs.show();   	    	
    }
    
    public static class GUI implements ActionListener  {
    	private JLabel label;
    	private JLabel label2;
    	
    	private JButton button2;
    	private JButton button1;
    	private JButton button3;
    	private JFrame frame;
    	private JPanel panel;
    	private int count =0;
    	public GUI(){
    		label = new JLabel ("WELCOME TO TICTACTOE");
    		label2 = new JLabel ("choose the ways of play");
    		button2 =new JButton("player vs player");
    		button1 =new JButton("player vs computer");
    		button3 =new JButton("SUPER Game ");
    		
    		
    		button1.addActionListener(new ActionListener() {
    		       @Override
    		       public void actionPerformed(ActionEvent e) {
    		    	   JFrame frame = new JFrame ("TICTACTOE");    		           
    		    	   TICTACTOR_original t =new TICTACTOR_original();
    	 		        frame.getContentPane().add (t);
    	 		        t.frame.add(t);
    	 		   		t.frame.pack();
    	 		   		//t.frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    	 		   		t.frame.setVisible(true);
    	 		   		t.frame.setLocationRelativeTo(null);
    	 		   		t.frame.setAlwaysOnTop(true);
    	 		   		t.frame.setTitle("TICTACTOE:PvC");
    	 		   		t.start();   	 		           		          
    	 		       }
    		   });
    		
    		button2.addActionListener(new ActionListener() {
 		       @Override
 		       public void actionPerformed(ActionEvent e) {
 		        JFrame frame = new JFrame ("TICTACTOE");    		           
 		        TICTACTOE t =new TICTACTOE();
 		        frame.getContentPane().add (t);
 		        t.frame.add(t);
 		   		t.frame.pack();
 		   		//t.frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
 		   		t.frame.setVisible(true);
 		   		t.frame.setLocationRelativeTo(null);
 		   		t.frame.setAlwaysOnTop(true);
 		   		t.frame.setTitle("TICTACTOE:PvP");
 		   		t.start();
 		           		          

 		       }
 		   });
    		button3.addActionListener(new ActionListener() {
 		       @Override
 		       public void actionPerformed(ActionEvent e) {
 		    	       		           
 		    	  SwingUtilities.invokeLater(new tictactoebackup());
 	 		        
 	 		           	 		           		          
 	 		       }
 		   });
    		       		    		    		
    		button2.setBounds(50,100,95,30);
    		frame= new JFrame();//window
    		JLabel background=new JLabel(new ImageIcon("C:\\Users\\amine\\OneDrive\\Desktop\\2.png"));
    		
    		panel=new JPanel();//what is inside the window
    		panel.add(background);
    		panel.setBorder(BorderFactory.createEmptyBorder(100,100,100,100));
    		panel.setLayout(new GridLayout(0,1));
    		panel.add(label);
    		panel.add(label2);
    		
    		panel.add(button2);
    		panel.add(button1);
    		panel.add(button3);
    		frame.add(panel,BorderLayout.CENTER);
    		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    		frame.setTitle("TICTACTOE");   		
    		frame.setResizable(false);
    		frame.pack();    		
    		frame.setVisible(true);	
    		
    		JMenuBar menubar =new JMenuBar();
    		frame.setJMenuBar(menubar);
    		
    		JMenu file =new JMenu("File");
    		
    		menubar.add(file);
    		
    		JMenuItem exit =new JMenuItem("Exit");
    		JMenuItem play =new JMenuItem("play music");
    		JMenu help =new JMenu("Help");
    		file.add(play);
    		file.add(exit);
    		menubar.add(help);
    		JMenuItem about1 =new JMenuItem("About us");
    		JMenuItem about2 =new JMenuItem("About the project");
    		help.add(about1);
    		help.add(about2);
    		JMenuItem how =new JMenu("how to play");
    		menubar.add(how);
    		JMenuItem h2 =new JMenuItem("classic");
    		JMenuItem h1 =new JMenuItem("Super");
    	    how.add(h1);
    	    how.add(h2);
    	    exit.addActionListener(new exitaction());
    	    about1.addActionListener(new abouting1());
    	    about2.addActionListener(new abouting2());
    		h1.addActionListener(new how1());
    		h2.addActionListener(new how2());
    		play.addActionListener(new playing());
    	}
    	class exitaction implements ActionListener{
	    	 public void actionPerformed (ActionEvent e) {
	    		 System.exit(0);
	    	 }
	    	 
	     }
    	class abouting1 implements ActionListener{
    		JFrame f= new JFrame("About us"); 
    		String html = "<html>" + "This game is devoloped by" + "<br />" + 
    				"Mouhamed Amine ZAddem" + "</html>";
    		
	    	 public void actionPerformed (ActionEvent e) {
	    		 JLabel l1,l2;  
	    		    l1=new JLabel(html);  
	    		    l1.setBounds(1,1, 500,50);
	    		    l1.setFont(new Font("Serif", Font.BOLD, 17));
	    		 
	    		    f.add(l1);   
	    		    f.setSize(300,300);  
	    		    f.setLayout(null);  
	    		    f.setVisible(true);  
	    	 }
	    	 
	     }
    	class abouting2 implements ActionListener{
    		JFrame f= new JFrame("About the project"); 
    		String html =  "<html>" + "this project is done  " + "<br />" + 
    				"for the algorithmic module project " + "for Eniso  " + "<br />" + "</html>";
    		  
	    	 public void actionPerformed (ActionEvent e) {
	    		 JLabel l1,l2;  
	    		 l1=new JLabel(html);  
	    		    l1.setBounds(1,1, 500,50);
	    		    l1.setFont(new Font("Serif", Font.BOLD, 15));
	    		    f.add(l1);   
	    		    f.setSize(300,300);  
	    		    f.setLayout(null);  
	    		    f.setVisible(true);  
	    	 }
	    	 
	     }
    	class how2 implements ActionListener{
    		JFrame f= new JFrame("Super"); 
    		String html = "<html>" + "Tic-tac-toe wo players, X and O" + "<br />" + 
    				"who take turns marking the spaces in a 3×3 grid" +"</br>"+
    				" The player who succeeds in placing three of their marks"+"</br>"+
    				" in a horizontal, vertical, or diagonal row "+"</br>"+
    				" is the winner "+"</html>";
	    	 public void actionPerformed (ActionEvent e) {
	    		 JLabel l1;  
	    		 l1=new JLabel(html);  
	    		 l1.setFont(new Font("Serif", Font.BOLD, 12));
	    		    l1.setBounds(1,1, 500,50);
	    		    f.add(l1);   
	    		    f.setSize(300,300);  
	    		    f.setLocationRelativeTo(null); 
	    		    f.setLayout(null);  
	    		    f.setVisible(true);  
	    	 }
	    	 
	     }
    	class how1 implements ActionListener{
    		JFrame f= new JFrame("Classic"); 
    		String html = "<html>" + "Tic-tac-toe Super two players, X and O" + "<br />" + 
    				"who take turns marking the spaces in a 9×9 grid" +"</br>"+
    				" The player playes in diffrent spacesand the one how win 5/9 of the spaces winnes  "+"</br>"+
    				"  but there is one purticular place where if one of the players wins it "+"</br>"+
    				"  wins the game the joker space  "+"</html>";
	    	 public void actionPerformed (ActionEvent e) {
	    		   
	    		 JLabel l1;  
	    		 l1=new JLabel(html);
	    		 l1.setFont(new Font("Serif", Font.BOLD, 12));
	    		    l1.setBounds(1,1, 500,65);
	    		    f.add(l1);      
	    		    f.setSize(350,350);  
	    		    f.setLocationRelativeTo(null); 
	    		    f.setLayout(null);  
	    		    f.setVisible(true);  
	    	 }
	    	 
	    	 
	     }
    	class playing  implements ActionListener {
    		
	    	 public void actionPerformed (ActionEvent e) {
	    		 URL url = null;
				try {
					url = new URL(
					         "http://www.brainybetty.com/March2008/Music/UpbeatFunk.wav");
				} catch (MalformedURLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
       Clip clip = null;
	try {
		clip = AudioSystem.getClip();
	} catch (LineUnavailableException e1) {
		// TODO Auto-generated catch block
		e1.printStackTrace();
	}
       
       AudioInputStream ais = null;
	try {
		ais = AudioSystem.getAudioInputStream( url );
	} catch (UnsupportedAudioFileException e1) {
		// TODO Auto-generated catch block
		e1.printStackTrace();
	} catch (IOException e1) {
		// TODO Auto-generated catch block
		e1.printStackTrace();
	}
       try {
		clip.open(ais);
	} catch (LineUnavailableException e1) {
		// TODO Auto-generated catch block
		e1.printStackTrace();
	} catch (IOException e1) {
		// TODO Auto-generated catch block
		e1.printStackTrace();
	}
       clip.loop(Clip.LOOP_CONTINUOUSLY);
       SwingUtilities.invokeLater(new Runnable() {
           public void run() {
               // A GUI element to prevent the Clip's daemon Thread 
               // from terminating at the end of the main()
               JOptionPane.showMessageDialog(null, "Close to exit!");
           }
       });
	    	 }
	    	 
	    	 
	     }
    	
    
    
	public static void main(String[]args) {
		GUI G=new GUI();				
	}
	
	public void actionPerformed(ActionEvent e) {				
	}
	
	
	

}
}
