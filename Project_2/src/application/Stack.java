package application;

public class Stack<T> {
	
	private Node<T> head;
	private int size = 0;
	
	public Stack() {
		// TODO Auto-generated constructor stub
		head = new Node<>(null);
	}
	
	public boolean isEmpty() {
		return size == 0;
	}
	
	public void push(T data) {
		
		Node<T> current = head;
		while (current.getNext() != null) {
			current = current.getNext();
		}
		
		current.setNext(new Node<>(data));
		size++;
	}
	
	public void pop() {
		if (size > 0) {
			Node<T> current = head;
			while (current.getNext().getNext() != null) { 
				current = current.getNext();
			}

			current.setNext(null);
			//System.out.println(current.getData());
			size--;
		}
	}
	
	
	public T top() {
		if (size > 0) {
			Node<T> current = head;
			while (current.getNext() != null)
				current = current.getNext();
			
			return current.getData();
		}
		return null;
	}
	
	public String toString() {
		Node<T> current = head;
		String str = "";
		while (current.getNext() != null) {
			current = current.getNext();
			str += current.getData().toString();
		}
		return str;
	}
	
	
	public int size() {
		return size;
	}
	
	

}
