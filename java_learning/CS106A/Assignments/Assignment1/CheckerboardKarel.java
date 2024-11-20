/*
 * File: CheckerboardKarel.java
 * ----------------------------
 * When you finish writing it, the CheckerboardKarel class should draw
 * a checkerboard using beepers, as described in Assignment 1.  You
 * should make sure that your program works for all of the sample
 * worlds supplied in the starter folder.
 */

import stanford.karel.*;

public class CheckerboardKarel extends SuperKarel {

	// You fill in this part
	public void run(){
		putBeeper();
		while(frontIsClear()){
			if(frontIsClear()){
				move();
				if(frontIsClear()){
					move();
					putBeeper();}}}
				
		if(beepersPresent()){
			if(leftIsClear()){
				turnLeft();
				move();
				turnLeft();
				while(frontIsClear()){
					if(frontIsClear()){
						move();
						putBeeper();
						if(frontIsClear()){
							move();}}}}}
		else{
			if(leftIsClear()){
				turnLeft();
				move();
				turnLeft();
				putBeeper();
				while(frontIsClear()){
					if(frontIsClear()){
						move();
						if(frontIsClear()){
							move();
							putBeeper();}}}}}
					
		
	//cycle starting
		
		while(rightIsClear()){		
			if(beepersPresent()){
				if(leftIsClear()){
					turnLeft();
					turnLeft();
					turnLeft();
					move();
					turnLeft();
					turnLeft();
					turnLeft();
					while(frontIsClear()){
						if(frontIsClear()){
							move();
							putBeeper();
							if(frontIsClear()){
								move();}}}}
				else{}}
			
			else{
				if(leftIsClear()){
					turnLeft();
					turnLeft();
					turnLeft();
					move();
					turnLeft();
					turnLeft();
					turnLeft();
					putBeeper();
					while(frontIsClear()){
						if(frontIsClear()){
							move();
							if(frontIsClear()){
								move();
								putBeeper();}}}}
				else{}
			}
			
			
			if(beepersPresent()){
				if(leftIsClear()){
					turnLeft();
					move();
					turnLeft();
					while(frontIsClear()){
						if(frontIsClear()){
							move();
							putBeeper();
							if(frontIsClear()){
								move();}}}}
				else{}}
			
			else{
				if(leftIsClear()){
					turnLeft();
					move();
					turnLeft();
					putBeeper();
					while(frontIsClear()){
						if(frontIsClear()){
							move();
							if(frontIsClear()){
								move();
								putBeeper();}}}}
				else{}
			}		
		}}}
			
	/*
		public void run() {
			boolean flag = true;
			while (true) {
				if (flag) {
					putBeeper();
					flag = false;
				}
				else flag = true;
				
				if (frontIsClear()) move();
				else {
					if ((facingWest() && rightIsBlocked()) || (facingEast() && leftIsBlocked())) break;
					
					if (facingEast()) {
						turnLeft();
						move();
						turnLeft();
					}
					else {
						turnRight();
						move();
						turnRight();
					}
				}
			}
		}
	}
	 */
			
					
					
				
				
					
								
				
					
				
								
		