package graph.dfs.cycle;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

import graph.Graph;

public class App {

	public static void main(String[] args) throws FileNotFoundException {
		
		Graph g = new Graph(new Scanner(new File("src/graph/tiny.txt")));
		int s = 7;
		
		Cycle c = new Cycle(g, s);
		if(c.hasCycle()) {
			System.out.println("Has cycle");
		}
		else
			System.out.println("Has not cycle");
		
		System.out.println();
		for (int v : c.cycle()) {
			System.out.printf("-%d", v);
		}
	}
}
