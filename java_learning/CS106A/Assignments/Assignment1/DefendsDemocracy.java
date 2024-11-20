/*
 * File: CheckerboardKarel.java
 * ----------------------------
 * When you finish writing it, the CheckerboardKarel class should draw
 * a checkerboard using beepers, as described in Assignment 1.  You
 * should make sure that your program works for all of the sample
 * worlds supplied in the starter folder.
 */

import stanford.karel.*;

public class DefendsDemocracy extends SuperKarel {

	// You fill in this part
	
	public void run(){
		move();
		while(frontIsClear()){
			if(beepersPresent()){
				jumpTwoSteps();
			}
			else{
				checkTheEnd();	
				jumpTwoSteps();
			}		
		}
	}
		

	private void checkTheEnd(){
		turnLeft();
		move();
		turnAround();
		while(beepersPresent()){
			pickBeeper();
		}
		move();
		move();
		turnAround();
		while(beepersPresent()){
			pickBeeper();
		}
		move();
		turnRight();	
	}
					
	private void jumpTwoSteps(){
		for(int i=0; i<2; i++){
			if(frontIsClear()){
				move();
			}
		}
	}
	
}
			
			
					
					
				
				
					
								
				
					
				
								
		