package Q1;

public class Node {
	
	int next;
	Object data;
	
	public Node(int next,Object data) {
		this.next = next;
		this.data = data;
	}
	
	public String toString() {
		return data.toString();
	}

}
