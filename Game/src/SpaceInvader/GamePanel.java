package SpaceInvader;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.util.ArrayList;
import java.util.Random;

import javax.swing.JPanel;

public class GamePanel extends JPanel implements Runnable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	static final int GAME_WIDTH = 500, GAME_HEIGHT = 600;
	static final int PLAYER_WIDTH = 60, PLAYER_HEIGHT = 60;
	static final int ENEMY_WIDTH = 50, ENEMY_HEIGHT = 50;
	Player player;
	ArrayList<Bullet> bullets;
	ArrayList<Enemy> enemies;
	Thread gameThread;
	boolean running = false;
	int FPS = 60;
	
	GamePanel(){
		enemies = new ArrayList<>();
		int x = 100;
		int y = 100;
		
			Random random = new Random();
			int xDirection = random.nextInt(2);
			if(xDirection == 0) {
				xDirection--;
				
			}
			
		for(int i=0;i<10;i++) {
			enemies.add(new Enemy(x,y,ENEMY_WIDTH,ENEMY_HEIGHT,this));
			enemies.get(i).setXDirection(xDirection);
			x+=60;
			if(i==4) {
				x=100;
				y+=60;
			}
		}
		
		bullets = new ArrayList<>();
		player = new Player((GAME_WIDTH/2) - (PLAYER_WIDTH/2), GAME_HEIGHT - PLAYER_HEIGHT * 2, PLAYER_WIDTH, PLAYER_HEIGHT);
		
		this.setPreferredSize(new Dimension(GAME_WIDTH, GAME_HEIGHT));
		this.setBackground(Color.BLACK);
		this.addMouseListener(new MouseHandler(player, this));
		this.addMouseMotionListener(new MouseHandler(player, this));
		startGame();
	}
	
	
	void startGame() {
		gameThread = new Thread(this);
		running = true;
		gameThread.start();
		//newBullet();
	}
	
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
	
	public void paint(Graphics g) {
		super.paint(g);
		draw(g);
		
	}
	
	void draw(Graphics g) {
		for(Enemy enemy : enemies) {
			enemy.draw(g);
		}
		
		player.draw(g);
		
		for(Bullet bullet : bullets) {
			bullet.draw(g);
		}
	}
	
	void move() {
		player.move();
		for(Enemy enemy : enemies) {
			enemy.move();
		}
		
		for(Bullet bullet : bullets) {
			bullet.move();
		}
	}
	
	void checkCollisions() {
		
		if(enemies.get(0).getX()< 0 ) {
			for(Enemy enemy :enemies) {
			enemy.xVelocity = Math.abs(enemy.xVelocity);
			}
			
		}
		
		if(enemies.get(4).getX() > GAME_WIDTH - ENEMY_WIDTH) {
			for(Enemy enemy : enemies) {
			enemy.xVelocity = -Math.abs(enemy.xVelocity);
			}
		}
		
		for(Bullet bullet : bullets) {
			for(Enemy enemy : enemies) {
			if(bullet.intersects(enemy) && !bullet.intersectsWithEnemy && !enemy.dead) {
				enemy.lives--;
				enemy.barValue -= 20;
				bullet.intersectsWithEnemy = true;
			}
			if(enemy.lives==0) {
				enemy.dead = true;
			}
			}
		}
		
	}
	
	void newBullet() {
		bullets.add(new Bullet((int)player.getX() + (PLAYER_WIDTH/2) - (10/2), (int)player.getY(), 5, 10));
	}
	
	

}
