package application;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

public class Node<Student> {
	Node<Student> next;
	Student data;
	

	public Node() {
		// TODO Auto-generated constructor stub
		data = null;
		next = null;
	}

	public Node(Student data) {
		// TODO Auto-generated constructor stub
		this.data = data;
		next = null;
	}

	public Node(Node<Student> next , Student data) {
		this.next = next;
		this.data = data;
	}

	public Node<Student> getNext() {
		return next;
	}

	public void setNext(Node<Student> next) {
		this.next = next;
	}

	public Student getData() {
		return data;
	}

	public void setData(Student data) {
		this.data = data;
	}

	public String toString() {
		return data.toString() + "\n";
	}



}
