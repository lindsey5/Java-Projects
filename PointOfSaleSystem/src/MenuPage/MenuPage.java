package MenuPage;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

import ObjectsAndInterfaces.CustomScrollBarUI;
import ObjectsAndInterfaces.MenuInterface;
import ObjectsAndInterfaces.RoundRectPanel;

public class MenuPage extends JScrollPane implements MenuInterface {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	JPanel mainPanel;
	int width = 700;
	int height = 400;
	int count =0;
	OrdersPanel orderspanel;
	
	
	MenuPage(OrdersPanel orderspanel){
		this.orderspanel=orderspanel;
		
		mainPanel = new JPanel();
		mainPanel.setBackground(Color.DARK_GRAY);
		mainPanel.setLayout(new FlowLayout(FlowLayout.LEFT,40,35));
		
		this.setViewportView(mainPanel);
		this.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
		//this.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
		this.getVerticalScrollBar().setUnitIncrement(10);
		this.getVerticalScrollBar().setBlockIncrement(100);
		this.setBorder(BorderFactory.createEmptyBorder(0,0,0,0));
		this.getVerticalScrollBar().setUI(new CustomScrollBarUI(Color.LIGHT_GRAY,Color.BLACK,Color.BLACK,Color.BLACK));
		this.getVerticalScrollBar().setBackground(new Color(32,32,32));
		
		
	}
	
	//Reset the dimension and clear the main panel
	@Override
	public void reset() {
		height = 400;
		count = 0;
		mainPanel.revalidate();
		mainPanel.removeAll();
		mainPanel.repaint();
	}
	
	
	//Display the menu for the given category
	@Override
	public void displayMenu(String category) {
		
		reset();
		
		//Retrieves the menu items from a MySQL database and dynamically creates panel for each item to display them in panel
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			Connection conn=DriverManager.getConnection("jdbc:mysql://localhost:3306/foodsystemdb","root","");
			PreparedStatement pstmt = conn.prepareStatement("SELECT * FROM MenuTable WHERE category=?");
			pstmt.setString(1, category);
			ResultSet rs = pstmt.executeQuery();
			
			while (rs.next()) {
				String itemName = rs.getString("ItemName");//Get the item name 
				double price = rs.getDouble("Price");//Get the price of the item
			    mainPanel.setPreferredSize(new Dimension(width, height));//Set the preferred size of the main panel

			    byte[] imageData = rs.getBytes("ItemImage");//Get the bytes of the image of the item
			    ImageIcon icon = new ImageIcon(imageData);
			    Image img = icon.getImage().getScaledInstance(125, 125, java.awt.Image.SCALE_AREA_AVERAGING);
			    icon = new ImageIcon(img);
			    JLabel image = new JLabel(icon);
			    image.setBounds(40, 50, icon.getIconWidth(), icon.getIconHeight());

			  
			    JLabel itemnamelbl = new JLabel();
			    itemnamelbl.setText(itemName);
			    itemnamelbl.setFont(new Font("Arial", Font.BOLD, 20));
			    itemnamelbl.setForeground(Color.WHITE);
			    itemnamelbl.setHorizontalAlignment(SwingConstants.CENTER);
			    itemnamelbl.setBounds(0,190,200, 25);
			    
			    JLabel pricelbl = new JLabel();
			    pricelbl.setText("P- "+price);
			    pricelbl.setFont(new Font("Arial", Font.BOLD, 20));
			    pricelbl.setForeground(Color.WHITE);
			    pricelbl.setHorizontalAlignment(SwingConstants.CENTER);
			    pricelbl.setBounds(0,215,200, 25);

	           
			    JTextField quantity = new JTextField("1");
			    quantity.setBounds(80, 250, 40, 30);
			    quantity.setEditable(false);
			    quantity.setHorizontalAlignment(SwingConstants.CENTER);

			    //This button will increased the quantity
			    JButton add = new JButton("+");
			    add.setBounds(135, 250, 45, 30);
			    add.setBackground(new Color(32,32,32));
			    add.setFont(new Font("Arial",Font.BOLD,15));
			    add.setForeground(Color.WHITE);
			    add.addActionListener(new ActionListener() {
			        @Override
			        public void actionPerformed(ActionEvent e) {
			           if(e.getSource()==add) {
			        	  int qty = Integer.parseInt(quantity.getText());
			        	  qty++;
			        	  quantity.setText(Integer.toString(qty));
			           }
			        }
			    });
			    
			    //This button will decreased the quantity
			    JButton subtract = new JButton("-");
			    subtract.setBounds(20, 250, 45, 30);
			    subtract.setFont(new Font("Arial",Font.BOLD,15));
			    subtract.setBackground(new Color(32,32,32));
			    subtract.setForeground(Color.WHITE);
			    subtract.addActionListener(new ActionListener() {
			        @Override
			        public void actionPerformed(ActionEvent e) {
			            if (e.getSource() == subtract) {
			            	int qty = Integer.parseInt(quantity.getText());
			            	if(qty>1) {
			            		qty--;
			            		quantity.setText(Integer.toString(qty));
			            	}
			            }
			        }
			            	
			    });
			    
			    //Add order to the table of orders panel class
			    JButton addOrder = new JButton("Add Order");
			    addOrder.setBounds(50,300,100,30);
				addOrder.setBackground(Color.BLACK);
				addOrder.setForeground(Color.WHITE);
				addOrder.addActionListener(new ActionListener() {

					@Override
					public void actionPerformed(ActionEvent e) {
						
						if(e.getSource()==addOrder) {
							double totalprice;
							
								totalprice = price * Double.parseDouble(quantity.getText());
								
								orderspanel.addOrder(itemName, price, Integer.parseInt(quantity.getText()),totalprice,category);
						}
					}
				});
			    
				//This panel handle the image, name, and price of the item and handle other components 
				RoundRectPanel panel = new RoundRectPanel(40,40);
				panel.setLayout(null);
				panel.setPreferredSize(new Dimension(200,350));
				panel.setBackground(new Color(32,32,32));
				panel.add(itemnamelbl);
				panel.add(pricelbl);
				panel.add(image);
				panel.add(quantity);
				panel.add(add);
				panel.add(subtract);
				panel.add(addOrder);
				
				//Increased the height of the main panel when the count is divisible by 3
				count++;
				if(count % 4==0) {
					height+=400;
				}
				mainPanel.add(panel);
			}
			
			
		}catch(SQLException e) {
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
	}

}
