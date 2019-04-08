package application;

import java.util.Arrays;

public class Student {
	private int id;
	private String name;
	private String[] courses;
	private int numOfCourses = 0;
	public Student() {
		courses = new String[5];
	}
	
	
	public Student(int id, String name) {
		super();
		this.id = id;
		this.name = name;
		courses = new String[5];
	}


	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String[] getCourses() {
		return courses;
	}
	public void setCourses(String[] courses) {
		this.courses = courses;
	}
	public int getNumOfCourses() {
		return numOfCourses;
	}
	public void addCourse(String course) throws ArrayIndexOutOfBoundsException {
		if (numOfCourses == 5) {
			throw new ArrayIndexOutOfBoundsException("You Can't regest more then 5 courses");
		} 
		
				courses[numOfCourses++] = course;
	}


	@Override
	public String toString() {
		return "Student [id=" + id + ", name=" + name + ", courses=" + Arrays.toString(courses) + "]";
	}
	
	
}
