package graph.edgeWeighted.prim;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

import graph.Edge;
import graph.GraphException;
import graph.bfs.Queue;
import graph.edgeWeighted.EdgeWeightedGraph;
import sorting.pq.IndexMinPQ;

public class PrimMST {

	private Edge[] edgeTo;
	private double[] distTo;
	private boolean[] visited;
	private IndexMinPQ<Double> pq;
	
	public PrimMST(EdgeWeightedGraph g) {
		if(g == null)
			throw new GraphException(g);
		
		edgeTo = new Edge[g.getV()];
		distTo = new double[g.getV()];
		visited= new boolean[g.getV()];
		
		pq = new IndexMinPQ<Double>(g.getV());
		
		for (int v = 0; v < g.getV(); v++) {
			distTo[v] = Double.POSITIVE_INFINITY;
		}
		
		for (int v = 0; v < g.getV(); v++) {
			if(!visited[v])
				prim(g,v);
		}
	}
	
	private void prim(EdgeWeightedGraph g, int s) {
		distTo[s] = 0.0;
		pq.insert(s, distTo[s]);
		while(!pq.isEmpty()) {
			int v = pq.delMin();
			scan(g, v);
		}
	}
	
	private void scan(EdgeWeightedGraph g, int v) {
		visited[v] = true;
		for(Edge e : g.getAdj(v)) {
			int w = e.other(v);
			if(visited[w])
				continue;
			if(e.weight() < distTo[w]) {
				distTo[w] = e.weight();
				edgeTo[w] = e;
				if(pq.contains(w))
					pq.decreaseKey(w, distTo[w]);
				else
					pq.insert(w, distTo[w]);
			}
		}
	}
	
	public Iterable<Edge> edges(){
		Queue<Edge> mst = new Queue<Edge>();
		for (int v = 0; v < edgeTo.length; v++) {
			Edge e = edgeTo[v];
			if(e != null)
				mst.enqueue(e);
		}
		return mst;
	}
	
	public double weight() {
		double w = 0.0;
		for(Edge e : edges())
			w += e.weight();
		return w;
	}
	
    public static void main(String[] args) throws FileNotFoundException{
        EdgeWeightedGraph G = new EdgeWeightedGraph(new Scanner(new File("src/graph/edgeWeighted/tinyEWG.txt")));
        PrimMST mst = new PrimMST(G);
        for (Edge e : mst.edges()) {
        	System.out.println(e);
        }
        System.out.printf("%.5f\n", mst.weight());
    }
}
