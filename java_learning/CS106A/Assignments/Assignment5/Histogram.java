/*
 * File: Yahtzee.java
 * ------------------
 * This program will eventually play the Yahtzee game.
 */

import acm.io.*;
import acm.program.*;
import acm.util.*;
import java.io.*;
import java.util.*;

public class Histogram extends ConsoleProgram {
	
	private BufferedReader readFile(String str){
		BufferedReader rd = null;
		while(rd == null){
			try{
				String fileName = readLine(str);
				rd = new BufferedReader(new FileReader(fileName));
			}catch(IOException ex){
				println("The file doesn't exist.");
			}
		}
		return rd;
	}
	
	
	public void run(){
		BufferedReader rd = readFile("Give me a filename: ");
		ArrayList<Integer> scores = new ArrayList<Integer>();
		try{
			while(true){
				String line = rd.readLine();
				if(line == null) break;
				scores.add(Integer.valueOf(line));
			}
			Integer[] scoreArray = scores.toArray(new Integer[0]);
			His(scoreArray);
		}catch(IOException ex){
			throw new ErrorException(ex);
		}	
	}
		
	
	
	private void His(Integer[] scoreArray){
		for(int i = 0; i < 10; i++){
			StringBuilder sb = new StringBuilder(i + "0-" + i + "9: ");
			for(int j = 0; j < scoreArray.length; j++){
				if((scoreArray[j] < (i + 1) * 10) && (scoreArray[j] >= i * 10)){
					sb.append("*");
				}
			}
			String str = sb.toString();
			println(str);
		}
		StringBuilder sb2 = new StringBuilder("  100: ");
		for(int k = 0; k < scoreArray.length; k++){
			if(scoreArray[k] == 100){
				sb2.append("*");
			}
		}
		String str_2 = sb2.toString();
		println(str_2);
	}
	
}
