package graph.dfs.perfectmaze;

public class App {

	public static void main(String[] args) {
		Maze m = new Maze(150);
		m.draw();
		new MazeSolver(m);
	}

}
