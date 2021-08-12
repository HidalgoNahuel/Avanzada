package graph.directed.hamiltonian;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Iterator;
import java.util.Scanner;

import graph.GraphException;
import graph.directed.Digraph;
import graph.directed.Topological;

public class DirectedHamiltonianPath {

	private Iterable<Integer> order;
	
	public DirectedHamiltonianPath(Digraph g) {
		if(g == null)
			throw new GraphException(g);
		
		order = new Topological(g).order();
		Iterator<Integer> iterator = order.iterator();
		
		int v = iterator.next();
		
		while(iterator.hasNext() && order != null) {
			if(!g.getAdj(v).contains((v = iterator.next()))) {
				order = null;
			}
		}

	}
	
	public boolean hasPath() {
		return order != null;
	}
	
	public Iterable<Integer> path(){
		return order;
	}
	
	public static void main(String[] args) throws FileNotFoundException{
		Digraph dg = new Digraph(new Scanner(new File("src/graph/directed/tinyDAG.txt")));
		DirectedHamiltonianPath dhp = new DirectedHamiltonianPath(dg);
		
		if(dhp.hasPath()) {
			System.out.println("Hamiltonian path:");
			for(int v : dhp.path())
				System.out.printf(v + " ");
		}
		else
			System.out.println("No Hamiltonian path");
	}
}
