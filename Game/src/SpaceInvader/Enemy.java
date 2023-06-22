package SpaceInvader;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import java.io.IOException;
import javax.imageio.ImageIO;
import javax.swing.JProgressBar;

public class Enemy extends Rectangle {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	int xVelocity;
	int speed = 2;
	int lives = 5;
	BufferedImage enemyImage;
	JProgressBar bar;
	int barValue = 100;
	boolean dead = false;
	GamePanel panel;
	
	Enemy(int x, int y, int width, int height, GamePanel panel){
		super(x, y, width, height);
		this.panel = panel;
		
		bar = new JProgressBar();
		bar.setForeground(Color.GREEN);
		
		try {
			enemyImage = ImageIO.read(getClass().getResourceAsStream("/SpaceInvader/alien.png"));
		} catch (IOException e) {
			e.printStackTrace();
		}
		xVelocity = speed;
	}
	
	void move() {
		x+= xVelocity;
	}
	
	void setXDirection(int xDir) {
		xVelocity= speed * xDir;
	}
	
	void draw(Graphics g) {
		if(lives>0) {
			bar.setValue(barValue);
			bar.setBounds(x, y, width, 7);
			panel.add(bar);
			g.drawImage(enemyImage, x, y, width+5, height+5, null);
		}else {
			bar.setVisible(false);
		}
	}
	
}
