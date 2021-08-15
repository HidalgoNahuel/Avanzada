package graph.edgeWeighted.prim;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

import graph.Edge;
import graph.GraphException;
import graph.bfs.Queue;
import graph.edgeWeighted.EdgeWeightedGraph;
import sorting.pq.MinPQ;

public class LazyPrimMST {

	private double weight;
	private boolean[] visited;
	private Queue<Edge> mst;
	private MinPQ<Edge> pq;
	
	public LazyPrimMST(EdgeWeightedGraph g) {
		if(g == null)
			throw new GraphException(g);
		
		mst = new Queue<Edge>();
		pq = new MinPQ<Edge>();
		visited = new boolean[g.getV()];
		for (int v = 0; v < g.getV(); v++) {
			if(!visited[v])
				prim(g, v);
		}

	}
	
	private void prim(EdgeWeightedGraph g, int s) {
		visit(g, s);
		while(!pq.isEmpty()) {
			Edge e = pq.delMin();
			int v = e.either(), w = e.other(v);
			assert !visited[v] || !visited[w];
			if(visited[v] && visited[w])
				continue;
			mst.enqueue(e);
			weight += e.weight();
			if(!visited[v])
				visit(g, v);
			if(!visited[w])
				visit(g, w);
		}
	}
	
	private void visit(EdgeWeightedGraph g, int s) {
		assert !visited[s];
		visited[s] = true;
		for(Edge e : g.getAdj(s)) {
			if(!visited[e.other(s)])
				pq.insert(e);
		}
	}
	
	public double weight() {
		return weight;
	}
	
	public Iterable<Edge> edges(){
		return mst;
	}
	
	public static void main(String[] args) throws FileNotFoundException{
		EdgeWeightedGraph ewg = new EdgeWeightedGraph(new Scanner(new File("src/graph/edgeWeighted/tinyEWG.txt")));
		LazyPrimMST mst = new LazyPrimMST(ewg);
		
		for(Edge e : mst.edges()) {
			System.out.println(e);
		}
		System.out.printf("%.2f", mst.weight());
	}
}
