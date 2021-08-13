package graph.directed.order;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Collections;
import java.util.Scanner;
import java.util.Stack;

import graph.GraphException;
import graph.bfs.Queue;
import graph.directed.Digraph;

public class DepthFirstOrder {

	private boolean []visited;
	
	private Stack<Integer> stack;
	
	private Queue<Integer> preOrder;
	private Queue<Integer> postOrder;
	private Stack<Integer> reverse;
	
	public DepthFirstOrder(Digraph g) {
		if(g == null)
			throw new GraphException(g);
		
		visited = new boolean[g.getV()];
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
			int v = stack.peek();
			
			if(!preOrder.contains(v))
				preOrder.enqueue(v);
			
			if(!visited[v]) {
				visited[v] = true;
				for(int w : g.getAdj(v)) {
					if(!visited[w]) {
						stack.push(w);
					}
				}				
			}
			else {
				int aux = stack.pop();
				if(!reverse.contains(aux)) {
					postOrder.enqueue(aux);
					reverse.push(aux);
				}
			}
		}
	}
	
	public Iterable<Integer> pre(){
		return preOrder;
	}
	
	public Iterable<Integer> post(){
		return postOrder;
	}
	
	public Iterable<Integer> reverse(){
		Collections.reverse(reverse);
		return reverse;
	}
	
	public static void main(String[] args) throws FileNotFoundException{
		
		Digraph dg = new Digraph(new Scanner(new File("src/graph/directed/tinyDAG.txt")));
		DepthFirstOrder dfo = new DepthFirstOrder(dg);
		
		System.out.printf("Preorder:  ");
		for (int v : dfo.pre()) {
			System.out.printf(v + " ");
		}
		System.out.println();
		
		System.out.printf("Postorder: ");
		for (int v : dfo.post()) {
			System.out.printf(v + " ");
		}
		System.out.println();
		
		System.out.printf("Reverse postorder: ");
		for (int v : dfo.reverse()) {
			System.out.printf(v + " ");
		}
		System.out.println();
	}
}
