package graph.dfs.npartite;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

import graph.Graph;

public class App {

	public static void main(String[] args) throws FileNotFoundException {
		Graph g = new Graph(new Scanner(new File("src/graph/mediumG.txt")));
		
		Npartite npartite = new Npartite(g);
		
		if(npartite.isPartite()) {
			System.out.println("The graph is N-Partite whit N equals to: " + npartite.npartite());
			for (int i = 0; i < npartite.npartite(); i++) {
				System.out.println("Partition "+(i+1)+":");
				for(int v : npartite.partitions(i)) {
					System.out.printf("%d ", v);
				}
				System.out.println();
			}
		}
		else {
			System.out.println("The graph is not N-Partite");
		}
	}
}
