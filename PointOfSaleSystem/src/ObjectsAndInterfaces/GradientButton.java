
package ObjectsAndInterfaces;

import java.awt.Color;
import java.awt.GradientPaint;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.JButton;

public class GradientButton extends JButton implements MouseListener {
	    /**
		 * 
		 */
		private static final long serialVersionUID = 1L;
		Color color1;
		Color color2;
		Color whenClicked;
		GradientPaint gradient;
	    boolean mousePressed = false;
	    
		public GradientButton(Color color1, Color color2, Color whenClicked) {
	       this.color1=color1;
	       this.color2=color2;
	       this.whenClicked=whenClicked;
	       setContentAreaFilled(false);
	       setForeground(Color.WHITE);
	       setBorder(null);
	       addMouseListener(this);
	    }

	    @Override
	    protected void paintComponent(Graphics g) {
	        Graphics2D g2 = (Graphics2D) g;
	        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
	        
	        if(gradient == null) {
	        	gradient = new GradientPaint(0, 0, color1,getWidth(), getHeight(), color2);
	        }
	        
	        if(mousePressed) {
	        	g2.setPaint(whenClicked);
	        }else {
	            g2.setPaint(gradient);
	        }
	        
	        g2.fillRoundRect(0, 0, getWidth(), getHeight(), 40, 40);
	        super.paintComponent(g);
	    }

	    @Override
	    public void mouseClicked(MouseEvent e) {}

	    @Override
	    public void mouseEntered(MouseEvent e) {
	           gradient = new GradientPaint(0, 0, color2,getWidth(), getHeight(), color1);
	           repaint();
	    }

	    @Override
	    public void mouseExited(MouseEvent e) {
	       gradient = new GradientPaint(0, 0, color1, getWidth(), getHeight(), color2);
	       repaint();
	       
	    }

	    @Override
	    public void mousePressed(MouseEvent e) {
	       mousePressed = true;
	       repaint();
	       
	    }

	    @Override
	    public void mouseReleased(MouseEvent e) {
	    	mousePressed = false;
	    	repaint();
	    }


	}