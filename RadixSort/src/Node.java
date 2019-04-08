

public class Node<T> {
	Node<T> next;
	T data;

	public Node() {
		// TODO Auto-generated constructor stub
		data = null;
		next = null;
	}

	public Node(T data) {
		// TODO Auto-generated constructor stub
		this.data = data;
		next = null;
	}

	public Node(Node<T> next , T data) {
		this.next = next;
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

	public String toString() {
		return data.toString() + "\n";
	}

}
