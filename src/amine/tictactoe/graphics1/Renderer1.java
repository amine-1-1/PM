package amine.tictactoe.graphics1;


import amine.tictactoe.graphics1.Sprite1;
import amine.tictactoe.original.TICTACTOR_original;

public class Renderer1 {
	
		private static int  w =TICTACTOR_original.WIDTH,h =TICTACTOR_original.HIEGH;
		public static int[]pixels = new int[w*h];
		
		public static void renderBackground () {
			for (int y=0 ;y<h;y++) {
				for (int x=0 ; x<w ;x++) {
					int col= Sprite1.bg.pixels[x+y*w];
					if(col== 0xffff00ff)pixels [x+y*w]= 0xfff4f4f4;
					else pixels [x+y * w] =col ;
				}
			}
		}

		
		public static void renderSprite1(Sprite1 s, int xp, int yp) {
			if (xp<-s.width || yp< -s.hieght || xp >= w || yp>=h)return ;
			for (int y=0 ; y <s.hieght;y++) {
				int yy=y+yp;
				if(yy>=h || yy<0) continue;
				for (int x =0 ;x< s.width ;x++) {
					int xx=x+xp;
					if(xx>=w || xx<0) continue;
					
					int col = s.pixels[x+y *s.width];
					if (col ==0xfffd00ff) continue ;
					pixels [xx + yy *w]=col;
					
				}
			
		}

	}
}


