package gameObjects;

public class StudentBiking extends Student  {
	
	public StudentBiking(float x, float y, float direction){
		super(x,y,direction);
		super.setSweat(super.getSweat()*3);
		super.setSpeed(super.getSpeed()*2);
		super.setStatus(2);
	}

}
