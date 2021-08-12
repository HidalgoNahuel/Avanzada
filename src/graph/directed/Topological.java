package graph.directed;

import graph.GraphException;
import graph.directed.cycle.DirectedCycle;
import graph.directed.order.DepthFirstOrder;

public class Topological {

	private Iterable<Integer> order;
	private int [] rank;
	
	public Topological(Digraph g) {
		DirectedCycle dc = new DirectedCycle(g);
		
		if(!dc.hasCycle()) {
			DepthFirstOrder dfo = new DepthFirstOrder(g);
			order = dfo.reverse();
			
			rank = new int[g.getV()];
			int i = 0;
			for(int v : order) 
				rank[v] = i++;
		}
		
	}
	
	public Iterable<Integer> order(){
		return order;
	}
	
	public boolean hasOrder() {
		return order != null;
	}
	
	public int rank(int v) {
		validateVertex(v);
		if(hasOrder())
			return rank[v];
		else
			return -1;
	}
	
	private void validateVertex(int v) {
		if(v < 0 ||  v > rank.length)
			throw new GraphException(v);
	}
}
