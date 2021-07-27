package figura;

public class App {

	public static void main(String[] args) {
		
		Circulo c1 = new Circulo(0, 0, 4);
		c1.dibujar();
		c1.rotar();
		
		Circulo c2 = new Circulo(0,0, 4);
		System.out.println(c2.equals(c1));

		
		Rectangulo r1 = new Rectangulo(0,0,12, 12);
		r1.rotar();
		r1.dibujar();
		
		Rectangulo r2 = new Rectangulo(0,0,12,12);
		System.out.println(r2.equals(r1));
		
		System.out.println("Area del circulo: " + c1.area());
		
		System.out.println("Area del rectangulo: " + r1.area());
	}

}
