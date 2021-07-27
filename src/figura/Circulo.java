package figura;

public class Circulo implements Rotable, Dibujable{

	private double radio;
	
	public Circulo(double x, double y, double radio) {
		//super(x, y);
		this.radio = radio;
	}
	
	/*public Punto2D getCentro() {
		return super.getCentro();
	}*/

	@Override
	public void dibujar() {
		System.out.println("Se dibujó el circulo");
		
	}

	@Override
	public void rotar() {
		System.out.println("Se rotó el circulo");
		
	}
	
	@Override
	public boolean equals(Object obj) {
		if(this == obj)
			return true;
		if(obj == null)
			return false;
		if(getClass() != obj.getClass())
			return false;
		//return ((Circulo)obj).radio == this.radio && ((Circulo)obj).getCentro().equals(getCentro());
		return true;
		
	}
	

	public double area() {
		return Math.PI * Math.pow(radio, 2);
	}

}
