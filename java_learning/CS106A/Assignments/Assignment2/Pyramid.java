/*
 * File: Pyramid.java
 * Name: 
 * Section Leader: 
 * ------------------
 * This file is the starter file for the Pyramid problem.
 * It includes definitions of the constants that match the
 * sample run in the assignment, but you should make sure
 * that changing these values causes the generated display
 * to change accordingly.
 */

import acm.graphics.*;
import acm.program.*;
import java.awt.*;

public class Pyramid extends GraphicsProgram {

/** Width of each brick in pixels */
	private static final int BRICK_WIDTH = 30;

/** Width of each brick in pixels */
	private static final int BRICK_HEIGHT = 12;

/** Number of bricks in the base of the pyramid */
	private static final int BRICKS_IN_BASE = 14;
	
	public void run() {
		/* You fill this in. */
		
		//number in each row
			int n=1;
			for(int a=BRICKS_IN_BASE; a>0; a--){
				for(int b=0; b<a;b++){
					double j = ((getWidth()- a*BRICK_WIDTH))/2.0+b*BRICK_WIDTH;
					double i = getHeight()-BRICK_HEIGHT*n;
					GRect BRICK = new GRect(j,i,BRICK_WIDTH,BRICK_HEIGHT);
					add(BRICK);
				}
				n=n+1;
			}		
}}


