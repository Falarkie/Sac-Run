package gameObjects;

import com.codename1.charts.util.ColorUtil;
import com.codename1.ui.Graphics;

public class RestRoom extends Facility {
	
	public RestRoom(float x, float y) {
		super(x, y);
		setStatusID(2);
		
		super.setColor(ColorUtil.GREEN);
		super.setSize(50);
	}
	
	@Override
	public void selfDraw(Graphics g) {
		
		g.setColor(this.getColor());
		g.fillRect((int)(-getSize()/2), (int)(-getSize()/2), (int)getSize(),(int)getSize());
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
