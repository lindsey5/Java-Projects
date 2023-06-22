package BrickBreaker;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Rectangle;

public class Paddle extends Rectangle{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	Color color;
	int xPos;

	Paddle(int x,int y, int PADDLE_WIDTH, int PADDLE_HEIGHT,Color color){
		super(x,y,PADDLE_WIDTH,PADDLE_HEIGHT);
		this.color = color;
		
	}
	
	void draw(Graphics g) {
		
		Graphics2D g2 = (Graphics2D) g;
		g2.setPaint(color);
		g2.fill(this);
	}
	
	void move() {
		x = xPos;
	}

}
