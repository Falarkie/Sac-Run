package gameObjects;

public class StudentSleeping extends Student  {
	
	public StudentSleeping(float x, float y, float direction){
		super(x,y,direction);
		super.setTalkStatus(false);
		super.setWalkStatus(false);
		super.setStatus(8);
	}

}
