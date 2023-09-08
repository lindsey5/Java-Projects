package SpaceInvader;

import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;

public class Player extends Rectangle{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	BufferedImage spaceshipImage;
	int xPos;
	
	Player(int playerX, int playerY, int PLAYER_WIDTH, int PLAYER_HEIGHT){
		super(playerX, playerY, PLAYER_WIDTH, PLAYER_HEIGHT);
		
		try {
			spaceshipImage = ImageIO.read(getClass().getResourceAsStream("/SpaceInvader/spaceship.png"));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	void draw(Graphics g) {
		//g.fillRect(x, y, width/2, height);
		g.drawImage(spaceshipImage, x, y, width, height, null);
	}
	
	void move() {
		x = xPos;
	}
	
	void setXPosition(int xPos) {
		this.xPos = xPos;
	}
	
	
}
