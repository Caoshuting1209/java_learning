
import acm.program.*;
	public class RemoveAllOccurrences extends ConsoleProgram {
		public void run() {
			String str = readLine("Enter a string: ");
			String a = readLine("Enter a character: ");
			char ch = a.charAt(0);
			removeAllOccurrences(str, ch);
			println(result);
		}
		
		
		public String removeAllOccurrences(String str, char ch){
			int n = str.length();
			for(int i = 0; i < n; ++i){
				char c =  str.charAt(i);
				if(c != ch){
					result += c;
				}
			}
			return result;	
		}
		
		private String result = "";
	
}