/*
 * File: Breakout.java
 * -------------------
 * Name:
 * Section Leader:
 * 
 * This file will eventually implement the game of Breakout.
 */

import acm.graphics.*;
import acm.program.*;
import acm.util.*;

import java.applet.*;
import java.awt.*;
import java.awt.event.*;

public class Breakout extends GraphicsProgram {

/** Width and height of application window in pixels */
	public static final int APPLICATION_WIDTH = 800;
	public static final int APPLICATION_HEIGHT = 800;

/** Dimensions of game board (usually the same) */
	private static final int WIDTH = APPLICATION_WIDTH;
	private static final int HEIGHT = APPLICATION_HEIGHT;

/** Dimensions of the paddle */
	private static final int PADDLE_WIDTH = 120;
	private static final int PADDLE_HEIGHT = 20;

/** Offset of the paddle up from the bottom */
	private static final int PADDLE_Y_OFFSET = 100;

/** Number of bricks per row */
	private static final int NBRICKS_PER_ROW = 10;

/** Number of rows of bricks */
	private static final int NBRICK_ROWS = 10;

/** Separation between bricks */
	private static final int BRICK_SEP = 8;

/** Width of a brick */
	private static final int BRICK_WIDTH =
	  (WIDTH - (NBRICKS_PER_ROW - 1) * BRICK_SEP) / NBRICKS_PER_ROW;

/** Height of a brick */
	private static final int BRICK_HEIGHT = 16;

/** Radius of the ball in pixels */
	private static final int BALL_RADIUS = 20;

/** Offset of the top brick row from the top */
	private static final int BRICK_Y_OFFSET = 70;

/** Number of turns */
	private static final int NTURNS = 3;
	
/** Initial x and y of the paddle */
	private static final double y_PADDLE = (HEIGHT - PADDLE_Y_OFFSET - PADDLE_HEIGHT) * 1.0;
	 private static final double x_PADDLE = (WIDTH - PADDLE_WIDTH) / 2.0;
	 
/** Initial x and y of the ball */
	private static final double x_BALL = (WIDTH / 2.0) - BALL_RADIUS;
	private static final double y_BALL = (HEIGHT / 2.0) - BALL_RADIUS;
	
/** Animation delay or pause time between ball moves */ 
	 private static final int DELAY = 10;

/** Add sounds */
	 private static final AudioClip bounceClip = MediaTools.loadAudioClip("bounce.au");
	

/* Method: run() */
/** Runs the Breakout program. */
	public void run() {
		/* You fill this in, along with any subsidiary methods */
		
		//Set up the bricks
		drawBricks();
		
		//Draw the original paddle
		drawPaddle();
		
		//Move the paddle
		addMouseListeners();
		addMouseMotionListener(this);
		
		//Game instructions
		addScoreInstruction();
		
		//Count the number of remaing bricks
		counter = NBRICK_ROWS * NBRICKS_PER_ROW;
		
		//Play the game
		for(int i = 0; i < NTURNS; ++i){
			
		//Create a ball
			if(isVictory()){
				break;
			}else{
				drawBall();
			}
			
			//set initial speed
			vx = rgen.nextDouble(1.0, 3.0); 
			if (rgen.nextBoolean(0.5)) vx = -vx;
			vy = 8.0;
		
		//Move the ball and check
			while(true){

				//Check if the ball lands
				if(BALL.getY() > HEIGHT - 2 * BALL_RADIUS) {
					remove(BALL);
					if((i < NTURNS - 1) && (2 - i > 1)){
						text = (2 - i) + " turns left";
						addText();
					}
					else if(2 - i == 1){
						text = "1 turn left";
						addText();
					}
					else{
						text = "GAME OVER";
						addText();
						text = "Your final score is " + Integrator;
						addText();
					}	
					break;
				}
				
				moveBall();
				checkWall();
				checkForCollision();
				
				//Check if all bricks removed
				if(isVictory()){
					remove(BALL);
					text = "VICTORY";
					addText();
					break;
				}
				
				//Present the movement process of the ball
				pause(DELAY);
			}
		}
	}
		
	
	public void drawBricks(){
		for(int i = 0; i < NBRICK_ROWS; i++){
			for(int j = 0; j < NBRICKS_PER_ROW; j++){
				double x_BRICK = j*(BRICK_WIDTH + BRICK_SEP);
				double y_BRICK = BRICK_Y_OFFSET + i * (BRICK_HEIGHT + BRICK_SEP);   
				GRect BRICK = new GRect(x_BRICK, y_BRICK, BRICK_WIDTH, BRICK_HEIGHT);
				add(BRICK);
				BRICK.setFilled(true);
				if(i < 2){
					BRICK.setColor(Color.RED);
					BRICK.setFillColor(Color.RED);
				}
				else if(i < 4){
					BRICK.setColor(Color.ORANGE);
					BRICK.setFillColor(Color.ORANGE);
				}
				else if(i < 6){
					BRICK.setColor(Color.YELLOW);
					BRICK.setFillColor(Color.YELLOW);
				}
				else if(i < 8){
					BRICK.setColor(Color.GREEN);
					BRICK.setFillColor(Color.GREEN);
				}
				else if(i < 10){
					BRICK.setColor(Color.CYAN);
					BRICK.setFillColor(Color.CYAN);
				}
			}
		}
	}
	
	
	public void drawPaddle(){
		PADDLE = new GRect(x_PADDLE, y_PADDLE, PADDLE_WIDTH, PADDLE_HEIGHT);
		PADDLE.setFilled(true);
		PADDLE.setFillColor(Color.BLACK);
		add(PADDLE);
	}
	
	
	public void mouseMoved(MouseEvent e) {
		if((e.getX() >= 0) && (e.getX() + PADDLE_WIDTH <= WIDTH)) {
			x_PADDLE_current = e.getX();
		    remove(PADDLE); 
		    PADDLE.setLocation(x_PADDLE_current, y_PADDLE); 
		    add(PADDLE); 	
		}
	}


	
	public void drawBall(){
		BALL = new GOval(x_BALL, y_BALL, 2 * BALL_RADIUS,2 * BALL_RADIUS);
		BALL.setFilled(true);
		BALL.setFillColor(Color.BLACK);
		add(BALL);
	}
	
	
	public void moveBall(){
		BALL.move(vx,vy);
	}
	
	
	public void checkWall(){
		if (BALL.getY() < 0) { 
			 vy = -vy; 
			 } 
		if ((BALL.getX() + 2 * BALL_RADIUS > WIDTH) | (BALL.getX() < 0)) { 
			 vx = -vx; 
			 } 
	}
	
	
	public GObject getCollidingObject(double x, double y){
			a = getElementAt(x, y);
			return a;
		}
	
	
	public void checkForCollision(){
		getCollidingObject(BALL.getX(), BALL.getY());
		if(a == null){
			getCollidingObject(BALL.getX() + 2 * BALL_RADIUS, BALL.getY());
			if(a == null){
				getCollidingObject(BALL.getX(), BALL.getY() + 2 * BALL_RADIUS);
				if(a == null){
					getCollidingObject(BALL.getX() + 2 * BALL_RADIUS, BALL.getY() + 2 * BALL_RADIUS);
					if(a != null){
						bounceClip.play();
						vy = -vy;
						if(a != PADDLE){
							integrator();
							counter--;
						}
					}
				}
				else{
					bounceClip.play();
					vy = -vy;
					if(a != PADDLE){
						integrator();
						counter--;
					}
				}
			}
			else{
				bounceClip.play();
				vy = -vy;
				if(a != PADDLE){
					integrator();
					counter--;
				}
			}	
		}
		else{
			bounceClip.play();
			vy = -vy;
			if(a != PADDLE){
				integrator();
				counter--;
			}
		}
	}

		
	public void addText(){
		TEXT = new GLabel(text);
		TEXT.setFont("Arial-50");
		x_TEXT = (WIDTH - TEXT.getWidth()) / 2.0;
		y_TEXT = (HEIGHT - TEXT.getAscent()) / 2.0;
		TEXT.setLocation(x_TEXT, y_TEXT);
		add(TEXT);
		pause(2000);
		remove(TEXT);
	}

	
	public boolean isVictory(){
		return counter == 0;
	}
	
