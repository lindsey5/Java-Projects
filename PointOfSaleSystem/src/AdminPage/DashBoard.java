package AdminPage;

import java.awt.Color;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.CategoryPlot;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.chart.renderer.category.BarRenderer;
import org.jfree.chart.renderer.category.StandardBarPainter;
import org.jfree.chart.title.TextTitle;
import org.jfree.data.category.DefaultCategoryDataset;

import EventHandler.MouseInputs;
import ObjectsAndInterfaces.RoundRectPanel;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.Image;
import java.sql.*;
import java.time.LocalDate;
import java.time.Month;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;

public class DashBoard extends JPanel{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	RoundRectPanel totalAcc;
	JLabel totalAcclbl;
	JLabel totalAccNo;
	RoundRectPanel Items;
	JLabel Itemslbl;
	JLabel ItemsNo;
	RoundRectPanel monthSales;
	JLabel monthSaleslbl;
	JLabel monthSalesNo;
	RoundRectPanel weekSales;
	JLabel weekSaleslbl;
	JLabel weekSalesNo;
	RoundRectPanel daySales;
	JLabel daySaleslbl;
	JLabel daySalesNo;
	RoundRectPanel totalOrders;
	JLabel totalOrderslbl;
	JLabel totalOrdersNo;
	

	DashBoard(){		
		
		ImageIcon icon2 = new ImageIcon("res/sales.png");
		Image img2 = icon2.getImage().getScaledInstance(40,40, java.awt.Image.SCALE_AREA_AVERAGING);
		icon2 = new ImageIcon(img2);
		
		totalOrdersNo = new JLabel();
		totalOrdersNo.setBounds(0, 80, 300, 50);
		totalOrdersNo.setForeground(Color.WHITE);
		totalOrdersNo.setHorizontalAlignment(SwingConstants.CENTER);
		totalOrdersNo.setFont(new Font("Callibri",Font.BOLD,30));
		
		totalOrderslbl = new JLabel();
		totalOrderslbl.setText("Transactions for the Last 7 Days");
		totalOrderslbl.setIcon(icon2);
		totalOrderslbl.setForeground(Color.WHITE);
		totalOrderslbl.setFont(new Font("Callibri",Font.BOLD,17));
		totalOrderslbl.setHorizontalTextPosition(JLabel.CENTER);
		totalOrderslbl.setVerticalTextPosition(JLabel.BOTTOM);
		totalOrderslbl.setHorizontalAlignment(SwingConstants.CENTER);
		totalOrderslbl.setBounds(0, 10, 300, 80);
		
	    totalOrders = new RoundRectPanel(30,30);
	    totalOrders.setLayout(null);
		totalOrders.setPreferredSize(new Dimension(300,150));
		totalOrders.setBackground(new Color(32,32,32));
		totalOrders.add(totalOrderslbl);
		totalOrders.add(totalOrdersNo);
		totalOrders.addMouseListener(new MouseInputs(totalOrders,new Color(32,32,32),Color.GRAY));
		
		
		daySalesNo = new JLabel();
		daySalesNo.setBounds(0, 80, 300, 50);
		daySalesNo.setForeground(Color.WHITE);
		daySalesNo.setHorizontalAlignment(SwingConstants.CENTER);
		daySalesNo.setFont(new Font("Callibri",Font.BOLD,30));
		
		
		daySaleslbl = new JLabel();
		daySaleslbl.setText("Sales this Day");
		daySaleslbl.setIcon(icon2);
		daySaleslbl.setForeground(Color.WHITE);
		daySaleslbl.setFont(new Font("Callibri",Font.BOLD,17));
		daySaleslbl.setHorizontalTextPosition(JLabel.CENTER);
		daySaleslbl.setVerticalTextPosition(JLabel.BOTTOM);
		daySaleslbl.setBounds(90, 10, 170, 80);
		
	    daySales = new RoundRectPanel(30,30);
	    daySales.setLayout(null);
		daySales.setPreferredSize(new Dimension(300,150));
		daySales.setBackground(new Color(32,32,32));
		daySales.add(daySaleslbl);
		daySales.add(daySalesNo);
		daySales.addMouseListener(new MouseInputs(daySales,new Color(32,32,32),Color.GRAY));
		
		weekSalesNo = new JLabel();
		weekSalesNo.setBounds(0, 80, 300, 50);
		weekSalesNo.setForeground(Color.WHITE);
		weekSalesNo.setHorizontalAlignment(SwingConstants.CENTER);
		weekSalesNo.setFont(new Font("Callibri",Font.BOLD,30));
		
		
		weekSaleslbl = new JLabel();
		weekSaleslbl.setText("Sales for the Last 7 Days");
		weekSaleslbl.setIcon(icon2);
		weekSaleslbl.setForeground(Color.WHITE);
		weekSaleslbl.setFont(new Font("Callibri",Font.BOLD,17));
		weekSaleslbl.setHorizontalTextPosition(JLabel.CENTER);
		weekSaleslbl.setVerticalTextPosition(JLabel.BOTTOM);
		weekSaleslbl.setBounds(50, 10, 200, 80);
		
	    weekSales = new RoundRectPanel(30,30);
	    weekSales.setLayout(null);
		weekSales.setPreferredSize(new Dimension(300,150));
		weekSales.setBackground(new Color(32,32,32));
		weekSales.add(weekSaleslbl);
		weekSales.add(weekSalesNo);
		weekSales.addMouseListener(new MouseInputs(weekSales,new Color(32,32,32),Color.GRAY));
		
		monthSalesNo = new JLabel();
		monthSalesNo.setBounds(0, 80, 300, 50);
		monthSalesNo.setForeground(Color.WHITE);
		monthSalesNo.setHorizontalAlignment(SwingConstants.CENTER);
		monthSalesNo.setFont(new Font("Callibri",Font.BOLD,30));
		
		
		monthSaleslbl = new JLabel();
		monthSaleslbl.setText("Sales this Month");
		monthSaleslbl.setIcon(icon2);
		monthSaleslbl.setForeground(Color.WHITE);
		monthSaleslbl.setFont(new Font("Callibri",Font.BOLD,17));
		monthSaleslbl.setHorizontalTextPosition(JLabel.CENTER);
		monthSaleslbl.setVerticalTextPosition(JLabel.BOTTOM);
		monthSaleslbl.setBounds(85, 10, 170, 80);
		
	    monthSales = new RoundRectPanel(30,30);
	    monthSales.setLayout(null);
		monthSales.setPreferredSize(new Dimension(300,150));
		monthSales.setBackground(new Color(32,32,32));
		monthSales.add(monthSaleslbl);
		monthSales.add(monthSalesNo);
		monthSales.addMouseListener(new MouseInputs(monthSales,new Color(32,32,32),Color.GRAY));
		
		ItemsNo = new JLabel("1");
		ItemsNo.setBounds(0, 80, 300, 50);
		ItemsNo.setForeground(Color.WHITE);
		ItemsNo.setHorizontalAlignment(SwingConstants.CENTER);
		ItemsNo.setFont(new Font("Callibri",Font.BOLD,30));
		
		ImageIcon icon1 = new ImageIcon("res/menu (1).png");
		Image img1 = icon1.getImage().getScaledInstance(40,40, java.awt.Image.SCALE_AREA_AVERAGING);
		icon1 = new ImageIcon(img1);
		Itemslbl = new JLabel();
		Itemslbl.setText("Total Items");
		Itemslbl.setIcon(icon1);
		Itemslbl.setForeground(Color.WHITE);
		Itemslbl.setFont(new Font("Callibri",Font.BOLD,17));
		Itemslbl.setHorizontalTextPosition(JLabel.CENTER);
		Itemslbl.setVerticalTextPosition(JLabel.BOTTOM);
		Itemslbl.setBounds(105, 10, 150, 80);
		
	    Items = new RoundRectPanel(30,30);
	    Items.setLayout(null);
		Items.setPreferredSize(new Dimension(300,150));
		Items.setBackground(new Color(32,32,32));
		Items.add(Itemslbl);
		Items.add(ItemsNo);
		Items.addMouseListener(new MouseInputs(Items,new Color(32,32,32),Color.GRAY));
		
		totalAccNo = new JLabel();
		totalAccNo.setBounds(0, 80, 300, 50);
		totalAccNo.setForeground(Color.WHITE);
		totalAccNo.setHorizontalAlignment(SwingConstants.CENTER);
		totalAccNo.setFont(new Font("Callibri",Font.BOLD,30));
		
		ImageIcon icon = new ImageIcon("res/user (1).png");
		Image img = icon.getImage().getScaledInstance(40,40, java.awt.Image.SCALE_AREA_AVERAGING);
		icon = new ImageIcon(img);
		totalAcclbl = new JLabel();
		totalAcclbl.setText("Total Accounts");
		totalAcclbl.setIcon(icon);
		totalAcclbl.setForeground(Color.WHITE);
		totalAcclbl.setFont(new Font("Callibri",Font.BOLD,17));
		totalAcclbl.setHorizontalTextPosition(JLabel.CENTER);
		totalAcclbl.setVerticalTextPosition(JLabel.BOTTOM);
		totalAcclbl.setBounds(90, 10, 150, 80);
		
	    totalAcc = new RoundRectPanel(30,30);
	    totalAcc.setLayout(null);
		totalAcc.setPreferredSize(new Dimension(300,150));
		totalAcc.setBackground(new Color(32,32,32));
		totalAcc.add(totalAcclbl);
		totalAcc.add(totalAccNo);
		totalAcc.addMouseListener(new MouseInputs(totalAcc,new Color(32,32,32),Color.GRAY));
		
		this.setLayout(new FlowLayout(FlowLayout.CENTER,65,40));
		this.add(totalAcc);
	    this.add(Items);
		this.add(monthSales);
		this.add(weekSales);
		this.add(daySales);
		this.add(totalOrders);
		this.setBackground(Color.DARK_GRAY);
		setData();
		createBarGraph();
		
	}
	
	
	
