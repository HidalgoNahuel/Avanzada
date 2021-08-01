package graph.dfs.cycle;

import graph.Graph;
import graph.GraphException;

public class Cycle {

	public boolean []visited;
	private boolean []cycle;
	
	public Cycle(Graph g, int v) {
		if(g == null)
			throw new GraphException(g);
		
		cycle = new boolean[g.getV()];
		visited = new boolean[g.getV()];
		
		validateVertex(v);
		
		dfs(g, v);
	}
	
	private void dfs(Graph g, int v) {
		visited[v] = true;
		for(int w : g.getAdj(v)) {
			count ++;
			if(!visited[w])
				dfs(g, w);
			else
				cycle[w] = true;
		}
	}
	
	private void validateVertex(int v) {
		if(v < 0 || v >= visited.length)
			throw new GraphException(v);
	}
	
	public boolean cycle(int v) {
		return cycle[v];
	}
	
	public boolean isVisited(int v) {
		return visited[v];
	}
}