	public void addScoreInstruction(){
		score =  "Cyan: +1;   Green: +2;   Yellow: +3;   Orange: +4;   Red: +5";
		GLabel SCORE = new GLabel(score);
		SCORE.setFont("Times-Bold-25");
		double x_SCORE = (WIDTH - SCORE.getWidth()) / 2.0;
		double y_SCORE = (HEIGHT - SCORE.getAscent()) / 2.0;
		SCORE.setLocation(x_SCORE, y_SCORE);
		add(SCORE);
		pause(3000);
		remove(SCORE);
	}
	
	public void integrator(){
		remove(a);
		if(Color.CYAN.equals(a.getColor())){
			Integrator += 1;
		}
		else if(Color.GREEN.equals(a.getColor())){
			Integrator += 2;
		}
		else if(Color.YELLOW.equals(a.getColor())){
			Integrator += 3;
		}
		else if(Color.ORANGE.equals(a.getColor())){
			Integrator += 4;
		}
		else{
			Integrator += 5;
		}
	}

	
	//Instance variables 
	private double x_PADDLE_current;
	private GRect PADDLE;
	private GOval BALL;
	private RandomGenerator rgen = RandomGenerator.getInstance();
	private double vx;
	private double vy;
	private GObject a;
	private int counter;
	private GLabel TEXT;
	private double x_TEXT;
	private double y_TEXT;
	private String text;
	private String score;
	private int Integrator;
}
		
