package empleado;

public class App {

	public static void main(String[] args) {
		
		Jefe j1 = new Jefe(12, "Pedro Alonso", 12594.23, "Produccion");
		
		Empleado e1 = new Empleado(12, "Pedo Alonso", 12594.23);
		
		System.out.println(e1.equals(j1));
		System.out.println(j1.equals(e1));

	}

}
