package graph.bfs;

import java.util.Iterator;
import java.util.NoSuchElementException;

public class Queue<T> implements Iterable<T>{

	private Node<T>first;
	private Node<T>last;
	private int n;
	
	private static class Node<T>{
		private T info;
		private Node<T> next;
	}
	
	public Queue() {
		first = null;
		last = null;
		n = 0;
	}
	
	public boolean isEmpty() {
		return first == null;
	}
	
	public int size() {
		return n;
	}
	
	public T peek() {
		if(isEmpty()) {
			throw new NoSuchElementException("Queue Empty");
		}
		return first.info;
	}
	
	public void enqueue(T info) {
		Node<T> aux = last;
		last = new Node<T>();
		last.info = info;
		last.next = null;
		
		if(isEmpty())
			first = last;
		else
			aux.next = last;
		n++;
	}
	
	public T dequeue() {
		if(isEmpty())
			throw new NoSuchElementException("Queue Empty");
		
		T aux = first.info;
		first = first.next;
		n--;
		if(isEmpty())
			last = null;
		
		return aux;
	}
	
	@Override
	public Iterator<T> iterator() {
		return null;
	}

}
