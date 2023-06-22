package login;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.geom.RoundRectangle2D;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
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

public class loginPage extends JFrame implements ActionListener {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	JButton closeButton = new JButton("X");
	JButton back = new JButton("🡐");
	JLabel label = new JLabel();
	JTextField username = new JTextField();
	JPasswordField password = new JPasswordField();
	JLabel usernamelbl = new JLabel("Username");
	JLabel passwordlbl = new JLabel("Password");
	JLabel label1 = new JLabel();
	JLabel label2 = new JLabel();
	GradientButton login = new GradientButton(new Color(0,255,127),new Color(0,100,0),new Color(144,238,144));
	JCheckBox showPass = new JCheckBox();
	JButton register = new JButton("<html><u>Register</u></html>");
	JButton forgot = new JButton("<html><u>Forgot Password?</u></html>");
	Thread incorrectpassThread;
	Thread incorrectusernameThread;
	
	public loginPage(){
		
		ImageIcon icon = new ImageIcon("res/profileIcon.png");
		Image img = icon.getImage().getScaledInstance(150, 150, java.awt.Image.SCALE_AREA_AVERAGING);
		icon = new ImageIcon(img);
		label.setIcon(icon);
		label.setBounds(180,150,icon.getIconWidth(),icon.getIconHeight());
		
		
		ImageIcon icon1 = new ImageIcon("res/hide.png");
		Image img1 = icon1.getImage().getScaledInstance(30, 30, java.awt.Image.SCALE_AREA_AVERAGING);
		icon1 = new ImageIcon(img1);
		
		ImageIcon icon2 = new ImageIcon("res/show.png");
		Image img2 = icon2.getImage().getScaledInstance(30, 30, java.awt.Image.SCALE_AREA_AVERAGING);
		icon2 = new ImageIcon(img2);
		
	    showPass.setIcon(icon1);
		showPass.setBounds(350,460,40,38);
		showPass.setBackground(Color.DARK_GRAY);
		showPass.addItemListener(new ItemEventHandler(showPass,password,icon1,icon2));
		
		
		label2.setBounds(270, 500, 150, 30);
		label2.setForeground(new Color(144,238,144));
		label2.setVisible(true);
		
		label1.setBounds(270, 400, 150, 30);
		label1.setForeground(new Color(144,238,144));
		label1.setVisible(true);
		
		forgot.setBounds(100,640,120,30);
		forgot.setBackground(Color.DARK_GRAY);
		forgot.setForeground(new Color(0,255,0));
		forgot.setBorder(null);
		forgot.setFont(new Font("Arial",Font.PLAIN,13));
		forgot.addActionListener(this);
		forgot.addMouseListener(new MouseInputs(forgot,Color.DARK_GRAY,Color.GRAY));
		
		
		register.setBounds(300,640,100,30);
		register.setBackground(Color.DARK_GRAY);
		register.setForeground(new Color(0,255,0));
		register.setBorder(null);
		register.setFont(new Font("Arial",Font.PLAIN,15));
		register.addActionListener(this);
		register.addMouseListener(new MouseInputs(register,Color.DARK_GRAY,Color.GRAY));
		
		login.setText("Login");
		login.setBounds(180, 550, 150, 50);
		login.setForeground(Color.WHITE);
		login.addActionListener(this);
		
		passwordlbl.setBounds(120,430,100,30);
		passwordlbl.setForeground(Color.LIGHT_GRAY);
		
		
		usernamelbl.setBounds(120,330,100,30);
		usernamelbl.setForeground(Color.LIGHT_GRAY);
		
		
	    password.setBounds(120, 460, 230, 40);
		password.setForeground(Color.WHITE);
		password.setEchoChar('*');
		password.setBackground(Color.DARK_GRAY);
		password.setBorder(null);
		password.setCaretColor(Color.WHITE);
		password.setBorder(BorderFactory.createMatteBorder(0, 0, 1, 0, new Color(0,255,0)));
		//password.setHorizontalAlignment(SwingConstants.CENTER);
		
		
		username.setBounds(120, 360, 270, 40);
		username.setForeground(Color.WHITE);
		username.setBackground(Color.DARK_GRAY);
		username.setBorder(null);
		username.setCaretColor(Color.WHITE);
		username.setBorder(BorderFactory.createMatteBorder(0, 0, 1, 0, new Color(0,255,0)));
		//username.setHorizontalAlignment(SwingConstants.CENTER);
		
		
		closeButton.setBounds(420, 35, 45, 40);
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
		
		
		this.setSize(500,750);
		this.setLocationRelativeTo(null);
		this.setResizable(false);
		this.setLayout(null);
		this.getContentPane().setBackground(Color.DARK_GRAY);
		this.setUndecorated(true);
		this.setShape(new RoundRectangle2D.Double(0,0,getWidth(),getHeight(),50,50));
		this.add(label);
		this.add(closeButton);
		this.add(back);
		this.add(showPass);
		this.add(username);
		this.add(password);
		this.add(usernamelbl);
		this.add(passwordlbl);
		this.add(login);
		this.add(register);
		this.add(label1);
		this.add(label2);
		this.add(forgot);
		this.setVisible(true);
	}
	
