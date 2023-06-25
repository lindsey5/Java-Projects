package AdminPage;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.filechooser.FileNameExtensionFilter;

import ObjectsAndInterfaces.CustomScrollBarUI;
import ObjectsAndInterfaces.MenuInterface;
import ObjectsAndInterfaces.RoundRectPanel;

public class AdminMenuPage extends JPanel implements ActionListener, MenuInterface{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	JPanel menuPanel,leftPanel;
	int width = 850,height = 400;
	int count =0;
	int itemCode;
	JLabel picture;
	JButton selectfile,add,update,back;
	JTextField itemname,pricetxt,itemCodetxt;
	JLabel itemNamelbl,pricelbl,itemCodelbl;
	byte[] imgData = null;
    DashBoard dashBoard = new DashBoard();
    String category = " ";
	
	AdminMenuPage(DashBoard dashBoard){
		this.dashBoard=dashBoard;
		
		itemCodelbl = new JLabel("Item Code");
		itemCodelbl.setBounds(60,570,150,30);
		itemCodelbl.setForeground(Color.LIGHT_GRAY);
		
		pricelbl = new JLabel("Price");
		pricelbl.setBounds(60,470,150,30);
		pricelbl.setForeground(Color.LIGHT_GRAY);
		
		itemNamelbl = new JLabel("ItemName");
		itemNamelbl.setBounds(60,370,150,30);
		itemNamelbl.setForeground(Color.LIGHT_GRAY);
		
		itemCodetxt = new JTextField();
		itemCodetxt.setBounds(60,600,150,30);
		
		pricetxt = new JTextField();
		pricetxt.setBounds(60,500,150,30);
		
		itemname = new JTextField();
		itemname.setBounds(60,400,150,30);
		
		update = new JButton("Update");
		update.setBounds(80,670,100,30);
		update.setVisible(false);
		update.setForeground(Color.WHITE);
		update.setBackground(Color.BLACK);
		update.addActionListener(this);
		
		back = new JButton("Back");
		back.setBounds(80,730,100,30);
		back.setForeground(Color.WHITE);
		back.setBackground(Color.BLACK);
		back.addActionListener(this);
		back.setVisible(false);
		
		add = new JButton("Add");
		add.setBounds(80,670,100,30);
		add.setForeground(Color.WHITE);
		add.setBackground(Color.BLACK);
		add.addActionListener(this);
		
		selectfile = new JButton("Select a File");
		selectfile.setBounds(60, 300, 150, 30);
		selectfile.setBackground(new Color(32,32,32));
		selectfile.setForeground(Color.WHITE);
		selectfile.addActionListener(this);
		
		picture = new JLabel();
		picture.setBounds(60,100,150,150);
		picture.setBackground(Color.DARK_GRAY);
		picture.setOpaque(true);
		
		leftPanel = new JPanel();
		leftPanel.setPreferredSize(new Dimension(250,0));
		leftPanel.setBackground(new Color(32,32,32));
		leftPanel.setLayout(null);
		leftPanel.add(picture);
		leftPanel.add(add);
		leftPanel.add(update);
		leftPanel.add(back);
		leftPanel.add(selectfile);
		leftPanel.add(itemname);
		leftPanel.add(itemNamelbl);
		leftPanel.add(pricetxt);
		leftPanel.add(pricelbl);
		leftPanel.add(itemCodetxt);
		leftPanel.add(itemCodelbl);
		
		menuPanel = new JPanel();
		menuPanel.setBackground(new Color(64,64,64));
		menuPanel.setLayout(new FlowLayout(FlowLayout.LEFT,60,25));
		
		
		JScrollPane scrollpane = new JScrollPane();
		scrollpane.setViewportView(menuPanel);
		scrollpane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
		//scrollpane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
		scrollpane.getVerticalScrollBar().setUnitIncrement(10);
		scrollpane.getVerticalScrollBar().setBlockIncrement(100);
		scrollpane.setBorder(BorderFactory.createEmptyBorder(0,0,0,0));
		scrollpane.getVerticalScrollBar().setUI(new CustomScrollBarUI(Color.GRAY,new Color(32,32,32),Color.BLACK,Color.BLACK));
		scrollpane.getHorizontalScrollBar().setUI(new CustomScrollBarUI(Color.GRAY,new Color(32,32,32),Color.BLACK,Color.BLACK));
		
		this.setLayout(new BorderLayout());
		this.add(scrollpane,BorderLayout.CENTER);
		this.add(leftPanel, BorderLayout.EAST);
		
	}
	
