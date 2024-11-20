/*
 * File: HangmanCanvas.java
 * ------------------------
 * This file keeps track of the Hangman display.
 */

import acm.graphics.*;

public class HangmanCanvas extends GCanvas {

/** Resets the display so that only the scaffold appears */
	public void reset() {
		/* You fill this in */
		GLine scaffoldLeft = new GLine(getWidth() / 2 - BEAM_LENGTH, SCAFFOLD_Y_OFFSET, 
										getWidth() / 2 - BEAM_LENGTH, SCAFFOLD_Y_OFFSET + SCAFFOLD_HEIGHT);
		GLine scaffoldMiddle = new GLine(getWidth() / 2 - BEAM_LENGTH, SCAFFOLD_Y_OFFSET,
										getWidth() / 2, SCAFFOLD_Y_OFFSET);
		GLine scaffoldRight = new GLine(getWidth() / 2, SCAFFOLD_Y_OFFSET, getWidth() / 2, 
										SCAFFOLD_Y_OFFSET + ROPE_LENGTH);
		add(scaffoldLeft);
		add(scaffoldMiddle);
		add(scaffoldRight);
	}

/**
 * Updates the word on the screen to correspond to the current
 * state of the game.  The argument string shows what letters have
 * been guessed so far; unguessed letters are indicated by hyphens.
 */
	public void displayWord(String word) {
		/* You fill this in */
		CURRENT_WORD.setLabel(word);
		CURRENT_WORD.setFont("Times-bold-20");
		CURRENT_WORD.setLocation(WORD_X_OFFSET ,WORD_Y_OFFSET);
		add(CURRENT_WORD);
	}

/**
 * Updates the display to correspond to an incorrect guess by the
 * user.  Calling this method causes the next body part to appear
 * on the scaffold and adds the letter to the list of incorrect
 * guesses that appears at the bottom of the window.
 */
	public void noteIncorrectGuess(char letter, int index) {
		/* You fill this in */
		note = "" + letter;
		GLabel NOTE = new GLabel(note);
		NOTE.setFont("Times-15");
		NOTE.setLocation(WORD_X_OFFSET + (12 * (index-1)), WORD_Y_OFFSET + NOTE.getAscent() + 10);
		add(NOTE);
		
		if(index == 1){
			GOval head = new GOval(getWidth() / 2 - HEAD_RADIUS, Y_HEAD, 
									2 * HEAD_RADIUS, 2 * HEAD_RADIUS);
			add(head);
		}
		else if(index == 2){
			GLine body = new GLine(getWidth() / 2, Y_HEAD + 2 * HEAD_RADIUS, 
					getWidth() / 2, Y_HEAD + 2 * HEAD_RADIUS + BODY_LENGTH);
			add(body);
		}
		else if(index == 3){
			GLine leftArmUpper = new GLine(getWidth() / 2, Y_ARM, getWidth() / 2 - UPPER_ARM_LENGTH, Y_ARM);
			GLine leftArmLeft = new GLine(getWidth() / 2 - UPPER_ARM_LENGTH, Y_ARM, 
											getWidth() / 2 - UPPER_ARM_LENGTH, Y_ARM + LOWER_ARM_LENGTH);
			add(leftArmUpper);
			add(leftArmLeft);
		}
		else if(index == 4){
			GLine rightArmUpper = new GLine(getWidth() / 2, Y_ARM, getWidth() / 2 + UPPER_ARM_LENGTH, Y_ARM);
			GLine rightArmLeft = new GLine(getWidth() / 2 + UPPER_ARM_LENGTH, Y_ARM, 
											getWidth() / 2 + UPPER_ARM_LENGTH, Y_ARM + LOWER_ARM_LENGTH);
			add(rightArmUpper);
			add(rightArmLeft);
		}
		else if(index == 5){
			GLine leftLegUpper = new GLine(getWidth() / 2, Y_LEG,
											(getWidth() - HIP_WIDTH) / 2, Y_LEG);
			GLine leftLegLeft = new GLine((getWidth() - HIP_WIDTH) / 2, Y_LEG,
											(getWidth() - HIP_WIDTH) / 2, Y_LEG + LEG_LENGTH);
			add(leftLegUpper);
			add(leftLegLeft);
		}
		else if(index == 6){
			GLine rightLegUpper = new GLine(getWidth() / 2, Y_LEG,
									(getWidth() + HIP_WIDTH) / 2, Y_LEG);
			GLine rightLegRight = new GLine((getWidth() + HIP_WIDTH) / 2, Y_LEG,
											(getWidth() + HIP_WIDTH) / 2, Y_LEG + LEG_LENGTH);
			add(rightLegUpper);
			add(rightLegRight);
		}
		else if(index == 7){
			GLine leftFoot = new GLine((getWidth() - HIP_WIDTH) / 2, Y_FOOT, 
										(getWidth() - HIP_WIDTH) / 2 - FOOT_LENGTH, Y_FOOT);
			add(leftFoot);
		}
		else if(index == 8){
			GLine rightFoot = new GLine((getWidth() + HIP_WIDTH) / 2, Y_FOOT,
										(getWidth() + HIP_WIDTH) / 2 + FOOT_LENGTH, Y_FOOT);
			add(rightFoot);
		}
	}

/* Constants for the simple version of the picture (in pixels) */
	private static final int SCAFFOLD_HEIGHT = 260;
	private static final int BEAM_LENGTH = 144;
	private static final int ROPE_LENGTH = 18;
	private static final int HEAD_RADIUS = 36;
	private static final int BODY_LENGTH = 100;
	private static final int ARM_OFFSET_FROM_HEAD = 28;
	private static final int UPPER_ARM_LENGTH = 72;
	private static final int LOWER_ARM_LENGTH = 44;
	private static final int HIP_WIDTH = 72;
	private static final int LEG_LENGTH = 108;
	private static final int FOOT_LENGTH = 40;
	
	private static final int SCAFFOLD_Y_OFFSET = 50;
	private static final int Y_HEAD = SCAFFOLD_Y_OFFSET + ROPE_LENGTH;
	private static final int Y_ARM = Y_HEAD + 2 * HEAD_RADIUS + ARM_OFFSET_FROM_HEAD;
	private static final int Y_LEG = Y_HEAD + 2 * HEAD_RADIUS + BODY_LENGTH;
	private static final int Y_FOOT = Y_LEG + LEG_LENGTH;
	
	
	
	//instance variables

	private double WORD_Y_OFFSET = 400;
	private double WORD_X_OFFSET = 20;
	private GLabel CURRENT_WORD = new GLabel("");
	private String note;
}
