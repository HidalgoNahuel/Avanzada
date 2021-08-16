package graph.directed.maze;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Graphics;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.JFrame;
import javax.swing.JPanel;

public class MazeDrawer {

	private static final int size = 500;
	private final int space;
	private final int n;
	private JFrame maze;
	private final String name;
	
	public static void main(String[] args) {
		new MazeDrawer(25, "Prim");
	}
	
	public MazeDrawer(int n, String name) {
		this.name = name;
		this.space = size/n;
		this.n = n;
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
		maze.setSize(size + 10, size + 10 + 30);
		maze.setTitle(name);
		maze.setLocationRelativeTo(null);
		maze.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		maze.getContentPane().setLayout(null);
		
		JPanel grid = new JPanel() {
			@Override
			protected void paintComponent(Graphics g) {
				super.paintComponent(g);
				g.setColor(Color.black);
				for(int i = 0; i < n; i++) {
					for(int j = 0; j < n; j++) {
						g.drawRect(5 + space * j, 5 + space * i, space, space);
					}
				}
			}
		};
		grid.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				int x = e.getX()/space, y = e.getY()/space;
				Graphics g = grid.getGraphics().create();
				g.clearRect(5 + space * x, 5 + space * y, space, space);
			}
		});
		grid.setSize(10 + size, size+10);
		grid.setBackground(Color.gray);
		maze.getContentPane().add(grid);
	}
}
