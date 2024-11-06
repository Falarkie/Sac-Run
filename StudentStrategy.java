package gameObjects;
 
public class StudentStrategy extends Student {
	private Student currentStrat;
	private String stratName = "";

	public StudentStrategy(float x, float y, float direction) {
		super(x, y, direction);
		// TODO Auto-generated constructor stub
	}
	
	
	
	public void moveStrat(int width, int height, int frameTime){
		if(currentStrat instanceof StudentConfused) {
			StudentConfused handleConfStrat = (StudentConfused) currentStrat;
			handleConfStrat.studentMove(width, height, frameTime);
			currentStrat = handleConfStrat;
		}
		else {
			currentStrat.studentMove(width, height, frameTime);
		}
	}
	
	
	
	public void setStrategy(int type) {
		if(currentStrat != null) {
			super.setLocalX(currentStrat.getLocalX());
			super.setLocalY(currentStrat.getLocalY());
			super.setHead(currentStrat.getHead());
		}
		switch (type) {
		case 0: //Confused strategy
			stratName = "Confused Strategy";
			currentStrat = new StudentConfused(super.getLocalX(),super.getLocalY(),super.getHead());
			break;
		case 1: //Vert strategy
			stratName = "Vertical Strategy";
			if(90 <= super.getHead() && super.getHead() <= 270) {
				super.setHead(0);
			}
			else {
				super.setHead(180);
			}
			currentStrat = new StudentStrategy(super.getLocalX(), super.getLocalY(), super.getHead() );
			break;
		case 2: //Horizonatal Strategy
			stratName = "Horizontal Strategy";
			if(0 <= super.getHead() && super.getHead() <= 180) {
				super.setHead(90);
			}
			else {
				super.setHead(270);
			}
			
			currentStrat = new StudentStrategy(super.getLocalX(), super.getLocalY(), super.getHead());
			break;
		}
	}

}
