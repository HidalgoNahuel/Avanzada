package graph.directed;

import java.util.LinkedList;
import java.util.NoSuchElementException;
import java.util.Scanner;
import java.util.Stack;
import java.util.Vector;

import graph.GraphException;

public class Digraph {

	private static final String NEWLINE = System.getProperty("line.separator");

	private final int V;
	private int E;
	private Vector<LinkedList<Integer>> adj;
	private int[] inDegree;
	private int[] outDegree;

	public Digraph(int V) {
		if (V < 0)
			throw new GraphException(V);
		this.V = V;
		this.E = 0;
		inDegree = new int[V];
		outDegree = new int[V];
		
		adj = new Vector<>(V);
		for (int v = 0; v < V; v++) {
			adj.add(new LinkedList<Integer>());
		}

	}

	public Digraph(Scanner in) {

		if (in == null)
			throw new GraphException(in);

		this.V = in.nextInt();
		try {
			if (V < 0)
				throw new GraphException(V);
			inDegree = new int[V];
			outDegree = new int[V];
			
			adj = new Vector<>(V);
			for (int v = 0; v < V; v++) {
				adj.add(new LinkedList<Integer>());
			}

			int E = in.nextInt();
			if (E < 0)
				throw new GraphException(E);

			for (int i = 0; i < E; i++) {
				int v = in.nextInt();
				int w = in.nextInt();

				validateVertex(v);
				validateVertex(w);
				addEdge(v, w);
			}
		} catch (NoSuchElementException e) {
			e.printStackTrace();
		}
	}

	public Digraph(Digraph G) {
		if (G == null)
			throw new IllegalArgumentException("Argumento nulo");

		this.V = G.getV();
		this.E = G.getE();

		if (V < 0)
			throw new GraphException(V);

		inDegree= new int[V];
		outDegree= new int[V];
		
		for (int v = 0; v < V; v++) {
			this.inDegree[v] = G.inDegree[v];
			this.outDegree[v] = G.outDegree[v];
		}

		adj = new Vector<>(V);
		for (int v = 0; v < V; v++) {
			adj.add(new LinkedList<Integer>());
		}

		for (int v = 0; v < G.getV(); v++) {
			Stack<Integer> reverse = new Stack<Integer>();
			for (int w : G.adj.get(v)) {
				reverse.push(w);
			}
			for (int w : reverse) {
				adj.get(v).add(w);
			}
		}
	}

	public int getV() {
		return V;
	}

	public int getE() {
		return E;
	}

	private void validateVertex(int v) {

		if (v < 0 || v >= V)
			throw new IllegalArgumentException("Vertice" + v + " No esta entre 0 y " + (V - 1));
	}

	public void addEdge(int v, int w) {
		validateVertex(v);
		validateVertex(w);
		adj.get(v).add(w);
		outDegree[v] ++;
		inDegree[w]++;
		E++;
	}
	
	public int inDegree(int v) {
		return inDegree[v];
	}
	
	public int outDegree(int v) {
		return outDegree[v];
	}
	
	public LinkedList<Integer> getAdj(int v){
		return adj.get(v);
	}

	public String toString() {
		StringBuilder s = new StringBuilder();
		s.append(V + " vertices, " + E + " edges " + NEWLINE);
		for (int v = 0; v < V; v++) {
			s.append(String.format("%d: ", v));
			for (int w : adj.get(v)) {
				s.append(String.format("%d ", w));
			}
			s.append(NEWLINE);
		}
		return s.toString();
	}

}
