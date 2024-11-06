package gameObjects;

import com.codename1.ui.Graphics;
import com.codename1.ui.Transform;
import com.codename1.ui.Transform.NotInvertibleException;

public abstract class Shape {
	public Transform XYform = Transform.makeIdentity();
	public Transform ROTform = Transform.makeIdentity();
	public Transform drawXform = Transform.makeIdentity();
	
	public void selfDraw(Graphics g) {};
	
	public boolean selfHit(float x, float y) {
		return false;
	}
	
	public void draw(Graphics g, int x, int y, boolean box) {
		Transform xForm = Transform.makeIdentity();
		Transform old = Transform.makeIdentity();
		
		g.getTransform(old);
		g.getTransform(xForm);
		
		
		//xForm.translate(x,y);
		//TODO Remove this translation when we do the VTM handling
		
		xForm.translate(x, y);
		xForm.concatenate(XYform);
		xForm.translate(-x, -y);

		drawXform.setTransform(xForm);
		
		xForm.concatenate(ROTform);
		
		
		
		g.setTransform(xForm);
		selfDraw(g);
		if(box) {
			drawBox(g);
		}
		g.setTransform(old);
	}
	
	public void drawBox(Graphics g) {
		// TODO Auto-generated method stub
		
	}

	public boolean contain(float x, float y) {
		boolean hit = false;
		
		return selfHit(x,y);
	}
	
	public float[] getInv(float x, float y) {
		float[] pts = {x,y};
		Transform invLTs = Transform.makeIdentity();
		try {
			drawXform.getInverse(invLTs);
		} catch(NotInvertibleException e) {
			e.printStackTrace();
		}
		invLTs.transformPoint(pts, pts);
		return pts;
	}
	
	public void setRotate(){
		
	}
	
	public void rotate() {
		
	}
	
}