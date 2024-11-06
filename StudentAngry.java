package gameObjects;

public class StudentAngry extends Student {
	
	public StudentAngry(float x, float y, float direction){
		super(x,y,direction);
		
		super.setTalk(super.getTalk() *2);
		super.setStatus(1);
	}

}
