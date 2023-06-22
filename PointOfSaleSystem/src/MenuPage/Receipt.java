package MenuPage;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import javax.swing.JFrame;
import javax.swing.JTextArea;

public class Receipt extends JFrame {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	JTextArea textarea = new JTextArea();
	ArrayList<Order> orders;
	
	Receipt(ArrayList<Order> orders,double totalPrice,double payment,double change,String cashier){
		
		this.orders =orders;

		StringBuilder str = new StringBuilder();
		str.append("\n")
			.append("\n")
			.append("\n")
			.append("-------------------------------------------------------------------------------\n")
			.append("\t        The Hungry Fork\n")
			.append("                 Lower Bicutan, Taguig City, Metro Manila\n")
			.append("                     www.facebook.com/AHAHAHA\n")
			.append("                                      123456789\n")
			.append("-------------------------------------------------------------------------------\n")
			.append("  Cashier: ")
			.append(cashier)
			.append("\n")
			.append("  Date: ").append(LocalDate.now()).append("\t").append("Time: ").append(java.sql.Time.valueOf(LocalTime.now())).append("\n")
			.append("-------------------------------------------------------------------------------\n")
			.append("  Name               Quantity               UnitPrice             TotalPrice\n\n");

		for (int i = 0; i < orders.size(); i++) {
		    int count = 0;
		    str.append("  ");
		    for (int j = 0; j < orders.get(i).getName().length(); j++) {
		        str.append(orders.get(i).getName().charAt(j));

		        if (orders.get(i).getName().charAt(j) == ' ') {
		            break;
		        } else {
		            count++;
		        }
		    }

		    if (!orders.get(i).getSize().equals(" ")) {
		        str.append("(").append(orders.get(i).getSize().charAt(0)).append(")");
		    }
		    str.append("\t").append(orders.get(i).getQuantity());
		    str.append("\t").append(orders.get(i).getUnitPrice());
		    str.append("\t").append(orders.get(i).getPrice());
		    str.append("\n");
		    str.append("  ");
		    if (count < orders.get(i).getName().length() && orders.get(i).getName().charAt(count) == ' ') {
		        for (int j = count + 1; j < orders.get(i).getName().length(); j++) {
		            str.append(orders.get(i).getName().charAt(j));
		        }
		    }

		    str.append("\n\n");
		}
		str.append("-------------------------------------------------------------------------------\n");
		str.append("  Total:\t").append(totalPrice).append("\n");
		str.append("-------------------------------------------------------------------------------\n");
		str.append("  Cash:\t").append(payment).append("\n");
		str.append("-------------------------------------------------------------------------------\n");
		str.append("  Change:\t").append(change).append("\n\n");
		str.append("  ************************************************************\n");
		str.append("\tThank You For Your Order\n\n");
		str.append("  ************************************************************\n");
		textarea.setText(str.toString());
		textarea.setEditable(false);
		this.add(textarea);
		this.setResizable(false);
		this.pack();
		this.setLocationRelativeTo(null);
		this.setVisible(true);
	}
	
	
  public void paint(Graphics g) {
	  super.paintComponents(g);
	  g.setColor(Color.BLACK);
	  g.setFont(new Font("Arial",Font.BOLD,20));
	  g.drawString("Receipt", 130, 70);
  }

}
