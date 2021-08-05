package graph.directed.cycle;

import java.util.Collections;
import java.util.LinkedList;
import java.util.Stack;

import graph.GraphException;
import graph.directed.Digraph;

public class DirectedCycle {

	private boolean[] visited;
	private int[] edgeTo;
	private LinkedList<Integer> onStack;
	private LinkedList<Integer> cycle;

	private Stack<Integer> stack;

	public DirectedCycle(Digraph g) {
		if (g == null)
			throw new GraphException(g);

		visited = new boolean[g.getV()];
		onStack = new LinkedList<Integer>();
		edgeTo = new int[g.getV()];

		stack = new Stack<Integer>();
		for (int v = 0; v < g.getV(); v++) {
			if (!visited[v] && cycle == null) {
				dfs(g, v);
			}
		}
	}

	private void dfs(Digraph g, int s) {
		stack.push(s);
		while (!stack.isEmpty()) {

			int v = stack.pop();
			onStack.add(v);
			if (!visited[v]) {
				visited[v] = true;
				for (int w : g.getAdj(v)) {
					if (cycle != null)
						return;
					else if (!visited[w]) {
						edgeTo[w] = v;
						stack.push(w);
					} else if (onStack.contains(w)) {
						cycle = new LinkedList<Integer>();
						for (int x = v; x != w; x = edgeTo[x])
							cycle.add(x);
						cycle.add(w);
						cycle.add(v);
						Collections.reverse(cycle);
					}
				}
				if (g.getAdj(v).size() == 0) {
					onStack.remove(onStack.indexOf(v));
				}
			}
		}
		onStack.removeAll(onStack);
	}

	public Iterable<Integer> cycle() {
		return cycle;
	}

	public boolean hasCycle() {
		return cycle != null;
	}
}
