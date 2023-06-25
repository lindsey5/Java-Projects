package AdminPage;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Image;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.Month;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.SwingConstants;
import javax.swing.table.DefaultTableModel;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.CategoryPlot;
import org.jfree.chart.plot.PiePlot;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.chart.renderer.category.BarRenderer;
import org.jfree.chart.renderer.category.StandardBarPainter;
import org.jfree.chart.title.TextTitle;
import org.jfree.data.category.DefaultCategoryDataset;
import org.jfree.data.general.DefaultPieDataset;

import ObjectsAndInterfaces.DarkTable;
import ObjectsAndInterfaces.RoundRectPanel;

public class Reports extends JPanel {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	DefaultTableModel model = new DefaultTableModel();
	DarkTable table = new DarkTable(model);
	JScrollPane scrollPane = new JScrollPane(table);
	JLabel titlelbl;
	RoundRectPanel daySales, itemSalesPanel;
	JLabel daySaleslbl;
	JLabel daySalesNo;
	JButton printReport;
	
	Reports(){
		
		printReport = new JButton("Print Daily Report");
		printReport.setBounds(75,200,150,50);
		printReport.setBackground(Color.BLACK);
		printReport.setForeground(Color.WHITE);
		printReport.addActionListener((e)->{ printDailyReport(); });
				
		ImageIcon icon2 = new ImageIcon("res/sales.png");
		Image img2 = icon2.getImage().getScaledInstance(50,50, java.awt.Image.SCALE_AREA_AVERAGING);
		icon2 = new ImageIcon(img2);
		
		daySalesNo = new JLabel();
		daySalesNo.setBounds(0, 120, 300, 50);
		daySalesNo.setForeground(Color.WHITE);
		daySalesNo.setHorizontalAlignment(SwingConstants.CENTER);
		daySalesNo.setFont(new Font("Callibri",Font.BOLD,30));
		
		daySaleslbl = new JLabel();
		daySaleslbl.setText("Total Sales this Day");
		daySaleslbl.setIcon(icon2);
		daySaleslbl.setForeground(Color.WHITE);
		daySaleslbl.setFont(new Font("Callibri",Font.BOLD,17));
		daySaleslbl.setHorizontalTextPosition(JLabel.CENTER);
		daySaleslbl.setVerticalTextPosition(JLabel.BOTTOM);
		daySaleslbl.setBounds(70, 30, 170, 80);
		
	    daySales = new RoundRectPanel(30,30);
	    daySales.setLayout(null);
		daySales.setPreferredSize(new Dimension(300,300));
		daySales.setBackground(new Color(32,32,32));
		daySales.add(daySaleslbl);
		daySales.add(daySalesNo);
		daySales.add(printReport);
		
		model.addColumn("Item Name");
		model.addColumn("Quantity");
		model.addColumn("Unit Price");
		model.addColumn("Size");
		model.addColumn("Category");
		model.addColumn("Total Price");
		
        scrollPane.setPreferredSize(new Dimension(870,300));
		scrollPane.getViewport().setBackground(new Color(32,32,32));
		table.fixTable(scrollPane);
		
		titlelbl = new JLabel("Items Sold Today");
		titlelbl.setForeground(Color.WHITE);
		titlelbl.setPreferredSize(new Dimension(300,50));
		titlelbl.setFont(new Font("Arial",Font.BOLD,25));
		
		itemSalesPanel = new RoundRectPanel(30,30);
		itemSalesPanel.setPreferredSize(new Dimension(900,400));
		itemSalesPanel.setLayout(new FlowLayout(FlowLayout.LEFT,15,20));
		itemSalesPanel.setBackground(new Color(32,32,32));
		itemSalesPanel.add(titlelbl);
		itemSalesPanel.add(scrollPane);
		
		this.setLayout(new FlowLayout(FlowLayout.CENTER,60,40));
		this.setBackground(new Color(64,64,64));
		
		this.add(itemSalesPanel);
		displayTodaySales();
		setTodayDataToTable();
		createPieChart();
		this.add(daySales);
		createBarGraph();
	}
	
