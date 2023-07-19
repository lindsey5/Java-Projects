package MenuPage;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.sql.*;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;

public class OrdersPanel extends JPanel implements ActionListener {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	DefaultTableModel model = new DefaultTableModel() ;
	JTable table = new JTable(model);
	JScrollPane scrollPane = new JScrollPane(table);
	ArrayList<Order> orders = new ArrayList<>();
	double total = 0;
	JLabel totallbl  = new JLabel("Total:");
	JTextField totaltxt = new JTextField();
	JLabel paymentlbl = new JLabel("Enter payment:");
	JTextField payment = new JTextField();
	JButton print = new JButton("Print");
	JButton remove = new JButton("Remove");
	String username;
	String password;
	String accountname;
	
	AccountHistory accountHistory;

	
	 OrdersPanel(String username, String password, AccountHistory accountHistory){
		 
		this.username=username;
		this.password=password;
		this.accountHistory = accountHistory;
		
		remove.setBounds(130, 520, 100, 40);
		remove.setBackground(Color.RED);
		remove.setForeground(Color.WHITE);
		remove.setFont(new Font("Arial",Font.BOLD,15));
		remove.addActionListener(this);
		
		print.setBounds(110,720,150,40);
		print.setBackground(Color.BLACK);
		print.setForeground(Color.WHITE);
		print.setFont(new Font("Arial",Font.BOLD,20));
		print.addActionListener(this);
		
		payment.setBounds(170,650,150,30);
		
		paymentlbl.setBounds(10,650,200,30);
		paymentlbl.setForeground(Color.BLACK);
		paymentlbl.setFont(new Font("Arial",Font.BOLD,20));
		
		totaltxt.setBounds(170,600,150,30);
		totaltxt.setEditable(false);
		
		totallbl.setBounds(40,600,150,30);
		totallbl.setForeground(Color.BLACK);
		totallbl.setFont(new Font("Arial",Font.BOLD,25));
		
		model.addColumn("Name");
		model.addColumn("UnitPrice");
		model.addColumn("Quantity");
		model.addColumn("Price");
		model.addColumn("Size");
		
		table.getTableHeader().setEnabled(false);
		table.getTableHeader().setForeground(Color.WHITE);
		table.getTableHeader().setBackground(new Color(32,32,32));
		table.getTableHeader().setFont(new Font("Callibri",Font.PLAIN,12));
		table.setFont(new Font("Arial",Font.PLAIN,15));
		table.setRowHeight(30);
		table.getColumnModel().getColumn(0).setPreferredWidth(170);
		table.setBackground(Color.WHITE);
		table.setForeground(Color.BLACK);
		table.setSelectionBackground(Color.RED);
		table.setSelectionForeground(Color.WHITE);
		
		scrollPane.setBounds(0,0,330,500);
		scrollPane.getViewport().setBackground(Color.DARK_GRAY);
		//scrollPane.setBorder(BorderFactory.createEmptyBorder(0,0,0,0));
		
		this.add(scrollPane);
		this.add(totallbl);
		this.add(totaltxt);
		this.add(paymentlbl);
		this.add(payment);
		this.add(print);
		this.add(remove);
		this.setPreferredSize(new Dimension(330,0));
		this.setBackground(Color.GRAY);
		this.setLayout(null);
		
	}
	
	
	//Add order to the table
	public void addOrder(String name,double unitprice,int quantity,double price,String category) {
		String size = "";
		boolean x = true;
		if(category.equals("Drink") || category.equals("Side")) {
			double smallPrice = unitprice;
			double mediumPrice = unitprice+10;
			double largePrice = unitprice+20;
			Object[] options = {"Small: "+smallPrice,"Medium: "+mediumPrice,"Large: "+largePrice};
			
			int choice = JOptionPane.showOptionDialog(null, "Choose Size", "", JOptionPane.DEFAULT_OPTION, JOptionPane.PLAIN_MESSAGE, null, options, options[0]);
		    switch(choice) {
		    case 0:
		    	size = "Small";
		    	break;
		    case 1:
		    	size = "Medium";
		    	unitprice+=10;
		    	price= unitprice*quantity;
		    	break;
		    case 2:
		    	size = "Large";
		    	unitprice+=20;
		    	price= unitprice*quantity;
		    	break;
		    default:
		    	x=false;
		    }
		}else {
			size = " ";
		}
		
		
		if(x) {
		//Check if the same item has already been ordered and update it's price and quantity instead of adding a new order
			boolean flag = false;
			for(int i=0;i<orders.size();i++) {
				if(orders.get(i).getName().equals(name) && orders.get(i).getSize().equals(size)) {
					orders.get(i).setPrice(price);
					orders.get(i).setQuantity(quantity);
					model.setValueAt(orders.get(i).getQuantity(), i, 2);
					model.setValueAt(orders.get(i).getPrice(), i, 3);
					totaltxt.setText(total+" ");
					flag=true;
					break;
				}			
			}
		
			if(!flag) {
				orders.add(new Order(name,unitprice,quantity,price,size,category));
				model.addRow(new Object[] {name,unitprice,quantity,price,size});
			}
		
			JOptionPane.showMessageDialog(null, "Order Successfully Added");
		
			//Compute total price
			total=0;
		
			for(int i=0;i<orders.size();i++) {
				total+=orders.get(i).getPrice();	
			}
				
			totaltxt.setText(total+"");
		
			}
	}
	
