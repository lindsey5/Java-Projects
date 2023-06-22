package MenuPage;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*;
import java.time.LocalDate;
import java.time.Month;
import javax.swing.JComboBox;
import javax.swing.JPanel;
import javax.swing.JScrollPane;

import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.table.DefaultTableModel;

import ObjectsAndInterfaces.DarkTable;

public class AccountHistory extends JPanel implements ActionListener {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	DefaultTableModel model = new DefaultTableModel();
	DarkTable table = new DarkTable(model);
	JScrollPane scrollPane = new JScrollPane(table);
	JTextField searchfield;
	JComboBox<String> combobox;
	String accountname;
    String category = " ";
    
	AccountHistory(String username, String password){
		
		try {
			Connection conn = DriverManager.getConnection("jdbc:mysql://localhost/foodsystemdb","root","");
		    PreparedStatement stmt = conn.prepareStatement("SELECT accountname FROM accounttable WHERE username=? AND password=?");
		    stmt.setString(1, username);
		    stmt.setString(2, password);
		    ResultSet rs = stmt.executeQuery();
		    
		    if(rs.next()) {
		    accountname = rs.getString("accountName");
		    }
		    
		}catch(SQLException e) {
			e.printStackTrace();
		}
		
		combobox = new JComboBox<>();
		combobox.setBounds(90,30,100,30);
		combobox.setPreferredSize(new Dimension(100, 30));
		combobox.addActionListener(this);
		combobox.addItem("All");
		combobox.addItem("This Day");
		combobox.addItem("This Week");
		combobox.addItem("This Month");
	
		searchfield = new JTextField();
		//searchfield.setBounds(250,50,180,40);
		searchfield.setPreferredSize(new Dimension(180,40));
		searchfield.setHorizontalAlignment(SwingConstants.CENTER);
		setDocumentListener();
	
		model.addColumn("Orders");
		model.addColumn("Date");
		model.addColumn("Time");
		model.addColumn("Payment");
		model.addColumn("Total");
		model.addColumn("Change");
	
		scrollPane.setPreferredSize(new Dimension(1280,600));
		scrollPane.getViewport().setBackground(Color.GRAY);
		table.fixTable(scrollPane);
	
		this.setLayout(new FlowLayout(FlowLayout.LEFT, 50,50));
		this.setBackground(new Color(64,64,64));
	
		this.add(searchfield);
		this.add(combobox);
		this.add(scrollPane);
		//this.add(printReport);
	
		setAllDataToTable();
	
	}

	//Method to set document listener
	private void setDocumentListener() {
		
            searchfield.getDocument().addDocumentListener(new DocumentListener() {
			
            //Method to update search results based on the text entered in the search field
			void updateSearchResults() {
				String searchText = searchfield.getText();
				
				String text = " ";
				if(category.equals("all")) {
					text = " ";
				}else if(category.equals("today")) {
					text = " AND date=?";
				}else if(category.equals("this week")) {
					text = " AND date BETWEEN ? AND ?";
				}else if(category.equals("this month")) {
					text = " AND MONTH(date)=?";				
				}
				
				try {
					Connection conn = DriverManager.getConnection("jdbc:mysql://localhost/foodsystemdb","root","");
				    PreparedStatement stmt = conn.prepareStatement("SELECT * FROM orderhistory WHERE ("
				    		        + " orders LIKE '%"+searchText+"%'"
					    			+ " OR date LIKE '%"+searchText+"%'"
					    			+ " OR time LIKE '%"+searchText+"%'"
					    			+ " OR payment LIKE '%"+searchText+"%'"
					    			+ " OR total LIKE '%"+searchText+"%'"
					    			+ " OR changee LIKE '%"+searchText+"%')"
					    					+ text+ " AND accountname=?");
				    if(category.equals("today")) {
				    	stmt.setDate(1, java.sql.Date.valueOf(LocalDate.now()));
				    	stmt.setString(2, accountname);
				    	
				    }else if(category.equals("this week")) {
				    	LocalDate currentDate = LocalDate.now();
						LocalDate startDate = currentDate.minusDays(7);
				    	stmt.setDate(1, java.sql.Date.valueOf(startDate));
				    	stmt.setDate(2, java.sql.Date.valueOf(currentDate));
				    	stmt.setString(3, accountname);
				    	
				    }else if(category.equals("this month")) {
				    	LocalDate currentDate = LocalDate.now();
						Month currentMonth = currentDate.getMonth();
						int monthValue = currentMonth.getValue();
						stmt.setInt(1, monthValue);
						stmt.setString(2, accountname);
				    }
				    
				    ResultSet rs = stmt.executeQuery();
				    model.setRowCount(0);
				    
				    while(rs.next()) {
						model.insertRow(0, new Object[] {
								rs.getString("orders"),
								rs.getString("date"),
								rs.getString("time"),
								rs.getString("payment"),
								rs.getString("total"),
								rs.getString("changee")});
					} 
					
				}catch(SQLException e) {
					e.printStackTrace();
				}
					
			}
			
			@Override
			public void insertUpdate(DocumentEvent e) {
				updateSearchResults();
				
			}

			@Override
			public void removeUpdate(DocumentEvent e) {
				updateSearchResults();
				
			}

			@Override
			public void changedUpdate(DocumentEvent e) {
				updateSearchResults();
				
			}
			
			
			
		});
	}
	
