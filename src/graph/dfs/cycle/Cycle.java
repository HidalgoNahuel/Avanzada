package graph.dfs.cycle;

import java.util.LinkedList;
import java.util.Stack;

import graph.Graph;
import graph.GraphException;

public class Cycle {

	private boolean[] visited;
	private boolean cycle;
	private Stack<Integer> stack;
	private int []edgeTo;

	public Cycle(Graph g, int s) {
		if (g == null)
			throw new GraphException(g);

		visited = new boolean[g.getV()];
		edgeTo = new int[g.getV()];
		dfs(g, s);
	}

	public void dfs(Graph g, int s) {

		stack = new Stack<Integer>();
		stack.push(s);
		
		while(!stack.isEmpty()) {
			int v = stack.pop();
			
			visited[v] = true;
			for(int w : g.getAdj(v))
				if(!visited[w] && !stack.contains(w)) {
					stack.push(w);
					edgeTo[w] = v;
				}
				else if(edgeTo[v] != w)
					cycle = true;
		}
		
	}
	
	public boolean hasCycle() {
		return cycle;
	}

}