	private void displayTodaySales() {
		//Compute the total sales this day
		try {
			
			Connection conn=DriverManager.getConnection("jdbc:mysql://localhost:3306/foodsystemdb","root","");
			PreparedStatement pstmt = conn.prepareStatement("SELECT * FROM orderhistory WHERE date=?");
			pstmt.setDate(1,java.sql.Date.valueOf(LocalDate.now()));
			double total = 0;
			ResultSet rs = pstmt.executeQuery();
			while(rs.next()) {
				double currenttotal = rs.getDouble("total");
				total+=currenttotal;
			}
			
			daySalesNo.setText(total+"");
			
			
		}catch(SQLException e) {
			e.printStackTrace();
		}
	}
	
	private void setTodayDataToTable() {
		model.setRowCount(0);
		try {
			Connection conn = DriverManager.getConnection("jdbc:mysql://localhost/foodsystemdb","root","");
		    PreparedStatement stmt = conn.prepareStatement("SELECT * FROM productsales WHERE date=?");
		    stmt.setDate(1,java.sql.Date.valueOf(LocalDate.now()) );
		    ResultSet rs = stmt.executeQuery();
		    
		    while(rs.next()) {
				model.addRow(new Object[] {
						rs.getString("itemname"),
						rs.getString("quantity"),
						rs.getString("unitprice"),
						rs.getString("size"),
						rs.getString("category"),
						rs.getString("total")});
			} 
			
		}catch(SQLException e) {
			e.printStackTrace();
		}
	}
	
	
	private void createPieChart() {
	    DefaultPieDataset dataset = new DefaultPieDataset();

	    try {
	        LocalDate currentDate = LocalDate.now();
	        Month currentMonth = currentDate.getMonth();
	        int monthValue = currentMonth.getValue();
	        int year = LocalDate.now().getYear();

	        Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/foodsystemdb", "root", "");
	        PreparedStatement pstmt = conn.prepareStatement("SELECT itemname, SUM(quantity) AS total_quantity FROM productsales WHERE MONTH(date) = ? AND YEAR(date) = ? GROUP BY itemname ORDER BY total_quantity DESC LIMIT 5");
	        pstmt.setInt(1, monthValue);
	        pstmt.setInt(2, year);
	        ResultSet rs = pstmt.executeQuery();

	        while (rs.next()) {
	            String itemName = rs.getString("itemname");
	            int quantity = rs.getInt("total_quantity");
	            dataset.setValue(itemName, quantity);
	        }

	    } catch (SQLException e) {
	        e.printStackTrace();
	    }

	    JFreeChart chart = ChartFactory.createPieChart("Top Selling Items This Month", dataset);
	    chart.setBackgroundPaint(new Color(32,32,32));
	    chart.getTitle().setPaint(Color.WHITE);

	    PiePlot plot = (PiePlot) chart.getPlot();
	    plot.setBackgroundPaint(new Color(32,32,32));
	    plot.setLabelFont(new Font("Arial", Font.BOLD, 12));
	    plot.setLabelPaint(Color.BLACK);
	    plot.setOutlineStroke(null);

	    // Create a ChartPanel to display the chart
	    ChartPanel chartPanel = new ChartPanel(chart);
	    chartPanel.setPreferredSize(new Dimension(300,400));
	    this.add(chartPanel);
	}
	
	private void createBarGraph() {
		
	    DefaultCategoryDataset dataset = new DefaultCategoryDataset();
	    
	    try {
	    	LocalDate startDate = LocalDate.now().minusDays(7);
	        Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/foodsystemdb", "root", "");
	        PreparedStatement pstmt = conn.prepareStatement("SELECT date, SUM(total) AS total_ammount FROM orderhistory WHERE date >= ? GROUP BY date ORDER BY date ASC LIMIT 7");
	        pstmt.setDate(1, java.sql.Date.valueOf(startDate));
	        ResultSet rs = pstmt.executeQuery();
	        while (rs.next()) {
	        	double totalSales = rs.getDouble("total_ammount");
	        	String date = rs.getString("date");
	            dataset.addValue(totalSales, "Sales",date);
	        }

	        conn.close();
	    } catch (SQLException e) {
	        e.printStackTrace();
	    }

	    JFreeChart chart = ChartFactory.createBarChart(
	        "Sales Status",// Chart title
	        "",// X-axis label
	        "Sales",// Y-axis label
	        dataset, // Dataset
	        PlotOrientation.VERTICAL,
	        false,
	        true,
	        false
	    );
	    chart.setBackgroundPaint(new Color(54,50,50));

	    CategoryPlot plot = chart.getCategoryPlot();
	    plot.getRenderer().setSeriesPaint(0, Color.GREEN);
	    BarRenderer renderer = (BarRenderer) plot.getRenderer();
	    //renderer.setShadowVisible(false);
	    renderer.setBarPainter(new StandardBarPainter());
	    plot.setBackgroundPaint(new Color(54,50,50));
	    plot.getRangeAxis().setTickLabelPaint(Color.WHITE);
	    plot.getRangeAxis().setLabelPaint(Color.WHITE);
	    plot.getDomainAxis().setTickLabelPaint(Color.WHITE);
	    //plot.getDomainAxis().setLabelFont(new Font("Arial",Font.BOLD,15));
	    plot.getDomainAxis().setLabelPaint(Color.WHITE);
	    
	    TextTitle title = chart.getTitle();
	    title.setPaint(Color.WHITE);
	    title.setFont(new Font("Arial",Font.BOLD,20));

	    // Create a ChartPanel to display the chart
	    ChartPanel chartPanel = new ChartPanel(chart);
	    chartPanel.setPreferredSize(new Dimension(600, 300));
	    
	    // Add the chart panel to your existing layout
	    this.add(chartPanel);
	}
	
