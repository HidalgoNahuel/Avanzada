package graph;

public class Edge implements Comparable<Edge>{

	private final int v;
	private final int w;
	private final double weight;
	
	public Edge(int v, int w, double weight) {
		if(v < 0)
			throw new GraphException(v);
		if(w < 0)
			throw new GraphException(w);
		
		if(Double.isNaN(weight))
			throw new GraphException(weight);
		
		this.v = v;
		this.w = w;
		this.weight = weight;
	}
	
	public int either() {
		return v;
	}
	
	public int other(int s) {
		if(s == v)
			return w;
		else if(s == w)
			return v;
		else
			throw new IllegalArgumentException("Illegal vertex");
	}
	
	public double weight() {
		return weight;
	}

	@Override
	public int compareTo(Edge o) {
		return Double.compare(this.weight, o.weight());
	}

	@Override
	public String toString() {
		return String.format("%3d-%3d %.5f", v,w, weight);
	}
	
	
	
}
