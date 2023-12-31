package login;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.geom.RoundRectangle2D;
import java.sql.*;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javax.swing.JPanel;
import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JTextField;

import EventHandler.ItemEventHandler;
import EventHandler.MouseInputs;
import ObjectsAndInterfaces.GradientButton;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JButton;
import javax.swing.JPasswordField;
import javax.swing.JCheckBox;

public class RegisterPage extends JFrame implements ActionListener {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	JPanel panel;
	JTextField accountName = new JTextField(), username = new JTextField();
	JPasswordField password = new JPasswordField(), confirmPassword = new JPasswordField(), pin = new JPasswordField();
	JLabel accountnamelbl = new JLabel("Account Name"), 
		   usernamelbl = new JLabel("Username"), 
		   passwordlbl = new JLabel("Password"), 
		   conpasslbl = new JLabel("Confirm Password"), 
		   pinlbl = new JLabel("Enter Pin");
	GradientButton submit = new GradientButton(new Color(0,255,127),new Color(0,100,0),new Color(144,238,144));
	JLabel title = new JLabel("Sign Up");
	JCheckBox passCheckbox = new JCheckBox(), conPassCheckbox = new JCheckBox(), pinCheckbox = new JCheckBox();
	JButton exit, back;
	
	public RegisterPage(){
		
		title.setBounds(190, 15, 200, 80);
		title.setFont(new Font("Arial",Font.BOLD,30));
		title.setForeground(new Color(0,255,0));
		
		back = new JButton("🡐");
		back.setBackground(Color.DARK_GRAY);
		back.setForeground(new Color(0,255,0));
		back.setFont(new Font("Callibri",Font.BOLD,20));
		back.setBounds(20,30,60,50);
		back.setBorder(null);
		back.addActionListener(this);
		back.addMouseListener(new MouseInputs(back,Color.DARK_GRAY,Color.GRAY));

		
		submit.setText("Submit");
		submit.setBounds(170,600,150,50);
		submit.addActionListener(this);
		
		pinlbl.setBounds(120,490,150,30);
		pinlbl.setForeground(Color.LIGHT_GRAY);
		
		pin.setBounds(120,510,210,50);
		pin.setBorder(BorderFactory.createMatteBorder(0,  0, 1 ,0 ,new Color(0,255,0)));
		pin.setBackground(Color.DARK_GRAY);
		pin.setForeground(Color.WHITE);
		pin.setEchoChar('*');
		pin.setCaretColor(Color.WHITE);
		
		conpasslbl.setBounds(120,400,200,30);
		conpasslbl.setForeground(Color.LIGHT_GRAY);
		
		confirmPassword.setBounds(120,420,210,50);
		confirmPassword.setBorder(BorderFactory.createMatteBorder(0,  0, 1 ,0 ,new Color(0,255,0)));;
		confirmPassword.setBackground(Color.DARK_GRAY);
		confirmPassword.setForeground(Color.WHITE);
		confirmPassword.setEchoChar('*');
		confirmPassword.setCaretColor(Color.WHITE);
		
		passwordlbl.setBounds(120,310,100,30);
		passwordlbl.setForeground(Color.LIGHT_GRAY);
		
		password.setBounds(120,330,210,50);
		password.setBorder(BorderFactory.createMatteBorder(0,  0, 1 ,0 ,new Color(0,255,0)));
		password.setBackground(Color.DARK_GRAY);
		password.setForeground(Color.WHITE);
		password.setEchoChar('*');
		password.setCaretColor(Color.WHITE);
		
		usernamelbl.setBounds(120,220,100,30);
		usernamelbl.setForeground(Color.LIGHT_GRAY);
		
		username.setBounds(120,240,250,50);
		username.setBorder(BorderFactory.createMatteBorder(0,  0, 1 ,0 ,new Color(0,255,0)));
		username.setBackground(Color.DARK_GRAY);
		username.setForeground(Color.WHITE);
		username.setCaretColor(Color.WHITE);
		
		
		accountnamelbl.setBounds(120,130,100,30);
		accountnamelbl.setForeground(Color.LIGHT_GRAY);
		
		accountName.setBounds(120,150,250,50);
		accountName.setBorder(BorderFactory.createMatteBorder(0,  0, 1 ,0 ,new Color(0,255,0)));
		accountName.setBackground(Color.DARK_GRAY);
		accountName.setForeground(Color.WHITE);
		accountName.setCaretColor(Color.WHITE);
		
		ImageIcon icon1 = new ImageIcon("res/hide.png");
		Image img1 = icon1.getImage().getScaledInstance(30, 30, java.awt.Image.SCALE_AREA_AVERAGING);
		icon1 = new ImageIcon(img1);
		
		ImageIcon icon2 = new ImageIcon("res/show.png");
		Image img2 = icon2.getImage().getScaledInstance(30, 30, java.awt.Image.SCALE_AREA_AVERAGING);
		icon2 = new ImageIcon(img2);
		
		
		passCheckbox.setBounds(330, 340, 40, 30);
		passCheckbox.setBackground(Color.DARK_GRAY);
		passCheckbox.setIcon(icon1);
		passCheckbox.addItemListener(new ItemEventHandler(passCheckbox,password,icon1,icon2));
		
		conPassCheckbox.setBounds(330, 430, 40, 30);
		conPassCheckbox.setBackground(Color.DARK_GRAY);
		conPassCheckbox.setIcon(icon1);
		conPassCheckbox.addItemListener(new ItemEventHandler(conPassCheckbox,confirmPassword,icon1,icon2));		
		
		pinCheckbox.setBounds(330, 520, 40, 30);
		pinCheckbox.setBackground(Color.DARK_GRAY);
		pinCheckbox.setIcon(icon1);
		pinCheckbox.addItemListener(new ItemEventHandler(pinCheckbox,pin,icon1,icon2));
		
		
		exit = new JButton("X");
		exit.setBounds(420, 40, 45, 40);
		exit.setBackground(Color.DARK_GRAY);
		exit.setForeground(new Color(0,255,0));
		exit.setFont(new Font("Arial",Font.BOLD,20));
		exit.setBorder(null);
		exit.addActionListener(this);
		exit.addMouseListener(new MouseInputs(exit,Color.DARK_GRAY,Color.GRAY));
		
		
		this.setSize(500,750);
		this.setUndecorated(true);
		this.setShape(new RoundRectangle2D.Double(0,0,getWidth(),getHeight(),40,40));
		this.setLocationRelativeTo(null);
		this.getContentPane().setBackground(Color.DARK_GRAY);
		this.setLayout(null);
		this.add(title);
		this.add(accountName);
		this.add(accountnamelbl);
		this.add(username);
		this.add(usernamelbl);
		this.add(password);
		this.add(passwordlbl);
		this.add(confirmPassword);
		this.add(conpasslbl);
		this.add(passCheckbox);
		this.add(conPassCheckbox);
		this.add(pin);
		this.add(pinlbl);
		this.add(pinCheckbox);
		this.add(submit);
		this.add(back);
		this.add(exit);
		this.setVisible(true);
	}
	
