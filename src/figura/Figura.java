package figura;

import punto.Punto2D;

public abstract class Figura {

	Punto2D centro;

	public Figura(double x, double y) {
		centro = new Punto2D(x, y);
	}
	
	public abstract double area();
	
	public Punto2D getCentro() {
		return centro;
	}
}
