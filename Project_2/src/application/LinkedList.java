package application;

import org.omg.CORBA.Current;

public class LinkedList {
	listNode head;
	
	public LinkedList() {
		head = new listNode(null);
	}
	
	public void add(String data) {
		listNode current = head;
		while (current.getNext() != null) {
			current = current.getNext();
		}
		current.setNext(new listNode(data));
		//System.out.println(toString());
	}
	public void setHead(listNode head) {
		this.head = head;
	}
	public listNode getHead() {
		return head;
	}
	
	public String poll() {
		
		if (head.getNext() != null) {
			String str = head.getNext().getData();
			head.setNext(head.getNext().getNext());
			return str;
		}
		return null;
		
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
