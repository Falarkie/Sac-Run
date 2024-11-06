package gameObjects;
import java.lang.Math;
import java.util.Random;

import com.codename1.charts.util.ColorUtil;
import com.codename1.ui.Graphics;

public abstract class Student extends GameObject {
	//Default values
	String [] statusTypes = new String[] {"Player","Angry","Biking","Car",
									"Confused","Friendly","Happy","Nonstop",
									"Sleeping","Running"};
	
	private int statusID;
	//Default values
	private float defSpeed = 150;
	private float defTalkLvl = 2;
	private float defSweatRate = 3;
	private int defHydrate = 100;
	//Student attributes
	private float speed;
	private float talkLvl;
	private float head = 0;
	private float timeRem = 0;
	private float hydrate = 100;
	private float waterIn;
	private float sweatRate;

	private boolean talk = true;
	private boolean move = true;
	
	//Point storage
	protected int[] xPts, yPts;
	
	//Set randomness with a seed
	private Random rand = new Random(System.currentTimeMillis());
	
	public Student(float x, float y, float direction){
		super(x, y);
		this.head = direction;
		if(head >= 360) {
			head %= 360;
		}
		if(head < 0) {
			head += 360;
		}
		
		//this.status = statusTypes[StatusIndex];
		//this.statusID = StatusIndex;
		this.speed = defSpeed;
		this.talkLvl = defTalkLvl;
		this.sweatRate = defSweatRate;
		super.setSize(rand.nextInt(10)+40);
		super.setColor(ColorUtil.rgb(255, 0, 0));
		
		xPts = new int[] {0, (int)getSize()/2, (int)-getSize()/2};
		yPts = new int[] {(int)getSize()/2, (int)-getSize()/2, (int)-getSize()/2};
		
		ROTform.setRotation((float) Math.toRadians(head), 0, (float)(getSize()*1.25));
	}
	//Checks
	public boolean willTalk() {
		return talk;
	}
	public void setTalkStatus(boolean x) {
		this.talk = x;
	}
	
	public boolean willMove() {
		return move;
	}
	public void setWalkStatus(boolean x) {
		this.move = x;
	}
	

	
	//Other Methods
	
	//Simulates a collision with a waterDispenser
	public void drinkWater() {
		this.waterIn += (defHydrate - hydrate);
		this.hydrate = defHydrate;
	}
	
	//Simulates a collision with a restRoom
	public void restRoom() {
		this.waterIn = 0;
	}
	
	
	
	//A static method to set any 2 students to talking
	public static float Talking(Student s1, Student s2) {
		float maxTime = 0;
		if(s1.willTalk() && s2.willTalk()) {//Ensures students will talk in the first place (sleeping and nonstop students won't talk)
			maxTime = Math.max(s1.getTalk(), s2.getTalk());
		}
		return maxTime;
	}
	
	public void Turn(int direction, int specificTurn) {
		//direction of 0 = left, direction of <= 1 = right
		if(getTimeRem() <= 0) {
			float turnAmt;
			
			//Specific turn amt is for the sake of handling confusedStudents, with non-confused students it should ALWAYS be passed 0 as an argument
			if(specificTurn == 0) {
				if(direction == 0) {
					turnAmt = +5;
				}
				else{
					turnAmt = -5;
				}
			}
			else {
				turnAmt = specificTurn;
			}
			this.head += turnAmt;
			
			//This ensures that the head does not go to unnecessarily high/negative numbers
			if(head >= 360) {
				head %= 360; //360 deg == 0 deg so the remainder of this will still be the correct angle
			}
			if(head < 0) {
				head += 360; //If the degrees becomes negative you can make it do an entire 360 in the positive direction and the degrees are equivalent i.e.: -270 == 90
			}
			
			ROTform.setRotation((float) Math.toRadians(head), 0, (float)(getSize()*1.25));
		}
	}
	
