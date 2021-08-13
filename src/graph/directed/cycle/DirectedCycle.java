package graph.directed.cycle;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Collections;
import java.util.Scanner;
import java.util.Stack;

import graph.GraphException;
import graph.directed.Digraph;

public class DirectedCycle {

	private boolean[] visited;
	private int[] edgeTo;
	private Stack<Integer> cycle;

	private Stack<Integer> stack;

	public DirectedCycle(Digraph g) {
		if (g == null)
			throw new GraphException(g);

		visited = new boolean[g.getV()];
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

			int v = stack.peek();
			if (!visited[v]) {
				visited[v] = true;
				for (int w : g.getAdj(v)) {
					if (cycle != null) {
						return;
					} else if (stack.contains(w) && visited[w]) {
						cycle = new Stack<Integer>();
						for (int x = v; x != w; x = edgeTo[x])
							cycle.push(x);
						cycle.push(w);
						cycle.push(v);
						Collections.reverse(cycle);
					} else if (!visited[w]) {
						edgeTo[w] = v;
						stack.push(w);
					}
				}
			} else
				stack.pop();
		}
	}

	public Iterable<Integer> cycle() {
		return cycle;
	}

	public boolean hasCycle() {
		return cycle != null;
	}
	
	public static void main(String[] args) throws FileNotFoundException{
		Digraph dg = new Digraph(new Scanner(new File("src/graph/directed/tinyDAG.txt")));
		DirectedCycle dc = new DirectedCycle(dg);
		
		if(dc.hasCycle()) {
			System.out.printf("Directed Cycle: ");
			for(int v : dc.cycle())
				System.out.printf(v + " ");
		}
		else
			System.out.println("No directed cycle");

	}
}
