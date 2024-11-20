
import acm.program.*;
	public class practiceMidterm05 extends ConsoleProgram {
		public void run() {
			String s = readLine("Enter a word: ");
			removeDoubledLetters(s);
			println(result);
		}
		
	public String removeDoubledLetters(String str){
		int n = str.length();
		for(int i = 0; i < n - 1; ++i){
			char a = str.charAt(i);
			char b = str.charAt(i + 1);
			if (a != b){
				result += a;
			}
		}
		result += str.charAt(n-1);
		return result;
	}
		
	private String result = "";
}