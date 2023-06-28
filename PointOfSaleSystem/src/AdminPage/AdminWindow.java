package AdminPage;

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
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;

import ObjectsAndInterfaces.AbstractWindow;
import ObjectsAndInterfaces.CategoryButton;
import login.AdminLoginPage;

public class AdminWindow extends AbstractWindow implements ActionListener{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private JLabel adminlbl;
	private JPanel panel;
	private JButton button, dashboard, menu, history, reports, logout;
	
	private DashBoard dashBoard = new DashBoard();
	private AdminMenuPage mealsMenu = new AdminMenuPage(dashBoard);
	private AdminMenuPage drinksMenu = new AdminMenuPage(dashBoard);
	private AdminMenuPage sidesMenu = new AdminMenuPage(dashBoard);
	private AdminMenuPage dessertsMenu = new AdminMenuPage(dashBoard);
	private AdminMenuPage combosMenu = new AdminMenuPage(dashBoard);
	private HistoryPage historypage = new HistoryPage();
	private Reports reportPage = new Reports();
	
	private CategoryButton meals,drinks,sides,desserts,combo;
	private boolean isSelected = false;//This is to show and hide category buttons
	private boolean clicked = false; //This is to increased and decreased the width of rightPanel
	
	
	public AdminWindow(){
		
		mealsMenu.displayMenu("Meal");
		drinksMenu.displayMenu("Drink");
		sidesMenu.displayMenu("Side");
		dessertsMenu.displayMenu("Dessert");
		combosMenu.displayMenu("Combo");
		
		meals = new CategoryButton("Meals");
		meals.setPreferredSize(new Dimension(200-1,40));
		meals.setFont(new Font("Callibri",Font.BOLD,13));
		meals.setHorizontalAlignment(SwingConstants.CENTER);
		meals.setForeground(Color.WHITE);
		meals.setBackground(new Color(54,50,50));
		meals.setBorder(null);
		meals.addActionListener(this);
		
		drinks = new CategoryButton("Drinks");
		drinks.setPreferredSize(new Dimension(200-1,40));
		drinks.setFont(new Font("Callibri",Font.BOLD,13));
		drinks.setHorizontalAlignment(SwingConstants.CENTER);
		drinks.setForeground(Color.WHITE);
		drinks.setBackground(new Color(54,50,50));
		drinks.setBorder(null);
		drinks.addActionListener(this);
		
		sides = new CategoryButton("Sides") ;
		sides.setPreferredSize(new Dimension(200-1,40));
		sides.setFont(new Font("Callibri",Font.BOLD,13));
		sides.setHorizontalAlignment(SwingConstants.CENTER);
		sides.setForeground(Color.WHITE);
		sides.setBackground(new Color(54,50,50));
		sides.setBorder(null);
		sides.addActionListener(this);
		
		desserts = new CategoryButton("Desserts");
		desserts.setPreferredSize(new Dimension(200-1,40));
		desserts.setFont(new Font("Callibri",Font.BOLD,13));
		desserts.setHorizontalAlignment(SwingConstants.CENTER);
		desserts.setForeground(Color.WHITE);
		desserts.setBackground(new Color(54,50,50));
		desserts.setBorder(null);
		desserts.addActionListener(this);
		
		combo = new CategoryButton("Combos");
		combo.setPreferredSize(new Dimension(200-1,40));
		combo.setFont(new Font("Callibri",Font.BOLD,13));
		combo.setHorizontalAlignment(SwingConstants.CENTER);
		combo.setForeground(Color.WHITE);
		combo.setBackground(new Color(54,50,50));
		combo.setBorder(null);
		combo.addActionListener(this);
		combo.last = true;
				
		ImageIcon icon = new ImageIcon("res/signout.png");
		Image img = icon.getImage().getScaledInstance(25, 25, java.awt.Image.SCALE_AREA_AVERAGING);
		icon = new ImageIcon(img);
		
		logout = new JButton();
		logout.setIcon(icon);
		logout.setHorizontalTextPosition(JLabel.RIGHT);
		logout.setPreferredSize(new Dimension(170,60));
		logout.setForeground(Color.WHITE);
		logout.setBackground(new Color(54,50,50));
		logout.setIconTextGap(10);
		logout.setBorder(null);
		logout.setFont(new Font("Callibri",Font.BOLD,15));
		logout.setContentAreaFilled(false);
		logout.addActionListener(this);
		
		ImageIcon reportIcon = new ImageIcon("res/reports.png");
		Image reportImg = reportIcon.getImage().getScaledInstance(25, 25, java.awt.Image.SCALE_AREA_AVERAGING);
		reportIcon = new ImageIcon(reportImg);
		
		reports = new JButton();
		reports.setIcon(reportIcon);
		reports.setHorizontalTextPosition(JLabel.RIGHT);
		reports.setPreferredSize(new Dimension(170,60));
		reports.setBackground(new Color(54,50,50));
		reports.setIconTextGap(10);
		reports.setBorder(null);
		reports.setForeground(Color.WHITE);
		reports.setFont(new Font("Callibri",Font.BOLD,15));
		reports.setContentAreaFilled(false);
		reports.addActionListener(this);
		reports.setFocusable(false);
		
		ImageIcon icon1 = new ImageIcon("res/clock.png");
		Image img1 = icon1.getImage().getScaledInstance(25, 25, java.awt.Image.SCALE_AREA_AVERAGING);
		icon1 = new ImageIcon(img1);
		
		history = new JButton();
		history.setIcon(icon1);
		history.setHorizontalTextPosition(JLabel.RIGHT);
		history.setPreferredSize(new Dimension(170,60));
		history.setBackground(new Color(54,50,50));
		history.setIconTextGap(10);
		history.setBorder(null);
		history.setForeground(Color.WHITE);
		history.setFont(new Font("Callibri",Font.BOLD,15));
		history.setContentAreaFilled(false);
		history.addActionListener(this);
		history.setFocusable(false);
		
		
		ImageIcon icon2 = new ImageIcon("res/menu (2).png");
		Image img2 = icon2.getImage().getScaledInstance(25, 25, java.awt.Image.SCALE_AREA_AVERAGING);
		icon2 = new ImageIcon(img2);
		
		menu= new JButton(){
			/**
			 * 
			 */
			private static final long serialVersionUID = 1L;

			protected void paintComponent(Graphics g) {
				super.paintComponent(g);
				Graphics2D g2 = (Graphics2D) g;
				g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
				g2.setStroke(new BasicStroke(2f));
				g2.setPaint(getForeground());
				if(!isSelected) {
					g2.drawLine(146, 28, 155, 35);
					g2.drawLine(164, 28, 155, 35);
				}else {
					g2.drawLine(146, 35, 155, 28);
					g2.drawLine(164, 35, 155, 28);
				}	
			}
			
		};
		menu.setIcon(icon2);
		menu.setHorizontalTextPosition(JLabel.RIGHT);
		menu.setHorizontalAlignment(SwingConstants.CENTER);
		menu.setPreferredSize(new Dimension(170-1,60));
		menu.setBackground(new Color(54,50,50));
		menu.setIconTextGap(10);
		menu.setForeground(Color.WHITE);
		menu.setFont(new Font("Callibri",Font.BOLD,15));
		menu.setBorder(null);
		menu.setContentAreaFilled(false);
		menu.addActionListener(this);
		menu.setFocusable(false);
		
		ImageIcon icon3 = new ImageIcon("res/dashboard.png");
		Image img3 = icon3.getImage().getScaledInstance(25, 25, java.awt.Image.SCALE_AREA_AVERAGING);
		icon3 = new ImageIcon(img3);
		
		dashboard = new JButton();
		dashboard.setIcon(icon3);
		dashboard.setHorizontalTextPosition(JLabel.RIGHT);
		dashboard.setPreferredSize(new Dimension(170-1,60));
		dashboard.setBackground(new Color(54,50,50));
		dashboard.setIconTextGap(10);
		dashboard.setForeground(Color.GREEN);
		dashboard.setFont(new Font("Callibri",Font.BOLD,15));
		dashboard.setBorder(null);
		dashboard.setContentAreaFilled(false);
		dashboard.addActionListener(this);
		dashboard.setFocusable(false);
		
		ImageIcon menuIcon = new ImageIcon("res/menu.png");
		Image menuImg = menuIcon.getImage().getScaledInstance(15, 15, java.awt.Image.SCALE_AREA_AVERAGING);
		menuIcon = new ImageIcon(menuImg);
		
		button = new JButton();
		button.setIcon(menuIcon);
		button.setPreferredSize(new Dimension(50,50));
		button.setBackground(new Color(54,50,50));
		button.setBorder(null);
		button.setContentAreaFilled(false);
		button.addActionListener(this);
		button.setFocusable(false);
				
		adminlbl = new JLabel("Admin");
		adminlbl.setForeground(Color.WHITE);
		adminlbl.setFont(new Font("Arial Black",Font.BOLD,20));
		adminlbl.setPreferredSize(new Dimension(110,30));
		
		panel = new JPanel();
		panel.setPreferredSize(new Dimension(200,60));
		panel.setBackground(new Color(54,50,50));
		panel.setLayout(new FlowLayout(FlowLayout.CENTER,10,0));
		panel.add(button);
		
		cardPanel.add(dashBoard,"1");
		cardPanel.add(mealsMenu,"2");
		cardPanel.add(drinksMenu,"3");
		cardPanel.add(sidesMenu,"4");
		cardPanel.add(dessertsMenu,"5");
		cardPanel.add(combosMenu,"6");
		cardPanel.add(historypage,"7");
		cardPanel.add(reportPage,"8");
		
		setVisible(true);
		
		hideCategoryButtons();
        startTitleThread("Admin Panel");
        showPanel("1");
        
	}
	
