package graph.directed.eurelian;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

import graph.directed.Digraph;

public class App {

	public static void main(String[] args) throws FileNotFoundException{
		Digraph g = new Digraph(new Scanner(new File("src/graph/directed/eurelianPath.txt")));
		DirectedEurelianCycle dec = new DirectedEurelianCycle(g);
		if(dec.hasCycle()) {
			System.out.println("Eurelian cycle");
			for(int v : dec.cycle()) {
				System.out.printf(v + " ");
			}
		}
		else
			System.out.println("No Eurelian cycle");
	}
}
