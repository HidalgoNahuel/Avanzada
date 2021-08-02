package graph.dfs.cycle;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

import graph.Graph;

public class App {

	public static void main(String[] args) throws FileNotFoundException{

		Graph g = new Graph(new Scanner(new File("src/graph/tiny.txt")));
		
		Cycle c = new Cycle(g, 2);
		System.out.println(c.hasCycle());
	}

}
