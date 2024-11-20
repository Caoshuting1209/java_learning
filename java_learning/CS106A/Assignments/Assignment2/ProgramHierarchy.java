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

public class ProgramHierarchy extends GraphicsProgram {	
	
	private static final double classBoxWidth = 170;
	private static final double classBoxHeight = 40;
	
	public void run() {
		/* You fill this in. */
		double width = getWidth();
		double height = getHeight();
		double x_outline = 100;
		double y_outline = height / 3;
		double outlineWidth = width - 2 * x_outline;
		double outlineHeight = y_outline;
		GRect outline = new GRect(x_outline,y_outline,outlineWidth,outlineHeight);
		
		//add rectangles
		double x1 = x_outline + outlineWidth/2 - classBoxWidth/2 ;
		double y1 = y_outline;
		GRect Rect_1 = new GRect(x1,y1,classBoxWidth,classBoxHeight);
		
		double x2 = x1;
		double y2 = y_outline + outlineHeight - classBoxHeight ;
		GRect Rect_2 = new GRect(x2,y2,classBoxWidth,classBoxHeight);
		
		double x3 = x_outline;
		double y3 = y2;
		GRect Rect_3 = new GRect(x3,y3,classBoxWidth,classBoxHeight);
		
		double x4 = x_outline + outlineWidth - classBoxWidth;
		double y4 = y2;
		GRect Rect_4 = new GRect(x4,y4,classBoxWidth,classBoxHeight);
		add(Rect_1);
		add(Rect_2);
		add(Rect_3);
		add(Rect_4);
		
		//add labels
		GLabel label_1 = new GLabel("Program");
		label_1.setFont("Arial-20");
		double label_1_Width = label_1.getWidth();
		double labelAscent = label_1.getAscent();
		double x_label_1 = x1 + classBoxWidth / 2 - label_1_Width / 2;
		double y_label_1 = y1 + classBoxHeight / 2 + labelAscent / 2;
		add(label_1, x_label_1, y_label_1);
		
		GLabel label_2 = new GLabel("ConsoleProgram");
		label_2.setFont("Arial-20");
		double label_2_Width = label_2.getWidth();
		double x_label_2 = x2 + classBoxWidth / 2 - label_2_Width / 2;
		double y_label_2 = y2 + classBoxHeight / 2 + labelAscent / 2;
		add(label_2, x_label_2, y_label_2);
		
		GLabel label_3 = new GLabel("GraphicsProgram");
		label_3.setFont("Arial-20");
		double label_3_Width = label_3.getWidth();
		double x_label_3 = x3 + classBoxWidth / 2 - label_3_Width / 2;
		double y_label_3 = y3 + classBoxHeight / 2 + labelAscent / 2;
		add(label_3, x_label_3, y_label_3);
		
		GLabel label_4 = new GLabel("DialogProgram");
		label_4.setFont("Arial-20");
		double label_4_Width = label_4.getWidth();
		double x_label_4 = x4 + classBoxWidth / 2 - label_4_Width / 2;
		double y_label_4 = y4 + classBoxHeight / 2 + labelAscent / 2;
		add(label_4, x_label_4, y_label_4);
		
		//add lines
		GLine line_1 = new GLine(x1 + classBoxWidth/2,y1 + classBoxHeight,x3 + classBoxWidth/2,y2);
		add(line_1);
		GLine line_2 = new GLine(x1 + classBoxWidth/2,y1 + classBoxHeight,x2 + classBoxWidth/2,y2);
		add(line_2);
		GLine line_3 = new GLine(x1 + classBoxWidth/2,y1 + classBoxHeight,x4 + classBoxWidth/2,y2);
		add(line_3);
	}
}

