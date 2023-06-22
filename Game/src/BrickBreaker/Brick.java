package BrickBreaker;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;

public class Brick extends Rectangle{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	boolean destroyed = false;
	
	Brick(int x,int y,int WIDTH,int HEIGHT){
		super(x, y, WIDTH, HEIGHT);
	}
	
	void destroy() {
		destroyed = true;
	}
	
	void draw(Graphics g) {
		if(!destroyed) {
		
		g.setColor(new Color(153,76,0));
		g.fillRect(x, y, width, height);
		g.setColor(Color.WHITE);
		g.drawRect(x, y, width-1, height-1);
		}
	}

}
