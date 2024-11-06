package gameObjects;

import com.codename1.charts.util.ColorUtil;
import com.codename1.ui.Graphics;

public class WaterDispenser extends Facility {
	
	public WaterDispenser(float x, float y) {
		super(x, y);
		setStatusID(1);
		super.setColor(ColorUtil.BLUE);
		super.setSize(50);
	}
	
	@Override
	public void selfDraw(Graphics g) {
		
		g.setColor(this.getColor());
		g.fillArc((int)(-getSize()/2), (int)(-getSize()/2), (int)getSize(), (int)getSize(), 0, 360);
	}
	
	@Override
	public boolean selfHit(float x, float y) {
		float[] pts = getInverse(x, y);
		x = pts[0];
		y = pts[1];
		//System.out.println(x +  " , " + y);
		if (x*x + y * y < getSize() * getSize()) {
			return true;
		}
		return false;
	}

}
