package graph;

import java.io.File;
import java.io.FileNotFoundException;
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
	private int maxDegreePos;
	
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
		
			int E = in.nextInt();
			if(E < 0)
				throw new GraphException(E);
			
			for(int i = 0; i < E; i++) {
				int v = in.nextInt();
				int w = in.nextInt();
				validateVertex(v);
				validateVertex(w);
				addEdge(v, w);
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
	
	public int getV() {
		return V;
	}
	
	public LinkedList<Integer> getAdj(int v) {
		validateVertex(v);
		return adj.get(v);
	}

	private void validateVertex(int v) {
		if(v < 0 || v >= V) {
			throw new GraphException(v);
		}
	}
	
	public  void addEdge(int v, int w) {
		validateVertex(v);
		validateVertex(w);
		
		adj.get(v).add(w);
		adj.get(w).add(v);
		E++;
		grado[v]++;
		grado[w]++;
	}
	
	public int degree(int v) {
		return grado[v];
	}
	
	public int maxDegree() {
		int mayor = 0;
		for (int v = 0; v < grado.length; v++) {
			if(grado[v] > mayor) {
				mayor = grado[v];
				maxDegreePos = v;
			}
		}
		return mayor;
	}
	
	public int maxDegreeVertex() {
		return maxDegreePos;
	}
	
	public static void main(String[] args) throws FileNotFoundException{
		
		Graph g = new Graph(new Scanner(new File("src/graph/tiny.txt")));
		
		System.out.println("Max vertex Degreee: "+g.maxDegree() + " vertex: " + g.maxDegreeVertex());
	}

}
