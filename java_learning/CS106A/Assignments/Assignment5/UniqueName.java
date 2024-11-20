import acm.program.*;
import java.util.*;

public class UniqueName extends ConsoleProgram {
	
	
	public void run(){
		ArrayList<String> names = new ArrayList<String>();
		while(true){
			String str = readLine("Enter name: ");
			if(str.equals(" ")) break;
			if(names.contains(str) == false){
				names.add(str);
			}	
		}
		
		println("Unique name list contains: ");
		for(int i = 0; i < names.size(); i++){
			println(names.get(i));
		}
	}
	
		
	
	
	
	
}
