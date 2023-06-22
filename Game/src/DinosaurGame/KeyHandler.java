package DinosaurGame;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class KeyHandler extends KeyAdapter {
	private Player player; 
	private GamePanel gamePanel;
	
	KeyHandler(Player player, GamePanel gamePanel){
		this.player = player;
		this.gamePanel = gamePanel;
	}
	
	public void keyPressed(KeyEvent e) {
		if(e.getKeyCode() == KeyEvent.VK_SPACE) {
			if(gamePanel.running) {
				player.setDown(false);
			
				if(player.y == 290)
					player.jump();
			}else {
				
				gamePanel.reset();
			}
				
		}
		
		if(e.getKeyCode() == KeyEvent.VK_DOWN) {
			if(!player.isDown()) {
				if(player.y == 290 || player.y == 320)
					player.setDown(true);
			}
		}
	}
	
	public void keyReleased(KeyEvent e) {
		player.setDown(false);
	}

}