	@Override
	public void decreasedRightPanelWidth() {
		rightPanel.setPreferredSize(new Dimension(80,0));
		logout.setText("");
		logout.setHorizontalAlignment(SwingConstants.CENTER);
		reports.setText("");
		reports.setHorizontalAlignment(SwingConstants.CENTER);
		history.setText("");
		history.setHorizontalAlignment(SwingConstants.CENTER);
		menu.setText("");
		menu.setHorizontalAlignment(SwingConstants.CENTER);
		dashboard.setText("");
		dashboard.setHorizontalAlignment(SwingConstants.CENTER);
		
		panel.setLayout(new FlowLayout(FlowLayout.CENTER,10,0));
		panel.removeAll();
		panel.revalidate();
		panel.repaint();
		panel.add(button);
		
		meals.setText("Meals");
		meals.setHorizontalAlignment(SwingConstants.CENTER);
		drinks.setText("Drinks");
		drinks.setHorizontalAlignment(SwingConstants.CENTER);
		sides.setText("Sides");
		sides.setHorizontalAlignment(SwingConstants.CENTER);
		desserts.setText("Desserts");
		desserts.setHorizontalAlignment(SwingConstants.CENTER);
		combo.setText("Combos");
		combo.setHorizontalAlignment(SwingConstants.CENTER);
		
		mealsMenu.menuPanel.setLayout(new FlowLayout(FlowLayout.LEFT,60,25));
		drinksMenu.menuPanel.setLayout(new FlowLayout(FlowLayout.LEFT,60,25));
		sidesMenu.menuPanel.setLayout(new FlowLayout(FlowLayout.LEFT,60,25));
		dessertsMenu.menuPanel.setLayout(new FlowLayout(FlowLayout.LEFT,60,25));
		combosMenu.menuPanel.setLayout(new FlowLayout(FlowLayout.LEFT,60,25));
		historypage.scrollPane.setPreferredSize(new Dimension(1280,600));
		reportPage.setLayout(new FlowLayout(FlowLayout.CENTER,60,40));
	}
	
