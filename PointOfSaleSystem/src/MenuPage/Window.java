package MenuPage;

import java.awt.BasicStroke;
import java.awt.BorderLayout;
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
import login.LoginPage;

public class Window extends AbstractWindow implements ActionListener{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private JLabel cashierlbl;
	private JPanel panel;
	private JButton button, menu, profile, history, logout;
	
	private MenuPage mealsMenu;
	private MenuPage drinksMenu;
	private MenuPage sidesMenu;
	private MenuPage dessertsMenu;
	private MenuPage combosMenu;
	private OrdersPanel orderspanel;
	private ProfilePage profilepage;
	private AccountHistory accountHistory;

	private CategoryButton meals, drinks, sides, desserts, combo;
	private boolean isSelected=true;//This is to show and hide category buttons
	private boolean clicked = false; //This is to increased and decreased the width of rightPanel

	public Window(String username, String password){
		
		profilepage = new ProfilePage(username,password);
		
		accountHistory = new AccountHistory(username,password);
		
		orderspanel = new OrdersPanel(username,password,accountHistory);
		
		mealsMenu = new MenuPage(orderspanel);
		drinksMenu = new MenuPage(orderspanel);
		sidesMenu = new MenuPage(orderspanel);
		dessertsMenu = new MenuPage(orderspanel);
		combosMenu = new MenuPage(orderspanel);
		
		mealsMenu.displayMenu("Meal");
		drinksMenu.displayMenu("Drink");
		sidesMenu.displayMenu("Side");
		dessertsMenu.displayMenu("Dessert");
		combosMenu.displayMenu("Combo");
	
		meals = new CategoryButton("Meals");
		meals.setPreferredSize(new Dimension(200,40));
		meals.setFont(new Font("Callibri",Font.BOLD,13));
		meals.setHorizontalAlignment(SwingConstants.CENTER);
		meals.setForeground(Color.WHITE);
		meals.setBackground(Color.GREEN);
		meals.setBorder(null);
		meals.addActionListener(this);
		
		drinks = new CategoryButton("Drinks");
		drinks.setPreferredSize(new Dimension(200,40));
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
		desserts.setPreferredSize(new Dimension(200,40));
		desserts.setFont(new Font("Callibri",Font.BOLD,13));
		desserts.setHorizontalAlignment(SwingConstants.CENTER);
		desserts.setForeground(Color.WHITE);
		desserts.setBackground(new Color(54,50,50));
		desserts.setBorder(null);
		desserts.addActionListener(this);
		
		combo = new CategoryButton("Combos");
		combo.setPreferredSize(new Dimension(200,40));
		combo.setFont(new Font("Callibri",Font.BOLD,13));
		combo.setHorizontalAlignment(SwingConstants.CENTER);
		combo.setForeground(Color.WHITE);
		combo.setBackground(new Color(54,50,50));
		combo.setBorder(null);
		combo.addActionListener(this);
		combo.last = true;
		
		ImageIcon logoutIcon = new ImageIcon("res/signout.png");
		Image logoutImage = logoutIcon.getImage().getScaledInstance(25, 25, java.awt.Image.SCALE_AREA_AVERAGING);
		logoutIcon = new ImageIcon(logoutImage);
		
		logout = new JButton();
		logout.setIcon(logoutIcon);
		logout.setHorizontalTextPosition(JLabel.RIGHT);
		logout.setPreferredSize(new Dimension(170,60));
		logout.setForeground(Color.WHITE);
		logout.setBackground(new Color(54,50,50));
		logout.setIconTextGap(10);
		logout.setBorder(null);
		logout.setFont(new Font("Callibri",Font.BOLD,15));
		logout.setContentAreaFilled(false);
		logout.addActionListener(this);
		
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
		
		ImageIcon icon = new ImageIcon("res/user.png");
		Image img = icon.getImage().getScaledInstance(25, 25, java.awt.Image.SCALE_AREA_AVERAGING);
		icon = new ImageIcon(img);
		
		profile = new JButton();
		profile.setIcon(icon);
		profile.setHorizontalTextPosition(JLabel.RIGHT);
		profile.setPreferredSize(new Dimension(170,60));
		profile.setBackground(new Color(54,50,50));
		profile.setForeground(Color.WHITE);
		profile.setFont(new Font("Callibri",Font.BOLD,15));
		profile.setBorder(null);
		profile.setContentAreaFilled(false);
		profile.addActionListener(this);
		profile.setFocusable(false);
		
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
		menu.setForeground(Color.GREEN);
		menu.setFont(new Font("Callibri",Font.BOLD,15));
		menu.setBorder(null);
		menu.setContentAreaFilled(false);
		menu.addActionListener(this);
		menu.setFocusable(false);
		
		
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
		
		cashierlbl = new JLabel("Cashier");
		cashierlbl.setForeground(Color.WHITE);
		cashierlbl.setFont(new Font("Arial Black",Font.BOLD,20));
		cashierlbl.setPreferredSize(new Dimension(110,30));
		
		panel = new JPanel();
		panel.setPreferredSize(new Dimension(200,60));
		panel.setBackground(new Color(54,50,50));
		panel.setLayout(new FlowLayout(FlowLayout.CENTER,10,0));
		panel.add(button);
		
		cardPanel.add(mealsMenu,"1");
		cardPanel.add(drinksMenu,"2");
		cardPanel.add(sidesMenu,"3");
		cardPanel.add(dessertsMenu,"4");
		cardPanel.add(combosMenu,"5");
		cardPanel.add(profilepage,"6");
		cardPanel.add(accountHistory,"7");
		
		add(orderspanel,BorderLayout.EAST);
		this.setVisible(true);
		
		showCategoryButtons();
	    startTitleThread("Point of Sale System");
		
	}

