package punto;

public class Punto2D {

	private double x;
	private double y;
	
	public Punto2D(double x, double y) {
	
		this.x = x;
		this.y = y;
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		
		return x == ((Punto2D)obj).x && y == ((Punto2D)obj).y;
	}
	
}
