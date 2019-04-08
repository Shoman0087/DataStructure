
public class Node {
	
	Node left;
	Node right;
	Integer data;
	
	public Node() {
		// TODO Auto-generated constructor stub
		left = null;
		right = null;
		data = null;
	}
	
	public Node(Integer data) {
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

	public Integer getData() {
		return data;
	}

	public void setData(Integer data) {
		this.data = data;
	}
	
	
	

}
