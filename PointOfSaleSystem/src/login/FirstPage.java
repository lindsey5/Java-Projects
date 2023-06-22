package login;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.RenderingHints;
import java.awt.geom.RoundRectangle2D;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;

import EventHandler.MouseInputs;
import ObjectsAndInterfaces.GradientButton;


public class FirstPage extends JFrame {

		/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	Image img;

		FirstPage(){
			
			img = new ImageIcon("res/point-of-sale.png").getImage();
			
			GradientButton admin = new GradientButton(new Color(0,255,127),new Color(0,100,0),new Color(144,238,144));
			
			admin.setText("Admin");
			admin.setBounds(400,200,150,50);
			admin.setBackground(new Color(148,0,211));
			admin.setForeground(Color.WHITE);
			admin.addActionListener((e)->{
						new AdminLoginPage();
						dispose();});
			
			GradientButton user = new GradientButton(new Color(0,255,127),new Color(0,100,0),new Color(144,238,144));
			user.setText("Cashier");
			user.setBounds(400,300,150,50);
			user.setBackground(new Color(148,0,211));
			user.setForeground(Color.WHITE);
			user.addActionListener((e)->{
				new loginPage(); 
				dispose();});
			
			JButton closeButton = new JButton("X");
			closeButton.setBounds(530, 30, 45, 40);
			closeButton.setBackground(Color.DARK_GRAY);
			closeButton.setForeground(new Color(0,255,0));
			closeButton.setFont(new Font("Arial",Font.BOLD,20));
			closeButton.setBorder(null);
			closeButton.addActionListener((e)-> {System.exit(0);});
			
			closeButton.addMouseListener(new MouseInputs(closeButton,Color.DARK_GRAY,Color.GRAY));	
			
			this.setUndecorated(true);
			this.setSize(600,500);
			this.setLocationRelativeTo(null);
			this.setShape(new RoundRectangle2D.Double(0,0,getWidth(),getHeight(),50,50));
			this.setLayout(null);
			this.getContentPane().setBackground(Color.DARK_GRAY);
			this.add(admin);
			this.add(user);
			this.add(closeButton);
			this.setVisible(true);
		}
		
		public void paint(Graphics g) {
			super.paint(g);
			Graphics2D g2 = (Graphics2D) g;
			g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
			//g2.setPaint(new GradientPaint(0,0,Color.BLACK,300,500, new Color(119,114,114)));
			//g2.setPaint(new Color(54,50,50));
			g2.setPaint(new Color(54,54,54));
			g2.fillRect(0, 0, 350, 500);
			g2.setPaint(Color.GREEN);
			g2.setFont(new Font("Arial",Font.BOLD,35));
			g2.drawString("Point Of Sale", 75, 300);
			g2.drawString("System", 120, 340);
			g2.drawImage(img, 120, 120, 120, 120, null);
			
		}
			
	}