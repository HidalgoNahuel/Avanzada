package graph.directed.dfs;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

import graph.directed.Digraph;

public class App {

	public static void main(String[] args) throws FileNotFoundException{
		
		Digraph dg = new Digraph(new Scanner(new File("src/graph/directed/tinyDG.txt")));
		int s = 2;
		DirectedDFS ddfs = new DirectedDFS(dg, s);
	
		System.out.printf("Vertex Connected to Vertex %d: ", s);
		for(int v = 0; v < dg.getV(); v++) {
			if(ddfs.isVisited(v))
				System.out.printf(v + " ");
		}
	}
}