	@Override
	public void paint(Graphics g) {
		super.paintComponents(g);
		g.setColor(new Color(0,255,0));
		g.drawLine(330, 379, 370, 379);
		g.drawLine(330, 469, 370, 469);
		g.drawLine(330, 559, 370, 559);
	}
	
	boolean confirmPass(String password, String confirmPass) {
		
		if(password.equals(confirmPass)) return true;
		
		else return false;
	}
	
	boolean usernameExist() {
		//Checks if Username is already exist
		try {
			Connection Conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/foodsystemdb","root","");
			PreparedStatement stmt = Conn.prepareStatement("SELECT * FROM accounttable WHERE username=? ");
			stmt.setString(1, username.getText());
				        
			ResultSet rs = stmt.executeQuery();
				
			if(rs.next()) {
				return true;
			}
				
		}catch(SQLException e1) {
			e1.printStackTrace();
		}
		
		
		return false;
	}
	
	boolean accNameExist() {
		//Checks if Accountname is already exist
		try {
			Connection Conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/foodsystemdb","root","");
			PreparedStatement stmt = Conn.prepareStatement("SELECT * FROM accounttable WHERE accountname=? ");
			stmt.setString(1, accountName.getText());
						        
			ResultSet rs = stmt.executeQuery();
						        
			if(rs.next()) {
				return true;
			}        	
						        	
		}catch(SQLException e1) {	    	 
			e1.printStackTrace();
		}
		
		return false;
	}
	
	boolean isPinValid() {
		 try {
			 //Checks if the text in pin textfield can be an integer
			 Integer.parseInt(String.valueOf(pin.getPassword()));
			 return true;
		        
		//Catch the NumberFormatException
		 }catch(NumberFormatException e1) {
			 return false;
		 }
		 
	}
	
	boolean hasUppercase(String pass) {
		Pattern uppercasePattern = Pattern.compile("[A-Z]");
		Matcher uppercaseMatcher = uppercasePattern.matcher(pass);
		
		if(uppercaseMatcher.find()) {
			return true;
		}else {
			return false;
		}
	}
	
