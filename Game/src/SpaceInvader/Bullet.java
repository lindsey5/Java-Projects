package SpaceInvader;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;

public class Bullet extends Rectangle{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	int speed = 10;
	int yVelocity;
	boolean intersectsWithEnemy = false;
	
	Bullet(int x, int y, int BULLET_WIDTH, int BULLET_HEIGHT){
		super(x, y, BULLET_WIDTH, BULLET_HEIGHT);
		yVelocity = -speed;
	}
	
	void draw(Graphics g) {
		if(!intersectsWithEnemy) {
		g.setColor(Color.RED);
		g.fillRect(x, y, width, height);
		}
	}
	
	void move() {
		y += yVelocity;
	}

}
