package graph.directed.bfdp;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

import graph.directed.Digraph;

public class App {

	public static void main(String[] args) throws FileNotFoundException{
		Digraph dg = new Digraph(new Scanner(new File("src/graph/directed/tinyDG.txt")));
		
		int s = 3;
		BreadthFirstDirectedPath bfdp = new BreadthFirstDirectedPath(dg, s);

		for(int v = 0; v < dg.getV(); v++) {
			if(bfdp.hasPathTo(v)) {
				System.out.printf("%d to %d: ", s, v);
				for(int w : bfdp.pathTo(v))
					System.out.printf(w + " ");
				System.out.println();
			}
			else {
				System.out.printf("%d to %d Not connected\n", s, v);
			}
		}
	}

}
