package gameObjects;

import com.codename1.charts.util.ColorUtil;
import com.codename1.ui.Graphics;
import com.codename1.ui.Transform;
import com.codename1.ui.Transform.NotInvertibleException;


public class GameObject extends Shape {
	private int color;
	private int size;
	
	
	GameObject(float x2, float y2) {
		this.setLocalX(x2);
		this.setLocalY(y2);
		this.color = ColorUtil.rgb(255, 0, 0);
	}
	
	//Will be used later to implement actual collision, but will likely be inserted into a class handling bounding boxes
	public void handleCollide(GameObject item) {
		
	}
	
	//Only simple get and set methods
	/*
	public float getX() {
		return x;
	}
	public float getY() {
		return y;
	}
	public void setY(float y) {
		this.y = y;
	}
	public void setX(float x) {
		this.x = x;
	}
	*/
	public float getLocalX() {
		return XYform.getTranslateX();
	}
	public float getLocalY() {
		return XYform.getTranslateY();
	}
	public void setLocalY(float y) {
		XYform.setTranslation(getLocalX(), y);
	}
	public void setLocalX(float x) {
		XYform.setTranslation(x, getLocalY());
	}
	public int getColor() {
		return color;
	}
	public void setColor(int val) {
		this.color = val;
	}
	
	public float getSize() {
		return size;
	}
	public void setSize(int val) {
		this.size = val;
	}

	@Override
	public void drawBox(Graphics g) {
		// TODO Auto-generated method stub
		g.setColor(ColorUtil.rgb(255, 0, 0));
		g.drawRect((int)(-getSize()/2), (int)(-getSize()/2), (int)getSize(),(int)getSize());
	}

	public boolean collides(GameObject other) {
		int L1 = (int) (this.getLocalX() - this.getSize() / 2); 
		int R1 = (int) (this.getLocalX() + this.getSize() / 2);
		int T1 = (int) (this.getLocalY() - this.getSize() / 2);
		int B1 = (int) (this.getLocalY() + this.getSize() / 2);
		
		int L2 = (int) (other.getLocalX() - other.getSize() / 2);
		int R2 = (int) (other.getLocalX() + other.getSize() / 2);
		int T2 = (int) (other.getLocalY() - other.getSize() / 2);
		int B2 = (int) (other.getLocalY() + other.getSize() / 2);
		
		if ((R1 < L2) || (R2 < L1) || (T2 > B1) || (T1 > B2))
			return false;
		return true;

	}

	@Override
	public boolean contain(float x, float y) {
		boolean hit = false;
		return hit | selfHit(x,y);
	}
	
	public float[] getInverse(float x, float y) {
		float[] pts = {x, y};
		Transform inverseConcatLTs = Transform.makeIdentity();
		try {
			drawXform.getInverse(inverseConcatLTs);
		} catch (NotInvertibleException e) {
			e.printStackTrace();
		}
		inverseConcatLTs.transformPoint(pts, pts);
		return pts;
	}
	
	public void placeAt(float x, float y) {
		float[] pts = getInverse(x, y);
		this.setLocalX(pts[0]);
		this.setLocalY(pts[1]);
	}

}
