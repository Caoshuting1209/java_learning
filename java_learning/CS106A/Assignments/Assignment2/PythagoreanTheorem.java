/*
 * File: PythagoreanTheorem.java
 * Name: 
 * Section Leader: 
 * -----------------------------
 * This file is the starter file for the PythagoreanTheorem problem.
 */

import acm.program.*;

public class PythagoreanTheorem extends ConsoleProgram {
	public void run() {
		
		println("Enter values to compute Pythagorean theorem.");
		int a = readInt("a:");
		int b = readInt("b:");
		double m = Math.pow(a,2);
		double n = Math.pow(b,2);
		double c = Math.sqrt(m+n);
		println("c = " + c);
		/* You fill this in */
	}
}