package SnakeGame;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.JPanel;

public class GamePanel extends JPanel implements KeyListener{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	static final int WIDTH = 600, HEIGHT = 600;
	static final int UNIT_SIZE = 30;
	static final int GAME_UNITS = WIDTH*HEIGHT / UNIT_SIZE;
	int appleX, appleY;
	Thread thread;
	boolean running = false, gameOver = false, pause = false;
	int snakelength = 6;
	int[] x = new int[GAME_UNITS];
	int[] y = new int[GAME_UNITS];
	char direction = 'R';
	int score = 0;
	
	GamePanel(){
		this.setPreferredSize(new Dimension(WIDTH,HEIGHT));
		this.setBackground(Color.BLACK);
		this.setFocusable(true);
		this.addKeyListener(this);
		startGame();
	}
	
	void startGame() {
		x = new int[GAME_UNITS];
		y = new int[GAME_UNITS];
		setGameThread();
		running = true;
		newApple();
	}
	
	void setGameThread() {
		thread = new Thread(new Runnable() {

			@Override
			public void run() {
				while(running) {
					
					try {
						move();
						checkCollisions();
						Thread.sleep(200);
						repaint();
						
					} catch (InterruptedException e) {
						
						e.printStackTrace();
					}
				}
			}
			
		});
		
		thread.start();
		
		
	}
	
	
	@Override
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		
		if(running) {
			
		g.setColor(Color.RED);
		g.fillRect(appleX, appleY, UNIT_SIZE, UNIT_SIZE);
		
		for(int i=0;i<snakelength;i++) {
			
			if(i==0) {
				g.setColor(new Color(0,102,0));
			}else {
				g.setColor(Color.GREEN);
			}
			
			g.fillRect(x[i], y[i], UNIT_SIZE, UNIT_SIZE);
		}
		
		g.setColor(Color.RED);
		g.setFont(new Font("Arial",Font.BOLD,35));
		g.drawString("Score: "+score, 235, 30);
		
		if(pause) {
			g.setColor(Color.RED);
			g.setFont(new Font("Arial",Font.BOLD,60));
			g.drawString("Pause", 220, 250);
		}
		
		
		}else if(gameOver) {
			g.setColor(Color.RED);
			g.setFont(new Font("Arial",Font.BOLD,50));
			g.drawString("Game Over", 160, 250);
		}
		
		
	}
	
	void newApple() {
		 appleX = (int) (Math.random() * (WIDTH / UNIT_SIZE)) * UNIT_SIZE;
	     appleY = (int) (Math.random() * (HEIGHT / UNIT_SIZE)) * UNIT_SIZE;
	}
	
	void move() {
		
		if(!pause) {
		for(int i=snakelength;i>0;i--) {
			x[i] = x[i-1];
			y[i] = y[i-1];
		}
		
		switch(direction) {
		case 'R':
			x[0] = x[0] + UNIT_SIZE;
			break;
		case 'L':
			x[0] = x[0] - UNIT_SIZE;
			break;
		case 'D':
			y[0] = y[0] + UNIT_SIZE;
			break;
		case 'U':
			y[0] = y[0] - UNIT_SIZE;
			break;
		
		}
		}
		
		
	}
	
	void checkCollisions() {
		
		for(int i=1;i<snakelength;i++) {
			if(x[0] == x[i] && y[0] == y[i]) {
				running = false;
				gameOver = true;
			}
		}
		
		if(x[0]==appleX && y[0] == appleY ) {
			newApple();
			score++;
		}
		
		if(x[0]<0 || x[0] >= WIDTH || y[0] <0 || y[0] >=HEIGHT) {
			gameOver = true;
			running = false;
		}
		
	}
	

	@Override
	public void keyTyped(KeyEvent e) {}

	@Override
	public void keyPressed(KeyEvent e) {	
			
		switch(e.getKeyCode()) {
				 
			case KeyEvent.VK_A:
					 
				if(direction != 'R') {
					direction = 'L';
						 
				}
				break;
			case KeyEvent.VK_D:
					 
				if(direction != 'L') {
					direction = 'R';
						 
				}
				break;
			case KeyEvent.VK_S:
				if(direction != 'U') {
					direction = 'D';
					
				}
				break;
					 
			case KeyEvent.VK_W:
				if(direction != 'D') {
					direction = 'U';
							
				}
				break;	 
			case KeyEvent.VK_SPACE:
				if(running) {
					if(!pause) {
						pause = true;
					}else if(pause) {
						pause = false;
					}
				}else if(gameOver) {
					gameOver=false;
					direction = 'R';
					score = 0;
					snakelength = 6;
					for(int i=0;i<snakelength;i++) {
                    	x[i]=0;
                    	y[i]=0;
                    }
					startGame();
				}
		}

	}

	@Override
	public void keyReleased(KeyEvent e) {}
	

}