	//Retrieve the data from MySQL database then display
	void setData() {
		
		//Retrieve the number of accounts in the database
		try {
			Connection conn=DriverManager.getConnection("jdbc:mysql://localhost:3306/foodsystemdb","root","");
			PreparedStatement pstmt = conn.prepareStatement("SELECT COUNT(*) FROM accounttable");
		
			ResultSet rs = pstmt.executeQuery();
		
			if(rs.next()) {
				int count = rs.getInt(1);
				totalAccNo.setText(Integer.toString(count));
			}
		}catch(SQLException e) {
			e.printStackTrace();
		}	
	
		//Retrieve the number of items from menu in the database
		try {
			Connection conn=DriverManager.getConnection("jdbc:mysql://localhost:3306/foodsystemdb","root","");
			PreparedStatement pstmt = conn.prepareStatement("SELECT COUNT(*) FROM menutable");
		
			ResultSet rs = pstmt.executeQuery();
		
			if(rs.next()) {
				int count = rs.getInt(1);
				ItemsNo.setText(Integer.toString(count));
			}
			
		}catch(SQLException e) {
			e.printStackTrace();
		}
	
		//Compute the total sales this month
		try {
			LocalDate currentDate = LocalDate.now();
			Month currentMonth = currentDate.getMonth();
			int monthValue = currentMonth.getValue();
		
			int year = LocalDate.now().getYear();
		 
			Connection conn=DriverManager.getConnection("jdbc:mysql://localhost:3306/foodsystemdb","root","");
			PreparedStatement pstmt = conn.prepareStatement("SELECT SUM(total) AS total_ammount FROM orderhistory WHERE MONTH(date)=? and YEAR(date)=?");
			pstmt.setInt(1,monthValue);
			pstmt.setInt(2, year);
			double total = 0;
			ResultSet rs = pstmt.executeQuery();
			if(rs.next()) {
				total = rs.getDouble("total_ammount");
			}
		
			startLabelThread(total,monthSalesNo);
		
		}catch(SQLException e) {
			e.printStackTrace();
		}
	
		//Compute the total sales this week
		try {
			LocalDate currentDate = LocalDate.now();
			LocalDate startDate = currentDate.minusDays(7);
			Connection conn=DriverManager.getConnection("jdbc:mysql://localhost:3306/foodsystemdb","root","");
			PreparedStatement pstmt = conn.prepareStatement("SELECT SUM(total) AS total_ammount FROM orderhistory WHERE date BETWEEN ? AND ? ");
			pstmt.setDate(1, java.sql.Date.valueOf(startDate));
			pstmt.setDate(2, java.sql.Date.valueOf(currentDate));
			double total = 0;
			ResultSet rs = pstmt.executeQuery();
			if(rs.next()) {
				total = rs.getDouble("total_ammount");
			}
		
			startLabelThread(total,weekSalesNo);
		
		}catch(SQLException e) {
			e.printStackTrace();
		}
	
		//Compute the total sales this day
		try {
		
			Connection conn=DriverManager.getConnection("jdbc:mysql://localhost:3306/foodsystemdb","root","");
			PreparedStatement pstmt = conn.prepareStatement("SELECT SUM(total) As total_ammount FROM orderhistory WHERE date=?");
			pstmt.setDate(1,java.sql.Date.valueOf(LocalDate.now()));
			double total = 0;
			ResultSet rs = pstmt.executeQuery();
			if(rs.next()) {
				total = rs.getDouble("total_ammount");
			}
		
		startLabelThread(total,daySalesNo);
		
		
		}catch(SQLException e) {
			e.printStackTrace();
		}
	
		try {
			LocalDate currentDate = LocalDate.now();
			Month currentMonth = currentDate.getMonth();
			int monthValue = currentMonth.getValue();
		
			int year = LocalDate.now().getYear();
			Connection conn=DriverManager.getConnection("jdbc:mysql://localhost:3306/foodsystemdb","root","");
			PreparedStatement pstmt = conn.prepareStatement("SELECT COUNT(*) FROM orderhistory WHERE MONTH(date)=? and YEAR(date)=?");
			pstmt.setInt(1, monthValue);
			pstmt.setInt(2, year);
			ResultSet rs = pstmt.executeQuery();
		
			if(rs.next()) {
				int count = rs.getInt(1);
				totalOrdersNo.setText(Integer.toString(count));
			}
		}catch(SQLException e) {
			e.printStackTrace();
		}
	}
	
