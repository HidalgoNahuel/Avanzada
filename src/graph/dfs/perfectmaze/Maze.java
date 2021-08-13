package graph.dfs.perfectmaze;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.util.Random;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;

class Maze {

	private int n;
	private boolean[][] north;
	private boolean[][] south;
	private boolean[][] east;
	private boolean[][] west;
	private boolean[][] visited;

	private static int esc;
	private Ball ball;

	public Maze(int n) {

		esc = 5 + 100 / n;
		ball = new Ball(n);

		this.n = n;
		init();
		generate(1, 1);
	}

	private void init() {
		// initialize border cells as already visited
		visited = new boolean[n + 2][n + 2];
		for (int x = 0; x < n + 2; x++) {
			visited[x][0] = true;
			visited[x][n + 1] = true;
		}
		for (int y = 0; y < n + 2; y++) {
			visited[0][y] = true;
			visited[n + 1][y] = true;
		}

		// initialze all walls as present
		north = new boolean[n + 2][n + 2];
		east = new boolean[n + 2][n + 2];
		south = new boolean[n + 2][n + 2];
		west = new boolean[n + 2][n + 2];
		for (int x = 0; x < n + 2; x++) {
			for (int y = 0; y < n + 2; y++) {
				north[x][y] = true;
				east[x][y] = true;
				south[x][y] = true;
				west[x][y] = true;
			}
		}
	}

	private void generate(int x, int y) {
		visited[x][y] = true;

		// while there is an unvisited neighbor
		while (!visited[x][y + 1] || !visited[x + 1][y] || !visited[x][y - 1] || !visited[x - 1][y]) {

			// pick random neighbor (could use Knuth's trick instead)
			while (true) {
				Random rand = new Random();
				int r = rand.nextInt(4);
				if (r == 0 && !visited[x][y + 1]) {
					north[x][y] = false;
					south[x][y + 1] = false;
					generate(x, y + 1);
					break;
				} else if (r == 1 && !visited[x + 1][y]) {
					east[x][y] = false;
					west[x + 1][y] = false;
					generate(x + 1, y);
					break;
				} else if (r == 2 && !visited[x][y - 1]) {
					south[x][y] = false;
					north[x][y - 1] = false;
					generate(x, y - 1);
					break;
				} else if (r == 3 && !visited[x - 1][y]) {
					west[x][y] = false;
					east[x - 1][y] = false;
					generate(x - 1, y);
					break;
				}
			}
		}
	}

	public int n() {
		return n;
	}

	public boolean northWall(int x, int y) {
		return north[x][y];
	}

	public boolean southWall(int x, int y) {
		return south[x][y];
	}

	public boolean eastWall(int x, int y) {
		return east[x][y];
	}

	public boolean westWall(int x, int y) {
		return west[x][y];
	}

	public void moveBall(int x, int y) {
		ball.moveBall(x, y);
	}

	public void draw() {

		EventQueue.invokeLater(new Runnable() {
			@Override
			public void run() {
				try {
					UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
				} catch (ClassNotFoundException | InstantiationException | IllegalAccessException
						| UnsupportedLookAndFeelException ex) {
				}
				
				JFrame frame = new JFrame("Maze");
				frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
				frame.getContentPane().setLayout(null);
				frame.getContentPane().add(ball);
				frame.getContentPane().add(new Panel());
				frame.setSize((n + 2) * esc, (n + 2) * esc + 30);
				frame.setLocationRelativeTo(null);
				frame.setVisible(true);
			}
		});
	}

	public class Panel extends JPanel {

		private static final long serialVersionUID = 5478931164695870842L;

		public Panel() {
			this.setBounds(0, 0, (n + 2) * esc, (n + 2) * esc + 30);
		}
		@Override
		protected void paintComponent(Graphics g) {
			super.paintComponents(g);
			Graphics2D g2D = (Graphics2D) g;

			g2D.setColor(Color.black);
			for (int x = 1; x <= n; x++) {
				for (int y = 1; y <= n; y++) {
					if (south[x][y])
						g2D.drawLine(x * esc, y * esc, (x + 1) * esc, y * esc);
					if (north[x][y])
						g2D.drawLine(x * esc, (y + 1) * esc, (x + 1) * esc, (y + 1) * esc);
					if (east[x][y])
						g2D.drawLine((x + 1) * esc, y * esc, (x + 1) * esc, (y + 1) * esc);
					if (west[x][y])
						g2D.drawLine(x * esc, y * esc, x * esc, (y + 1) * esc);
				}
			}
		}

		@Override
		public Dimension getPreferredSize() {
			return new Dimension((n + 2) * esc, (n + 2) * esc + 30);
		}
	}

	public class Ball extends JPanel {

		private static final long serialVersionUID = -4934950428767393343L;
		
		private int x = 1;
		private int y = 1;

		public Ball(int n) {
			this.setSize((n + 2) * esc, (n + 2) * esc + 30);
			this.setOpaque(false);
		}
		@Override
		protected void paintComponent(Graphics g) {
			super.paintComponent(g);

			g.setColor(Color.red);
			g.fillOval(this.x*esc, this.y*esc, esc, esc);
		}
		
		@Override
		public Dimension getPreferredSize() {
			return new Dimension((n + 2) * esc, (n + 2) * esc + 30);
		}

		public void moveBall(int x, int y) {
			this.x = x;
			this.y = y;
			this.repaint();
			try {
				Thread.sleep(100);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}
	
	public static void main(String[] args) {
		Maze m = new Maze(15);
		m.draw();
		new MazeSolver(m);
	}

}
