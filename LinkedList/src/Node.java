
public class Node {
	
	Node next;
	Object data;
	
	public Node(Object data) {
		this.data = data;
		next = null;
		
	}
	
	public Node(Object data,Node next) {
		this.data = data;
		this.next = next;
		
	}

	public Node getNext() {
		return next;
	}

	public void setNext(Node next) {
		this.next = next;
	}

	public Object getData() {
		return data;
	}

	public void setData(Object data) {
		this.data = data;
	}
	
	

}
