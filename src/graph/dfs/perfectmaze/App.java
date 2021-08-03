package graph.dfs.perfectmaze;

public class App {

	public static void main(String[] args) {
		Maze m = new Maze(125);
		m.draw();
		MazeSolver ms = new MazeSolver(m);
	}

}
