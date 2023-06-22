package MenuPage;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.geom.RoundRectangle2D;
import java.sql.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPasswordField;
import EventHandler.ItemEventHandler;
import EventHandler.MouseInputs;
import ObjectsAndInterfaces.GradientButton;

public class ChangePassFrame extends JFrame implements ActionListener{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	JLabel icon;
	JLabel title;
	JPasswordField oldPassword;
	JLabel oldPasswordlbl;
	JButton submit;
	JPasswordField newPassword;
	JLabel newPasswordlbl;
	JPasswordField conpass;
	JLabel conpasslbl;
	GradientButton submit1;
    JCheckBox newPassCheckBox = new JCheckBox();
    JCheckBox conPassCheckBox = new JCheckBox();
    JCheckBox oldPassCheckBox = new JCheckBox();
    boolean submitted = false;
    String accountname;
    JButton closeButton;
	
	public ChangePassFrame(String accountname){
		this.accountname = accountname;
		
		closeButton = new JButton("X");
		closeButton.setBounds(420, 35, 45, 40);
		closeButton.setBackground(Color.DARK_GRAY);
		closeButton.setForeground(new Color(0,255,0));
		closeButton.setFont(new Font("Arial",Font.BOLD,20));
		closeButton.setBorder(null);
		closeButton.addActionListener(this);
		closeButton.addMouseListener(new MouseInputs(closeButton,Color.DARK_GRAY,Color.GRAY));
		
		submit1 = new GradientButton(new Color(0,255,127),new Color(0,100,0),new Color(144,238,144));
		submit1.setText("Submit");
		submit1.setBounds(260,370,120,40);
		submit1.addActionListener(this);
		
		conpasslbl = new JLabel("Confirm Password");
		conpasslbl.setBounds(115,270,150,30);
		conpasslbl.setForeground(Color.LIGHT_GRAY);
		
		conpass = new JPasswordField();
		conpass.setBounds(115, 300, 230, 40);
		conpass.setForeground(Color.WHITE);
		conpass.setBackground(Color.DARK_GRAY);
		conpass.setBorder(null);
		conpass.setCaretColor(Color.WHITE);
		conpass.setBorder(BorderFactory.createMatteBorder(0, 0, 1, 0, new Color(0,255,0)));
		
		newPasswordlbl = new JLabel("New Password");
		newPasswordlbl.setBounds(115,180,150,30);
		newPasswordlbl.setForeground(Color.LIGHT_GRAY);
		
		newPassword = new JPasswordField();
		newPassword.setBounds(115, 210, 230, 40);
		newPassword.setForeground(Color.WHITE);
		newPassword.setBackground(Color.DARK_GRAY);
		newPassword.setBorder(null);
		newPassword.setCaretColor(Color.WHITE);
		newPassword.setBorder(BorderFactory.createMatteBorder(0, 0, 1, 0, new Color(0,255,0)));
		
		ImageIcon icon1 = new ImageIcon("res/hide.png");
		Image img1 = icon1.getImage().getScaledInstance(30, 30, java.awt.Image.SCALE_AREA_AVERAGING);
		icon1 = new ImageIcon(img1);
		
		ImageIcon icon2 = new ImageIcon("res/show.png");
		Image img2 = icon2.getImage().getScaledInstance(30, 30, java.awt.Image.SCALE_AREA_AVERAGING);
		icon2 = new ImageIcon(img2);
		
		newPassCheckBox.setBounds(350, 210, 40, 30);
		newPassCheckBox.setBackground(Color.DARK_GRAY);
		newPassCheckBox.setIcon(icon1);
		newPassCheckBox.addItemListener(new ItemEventHandler(newPassCheckBox,newPassword,icon1,icon2));
		
		conPassCheckBox.setBounds(350, 300, 40, 30);
		conPassCheckBox.setBackground(Color.DARK_GRAY);
		conPassCheckBox.setIcon(icon1);
		conPassCheckBox.addItemListener(new ItemEventHandler(conPassCheckBox,conpass,icon1,icon2));	
		
		submit = new GradientButton(new Color(0,255,127),new Color(0,100,0),new Color(144,238,144));
		submit.setText("Submit");
		submit.setBounds(190,320,120,40);
		submit.addActionListener(this);
		
		oldPasswordlbl = new JLabel("Old Password");
		oldPasswordlbl.setBounds(115,220,250,30);
		oldPasswordlbl.setForeground(Color.LIGHT_GRAY);
		
		oldPassword = new JPasswordField();
		oldPassword.setBounds(115, 250, 230, 40);
		oldPassword.setForeground(Color.WHITE);
		oldPassword.setBackground(Color.DARK_GRAY);
		oldPassword.setBorder(null);
		oldPassword.setCaretColor(Color.WHITE);
		oldPassword.setBorder(BorderFactory.createMatteBorder(0, 0, 1, 0, new Color(0,255,0)));
		
		oldPassCheckBox.setBounds(350, 250, 40, 30);
		oldPassCheckBox.setBackground(Color.DARK_GRAY);
		oldPassCheckBox.setIcon(icon1);
		oldPassCheckBox.addItemListener(new ItemEventHandler(oldPassCheckBox,oldPassword,icon1,icon2));	
		
		title = new JLabel("Change Password");
		title.setBounds(135, 130, 230, 50);
		title.setForeground(new Color(0,255,0));
		title.setFont(new Font("Arial",Font.BOLD,25));
		
		
		ImageIcon imgIcon = new ImageIcon("res/forgot-password.png");
		Image img = imgIcon.getImage().getScaledInstance(80, 80, java.awt.Image.SCALE_AREA_AVERAGING);
		imgIcon = new ImageIcon(img);
		
		icon = new JLabel(imgIcon);
		icon.setBounds(205, 50, imgIcon.getIconWidth(), imgIcon.getIconHeight());
		
		
		this.setSize(500,450);
		this.setLocationRelativeTo(null);
		this.setResizable(false);
		this.setLayout(null);
		this.getContentPane().setBackground(Color.DARK_GRAY);
		this.setUndecorated(true);
		this.setShape(new RoundRectangle2D.Double(0,0,getWidth(),getHeight(),50,50));
		this.add(icon);
		this.add(title);
		this.add(oldPassword);
		this.add(oldPasswordlbl);
		this.add(oldPassCheckBox);
		this.add(submit);
		this.add(closeButton);
		this.setVisible(true);
	}
	
