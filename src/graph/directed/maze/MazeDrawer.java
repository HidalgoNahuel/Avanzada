package graph.directed.maze;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.Random;

import javax.swing.JFrame;
import javax.swing.JPanel;

public class MazeDrawer {

	private static final int size = 500;
	private final int space;
	private final int n;
	private JFrame maze;
	private final String name;
	private boolean[][]visited;

	public static void main(String[] args) {
		new MazeDrawer(25, "Prim");
	}

	public MazeDrawer(int n, String name) {
		this.name = name;
		this.space = size / n;
		this.n = n;
		visited = new boolean[n+2][n+2];
		for(int x = 0; x < n+2; x++) {
			visited[x][0] = true;
			visited[x][n+1] = true;
		}
		for(int y = 0; y < n+2; y++) {
			visited[0][y] = true;
			visited[n+1][y] = true;
		}
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					initialize();
					maze.setVisible(true);
				} catch (Exception e) {
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
				for (int i = 0; i <= n; i++) {
					int y = 5 + space * i;
					g.drawLine(5, y, size + 5, y);
				}
				for (int j = 0; j <= n; j++) {
					int x = 5 + space * j;
					g.drawLine(x, 5, x, size + 5);
				}
			}
		};
		grid.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				int x = (int)Math.ceil(Double.valueOf(e.getX())/space);
				int y = (int)Math.ceil(Double.valueOf(e.getY())/space);
				Graphics2D g2d = (Graphics2D) grid.getGraphics().create();
				mark(x, y, g2d);
			}
		});
		grid.setSize(10 + size, size + 10);
		grid.setBackground(Color.gray);
		maze.getContentPane().add(grid);
	}

	private void mark(int x, int y, Graphics2D g2d) {
		try {
			Thread.sleep(100);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		visited[x][y] = true;
		g2d.clearRect(5 + space * (x-1), 5 + space * (y-1), space, space);
		addFrontier(x, y, g2d);

		while(!visited[x][y+1] || !visited[x+1][y] || !visited[x][y-1] || !visited[x-1][y]) {
			
			while (true) {
				Random rand = new Random();
				int r = rand.nextInt(4);
				if (r == 0 && !visited[x][y+1]) {
					mark(x, y + 1, g2d);
					break;
				} else if (r == 1 && !visited[x+1][y]) {
					mark(x + 1, y, g2d);
					break;
				} else if (r == 2 && !visited[x][y-1]) {
					mark(x, y - 1, g2d);
					break;
				} else if (r == 3 && !visited[x-1][y]) {
					mark(x - 1, y, g2d);
					break;
				}
			}

		}
	}

	private void addFrontier(int x, int y, Graphics2D g2d) {
		
	}
}
