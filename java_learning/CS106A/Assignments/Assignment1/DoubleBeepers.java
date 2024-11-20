/*
 * File: DoubleBeepers.java
 */

import stanford.karel.*;

public class DoubleBeepers extends SuperKarel {

	// You fill in this part
	public void run(){
		move();
		doubleBeepers();
		moveBack();		
		}
	private void doubleBeepers(){
		while(beepersPresent()){
			pickBeeper();
			move();
			putTwoBeeperNext();
			turnBack();
			move();
			turnBack();
			}
		move();
		turnBack();
		while(beepersPresent()){
			pickBeeper();
			move();
			putBeeper();
			turnBack();
			move();
			turnBack();
			}
	}
	private void moveBack(){
		move();
		move();
		turnBack();
	}
	private void turnBack(){
		turnLeft();
		turnLeft();
	}
	private void putTwoBeeperNext(){
		putBeeper();
		putBeeper();
	}
	
}	
			
			
			
			
					
					
				
				
					
								
				
					
				
								
		