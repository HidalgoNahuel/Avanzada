package graph.directed.maze;

import java.awt.EventQueue;
import java.awt.Graphics;

import javax.swing.JFrame;
import javax.swing.JPanel;

public class MazeDrawer {

	private final int size;
	private JFrame maze;
	private final String name;
	
	public static void main(String[] args) {
		new MazeDrawer(10, "Prim");
	}
	
	public MazeDrawer(int size, String name) {
		this.name = name;
		this.size = size*100;
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					initialize();
					maze.setVisible(true);
				} catch(Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
	
	@SuppressWarnings("serial")
	private void initialize() {
		maze = new JFrame(name);
		maze.setSize(size+17, size+40);
		maze.setTitle(name);
		maze.setLocationRelativeTo(null);
		maze.getContentPane().setLayout(null);
		
		JPanel grid = new JPanel() {
			@Override
			protected void paintComponent(Graphics g) {
				super.paintComponent(g);
				g.drawLine(5, 5, size-5,5);
				g.drawLine(5, 5, 5, size-5);
				g.drawLine(size-5, 5, size-5, size-5);
				g.drawLine(5, size-5, size-5, size-5);
				for (int i = 10; i < size-5; i+=10) {
					g.drawLine(i*10, 5, i*10, size-5);
				}
				for (int j = 10; j < size-5; j+=10) {
					g.drawLine(5, j*10, size-5, j*10);
				}
			}
		};
		grid.setSize(size, size);
		maze.getContentPane().add(grid);
	}
}
