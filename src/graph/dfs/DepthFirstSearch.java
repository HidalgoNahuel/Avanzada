package graph.dfs;

import graph.Graph;
import graph.GraphException;

public class DepthFirstSearch {
	
	private boolean[] visited;
	private int count;
	
	public DepthFirstSearch(Graph G, int s) {
		
		if(G == null)
			throw new GraphException(G);
		
		visited = new boolean[G.getV()];
		validateVertex(s);
		
		dfs(G,s);
	}
	
	public boolean isVisited(int v) {
		return visited[v];
	}
	
	public int getCount() {
		return count;
	}
	
	private void dfs(Graph G, int v) {
		count++;
		
		visited[v] = true;
		
		for(int w : G.getAdj(v)) {
			if(!visited[w])
				dfs(G,w);
		}
	}
	
	private void validateVertex(int v) {
		if(v < 0 || v >= visited.length)
			throw new GraphException(v);
	}
	
	
}
