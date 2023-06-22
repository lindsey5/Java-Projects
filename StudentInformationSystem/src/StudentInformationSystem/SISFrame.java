package StudentInformationSystem;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GradientPaint;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.*;
import javax.swing.BorderFactory;
import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.table.DefaultTableModel;

public class SISFrame extends JFrame implements ActionListener{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	JPanel panel;
	RoundRectTextField firstname;
	RoundRectTextField lastname;
	RoundRectTextField studentID;
	RoundRectTextField year;
	RoundRectTextField section;
	RoundRectTextField age;
	JRadioButton male;
	JRadioButton female;
	JLabel studentIDlbl;
	JLabel firstnamelbl;
	JLabel lastnamelbl;
	JLabel yearlbl;
	JLabel sectionlbl;
	JLabel agelbl;
	JLabel sexlbl;
	DefaultTableModel model = new DefaultTableModel();
	JTable table = new JTable(model);
	JComboBox<String> combobox;
	JButton add;
	JButton update;
	JButton delete;
	JButton course;
	JTextField searchfield;
	JLabel title = new JLabel();
	Thread titleThread;
	DefaultTableModel model1 = new DefaultTableModel();
	JTable table1 = new JTable(model1);
	JScrollPane scrollPane1 = new JScrollPane(table1);
	JButton back = new JButton("🡐");
	JButton addCourse = new JButton("Add Course");
	JButton delCourse = new JButton("Del Course");

