/*
 * File: MidpointFindingKarel.java
 * -------------------------------
 * When you finish writing it, the MidpointFindingKarel class should
 * leave a beeper on the corner closest to the center of 1st Street
 * (or either of the two central corners if 1st Street has an even
 * number of corners).  Karel can put down additional beepers as it
 * looks for the midpoint, but must pick them up again before it
 * stops.  The world may be of any size, but you are allowed to
 * assume that it is at least as tall as it is wide.
 */

import stanford.karel.*;

public class MidpointFindingKarel extends SuperKarel {

	public void run(){
	//Place beepers at each square of the street except two ends
		move();
		while(frontIsClear()){
			putBeeper();
			move();
		}
		turnAround();
		while(frontIsClear()){
			move();
		}
		turnAround();
		
	//Pick all beepers at the end to next square forward
		while(noBeepersPresent()){
				while(noBeepersPresent()){
					move();}
				
	//Determine if all beepers are concentrated in the central point of the street
				move();
				if(noBeepersPresent()){
					putBeeper();}
				else{
					turnAround();
					move();
					turnAround();
					putAllBeepersToNextSquare();
					moveToTheOtherEnd();
					turnAround();}
		}
		
	//Pick all excess beepers
		pickBeeper();
		turnAround();
		move();
		while(beepersPresent()){
			pickBeeper();
			}
		putBeeper();
			
}
	
	private void putAllBeepersToNextSquare(){
		while(beepersPresent()){
			pickBeeper();
			move();
			putBeeper();
			turnAround();
			move();
			turnAround();
		}}
	
	
	private void moveToTheOtherEnd(){
		while(frontIsClear()){
			move();
		}}

	
}
