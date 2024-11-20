/*
 * File: Hangman.java
 * ------------------
 * This program will eventually play the Hangman game from
 * Assignment #4.
 */

import acm.graphics.*;
import acm.program.*;
import acm.util.*;

import java.awt.*;

public class Hangman extends ConsoleProgram {
	private static final int GAME_PLAY_ROUNDS = 8;
    public void run() {
		/* You fill this in */
    	canvas.reset();
    	setFont("Times-bold-15");
    	word = hangmanLexicon.getWord(readInt("Give an index: "));
    	length = word.length();
    	initial = "";
    	for(int i = 0; i < length; ++i){
    		initial += "-";
    	}
    	println("Welcome to Hangman!");
    	println("The word now looks like this£º " + initial);
    	canvas.displayWord(initial);
    	
    	arr = initial.toCharArray();
    	numberOfGuesses = GAME_PLAY_ROUNDS;
    	current = initial;
    	wrong_guess = 0;
    	
    	while(numberOfGuesses > 0){
    		if(isWin() == true){
    			break;
    		}
    		println("You have " + numberOfGuesses + " guesses left.");
    		userGuess();
    	}
    	
    	if(isWin() == false){
    		println("You are completely hung.");
    		println("The word was: " + word + ".");
    		println("You lose.");
		}
    }

   

    
    private void userGuess(){
    	guessedWord = (readLine("Your guess: ")).charAt(0);
    	if(Character.isLetter(guessedWord) == false){
    		println("You entered the wrong type. Please enter a letter.");
    	}
    	else{
    		guessedWord = Character.toUpperCase(guessedWord);
    		int count = 0;
    		for(int j = 0; j < length; ++j){
    			char a = word.charAt(j);
    			if(a == guessedWord){
    				arr[j] = a;
//    				StringBuilder sb = new StringBuilder(current);
//                	sb.setCharAt(j, a);
//                	current = sb.toString();
    				current = new String(arr);
    				count += 1;
    			}
    		}
    	
    		if(count == 0){
    			println("There are no " + guessedWord + "'s in the word.");
    			numberOfGuesses--;
    			wrong_guess++;	
    			canvas.noteIncorrectGuess(guessedWord, wrong_guess);
    		}
    		else{
    			println("This guess is correct.");
    		}
    	
    		if(isWin() == false){
    			println("The word now looks like this£º " + current);
    			canvas.displayWord(current);
    		}
    		else{
    			println("You guessed the word: " + word + ".");
    			println("You win.");
    		}	
    	}
    }
	
    private boolean isWin(){
    	return current.equals(word);
    }

    public void init() {
    	 canvas = new HangmanCanvas();
    	 add(canvas);
    	}
    
    private HangmanLexicon hangmanLexicon = new HangmanLexicon();
    private int numberOfGuesses;
    private String word;
    private int length;
    private String initial;
    private String current;
    private char[] arr;
    private char guessedWord;
    private int wrong_guess;
    private HangmanCanvas canvas;
}
