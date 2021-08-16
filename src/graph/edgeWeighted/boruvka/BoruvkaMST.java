package graph.edgeWeighted.boruvka;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

import fundamentals.uf.UF;
import graph.Edge;
import graph.GraphException;
import graph.bfs.Queue;
import graph.edgeWeighted.EdgeWeightedGraph;
import sorting.pq.MinPQ;

public class BoruvkaMST {

	private UF uf;
	private Queue<Edge> mst;
	private double weight;

	public BoruvkaMST(EdgeWeightedGraph g) {
		if (g == null)
			throw new GraphException(g);

		mst = new Queue<Edge>();
		uf = new UF(g.getV());

		for (int v = 0; v < g.getV(); v++) {
			boruvka(g, v);
		}

	}

	private void boruvka(EdgeWeightedGraph g, int s) {

		Edge[] min = new Edge[g.getV()];
		for (Edge e : g.getEdges()) {
			int v = e.either(), w = e.other(v);

			if (uf.find(v) == uf.find(w))
				continue;
			if (min[uf.find(v)] == null || e.compareTo(min[uf.find(v)]) < 0)
				min[v] = e;
			if (min[uf.find(w)] == null || e.compareTo(min[uf.find(w)]) < 0)
				min[uf.find(w)] = e;
		}

		for (int i = 0; i < g.getV(); i++) {
			Edge e = min[i];
			if (e != null) {
				int v = e.either(), w = e.other(v);
				if (uf.find(v) != uf.find(w)) {
					mst.enqueue(e);
					uf.union(v, w);
					weight += e.weight();
				}
			}
		}
	}

	public Iterable<Edge> edges() {
		return mst;
	}
	
	public double weight() {
		return weight;
	}

	public static void main(String[] args) throws FileNotFoundException {
		EdgeWeightedGraph ewg = new EdgeWeightedGraph(new Scanner(new File("src/graph/edgeWeighted/tinyEWG.txt")));
		BoruvkaMST mst = new BoruvkaMST(ewg);
		for (Edge e : mst.edges()) {
			System.out.println(e);
		}
		System.out.printf("%.5f\n", mst.weight());
	}
}
