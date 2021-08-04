package graph.dfs.cycle;

import java.util.LinkedList;
import java.util.Stack;

import graph.Graph;
import graph.GraphException;

public class Cycle {

	private boolean[] visited;
	private int[] edgeTo;
	private LinkedList<Integer> cycle;
	private Stack<Integer> stack;

	public Cycle(Graph g, int s) {
		if (g == null)
			throw new GraphException(g);

		visited = new boolean[g.getV()];
		edgeTo = new int[g.getV()];
		stack = new Stack<Integer>();
		cycle = new LinkedList<Integer>();
		dfs(g,s);
	}

	public void dfs(Graph g, int s) {
		stack.push(s);

		while (!stack.isEmpty()) {

			if (!cycle.isEmpty())
				return;

			int v = stack.pop();
			if(!visited[v]) {
				visited[v] = true;
				for (int w : g.getAdj(v)) {
					if (!visited[w]) {
						edgeTo[w] = v;
						stack.push(w);
					} else if (edgeTo[v] != w) {
						for (int x = v; x != w; x = edgeTo[x]) {
							cycle.push(x);
						}
						cycle.push(w);
					}
				}
			}
		}

	}

	public Iterable<Integer> cycle() {
		return cycle;
	}

	public boolean hasCycle() {
		return !cycle.isEmpty();
	}

}
