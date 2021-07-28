package ordenamiento;

public class Vector {

	double[] vector;

	public Vector(double[] vector) {
		super();
		this.vector = vector;
	}
	
	public int getLength() {
		return vector.length;
	}

	public void seleccion() {
		double menor, aux;
		int pos;

		for (int i = 0; i < vector.length; i++) {
			
			menor = vector[i];
			pos = i;
			
			for (int j = i + 1; j < vector.length; j++) {

				if (vector[j] < menor) {
					pos = j;
					menor = vector[j];
				}
			}
			
			if(pos != i) {
				aux = vector[i];
				vector[i] = vector[pos];
				vector[pos] = aux;
			}

		}

	}
	
	public void burbujeo() {
		
		double aux;
		for (int i = 0; i < vector.length; i++) {
			for (int j = i+1; j < vector.length; j++) {
				if(vector[j] < vector[i]) {
					aux = vector[i];
					vector[i] = vector[j];
					vector[j] = aux;
				}
			}
		}
	}
	
	public void insercion() {
		for (int i = 1; i < vector.length; i++) {
			double aux = vector[i];
			int j;
			for (j = i-1; j >=0 && vector[j] > aux; j--) {
					vector[j+1] = vector[j];
			}
			vector[j+1] = aux;
		}
	}
	
	public void shellSort() {
		for(int inc = vector.length/2; inc > 0; inc=(inc == 2?inc:(int)Math.round(inc/2.2))) {
			for(int i = inc; i<vector.length;i++)
				for(int j = i; j>=inc && vector[j-inc]>vector[j];j-=inc) {
					double temp = vector[j];
					vector[j]=vector[j-inc];
					vector[j-inc]=temp;
				}
		}
	}
	
	public void quickSort(int a, int b) {
		
		int from = a, to = b;
		double pivot = vector[(from+to)/2];
		do{
			while(vector[from] < pivot){
				from++;
			}
			while(vector[to] > pivot){
				to--;
			}
			if(from <= to) {
				double buff = vector[from];
				vector[from] = vector[to];
				vector[to] = buff;
				from++; to--;
			}
		}while(from <= to);
		
		if(a < to) {
			quickSort(a, to);
		}
		if(from < b) {
			quickSort(from,b);
		}
	}
	
	public void mergeSort(int init, int n) {
		int n1, n2;
		if(n>1) {
			n1 = n /2;
			n2 = n/2;
			mergeSort(init, n1);
			mergeSort(init+n1, n2);
			merge(init, n1,n2);
		}
	}
	
	public void merge(int init, int n1, int n2) {
		double[] buff = new double[n1+n2];
		int tmp = 0;
		int tmp1 = 0;
		int tmp2 = 0;
		
		while((tmp1 < n1) && (tmp2 < n2)) {
			if(vector[init + tmp1] < vector[init + n1 + tmp2])
				buff[tmp++] = vector[init + (tmp1++)];
			else
				buff[tmp++] = vector[init + n1 + (tmp2++)];
		}
		
		while(tmp1 < n1) {
			buff[tmp++] = vector[init + (tmp1++)];
		}
		
		while(tmp2 < n2) {
			buff[tmp++] = vector[init + n1 + (tmp2++)];
		}
		
		for (int i = 0; i < buff.length; i++) {
			vector[init +i] = buff[i];
		}
	}

	@Override
	public String toString() {
		String res = "[ ";
		
		for (double d : vector) {
			res += d;
			res += " ";
		}
		
		res += "]";
		return res;
	}
	
	/*
	 * a) El algoritmo de Burbuja es quien mejor se comporta en una estructura de datso ordenada.
	 * b)
	 */
	
	
	
	/*Complejidad Computacional:
	 * Algoritmo		Operaciones Maximas
	 * Burbuja			O(n^2)			
	 * Insercion		O(n^2/4)
	 * Seleccion		O(n^2)
	 * Shell			O(n log2n)
	 * Merge			O(n logn)
	 * Quick			O(n^2) PEOR CASO ----- O(n logn) PROMEDIO DE CASOS
	 */
}
