package graph.directed.eurelian;

import java.util.Collections;
import java.util.Iterator;
import java.util.Stack;

import graph.GraphException;
import graph.directed.Digraph;

public class DirectedEurelianCycle {

	private Stack<Integer> stack;
	private Stack<Integer> cycle = null;

	public DirectedEurelianCycle(Digraph g) {
		if (g == null)
			throw new GraphException(g);

		for(int v = 0; v < g.getV(); v++) {
			if(g.outDegree(v) != g.inDegree(v))
				return;
		}
		
		@SuppressWarnings("unchecked")
		Iterator<Integer>[] adj = (Iterator<Integer>[]) new Iterator[g.getV()];
		for(int v = 0; v < g.getV(); v++)
			adj[v] = g.getAdj(v).iterator();
		
		int s = nonIsolatedVertex(g);
		stack = new Stack<Integer>();
		stack.push(s);
		
		cycle = new Stack<Integer>();
		while(!stack.isEmpty()) {
			int v = stack.pop();
			while(adj[v].hasNext()) {
				stack.push(v);
				v = adj[v].next();
			}
			cycle.push(v);
		}
		
		if(cycle.size() != g.getE() + 1)
			cycle = null;
	}

	public int nonIsolatedVertex(Digraph g) {
		for(int v = 0; v < g.getV(); v++) {
			if(g.outDegree(v) > 0)
				return v;
				
		}
		return -1;
	}

	public boolean hasCycle() {
		return cycle != null;
	}

	public Iterable<Integer> cycle() {
		Collections.reverse(cycle);
		return cycle;
	}

}
