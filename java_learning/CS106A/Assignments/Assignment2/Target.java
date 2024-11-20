/*
 * File: Target.java
 * Name: 
 * Section Leader: 
 * -----------------
 * This file is the starter file for the Target problem.
 */

import acm.graphics.*;
import acm.program.*;
import java.awt.*;

public class Target extends GraphicsProgram {	
	
	private static final double radius_1 = 72;
	private static final double radius_2 = 72*0.65;
	private static final double radius_3 = 72*0.3;
	
	public void run() {
		/* You fill this in. */
		double x_Center = getWidth()/2.0;
		double y_Center = getHeight()/2.0;
		GOval Circle_1 = new GOval(x_Center-radius_1, y_Center-radius_1,radius_1*2,radius_1*2);
		Circle_1.setFilled(true);
		Circle_1.setColor(Color.RED);
		GOval Circle_2 = new GOval(x_Center-radius_2, y_Center-radius_2,radius_2*2,radius_2*2);
		Circle_2.setFilled(true);
		Circle_2.setColor(Color.WHITE);
		GOval Circle_3 = new GOval(x_Center-radius_3,y_Center-radius_3,radius_3*2,radius_3*2);
		Circle_3.setFilled(true);
		Circle_3.setColor(Color.RED);
		add(Circle_1);
		add(Circle_2);
		add(Circle_3);
	}
}
