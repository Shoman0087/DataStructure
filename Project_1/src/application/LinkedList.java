package application;



public class LinkedList {
	private Node<Student> head;
	private int size = 0;

	public LinkedList() {
		// TODO Auto-generated constructor stub
		head = new Node<>(null);

	}
	
	public Node<Student> getHead() {
		return head;
	}
	
	
	// remove the first element in the linkedlist
	public Student poll() {
		Node<Student> current = head;
		Student temp = current.getNext().getData();	
		delete(0);
		return temp;
		
	}
	
	
	// add elements sorted by alphapitcal
	public Node<Student> addSorted(Student data) {
		Node<Student> temp;
		Node<Student> current = head;
		
		if (size == 0) {
			add(data, 0);
			return null;
		}
		
		for (int i = 0 ; i < size ; i++) {
				if (current.getNext().getData().getName().compareTo(data.getName()) > 0) {
					add(data,i);
					return null;
				}
			current = current.getNext();		
		}
		add(data,size);

		return null;
	}
	
	public Node<Student> add(Student data) {
		Node<Student> temp;
		Node<Student> current = head;


		while (current.getNext() != null)
			current = current.getNext();

		temp = new Node<>(data);
		current.setNext(temp);
		size++;
		return null;
	}
	

	public Node<Student> add(Student data, int index) {
		if (index < 0 || index > size)
			return null;
		Node<Student> temp;
		Node<Student> current = head;

		for (int i = 0 ; i < index ; i++) {
			current = current.getNext();
		}
		temp = new Node<>(data);
		temp.setNext(current.getNext());
		current.setNext(temp);
		size++;
		return null;
	}

	
	public void clear() {
		head.setNext(null);
		size = 0;
	}

	public Boolean delete(Student data) {
		Node<Student> current = head;
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
		Node<Student> current = head;
		for (int i = 0 ; i < index ; i++) {
			current = current.getNext();
		}
		current.setNext(current.getNext().getNext());
		size--;
		return true;
	}


	public Boolean find(Student data) {
		Node<Student> current = head;
		while (current.getNext() != null) {
			if (current.getNext().getData().equals(data)) {
				return true;
			}
		}
		return false;
	}
	
	public Boolean isExist(int id) {
		Node<Student> current = head;
		
		while (current.getNext() != null) {
			if (current.getNext().getData().getId() == id) {
				return true;
			}
			current = current.getNext();
		}
		return false;
	}

	public Student get(int index) {
		if (index < 0 || index >= size)
			return null;
		Node<Student> current = head;

		for (int i = 0 ; i <= index ; i++) {
			current = current.getNext();
		}
		return current.getData();
	}
	
	public boolean isEmpty() {
		return size == 0;
	}
	


	public int size() {
		return size;
	}
	
	
	

	public String toString() {
		Node<Student> current = head;
		String str = "{\n";
		while (current.getNext() != null) {
			str += current.getNext().getData().toString() + "\n";
			current = current.getNext();
		}
		str += "}";
		return str;
	}

}
