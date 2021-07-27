package complejo;

public class Complejo {

	private double real, img;

	public Complejo() {
		this.real = 0.0;
		this.img = 0.0;
	}
	
	public Complejo(double real, double img) {
		this.real = real;
		this.img = img;
	}
	
	public Complejo suma(Complejo c) {
		return new Complejo(real + c.real, img + c.img);
	}
	
	public Complejo restar(Complejo c) {
		return new Complejo(real - c.real, img - c.img);
	}
	
	public Complejo multiplicar(Complejo c) {
		return new Complejo(real * c.real, img * c.img);
	}

	public Double modulo() {
		return Math.sqrt(real * real + img * img);
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Complejo other = (Complejo) obj;
		return Double.doubleToLongBits(img) == Double.doubleToLongBits(other.img)
				&& Double.doubleToLongBits(real) == Double.doubleToLongBits(other.real);
	}
	
	@Override
	public Complejo clone() {
		return new Complejo(real, img);
	}

	@Override
	public String toString() {
		return real + "," + img;  
	}
}
