package graph.directed.dfs;

import java.util.Stack;

import graph.GraphException;
import graph.directed.Digraph;

public class DirectedDFS {

	private boolean []visited;
	private Stack<Integer>stack;
	private int count;
	
	public DirectedDFS(Digraph g, int v) {
		if(g == null)
			throw new GraphException(g);
		
		visited = new boolean[g.getV()];
		stack = new Stack<Integer>();
		dfs(g, v);
	}
	
	private void dfs(Digraph g, int s) {
		stack.push(s);
		while(!stack.isEmpty()) {
			int v = stack.pop();
			if(!visited[v]) {
				visited[v] = true;
				for(int w : g.getAdj(v)) {
					if(!visited[w]) {
						count++;
						stack.push(w);
					}
				}
			}
		}
	}
	
	public boolean isVisited(int v) {
		validateVertex(v);
		return visited[v];
	}
	
	public void validateVertex(int v) {
		if(v < 0 || v >= visited.length)
			throw new GraphException(v);
	}
	
	public int count() {
		return count;
	}
	
}
