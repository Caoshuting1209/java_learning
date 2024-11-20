import acm.program.ConsoleProgram;

public class FibonacciSequence extends ConsoleProgram {
	
	private static final int MAX_TERM_VALUE = 10000;
	public void run() {
		int i = 0;
		int j = 1;
		println(0);
		int result = 0;
		while(result < MAX_TERM_VALUE){
			println(j);
			result = i + j;
			i = j;
			j = result;
		}
	
	
}}