package application;

public class listNode {
	listNode next;
	City data;
	
	public listNode() {
		// TODO Auto-generated constructor stub
	}
	public listNode(City data) {
		// TODO Auto-generated constructor stub
		this.data = data;
	}
	public listNode getNext() {
		return next;
	}
	public void setNext(listNode next) {
		this.next = next;
	}
	public City getData() {
		return data;
	}
	public void setData(City data) {
		this.data = data;
	}
	
	
	

}
