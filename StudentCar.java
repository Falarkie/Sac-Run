package gameObjects;

public class StudentCar extends Student {
	
	public StudentCar(float x, float y, float direction){
		super(x,y,direction);
		super.setSpeed(super.getSpeed()*5);
		super.setSweat(0);
		//Ensure the degrees of the car student are either 90 or 270
		if(0 <= super.getHead() && super.getHead() <= 180) {
			super.setHead(90);
		}
		else {
			super.setHead(270);
		}
		super.setStatus(3);
		
	}

}
