package vectormath;

import java.util.Arrays;

public class VectorMath {

	double[] v;

	public VectorMath(double[] sum) {
		this.v = sum;
	}
	
	public VectorMath sumaVectores(VectorMath vec) {
		
		this.validarDimensiones(vec);		
		double[] sum = new double[vec.v.length];

		for (int i = 0; i < sum.length; i++) {
			sum[i] = vec.v[i] + v[i];
		}
		return new VectorMath(sum);
	}
	
	public VectorMath restaVectores(VectorMath vec) {
		this.validarDimensiones(vec);
		
		double[] resta = new double[vec.v.length];
		
		for(int i = 0; i < resta.length; i++) {
			resta[i] = v[i] - vec.v[i]; 
		}
		
		return new VectorMath(resta);
	}
	
	public VectorMath productoVectorVector(VectorMath vec) {
		this.validarDimensiones(vec);
		
		double[] producto = new double[vec.v.length];
		for (int i = 0; i < producto.length; i++) {
			producto[i] = v[i] * vec.v[i];
		}
		
		return new VectorMath(producto);
	}
	
	/*public VectorMath productoVectorMatriz(MatrizMath m) {
		
	}*/
	
	public VectorMath productoVectorReal(double d) {
		
		double [] vec = new double [v.length]; 
		for (int i = 0; i < v.length; i++) {
			vec[i] = v[i] * d;
		}	
		return new VectorMath(vec);
	}
	
	public double normaUno() {
		
		double sum = 0;
		
		for (int i = 0; i < v.length; i++) {
			sum += Math.abs(v[i]);
		}
		
		return Math.sqrt(sum);
	}
	
	public double normaDos() {
		
		double sum = 0;
		
		for (int i = 0; i < v.length; i++) {
			sum += Math.pow(v[i], 2);
		}
		
		return Math.sqrt(sum);
	}
	
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		VectorMath other = (VectorMath) obj;
		return Arrays.equals(v, other.v);
	}
	
	
	
	@Override
	protected VectorMath clone() {
		return new VectorMath(v);
	}

	@Override
	public String toString() {
		
		String res = "[ ";
		for (int i = 0; i < v.length; i++) {
			
			res += v[i];
			
			if(i != v.length-1)
				res += ", ";
		}
		
		
		//0800-999-9364
		
		res += "]";
		
		return res;
		
	}

	private void validarDimensiones(VectorMath vec) {
		if(v.length != vec.v.length)
			throw new ArithmeticException("Dimesiones diferentes");
	}
}
