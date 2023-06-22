package BrickBreaker;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.util.Random;

public class Ball extends Rectangle {
    private static final long serialVersionUID = 1L;

    private Random random;
    int xVelocity, yVelocity;
    private int initialSpeed = 4;
    boolean clicked = false;

    Ball(int x, int y, int width, int height) {
        super(x, y, width, height);
        random = new Random();
    }

    void draw(Graphics g) {
        g.setColor(Color.RED);
        g.fillOval(x, y, width, height);
    }

    void move() {
        if (clicked) {
        	
            if (xVelocity == 0 && yVelocity == 0) {
                int randomXDirection = random.nextInt(2);
                if (randomXDirection == 0) {
                    randomXDirection--;
                }
                xVelocity = randomXDirection * initialSpeed;

                yVelocity = -initialSpeed;
            }

            x += xVelocity;
            y += yVelocity;
        }
    }
}