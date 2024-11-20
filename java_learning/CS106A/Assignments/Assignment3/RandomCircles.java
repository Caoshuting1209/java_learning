/* 
 * File: Hogwarts.java 
 * ------------------- 
 * This program is just testing your understanding of parameter passing. 
 */ 
	import acm.program.*; 
import acm.util.RandomGenerator;
import acm.graphics.*;

	
	public class RandomCircles extends GraphicsProgram { 
		public void run() { 
			for(int i = 0; i < 10; ++i){
				radius = rgen.nextDouble(0,50);
				x_Circle = rgen.nextDouble(0,getWidth() - 2 * radius);
				y_Circle = rgen.nextDouble(0,getHeight() - 2 * radius);
				GOval randomCircle = new GOval(x_Circle, y_Circle, 2 * radius, 2 * radius);
				randomCircle.setFilled(true);
				randomCircle.setColor(rgen.nextColor());
				add(randomCircle);
			}	
	}

	private RandomGenerator rgen = RandomGenerator.getInstance();
	private double x_Circle;
	private double y_Circle;
	private double radius;
	}