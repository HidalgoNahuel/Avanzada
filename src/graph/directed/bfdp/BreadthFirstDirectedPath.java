package graph.directed.bfdp;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Collections;
import java.util.LinkedList;
import java.util.Scanner;

import graph.GraphException;
import graph.bfs.Queue;
import graph.directed.Digraph;

public class BreadthFirstDirectedPath {

	private boolean []visited;
	private int []distTo;
	private int []edgeTo;
	private Queue<Integer>q;
	private final int s;
	
	public BreadthFirstDirectedPath(Digraph g, int s) {
		if(g == null)
			throw new GraphException(g);
		
		this.s = s;

		visited = new boolean[g.getV()];
		distTo = new int[g.getV()];
		edgeTo = new int[g.getV()];
		
		q = new Queue<Integer>();
		validateVertex(s);
		bfs(g, s);
	}
	
	private void bfs(Digraph g, int s) {
		visited[s] = true;
		distTo[s] = 0;
		q.enqueue(s);
		
		while(!q.isEmpty()) {
			int v = q.dequeue();
			for(int w : g.getAdj(v)) {
				if(!visited[w]) {
					visited[w] = true;
					edgeTo[w] = v;
					distTo[w] = distTo[v] + 1;
					q.enqueue(w);
				}
			}
		}
	}
	
	private void validateVertex(int v) {
		if(v < 0 || v >= visited.length) {
			throw new GraphException(v);
		}
	}
	
	public boolean hasPathTo(int v) {
		return visited[v];
	}
	
	public Iterable<Integer> pathTo(int v){
		LinkedList<Integer> path = new LinkedList<Integer>();
		
		for(int x = v; distTo[x] != 0; x = edgeTo[x])
			path.add(x);
		path.add(s);
		
		Collections.reverse(path);
		return path;
	}
	
	public int distTo(int v) {
		return distTo[v];
	}
	
	public static void main(String[] args) throws FileNotFoundException{
		Digraph dg = new Digraph(new Scanner(new File("src/graph/directed/tinyDG.txt")));
		
		int s = 3;
		BreadthFirstDirectedPath bfdp = new BreadthFirstDirectedPath(dg, s);

		for(int v = 0; v < dg.getV(); v++) {
			if(bfdp.hasPathTo(v)) {
				System.out.printf("%d to %d (Distance = %d): ", s, v, bfdp.distTo(v));
				for(int w : bfdp.pathTo(v))
					System.out.printf(w + " ");
				System.out.println();
			}
			else {
				System.out.printf("%d to %d Not connected\n", s, v);
			}
		}
	}

}
