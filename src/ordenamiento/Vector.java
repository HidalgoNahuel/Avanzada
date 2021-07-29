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
	
	public void mergeSort(int l, int r) {
		if(l<r) {
			int m = l+(r-l)/2;
			mergeSort(l, m);
			mergeSort(m+1, r);
			merge(l, m,r);
		}
	}
	
	public void merge(int l, int m, int r) {
		int n1 = m-l+1;
		int n2 = r-m;
		
		double L[] = new double[n1];
		double R[] = new double[n2];
		
		for (int i = 0; i < L.length; i++) {
			L[i] = vector[l+i];
		}
		
		for (int i = 0; i < R.length; i++) {
			R[i] = vector[m+1+i];
		}
		int i = 0, j = 0, k = l;
		
		while(i < n1 && j < n2) {
			if(L[i]<=R[j]) {
				vector[k] = L[i];
				i++;
			}
			else {
				vector[k] = R[j];
				j++;
			}
			k++;
				
		}
		
		while(i<n1) {
			vector[k] = L[i];
			i++;
			k++;
		}
		while(j<n2) {
			vector[k] = R[j];
			j++;
			k++;
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
