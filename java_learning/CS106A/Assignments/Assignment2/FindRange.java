import acm.program.ConsoleProgram;

public class FindRange extends ConsoleProgram {
	private static final int SENTINEL = 0;
	public void run() {
		println("This program finds the largest and smallest numbers.");
		int a = readInt("?");
		if(a == SENTINEL){
			println("No integer is entered.");}
		else{
			int b = readInt("?");
			if (b == SENTINEL){
				println("Smallest£º" + a);
				println("Largest:" + a);
				}
			else{
				if(a>b){
					int m = a;
					int n = b;
					a = n;
					b = m;
				}
				int S = a;
				int L = b;
				while(true){
					int c = readInt("?");
					if(c == SENTINEL) break;
					if(c<a){
						S = c;
					}
					else if(c>b){
						L = c;
					}
				}
				println("Smallest£º" + S);
				println("Largest:" + L);
		}}
	}}