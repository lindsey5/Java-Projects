package FlappyBird;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class KeyHandler extends KeyAdapter {
    Bird bird;
	GamePanel gamePanel;
    
	KeyHandler(Bird bird, GamePanel gamePanel){
		this.bird = bird;
		this.gamePanel = gamePanel;
	}
	
	public void keyPressed(KeyEvent e) {
		if(gamePanel.running) {
			if(e.getKeyCode() == KeyEvent.VK_SPACE) {
				bird.jump();
			}
		}else {
			gamePanel.reset();
		}
	}
	
	
}
