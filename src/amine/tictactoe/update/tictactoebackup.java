package amine.tictactoe.update;


import java.awt.BasicStroke;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.FlowLayout;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.GridLayout;
import java.awt.Rectangle;
import java.awt.Shape;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.geom.Ellipse2D;
import java.awt.geom.Line2D;
import java.awt.geom.Rectangle2D;
import java.util.ArrayList;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;

//import amine.tictactoe.Ultimate.Tictactoe_ultimate.DrawingPanel;

public class tictactoebackup extends JFrame implements Runnable {
	public static final int ROWS = 3; // ROWS by COLS cells
	public static final int COLS = 3;

    private JButton oButton, xButton;//to add two buttons 
    public JPanel board;
    public ArrayList<Shape> shapes;
	private ArrayList drawingPanels;
	private String[][] mask =
	      {{"a", "z", "e", "r","t", "y", "u", "i", "o"},
	    	  {"a", "z", "e", "r","t", "y", "u", "i", "o"},
	    	  {"a", "z", "e", "r","t", "y", "u", "i", "o"},
	    	  {"a", "z", "e", "r","t", "y", "u", "i", "o"},
	    	  {"a", "z", "e", "r","t", "y", "u", "i", "o"},
	    	  {"a", "z", "e", "r","t", "y", "u", "i", "o"},
	    	  {"a", "z", "e", "r","t", "y", "u", "i", "o"},
	    	  {"a", "z", "e", "r","t", "y", "u", "i", "o"},
	    	  {"a", "z", "e", "r","t", "y", "u", "i", "o"}};
	
	public tictactoebackup() {
    	this.drawingPanels = new ArrayList<>();
        shapes = new ArrayList<Shape>();
        JPanel topPanel = new JPanel();
        topPanel.setLayout(new GridLayout(3, 3, 10, 10));
        for (int i = 0; i < 12; i++) {
        	Board drawingPanel = new Board();
        	topPanel.add(drawingPanel);
            drawingPanels.add(drawingPanel);
        }     
        add(topPanel);
        
       // setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }
	public void run() {
        try {
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        } catch (ClassNotFoundException | InstantiationException | IllegalAccessException | UnsupportedLookAndFeelException ex) {
            ex.printStackTrace();
        }

        JFrame frame = new tictactoebackup();
        frame.setTitle(" Tic Tac Toe");
        
        frame.pack();
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }
	
	public class Board extends JPanel {

        private Shape selectedCell = null;
		private int turn=1;

        public Board() {
        addMouseListener(new MouseAdapter() {
        @Override
        public void mouseClicked(MouseEvent e) {
            int w = getWidth();
            int h = getHeight();
            selectedCell = null;
            for (int col = 0; col < COLS; col++) {
                for (int row = 0; row < ROWS; row++) {
                	
                	//if (mask[col][row]=="z") {
                    int x = (w / 3) * col;
                    int y = (h / 3) * row;
                    Rectangle cell2 = new Rectangle(x, y, w / 3, h / 3); 
                    Shape Circle = new Ellipse2D.Double(x, y, w / 3, h / 3);
                    if (Circle.contains(e.getPoint())&& turn==1) {
                        System.out.println("player1");
                        selectedCell = Circle;
                        repaint(); 
                        turn=2;
                        break;
                    }
                    if(cell2.contains(e.getPoint())&&turn==2) {
                    	System.out.println("Player2");
                        selectedCell = cell2;
                        repaint(); 
                        turn=1;
                        break;
                    	
                    }
                }
                	     }            
            
        }
    });
        }
        public void MakeMove(Graphics g) {
        	Graphics2D g2d = (Graphics2D) g;
    		if (selectedCell != null) {
            	if(turn==1) {
                g2d.setColor(Color.BLUE);
               g2d.fill(selectedCell);
                
            
            }else if(turn==2) {
            	g2d.setColor(Color.RED);
                g2d.fill(selectedCell);
            }
            	
            }
    		
    	}

        @Override
        public Dimension getPreferredSize() {
            return new Dimension(170, 170);
        }

        @Override
        public void paintComponent(Graphics g) {
            super.paintComponent(g);
            g.setColor(Color.WHITE);
            g.fillRect(0, 0, getWidth(), getHeight());
            
            Graphics2D g2d = (Graphics2D) g;
            int w = getWidth();
            int h = getHeight();

            g2d.setPaint(Color.WHITE);
            g2d.fill(new Rectangle2D.Double(0, 0, w, h));
            MakeMove(g);

            
            
            
            g2d.setStroke(new BasicStroke(5.0F));
            Rectangle r = new Rectangle(0, 0, getWidth(), getHeight());
            int sectionWidth = r.width / 3;
            int sectionHeight = r.height / 3;
           
  
            g2d.setColor(Color.BLACK);
            g2d.drawLine(r.x, r.y + sectionHeight, 
                    r.x + r.width, r.y + sectionHeight);
            g2d.drawLine(r.x, r.y + sectionHeight + sectionHeight, 
                    r.x + r.width, r.y + sectionHeight + sectionHeight);
            g2d.drawLine(r.x + sectionWidth, r.y, r.x + sectionWidth, 
                    r.y + r.height);
            g2d.drawLine(r.x + sectionWidth + sectionWidth, r.y, 
                    r.x + sectionWidth + sectionWidth, r.y + r.height);
            
            
            Shape square = new Rectangle2D.Float(10, 10, 50, 50); 
           Shape Circle = new Ellipse2D.Double(0, 100, 30, 30);
            shapes.add(square);
            shapes.add(Circle);
            
            
        }
        
    }

    /*public static void main(String[] args) {
    	SwingUtilities.invokeLater(new tictactoebackup());
    }*/
    	
       

}
