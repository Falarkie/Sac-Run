package gameObjects;
import java.util.Random;

public class StudentConfused extends Student {
	private Random rand = new Random(System.currentTimeMillis());
	
	public StudentConfused(float x, float y, float direction){
		super(x,y,direction);
		super.setStatus(4);
	}
	
	public void studentMove(int width, int height, int frameTime) {
		if(willMove()) { //eliminates non-stop and sleeping students
			if(super.getTimeRem() == 0) {
				int turnAmt = rand.nextInt(180);
				//left or right
				int LorR = rand.nextInt(1);
				if(LorR == 0) {
					turnAmt *= -1;
					super.Turn(0, turnAmt);
				}
				else {
					super.Turn(1, turnAmt);
				}
				super.studentMove(width,height, frameTime);
			}
		}
	}

}
