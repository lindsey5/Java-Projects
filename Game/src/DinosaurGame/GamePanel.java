package DinosaurGame;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.util.Random;

import javax.swing.JPanel;

public class GamePanel extends JPanel implements Runnable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	static final int GAME_WIDTH = 900;
	final int GAME_HEIGHT = 400;
	static final int Player_WIDTH = 50;
	static final int Player_HEIGHT = 60;
	Obstacle obstacle;
	Player player;
	
	boolean running = false;
	int FPS = 60;
	Thread gameThread;
	int score = 0;
	int obstacleSpeed = 4;

	
	GamePanel(){
		player = new Player((GAME_HEIGHT/2)-(Player_WIDTH), GAME_HEIGHT-Player_HEIGHT-50, Player_WIDTH, Player_HEIGHT,this);
		gameThread = new Thread(this);
		this.setPreferredSize(new Dimension(GAME_WIDTH,GAME_HEIGHT));
		this.setBackground(Color.WHITE);
		this.setFocusable(true);
		this.addKeyListener(new KeyHandler(player,this));
	    startGame();
		
	}
	
	void startGame() {
		running = true;
		gameThread.start();
		newObstacle();
	}
	
	
	@Override
	public void run() {
		// Game loop  
		double drawInterval = 1000000000/FPS;
		double delta = 0;
		long lastTime = System.nanoTime();
		long currentTime;
		long timer = 0;
		int drawCount = 0;
		         
		while(running) {
			currentTime = System.nanoTime();
			delta += (currentTime - lastTime) / drawInterval;
			timer += (currentTime - lastTime);
			lastTime = currentTime;
				if(delta >=1) {
					move();
				    checkCollisions();
				        
				     if(obstacle.getX() < 0) {
				    	newObstacle();
				     }
				        
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
	
	void move() {
		
		obstacle.move();
		
		if(obstacle.getId()!=2 && !obstacle.isPassed()) {
			player.setDown(false);
		}
			
		if(player.y == 290 && obstacle.x == player.x+60) {
			if(obstacle.getId() == 2) {
				player.setDown(true);
			}else {
				player.jump();
			}
		}
			
		if(!obstacle.isPassed() && obstacle.getX() < player.getX() - obstacle.width) {
			score++;
			obstacle.setPassed(true);
			/*if(score % 20 == 0) {
				obstacleSpeed+=2;
				//player.speed+=0.1;
			}*/
		}
		
		player.move();
	}
	
	
	void checkCollisions() {
		
		if(!player.isDown()) {
			
			if(player.y >GAME_HEIGHT-Player_HEIGHT-50) {
				player.y = GAME_HEIGHT-Player_HEIGHT-50;
				
			}
		}else {
			player.y = GAME_HEIGHT-Player_HEIGHT - 20;
		}
		
		
		if(obstacle.intersects(player)) {
			running = false;
				
		}
		
	}
	
	public void paint(Graphics g) {
		super.paintComponent(g);
		/*Graphics2D g2 = (Graphics2D) g;		
		g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
		g2.setPaint(Color.BLACK);
		g2.drawOval(250, 100, 100, 100);
		g2.drawLine(300, 100, 600, 100);
		g2.drawLine(250, 150, 280, 150);
		g2.drawLine(300, 200, 600, 200);
		g2.drawOval(600, 50, 100, 100);
		g2.drawOval(600, 150, 100, 100);*/
		
		g.setColor(Color.BLACK);
		
		g.drawLine(0, 350, GAME_WIDTH,350);
		
		g.setFont(new Font("Arial",Font.BOLD,30));
		g.setColor(Color.RED);
		g.drawString("Score: "+score, 0, 30);
		
		draw(g);
		if(!running){
			g.setColor(Color.RED);
			g.setFont(new Font("Arial",Font.BOLD,60));
			g.drawString("GAMEOVER", 270, 220);
			player.drawDead(g);
		}
	}
	
	void draw(Graphics g) {
		player.draw(g);
		obstacle.draw(g);
	}
	
	void newObstacle() {
		Random random = new Random();
		int n = random.nextInt(3);
		if(n==0) {
			obstacle = new Obstacle(850, 290, 25, Player_HEIGHT, obstacleSpeed, n);
		}else if(n==1) {
			obstacle = new Obstacle(850, 310, 45, Player_HEIGHT-20, obstacleSpeed, n);
		}else {
			obstacle = new Obstacle(850, 270, 50, 40, obstacleSpeed, n);
		}
	}
	
	void reset() {
		running = true;
		newObstacle();
		score = 0;
		gameThread = new Thread(this);
		gameThread.start();
	}
	
}
