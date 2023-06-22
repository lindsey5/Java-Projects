package DinosaurGame;

import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;

public class Obstacle extends Rectangle {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private int xVelocity;
	private boolean passed = false;
	private BufferedImage image;
	private int id;
	
	private int currentFrame;
	private int frameTimer;
	private static final int FRAME_DELAY = 9;
	private BufferedImage[] birdImages = new BufferedImage[2];
	
	Obstacle(int x, int y, int WIDTH, int HEIGHT, int speed, int id){
		super(x, y, WIDTH, HEIGHT);
		this.id = id;
		setXVelocity(speed);
		try {
			if(id==0) {
				image = ImageIO.read(getClass().getResourceAsStream("/DinosaurGame/cactus1.png"));
			}else if(id==1){
				image = ImageIO.read(getClass().getResourceAsStream("/DinosaurGame/cactus2.png"));
			}else {
				birdImages[0] = ImageIO.read(getClass().getResourceAsStream("/DinosaurGame/bird-fly-1.png"));
				birdImages[1] = ImageIO.read(getClass().getResourceAsStream("/DinosaurGame/bird-fly-2.png"));
			}
			
		} catch (IOException e) {
			
			e.printStackTrace();
		}
		
	}
	
	void draw(Graphics g) {
		if(id == 0 || id == 1) {
		g.drawImage(image, x-5, y-5, width+15, height+5, null);
		
		}else {
			g.drawImage(birdImages[currentFrame], x-10, y, width+10, height, null);
			
			frameTimer++;
			if (frameTimer >=FRAME_DELAY) {
			    currentFrame = (currentFrame+1) % birdImages.length;
				frameTimer = 0;
			}
		}
		
	}
	
	void setXVelocity(int speed) {
		xVelocity = -speed;
	}
	
	void move() {
		x+= xVelocity;
		
	}
	
	void setPassed(boolean passed) {
		this.passed = passed;
	}
	
	boolean isPassed() {
		return passed;
	}
	
	
	int getId() {
		return id;
	}

}
