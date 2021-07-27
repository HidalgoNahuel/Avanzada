package empleado;

public class Empleado {

	private int legajo;
	private String nombre;
	private double salario;
	
	public Empleado(int legajo, String nombre, double salario) {
		this.legajo = legajo;
		this.nombre = nombre;
		this.salario = salario;
	}

	public int getLegajo() {
		return legajo;
	}

	public String getNombre() {
		return nombre;
	}

	public double getSalario() {
		return salario;
	}
	
	
	
}
