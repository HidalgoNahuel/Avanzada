package graph.directed.order;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

import graph.directed.Digraph;

public class App {

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
