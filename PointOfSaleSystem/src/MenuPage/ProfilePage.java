package MenuPage;
import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.RenderingHints;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.geom.Ellipse2D;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.sql.*;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.filechooser.FileNameExtensionFilter;
import EventHandler.MouseInputs;
import ObjectsAndInterfaces.GradientButton;
import ObjectsAndInterfaces.RoundRectPanel;

public class ProfilePage extends JPanel implements ActionListener {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	RoundRectPanel panel;
	JLabel accountname, profile, profilelbl, usernamelbl;
	JButton selectfile;
	JTextField usernametxt;
	JButton changePass;
	JButton changePin;
	GradientButton save;
	
	String username,password;
	
	byte[] imgData;
	
	ProfilePage(String username, String password) {
		this.username = username;
		this.password = password;
		
		changePin = new JButton("<html><u>Change Pin</u></html>");
		changePin.setBounds(530, 400, 150, 30);
		changePin.setForeground(Color.WHITE);
		changePin.setBorder(null);
		changePin.setBackground(new Color(54,50,50));
		changePin.addActionListener(this);
		changePin.addMouseListener(new MouseInputs(changePin,new Color(54,50,50),Color.GRAY));
		
		changePass = new JButton("<html><u>Change Password</u></html>");
		changePass.setBounds(360, 400, 150, 30);
		changePass.setForeground(Color.WHITE);
		changePass.setBorder(null);
		changePass.setBackground(new Color(54,50,50));
		changePass.addActionListener(this);
		changePass.addMouseListener(new MouseInputs(changePass,new Color(54,50,50),Color.GRAY));
		
		save = new GradientButton(new Color(0,255,127),new Color(0,100,0),new Color(144,238,144));
		save.setText("Save");
		save.addActionListener(this);
		save.setBounds(460,320,120,40);
		
		
		usernametxt = new JTextField(username) {
			
			/**
			 * 
			 */
			private static final long serialVersionUID = 1L;

			@Override
			protected void paintComponent(Graphics g) {
				setBorder(null);
				setOpaque(false);
				setHorizontalAlignment(SwingConstants.CENTER);
				Graphics2D g2 = (Graphics2D) g;
				g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
				g2.setPaint(getBackground());
				g2.fillRoundRect(0, 0, getWidth(), getHeight(), 10, 10);
				g2.setColor(Color.GRAY);
				g2.setStroke(new BasicStroke(2));
				g2.drawRoundRect(0, 0, getWidth()-1, getHeight()-1, 10, 10);
				super.paintComponent(g);
			}
		};
		usernametxt.setBounds(400,200,240,40);
		usernametxt.setBackground(new Color(54,50,50));
		usernametxt.setFont(usernametxt.getFont().deriveFont(Font.BOLD,12));
		usernametxt.setForeground(Color.WHITE);
		usernametxt.setCaretColor(Color.WHITE);
		
		usernamelbl = new JLabel("Username");
		usernamelbl.setForeground(Color.WHITE);
		usernamelbl.setBounds(400,160,150,30);
		
		
		profilelbl = new JLabel("Profile Setting");
		profilelbl.setForeground(Color.WHITE);
		profilelbl.setBounds(400,50,200,30);
		profilelbl.setFont(new Font("Arial",Font.BOLD,25));
		
		selectfile = new JButton("Select Image");
		selectfile.setBounds(100, 230, 150, 30);
		selectfile.setBackground(new Color(32,32,32));
		selectfile.setForeground(Color.WHITE);
		selectfile.addActionListener(this);
		
        accountname = new JLabel();
		accountname.setBounds(0, 280, 350, 50);
		accountname.setFont(new Font("Arial",Font.BOLD,30));
		accountname.setForeground(Color.WHITE);
		accountname.setHorizontalAlignment(SwingConstants.CENTER);
		
		profile = new JLabel() {
			/**
			 * 
			 */
			private static final long serialVersionUID = 1L;

			@Override
			protected void paintComponent(Graphics g) {
				Graphics2D g2 = (Graphics2D) g;
				g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
				g2.setClip(new Ellipse2D.Double(0,0,getWidth(),getHeight()));
				super.paintComponent(g2);
			}
		};
		profile.setBounds(100, 60, 150, 150);
		profile.setBackground(Color.DARK_GRAY);
		profile.setOpaque(true);
		
		panel = new RoundRectPanel(40,40) {
			/**
			 * 
			 */
			private static final long serialVersionUID = 1L;
			
			@Override
			public void paint(Graphics g) {
				super.paint(g);
				Graphics2D g2 = (Graphics2D) g;
				g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
				g2.setPaint(Color.WHITE);
				g2.drawLine(350, 0, 350, getHeight());
			}
			
		};
		panel.setPreferredSize(new Dimension(700, 500));
		panel.setBackground(new Color(54,50,50));
		panel.setLayout(null);
		panel.add(profile);
		panel.add(selectfile);
		panel.add(accountname);
		panel.add(profilelbl);
		panel.add(usernamelbl);
		panel.add(usernametxt);
		panel.add(save);
		panel.add(changePass);
		panel.add(changePin);

		
		this.setLayout(new FlowLayout(FlowLayout.CENTER,0, 150));
	    this.setBackground(Color.DARK_GRAY);
		this.add(panel);
		
		set();
		    
		}
	
	
	private void set() {
		
		//Retrieve accountname from MySQL database then Set accountname to accountname label
		try {
			Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/foodsystemdb", "root", "");
			PreparedStatement stmt = conn.prepareStatement("SELECT accountName FROM accounttable WHERE username=? and password=?");
			stmt.setString(1, username);
			stmt.setString(2, password);
			ResultSet rs = stmt.executeQuery(); 

			if (rs.next()) {
				accountname.setText(rs.getString("accountName"));
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}
					
		//Retrieve picture from MySQL database then set to profile label
		try {
			Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/foodsystemdb","root","");
			PreparedStatement stmt1 = conn.prepareStatement("SELECT * FROM accounttable WHERE accountname=?");
			stmt1.setString(1, accountname.getText());
			ResultSet rs = stmt1.executeQuery();
					 
			if(rs.next()) {	 
				byte[] imageData = rs.getBytes("profile");	   
				if(imageData!=null) {
					ImageIcon icon = new ImageIcon(imageData);
					Image img = icon.getImage().getScaledInstance(150, 150, java.awt.Image.SCALE_AREA_AVERAGING);
					icon = new ImageIcon(img);
					profile.setIcon(icon);
					imgData = imageData;
				}	 
			}
			
		}catch(SQLException e) {
			e.printStackTrace();
		}
	}
	
	private void update() {
		
		try {
			Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/foodsystemdb","root","");

			PreparedStatement stmt1 = conn.prepareStatement("Update accounttable SET profile=?, username=? WHERE username=? and password=?");
			stmt1.setBytes(1, imgData);
			stmt1.setString(2, usernametxt.getText());
			stmt1.setString(3, username);
			stmt1.setString(4, password);
			stmt1.executeUpdate();
	 
			username = usernametxt.getText();
		
			JOptionPane.showMessageDialog(null, "Update Successfully");
		}catch(SQLException e) {
			JOptionPane.showMessageDialog(null, "Username already taken","Invalid",JOptionPane.ERROR_MESSAGE);
		}
		
	}
		

		@Override
		public void actionPerformed(ActionEvent e) {
			if(e.getSource()==selectfile) {
				JFileChooser filechooser = new JFileChooser();
				 filechooser.setFileFilter(new FileNameExtensionFilter("Image files","jpg","jpeg","png","gif"));
				 int result = filechooser.showOpenDialog(null);
				 //System.out.println(result);
				 if(result != JFileChooser.APPROVE_OPTION) {
					return;
				 }
				 
				 File selectedFile = filechooser.getSelectedFile();
				 
				 try {
					 byte[] imageData = new byte[(int) selectedFile.length()];
					 FileInputStream inputStream = new FileInputStream(selectedFile);
					 inputStream.read(imageData);
					 inputStream.close();
					 
					 ImageIcon icon = new ImageIcon(imageData);
					 Image img = icon.getImage().getScaledInstance(150, 150, java.awt.Image.SCALE_AREA_AVERAGING);
					 icon = new ImageIcon(img);
					 profile.setIcon(icon);
					 
					 imgData = imageData;

				 }catch(IOException e1) {
					 e1.printStackTrace();
				 }
			}
			
			if(e.getSource()==save) {
				
				try {
				int pin = Integer.parseInt(JOptionPane.showInputDialog(null,"Enter pin"));
				Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/foodsystemdb","root","");
				PreparedStatement stmt = conn.prepareStatement("SELECT pin FROM accounttable WHERE accountname=? and pin=?");
				stmt.setString(1, accountname.getText());
				stmt.setInt(2, pin);
				ResultSet rs = stmt.executeQuery();
				if(rs.next()) {
					update();
				}
				
				}catch(SQLException e1) {
					e1.printStackTrace();
				}catch(NumberFormatException e1) {
					JOptionPane.showMessageDialog(null, "Invalid Pin","",JOptionPane.ERROR_MESSAGE);
				}
				
			}
			
			
			if(e.getSource()==changePass) {
				new ChangePassFrame(accountname.getText());
			}
			
			if (e.getSource()==changePin) {
				try {
					int pin = Integer.parseInt(JOptionPane.showInputDialog(null,"Enter Pin"));
					Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/foodsystemdb","root","");
					PreparedStatement stmt = conn.prepareStatement("SELECT * FROM accounttable WHERE accountname=? and pin=?");
					stmt.setString(1, accountname.getText());
					stmt.setInt(2, pin);
					ResultSet rs = stmt.executeQuery();
					 
					if(rs.next()) {
						String newPin = JOptionPane.showInputDialog(null,"Enter new Pin");
						 
						if(newPin.length()!=4) {
							JOptionPane.showMessageDialog(null, "Pin should be 4 digits integer","",JOptionPane.ERROR_MESSAGE);
						}else {
							Connection conn1 = DriverManager.getConnection("jdbc:mysql://localhost:3306/foodsystemdb","root","");
							PreparedStatement pstmt1 = conn1.prepareStatement("UPDATE accounttable SET pin=? WHERE accountname=?");
							pstmt1.setInt(1, Integer.parseInt(newPin));
							pstmt1.setString(2, accountname.getText());
							pstmt1.executeUpdate();
						 
							JOptionPane.showMessageDialog(null, "Pin Successfully Changed");
						}
						 
					 }else {
						 JOptionPane.showMessageDialog(null, "Invalid Pin","",JOptionPane.ERROR_MESSAGE);
					 }
					 
				}catch(SQLException e1) {
					
					e1.printStackTrace();
					
				}catch(NumberFormatException e1) {
					JOptionPane.showMessageDialog(null, "Invalid Pin","",JOptionPane.ERROR_MESSAGE);
				}
				
			}
			
		}
		
	}