	void startLabelThread(double total,JLabel label) {
		
		Thread thread = new Thread(new Runnable() {

			@Override
			public void run() {
				double counter = 1;
				while(counter <total) {
				label.setText("P- "+counter);
				counter +=200;
				try {
					Thread.sleep(50);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				}
				
				label.setText("P- "+total);
			}
			
		});
		
		thread.start();
	}
	
	
	
	private void createBarGraph() {
		
		//Add months to the data set 
	    DefaultCategoryDataset dataset = new DefaultCategoryDataset();
	    for (int i = 1; i <= 12; i++) {
	        String month = getMonthLabel(i);
	        dataset.addValue(0, "Sales", month);
	    }
	    
	    try {
	        Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/foodsystemdb", "root", "");
	        PreparedStatement pstmt = conn.prepareStatement("SELECT MONTH(date), total FROM orderhistory WHERE YEAR(date)=?");
	        pstmt.setInt(1, LocalDate.now().getYear()); 
	        ResultSet rs = pstmt.executeQuery();

	        while (rs.next()) {
	            int month = rs.getInt("MONTH(date)");  // Assuming the retrieved month is an integer
	            double sales = rs.getDouble("total");  // Assuming the retrieved sales value is a double
	            String monthLabel = getMonthLabel(month);  // Function to convert month number to label

	            double currentSales = dataset.getValue("Sales", monthLabel).doubleValue();
	            double totalSales = currentSales + sales;
	            dataset.setValue(totalSales, "Sales", monthLabel);  // Update the sales value in the dataset
	            
	        }

	        conn.close();
	    } catch (SQLException e) {
	        e.printStackTrace();
	    }

	    JFreeChart chart = ChartFactory.createBarChart(
	        "Monthly Sales",    // Chart title
	        "Month",            // X-axis label
	        "Sales",            // Y-axis label
	        dataset,            // Dataset
	        PlotOrientation.VERTICAL,
	        false,
	        true,
	        false
	    );
	    chart.setBackgroundPaint(new Color(54,50,50));

	    CategoryPlot plot = chart.getCategoryPlot();
	    plot.getRenderer().setSeriesPaint(0, Color.GREEN);
	    BarRenderer renderer = (BarRenderer) plot.getRenderer();
	   // renderer.setShadowVisible(false);
	    renderer.setBarPainter(new StandardBarPainter());
	    plot.setBackgroundPaint(new Color(54,50,50));
	    plot.getRangeAxis().setTickLabelPaint(Color.WHITE);
	    plot.getRangeAxis().setLabelPaint(Color.WHITE);
	    plot.getDomainAxis().setTickLabelPaint(Color.WHITE);
	    plot.getDomainAxis().setLabelPaint(Color.WHITE);
	    
	    TextTitle title = chart.getTitle();
	    title.setPaint(Color.WHITE);

	    // Create a ChartPanel to display the chart
	    ChartPanel chartPanel = new ChartPanel(chart);
	    chartPanel.setPreferredSize(new Dimension(1000, 350));
	    
	   
	    // Add the chart panel to your existing layout
	    this.add(chartPanel);
	}

	// Function to convert month number to label (e.g., 1 -> "January")
	private String getMonthLabel(int month) {
	    switch (month) {
	        case 1:
	            return "January";
	        case 2:
	            return "February";
	        case 3:
	            return "March";
	        case 4:
	            return "April";
	        case 5:
	            return "May";
	        case 6:
	            return "June";
	        case 7:
	            return "July";
	        case 8:
	            return "August";
	        case 9:
	            return "September";
	        case 10:
	            return "October";
	        case 11:
	            return "November";
	        case 12:
	            return "December";
	    }
	    return "";
	}
	
	
}

