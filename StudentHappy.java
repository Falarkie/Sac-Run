package gameObjects;
import java.util.Random;

public class StudentHappy extends Student {
	private Random rand = new Random(System.currentTimeMillis());
	
	public StudentHappy(float x, float y, float direction){
		super(x,y,direction);
		super.setStatus(6);
	}
	
	public void studentMove(int width, int height, int frameTime) {
		if(getTimeRem() <= 0) {
			int roll = rand.nextInt(10); //roll to see if the student does a jump ~10% chance
			int mult = 1;
			if(roll == 1 ) {
				mult = 10; //multiply speed by 10 for this movement step
			}
			setLocalX((float) (super.getLocalX() + Math.cos(Math.toRadians(super.getHead()+90))  * (mult*super.getSpeed()*(frameTime/1000.0))));
			if(super.getLocalX() >= width) {
				//setX((float) width);
				this.Turn(0, 180);
			}
			if(super.getLocalX() <= 0) {
				//setX(0);
				this.Turn(0, 180);
			}
			setLocalY((float) (super.getLocalY() + Math.sin(Math.toRadians(super.getHead()+90))* (mult*super.getSpeed()*(frameTime/1000.0))));
			if(super.getLocalY() >= height) {
				//setY((float) height);
				this.Turn(0, 180);
			}
			if(super.getLocalY() <= 0) {
				//setY(0);
				this.Turn(0, 180);
			}
			else {
				super.studentMove(width,height, frameTime);
			}
		}
	}

}
