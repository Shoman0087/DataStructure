package application;

public class Node<T> {
	
	private Node<T> next;
	private T data;
	
	public Node() {
		// TODO Auto-generated constructor stub
		
		this.next = null;
		this.data = null;
	}
	
	public Node(T data) {
		this.data = data;
	}

	public Node<T> getNext() {
		return next;
	}

	public void setNext(Node<T> next) {
		this.next = next;
	}

	public T getData() {
		return data;
	}

	public void setData(T data) {
		this.data = data;
	}
	
	

}
