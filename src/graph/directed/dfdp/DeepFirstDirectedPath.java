package graph.directed.dfdp;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Collections;
import java.util.LinkedList;
import java.util.Scanner;
import java.util.Stack;

import graph.GraphException;
import graph.directed.Digraph;

public class DeepFirstDirectedPath {

	private boolean[] visited;
	private int[] edgeTo;
	private final int s;
	private Stack<Integer> stack;

	public DeepFirstDirectedPath(Digraph g, int s) {
		if (g == null)
			throw new GraphException(g);

		this.s = s;
		visited = new boolean[g.getV()];
		edgeTo = new int[g.getV()];
		stack = new Stack<Integer>();
		dfs(g, s);

	}

	private void dfs(Digraph g, int s) {
		stack.push(s);

		while (!stack.isEmpty()) {
			int v = stack.pop();
			if (!visited[v]) {
				visited[v] = true;
				for (int w : g.getAdj(v)) {
					if (!visited[w]) {
						edgeTo[w] = v;
						stack.push(w);
					}
				}
			}
		}
	}

	public boolean hasPathTo(int v) {
		return visited[v];
	}

	public Iterable<Integer> pathTo(int v) {
		LinkedList<Integer> path = new LinkedList<Integer>();

		for (int x = v; x != s; x = edgeTo[x]) {
			path.add(x);
		}
		path.add(s);

		Collections.reverse(path);
		return path;
	}
	
	public static void main(String[] args) throws FileNotFoundException{
		Digraph dg = new Digraph(new Scanner(new File("src/graph/directed/tinyDG.txt")));

		int s = 3;
		DeepFirstDirectedPath dfdp = new DeepFirstDirectedPath(dg, s);

		for(int v = 0; v < dg.getV(); v++) {
			if(dfdp.hasPathTo(v)) {
				System.out.printf("%d to %d: ", s ,v);
				for(int w : dfdp.pathTo(v)) {
					System.out.printf(w + " ");
				}
				System.out.println();
			}
			else {
				System.out.printf("%d to %d: Not connected\n", s, v);
			}
		}
	}

}
