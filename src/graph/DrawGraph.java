package graph;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.FontMetrics;
import java.awt.Graphics;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.UIManager;

public class DrawGraph {

	public DrawGraph() {
		EventQueue.invokeLater(new Runnable() {
			@Override
			public void run() {
				try {
					UIManager.setLookAndFeel(UIManager.getLookAndFeel());
				} catch (Exception e) {
					e.printStackTrace();
				}
				JFrame frame = new JFrame("GraphS");
				frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
				frame.setLayout(new BorderLayout());
				frame.setSize(new Dimension(500,500));
				frame.add(new GraphPanel());
				frame.setLocationRelativeTo(null);
				frame.setVisible(true);
				
			}
		});
	}
	
	public class GraphPanel extends JPanel{
		
		private static final long serialVersionUID = 4561987997666902950L;

		@Override
		public Dimension getPreferredSize() {
			return new Dimension(500,500);
		}
		
		@Override 
		protected void paintComponent(Graphics g) {
			super.paintComponent(g);
			FontMetrics fm = g.getFontMetrics();
			
			g.setColor(Color.cyan);
			g.fillOval(0, 0, 100, 100);
			g.drawString("Here!", 0, fm.getAscent());
		}
	}
}
