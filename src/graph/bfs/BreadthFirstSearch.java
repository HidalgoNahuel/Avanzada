package graph.bfs;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Collections;
import java.util.LinkedList;
import java.util.Scanner;

import graph.Graph;
import graph.GraphException;

public class BreadthFirstSearch {

	private boolean marked[];
	private int[] distTo;
	private int[] edgeTo;
	private static final int INFINITY = Integer.MAX_VALUE;

	public BreadthFirstSearch(Graph g, int s) {
		if (g == null)
			throw new GraphException(g);

		marked = new boolean[g.getV()];
		distTo = new int[g.getV()];
		edgeTo = new int[g.getV()];
		for (int v = 0; v < g.getV(); v++) {
			distTo[v] = INFINITY;
		}

		validateVertex(s);
		bfs(g, s);
	}

	private void bfs(Graph g, int s) {
		Queue<Integer> q = new Queue<Integer>();
		q.enqueue(s);
		distTo[s] = 0;
		marked[s] = true;

		while (!q.isEmpty()) {
			int v = q.dequeue();
			for (int w : g.getAdj(v)) {
				if (!marked[w]) {
					marked[w] = true;
					distTo[w] = distTo[v] + 1;
					edgeTo[w] = v;
					q.enqueue(w);
				}
			}
		}
	}
	
	public LinkedList<Integer> pathTo(int v){
		validateVertex(v);
		if(!hasPathTo(v))
			return null;
		LinkedList<Integer>path = new LinkedList<Integer>();
		int x;
		for (x = v; distTo[x] != 0; x = edgeTo[x])
            path.add(x);
		path.add(x);
		Collections.reverse(path);;
		return path;
	}
	
	public int distTo(int v) {
		validateVertex(v);
		return distTo[v];
	}
	
	public boolean hasPathTo(int v) {
		validateVertex(v);
		return marked[v];
	}

	private void validateVertex(int s) {
		if (s < 0 || s >= marked.length)
			throw new GraphException(s);
	}
	
	public static void main(String[] args) throws FileNotFoundException {
        Graph G = new Graph(new Scanner(new File("src/graph/tiny.txt")));

        int s = 3;
        BreadthFirstSearch bfs = new BreadthFirstSearch(G, s);

        for (int v = 0; v < G.getV(); v++) {
            if (bfs.hasPathTo(v)) {
                System.out.printf("%3d to %3d (%3d):  ", s, v, bfs.distTo(v));
                for (int x : bfs.pathTo(v)) {
                    if (x == s) 
                    	System.out.printf("%d", x);
                    else        
                     	System.out.printf("-%d", x);
                }
                System.out.println();
            }

            else {
                System.out.printf("%3d to %3d (-):  not connected\n", s, v);
            }

        }
    }
}
