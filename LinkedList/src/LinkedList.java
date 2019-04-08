import Q1.Node;

public class LinkedList<Object> {
	
	private Node head;
	private int size;
	
	public LinkedList() {
		// TODO Auto-generated constructor stub
		head = new Node(null);
		size = 0;
		
	}
	
	public Node add (Object data) {
		Node temp = new Node(data);
		Node current = head;
		
		while (current.getNext() != null) {
			current = current.getNext();
		}
		
		current.setNext(temp); 
		size++;
		return null;
	}
	
	public Node add(Object data, int index) {
		Node temp = new Node(null);
		Node current = head;
		for (int i = 0 ; i < index && current.getNext() != null ; i++) {
			current = current.getNext();
		}
		
		temp.setNext(current.getNext());
		current.setNext(temp);
		size++;
		
		
		return null;
	}
	
	public Object get(int index) {
		if (index < 0 && index >= size)
			return null;
		Node current = new Node(null);
		while (index-- != 0) {
			current = current.getNext();
		}
		return  (Object) current.getData();
		
	}
	
	public boolean remove(int index) {
		if (index < 0 && index >= size) {
			return  false;
		}

		Node current = head;
		for (int i = 0 ; i < index ; i++) {
			current = current.getNext();
		}
		current.setNext(current.getNext().getNext());
		size--;
		return true;
		
		
	}
	
	public boolean remove(Object data) {
		
		Node current = head;
		
		while (current.getNext() != null) {
			if (current.getData().equals(data)) {
				current.setNext(current.getNext().getNext());
				size--;
				return true;
			}
			current = current.getNext();
		}
		return false;
		
	}
	
	public String toString() {
		String pDate = null;
		Node current = head;
		pDate = "[ ";
		while (current.getNext() != null) {
			
			pDate += current.getData() + " , ";
			current = current.getNext();
		}
		return pDate += " ]";
	}
	
	

	
	
	
	

}
