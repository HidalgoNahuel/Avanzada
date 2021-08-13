package graph.edgeWeighted;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.LinkedList;
import java.util.Scanner;
import java.util.Stack;
import java.util.Vector;

import graph.Edge;
import graph.GraphException;

public class EdgeWeightedGraph {

	private static final String NEWLINE = System.getProperty("line.separator");
	
	
	private final int V;
	private int E;
	private Vector<LinkedList<Edge>> adj;
	
	public EdgeWeightedGraph(Scanner in) {
		if(in == null)
			throw new GraphException(in);
		
		V = in.nextInt();
		if(V < 0)
			throw new GraphException(V);
		
		adj = new Vector<>(V);
		for(int i = 0; i < V; i++) {
			adj.add(new LinkedList<Edge>());
		}
		
		E = in.nextInt();
		if(E < 0)
			throw new GraphException(E);
		
		for(int i = 0; i < E; i++) {
			int v = in.nextInt();
			int w = in.nextInt();
			validateVertex(v);
			validateVertex(w);
			double weight = Double.valueOf(in.next());
			Edge e = new Edge(v, w, weight);
			addEdge(e);
		}
	}
	
	public EdgeWeightedGraph(int v) {
		if(v < 0)
			throw new GraphException(v);
		
		this.V = v;
		this.E = 0;
		
		adj = new Vector<>(V);
		for (int i = 0; i < V; i++) {
			adj.add(new LinkedList<Edge>());
		}
	}
	
	public EdgeWeightedGraph(EdgeWeightedGraph G) {
		if(G == null)
			throw new GraphException(G);
		
		this.V = G.getV();
		this.E = G.getE();
		
		if(V < 0)
			throw new GraphException(V);
		
		for (int i = 0; i < V; i++) {
			adj.add(new LinkedList<Edge>());
		}
		
		if(E < 0)
			throw new GraphException(E);
		
		for (int v = 0; v < V; v++) {
			Stack<Edge> reverse = new Stack<Edge>();
			for(Edge e : G.getAdj(v)) {
				reverse.push(e);
			}
			
			for(Edge e : reverse) {
				adj.get(v).add(e);
			}
		}
	}
	
	public int getV() {
		return this.V;
	}
	
	public int getE() {
		return this.E;
	}
	
	public Iterable<Edge> getAdj(int v){
		validateVertex(v);
		return adj.get(v);
	}
	
	public Iterable<Edge> getEdges(){
		LinkedList<Edge> list = new LinkedList<Edge>();
		for(int v = 0; v < V; v++) {
			int selfLoops = 0;
			for(Edge e : adj.get(v)) {
				if(e.other(v) > v)
					list.add(e);
				else if(e.other(v) == v) {
					if(selfLoops%2 == 0)
						list.add(e);
					selfLoops++;
				}
			}
		}
		return list;
	}
	
	private void validateVertex(int v) {
		if(v < 0 || v > this.V)
			throw new GraphException(v);
	}
	
	private void addEdge(Edge e) {
		int v = e.either();
		int w = e.other(v);
		adj.get(v).add(e);
		adj.get(w).add(e);
	}
	
    public String toString() {
        StringBuilder s = new StringBuilder();
        s.append(V + " " + E + NEWLINE);
        for (int v = 0; v < V; v++) {
            s.append(v + ": ");
            for (Edge e : adj.get(v)) {
                s.append(e + "  ");
            }
            s.append(NEWLINE);
        }
        return s.toString();
    }
    
    public static void main(String[] args) throws FileNotFoundException{
    	
		EdgeWeightedGraph ewg = new EdgeWeightedGraph(new Scanner(new File("src/graph/edgeWeighted/tinyEWG.txt")));
		System.out.println(ewg);
	}
}
