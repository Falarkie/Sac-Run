package gameObjects;
import java.util.ArrayList;
import java.util.Random;

import com.codename1.charts.util.ColorUtil;
import com.codename1.ui.Font;
import com.codename1.ui.Graphics;
import com.codename1.ui.Transform;

public class LectureHall extends Facility{
	private Lecture lect;
	private String name;
	private static String[] halls = new String[] {"Alpine", "Amador", "AIRC", "Benica", "Brighton", "Calaveras", "Capistrano", "Douglas", "Del Norte", "Eureka", "Mendocino", "Riverside"};
	private static ArrayList <Integer> usedNames = new ArrayList<Integer>();
	private Random rand = new Random(System.currentTimeMillis());
	
	public LectureHall(float x, float y, int time, String name) {
		super(x, y);
		this.lect = new Lecture(time, name);
		setStatusID(0);
		super.setSize(50);
		boolean nameChosen = false;
		int tryname;
		super.setColor(ColorUtil.BLUE);
		while(!nameChosen){
			boolean nameDup = false;
			tryname = rand.nextInt(10);
			for(int i = 0; i < usedNames.size(); i++) {
				if(tryname == usedNames.get(i)) {
					nameDup = true;
				}
			}
			if(!nameDup) {
				this.name = halls[tryname];
				nameChosen = true;
				usedNames.add(tryname);
			}
		}
		
		
	}
	
	//These methods retreive data about the lecture within the lecture hall
	public Lecture getLect() {
		return lect;
	}
	public float getTimeRem() {
		return lect.getTimeRem();
	}
	public void decTimeRem(int frameTime) {
		lect.decTimeRem(frameTime);
	}
	
	public String getClassType() {
		return lect.getName();
	}
	
	public boolean isOpen() {
		return lect.isOpen();
	}
	
	public void lectureEntered() {
		lect.lectureEntered();
	}
	
	public void genNewLect(String name, int time) {
		if(!lect.isOpen()) {
			lect.makeLect(name, time);
		}
	}
	
	public String getName() {
		return this.name;
	}
	
	@Override
	public void selfDraw(Graphics g) {
		
		g.setColor(this.getColor());
		g.fillRect((int)(-getSize()/2), (int)(-getSize()/2), (int)getSize(),(int)getSize());
		
		g.scale(1, -1);
		g.setColor(ColorUtil.rgb(255, 255, 255));
		g.drawString(name,(int)(-getSize()/2), -3*(int)getSize());
		g.scale(1, -1);

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
