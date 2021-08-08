package graph.directed.order;

import java.util.LinkedList;
import java.util.Stack;

import graph.GraphException;
import graph.bfs.Queue;
import graph.directed.Digraph;

public class DepthFirstOrder {

	private boolean []visited;
	
	private Stack<Integer> stack;
	private int []edgeTo;
	private LinkedList<Integer> path;
	
	private Queue<Integer> preOrder;
	private Queue<Integer> postOrder;
	private Stack<Integer> reverse;
	
	public DepthFirstOrder(Digraph g) {
		if(g == null)
			throw new GraphException(g);
		
		visited = new boolean[g.getV()];
		edgeTo = new int[g.getV()];

		path = new LinkedList<Integer>();
		stack = new Stack<Integer>();
	
		preOrder = new Queue<Integer>();
		postOrder = new Queue<Integer>();
		reverse = new Stack<Integer>();
		
		for(int v = 0; v < g.getV(); v++) {
			if(!visited[v])
				dfs(g, v);
		}
	}
	
	private void dfs(Digraph g, int s) {
		stack.push(s);
		
		while(!stack.isEmpty()) {
			int v = stack.pop();
			
			if(!preOrder.contains(v))
				preOrder.enqueue(v);
			
			if(!visited[v]) {
				visited[v] = true;
				path.add(v);
				for(int w : g.getAdj(v)) {
					if(!visited[w]) {
						stack.push(w);
						edgeTo[w] = v;
					}
				}
				if(g.getAdj(v).size() == 0) {
					for(int x = v; g.degree(x) <= 1; x = edgeTo[x]) {
						postOrder.enqueue(path.getLast());
						reverse.add(0, path.removeLast());
					}
					if(path.isEmpty()) {
						postOrder.enqueue(path.getLast());
						reverse.push(path.removeLast());
					}
				}					
			}
		}
		while(!path.isEmpty()) {
			postOrder.enqueue(path.getLast());
			reverse.add(0, path.removeLast());
		}
	}
	
	public Iterable<Integer> pre(){
		return preOrder;
	}
	
	public Iterable<Integer> post(){
		return postOrder;
	}
	
	public Iterable<Integer> reverse(){
		return reverse;
	}
}
