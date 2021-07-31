package graph.dfs;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

import graph.Graph;

public class App {

	public static void main(String[] args) throws FileNotFoundException {
		
		Graph g = new Graph(new Scanner(new File("src/graph/mediumG.txt")));
		int s = 1;
		DepthFirstSearch srch = new DepthFirstSearch(g, s);
		
		for (int v = 0; v < g.getV(); v++) {
			if(srch.isVisited(v)) {
				System.out.println(v + " ");
			}
		}
		
		System.out.println();
		if(srch.getCount() != g.getV()) 
			System.out.println("Not Connected");
		else
			System.out.println("Connected");
	}
}