	SISFrame(){
		delCourse.setBounds(250,550,100,30);
		delCourse.setBackground(new Color(255,204,203));
		delCourse.setForeground(Color.RED);
		delCourse.addActionListener(this);
		
		addCourse.setBounds(50,550,100,30);
		addCourse.setBackground(new Color(127,255,212));
		addCourse.addActionListener(this);
		
		back.setBounds(30, 30, 45, 30);
		back.setBackground(new Color(127,255,212));
		back.addActionListener(this);
		
		model1.addColumn("Course");
		model1.addColumn("No. of Students");
		
		table1.getTableHeader().setBackground(new Color(127,255,212));
		table1.setSelectionBackground(Color.RED);
		table1.setSelectionForeground(Color.WHITE);
		table1.getTableHeader().setFont(new Font("Callibri",Font.BOLD,13));
		table1.getTableHeader().setEnabled(false);
		table1.getTableHeader().setPreferredSize(new Dimension(0,30));
		table1.setRowHeight(20);
		
		scrollPane1.setBounds(50,80,300,400);
		//scrollPane1.getViewport().setBackground(new Color(255,228,225));
		//scrollPane1.setBorder(BorderFactory.createEmptyBorder(0,0,0,0));
		

		title.setBounds(50,30,450,50);
		title.setFont(new Font("Arial",Font.BOLD,30));
		
		
		searchfield = new JTextField();
		searchfield.setBounds(200,150,150,30);
		searchfield.setHorizontalAlignment(SwingConstants.CENTER);
		
		sexlbl = new JLabel("SEX");
		sexlbl.setBounds(230,420,100,30);
		sexlbl.setFont(new Font("Callibri",Font.BOLD,13));
		
		male = new JRadioButton("Male");
		male.setBounds(220,450,60,30);
		male.setOpaque(false);
		
		female = new JRadioButton("Female");
		female.setBounds(300,450,70,30);
		female.setOpaque(false);
		
		
		ButtonGroup buttons = new ButtonGroup();
		buttons.add(male);
		buttons.add(female);
       
		
		agelbl = new JLabel("AGE");
		agelbl.setBounds(30,420,100,30);
		agelbl.setFont(new Font("Callibri",Font.BOLD,13));
		
        age = new RoundRectTextField();
		age.setBounds(20,450,150,40);
		age.setHorizontalAlignment(SwingConstants.CENTER);
		
		
		sectionlbl = new JLabel("SECTION");
		sectionlbl.setBounds(230,310,100,30);
		sectionlbl.setFont(new Font("Callibri",Font.BOLD,13));
		
		section = new RoundRectTextField();
		section.setBounds(220,340,150,40);
		section.setHorizontalAlignment(SwingConstants.CENTER);
		
		yearlbl = new JLabel("YEAR");
		yearlbl.setBounds(30,310,100,30);
		yearlbl.setFont(new Font("Callibri",Font.BOLD,13));
		
		year = new RoundRectTextField();
		year.setBounds(20,340,150,40);
		year.setHorizontalAlignment(SwingConstants.CENTER);

		lastnamelbl = new JLabel("LASTNAME");
		lastnamelbl.setBounds(230,200,100,30);
		lastnamelbl.setFont(new Font("Callibri",Font.BOLD,13));
		
		lastname = new RoundRectTextField();
		lastname.setBounds(220,230,150,40);
		lastname.setHorizontalAlignment(SwingConstants.CENTER);
		
		
		firstnamelbl = new JLabel("FIRSTNAME");
		firstnamelbl.setBounds(30,200,100,30);
		firstnamelbl.setFont(new Font("Callibri",Font.BOLD,13));
		
		firstname = new RoundRectTextField();
		firstname.setBounds(20,230,150,40);
		firstname.setHorizontalAlignment(SwingConstants.CENTER);
		
		studentIDlbl = new JLabel("STUDENT ID");
		studentIDlbl.setBounds(30,90,100,30);
		studentIDlbl.setFont(new Font("Callibri",Font.BOLD,13));
		
		studentID = new RoundRectTextField();
		studentID.setBounds(20,120,150,40);
		studentID.setHorizontalAlignment(SwingConstants.CENTER);
		
		 panel = new JPanel() {
				
				/**
				 * 
				 */
				private static final long serialVersionUID = 1L;

				@Override
				protected void paintComponent(Graphics g) {
					Graphics2D g2 = (Graphics2D) g;
					setOpaque(false);
					g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
					g2.setPaint(new GradientPaint(0, 0,new Color(127,255,212),getWidth(), getHeight(), new Color(162,107,243)));
					g2.fillRoundRect(0, 0, getWidth(), getHeight(), 40, 40);
					super.paintComponent(g);
				}
			};
		panel.setBounds(550,50,400,650);
		panel.setLayout(null);
		panel.add(studentID);
		panel.add(studentIDlbl);
		panel.add(firstname);
		panel.add(firstnamelbl);
		panel.add(lastname);
		panel.add(lastnamelbl);
		panel.add(year);
		panel.add(yearlbl);
		panel.add(section);
		panel.add(sectionlbl);
		panel.add(age);
		panel.add(agelbl);
		panel.add(male);
		panel.add(female);
		panel.add(sexlbl);
		//panel.add(scrollPane1);
		
		
		course = new JButton("Course");
		course.setBounds(420, 150, 100, 30);
		course.setBackground(new Color(255,204,203));
		course.addActionListener(this);
		
		
		delete = new JButton("DELETE");
		delete.setBounds(360,650,100,30);
		delete.setBackground(new Color(127,255,212));
		delete.addActionListener(this);

		
		update = new JButton("UPDATE");
		update.setBounds(230,650,100,30);
		update.setBackground(new Color(127,255,212));
		update.addActionListener(this);
		
		add = new JButton("ADD");
		add.setBounds(100,650,100,30);
		add.setBackground(new Color(127,255,212));
		add.addActionListener(this);
		
		combobox = new JComboBox<>();
		combobox.setBounds(30,150,100,30);
		combobox.setBackground(Color.WHITE);
		combobox.addActionListener(this);
		
		
		model.addColumn("StudentID");
		model.addColumn("Firstname");
		model.addColumn("Lastname");
		model.addColumn("Year");
		model.addColumn("Section");
		model.addColumn("Age");
		model.addColumn("Sex");
		
		
		table.addMouseListener(new MouseAdapter() {
		    
	        public void mouseClicked(MouseEvent e) {
	        	    studentID.setText((String)model.getValueAt(table.getSelectedRow(), 0));
	        	    
	                firstname.setText((String)model.getValueAt(table.getSelectedRow(), 1));
	                
	                lastname.setText((String)model.getValueAt(table.getSelectedRow(), 2));
	                
	                year.setText((String)model.getValueAt(table.getSelectedRow(), 3));
	               
	                section.setText((String)model.getValueAt(table.getSelectedRow(), 4));
	                
	                age.setText((String)model.getValueAt(table.getSelectedRow(), 5));
	                
	                String gender = (String)model.getValueAt(table.getSelectedRow(), 6);
	                if(gender.equals("Male")) {
	                	male.setSelected(true);
	                }else {
	                	female.setSelected(true);
	                }
	            
	        }
	    });
		
		table.getTableHeader().setBackground(new Color(127,255,212));
		table.setSelectionBackground(Color.RED);
		table.setSelectionForeground(Color.WHITE);
		table.getTableHeader().setFont(new Font("Callibri",Font.BOLD,13));
		table.getTableHeader().setEnabled(false);
		table.getTableHeader().setPreferredSize(new Dimension(0,30));
		table.setRowHeight(20);
		
		JScrollPane scrollPane = new JScrollPane(table);
		scrollPane.setBounds(30,200,500,420);
		scrollPane.getViewport().setBackground(new Color(255,228,225));
		scrollPane.setBorder(BorderFactory.createEmptyBorder(0,0,0,0));
		
		
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
		this.setSize(1000,800);
		this.getContentPane().setBackground(new Color(255,228,225));
		this.setLayout(null);
		this.setLocationRelativeTo(null);
		this.setResizable(false);
		this.add(panel);
		this.add(scrollPane);
		this.add(combobox);
		this.add(course);
		this.add(add);
        this.add(update);
		this.add(delete);
		this.add(searchfield);
		this.add(title);
		this.setVisible(true);
		
		
		setCoursesTable();
		addDocumentListener();
		setDataFromDBtoTable();
		setComboboxItems();
		startTitleThread();
	}
	
