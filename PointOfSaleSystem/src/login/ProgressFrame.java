package login;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.SwingConstants;


public class ProgressFrame extends JFrame {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	int id;
	String username, password;
	JLabel label = new JLabel();
	Thread thread;
	CircularProgressBar progressBar;
	
	ProgressFrame(){
		id = 1;
		
		label.setForeground(Color.WHITE);
		label.setFont(new Font("Arial",Font.BOLD,20));
		label.setPreferredSize(new Dimension(100,30));
		label.setHorizontalAlignment(SwingConstants.CENTER);
		progressBar = new CircularProgressBar(this);
		progressBar.setPreferredSize(new Dimension(200,150));
		
		this.setUndecorated(true);
		this.add(progressBar);
		this.add(label);
		this.setSize(200,250);
		this.setLayout(new FlowLayout(FlowLayout.CENTER,0,20));
		this.getContentPane().setBackground(Color.DARK_GRAY);
		this.setLocationRelativeTo(null);
		this.setVisible(true);
		startThread();
	}
	
	ProgressFrame(String username, String password){
		
		this.username = username;
		this.password = password;
		id = 2;
		
		label.setForeground(Color.WHITE);
		label.setFont(new Font("Arial",Font.BOLD,20));
		label.setPreferredSize(new Dimension(100,30));
		label.setHorizontalAlignment(SwingConstants.CENTER);
		progressBar = new CircularProgressBar(this);
		progressBar.setPreferredSize(new Dimension(200,150));
		
		this.setUndecorated(true);
		this.add(progressBar);
		this.add(label);
		this.setSize(200,250);
		this.setLayout(new FlowLayout(FlowLayout.CENTER,0,30));
		this.getContentPane().setBackground(Color.DARK_GRAY);
		this.setLocationRelativeTo(null);
		this.setVisible(true);
		startThread();
	}
	
	void startThread() {
		
		thread = new Thread(new Runnable() {

			@Override
			public void run() {
				while(true) {
					
					try {
						label.setText("Loading");
						Thread.sleep(100);
						label.setText("Loading.");
						Thread.sleep(100);
						label.setText("Loading..");
						Thread.sleep(100);
						label.setText("Loading...");
						Thread.sleep(100);
					} catch (InterruptedException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
				
				
			}
			
		});
		thread.start();
	}

}
