package graph;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class App {

	public static void main(String[] args) {
		
		Digraph dg;
		try {
			dg = new Digraph(new Scanner(new File("src/graph/mediumG.txt")));
			System.out.println(dg);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
	}
}