	//This method displays the menu items of a specific category
	@Override
	public void displayMenu(String category) {
		
		this.category=category;
		
		reset();//Resets the menu
		
		//Retrieves the menu items from a MySQL database and dynamically creates panel for each item to display them in menu panel
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			Connection conn=DriverManager.getConnection("jdbc:mysql://localhost:3306/foodsystemdb","root","");
			PreparedStatement pstmt = conn.prepareStatement("SELECT * FROM MenuTable WHERE category=?");
			pstmt.setString(1, category);
			ResultSet rs = pstmt.executeQuery();
			 
			while (rs.next()) {
				String itemName = rs.getString("ItemName");//Get the item name
				double price = rs.getDouble("Price");//Get the price
				int itmcode =rs.getInt("Itemcode");//Get the item code
				//System.out.println(itmcode);
			    menuPanel.setPreferredSize(new Dimension(width, height));//Set the preferred size

			    byte[] imageData = rs.getBytes("ItemImage");//Get the item image bytes
			    ImageIcon icon = new ImageIcon(imageData);//This converts bytes to image icon
			    Image img = icon.getImage().getScaledInstance(125, 125, java.awt.Image.SCALE_AREA_AVERAGING);//This sets the size of the image 
			    icon = new ImageIcon(img);
			    JLabel image = new JLabel(icon);//Set the image icon to the label
			    //image.setBounds(40, 50, icon.getIconWidth(), icon.getIconHeight());
			    image.setPreferredSize(new Dimension(icon.getIconWidth(), icon.getIconHeight()));
			    
			    JLabel itemnamelbl = new JLabel();
			    itemnamelbl.setText(itemName);
			    itemnamelbl.setFont(new Font("Arial", Font.BOLD, 20));
			    itemnamelbl.setForeground(Color.WHITE);
			    itemnamelbl.setHorizontalAlignment(SwingConstants.CENTER);
			    //itemnamelbl.setBounds(0,190,200, 25);
			    itemnamelbl.setPreferredSize(new Dimension(200,25));
			    
			    JLabel pricelbl = new JLabel();
			    pricelbl.setText("P- "+price);
			    pricelbl.setFont(new Font("Arial", Font.BOLD, 20));
			    pricelbl.setForeground(Color.WHITE);
			    pricelbl.setHorizontalAlignment(SwingConstants.CENTER);
			    //pricelbl.setBounds(0,215,200, 25);
			    pricelbl.setPreferredSize(new Dimension(200,25));
			  
			    JButton deleteButton = new JButton("Delete");
			    //deleteButton.setBounds(25,300,150,30);
			    deleteButton.setPreferredSize(new Dimension(150,30));
			    deleteButton.setBackground(Color.RED);
			    deleteButton.setForeground(Color.WHITE);
			    deleteButton.addActionListener((e)->{
			    	int response = JOptionPane.showOptionDialog(null, "Are you sure you want to delete?", "",JOptionPane.YES_NO_OPTION,JOptionPane.QUESTION_MESSAGE, null,null,null);
			    	
					if(response == JOptionPane.YES_OPTION) {	
						try{
							Connection conn1=DriverManager.getConnection("jdbc:mysql://localhost:3306/foodsystemdb","root","");
							PreparedStatement pstmt1 = conn1.prepareStatement("DELETE FROM menutable WHERE itemCode=?");//Deletes item to the MySQL database
							pstmt1.setInt(1, itmcode);
							pstmt1.executeUpdate();
							reset();
							displayMenu(category);//Refresh the menu
							dashBoard.setData();//Update the dash board
								
						}catch(SQLException e1) {
							e1.printStackTrace();
						}	
							JOptionPane.showMessageDialog(null, "Item successfully deleted");
					}		
			    });
			    
			    JButton select = new JButton("Select");
			    //select.setBounds(25,250,150,30);
			    select.setPreferredSize(new Dimension(150,30));
			    select.setBackground(new Color(32,32,32));
			    select.setForeground(Color.WHITE);
			    select.addActionListener((e)->{
			    	update.setVisible(true);
					add.setVisible(false);
					back.setVisible(true);
					itemCodetxt.setText(itmcode+"");
					itemCodetxt.setEditable(false);
					
				    imgData = imageData;
					ImageIcon imgIcon = new ImageIcon(imgData);
					Image img1 = imgIcon.getImage().getScaledInstance(150, 150, java.awt.Image.SCALE_AREA_AVERAGING);
					imgIcon = new ImageIcon(img1);
					picture.setIcon(imgIcon);
					//System.out.println(imgData);
					itemCode = itmcode;
					itemname.setText(itemName);
					pricetxt.setText(price+"");
			    });
			    
				RoundRectPanel itempanel = new RoundRectPanel(40,40);
				itempanel.setPreferredSize(new Dimension(200,370));
				itempanel.setBackground(new Color(32,32,32));
				itempanel.setLayout(new FlowLayout(FlowLayout.CENTER,0,20));
				itempanel.add(image);
				itempanel.add(itemnamelbl);
				itempanel.add(pricelbl);
				itempanel.add(select);
				itempanel.add(deleteButton);
				
				
				//The height of mainPanel increased when count is divisible by 3
				count++;
				if(count % 4==0) {
					height+=400;
				}
				menuPanel.add(itempanel);
			}
			
		}catch(SQLException e) {
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
			
	}
	
