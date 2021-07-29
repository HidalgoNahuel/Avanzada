package practica3_1;

public class App {

	public static void main(String[] args) {

		Punto2D p2D = new Punto2D(0,0);
		Punto3D p3D = new Punto3D(0,0,1);
		Punto3D a3D = null;
		System.out.println(p2D.equals(p3D)); // false
		System.out.println(p3D.equals(p2D)); // false
		System.out.println(p2D.equals(a3D)); // false
		System.out.println(p3D.equals(a3D)); // false
		a3D = new Punto3D(0,0,0);
		System.out.println(p2D.equals(a3D)); // false
		System.out.println(a3D.equals(p2D)); // false

	}

}
