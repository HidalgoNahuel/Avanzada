package graph;

import java.util.LinkedList;
import java.util.NoSuchElementException;
import java.util.Scanner;
import java.util.Stack;
import java.util.Vector;

public class Graph {

	private final int V;
	private int E;
	private Vector<LinkedList<Integer>>adj;
	private int[] grado;
	
	public Graph(int V) {
		if(V < 0) 
			throw new GraphException(V);
		this.V = V;
		this.E = 0;
		
		adj = new Vector<>(V);
		grado = new int[V];
		for (int v = 0; v < V; v++) {
			adj.add(new LinkedList<Integer>());
		}
	}
	
	public Graph(Scanner in) {
		
		if(in == null)
			throw new GraphException(in);
		
		this.V = in.nextInt();
		try {
			adj = new Vector<>(V);
			grado = new int[V];
			
			for(int v = 0; v < V; v++) {
				adj.add(new LinkedList<Integer>());
			}
		
			this.E = in.nextInt();
			if(E < 0)
				throw new GraphException(E);
			for(int i = 0; i < E; i++) {
				addEdge(in.nextInt(), in.nextInt());
			}
		} catch(NoSuchElementException e) {
			throw new IllegalArgumentException("Formato de entrada No valido", e);
		}
	}
	
	public Graph(Graph G) {
		if(G == null)
			throw new GraphException(G);
		
		this.V = G.V;
		this.E = G.E;
		
		if(V < 0)
			throw new GraphException(V);
		
		adj = new Vector<>(V);
		grado = new int[V];
		
		for(int v = 0; v < V; v++) {
			adj.add(new LinkedList<Integer>());
		}
		
		for(int v = 0; v < G.V; v++) {
			Stack<Integer> reverse = new Stack<Integer>();
			
			for(int w : G.adj.get(v)) {
				reverse.push(w);
			}
			
			for(int w : reverse)
				adj.get(v).add(w);
		}
	}

	private void validateVertex(int v) {
		if(v < 0 || v >= V-1) {
			throw new GraphException(v);
		}
	}
	
	public  void addEdge(int v, int w) {
		validateVertex(v);
		validateVertex(w);
		
		adj.get(v).add(w);
		grado[w]++;
		E++;
	}

}
