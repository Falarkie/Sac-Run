package gameObjects;

public abstract class Facility extends GameObject{
	//Stores the names of different facilities
	private String[] facilType = new String[] {"LectureHall", "WaterDispenser", "RestRoom"};
	//StatusID is simply the index of the name type in the above array
	private int statusID;
	
	Facility(float x, float y){
		super(x, y);
	}
	
	//Only contains simple get/set methods
	public int getTypeID() {
		return getStatusID();
	}
	public String getType() {
		return facilType[getStatusID()];
	}

	public int getStatusID() {
		return statusID;
	}

	public void setStatusID(int statusID) {
		this.statusID = statusID;
	}
	
}
