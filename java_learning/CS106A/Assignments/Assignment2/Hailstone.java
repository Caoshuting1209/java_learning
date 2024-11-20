/*
 * File: Hailstone.java
 * Name: 
 * Section Leader: 
 * --------------------
 * This file is the starter file for the Hailstone problem.
 */

import acm.program.*;

public class Hailstone extends ConsoleProgram {
	public void run() {
		/* You fill this in */
		int originValue = readInt("Please enter a number:");
		int count = 0;
		while(originValue != 1){
			int remainder = originValue % 2;
			if(remainder == 0){
				int m = originValue;
				originValue /= 2;
				println(m + " is even, so I take half: " + originValue );
			}
			else{
				int n = originValue;
				originValue = 3 * originValue + 1;
				println(n + " is odd, so I make 3n+1: " + originValue );
			}
			count += 1 ;
		}
		println("This process took " + count + " to reach 1" );
	}}

