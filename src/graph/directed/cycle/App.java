package graph.directed.cycle;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

import graph.directed.Digraph;

public class App {

	public static void main(String[] args) throws FileNotFoundException{
		Digraph dg = new Digraph(new Scanner(new File("src/graph/directed/tinyDAG.txt")));
		DirectedCycle dc = new DirectedCycle(dg);
		
		if(dc.hasCycle()) {
			System.out.printf("Directed Cycle: ");
			for(int v : dc.cycle())
				System.out.printf(v + " ");
		}
		else
			System.out.println("No directed cycle");

	}

}
