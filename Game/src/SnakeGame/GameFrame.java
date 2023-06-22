package SnakeGame;
import javax.swing.JFrame;

public class GameFrame extends JFrame{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	GameFrame(){
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
		this.add(new GamePanel());
		this.pack();
		this.setLocationRelativeTo(null);
		this.setVisible(true);
	}

}
