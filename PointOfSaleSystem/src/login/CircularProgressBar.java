package login;

import javax.swing.*;

import AdminPage.AdminWindow;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import MenuPage.Window;

public class CircularProgressBar extends JPanel {
    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private static final int PROGRESS_DURATION = 2000; // Progress animation duration in milliseconds
    private static final int ROTATION_DURATION = 4000; // Rotation animation duration in milliseconds
    private static final int TIMER_DELAY = 5; // Timer delay in milliseconds

    private int startAngle = 90;
    private int progressAngle = 0;
    private Timer progressTimer;
    private Timer rotationTimer;
    int count = 0;

    public CircularProgressBar(ProgressFrame frame) {
        // Set the preferred size of the panel
        setPreferredSize(new Dimension(200, 200));

        // Create the progress timer
        progressTimer = new Timer(TIMER_DELAY, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                progressAngle++;
                if (progressAngle >= 360) {
                    progressAngle = 0;
                    count++;
                    if(count==5) {
                    	if(frame.id==1) {
                    		new AdminWindow();
               
                    	}else {
                    		new Window(frame.username, frame.password);
                    	}
                    	frame.dispose();
                    }
                }
                repaint();
            }
        });
        progressTimer.setInitialDelay(0);
        progressTimer.setDelay(PROGRESS_DURATION / 360);

        // Create the rotation timer
        rotationTimer = new Timer(TIMER_DELAY, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                startAngle++;
                if (startAngle >= 360) {
                    startAngle = 0;
          
                }
            
                repaint();
            }
        });
        rotationTimer.setInitialDelay(0);
        rotationTimer.setDelay(ROTATION_DURATION / 360);

        // Start the timers
        progressTimer.start();
        rotationTimer.start();
        this.setBackground(Color.DARK_GRAY);
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2d = (Graphics2D) g;
        g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);	
        int x = getWidth() / 2 - 40;
        int y = getHeight() / 2 - 40;
        int width = 80;
        int height = 80;

        // Draw the circular progress bar
        g2d.setColor(Color.GREEN);
        g2d.setStroke(new BasicStroke(10));
        g2d.drawArc(x, y, width, height, startAngle, progressAngle);

        // Draw the background circle
        g2d.setColor(getBackground());
        g2d.fillOval(x, y, width, height);
    }


}