	@Override
	public void paint(Graphics g) {
		super.paintComponents(g);
		g.setColor(new Color(0,255,0));
		if(!submitted) {
		g.drawLine(345, 289, 387, 289);
		}else {
		g.drawLine(340, 249, 382,249);
		g.drawLine(340, 339, 382,339);
		}
		
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		try {
		if(e.getSource()==submit) {
			Connection Conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/foodsystemdb","root","");
			PreparedStatement pstmt = Conn.prepareStatement("SELECT * FROM accounttable WHERE accountname=? and password=?");
			pstmt.setString(1, accountname);
			pstmt.setString(2, String.valueOf(oldPassword.getPassword()));
			ResultSet rs = pstmt.executeQuery();
			if(rs.next()) {
				
				if(!rs.getString("password").equals( String.valueOf(oldPassword.getPassword()))) {
					JOptionPane.showMessageDialog(null, "Incorrect Password","",JOptionPane.ERROR_MESSAGE);
				}else {
					this.add(newPassword);
					this.add(newPasswordlbl);
					this.add(conpass);
					this.add(conpasslbl);
					this.add(submit1);
					this.add(newPassCheckBox);
					this.add(conPassCheckBox);
					oldPassword.setVisible(false);
					oldPasswordlbl.setVisible(false);
					oldPassCheckBox.setVisible(false);
					submit.setVisible(false);
					submitted = true;
					this.repaint();
				}
			}else {
				JOptionPane.showMessageDialog(null, "Incorrect Password","",JOptionPane.ERROR_MESSAGE);
			}
		}
				
			}catch(SQLException e1) {
				e1.printStackTrace();
			}catch(NumberFormatException e1) {
				JOptionPane.showMessageDialog(null, "Invalid Pin","",JOptionPane.ERROR_MESSAGE);
			}
		
		 if(e.getSource()==submit1) {
			  String pass = String.valueOf(newPassword.getPassword());
			  String conPass = String.valueOf(conpass.getPassword());
			  boolean confirm = pass.equals(conPass);
			  
			  Pattern uppercasePattern = Pattern.compile("[A-Z]");
		      Pattern lowercasePattern = Pattern.compile("[a-z]");
			  Matcher uppercaseMatcher = uppercasePattern.matcher(pass);
			  Matcher lowercaseMatcher = lowercasePattern.matcher(pass);
			    
			  boolean hasUppercase =uppercaseMatcher.find();
			  boolean hasLowercase =lowercaseMatcher.find();
			  
			  if(pass.length()==0 || conPass.length()==0) {
				  JOptionPane.showMessageDialog(null, "Fill The Blanks","",JOptionPane.ERROR_MESSAGE);
			  }else if(!confirm) {
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
					
					JOptionPane.showMessageDialog(null,"<html>" + "USE ATLEAST ONE SPECIAL CHARACTERS TO YOUR PASSWORD <br> (!,@,#,$,%,^,&,*,(,),-,_,=,+)" + "</html>","",JOptionPane.ERROR_MESSAGE );
					
			  }else if(!hasUppercase||!hasLowercase) {
					 JOptionPane.showMessageDialog(null, "Password must have atleast one Uppercase letter and atleast one Lowercase letter","",JOptionPane.ERROR_MESSAGE);
					 
			  }else if(pass.length()<8 || pass.length()>20){
					JOptionPane.showMessageDialog(null, "The length of Password should be 8-20","",JOptionPane.ERROR_MESSAGE);
					
			  }else {
				  try {
						Connection Conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/foodsystemdb","root","");
						PreparedStatement pstmt = Conn.prepareStatement("UPDATE accounttable SET password=? WHERE accountname=?");
						pstmt.setString(1, pass);
						pstmt.setString(2, accountname);
						
						pstmt.executeUpdate();
						
						JOptionPane.showMessageDialog(null, "Password Successfully Changed");
						
						
				  }catch(SQLException e1) {
					  e1.printStackTrace();
				  }
				  
				  
				  this.dispose();			 
			  }
	
		  }
		 
		 if(e.getSource()==closeButton) {
				this.dispose();
				
			}
		}
	
}
