package punto;

public class Punto3D extends Punto2D{

	private double z;
	
	public Punto3D(double x, double y, double z) {
		super(x, y);
		this.z = z;
	}
	
	@Override
	public boolean equals(Object obj) {
		return super.equals(obj);
	}
	
	public double getZ() {
		return z;
	}
	 
	
}