	//Generic method for student movement
	public void studentMove(int width, int height, int frameTime) {
		boolean wall = false;
		//System.out.println(frameTime/1000.0);
		if(willMove()) {//eliminates non-stop and sleeping students
			if(timeRem <= 0) {//Ensure a talking student does not move
				//Standard Student movement
				if((this.statusID != -1)) {
					hydrate -= sweatRate*(frameTime/1000.0);
					setLocalX((float) (getLocalX() + (Math.cos(Math.toRadians(head+90))*speed*(frameTime/1000.0))));
					//These 2 if statements ensure the student does not exit the level bounds
					//System.out.println(super.getX() + ";" + width);
					//System.out.println(super.getY() + ";" + height);
					//System.out.println("__________________________________");
					if((int)super.getLocalX() >= width) {
						//setX((float) width);
						wall = true;
						this.Turn(0, 180);
					}
					if((int)super.getLocalX() <= 0) {
						//setX(0);
						wall = true;
						this.Turn(0, 180);
					}
					setLocalY((float) (getLocalY() + (Math.sin(Math.toRadians(head+90))*speed*(frameTime/1000.0))));
					//These 2 if statements ensure the student does not exit the level bounds
					if((int)super.getLocalY() >= height ) {
						//setY((float) height);
						wall = true;
						this.Turn(0, 180);
					}
					if((int)super.getLocalY() <= 0)  {
						//setY(0);
						wall = true;
						this.Turn(0, 180);
					}
				}
				//allows the student strategy to simulate confused movement
				else if(this.statusID == -1) {
					int turnAmt = rand.nextInt(180);
					//left or right
					int LorR = rand.nextInt(1);
					if(LorR == 0) {
						turnAmt *= -1;
						Turn(0, turnAmt);
					}
					else {
						Turn(1, turnAmt);
					}
					hydrate -= sweatRate;
					setLocalX((float) (getLocalX() + (Math.cos(Math.toRadians(head))*speed*(frameTime/1000.0))));
					//These 2 if statements ensure the student does not exit the level bounds
					if((int)super.getLocalX() >= width) {
						//setX((float) width);
						wall = true;
						this.Turn(0, 180);
					}
					if((int)super.getLocalX() <= 0) {
						//setX(0);
						wall = true;
						this.Turn(0, 180);
					}
					
					setLocalY((float) (getLocalY() + (Math.sin(Math.toRadians(head))*speed*(frameTime/1000.0))));
					//These 2 if statements ensure the student does not exit the level bounds
					if((int)super.getLocalY() >= height) {
						//setY((float) height);
						wall = true;
						this.Turn(0, 180);
					}
					if((int)super.getLocalY() <= 0) {
						//setY(0);
						wall = true;
						this.Turn(0, 180);
					}
					
				}
			}
		}
	}
	
	public void incTime(int frameTime) {
		if(timeRem >= -2) {
			timeRem -= (frameTime/1000.0);
		}
		if (timeRem <= 0) {
			if(super.getColor() != ColorUtil.rgb(255, 0, 0)) {
				super.setColor(ColorUtil.rgb(255, 0, 0));
			}
		}
		
	}
	//Get/set methods
	public int getTypeID() {
		return statusID;
	}
	public void setStatus(int val) {
		this.statusID = val;
	}
	
	public String getType() {
		return statusTypes[statusID];
	}
	
	public float getHead() {
		return head;
	}
	public void setHead(float val) {
		this.head = val;
		ROTform.setRotation((float) Math.toRadians(head), 0, (float)(getSize()*1.25));
	}
	
	public float getSpeed() {
		return speed;
	}
	public void setSpeed(float val) {
		this.speed = val;
	}
	
	public float getHydration() {
		return hydrate;
	}
	
	public float getTalk() {
		return talkLvl;
	}
	public void setTalk(float val) {
		this.talkLvl = val;
	}
	
	public float getTimeRem() {
		return timeRem;
	}
	public void setTimeRem(float value){
		this.timeRem = value;
	}
	
	public float getWaterIn() {
		return waterIn;
	}
	
	public float getSweat() {
		return sweatRate;
	}
	public void setSweat(float val) {
		this.sweatRate = val;
	}
	
	@Override
	public void selfDraw(Graphics g) {
		
		g.setColor(this.getColor());
		g.drawPolygon(xPts, yPts, 3 );
	}
	
	@Override
	public boolean selfHit(float x, float y) {
		float[] pts = getInverse(x, y);
		x = pts[0];
		y = pts[1];
		if (x > -getSize()/2 && x < getSize()/2 && y > -getSize()/2 && y < getSize()/2) {
			return true;
		}
		return false;
	}
}
