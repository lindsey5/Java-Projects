package BrickBreaker;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class MouseHandler extends MouseAdapter {
	
	private Paddle paddle;
	private Ball ball;
	
	public MouseHandler(Paddle paddle, Ball ball) {
		this.paddle = paddle;
		this.ball = ball;
		
	}
	
	@Override
	public void mouseMoved(MouseEvent e) {
		paddle.xPos = (int)(e.getX() - paddle.getWidth() / 2);
		if(!ball.clicked) {
		ball.x = (int)(e.getX() - ball.getWidth() / 2);
		}
	
	}
	
	@Override
	public void mousePressed(MouseEvent e) {
		ball.clicked = true;
		
	}

}
