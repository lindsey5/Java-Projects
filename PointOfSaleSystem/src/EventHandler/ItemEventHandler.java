package EventHandler;

import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import javax.swing.ImageIcon;
import javax.swing.JCheckBox;
import javax.swing.JPasswordField;
import javax.swing.JToggleButton;

public class ItemEventHandler implements ItemListener{
	JToggleButton checkbox;
	JPasswordField passwordField;
	ImageIcon icon1, icon2;
	
	public ItemEventHandler(JCheckBox checkbox,JPasswordField passwordField, ImageIcon icon1, ImageIcon icon2) {
		this.checkbox=checkbox;
		this.passwordField=passwordField;
		this.icon1=icon1;
		this.icon2=icon2;
	}
	

	@Override
	public void itemStateChanged(ItemEvent e) {
		if(checkbox.isSelected()) {
			passwordField.setEchoChar((char) 0);
			checkbox.setIcon(icon2);
		  
	  }else {
		  passwordField.setEchoChar('*');
		  checkbox.setIcon(icon1);
	  }

		
	}

}
