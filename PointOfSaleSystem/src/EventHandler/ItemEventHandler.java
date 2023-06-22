package EventHandler;

import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import javax.swing.ImageIcon;
import javax.swing.JPasswordField;
import javax.swing.JToggleButton;

public class ItemEventHandler implements ItemListener{
	JToggleButton button;
	JPasswordField passwordField;
	ImageIcon icon1, icon2;
	
	public ItemEventHandler(JToggleButton button,JPasswordField passwordField, ImageIcon icon1, ImageIcon icon2) {
		this.button=button;
		this.passwordField=passwordField;
		this.icon1=icon1;
		this.icon2=icon2;
	}
	

	@Override
	public void itemStateChanged(ItemEvent e) {
		if(button.isSelected()) {
			passwordField.setEchoChar((char) 0);
			button.setIcon(icon2);
		  
	  }else {
		  passwordField.setEchoChar('*');
		  button.setIcon(icon1);
	  }

		
	}

}