	@Override
	public void paint(Graphics g) {
		super.paintComponents(g);
		g.setColor(new Color(0,255,0));
		g.drawLine(340, 499, 390, 499);
	}
	
	private void setThreads() {
		incorrectusernameThread = new Thread(new Runnable() {
			@Override
			public void run() {
				try {
					 label1.setText("Incorrect Username");
					 Thread.sleep(2000);
					 label1.setForeground(new Color(0,255,0,100));
					 Thread.sleep(1000);
					 label1.setText(" ncorrect Usernam ");
					 Thread.sleep(100);
					 label1.setText("  correct Userna  ");
					 Thread.sleep(100);
					 label1.setText("    rrect User    ");
					 Thread.sleep(100);
					 label1.setText("       ct Us      ");
					 Thread.sleep(100);
					 
				} catch (InterruptedException e1) {
					
					e1.printStackTrace();
				}
				
				label1.setText("");
				label1.setForeground(new Color(144,238,144));
				

			}
			
		});
		
		incorrectpassThread = new Thread(new Runnable() {

			@Override
			public void run() {
				try {
					 label2.setText("Incorrect Password");
					 Thread.sleep(2000);
					 label2.setForeground(new Color(0,255,0,80));
					 Thread.sleep(1000);
					 label2.setText(" ncorrect Passwor ");
					 Thread.sleep(100);
					 label2.setText("  correct Passwo  ");
					 Thread.sleep(100);
					 label2.setText("    rrect Pass    ");
					 Thread.sleep(100);
					 label2.setText("       ct Pa      ");
					 Thread.sleep(100);
					
				} catch (InterruptedException e1) {
					
					e1.printStackTrace();
				}
				
				label2.setText("");
				label2.setForeground(new Color(144,238,144));

			}
			
		});
	}
	
	
	@Override
	public void actionPerformed(ActionEvent e) {
		if(e.getSource()==closeButton) {
			this.dispose();
			
		}else if(e.getSource()==back) {
			new FirstPage();
			this.dispose();
		}else if(e.getSource()==login) {
			setThreads();
			
			try {
				Connection Conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/foodsystemdb","root","");
				PreparedStatement pstmt = Conn.prepareStatement("SELECT * FROM accounttable WHERE username=? and password=?");
				pstmt.setString(1,username.getText());
				pstmt.setString(2, String.valueOf(password.getPassword()));
				ResultSet rs = pstmt.executeQuery();
				
				if(rs.next()) {
					String user = rs.getString("username");
					String pass = rs.getString("Password");
					if(user.equals(username.getText()) && pass.equals(String.valueOf(password.getPassword()))) {
					this.dispose();
					new ProgressFrame(user, pass);
					}else {
						incorrectusernameThread.start();
						incorrectpassThread.start();
					}
				}else {
					incorrectusernameThread.start();
					incorrectpassThread.start();
				}
			 }catch(SQLException e2) {
				   e2.printStackTrace();	
				}
		
		
			
		}else if(e.getSource()==register) {
			
			try {
			int input = Integer.parseInt(JOptionPane.showInputDialog(null,"Enter Pin"));
			
			if(input==143) {
			
			this.dispose();
			new registerPage();
			
			}else {
				JOptionPane.showMessageDialog(null, "Incorrect Pin","",JOptionPane.ERROR_MESSAGE);
			}
			}catch(NumberFormatException e1) {
				JOptionPane.showMessageDialog(null, "Incorrect Pin","",JOptionPane.ERROR_MESSAGE);
			}
		}else if(e.getSource()==forgot) {
			
			this.dispose();
			new ForgotPasswordPage();
		}
	}
	
}