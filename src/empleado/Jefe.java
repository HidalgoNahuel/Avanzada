package empleado;

public class Jefe extends Empleado{

	private String sector;

	public Jefe(int legajo, String nombre, double salario, String sector) {
		super(legajo, nombre, salario);
		this.sector = sector;
	}

	@Override
	public boolean equals(Object obj) {
		if(this == obj)
			return true;
		if(obj == null)
			return false;
		if(getClass() != obj.getClass())
			return false;
		return sector == ((Jefe)obj).sector && getLegajo() == ((Jefe)obj).getLegajo() && getNombre() == ((Jefe)obj).getNombre();
	}
	

}
