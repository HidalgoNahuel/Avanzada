package graph.dfs.npartite;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.LinkedList;
import java.util.Scanner;
import java.util.Stack;
import java.util.Vector;

import graph.Graph;
import graph.GraphException;

public class Npartite {

	private boolean[]visited;
	private int n = 0;
	
	private Stack<Integer> stack;
	private Vector<LinkedList<Integer>>partitions;
	
	public Npartite(Graph g) {
		if(g == null)
			throw new GraphException(g);
		
		visited = new boolean[g.getV()];
		stack = new Stack<Integer>();		
		partitions = new Vector<LinkedList<Integer>>();
		
		for(int v = 0; v < g.getV(); v++) {
			if(!visited[v]) {
				partitions.add(new LinkedList<Integer>());
				dfs(g,v);
				n++;
			}
		}
	}
	
	public void dfs(Graph g, int s) {
		
		stack.push(s);
		partitions.get(n).add(s);
		
		while(!stack.isEmpty()) {
			int v = stack.pop();
		
			if(!visited[v]) {
				visited[v] = true;
				for(int w : g.getAdj(v)) {
					if(!visited[w]) {
						visited[w] = true;
						stack.push(w);
						partitions.get(n).add(w);
					}
				}
			}
		}
	}
	
	public int npartite() {
		return n;
	}
	
	public boolean isPartite() {
		return n > 1;
	}
	
	public Iterable<Integer>partitions(int i){
		return partitions.get(i);
	}
	
	public static void main(String[] args) throws FileNotFoundException {
		Graph g = new Graph(new Scanner(new File("src/graph/mediumG.txt")));
		
		Npartite npartite = new Npartite(g);
		
		if(npartite.isPartite()) {
			System.out.println("The graph is N-Partite whit N equals to: " + npartite.npartite());
			for (int i = 0; i < npartite.npartite(); i++) {
				System.out.println("Partition "+(i+1)+":");
				for(int v : npartite.partitions(i)) {
					System.out.printf("%d ", v);
				}
				System.out.println();
			}
		}
		else {
			System.out.println("The graph is not N-Partite");
		}
	}
}
