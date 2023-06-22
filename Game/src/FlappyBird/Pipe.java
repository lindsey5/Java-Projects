package FlappyBird;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;

public class Pipe extends Rectangle{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private int speed =3;
	private boolean passed = false;
	
	Pipe(int x, int y, int WIDTH, int HEIGHT){
		super(x, y, WIDTH, HEIGHT);
		
	}
	
	
	void draw(Graphics g) {
		g.setColor(Color.GREEN);
		g.fillRect(x, y, width, height);
		
	}
	
	void setPassed(boolean passed) {
		this.passed = passed;
	}
	
	boolean isPassed() {
		return passed;
	}
	
	
	void move() {
		x-=speed;
		
	}

}
