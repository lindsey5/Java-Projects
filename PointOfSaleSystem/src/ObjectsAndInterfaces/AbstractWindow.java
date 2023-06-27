package ObjectsAndInterfaces;

import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.Graphics;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

public abstract class AbstractWindow extends JFrame{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	protected JPanel rightPanel,topPanel, cardPanel;
	protected CardLayout cardLayout;
	protected JLabel title;
	protected Thread titleThread;
	
	protected int rectY = 60;
	protected int rectHeight = 60;
	
	public AbstractWindow(){
		
		rightPanel = new JPanel() {
			/**
			 * 
			 */
			private static final long serialVersionUID = 1L;

			protected void paintComponent(Graphics g) {
				super.paintComponent(g);
				g.setColor(Color.GREEN);
				g.fillRoundRect(getWidth()-10, rectY+15,5, rectHeight-20,3,3);
			}
		};
		rightPanel.setPreferredSize(new Dimension(80,0));
		rightPanel.setBackground(new Color(54,50,50));
		rightPanel.setLayout(new FlowLayout(FlowLayout.CENTER,0,0));
		
		cardLayout = new CardLayout();

		cardPanel = new JPanel();
		cardPanel.setLayout(cardLayout);
		
		title = new JLabel();
		title.setFont(new Font("Arial Black",Font.BOLD,30));
		title.setForeground(Color.WHITE);
		title.setBounds(30,20,500,50);
		
		topPanel = new JPanel();
		topPanel.setPreferredSize(new Dimension(0,100));
		topPanel.setBackground(new Color(32,32,32));
		topPanel.setLayout(null);
		topPanel.add(title);
		
		this.setExtendedState(MAXIMIZED_BOTH);
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
		this.setUndecorated(true);
		this.setLocationRelativeTo(null);
		this.setLayout(new BorderLayout());
		this.add(topPanel,BorderLayout.NORTH);
		this.add(rightPanel,BorderLayout.WEST);
		this.add(cardPanel);
	}
	
	protected void showPanel(String num) {
		cardLayout.show(cardPanel, num);
	}
	
	protected void startTitleThread(String titleText) {
		
		titleThread = new Thread(new Runnable() {
			@Override
			public void run() {
				//Animate the title text
				while(true) {
					for(int i=0;i<titleText.length();i++) {
						title.setText(title.getText()+titleText.charAt(i));
						try {
							Thread.sleep(200);
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
	
	public abstract void showCategoryButtons();
	public abstract void hideCategoryButtons();
	public abstract void increasedRightPanelWidth();
	public abstract void decreasedRightPanelWidth();
	public abstract void resetCategoryButtonsColor();

}
