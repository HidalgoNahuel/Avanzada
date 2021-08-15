package sorting.pq;

import java.util.Iterator;
import java.util.NoSuchElementException;

public class IndexMinPQ<T extends Comparable<T>> implements Iterable<Integer> {
	
	private int maxN;
	private int n;
	private int[] pq;
	private int[] qp;
	private T[] ts;
	
	
	@SuppressWarnings("unchecked")
	public IndexMinPQ(int maxN) {
		if(maxN < 0)
			throw new IllegalArgumentException();
		this.maxN = maxN;
		n = 0;
		ts = (T[])new Comparable[maxN+1];
		pq = new int[maxN+1];
		qp = new int[maxN+1];
		for (int i = 0; i < maxN; i++) {
			qp[i] = -1;
		}
	}
	
	public boolean isEmpty() {
		return n == 0;
	}
	
	public boolean contains(int i) {
		validateIndex(i);
		return qp[i] != -1;
	}
	
	public int size() {
		return n;
	}
	
	public void insert(int i, T t) {
		validateIndex(i);
		if(contains(i))
			throw new IllegalArgumentException("Index already in PQ");
		n++;
		qp[i] = n;
		pq[n] = i;
		ts[i] = t;
		swim(n);
	}
	
	public int minIndex() {
		if(n == 0)
			throw new NoSuchElementException("PQ is empty");
		return pq[1];
	}
	
	public T minKey() {
		if(n == 0)
			throw new NoSuchElementException("PQ is empty");
		return ts[pq[1]];
	}
	
	public int delMin() {
		if(n == 0)
			throw new NoSuchElementException("PQ is empty");
		int min = pq[1];
		exch(1, n--);
		sink(1);
		assert min == pq[n+1];
		qp[min] = -1;
		ts[min] = null;
		pq[n+1] = -1;
		return min;
	}
	
	public T keyOf(int i) {
		validateIndex(i);
		if(!contains(i))
			throw new NoSuchElementException("Index is not in PQ");
		else return ts[i];
	}
	
	public void changeKet(int i, T t) {
		validateIndex(i);
		if(!contains(i))
			throw new NoSuchElementException("Index is not in PQ");
		ts[i] = t;
		swim(qp[i]);
		sink(qp[i]);
	}
	
	public void decreaseKey(int i, T t) {
		validateIndex(i);
		if(!contains(i))
			throw new NoSuchElementException("Index is not in PQ");
		if(ts[i].compareTo(t) == 0)
			throw new IllegalArgumentException("Calling decreaseKey() with a key equal to a key in the PQ");
		if(ts[i].compareTo(t) < 0)
			throw new IllegalArgumentException("Calling decreaseKey() with a key strictly greater than the key in the PQ");
		ts[i] = t;
		swim(qp[i]);
	}
	
	public void increaseKey(int i, T t) {
		validateIndex(i);
		if(!contains(i))
			throw new NoSuchElementException("Index is not in PQ");
		if(ts[i].compareTo(t) == 0)
			throw new IllegalArgumentException("Calling decreaseKey() with a key equal to a key in the PQ");
		if(ts[i].compareTo(t) < 0)
			throw new IllegalArgumentException("Calling decreaseKey() with a key strictly less than the key in the PQ");
		ts[i] = t;
		sink(qp[i]);
	}
	
	public void delete(int i) {
		validateIndex(i);
		if(!contains(i))
			throw new NoSuchElementException("Index is not in the PQ");
		int index = qp[i];
		exch(index, n--);
		swim(index);
		sink(index);
		ts[i] = null;
		qp[i] = -1;
	}
	
	private void validateIndex(int i) {
		if(i < 0)
			throw new IllegalArgumentException("Index negative: " + i);
		if(i >= maxN)
			throw new IllegalArgumentException("Index >= capacity: " + i);
	}
	
	private boolean greater(int i, int j) {
		return ts[pq[i]].compareTo(ts[pq[j]]) > 0;
	}
	
	private void exch(int i, int j) {
		int swap = pq[i];
		pq[i] = pq[j];
		pq[j] = swap;
		qp[pq[i]] = i;
		qp[pq[j]] = j;
	}
	
	private void swim(int k) {
		while(k > 1 && greater(k/2, k)) {
			exch(k, k/2);
			k = k/2;
		}
	}
	
	private void sink(int k) {
		while(2*k <= n) {
			int j = 2*k;
			if(j < n && greater(j, j+1))
				j++;
			exch(k, j);
			k = j;
		}
	}
	
	@Override
	public Iterator<Integer> iterator() {
		return new HeapIterator();
	}
	
	private class HeapIterator implements Iterator<Integer>{

		private IndexMinPQ<T> cpy;
		
		public HeapIterator() {
			cpy = new IndexMinPQ<T>(pq.length -1);
			for (int i = 1; i <= n; i++) {
				cpy.insert(pq[i], ts[pq[i]]);
			}
		}
		
		public boolean hasNext() {
			return !cpy.isEmpty();
		}
		
		public Integer next() {
			if(!hasNext())
				throw new NoSuchElementException();
			return cpy.delMin();
		}
		
	}
	
    public static void main(String[] args) {
        // insert a bunch of strings
        String[] strings = { "it", "was", "the", "best", "of", "times", "it", "was", "the", "worst" };

        IndexMinPQ<String> pq = new IndexMinPQ<String>(strings.length);
        for (int i = 0; i < strings.length; i++) {
            pq.insert(i, strings[i]);
        }

        // delete and print each key
        while (!pq.isEmpty()) {
            int i = pq.delMin();
            System.out.println(i + " " + strings[i]);
        }
        System.out.println();

        // reinsert the same strings
        for (int i = 0; i < strings.length; i++) {
            pq.insert(i, strings[i]);
        }

        // print each key using the iterator
        for (int i : pq) {
        	System.out.println(i + " " + strings[i]);
        }
        while (!pq.isEmpty()) {
            pq.delMin();
        }

    }
}