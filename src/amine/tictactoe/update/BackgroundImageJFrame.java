package amine.tictactoe.update;

import javax.swing.*;

import java.awt.*;

class BackgroundImageJFrame extends JFrame
{
/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
JButton b1;
JLabel l1;
	public BackgroundImageJFrame()
	{
	setTitle("Background Color for JFrame");
	setSize(400,400);
	setLocationRelativeTo(null);
	setDefaultCloseOperation(EXIT_ON_CLOSE);
	setVisible(true);
/*
	One way
	-----------------
	setLayout(new BorderLayout());
	JLabel background=new JLabel(new ImageIcon("C:\\Users\\Computer\\Downloads\\colorful design.png"));
	add(background);
	background.setLayout(new FlowLayout());
	l1=new JLabel("Here is a button");
	b1=new JButton("I am a button");
	background.add(l1);
	background.add(b1);
*/
// Another way
	setLayout(new BorderLayout());
	setContentPane(new JLabel(new ImageIcon("C:\\Users\\amine\\OneDrive\\Desktop\\2.png")));
	setLayout(new FlowLayout());
	l1=new JLabel("Here is a button");
	b1=new JButton("I am a button");
	add(l1);
	add(b1);
	// Just for refresh :) Not optional!
	setSize(399,399);
	setSize(400,400);
	}
	/*public static void main(String args[])
	{
	new BackgroundImageJFrame();*/
	
}
