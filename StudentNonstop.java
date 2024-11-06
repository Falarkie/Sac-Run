package gameObjects;

public class StudentNonstop extends Student  {
	
	public StudentNonstop(float x, float y, float direction){
		super(x,y,direction);
		super.setTalkStatus(false);
		super.setStatus(7);
	}

}
