package StudentInformationSystem;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;

import javax.swing.JTextField;

public class RoundRectTextField extends JTextField{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	@Override
	protected void paintComponent(Graphics g) {
		setBorder(null);
		setOpaque(false);
		Graphics2D g2 = (Graphics2D) g;
	    g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
	    g2.setColor(getBackground());
	    g2.fillRoundRect(0, 0, getWidth(), getHeight(), 20, 20);
	    g2.setColor(Color.GRAY);
	    g2.drawRoundRect(0, 0, getWidth()-1, getHeight()-1, 20, 20);
	    super.paintComponent(g);
		
		
	}
	
	

}
