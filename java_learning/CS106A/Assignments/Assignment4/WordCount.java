
import acm.program.*;
import acm.util.ErrorException;

import java.io.*;
import java.util.*;

	public class WordCount extends ConsoleProgram {
		public void run() {
			BufferedReader rd = new BufferedReader(new FileReader(ShorterLexicon.txt));
			ArrayList<String> arr = new ArrayList<String>();
			int i = 0;
			try{
				while(true){
					String a = rd.readLine();
					if(a == null) break;
					arr.add(a);
					i++;
				}
				rd.close();
			}catch(IOException ex){
				throw new ErrorException(ex);
			}	
		}
		
	
	
}