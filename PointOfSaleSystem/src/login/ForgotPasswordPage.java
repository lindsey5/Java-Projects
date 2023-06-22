package login;

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
import javax.swing.JTextField;

import EventHandler.ItemEventHandler;
import EventHandler.MouseInputs;
import ObjectsAndInterfaces.GradientButton;

public class ForgotPasswordPage extends JFrame implements ActionListener{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	JLabel icon;
	JLabel title;
	JTextField accountname;
	JLabel accountnamelbl;
	JButton back;
	GradientButton submit;
    JPasswordField password;
    JLabel passwordlbl;
    JPasswordField conpass;
    JLabel conpasslbl;
    GradientButton submit1;
    String accname = "";
    ImageIcon icon1 = new ImageIcon("res/show.png");
	ImageIcon icon2 = new ImageIcon("res/hide.png");
	JCheckBox checkbox1 = new JCheckBox();
	JCheckBox checkbox2 = new JCheckBox();
	Color color = Color.DARK_GRAY;
	
	public ForgotPasswordPage(){
		
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
		
		passwordlbl = new JLabel("New Password");
		passwordlbl.setBounds(115,180,150,30);
		passwordlbl.setForeground(Color.LIGHT_GRAY);
		
		password = new JPasswordField();
		password.setBounds(115, 210, 230, 40);
		password.setForeground(Color.WHITE);
		password.setBackground(Color.DARK_GRAY);
		password.setBorder(null);
		password.setCaretColor(Color.WHITE);
		password.setBorder(BorderFactory.createMatteBorder(0, 0, 1, 0, new Color(0,255,0)));
		
		ImageIcon icon1 = new ImageIcon("res/hide.png");
		Image img1 = icon1.getImage().getScaledInstance(30, 30, java.awt.Image.SCALE_AREA_AVERAGING);
		icon1 = new ImageIcon(img1);
		
		ImageIcon icon2 = new ImageIcon("res/show.png");
		Image img2 = icon2.getImage().getScaledInstance(30, 30, java.awt.Image.SCALE_AREA_AVERAGING);
		icon2 = new ImageIcon(img2);
		
		checkbox1.setBounds(350, 210, 40, 30);
		checkbox1.setBackground(Color.DARK_GRAY);
		checkbox1.setIcon(icon1);
		checkbox1.addItemListener(new ItemEventHandler(checkbox1,password,icon1,icon2));
		
		checkbox2.setBounds(350, 300, 40, 30);
		checkbox2.setBackground(Color.DARK_GRAY);
		checkbox2.setIcon(icon1);
		checkbox2.addItemListener(new ItemEventHandler(checkbox2,conpass,icon1,icon2));		
		
		
		submit = new GradientButton(new Color(0,255,127),new Color(0,100,0),new Color(144,238,144));
		submit.setText("Submit");
		submit.setBounds(190,320,120,40);
		submit.addActionListener(this);
		
		
		back = new JButton("🡐");
		back.setBackground(Color.DARK_GRAY);
		back.setForeground(new Color(0,255,0));
		back.setFont(new Font("Callibri",Font.BOLD,20));
		back.setBounds(20,30,60,50);
		back.setBorder(null);
		back.addActionListener(this);
		back.addMouseListener(new MouseInputs(back,Color.DARK_GRAY,Color.GRAY));
		
		accountnamelbl = new JLabel("Enter Account Name");
		accountnamelbl.setBounds(115,220,250,30);
		accountnamelbl.setForeground(Color.LIGHT_GRAY);
		
		accountname = new JTextField();
		accountname.setBounds(115, 250, 270, 40);
		accountname.setForeground(Color.WHITE);
		accountname.setBackground(Color.DARK_GRAY);
		accountname.setBorder(null);
		accountname.setCaretColor(Color.WHITE);
		accountname.setBorder(BorderFactory.createMatteBorder(0, 0, 1, 0, new Color(0,255,0)));
		
		ImageIcon imgIcon = new ImageIcon("res/forgot-password.png");
		Image img = imgIcon.getImage().getScaledInstance(80, 80, java.awt.Image.SCALE_AREA_AVERAGING);
		imgIcon = new ImageIcon(img);
		
		icon = new JLabel(imgIcon);
		icon.setBounds(205, 50, imgIcon.getIconWidth(), imgIcon.getIconHeight());
		
		title = new JLabel("Forgot Password");
		title.setBounds(145, 130, 230, 50);
		title.setForeground(new Color(0,255,0));
		title.setFont(new Font("Arial",Font.BOLD,25));
		
		
		this.setSize(500,450);
		this.setLocationRelativeTo(null);
		this.setResizable(false);
		this.setLayout(null);
		this.getContentPane().setBackground(Color.DARK_GRAY);
		this.setUndecorated(true);
		this.setShape(new RoundRectangle2D.Double(0,0,getWidth(),getHeight(),50,50));
		this.add(icon);
		this.add(title);
		this.add(accountname);
		this.add(accountnamelbl);
		this.add(submit);
		this.add(back);
		this.setVisible(true);
	}
	
	public void paint(Graphics g) {
		super.paintComponents(g);
		g.setColor(color);
		g.drawLine(340, 249, 382,249);
		g.drawLine(340, 339, 382,339);
		
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if(e.getSource()==back) {
			this.dispose();
			new loginPage();
		}
		
		if(e.getSource()==submit) {
			try {
				Connection Conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/foodsystemdb","root","");
				PreparedStatement pstmt = Conn.prepareStatement("SELECT * FROM accounttable WHERE accountname=?");
				pstmt.setString(1, accountname.getText());
				ResultSet rs = pstmt.executeQuery();
				
				if(rs.next()) {
					accname = rs.getString("accountname");
					int pin = rs.getInt("pin");
					
					try {
						int p = Integer.parseInt(JOptionPane.showInputDialog(null,"Enter Pin"));
						if(p==pin) {
							this.add(password);
							this.add(passwordlbl);
							this.add(conpass);
							this.add(conpasslbl);
							this.add(submit1);
							this.add(checkbox1);
							this.add(checkbox2);
							accountname.setVisible(false);
							accountnamelbl.setVisible(false);
							submit.setVisible(false);
							color = new Color(0,255,0);
							this.repaint();
							
							
						}else {
							JOptionPane.showMessageDialog(null, "Incorrect Pin","",JOptionPane.ERROR_MESSAGE);
						}
						
					}catch(NumberFormatException e1) {
						JOptionPane.showMessageDialog(null, "Input must be an Integer","",JOptionPane.ERROR_MESSAGE);
					}
							
				}else {
					JOptionPane.showMessageDialog(null, "Account Name does not Exist","",JOptionPane.ERROR_MESSAGE);
				}
				
			}catch(SQLException e1) {
				e1.printStackTrace();
			}
		}
		
	  if(e.getSource()==submit1) {
		  String pass = String.valueOf(password.getPassword());
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
					pstmt.setString(2, accname);
					
					pstmt.executeUpdate();
					
					JOptionPane.showMessageDialog(null, "Password Successfully Changed");
					
					
			  }catch(SQLException e1) {
				  
			  }
			  
			  
			  this.dispose();
			  new loginPage();
			 
		  }
		  
		  
	  }
		
	}

}
