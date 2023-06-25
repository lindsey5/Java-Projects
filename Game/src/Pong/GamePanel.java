package Pong;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.util.Random;

import javax.swing.JPanel;

public class GamePanel extends JPanel implements Runnable{
	
	private static final long serialVersionUID = 1L;
	static final int GAME_WIDTH = 1000;
	static final int GAME_HEIGHT = (int) (GAME_WIDTH * (0.5555));
	static final int BALL_DIAMETER = 20;
	static final int PADDLE_WIDTH = 25;
	static final int PADDLE_HEIGHT = 100;
	Thread gameThread;
    Paddle paddle1;
    Paddle paddle2;
    Ball ball;
	int FPS = 60;
	Random random;
	int player1score=0, player2score=0;
	
	
	GamePanel() {
		gameThread = new Thread(this);
		this.setPreferredSize(new Dimension(GAME_WIDTH, GAME_HEIGHT));
		this.setBackground(Color.BLACK);
		this.setFocusable(true);
		startGame();
		
	}
	
	void startGame() {
		newPaddles();
		newBall();
		gameThread.start();
		// repaint();
	}
	
	
	public void run() {
		// Game loop
		double drawInterval = 1000000000/FPS;
		double delta = 0;
		long lastTime = System.nanoTime();
		long currentTime;
		long timer = 0;
		int drawCount = 0;
		
		while(gameThread != null) {
			currentTime = System.nanoTime();
			
			delta += (currentTime - lastTime) / drawInterval;
			timer += (currentTime - lastTime);
			lastTime = currentTime;
			if(delta >=1) {
				move();
				checkCollisions();
				repaint();
				delta--;
				drawCount++;
				
			}
			if(timer >= 1000000000) {
				System.out.println("FPS:"+drawCount);
				drawCount = 0;
				timer = 0;
			}
		}
	}
	
	
	void newPaddles() {
		paddle1 = new Paddle(0, (GAME_HEIGHT / 2) - (PADDLE_HEIGHT / 2), PADDLE_WIDTH, PADDLE_HEIGHT, Color.BLUE);
		paddle2 = new Paddle(GAME_WIDTH - PADDLE_WIDTH, (GAME_HEIGHT / 2) - (PADDLE_HEIGHT / 2), PADDLE_WIDTH, PADDLE_HEIGHT, Color.RED);
		this.addKeyListener(new KeyHandler(paddle1,paddle2));
	}
	
	@Override
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		g.setColor(Color.WHITE);
		g.drawLine(GAME_WIDTH/2, 0, GAME_WIDTH/2, GAME_HEIGHT);
		
		g.setColor(Color.red);
 		g.setFont(new Font("Arial",Font.PLAIN,60));
		g.drawString(String.valueOf(player1score/10)+String.valueOf(player1score%10), (GAME_WIDTH/2)-85, 50);
		g.drawString(String.valueOf(player2score/10)+String.valueOf(player2score%10), (GAME_WIDTH/2)+20, 50);
		draw(g);
	
	}
	
	void newBall() {
		random = new Random();
		ball = new Ball((GAME_WIDTH/2) - (BALL_DIAMETER/2), random.nextInt(GAME_HEIGHT-BALL_DIAMETER),BALL_DIAMETER,BALL_DIAMETER);
	}
	
	void draw(Graphics g) {
		paddle1.draw(g);
		paddle2.draw(g);
		ball.draw(g);
	}
	
	
	void move() {
		paddle1.move();
		paddle2.move();
		ball.move();
	}
	
	void checkCollisions() {
		
			if(ball.y <=0) {
				ball.setYDirection(-ball.yVelocity);
			}
			
			if(ball.y >= GAME_HEIGHT-BALL_DIAMETER) {
				ball.setYDirection(-ball.yVelocity);
			}
			
			if(ball.intersects(paddle1)) {
				ball.xVelocity = Math.abs(ball.xVelocity);
				ball.xVelocity++;
				
				if(ball.yVelocity>0) {
					ball.yVelocity++;
				}else {
					ball.yVelocity--;
				}
				ball.setXDirection(ball.xVelocity);
				ball.setYDirection(ball.yVelocity);
				
			}
			
			if(ball.intersects(paddle2)) {
				ball.xVelocity = Math.abs(ball.xVelocity);
				ball.xVelocity++;
				
				
				if(ball.yVelocity>0) {
					ball.yVelocity++;
				}else {
					ball.yVelocity--;
				}
				ball.setXDirection(-ball.xVelocity);
				ball.setYDirection(ball.yVelocity);
				
			}

					
			if(paddle1.y<=0) {
				paddle1.y=0;
			}
			if(paddle1.y >= GAME_HEIGHT - PADDLE_HEIGHT) {
				paddle1.y = GAME_HEIGHT - PADDLE_HEIGHT;
			}
			
			if(paddle2.y<=0) {
				paddle2.y=0; 
			}
			if(paddle2.y >= GAME_HEIGHT - PADDLE_HEIGHT) {
				paddle2.y = GAME_HEIGHT - PADDLE_HEIGHT;
			}
			
			if(ball.x <= 0) {
				player2score++;
				newPaddles();
				newBall();
			}
			
			if(ball.x >= GAME_WIDTH-BALL_DIAMETER) {
				player1score++;
				newPaddles();
				newBall();
			}
			
		
	}
	
	
}