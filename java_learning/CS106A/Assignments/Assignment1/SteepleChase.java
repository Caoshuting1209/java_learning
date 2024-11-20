/*
 * File: StoneMasonKarel.java
 * --------------------------
 * The StoneMasonKarel subclass as it appears here does nothing.
 * When you finish writing it, it should solve the "repair the quad"
 * problem from Assignment 1.  In addition to editing the program,
 * you should be sure to edit this comment so that it no longer
 * indicates that the program does nothing.
 */

import stanford.karel.*;

public class SteepleChase extends SuperKarel {
		//	 You fill in this part
	
	public void run(){
		for(int i=0; i<9; i++){	
			if(frontIsClear()){
				move();
			}
			else{
				jumpOverSteeple();
			}
		}}
	
	private void jumpOverSteeple(){
		turnLeft();
		while(rightIsBlocked()){
			move();
		}
		turnRight();
		move();
		turnRight();
		while(frontIsClear()){
			move();
		}
		turnLeft();
		
	}
	}

		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		