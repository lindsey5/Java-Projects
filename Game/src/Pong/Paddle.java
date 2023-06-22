package Pong;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Rectangle;

public class Paddle extends Rectangle {
	
	private static final long serialVersionUID = 1L;
	Color color;
	int speed = 10;
	int yVelocity;
	
	
	
	Paddle(int x, int y, int width, int height, Color color) {
		super(x, y, width, height);
		this.color = color;
		
	}
	
	
	void draw(Graphics g) {
		Graphics2D g2 = (Graphics2D) g;
		g2.setPaint(color);
		g2.fill(this);
		
	}
	
	void move() {
		y += yVelocity;
	}
	
	
}