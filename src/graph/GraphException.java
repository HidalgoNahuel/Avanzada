package graph;

import java.util.Scanner;

public class GraphException extends RuntimeException{

	private static final long serialVersionUID = -8434650095866525824L;

	public GraphException(int v) {
		if(v < 0 )
			throw new IllegalArgumentException("Vertice negativo");
		throw new IllegalArgumentException("Supera el maximo de vertices");
	}
	
	public GraphException(Graph g) {
		if(g == null)
			throw new IllegalArgumentException("Grafo nulo");
	}
	
	public GraphException(Scanner in) {
		if(in == null) {
			throw new IllegalArgumentException("Argumento nulo");
		}
	}
}
