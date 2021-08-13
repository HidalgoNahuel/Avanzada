package graph.directed.dfs;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;
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
			int v = stack.peek();
			if(!visited[v]) {
				visited[v] = true;
				for(int w : g.getAdj(v)) {
					if(!visited[w]) {
						count++;
						stack.push(w);
					}
				}
			}
			else
				stack.pop();
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
	
	public static void main(String[] args) throws FileNotFoundException{
		
		Digraph dg = new Digraph(new Scanner(new File("src/graph/directed/tinyDG.txt")));
		int s = 2;
		DirectedDFS ddfs = new DirectedDFS(dg, s);
	
		System.out.printf("Vertex Connected to Vertex %d: ", s);
		for(int v = 0; v < dg.getV(); v++) {
			if(ddfs.isVisited(v))
				System.out.printf(v + " ");
		}
	}
	
}
