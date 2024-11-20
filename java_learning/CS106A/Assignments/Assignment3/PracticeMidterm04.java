
import acm.program.*; 
import acm.util.RandomGenerator;
import acm.graphics.*;
import java.awt.*;
import java.awt.event.*;

	
	public class PracticeMidterm04 extends GraphicsProgram { 
		public static final int SQSIZE = 75;
		public static final int NCOLS = 7;
		public static final int NROWS = 3;
		public static final int APPLICATION_WIDTH = SQSIZE * NCOLS;
		public static final int APPLICATION_HEIGHT = SQSIZE * NROWS;
		public static final int DX = 75;
		public static final int DY = 75;
		
		public void run() { 
			setup();
			addMouseListeners();
		}
		
		private void setup(){
			frog = new GImage("frog.gif");
			double w = frog.getWidth();
			double h = frog.getHeight();
			frog.scale(SQSIZE / w, SQSIZE / h);
			frog.setLocation( NCOLS / 2 * SQSIZE, SQSIZE * (NROWS - 1));
			add(frog);
		}
		
		public void mouseClicked(MouseEvent e){
			center = new GPoint(frog.getX() + SQSIZE / 2.0, frog.getY() + SQSIZE / 2.0);
			vertical = Math.abs(e.getY() - center.getY());
			horizontal = Math.abs(e.getX() - center.getX());
			if(vertical > horizontal){
				if(e.getY() - center.getY() > 0){
					if(center.getY() + SQSIZE * 1.5 <= APPLICATION_HEIGHT){
						frog.move(0, DY);
					}
				}
				else{
					if(center.getY() - SQSIZE * 1.5 >= 0){
						frog.move(0, -DY);
					}
				}
			}
			else{
				if(e.getX() - center.getX() > 0){
					if(center.getX() + SQSIZE * 1.5 <= APPLICATION_WIDTH){
						frog.move(DX, 0);
					}
				}
				else{
					if(center.getX() - SQSIZE * 1.5 >= 0){
						frog.move(-DX, 0);
					}
				}
			}
		}

		private GImage frog;
		private GPoint center;
		private double vertical;
		private double horizontal;
		}