	@Override
	public void increasedRightPanelWidth() {
		
		rightPanel.setPreferredSize(new Dimension(200,0));
		logout.setText("Sign out");
		logout.setHorizontalAlignment(SwingConstants.LEFT);
		reports.setText("Reports");
		reports.setHorizontalAlignment(SwingConstants.LEFT);
		history.setText("History");
		history.setHorizontalAlignment(SwingConstants.LEFT);
		menu.setText("Menu");
		menu.setHorizontalAlignment(SwingConstants.LEFT);
		dashboard.setText("Dashboard");
		dashboard.setHorizontalAlignment(SwingConstants.LEFT);
		
		panel.setLayout(new FlowLayout(FlowLayout.LEFT,20,0));
		panel.removeAll();
		panel.revalidate();
		panel.repaint();
		panel.add(adminlbl);
		panel.add(button);
		
		
		meals.setText("<html><div style='text-align: left; margin-left: 40px;'>Meals</div></html>");
		meals.setHorizontalAlignment(SwingConstants.LEFT);
		drinks.setText("<html><div style='text-align: left; margin-left: 40px;'>Drinks</div></html>");
		drinks.setHorizontalAlignment(SwingConstants.LEFT);
		sides.setText("<html><div style='text-align: left; margin-left: 40px;'>Sides</div></html>");
		sides.setHorizontalAlignment(SwingConstants.LEFT);
		desserts.setText("<html><div style='text-align: left; margin-left: 40px;'>Desserts</div></html>");
		desserts.setHorizontalAlignment(SwingConstants.LEFT);
		combo.setText("<html><div style='text-align: left; margin-left: 40px;'>Combos</div></html>");
		combo.setHorizontalAlignment(SwingConstants.LEFT);
		
		mealsMenu.menuPanel.setLayout(new FlowLayout(FlowLayout.LEFT,35,25));
		drinksMenu.menuPanel.setLayout(new FlowLayout(FlowLayout.LEFT,35,25));
		sidesMenu.menuPanel.setLayout(new FlowLayout(FlowLayout.LEFT,35,25));
		dessertsMenu.menuPanel.setLayout(new FlowLayout(FlowLayout.LEFT,35,25));
		combosMenu.menuPanel.setLayout(new FlowLayout(FlowLayout.LEFT,35,25));
		historypage.scrollPane.setPreferredSize(new Dimension(1150,600));
		reportPage.setLayout(new FlowLayout(FlowLayout.CENTER,10,40));		
	}
	
    
	@Override
	public void showCategoryButtons() {
		rightPanel.removeAll();
		rightPanel.revalidate();
		rightPanel.add(panel);
		rightPanel.add(dashboard);
		rightPanel.add(menu);
		rightPanel.add(meals);
		rightPanel.add(drinks);
		rightPanel.add(sides);
		rightPanel.add(desserts);
		rightPanel.add(combo);
		rightPanel.add(history);
		rightPanel.add(reports);
		rightPanel.add(logout);
		rightPanel.repaint();	
	}
    
