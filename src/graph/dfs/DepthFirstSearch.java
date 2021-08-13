package graph.dfs;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

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
	
	public static void main(String[] args) throws FileNotFoundException {
		
		Graph g = new Graph(new Scanner(new File("src/graph/tiny.txt")));
		int s = 1;
		DepthFirstSearch srch = new DepthFirstSearch(g, s);
		
		for (int v = 0; v < g.getV(); v++) {
			if(srch.isVisited(v)) {
				System.out.println(v + " ");
			}
		}
		
		System.out.println();
		if(srch.getCount() != g.getV()) 
			System.out.println("Not Connected");
		else
			System.out.println("Connected");
	
	}
	
}