	@Override
	public void showCategoryButtons() {
		rightPanel.removeAll();
		rightPanel.revalidate();
		rightPanel.add(panel);
		rightPanel.add(menu);
		rightPanel.add(meals);
		rightPanel.add(drinks);
		rightPanel.add(sides);
		rightPanel.add(desserts);
		rightPanel.add(combo);
		rightPanel.add(profile);
		rightPanel.add(history);
		rightPanel.add(logout);
		rightPanel.repaint();
	}


	@Override
	public void hideCategoryButtons() {
		rightPanel.removeAll();
		rightPanel.revalidate();
		rightPanel.add(panel);
		rightPanel.add(menu);
		rightPanel.add(profile);
		rightPanel.add(history);
		rightPanel.add(logout);
		rightPanel.repaint();
	}


	@Override
	public void increasedRightPanelWidth() {
		rightPanel.setPreferredSize(new Dimension(200,0));
		menu.setText("Menu");
		menu.setHorizontalAlignment(SwingConstants.LEFT);
		profile.setText("Profile");
		profile.setHorizontalAlignment(SwingConstants.LEFT);
		history.setText("History");
		history.setHorizontalAlignment(SwingConstants.LEFT);
		logout.setText("Log out");
		logout.setHorizontalAlignment(SwingConstants.LEFT);
		
		
		panel.setLayout(new FlowLayout(FlowLayout.LEFT,20,0));
		panel.removeAll();
		panel.revalidate();
		panel.repaint();
		panel.add(cashierlbl);
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
		
		mealsMenu.mainPanel.setLayout(new FlowLayout(FlowLayout.LEFT,20,35));
		drinksMenu.mainPanel.setLayout(new FlowLayout(FlowLayout.LEFT,20,35));
		sidesMenu.mainPanel.setLayout(new FlowLayout(FlowLayout.LEFT,20,35));
		dessertsMenu.mainPanel.setLayout(new FlowLayout(FlowLayout.LEFT,20,35));
		combosMenu.mainPanel.setLayout(new FlowLayout(FlowLayout.LEFT,20,35));
		
		accountHistory.scrollPane.setPreferredSize(new Dimension(1150,600));
		
	}


	@Override
	public void decreasedRightPanelWidth() {
		rightPanel.setPreferredSize(new Dimension(80,0));
		menu.setText("");
		menu.setHorizontalAlignment(SwingConstants.CENTER);
		profile.setText("");
		profile.setHorizontalAlignment(SwingConstants.CENTER);
		history.setText("");
		history.setHorizontalAlignment(SwingConstants.CENTER);
		logout.setText("");
		logout.setHorizontalAlignment(SwingConstants.CENTER);
		
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
		
		mealsMenu.mainPanel.setLayout(new FlowLayout(FlowLayout.LEFT,40,35));
		drinksMenu.mainPanel.setLayout(new FlowLayout(FlowLayout.LEFT,40,35));
		sidesMenu.mainPanel.setLayout(new FlowLayout(FlowLayout.LEFT,40,35));
		dessertsMenu.mainPanel.setLayout(new FlowLayout(FlowLayout.LEFT,40,35));
		combosMenu.mainPanel.setLayout(new FlowLayout(FlowLayout.LEFT,40,35));
		
		accountHistory.scrollPane.setPreferredSize(new Dimension(1280,600));

	}


	@Override
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
		
		if(e.getSource()==menu) {
			if(!isSelected) {
				showCategoryButtons();
				isSelected = true;
			}else {
				hideCategoryButtons();
				isSelected = false;
			}
			rectY = menu.getY();
			rectHeight = menu.getHeight();
			rightPanel.repaint();
			
			history.setForeground(Color.WHITE);
			profile.setForeground(Color.WHITE);
			menu.setForeground(Color.GREEN);
			
		}
		
		if(e.getSource()==meals) {
			showPanel("1");
			orderspanel.setVisible(true);
			resetCategoryButtonsColor();
			meals.setBackground(Color.GREEN);
			
		}else if(e.getSource()==drinks) {
			showPanel("2");
			orderspanel.setVisible(true);
			resetCategoryButtonsColor();
			drinks.setBackground(Color.GREEN);
			
		}else if(e.getSource()==sides) {
			showPanel("3");
			orderspanel.setVisible(true);
			resetCategoryButtonsColor();
			sides.setBackground(Color.GREEN);
			
		}else if(e.getSource()==desserts) {
			showPanel("4");
			orderspanel.setVisible(true);
			resetCategoryButtonsColor();
			desserts.setBackground(Color.GREEN);
			
		}else if(e.getSource()==combo) {
			showPanel("5");
			orderspanel.setVisible(true);
			resetCategoryButtonsColor();
			combo.setBackground(Color.GREEN);
			
		}else if(e.getSource()==profile) {
		    orderspanel.setVisible(false);
			showPanel("6");
			
			rectY = profile.getY();
			rectHeight = profile.getHeight();
			rightPanel.repaint();
			
			history.setForeground(Color.WHITE);
			profile.setForeground(Color.GREEN);
			menu.setForeground(Color.WHITE);
			resetCategoryButtonsColor();
			
		}else if(e.getSource()==history) {
			orderspanel.setVisible(false);
			showPanel("7");
			
			rectY = history.getY();
			rectHeight = history.getHeight();
			rightPanel.repaint();
			
			history.setForeground(Color.GREEN);
			profile.setForeground(Color.WHITE);
			menu.setForeground(Color.WHITE);
			resetCategoryButtonsColor();
			
		}else if(e.getSource()==logout) {
			this.dispose();
			new LoginPage();
		}
		
		
		
	}
	
}
