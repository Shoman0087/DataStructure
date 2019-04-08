

public class LinkedList<T> {
	private Node<T> head;
	private int size = 0;

	public LinkedList() {
		// TODO Auto-generated constructor stub
		head = new Node<>(null);

	}
	
	public Node<T> add(T data) {
		Node<T> temp;
		Node<T> current = head;


		while (current.getNext() != null)
			current = current.getNext();

		temp = new Node<>(data);
		current.setNext(temp);
		size++;
		return null;
	}
	
	public Node<T> getHead() {
		return head;
	}
	public Node<T> add(T data, int index) {
		if (index < 0 || index >= size)
			return null;
		Node<T> temp;
		Node<T> current = head;

		for (int i = 0 ; i < index ; i++) {
			current = current.getNext();
		}
		temp = new Node<>(data);
		temp.setNext(current.getNext());
		current.setNext(temp);
		size++;
		return null;
	}
	
	public T poll() {
		Node<T> current = head;

		T temp = current.getNext().getData();
		
		delete(0);

		return temp;
		
	}

	public Boolean delete(T data) {
		Node<T> current = head;
		while (current.getNext() != null){
			if (current.getNext().getData().equals(data)) {
				current.setNext(current.getNext().getNext());
				size--;
				return true;
			}
			current = current.getNext();


		}
		return false;
	}

	public Boolean delete(int index) {
		if (index < 0 || index >= size)
			return false;
		
		Node<T> current = head;

		for (int i = 0 ; i < index ; i++) {
			current = current.getNext();
		}
		current.setNext(current.getNext().getNext());
		size--;
		return true;
	}

	public Boolean find(T data) {
		Node<T> current = head;
		while (current.getNext() != null) {
			if (current.getNext().getData().equals(data)) {
				return true;
			}
		}
		return false;
	}

	public T get(int index) {
		if (index < 0 || index >= size)
			return null;
		Node<T> current = head;

		for (int i = 0 ; i <= index ; i++) {
			current = current.getNext();
		}
		return current.getData();
	}

	public int size() {
		return size;
	}

	public String toString() {
		Node<T> current = head;
		String str = "{\n";
		while (current.getNext() != null) {
			str += current.getNext().getData().toString() + "\n";
			current = current.getNext();
		}
		str += "}";
		return str;
	}

}
