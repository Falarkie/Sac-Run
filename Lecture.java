package gameObjects;

public class Lecture {
	private float timeRem = 0;
	private String lectName;
	private boolean lectActive;
	
	Lecture(int time, String name){
		if(name.isEmpty()) {
			this.lectActive = false;
			this.timeRem = 0;
		}
		else {
			this.lectActive = true;
			this.timeRem = time;
		}
		this.lectName = name;
	}
	
	//Simple methods to get data about the lecture and handle the time and if it has been entered
	public void lectureEntered() {
		lectActive = false;
		timeRem = 0;
	}
	
	public float getTimeRem() {
		return timeRem;
	}
	public void decTimeRem(int frameTime) {
		if(lectActive)
			timeRem -= (frameTime/1000.0);
	}
	
	public String getName() {
		return lectName;
	}
	
	public boolean isOpen() {
		return lectActive;
	}
	public void makeLect(String name, int time) {
		lectActive = true;
		this.timeRem = time;
	}
}
