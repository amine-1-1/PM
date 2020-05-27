package amine.tictactoe.game1;

import java.awt.Color;
import java.awt.Component;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.event.MouseEvent;
import java.util.Random;

import javax.swing.JOptionPane;

import amine.tictactoe.original.TICTACTOR_original;
import amine.tictactoe.graphics1.Sprite1;
import amine.tictactoe.TICTACTOE.GUI;
import amine.tictactoe.graphics1.Renderer1;
import amine.tictactoe.input1.Mouse;

public class Game1 {
	private int[]boardState;
	private int boardWidth =3 ;
	private int [][]winPositions = {
			{0,1,2},
			{3,4,5},
			{6,7,8},
			{0,3,6},
			{1,4,7},
			{2,5,8},
			{0,4,8},
			{2,4,6}
			
	};
	private int xpos =-1 ,ypos =-1;
	private int position =-1;
	private int turn;
	boolean gameover;
	Random rand = new Random();
	public Game1() {
		init();
		
	}
	public Object getwinpositions() {
		return(winPositions);
	}
	public Object boardState() {
		return boardState;
	}
	
	public void init() {
		boardState = new int[9];
		turn = 1;
		gameover =false;	
		
	}
	private void makeAMoveplayervsplayer() {
		int i=0;
		if(turn==1) {
			
		xpos=Mouse.getX()/32;
		ypos=Mouse.getY()/32;

		position =xpos+ypos*boardWidth;
		turn=2;
		if (boardState[position]==0) {
			boardState[position] =turn;
			chekForGameOver();
			if (gameover)return;
			
	    }
		}else if (turn==2 ){
			
			int n = rand.nextInt(9);
			position=n;
			turn=1;
			if (boardState[position]==0) {
				boardState[position] =turn;
				chekForGameOver();
				if (gameover)return;
				
		    }
		}
		
	}			
	
	private void chekForGameOver() {
		boolean end;
		for(int i=0 ;i<winPositions.length;i++) {
			end= true;
			for(int j=0 ; j<winPositions[i].length;j++) {
				if (boardState [winPositions[i][j]]!= turn) {
					end =false;
				}				
				
			}
			if (end) {
				gameover=true;
				break;
			}			
		}
		boolean tie=true ;
		for(int  i=0 ; i<boardState.length;i++) {
			if(boardState[i] == 0) {
				tie =false;				
			}
		}
		if(tie) {
			gameover=true ;
			turn =0 ;
		}
	}

	public void update() {
		if (gameover) {
			if (Mouse.buttonUp(MouseEvent.BUTTON1)) {
				init();
			}
			return;
		}
		if (Mouse.buttonUp(MouseEvent.BUTTON1)) {
			makeAMoveplayervsplayer();
		}
		
	}
	

	public void render() {
		Renderer1.renderBackground();
		
		for(int i=0 ;i< boardState.length;i++) {
			if (boardState[i] == 0)continue ;
			else if (boardState[i] ==1)Renderer1.renderSprite1(Sprite1.cross1, (i%3)*32, (i/3)*32);
			else Renderer1.renderSprite1(Sprite1.circrle1,(i%3)*32, (i/3)*32);
		}
		
		for(int i=0;i<TICTACTOR_original.pixels.length;i++) {
			TICTACTOR_original.pixels[i] =Renderer1.pixels[i];
			
		}
		
	}
	public void renderText(Graphics2D g) {
		if(!gameover)return;
		String winner="";
		if(turn ==2) winner ="player 1 wins" ;
		if(turn ==1) winner ="computer wins " ;
		if(turn ==0) winner ="it's a tie" ;
		
		
		try {
			Thread.sleep(50);
		} catch (InterruptedException e) {
			
			e.printStackTrace();
		}
		g.setFont(new Font("Arial",0,60));
		g.setColor(Color.BLUE);
		
		int  w= g.getFontMetrics().stringWidth(winner) /2;
		g.drawString(winner,TICTACTOR_original.WIDTH*TICTACTOR_original.scale/2-w ,TICTACTOR_original.HIEGH*TICTACTOR_original.scale/2 );	
		
           
		
	}
		
	

}
