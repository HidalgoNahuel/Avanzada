package graph.edgeWeighted.kruskal;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

import fundamentals.uf.UF;
import graph.Edge;
import graph.GraphException;
import graph.bfs.Queue;
import graph.edgeWeighted.EdgeWeightedGraph;
import sorting.pq.MinPQ;

public class KruskalMST {

	private double weight;
	private MinPQ<Edge> pq;
	private Queue<Edge> mst;
	
	public KruskalMST(EdgeWeightedGraph g) {
		if(g == null)
			throw new GraphException(g);
		
		mst = new Queue<Edge>();
		
		pq = new MinPQ<Edge>(g.getE());
		for(Edge e : g.getEdges()) {
			pq.insert(e);
		}
		
		UF uf = new UF(g.getV());
		for (int i = 0; i < g.getE() && mst.size() < g.getV() - 1; i++) {
			Edge e = pq.delMin();
			int v = e.either();
			int w = e.other(v);
			
			if(uf.find(v) != uf.find(w)) {
				uf.union(v, w);
				mst.enqueue(e);
				weight+=e.weight();
			}
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
		KruskalMST mst = new KruskalMST(ewg);
		
		for(Edge e : mst.edges()) {
			System.out.println(e);
		}
		
		System.out.println(mst.weight());
	}
}
