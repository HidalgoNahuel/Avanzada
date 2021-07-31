package graph.bfs;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

import graph.Graph;

public class App {

	public static void main(String[] args) throws FileNotFoundException {
        Graph G = new Graph(new Scanner(new File("src/graph/mediumG.txt")));

        int s = 100;
        BreadthFirstSearch bfs = new BreadthFirstSearch(G, s);

        for (int v = 0; v < G.getV(); v++) {
            if (bfs.hasPathTo(v)) {
                System.out.printf("%d to %3d (%3d):  ", s, v, bfs.distTo(v));
                for (int x : bfs.pathTo(v)) {
                    if (x == s) 
                    	System.out.printf("%d", x);
                    else        
                     	System.out.printf("-%d", x);
                }
                System.out.println();
            }

            else {
                System.out.printf("%3d to %3d (-):  not connected\n", s, v);
            }

        }
    }
}
