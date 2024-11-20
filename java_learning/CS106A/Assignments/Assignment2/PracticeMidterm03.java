import acm.program.ConsoleProgram;

public class PracticeMidterm03 extends ConsoleProgram {
	private static final int SENTINEL = 0;
	public void run() {
		println("This program finds the two largest integers in a list. " +
				"Enter values, one per line, using a 0 to signal the end of the list.");
		
		int num1 = readInt("?");
		determine(num1);
		int num2 = readInt("?");
		determine(num2);
		compare(0, num1, num2);
		
		while(true){
			int current = readInt("?");
			if(current == SENTINEL) {
				break;
			}
			compare(current, lar, sec);
		}
		
		println("The largest value is " + lar);
		println("The second largest is " + sec);
	}
		
		
		public void compare(int a, int b, int c){
			if (a >= b){
				if(a >= c){
					lar = a;
					if(b >= c){
						sec = b;
					}
					else{
						sec = c;
					}
				}
				else{
					lar = c;
					sec = a;
				}
			}
			
			else{
				if(b >= c){
					lar = b;
					if(a >= c){
						sec = a;
					}
					else{
						sec = c;
					}
				}
				else{
					lar = c;
					sec = b;
				}
			}
		}
		
		private void determine(int a){
			if(a < 0){
				println("You must enter a positive integer.");
			}
			if(a == SENTINEL){
				println("You should enter at least 2 numbers before the sentinel.");
			}
		}
		
	
		private int lar;
		private int sec;
		
		/*
		 * public void run() {
				println("This program finds the two largest integers in a");
				println("list. Enter values, one per line, using a " + SENTINEL + " to");
				println("signal the end of the list.");
				int largest = -1;
				int secondLargest = -1;
				while (true) {
					int input = readInt(" ? ");
					if (input == SENTINEL) break;
					if (input > largest) {
						secondLargest = largest;
						largest = input;
					} else if (input > secondLargest) {
						secondLargest = input;
					}
					}
				println("The largest value is " + largest);
				println("The second largest is " + secondLargest);

		}	
		 */
	}
	


