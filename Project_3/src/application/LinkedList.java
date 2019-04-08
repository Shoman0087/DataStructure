package application;



public class LinkedList {
	listNode head;
	int size = 0;
	
	public LinkedList() {
		head = new listNode(null);
	}
	
	public void add(City data) {
		listNode current = head;
		while (current.getNext() != null) {
			current = current.getNext();
		}
		current.setNext(new listNode(data));
		size++;
		//System.out.println(toString());
	}
	public void setHead(listNode head) {
		this.head = head;
	}
	public listNode getHead() {
		return head;
	}
	
	
	public City get(int index) {
		if (index < 0 || index >= size)
			return null;
		listNode current = head;

		for (int i = 0 ; i <= index ; i++) {
			current = current.getNext();
		}
		return current.getData();
	}

	
	public boolean isEmpty() {
		return head.getNext() == null;
	}
	
	public String toString() {
		String str = "";
		listNode current = head;
		while (current.getNext() != null) {
			
			str += current.getNext().getData();
			current = current.getNext();
		}
		return str;
	}
	
	
	

}