	void addToDataBase() {
	    try {
	    	
	        for (Order order : orders) {
	            Connection conn = DriverManager.getConnection("jdbc:mysql://localhost/foodsystemdb", "root", "");
	            PreparedStatement stmt = conn.prepareStatement("SELECT * FROM productsales WHERE itemName=? and date=? and size=?");
	            stmt.setString(1, order.getName());
	            stmt.setDate(2, java.sql.Date.valueOf(LocalDate.now()));
	            stmt.setString(3, order.getSize());
	            ResultSet rs = stmt.executeQuery();
	            
	            if (rs.next()) {
	                int quantity = rs.getInt("quantity");
	                double total = rs.getDouble("total");
	                PreparedStatement stmt1 = conn.prepareStatement("UPDATE productsales SET quantity=?, total=? WHERE itemName=? and date=? and size=?");
	                stmt1.setInt(1, quantity + order.getQuantity());
	                stmt1.setDouble(2, total + order.getPrice());
	                stmt1.setString(3, order.getName());
	                stmt1.setDate(4, java.sql.Date.valueOf(LocalDate.now()));
	                stmt1.setString(5, order.getSize());
	                stmt1.executeUpdate();
	                
	            }else {
	                PreparedStatement stmt2 = conn.prepareStatement("INSERT INTO productsales (itemName, quantity, unitprice, total, date,size,category) VALUES (?,?,?,?,?,?,?)");
	                stmt2.setString(1, order.getName());
	                stmt2.setInt(2, order.getQuantity());
	                stmt2.setDouble(3, order.getUnitPrice());
	                stmt2.setDouble(4, order.getPrice());
	                stmt2.setDate(5, java.sql.Date.valueOf(LocalDate.now()));
	                stmt2.setString(6, order.getSize());
	                stmt2.setString(7, order.getCategory());
	                stmt2.executeUpdate();
	            }
	        }
	    } catch (SQLException e) {
	        e.printStackTrace();
	    }
	    
	}

	
	@Override
	public void actionPerformed(ActionEvent e) {
		
		if(e.getSource()==print) {
			
		try {
			
		if(Double.parseDouble(payment.getText())<total){
			JOptionPane.showMessageDialog(null, "Insufficient Payment");
			
		}else {
			double change = Double.parseDouble(payment.getText()) - total;
			
			try {
				//Get the AccountName
			    Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/foodsystemdb","root","");
			    PreparedStatement stmt = conn.prepareStatement("SELECT accountname FROM accounttable WHERE username=? and password=?");
			    stmt.setString(1, username);
			    stmt.setString(2, password);
			    ResultSet rs = stmt.executeQuery();
			    if(rs.next()) {
			        accountname = rs.getString(1);
			       // System.out.println(accountname);
			        
			        StringBuilder builder = new StringBuilder();
			        for(int i=0;i<orders.size();i++) {
			            String name = orders.get(i).getName();
			            int quantity = orders.get(i).getQuantity();
			            if(i==orders.size()-1) {
			            	builder.append(quantity+" "+name+"("+orders.get(i).getSize()+")");
			            } else {
			                builder.append(quantity+" "+name+"("+orders.get(i).getSize()+")"+",");
			            }
			            
			            if(i%2==0 && i!=0) {
			            	builder.append("\n");
			            }
			            
			        }
			        String ordersStr = builder.toString();
			        stmt = conn.prepareStatement("INSERT INTO orderhistory(accountname, orders, date, time, payment, total, changee) VALUES(?,?,?,?,?,?,?)");
			        stmt.setString(1, accountname);
			        stmt.setString(2, ordersStr);
			        stmt.setDate(3, java.sql.Date.valueOf(LocalDate.now()));
			        stmt.setTime(4, java.sql.Time.valueOf(LocalTime.now()));
			        stmt.setDouble(5, Double.parseDouble(payment.getText()));
			        stmt.setDouble(6, total);
			        stmt.setDouble(7, change);
			        stmt.executeUpdate();
			    }
			    
			    
			} catch(SQLException e1) {
			    e1.printStackTrace();
			}
			accountHistory.setAllDataToTable();//Updates the history 
			addToDataBase();
			new Receipt(orders,total,Double.parseDouble(payment.getText()),change,accountname);//Show receipt
			
			//Resets the table, totalprice, and payment
			orders.clear();
			model.setRowCount(0);
			totaltxt.setText("");
			total=0;
			payment.setText("");
		}
		}catch(NumberFormatException e1) {
			
		}
	}
		
		if(e.getSource()==remove) {
			if(table.getSelectedRow()!=-1) {
				total-=orders.get(table.getSelectedRow()).getPrice();
				orders.remove(table.getSelectedRow());
				model.removeRow(table.getSelectedRow());
				totaltxt.setText(total+"");
			}
		}
	}
	
}