	boolean hasLowercase(String pass) {
		Pattern lowercasePattern = Pattern.compile("[a-z]");
	    Matcher lowercaseMatcher = lowercasePattern.matcher(pass);
	    
	    if(lowercaseMatcher.find()) {
	    	return true;
	    }else {
	    	return false;
	    }
	}
	
	
	void addAccountToDB() {
		String pass = String.valueOf(password.getPassword());
		String conPass = String.valueOf(confirmPassword.getPassword());
		
		if(accountName.getText().isEmpty() 
				|| username.getText().isEmpty() 
				|| String.valueOf(password.getPassword()).isEmpty() 
				|| String.valueOf(confirmPassword.getPassword()).isEmpty() 
				|| String.valueOf(pin.getPassword()).isEmpty() ) {
			JOptionPane.showMessageDialog(null, "Fill the blanks","",JOptionPane.ERROR_MESSAGE);
			
		}else if(!confirmPass(pass, conPass)) {
		    JOptionPane.showMessageDialog(null, "Password does not match","",JOptionPane.ERROR_MESSAGE);
		    
		}else if (!pass.contains("!") 
				 && !pass.contains("@") 
				 && !pass.contains("#")
				 && !pass.contains("$")
				 && !pass.contains("%")
				 && !pass.contains("^")
				 && !pass.contains("&") 
				 && !pass.contains("*")
				 && !pass.contains("(")
				 && !pass.contains(")")
				 && !pass.contains("-")
				 && !pass.contains("_")
				 && !pass.contains("=")
				 && !pass.contains("+")){
			
			JOptionPane.showMessageDialog(null,"Use atleast one special characters in your password (!,@,#,$,%,^,&,*,(,),-,_,=,+)","",JOptionPane.ERROR_MESSAGE );
			
		}else if(!hasUppercase(pass)||!hasLowercase(pass)) {
			 JOptionPane.showMessageDialog(null, "Password must have atleast one Uppercase letter and atleast one Lowercase letter","",JOptionPane.ERROR_MESSAGE);
		}else if(pass.length()<8 || pass.length()>20){
			JOptionPane.showMessageDialog(null, "The length of Password should be 8-20","",JOptionPane.ERROR_MESSAGE);
			
		}else if(String.valueOf(pin.getPassword()).length()!=4) {
			JOptionPane.showMessageDialog(null, "Pin should be 4 digits integer","",JOptionPane.ERROR_MESSAGE);
			
		}else if(accNameExist()) {
			JOptionPane.showMessageDialog(null, "Account Name Already Exist","",JOptionPane.ERROR_MESSAGE);
		}else if(usernameExist()) {
			JOptionPane.showMessageDialog(null, "Username Already Exist","",JOptionPane.ERROR_MESSAGE);
			
		}else if(!isPinValid()) {
			JOptionPane.showMessageDialog(null, "Pin should be 4 digits integer","",JOptionPane.ERROR_MESSAGE);
			
		}else{
			try {
				Connection Conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/foodsystemdb","root","");
				PreparedStatement stmt = Conn.prepareStatement("INSERT INTO accounttable (accountName,username,password,pin,datecreated,timecreated) VALUES (?,?,?,?,?,?)");
				stmt.setString(1, accountName.getText());
				stmt.setString(2, username.getText());
				stmt.setString(3, pass);
				stmt.setString(4, String.valueOf(pin.getPassword()));
				stmt.setDate(5, java.sql.Date.valueOf(LocalDate.now())); 
				stmt.setTime(6, java.sql.Time.valueOf(LocalTime.now()));
				stmt.executeUpdate();
				JOptionPane.showMessageDialog(null, "Account Successfully Created");
				reset();      
					        
			}catch(SQLException e1) {
				e1.printStackTrace();
		    }   
		    	this.dispose();
		    	new LoginPage();    
		   	}
	}
	
	
	void reset() {
		accountName.setText("Account Name");
		accountName.setForeground(Color.LIGHT_GRAY);
		username.setText("Username");
		username.setForeground(Color.LIGHT_GRAY);
		password.setText("Password");
		password.setForeground(Color.LIGHT_GRAY);
		confirmPassword.setText("Confirm Password");
		confirmPassword.setForeground(Color.LIGHT_GRAY);
		pin.setText("Enter Pin");
		pin.setForeground(Color.LIGHT_GRAY);
		
	}
	

	@Override
	public void actionPerformed(ActionEvent e) {
		if(e.getSource()==submit) {
			addAccountToDB(); 
		}else if(e.getSource()==exit) {
			dispose();
		}else if(e.getSource()==back) {
			dispose();
			new LoginPage();
			
		}
		
	}

	
}
