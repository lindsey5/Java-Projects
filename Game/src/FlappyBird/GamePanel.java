package FlappyBird;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.util.Random;

import javax.swing.JPanel;

public class GamePanel extends JPanel implements Runnable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	static final int GAME_WIDTH = 700;
	static final int GAME_HEIGHT = 400;
	static final int BIRD_WIDTH = 50;
	static final int BIRD_HEIGHT = 40;
	static final int PIPE_WIDTH = 80;
	int PIPE_HEIGHT;
	Bird bird;
	Thread gameThread;
	int FPS = 60;
	boolean running = false;
	
	Random random;
	Pipe topPipe;
	Pipe bottomPipe;
	int pipeGap = 140;
	
	int score = 0;
	
	GamePanel(){
		random = new Random();
		bird = new Bird(100,(GAME_HEIGHT/2) - (BIRD_HEIGHT/2), BIRD_WIDTH, BIRD_HEIGHT);
		
		this.setPreferredSize(new Dimension(GAME_WIDTH, GAME_HEIGHT));
		this.setBackground(Color.CYAN);
		this.addKeyListener(new KeyHandler(bird, this));
		this.setFocusable(true);
		gameThread = new Thread(this);
		startGame();
	}
	
	
	void startGame() {    
		spawnPipes();
		running = true;
		gameThread.start();
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
	
	@Override
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		//g.drawLine(0, GAME_HEIGHT/2, GAME_WIDTH, GAME_HEIGHT/2);
		draw(g);
		g.setColor(Color.RED);
		g.setFont(new Font("Arial",Font.BOLD,30));
		g.drawString(""+score,20, 30);
		
		if(!running) {
			g.setFont(new Font("Arial",Font.BOLD,60));
			g.drawString("GAME OVER",160, 200);
		}
		
	}
	
	void draw(Graphics g) {
		bird.draw(g);
		bottomPipe.draw(g);
		topPipe.draw(g);
		
	}
	
	void move() {
		bird.move();
		
		topPipe.move();
		bottomPipe.move();
		
		if(topPipe.getX() <0 - PIPE_WIDTH && bottomPipe.getX() <0 - PIPE_WIDTH) {
			spawnPipes();
		}
		
		if(topPipe.getX() == bird.getX() - PIPE_WIDTH 
				&& bottomPipe.getX() == bird.getX() - PIPE_WIDTH) {
				score ++;
		}
	}
	
	void checkCollisions() {
		 
		if(bird.intersects(topPipe) || bird.intersects(bottomPipe)) {
			running = false;
		}
		
		
		if(bird.y <= 0) {
			bird.y = 0;
		}
		
		if(bird.y > GAME_HEIGHT) {
			running = false;
		}
		
	}
	
	void spawnPipes() {
		PIPE_HEIGHT = random.nextInt(GAME_HEIGHT - pipeGap);
		
		topPipe = new Pipe(GAME_WIDTH - PIPE_WIDTH, 0, PIPE_WIDTH, PIPE_HEIGHT);
		bottomPipe = new Pipe(GAME_WIDTH - PIPE_WIDTH, PIPE_HEIGHT+pipeGap,PIPE_WIDTH,GAME_HEIGHT - PIPE_HEIGHT - pipeGap);
		
	}
	
	void reset() {
		score = 0;
		running = true;
		bird = new Bird(100,(GAME_HEIGHT/2) - (BIRD_HEIGHT/2), BIRD_WIDTH, BIRD_HEIGHT);
		spawnPipes();
		this.addKeyListener(new KeyHandler(bird, this));
		gameThread = new Thread(this);
		gameThread.start();
		
	}

}