	//Resets the menu panel by removing all
	@Override
	public void reset() {
		count=0;
		height = 400;
		menuPanel.removeAll();
		//menuPanel.revalidate();
		menuPanel.repaint();
		itemCodetxt.setText("");
		imgData = null;
		itemCode = 0;
		picture.setIcon(null);
		itemname.setText("");
		pricetxt.setText("");	
		
		add.setVisible(true);
		itemCodetxt.setVisible(true);
		itemCodetxt.setEditable(true);
		back.setVisible(false);
		update.setVisible(false);

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
			 imgData = new byte[(int) selectedFile.length()];
			 
			 FileInputStream inputStream = new FileInputStream(selectedFile);
			 inputStream.read(imgData);
			 inputStream.close();
			 
			 ImageIcon icon = new ImageIcon(imgData);
			 Image img = icon.getImage().getScaledInstance(150, 150, java.awt.Image.SCALE_AREA_AVERAGING);
			 icon = new ImageIcon(img);
			 
			 picture.setIcon(icon);
			 
		     }catch(IOException e1) {
		    	 e1.printStackTrace();
		     }
	}
	
	
	if(e.getSource()==add) {
		
		if(!itemname.getText().isEmpty() && !pricetxt.getText().isEmpty() && !itemCodetxt.getText().isEmpty() && imgData!=null ) {
				
			try {
				//Insert item to the database
				Connection conn=DriverManager.getConnection("jdbc:mysql://localhost:3306/foodsystemdb","root","");
				PreparedStatement pstmt = conn.prepareStatement("INSERT INTO menutable (itemcode,itemname,price,itemimage,category) VALUES(?,?,?,?,?)");
				pstmt.setInt(1, Integer.parseInt(itemCodetxt.getText()));
				pstmt.setString(2, itemname.getText());
				pstmt.setDouble(3, Double.parseDouble(pricetxt.getText()));
				pstmt.setBytes(4, imgData);
				pstmt.setString(5, category);
				pstmt.executeUpdate();
			
				reset();
				displayMenu(category);//Refresh the menu
				dashBoard.setData();//Update the dash board
			
			
		}catch(SQLException e1) {
			JOptionPane.showMessageDialog(null, "Itemcode Already Exist");
		}catch(NumberFormatException e1) {
			e1.printStackTrace();
		}
				
		}else {
			JOptionPane.showMessageDialog(null, "Fill the Blanks","",JOptionPane.ERROR_MESSAGE);
		}
	}
	
	
	if(e.getSource()==update) {
		
		if(itemname.getText().isEmpty() || pricetxt.getText().isEmpty()) {
			JOptionPane.showMessageDialog(null, "Fill the Blanks","",JOptionPane.ERROR_MESSAGE);
		}
		else {
		
		try {
			//Update the item
			Connection conn=DriverManager.getConnection("jdbc:mysql://localhost:3306/foodsystemdb","root","");
			PreparedStatement pstmt = conn.prepareStatement("UPDATE menutable SET itemName=?, price=?, itemimage=? WHERE itemcode=?");
			pstmt.setString(1, itemname.getText());
			pstmt.setDouble(2, Double.parseDouble(pricetxt.getText()));
			pstmt.setBytes(3, imgData);
			pstmt.setInt(4, itemCode);
			pstmt.executeUpdate();
			
			reset();
			displayMenu(category);
			
			JOptionPane.showMessageDialog(null, "Update Successfully");
			
		}catch(SQLException e1) {
			e1.printStackTrace();
		}
		}
	}
	
	
	if(e.getSource()==back) {
		add.setVisible(true);
		itemCodetxt.setVisible(true);
		itemCodetxt.setEditable(true);
		itemCodetxt.setText("");
		back.setVisible(false);
		update.setVisible(false);
		itemCode = 0;
		imgData = null;
		picture.setIcon(null);
		itemname.setText("");
		pricetxt.setText("");
		 
	}
	
}

}

