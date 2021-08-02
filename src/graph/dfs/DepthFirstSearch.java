package graph.dfs;

import graph.Graph;
import graph.GraphException;

public class DepthFirstSearch {
	
	private boolean[] visited;
	private int count;
	public int degree[];
	public int maxDegreePos;
	
	public DepthFirstSearch(Graph G, int s) {
		
		if(G == null)
			throw new GraphException(G);
		
		visited = new boolean[G.getV()];
		degree = new int[G.getV()];
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
			degree[w] ++;
			if(!visited[w])
				dfs(G,w);
		}
	}
	
	private void validateVertex(int v) {
		if(v < 0 || v >= visited.length)
			throw new GraphException(v);
	}
	
	public int degree(int v) {
		return degree[v];
	}
	
	public int maxDegree() {
		int mayor = 0;
		for(int v = 0; v < degree.length; v++)
			if(degree[v] > mayor) {
				mayor = degree[v];
				maxDegreePos = v;
			}
		return mayor;
	}
	
	public int maxDegreePos() {
		return maxDegreePos;
	}
	
}
