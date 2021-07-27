package figura;

public class Rectangulo implements Rotable, Dibujable{

	private double ancho, alto;
	
	public Rectangulo(double x, double y, double ancho, double alto) {
		//super(x, y);
		this.ancho = ancho;
		this.alto = alto;
		
	}

	@Override
	public void dibujar() {
		System.out.println("Se dibujó el Rectangulo");
		
	}

	@Override
	public void rotar() {
		System.out.println("Se rotó el Rectangulo");
		
	}

	@Override
	public boolean equals(Object obj) {
		if(this == obj)
			return true;
		if(obj == null)
			return false;
		if(getClass() != obj.getClass())
			return false;
		//return ((Rectangulo)obj).alto == this.alto && ((Rectangulo)obj).ancho == this.ancho && getCentro().equals(((Rectangulo)obj).getCentro());
		return true;
	}

	public double area() {
		return ancho * alto;
	}

}
