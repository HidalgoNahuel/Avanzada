package graph;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class App {

	public static void main(String[] args) throws FileNotFoundException{
		
		Graph g = new Graph(new Scanner(new File("src/graph/tiny.txt")));
		
		System.out.println("Max vertex Degreee: "+g.maxDegree() + " vertex: " + g.maxDegreeVertex());
	}
}
