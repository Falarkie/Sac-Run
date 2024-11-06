package gameObjects;

import java.util.ArrayList;

public class GameObjectCollection {
	private ArrayList <GameObject> gameObjs = new ArrayList<GameObject>();
	private int pointer = -1;
	
	public GameObjectCollection(){
	}
	
	public void add(GameObject item) {
		gameObjs.add(item);
	}
	
	public int size() {
		return gameObjs.size();
	}
	
	public boolean hasNext() {
		if(gameObjs.size() <= 0) {
			return false;
		}
		if(pointer == gameObjs.size() - 1) {
			return false;
		}
		return true;
	}
	
	public GameObject getNext() {
		pointer++;
		return(gameObjs.get(pointer));
		
	}
	
	public void reset() {
		pointer = -1;
	}
	
	public void clear() {
		gameObjs.clear();
		pointer = -1;
	}
	
	public GameObject get(int index) {
		return gameObjs.get(index);
	}
	
	public void set(int index, GameObject item) {
		gameObjs.set(index, item);
	}

}
