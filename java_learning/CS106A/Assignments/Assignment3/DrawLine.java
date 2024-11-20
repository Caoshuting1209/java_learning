/* 
 * File: Hogwarts.java 
 * ------------------- 
 * This program is just testing your understanding of parameter passing. 
 */ 
	import acm.program.*; 
	import acm.graphics.*;
	import java.awt.event.*;

	
	public class DrawLine extends GraphicsProgram { 
		public void run() { 
			addMouseListeners();
			}	
		
		public void mousePressed(MouseEvent e){
			startX = e.getX();
			startY = e.getY();
			currentLine = new GLine(startX, startY,startX, startY);
			add(currentLine);
		}
		
		public void mouseDragged(MouseEvent e){
			double x = e.getX();
			double y = e.getY();
			currentLine.setEndPoint(x, y);
		}
	

		private double startX;
		private double startY;
		private GLine currentLine;
	}