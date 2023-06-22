package Pong;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class KeyHandler extends KeyAdapter {
	Paddle paddle1;
	Paddle paddle2;
	
	KeyHandler(Paddle paddle1, Paddle paddle2){
		this.paddle1 = paddle1;
		this.paddle2 = paddle2;
	}
	
	@Override
	public void keyPressed(KeyEvent e) {
		switch(e.getKeyCode()) {
		case KeyEvent.VK_W:
			paddle1.yVelocity = -paddle1.speed;
			break;
		
		case KeyEvent.VK_S:
			paddle1.yVelocity = paddle1.speed;
			break;
		case KeyEvent.VK_UP:
			paddle2.yVelocity = -paddle2.speed;
			break;
		case KeyEvent.VK_DOWN:
			paddle2.yVelocity = paddle1.speed;
			break;
		}
				
	}
	
	@Override
	public void keyReleased(KeyEvent e) {
		
		switch(e.getKeyCode()) {
		case KeyEvent.VK_W:
			paddle1.yVelocity = 0;
			break;
		
		case KeyEvent.VK_S:
			paddle1.yVelocity = 0;
			break;
		case KeyEvent.VK_UP:
			paddle2.yVelocity = 0;
			break;
		case KeyEvent.VK_DOWN:
			paddle2.yVelocity = 0;
			break;
		}
	}
}

