package ObjectsAndInterfaces;

import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Font;

import javax.swing.BorderFactory;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;

public class DarkTable extends JTable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	public DarkTable(DefaultTableModel model) {
		super(model);
		getTableHeader().setDefaultRenderer(new TableDarkHeader());
		getTableHeader().setPreferredSize(new Dimension(0, 35));
		setDefaultRenderer(Object.class, new TableDarkCell());
		setRowHeight(30);
	}
	
	public void fixTable(JScrollPane scroll) {
		scroll.setVerticalScrollBar(new ScrollBarCustom());
		JPanel panel = new JPanel();
		panel.setBackground(new Color(30,30,30));
		scroll.setCorner(JScrollPane.UPPER_RIGHT_CORNER, panel);
		scroll.getViewport().setBackground(Color.DARK_GRAY);
		//scroll.setBorder(BorderFactory.createLineBorder(new Color(60,60,60), 2));
		scroll.setBorder(BorderFactory.createEmptyBorder());
	}
	
	private class TableDarkHeader extends DefaultTableCellRenderer {
		
		/**
		 * 
		 */
		private static final long serialVersionUID = 1L;

		@Override
		public Component getTableCellRendererComponent(JTable jtable, Object o, boolean bln, boolean bln1, int i, int i1) {
			Component com = super.getTableCellRendererComponent(jtable, o, bln, bln1, i, i1);
			com.setBackground(new Color(30,30,30));
			com.setForeground(new Color(200,200,200));
			com.setFont(com.getFont().deriveFont(Font.BOLD,12));
			
			return com;
		}
	}

	private class TableDarkCell extends DefaultTableCellRenderer{
		
		/**
		 * 
		 */
		private static final long serialVersionUID = 1L;

		@Override
		public Component getTableCellRendererComponent(JTable jtable, Object o, boolean bln, boolean bln1, int row, int column) {
			Component com =  super.getTableCellRendererComponent(jtable, o, bln, bln1, row, column);
			if(isCellSelected(row, column)) {
				com.setBackground(Color.RED);
			}else {
				if(row%2==0) {
					com.setBackground(new Color(50,50,50));
				}else {
					com.setBackground(new Color(30,30,30));
				}
			}
			com.setForeground(Color.WHITE);
			setBorder(new EmptyBorder(0, 5, 0, 5));
			return com;
		}
		
	}
	
}
