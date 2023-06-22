package SpaceInvader;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class MouseHandler extends MouseAdapter{
	Player player;
	GamePanel gamePanel;
	
	MouseHandler(Player player, GamePanel gamePanel){
		this.player = player;
		this.gamePanel = gamePanel;
	}
	
	@Override
	public void mouseMoved(MouseEvent e){
		player.setXPosition(e.getX() - player.width/2);
	}
	
	@Override
	public void mousePressed(MouseEvent e) {
		gamePanel.newBullet();
	}


}
