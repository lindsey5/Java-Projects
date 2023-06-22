package ObjectsAndInterfaces;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;

import javax.swing.JPanel;

public class RoundRectPanel extends JPanel{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	int arcWidth,arcHeight;
	
	public RoundRectPanel(int arcWidth,int arcHeight) {
		this.arcWidth=arcWidth;
		this.arcHeight=arcHeight;
	}

	@Override
	protected void paintComponent(Graphics g) {
		setOpaque(false);
		Graphics2D g2 = (Graphics2D) g;
		g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
		g2.setPaint(getBackground());
		g2.fillRoundRect(0, 0, getWidth(), getHeight(), arcWidth, arcHeight);
		g2.setPaint(Color.WHITE);
		g2.drawRoundRect(0, 0, getWidth()-1, getHeight()-1, arcWidth, arcHeight);
		super.paintComponent(g2);
	}

}
