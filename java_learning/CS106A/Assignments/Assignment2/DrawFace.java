/*
 * File: ProgramHierarchy.java
 * Name: 
 * Section Leader: 
 * ---------------------------
 * This file is the starter file for the ProgramHierarchy problem.
 */

import acm.graphics.*;
import acm.program.*;
import java.awt.*;

public class DrawFace extends GraphicsProgram {	
	
	private static final int HEAD_WIDTH =  200;
	private static final int HEAD_HEIGHT = 300;
	private static final int EYE_REDIUS = 30;
	private static final int MOUTH_WIDTH = 80;
	private static final int MOUTH_HEIGHT = 50;
	
	public void run() {
		/* You fill this in. */
		double x_HEAD = (double)(getWidth() / 2);
		double y_HEAD = (double)(getHeight() / 2);
		HEAD(x_HEAD, y_HEAD, HEAD_WIDTH , HEAD_HEIGHT);
		
		double x_LEFT_EYE = x_HEAD - (double)(HEAD_WIDTH / 4);
		double y_LEFT_EYE = y_HEAD - (double)(HEAD_HEIGHT / 4);
		EYE(x_LEFT_EYE, y_LEFT_EYE, EYE_REDIUS);
			
		double x_RIGHT_EYE = x_HEAD + (double)(HEAD_WIDTH / 4);
		double y_RIGHT_EYE = y_HEAD - (double)(HEAD_HEIGHT / 4);
		EYE(x_RIGHT_EYE, y_RIGHT_EYE, EYE_REDIUS);
		
		double x_MOUTH = x_HEAD;
		double y_MOUTH = y_HEAD + (double)(HEAD_HEIGHT / 4);
		MOUTH(x_MOUTH, y_MOUTH, MOUTH_WIDTH, MOUTH_HEIGHT);
	}
	
	private GRect HEAD(double x,double y, double w, double h){
		GRect HEAD = new GRect(x - (double)(HEAD_WIDTH / 2), y - (double)(HEAD_HEIGHT / 2), w, h);
		HEAD.setFilled(true);
		HEAD.setFillColor(Color.GRAY);
		add(HEAD);
		return HEAD;
	}
	
	private GOval EYE(double x, double y, double r){
		GOval EYE = new GOval((x - r), (y - r), 2 * r, 2 * r);
		EYE.setColor(Color.YELLOW);
		EYE.setFilled(true);
		EYE.setFillColor(Color.YELLOW);
		add(EYE);
		return EYE;
	}
	
	private GRect MOUTH(double x,double y, double w, double h){
		GRect MOUTH = new GRect(x - (double)(MOUTH_WIDTH / 2), y - (double)(MOUTH_HEIGHT / 2), w, h);
		MOUTH.setColor(Color.WHITE);
		MOUTH.setFilled(true);
		MOUTH.setFillColor(Color.WHITE);
		add(MOUTH);
		return MOUTH;
	}
}

