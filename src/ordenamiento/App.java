package ordenamiento;

public class App {

	public static void main(String[] args) {
		
		double [] vec = {50, 26, 7, 9, 15, 27};		
		double [] vec2 = {50, 26, 7, 9, 15, 27};	
		double [] vec3 = {50, 26, 7, 9, 15, 27};	
		double [] vec4 = {50, 26, 7, 9, 15, 27};	
		double [] vec5 = { 23, 31, 1, 21, 36, 72};	
		double [] vec6 = {50, 26, 7, 9, 15, 27};	
		
		Vector vector = new Vector(vec);
		Vector vector2 = new Vector(vec2);
		Vector vector3 = new Vector(vec3);
		Vector vector4 = new Vector(vec4);
		Vector vector5 = new Vector(vec5);
		Vector vector6 = new Vector(vec6);
		
		System.out.println("Vector Desordenado" + vector);
		vector.seleccion();
		System.out.println("Vector Ordenado Seleccion" + vector);
		
		System.out.println();
		System.out.println("Vector Desordenado " + vector2);
		vector2.burbujeo();
		System.out.println("Vector Ordenado Burbujeo" + vector2);
		
		System.out.println();
		System.out.println("Vector Desordenado " + vector3);
		vector3.insercion();
		System.out.println("Vector Ordenado Insersion " + vector3);
				
		System.out.println();
		System.out.println("Vector Desordenado " + vector4);
		vector4.shellSort();
		System.out.println("Vector Ordenado Shell " + vector4);
		
		System.out.println();
		System.out.println("Vector Desordenado " + vector5);
		vector5.quickSort(0, (vector.getLength()-1));
		System.out.println("Vector Ordenado QuickSort " + vector5);
		
		System.out.println();
		System.out.println("Vector Desordenado " + vector6);
		vector6.mergeSort(0, (vector.getLength()-1));
		System.out.println("Vector Ordenado MergeSort " + vector6);
	}

}
