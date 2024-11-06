package gameObjects;

public class StudentRunning extends Student  {
	
	public StudentRunning(float x, float y, float direction){
		super(x,y,direction);
		super.setSweat(getSweat() * 2);
		super.setSpeed(super.getSpeed()*2);
		super.setStatus(9);
	}

}
