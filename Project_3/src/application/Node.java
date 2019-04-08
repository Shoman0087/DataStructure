package application;

public class Node {
	
	Node left;
	Node right;
	Country data;
	
	public Node() {
		// TODO Auto-generated constructor stub
		left = null;
		right = null;
		data = null;
	}
	
	public Node(Country data) {
		this.data = data;

	}

	public Node getLeft() {
		return left;
	}

	public void setLeft(Node left) {
		this.left = left;
	}

	public Node getRight() {
		return right;
	}

	public void setRight(Node right) {
		this.right = right;
	}

	public Country getData() {
		return data;
	}

	public void setData(Country data) {
		this.data = data;
	}
	
	
	

}
