package graph.dfs.cycle;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

import graph.Graph;

public class App {

	public static void main(String[] args) throws FileNotFoundException {
		
		Graph g = new Graph(new Scanner(new File("src/graph/tiny.txt")));
		int s = 2;
		
		Cycle c = new Cycle(g, s);
		
		for (int v = 0; v < g.getV(); v++) {
			if(c.cycle(v)) {
				System.out.println(v + " has cycle");
			}
			else
				System.out.println(v + " has not cycle");
		}

	}

}
