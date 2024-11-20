
import stanford.karel.*;

public class PracticeMidterm01 extends SuperKarel {

	// You fill in this part
	public void run(){
		turnLeft();
		move();
		turnRight();
		for(int i = 0; i < 4; ++i){
			setBorder();
		}
		pickBeeper();
		if(noBeepersPresent()){
			putBeeper();
		}
	}
	
	
	private void setBorder(){
		while(frontIsClear()){
			move();
			putBeeper();
		}
		pickBeeper();
		turnAround();
		move();
		turnRight();
	}
	
}
