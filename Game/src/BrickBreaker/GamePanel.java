package BrickBreaker;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.util.ArrayList;

import javax.swing.JPanel;

public class GamePanel extends JPanel implements Runnable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	static final int GAME_WIDTH = 500;
	static final int GAME_HEIGHT = 700;
	static final int PADDLE_WIDTH = 70;
	static final int PADDLE_HEIGHT = 12;
	static final int BALL_DIAMETER =20;
	
	Paddle paddle;
	Ball ball;
	ArrayList<Brick> bricks;
	Thread gameThread;
	int FPS = 60;
	MouseHandler mouseHandler;
	boolean running;
	
	GamePanel(){
		bricks =  new ArrayList<>();
		paddle = new Paddle((GAME_WIDTH/2) - PADDLE_WIDTH/2,GAME_HEIGHT-60,PADDLE_WIDTH,PADDLE_HEIGHT,Color.BLUE);
		ball = new Ball((GAME_WIDTH/2) - (BALL_DIAMETER/2),(GAME_HEIGHT-PADDLE_HEIGHT)- 70, BALL_DIAMETER, BALL_DIAMETER);
		mouseHandler = new MouseHandler(paddle,ball);
		this.setPreferredSize(new Dimension(GAME_WIDTH, GAME_HEIGHT));
		this.setBackground(Color.BLACK);
		this.addMouseMotionListener(mouseHandler);
		this.addMouseListener(mouseHandler);
		gameThread = new Thread(this);
		setBricks();
		gameStart();
		
	}
	
	void setBricks() {
		int x = 30;
		int y = 40;
		
		
		for(int i=1;i<=30;i++) {
			bricks.add(new Brick(x, y, 80, 50));
			x+=90;
			if(i%5==0) {
			x= 30;
			y+=60;
			}
		}
		
		
		
		//System.out.println(bricks.size());
	}
	
	void gameStart() {
		running = true;
		gameThread.start();
		setBricks();
	}
	
	@Override
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
	      
		if(running) {
		draw(g);
		
		}else {
			g.setColor(Color.RED);
			g.setFont(new Font("Arial",Font.BOLD,60));
			g.drawString("Game Over", 95 , 300);
		}
	}
	
	void draw(Graphics g) {
		paddle.draw(g);
		ball.draw(g);
		for(int i =0;i<bricks.size();i++) {
			bricks.get(i).draw(g);
		}

	}

    void move() {
    	paddle.move();
    	ball.move();
  
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
	
	
	void checkCollisions() {
		
	    if (paddle.x <= 0) {
	        paddle.x = 0;
	        if(!ball.clicked) {
	        ball.x = paddle.x + (PADDLE_WIDTH / 2) - (BALL_DIAMETER / 2);
	        }
	        
	    }
	    
	    if (paddle.x >= GAME_WIDTH - PADDLE_WIDTH) {
	        paddle.x = GAME_WIDTH - PADDLE_WIDTH;
	        if(!ball.clicked) {
	        ball.x = paddle.x + (PADDLE_WIDTH / 2) - (BALL_DIAMETER / 2);
	        }
	    }
	    
	    if(ball.intersects(paddle) && ball.y > paddle.y - BALL_DIAMETER) {
	    	ball.yVelocity = Math.abs(ball.yVelocity);
			ball.yVelocity=-ball.yVelocity;
			
		}
	    
	    if (ball.x <= 0) {
	        ball.x = 0;
	        ball.xVelocity = Math.abs(ball.xVelocity); // Make the ball bounce off the left wall
	    }

	    if (ball.x >= GAME_WIDTH - BALL_DIAMETER) {
	        ball.x = GAME_WIDTH - BALL_DIAMETER;
	        ball.xVelocity = -Math.abs(ball.xVelocity); // Make the ball bounce off the right wall
	    }

	    if (ball.y <= 0) {
	        ball.y = 0;
	        ball.yVelocity = Math.abs(ball.yVelocity); // Make the ball bounce off the top wall
	    }

	    if (ball.y > GAME_HEIGHT) {
	       running = false;
	    }
	    
	    for(Brick brick : bricks) {
	    if(ball.intersects(brick) && !brick.destroyed) {
	    	brick.destroy();
	    	
	    	if(ball.xVelocity<0 && ball.yVelocity>0) {
	    		ball.xVelocity = Math.abs(ball.xVelocity);
	    		ball.yVelocity = -Math.abs(ball.yVelocity);
	    		
	    	}else if(ball.xVelocity>0 && ball.yVelocity>0) {
	    		ball.xVelocity = -Math.abs(ball.xVelocity);
	    		ball.yVelocity = -Math.abs(ball.yVelocity);
	    		
	    	}else if(ball.xVelocity<0) {
	    		ball.xVelocity = Math.abs(ball.xVelocity);
	    		
	    	}else if(ball.yVelocity<0){
	    	    ball.yVelocity = Math.abs(ball.yVelocity);
	    	    
	    	}else if(ball.xVelocity>0) {
	    	    ball.xVelocity = -Math.abs(ball.xVelocity);
	    	    
	    	}else if(ball.yVelocity>0) {
		    	ball.yVelocity = -Math.abs(ball.yVelocity);
		    }
	    	
	   }
	    
	    }
	}

}
