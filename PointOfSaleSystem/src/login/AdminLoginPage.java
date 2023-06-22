package login;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.geom.RoundRectangle2D;
import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import EventHandler.ItemEventHandler;
import EventHandler.MouseInputs;
import ObjectsAndInterfaces.GradientButton;

public class AdminLoginPage extends JFrame implements ActionListener {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	JLabel icon;
	JLabel title;
	JTextField username;
	JPasswordField password;
	JLabel usernamelbl;
	JLabel passwordlbl;
	GradientButton login;
	JCheckBox showPass = new JCheckBox();
	JButton closeButton = new JButton("X");
	JButton back = new JButton("🡐"); 

	public AdminLoginPage(){
		
		closeButton.setBounds(420, 40, 45, 40);
		closeButton.setBackground(Color.DARK_GRAY);
		closeButton.setForeground(new Color(0,255,0));
		closeButton.setFont(new Font("Arial",Font.BOLD,20));
		closeButton.setBorder(null);
		closeButton.addActionListener(this);
		closeButton.addMouseListener(new MouseInputs(closeButton,Color.DARK_GRAY,Color.GRAY));

		back.setBackground(Color.DARK_GRAY);
		back.setForeground(new Color(0,255,0));
		back.setFont(new Font("Callibri",Font.BOLD,20));
		back.setBounds(50,30,60,50);
		back.setBorder(null);
		back.addActionListener(this);
		back.addMouseListener(new MouseInputs(back,Color.DARK_GRAY,Color.GRAY));
		
		
		login = new GradientButton(new Color(0,255,127),new Color(0,100,0),new Color(144,238,144));
		login.setText("Login");
		login.setBounds(280,420,120,40);
		login.addActionListener(this);
		
		
		usernamelbl = new JLabel("Usename");
		usernamelbl.setBounds(100,190,150,30);
		usernamelbl.setForeground(Color.LIGHT_GRAY);
		
		passwordlbl = new JLabel("Password");
		passwordlbl.setBounds(100,300,150,30);
		passwordlbl.setForeground(Color.LIGHT_GRAY);
		
		username = new JTextField();
		username.setBorder(BorderFactory.createMatteBorder(0, 0, 1, 0, new Color(0,255,0)));
		username.setBounds(100,220,300,50);
		username.setBackground(Color.DARK_GRAY);
		username.setForeground(Color.WHITE);
		username.setFont(new Font("Arial",Font.PLAIN,15));
		username.setCaretColor(Color.WHITE);
		
		password = new JPasswordField();
		password.setBorder(BorderFactory.createMatteBorder(0, 0, 1, 0, new Color(0,255,0)));
		password.setBounds(100,330,260,50);
		password.setBackground(Color.DARK_GRAY);
		password.setForeground(Color.WHITE);
		password.setFont(new Font("Arial",Font.PLAIN,15));
		password.setCaretColor(Color.WHITE);
		
		ImageIcon icon1 = new ImageIcon("res/hide.png");
		Image img1 = icon1.getImage().getScaledInstance(30, 30, java.awt.Image.SCALE_AREA_AVERAGING);
		icon1 = new ImageIcon(img1);
		
		ImageIcon icon2 = new ImageIcon("res/show.png");
		Image img2 = icon2.getImage().getScaledInstance(30, 30, java.awt.Image.SCALE_AREA_AVERAGING);
		icon2 = new ImageIcon(img2);
		
	    showPass.setIcon(icon1);
		showPass.setBounds(360,330,40,38);
		showPass.setBackground(Color.DARK_GRAY);
		showPass.addItemListener(new ItemEventHandler(showPass,password,icon1,icon2));
		
		
		title = new JLabel("Admin Panel");
		title.setFont(new Font("Arial",Font.BOLD,30));
		title.setForeground(new Color(0,255,0));
		title.setBounds(155,120,200,50);
		
		icon = new JLabel("🔑");
		icon.setFont(new Font("Callibri",Font.BOLD,60));
		icon.setForeground(new Color(0,255,0));
		icon.setBounds(215,30,100,80);
		
		this.setUndecorated(true);
		this.setSize(500,550);
		this.setLocationRelativeTo(null);
		this.setShape(new RoundRectangle2D.Double(0,0,getWidth(),getHeight(),50,50));
		this.setLayout(null);
		this.getContentPane().setBackground(Color.DARK_GRAY);
		this.add(icon);
		this.add(title);
		this.add(username);
		this.add(password);
		this.add(showPass);
		this.add(usernamelbl);
		this.add(passwordlbl);
		this.add(login);
		this.add(back);
		this.add(closeButton);
		this.setVisible(true);
	}
	
	@Override
	public void paint(Graphics g) {
		super.paintComponents(g);
		g.setColor(new Color(0,255,0));
		g.drawLine(360, 379, 400, 379);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		
		if(e.getSource()==login) {
			String pass = String.valueOf(password.getPassword());
			if(username.getText().equals("Admin") && pass.equals("lindsey")) {
			this.dispose();
			new ProgressFrame();
			}else {
				JOptionPane.showMessageDialog(null, "Login Denied","",JOptionPane.ERROR_MESSAGE);
			}
		}else if(e.getSource()==closeButton) {
			this.dispose();
			
		}else if(e.getSource()==back) {
			new FirstPage();
			this.dispose();
		}
		
	}

}