	private void printDailyReport() {
		
	    JFrame frame = new JFrame();
	    frame.setResizable(false);
	    JTextArea textarea = new JTextArea() {
	    	
	    	/**
			 * 
			 */
			private static final long serialVersionUID = 1L;

			@Override
			public void paint(Graphics g) {
	    		super.paintComponent(g);
	    		g.setColor(Color.BLACK);
	    		g.setFont(new Font("Arial",Font.BOLD,20));
	    		g.drawString("Daily Sales Report", 120, 40);
	    		
	    	}
	    };
	 
	    StringBuilder str = new StringBuilder();
	    try {
	    	Connection conn=DriverManager.getConnection("jdbc:mysql://localhost:3306/foodsystemdb","root","");
			PreparedStatement pstmt = conn.prepareStatement("SELECT * FROM productsales WHERE date=?");
			pstmt.setDate(1, java.sql.Date.valueOf(LocalDate.now()));
			ResultSet rs = pstmt.executeQuery();
			str.append("--------------------------------------------------------------------------------------------------------\n");
			str.append("\n");
			str.append("\n");
			str.append("\n");
			str.append("--------------------------------------------------------------------------------------------------------\n");
	        str.append("\t                          The Hungry Fork\n");
	        str.append("                               Lower Bicutan,Taguig City, Metro Manila\n");
	        str.append("                                    www.facebook.com/HAHAHAHAHA\n");
	        str.append("                                                     12345678\n");
			str.append("--------------------------------------------------------------------------------------------------------\n");
			str.append("  Name               Quantity               UnitPrice             Size                      TotalPrice\n\n");
			str.append("\n");
			double totalPrice=0;
			while(rs.next()) {
				int count = 0;
				String itemname = rs.getString("itemname");
				int quantity = rs.getInt("quantity");
				double unitPrice = rs.getDouble("unitPrice");
				String size = rs.getString("Size");
				double total = rs.getDouble("total");
				
				totalPrice+=total;
				
				for (int j = 0; j <itemname.length(); j++) {
			        str.append(itemname.charAt(j));

			        if (itemname.charAt(j) == ' ') {
			            break;
			        } else {
			            count++;
			        }
			    }	
				
				    str.append("\t"+quantity);
				    str.append("\t"+unitPrice);
				    str.append("\t"+size);
					str.append("\t"+total);
					str.append("\n");
				if(count < itemname.length() && itemname.charAt(count) == ' ') {
					for (int j = count + 1; j < itemname.length(); j++) {
						str.append(itemname.charAt(j));
					}
			    }
				
				    str.append("\n");
				    str.append("\n");
				
			}
			
			str.append("--------------------------------------------------------------------------------------------------------\n");
			str.append("  Total Sales This Day:\t"+totalPrice+"\n");
			str.append("--------------------------------------------------------------------------------------------------------\n");
			str.append(" Date: "+LocalDate.now()+"\t"+"Time: "+java.sql.Time.valueOf(LocalTime.now())+"\n");
			str.append("--------------------------------------------------------------------------------------------------------\n");
			
			textarea.setText(str.toString());
			textarea.setEditable(false);
			frame.add(textarea);
			frame.pack();
		    frame.setLocationRelativeTo(null);
			frame.setVisible(true);
			
			
	    }catch(SQLException e) {
	    	e.printStackTrace();
	    }   
	    
	}

}