	void startTitleThread(){
		titleThread = new Thread(new Runnable() {

			@Override
			public void run() {
				while(true) {
				String str = "Student Information System";
				
					
					for(int i=0;i<str.length();i++) {
						
						try {
							title.setText(title.getText()+str.charAt(i));
							Thread.sleep(150);
						} catch (InterruptedException e) {
							
							e.printStackTrace();
						}
					}
					title.setText("");
				}
				
			}
			
		});
		
		titleThread.start();
	}
	
	void addDocumentListener() {
		
       searchfield.getDocument().addDocumentListener(new DocumentListener() {
			
			void updateSearchResults() {
				String searchText = searchfield.getText();
				String tableName =(String)combobox.getSelectedItem();
				
				try {
					Connection conn = DriverManager.getConnection("jdbc:mysql://localhost/studentinfo","root","");
				    Statement stmt = conn.createStatement();
				    ResultSet rs = stmt.executeQuery("SELECT * FROM "+tableName+" WHERE (Firstname LIKE '%" +searchText+ "%' "
				    	+ "OR Lastname LIKE '%" +searchText+ "%' "
				    		+ "OR StudentID LIKE '%" +searchText+ "%' "
				    		+ "OR Year LIKE '%" +searchText+ "%' "
				    		+ "OR Section LIKE '%" +searchText+ "%' "
				    		+ "OR Age LIKE '%" +searchText+ "%' "
				    		+ "OR Sex LIKE '%" +searchText+ "%')");
				    
				    model.setRowCount(0);
				    
				    while(rs.next()) {
						model.addRow(new Object[] {
								rs.getString("StudentID"),
								rs.getString("Firstname"),
								rs.getString("Lastname"),
								rs.getString("Year"),
								rs.getString("Section"),
								rs.getString("Age"),
								rs.getString("Sex")});
					}
				    
				    rs.close();
				    stmt.close();
				    conn.close();
					
				}catch(SQLException e) {
					
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
	
	
	
	void setComboboxItems() {
		try {
			Connection Conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/studentinfo","root","");
			PreparedStatement pstmt = Conn.prepareStatement("SHOW TABLES");
			ResultSet rs = pstmt.executeQuery();
			while(rs.next()) {
				String tableName = rs.getString(1);
				combobox.addItem(tableName.toUpperCase());
			}
			
		}catch(SQLException e2) {
			e2.printStackTrace();
		}
		
	}
	
	void setDataFromDBtoTable() {
		try {
			Connection Conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/studentinfo","root","");
			String tablename = (String)combobox.getSelectedItem();
			PreparedStatement pstmt = Conn.prepareStatement("SELECT * FROM "+tablename);
			ResultSet rs = pstmt.executeQuery();
			model.setRowCount(0);
			while(rs.next()) {
				model.addRow(new Object[] {
						rs.getString("StudentID"),
						rs.getString("Firstname"),
						rs.getString("Lastname"),
						rs.getString("Year"),
						rs.getString("Section"),
						rs.getString("Age"),
						rs.getString("Sex")});
			}
			 rs.close();
			 pstmt.close();
			 Conn.close();
			
		}catch(SQLException e2) {
			//e2.printStackTrace();
		}
		
		
	}
	
	void setCoursesTable() {
		try {
			Connection Conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/studentinfo","root","");
			PreparedStatement pstmt = Conn.prepareStatement("SHOW TABLES");
			ResultSet rs = pstmt.executeQuery();
			model1.setRowCount(0);
			while(rs.next()) {
				String tableName = rs.getString(1).toUpperCase();
				PreparedStatement pstmt1 = Conn.prepareStatement("SELECT COUNT(*) FROM "+tableName);
				ResultSet rs1 = pstmt1.executeQuery();
				
				int count =0;
				if(rs1.next()) {
					count = rs1.getInt(1);
				}
				model1.addRow(new Object[] {tableName,Integer.toString(count)});
			}
			
		}catch(SQLException e2) {
			e2.printStackTrace();
		}

	}

	@Override
	public void actionPerformed(ActionEvent e) {
		
		if(e.getSource()==add) {
			
			//Check if student ID exist
			boolean exist = false;
			String tablename = (String)combobox.getSelectedItem();
			try {
				Connection Conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/studentinfo","root","");
				PreparedStatement pstmt = Conn.prepareStatement("SELECT * FROM "+tablename+" WHERE StudentID=?");
				pstmt.setString(1, studentID.getText());
				ResultSet rs = pstmt.executeQuery();
				if(rs.next()) {
					exist=true;
				}
			
			}catch(SQLException e2) {
				e2.printStackTrace();
			}
			
			
			
			if(exist) {
				JOptionPane.showMessageDialog(null, "Student ID exist");
				
			}else {
				
			if(!studentID.getText().equals("") && !firstname.getText().equals("") && !lastname.getText().equals("") 
					&& !year.getText().equals("")
					&& !section.getText().equals("") 
					&& !age.getText().equals("") 
					&& (male.isSelected() || female.isSelected())) {
			try {
				Connection Conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/studentinfo","root","");
				String tableName = (String)combobox.getSelectedItem();
				PreparedStatement pstmt = Conn.prepareStatement("INSERT INTO "+tableName+" "
						+ "(studentID, Firstname, Lastname, year, section, age, sex) "
						+ "VALUES (?,?,?,?,?,?,?)" );
				
				pstmt.setString(1, studentID.getText());
				pstmt.setString(2, firstname.getText());
				pstmt.setString(3, lastname.getText());
				pstmt.setInt(4, Integer.parseInt(year.getText()));
				pstmt.setString(5, section.getText());
				pstmt.setInt(6, Integer.parseInt(age.getText()));
				if(male.isSelected()) {
					pstmt.setString(7, "Male");
				}else {
					pstmt.setString(7, "Female");
				}
				
				pstmt.executeUpdate();
				
				setDataFromDBtoTable();
				
				setCoursesTable();
				
				
				 pstmt.close();
				 Conn.close();
				
			}catch(SQLException e2) {
				e2.printStackTrace();
			}catch(NumberFormatException e2) {
				JOptionPane.showMessageDialog(null, "The 'Year' and 'Age' should be an Integer","Invalid",JOptionPane.ERROR_MESSAGE);
			}
			
			studentID.setText("");
			firstname.setText("");
			lastname.setText("");
			year.setText("");
			section.setText("");
			age.setText("");
			
			
		}else {
			JOptionPane.showMessageDialog(null, "Fill the Blanks");
		}
			}
		}
		
		if(e.getSource()==update) {
			if(table.getSelectedRow()!=-1 && !studentID.getText().equals("")
					&& !firstname.getText().equals("") 
					&& !lastname.getText().equals("") 
					&& !year.getText().equals("")
					&& !section.getText().equals("") 
					&& !age.getText().equals("") 
					&& (male.isSelected() || female.isSelected())) {
				
				
				
				try {
					Connection Conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/studentinfo","root","");
					String tableName = (String)combobox.getSelectedItem();
					PreparedStatement pstmt = Conn.prepareStatement("UPDATE "+tableName
							+ " SET studentID=?, Firstname=?, Lastname=?, year=?, section=?, age=?, sex=? WHERE studentID=?");
					
					
					String sID = studentID.getText();
					String fName = firstname.getText();
					String lName = lastname.getText();
					int yr = Integer.parseInt(year.getText());
					String sec = section.getText();
					int a = Integer.parseInt(age.getText());
					
					pstmt.setString(1, sID );
					pstmt.setString(2, fName);
					pstmt.setString(3, lName);
					pstmt.setInt(4, yr);
					pstmt.setString(5, sec);
					pstmt.setInt(6, a);
					if(male.isSelected()) {
						pstmt.setString(7, "Male");
					}else {
						pstmt.setString(7, "Female");
					}
					pstmt.setString(8, sID);
					pstmt.executeUpdate();
					
					model.setValueAt(studentID.getText(), table.getSelectedRow() , 0);
					model.setValueAt(firstname.getText(), table.getSelectedRow() , 1);
					model.setValueAt(lastname.getText(), table.getSelectedRow() , 2);
					model.setValueAt(year.getText(), table.getSelectedRow() , 3);
					model.setValueAt(section.getText(), table.getSelectedRow() , 4);
					model.setValueAt(age.getText(), table.getSelectedRow() , 5);
					if(male.isSelected()) {
						model.setValueAt("Male", table.getSelectedRow(), 6);
					}else {
						model.setValueAt("Female", table.getSelectedRow(), 6);
					}
					 pstmt.close();
					 Conn.close();
					
				}catch(SQLException e2) {
					e2.printStackTrace();
				}catch(NumberFormatException e2) {
					JOptionPane.showMessageDialog(null, "The 'Year' and 'Age' should be an Integer","Invalid",JOptionPane.ERROR_MESSAGE);
				}
				
				studentID.setText("");
				firstname.setText("");
				lastname.setText("");
				year.setText("");
				section.setText("");
				age.setText("");
				
				
				
			}else if(studentID.getText().equals("")
					&& firstname.getText().equals("") 
					&& lastname.getText().equals("") 
					&& year.getText().equals("")
					&& section.getText().equals("") 
					&& age.getText().equals("")) {
				
				JOptionPane.showMessageDialog(null, "Fill the Blanks");
			}
			else {
				JOptionPane.showMessageDialog(null, "Please Select Row");
			}
		}
		
		
		if(e.getSource()==delete) {
			if(table.getSelectedRow()!=-1) {
			   
			   try {
					Connection Conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/studentinfo","root","");
					String tableName = (String)combobox.getSelectedItem();
					String sID = (String)model.getValueAt(table.getSelectedRow(), 0);
					PreparedStatement pstmt = Conn.prepareStatement("DELETE FROM "+tableName+" WHERE studentID=?");
					pstmt.setString(1, sID);
					pstmt.executeUpdate();
					
					model.removeRow(table.getSelectedRow());
					
					setCoursesTable();
					
					
					pstmt.close();
					Conn.close();
					
			   }catch(SQLException e2) {
					
				}
			   
			    studentID.setText("");
				firstname.setText("");
				lastname.setText("");
				year.setText("");
				section.setText("");
				age.setText("");
				
			}
			else {
				JOptionPane.showMessageDialog(null, "Please Select Row");
			}
		}
		
		
		if(e.getSource()==course) {
			studentID.setVisible(false);
			studentIDlbl.setVisible(false);;
			firstname.setVisible(false);;
			firstnamelbl.setVisible(false);;
			lastname.setVisible(false);;
			lastnamelbl.setVisible(false);;
			year.setVisible(false);;
			yearlbl.setVisible(false);;
			section.setVisible(false);;
			sectionlbl.setVisible(false);;
			age.setVisible(false);;
			agelbl.setVisible(false);;
			male.setVisible(false);;
			female.setVisible(false);;
			sexlbl.setVisible(false);;
			
			panel.add(back).setVisible(true);
			panel.add(scrollPane1).setVisible(true);
			panel.add(addCourse).setVisible(true);
			panel.add(delCourse).setVisible(true);
			panel.repaint();
			  
		}
		
		
		if(e.getSource()==combobox) {
			studentID.setText("");
			firstname.setText("");
			lastname.setText("");
			year.setText("");
			section.setText("");
			age.setText("");
			setDataFromDBtoTable();
		}
		
		
		if(e.getSource()==addCourse) {
			boolean exist = false;
			  
			  
			  try {
				 String courseName = JOptionPane.showInputDialog(null,"Enter Course");
				  
				 Connection Conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/studentinfo","root","");
				 PreparedStatement stmt = Conn.prepareStatement("SHOW TABLES");
				 ResultSet rs = stmt.executeQuery();
				 
				 while(rs.next()) {
					 if(courseName.equalsIgnoreCase(rs.getString(1))) {
						 JOptionPane.showMessageDialog(null, "Course Already Exist","",JOptionPane.ERROR_MESSAGE);
						 exist=true;
						 break;
					 }
				 }
				 
				 
				if(!exist) {
				 PreparedStatement pstmt = Conn.prepareStatement("CREATE TABLE "+courseName
						 +" (StudentID varchar(20) NOT NULL, "
						 + "Firstname varchar(30), "
						 + "Lastname varchar(30), "
						 + "Year int, "
						 + "Section varchar(20), "
						 + "Age int, "
						 + "Sex varchar(10), "
						 + "PRIMARY KEY (StudentID))");
				 pstmt.executeUpdate();
				 
				 combobox.addItem(courseName.toUpperCase());
					
				 JOptionPane.showMessageDialog(null, "Course Successfully Added"); 
				 
				}
				
				setCoursesTable();
				
				 rs.close();
				 stmt.close();
				 Conn.close();
				
				
			  }catch(SQLException e2) {
					e2.printStackTrace();
			  }catch(NullPointerException e2) {
				  //e2.printStackTrace();
			  }
		}
		
		
		if(e.getSource()==delCourse) {
				if(table1.getSelectedRow()!=-1) {
					String tableName = (String)model1.getValueAt(table1.getSelectedRow(), 0);
					
					try {
						 Connection Conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/studentinfo","root","");
						 PreparedStatement pstmt = Conn.prepareStatement("DROP TABLE "+tableName);
						 pstmt.executeUpdate();
						 
						 setCoursesTable();						 
						 combobox.removeAllItems();
						 setComboboxItems();
						 
						 
						
					}catch(SQLException e2) {
						//e2.printStackTrace();
					}
					
					
				}else {
					JOptionPane.showMessageDialog(null, "Please Select Row");
				}
				
		}
		
		if(e.getSource()==back) {
			studentID.setVisible(true);
			studentIDlbl.setVisible(true);;
			firstname.setVisible(true);;
			firstnamelbl.setVisible(true);;
			lastname.setVisible(true);;
			lastnamelbl.setVisible(true);;
			year.setVisible(true);;
			yearlbl.setVisible(true);;
			section.setVisible(true);;
			sectionlbl.setVisible(true);;
			age.setVisible(true);;
			agelbl.setVisible(true);;
			male.setVisible(true);;
			female.setVisible(true);;
			sexlbl.setVisible(true);;
			back.setVisible(false);
			scrollPane1.setVisible(false);
			addCourse.setVisible(false);
			delCourse.setVisible(false);
			panel.repaint();
		}
		
	}	

}