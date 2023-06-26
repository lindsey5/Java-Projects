package ObjectsAndInterfaces;

import java.awt.BasicStroke;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;

import javax.swing.JButton;


public class CategoryButton extends JButton{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	public boolean last = false;
	
	public CategoryButton(String text) {
		super(text);
		setFocusable(false);
	}

	protected void paintComponent(Graphics g) {
		super.paintComponent(g);
		if(!last) {
			Graphics2D g2 = (Graphics2D) g;
			g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
			g2.setStroke(new BasicStroke(0.5f));
			g2.drawLine(30, 0, 30, getHeight());
			g2.drawLine(30, getHeight()/2, 40, getHeight()/2);
		}else {
			Graphics2D g2 = (Graphics2D) g;
			g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
			g2.setStroke(new BasicStroke(0.5f));
			g2.drawLine(30, 0, 30, getHeight()/2);
			g2.drawLine(30, getHeight()/2, 40, getHeight()/2);
		}
		
	}

}
