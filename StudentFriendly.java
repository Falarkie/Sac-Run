package gameObjects;

public class StudentFriendly extends Student {
	
	public StudentFriendly(float x, float y, float direction){
		super(x,y,direction);
		super.setTalk(super.getTalk() /2);
		super.setStatus(5);
	}

}
