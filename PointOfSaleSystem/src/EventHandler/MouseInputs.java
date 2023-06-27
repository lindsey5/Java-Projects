package EventHandler;

import java.awt.Color;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.JComponent;

public class MouseInputs implements MouseListener{
	
	protected JComponent c;
	protected Color color1;
	protected Color color2;
	
	public MouseInputs(JComponent c, Color color1, Color color2) {
		this.c=c;
		this.color1=color1;
		this.color2=color2;
		
	}


	@Override
	public void mouseReleased(MouseEvent e) {
		c.setBackground(color1);	
	}

	@Override
	public void mouseEntered(MouseEvent e) {
		c.setBackground(color2);
		
	}

	@Override
	public void mouseExited(MouseEvent e) {
		c.setBackground(color1);
		
	}


	@Override
	public void mouseClicked(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}


	@Override
	public void mousePressed(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}
	
}
