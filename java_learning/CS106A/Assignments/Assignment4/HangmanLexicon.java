/*
 * File: HangmanLexicon.java
 * -------------------------
 * This file contains a stub implementation of the HangmanLexicon
 * class that you will reimplement for Part III of the assignment.
 */

import acm.program.*;
import acm.util.*;
import java.io.*;
import java.util.*;


public class HangmanLexicon{

	public HangmanLexicon(){
		wordList = new ArrayList<String>();
		BufferedReader file = new BufferedReader(new FileReader(ShorterLexicon.txt));
		try{
			while(true){
				String a = file.readLine();
				if(a == null) break;
				wordList.add(a);
			}
			file.close();
		}catch(IOException ex){
			throw new ErrorException(ex);
		}	
	}
	
	
/** Returns the number of words in the lexicon. */
	public int getWordCount(ArrayList a) {
		return a.size();
	}
	

/** Returns the word at the specified index. */
	public String getWord(int index) {
		if(index < wordList.size()){
			return wordList.get(index);
		}else{
			return null;
		}
	}
		
		/*switch (index) {
			case 0: return "BUOY";
			case 1: return "COMPUTER";
			case 2: return "CONNOISSEUR";
			case 3: return "DEHYDRATE";
			case 4: return "FUZZY";
			case 5: return "HUBBUB";
			case 6: return "KEYHOLE";
			case 7: return "QUAGMIRE";
			case 8: return "SLITHER";
			case 9: return "ZIRCON";
			default: throw new ErrorException("getWord: Illegal index");
		}
		*/
	public ArrayList<String> wordList;
}