    @Override
	public void hideCategoryButtons() {
		rightPanel.removeAll();
		rightPanel.revalidate();
		rightPanel.add(panel);
		rightPanel.add(dashboard);
		rightPanel.add(menu);
		rightPanel.add(history);
		rightPanel.add(reports);
		rightPanel.add(logout);
		rightPanel.repaint();
		
	}
	
	
    public void resetCategoryButtonsColor() {
    	meals.setBackground(new Color(54,50,50));
		drinks.setBackground(new Color(54,50,50));
		sides.setBackground(new Color(54,50,50));
		desserts.setBackground(new Color(54,50,50));
		combo.setBackground(new Color(54,50,50));
    }

	@Override
	public void actionPerformed(ActionEvent e) {
		
		if(e.getSource()==button) {
			if(!clicked) {
				increasedRightPanelWidth();
				clicked = true;
			}else{
				decreasedRightPanelWidth();
				clicked = false;
			}
		}
		
		
		
		if(e.getSource()==dashboard) {
			showPanel("1");
			rectY = dashboard.getY();
			rectHeight = dashboard.getHeight();
			rightPanel.repaint();
	
			dashboard.setForeground(Color.GREEN);
			menu.setForeground(Color.WHITE);
			history.setForeground(Color.WHITE);
			reports.setForeground(Color.WHITE);
			resetCategoryButtonsColor();
			
		}else if(e.getSource()==menu) {
			
			if(!isSelected) {
				showCategoryButtons(); 
				isSelected=true;
				//menu.repaint();
			}else {
				hideCategoryButtons(); 
				isSelected=false;	
				//menu.repaint();
			}
			rectY = menu.getY();
			rectHeight = menu.getHeight();
			rightPanel.repaint();
			
			dashboard.setForeground(Color.WHITE);
			menu.setForeground(Color.GREEN);
			history.setForeground(Color.WHITE);
			reports.setForeground(Color.WHITE);
			
		}else if(e.getSource()==meals) {
			showPanel("2");
			resetCategoryButtonsColor();
			meals.setBackground(Color.GREEN);
			
		}else if(e.getSource()==drinks) {
			showPanel("3");
			resetCategoryButtonsColor();
			drinks.setBackground(Color.GREEN);
			
		}else if(e.getSource()==sides) {
			showPanel("4");
			resetCategoryButtonsColor();
			sides.setBackground(Color.GREEN);
			
		}else if(e.getSource()==desserts) {
			showPanel("5");
			resetCategoryButtonsColor();
			desserts.setBackground(Color.GREEN);
			
		}else if(e.getSource()==combo) {
			showPanel("6");
			resetCategoryButtonsColor();
			combo.setBackground(Color.GREEN);
			
		}else if(e.getSource()==history) {
			showPanel("7");
			rectY = history.getY();
			rectHeight = history.getHeight();
			rightPanel.repaint();
			
			dashboard.setForeground(Color.WHITE);
			menu.setForeground(Color.WHITE);
			history.setForeground(Color.GREEN);
			reports.setForeground(Color.WHITE);
			resetCategoryButtonsColor();
			
		}else if(e.getSource()==reports) {
			showPanel("8");
			rectY = reports.getY();
			rectHeight = reports.getHeight();
			rightPanel.repaint();
			
			dashboard.setForeground(Color.WHITE);
			menu.setForeground(Color.WHITE);
			history.setForeground(Color.WHITE);
			reports.setForeground(Color.GREEN);
			
		}else if(e.getSource()==logout) {
			new AdminLoginPage();
			this.dispose();
		}
		
		
	}


}
