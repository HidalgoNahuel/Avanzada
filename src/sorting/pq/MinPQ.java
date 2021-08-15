package sorting.pq;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Comparator;
import java.util.Iterator;
import java.util.NoSuchElementException;
import java.util.Scanner;

import graph.Edge;

public class MinPQ<T> implements Iterable<T>{

	private T[] pq;
	private int n;
	private Comparator<T> cmp;
	
	@SuppressWarnings("unchecked")
	public MinPQ(int init) {
		pq = (T[]) new Object[init +1];
		n = 0;
	}
	
	public MinPQ() {
		this(1);
	}
	
	@SuppressWarnings("unchecked")
	public MinPQ(int init, Comparator<T> cmp) {
		this.cmp = cmp;
		pq = (T[]) new Object[init +1];
		n = 0;
	}
	
	@SuppressWarnings("unchecked")
	public MinPQ(T[] k) {
		n = k.length;
		pq = (T[]) new Object[k.length +1];
		for (int i = 0; i < n; i++) 
			pq[i+1] = k[i];
		for (int j = n/2; j >= 1; j--) 
			sink(j);
	}
	
	public boolean isEmpty() {
		return n == 0;
	}
	
	public boolean contains(T t) {
		if(isEmpty())
			return false;
		for(T e : pq) {
			if(e != null && e.equals(t))
				return true;
		}
		return false;
	}
	
	public int size() {
		return n;
	}
	
	public T min() {
		if(isEmpty())
			throw new NoSuchElementException("Priority Queue empty");
		return pq[1];
	}
	
	private void resize(int cap) {
		assert cap > n;
		@SuppressWarnings("unchecked")
		T[] temp = (T[])new Object[cap];
		for (int i = 1; i <= n; i++) {
			temp[i] = pq[i];
		}
		pq = temp;
	}
	
	public void insert(T x) {
		if(n == pq.length - 1)
			resize(2 * pq.length);
		
		pq[++n] = x;
		swim(n);
		assert isMinHeap();
	}
	
	public T delMin() {
		if(isEmpty())
			throw new NoSuchElementException("Priority Queue Empty");
		T min = pq[1];
		exch(1, n--);
		sink(1);
		pq[n+1] = null;
		if((n > 0) && (n == (pq.length - 1)/4))
			resize(pq.length / 2);
		assert isMinHeap();
		return min;
	}
	
	private void swim(int k) {
		while(k > 1 && greater(k /2, k)) {
			exch(k, k /2);
			k = k/2;
		}
	}
	
	private void sink(int k) {
		while(2 * k <= n) {
			int j = 2*k;
			if(j < n && greater(j, j+1))
				j++;
			if(!greater(k, j))
				break;
			exch(k, j);
			k = j;
		}
	}
	
	@SuppressWarnings("unchecked")
	private boolean greater(int i, int j) {
		if(cmp == null) {
			return ((Comparable<T>) pq[i]).compareTo(pq[j]) > 0;
		}
		else
			return cmp.compare(pq[i], pq[j]) > 0;
	}
	
	private void exch(int i, int j) {
		T swp = pq[i];
		pq[i] = pq[j];
		pq[j] = swp;
	}
	
	private boolean isMinHeap() {
		for(int i = 1; i <= n; i++) {
			if(pq[i] == null)
				return false;
		}
		
		for(int i = n+1; i < pq.length; i++) {
			if(pq[i] != null)
				return false;
		}
		
		if(pq[0] != null)
			return false;
		return isMinHeapOrdered(1);
	}
	
	private boolean isMinHeapOrdered(int k) {
		if(k > n)
			return true;
		int left = 2*k;
		int right = 2*k+1;
		if(left <= n && greater(k, left))
			return false;
		if(right <= n && greater(k, right))
			return false;
		return isMinHeapOrdered(left) && isMinHeapOrdered(right);
	}

	@Override
	public Iterator<T> iterator() {
		return new HeapIterator();
	}
	
	private class HeapIterator implements Iterator<T>{
		
		private MinPQ<T> cpy;
		
		public HeapIterator() {
			if(cmp== null)
				cpy = new MinPQ<T>(size());
			else 
				cpy = new MinPQ<T>(size(), cmp);
			
			for(int i = 1; i <= n; i++)
				cpy.insert(pq[i]);
		}
		
		public boolean hasNext() {
			return !cpy.isEmpty();
		}
		
		public void remove() {
			throw new UnsupportedOperationException();
		}
		
		public T next() {
			if(!hasNext())
				throw new NoSuchElementException();
			return cpy.delMin();
		}
	}
	
	@Override
	public String toString() {
		final String NEWLINE = System.getProperty("line.separator");
        StringBuilder s = new StringBuilder();
        for (T item : this) {
            s.append(item);
            s.append(NEWLINE);
        }
        return s.toString();
	}

	public static void main(String[] args) throws FileNotFoundException{
		MinPQ<String> pq = new MinPQ<String>();
		Scanner sc = new Scanner(new File("src/sorting/pq/tinyPQ.txt"));
		while(sc.hasNext()) {
			String next = sc.next();
			if(!next.equals("-"))
				pq.insert(next);
			else if(!pq.isEmpty())
				System.out.printf(pq.delMin() + " ");
		}
		System.out.println("(" + pq.size() + " left on pq)");
	}
}
