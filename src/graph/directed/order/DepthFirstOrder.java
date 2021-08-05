package graph.directed.order;

import java.util.Stack;

import graph.GraphException;
import graph.bfs.Queue;
import graph.directed.Digraph;

public class DepthFirstOrder {

	private boolean []visited;
	//private int []preOrder;
	//private int []postOrder;
	private Stack<Integer> stack;
	
	private Queue<Integer> preOrder;
	private Queue<Integer> postOrder;
	
	public DepthFirstOrder(Digraph g) {
		if(g == null)
			throw new GraphException(g);
		
		visited = new boolean[g.getV()];
		//preOrder = new int[g.getV()];
		//postOrder = new int[g.getV()];
		stack = new Stack<Integer>();
		
		preOrder = new Queue<Integer>();
		postOrder = new Queue<Integer>();
		
		for(int v = 0; v < g.getV(); v++) {
			if(!visited[v])
				dfs(g, v);
		}
	}
	
	private void dfs(Digraph g, int s) {
		stack.push(s);
		
		while(!stack.isEmpty()) {
			int v = stack.pop();
			preOrder.enqueue(v);
			if(!visited[v]) {
				visited[v] = true;
				for(int w : g.getAdj(v)) {
					if(!visited[w]) {
						stack.push(w);
					}
				}
			}
			preOrder.enqueue(v);
		}
	}
}
