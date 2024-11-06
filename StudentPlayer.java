package gameObjects;

import com.codename1.charts.util.ColorUtil;
import com.codename1.ui.Graphics;
import com.csus.csc133.GameModel;

//TODO: Make a Singleton
public class StudentPlayer extends Student {
	private int absenceTime;
	private static StudentPlayer player;
	
	private StudentPlayer(float x, float y, float direction){
		super(x,y,direction);
		super.setStatus(0);
		this.absenceTime = 0;
		//Student player should be a consistent size
		super.setSize(50);
	}
	
	public int getAbsenceTime() {
		return player.absenceTime;
	}
	public void incAbsenseTime() {
		player.absenceTime ++;
	}
	
	public static StudentPlayer getPlayer(GameModel gm) {
		if(player == null) {
			player = new StudentPlayer(gm.getWidth()/2,gm.getHeight()/2,90);
		}
		return player;
		
	}
	public static void placeCenter(GameModel gm) {
		player.setLocalX(gm.getWidth()/2);
		player.setLocalY(gm.getHeight()/2);
		
	}
	
	@Override
	public void selfDraw(Graphics g) {
		
		g.setColor(this.getColor());
		g.fillPolygon(xPts, yPts, 3 );
	}
}
