package vectormath;

public class App {

	public static void main(String[] args) {
			
		Reader rd = new Reader("src/myVector.in");
		
		double [] vec = new double[rd.readInt()];
		int i = 0;

		while(rd.hasNext()) {
			vec[i] = rd.readDouble();
			i++;
		}

		VectorMath vm = new VectorMath(vec);
		
		System.out.println("Vector Base " + vm);
		VectorMath vecProdReal = vm.productoVectorReal(2);
		System.out.println("Vector Producto Real " + vecProdReal);
		
		VectorMath vecResta = vm.restaVectores(vecProdReal);
		System.out.println("Vector Resta " + vecResta);
		
		VectorMath vecSuma = vm.sumaVectores(vecProdReal);
		System.out.println("Vector Suma " + vecSuma);
		
		double normaUno = vm.normaUno();
		System.out.println("Norma Uno " + normaUno);
		
		double normaDos = vm.normaDos();
		System.out.println("Norma Dos " + normaDos);
	}

}
