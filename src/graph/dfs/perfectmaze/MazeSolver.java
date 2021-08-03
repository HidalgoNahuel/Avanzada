package graph.dfs.perfectmaze;

import java.util.NoSuchElementException;

public class MazeSolver {

	private boolean[][]visited;
	private boolean solved;
	
	public MazeSolver(Maze maze) {
		if(maze == null)
			throw new NoSuchElementException("Maze is null");
		
		visited = new boolean[maze.n()+2][maze.n()+2];
		solve(maze);
	}
	
	private void solve(Maze maze) {
		explore(maze, 1,1);
	}
	
	private void explore(Maze maze, int x, int y) {
		visited[x][y] = true;
		System.out.printf("[%d][%d]\n", x, y);
		//maze.moveBall(x, y);
				
		if(x == maze.n() && y == maze.n())
			solved = true;
		if(!maze.northWall(x, y) && !visited[x][y+1] && !solved)
			explore(maze, x, y+1);
		if(!maze.southWall(x, y) && !visited[x][y-1] && !solved)
			explore(maze, x, y-1);
		if(!maze.eastWall(x, y) && !visited[x+1][y] && !solved)
			explore(maze, x+1, y);
		if(!maze.westWall(x, y) && !visited[x-1][y] && !solved)
			explore(maze, x-1, y);
	}
	
}
