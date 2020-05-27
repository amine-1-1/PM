package amine.tictactoe.game;

import java.awt.Color;

import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.event.MouseEvent;
import java.util.Random;



import amine.tictactoe.TICTACTOE;
import amine.tictactoe.graphics.Renderer;
import amine.tictactoe.graphics.Sprite;
import amine.tictactoe.input.Mouse;

public class Game {
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
	public Game() {
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
		xpos=Mouse.getX()/32;
		ypos=Mouse.getY()/32;
		position =xpos+ypos*boardWidth;
		if (boardState[position]==0) {
			boardState[position] =turn;
			chekForGameOver();
			if (gameover)return;
			if(turn==1) turn=2;
			else turn= 1;
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
		Renderer.renderBackground();
		
		for(int i=0 ;i< boardState.length;i++) {
			if (boardState[i] == 0)continue ;
			else if (boardState[i] ==1)Renderer.renderSprite(Sprite.cross1, (i%3)*32, (i/3)*32);
			else Renderer.renderSprite(Sprite.circrle1,(i%3)*32, (i/3)*32);
		}
		
		for(int i=0;i<TICTACTOE.pixels.length;i++) {
			TICTACTOE.pixels[i] =Renderer.pixels[i];
			
		}
		
	}
	public void renderText(Graphics2D g) {
		if(!gameover)return;
		String winner="";
		if(turn ==1) winner ="player 1 wins" ;
		if(turn ==2) winner ="player 2 wins" ;
		if(turn ==0) winner ="it's a  tie" ;
		
		//setImage(new GreenfootImage("Game Over", 48, Color.WHITE, Color.BLACK));
		try {
			Thread.sleep(50);
		} catch (InterruptedException e) {
			
			e.printStackTrace();
		}
		g.setFont(new Font("Arial",0,60));
		g.setColor(Color.BLUE);
		//g.setBackground(Color.white);
		//g.fillRect(boardWidth, ypos, ypos, xpos);
		
		//g.setRenderingHint(RenderingHints.KEY_ANTIALIASING,RenderingHints.VALUE_ANTIALIAS_ON);
		
		int  w= g.getFontMetrics().stringWidth(winner) /2;
		g.drawString(winner,TICTACTOE.WIDTH*TICTACTOE.scale/2-w ,TICTACTOE.HIEGH*TICTACTOE.scale/2 );	
		
	}
		
	

}