	void setAllDataToTable() {
		category = "all";
		model.setRowCount(0);
		try {
			Connection conn = DriverManager.getConnection("jdbc:mysql://localhost/foodsystemdb","root","");
		    PreparedStatement stmt = conn.prepareStatement("SELECT * FROM orderhistory WHERE accountname=?");
		    stmt.setString(1, accountname);
		    ResultSet rs = stmt.executeQuery();
		    
		    while(rs.next()) {
				model.insertRow(0, new Object[] {
						rs.getString("orders"),
						rs.getString("date"),
						rs.getString("time"),
						rs.getString("payment"),
						rs.getString("total"),
						rs.getString("changee")});
			}
		    
			
		}catch(SQLException e) {
			
		}
	}
	
	void setTodayDataToTable() {
		category = "today";
		model.setRowCount(0);
		try {
			Connection conn = DriverManager.getConnection("jdbc:mysql://localhost/foodsystemdb","root","");
		    PreparedStatement stmt = conn.prepareStatement("SELECT * FROM orderhistory WHERE date=? AND accountname=?");
		    stmt.setDate(1,java.sql.Date.valueOf(LocalDate.now()) );
		    stmt.setString(2, accountname);
		    
		    ResultSet rs = stmt.executeQuery();
		    
		    while(rs.next()) {
				model.insertRow(0, new Object[] {
						rs.getString("orders"),
						rs.getString("date"),
						rs.getString("time"),
						rs.getString("payment"),
						rs.getString("total"),
						rs.getString("changee")});
				
			}
		    
		   			
		}catch(SQLException e) {
			e.printStackTrace();
		}
	}
	
	void setThisWeekDataToTable() {
		category = "this week";
		model.setRowCount(0);
		try {
			LocalDate currentDate = LocalDate.now();
			LocalDate startDate = currentDate.minusDays(7);
			Connection conn=DriverManager.getConnection("jdbc:mysql://localhost:3306/foodsystemdb","root","");
			PreparedStatement pstmt = conn.prepareStatement("SELECT * FROM orderhistory WHERE date BETWEEN ? AND ? AND accountname=?");
			pstmt.setDate(1, java.sql.Date.valueOf(startDate));
			pstmt.setDate(2, java.sql.Date.valueOf(currentDate));
			pstmt.setString(3, accountname);
			ResultSet rs = pstmt.executeQuery();
			
			while(rs.next()) {
				model.insertRow(0, new Object[] {
						rs.getString("orders"),
						rs.getString("date"),
						rs.getString("time"),
						rs.getString("payment"),
						rs.getString("total"),
						rs.getString("changee")});
				
			}
		    
		   			
       }catch(SQLException e) {
			e.printStackTrace();
		}
	}
	
	void setThisMonthDataToTable() {
		category = "this month";
		model.setRowCount(0);
		try {
			LocalDate currentDate = LocalDate.now();
			Month currentMonth = currentDate.getMonth();
			int monthValue = currentMonth.getValue();
			Connection conn=DriverManager.getConnection("jdbc:mysql://localhost:3306/foodsystemdb","root","");
			PreparedStatement pstmt = conn.prepareStatement("SELECT * FROM orderhistory WHERE MONTH(date)=? AND accountname=?");
			pstmt.setInt(1,monthValue);
			pstmt.setString(2, accountname);
			ResultSet rs = pstmt.executeQuery();
			
			while(rs.next()) {
				model.insertRow(0, new Object[] {
						rs.getString("orders"),
						rs.getString("date"),
						rs.getString("time"),
						rs.getString("payment"),
						rs.getString("total"),
						rs.getString("changee")});
			}
		    
		    		
        }catch(SQLException e) {
			e.printStackTrace();
		}
		
	}
		
	
	@Override
	public void actionPerformed(ActionEvent e) {
		if(e.getSource()==combobox) {
			String selectedItem = (String)combobox.getSelectedItem();
			if(selectedItem.equals("All")) {
				setAllDataToTable(); 
			}else if(selectedItem.equals("This Day")) {
				setTodayDataToTable();
			}else if(selectedItem.equals("This Week")) {
				setThisWeekDataToTable();
			}else if(selectedItem.equals("This Month")) {
				setThisMonthDataToTable();
			}
		}	
		
	}
}

