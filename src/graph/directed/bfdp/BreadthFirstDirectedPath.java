package graph.directed.bfdp;

import java.util.Collections;
import java.util.LinkedList;

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
		q.enqueue(s);
		
		while(!q.isEmpty()) {
			int v = q.dequeue();
			visited[v] = true;
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
		
		for(int x = v; x != s; x = edgeTo[x])
			path.add(x);
		path.add(s);
		
		Collections.reverse(path);
		return path;
	}
}
