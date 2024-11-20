
import acm.program.*;
	public class AddCommas extends ConsoleProgram {
		public void run() {
			 while (true) {
				 String digits = readLine("Enter a numeric string: ");
				 if (digits.length() == 0) break;
				 println(addCommasToNumericString(digits));
			 }
		}
		
		
	private String addCommasToNumericString(String digits){
		int n = digits.length();
		int m = n % 3;
		String str_1 = digits.substring(0, m);
		String result = str_1;
		for(int i = 0; i < n / 3; ++i){
			String str = digits.substring(m + i * 3, m + (i + 1) * 3 );
			result += ("," + str);
		}
		return result;
	}
	
	
}