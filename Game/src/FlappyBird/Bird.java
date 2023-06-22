package FlappyBird;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;

public class Bird extends Rectangle {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private int gravity = 5;
	private int yVelocity;
	
	Bird(int x, int y, int WIDTH, int HEIGHT){
		super(x, y, WIDTH, HEIGHT);
		yVelocity = gravity;
	}
	
	void move() {
		y += yVelocity;
		yVelocity = gravity;
		
	}
	
	void draw(Graphics g) {
		g.setColor(Color.RED);
		g.fillRect(x, y, width, height);
		
	}
	
	void jump() {
		yVelocity = -60;
		
	}

}
