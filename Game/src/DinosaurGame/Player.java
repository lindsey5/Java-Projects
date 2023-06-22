package DinosaurGame;

import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;

public class Player extends Rectangle {
	
	private static final long serialVersionUID = 1L;
	
	private static final int FRAME_DELAY = 5; // Delay between each frame (adjust as needed)
	private BufferedImage[] downImages;
	private BufferedImage[] runImages;
	private BufferedImage dead;
	
	private int currentFrame;
	private int frameTimer;
	
	private boolean isDown = false;
	float gravity = 5.7f;
	private float yVelocity;
	boolean isJumping = false;
	GamePanel gamePanel;
	
	Player(int x, int y, int width, int height, GamePanel gamePanel) {
		super(x, y, width, height);
		this.gamePanel = gamePanel;
		downImages = new BufferedImage[2];
		runImages = new BufferedImage[3];
		currentFrame = 0;
		frameTimer = 1;
		
		try {
			//Initialized the images for animations
			for (int i = 0; i <3; i++) {
				int j = i+1;
				runImages[i] = ImageIO.read(getClass().getResourceAsStream("/DinosaurGame/main-character"+ j +".png"));
			}
			
			for(int i=0;i<2;i++) {
				int j = i+5;
				downImages[i] = ImageIO.read(getClass().getResourceAsStream("/DinosaurGame/main-character"+ j +".png"));
			}
			
			 dead = ImageIO.read(getClass().getResourceAsStream("/DinosaurGame/main-character4.png"));
			
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		yVelocity = gravity;
	}
	
	void drawDead(Graphics g) {
		g.drawImage(dead, x, y, width, height, null);
	}
	
	void draw(Graphics g) {
		
		//This will drawn if the game is running
		if(gamePanel.running) {
		
		
		//If isJumping is true 
		if(isJumping) {
			g.drawImage(runImages[2], x, y, width, height, null);
		
		//If isDown is true	
		}else if(isDown) {
			if(currentFrame < downImages.length)
			g.drawImage(downImages[currentFrame], x, y, width, height, null);
			
		}else {
			if(currentFrame < runImages.length)
		    g.drawImage(runImages[currentFrame], x, y, width, height, null);
		}
		
		// Update the frame
		frameTimer++;
		if (frameTimer >=FRAME_DELAY) {
			if(isDown) {
				currentFrame = (currentFrame+1) % downImages.length;
			}else {
				currentFrame = (currentFrame+1) % runImages.length;
			}
			frameTimer = 0;
		}
		}
		
	}
	void move() {
		y += yVelocity;
		yVelocity =gravity;
		//If the y-coordinate of character is 290 isJumping variable will be false	
		if(y == 290) {
			isJumping = false;
		}
	}
	
	void jump() {
		yVelocity = -170;
		isJumping = true;
	}
	
	boolean isDown() {
		return isDown;
	}
	
	void setDown(boolean down) {
		isDown = down;
		if(down) {
			width = 70;
			height = 60/2;
		}else {
			reset();
		}
	}
	
	void reset() {
		width = 50;
		height = 60;
	}
	
	
}