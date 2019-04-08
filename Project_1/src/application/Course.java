package application;

public class Course {
	private String name;
	private String id;
	private int year;
	private String starTime;
	private String endTime;
	private int maxNumberOfStudent;
	private LinkedList list = new LinkedList();
	private LinkedList radixList = new LinkedList();
	private Student[] arr2;
	private int numOfStudent;
	private LinkedList[] arr = new LinkedList[10];	
	
	public Course() {
		// TODO Auto-generated constructor stub
	}
	
	public Course(String name, String id, int year, String starTime, String endTime,int maxNumberOfStudent) {
		super();
		this.name = name;
		this.id = id;
		this.year = year;
		this.starTime = starTime;
		this.endTime = endTime;
		this.maxNumberOfStudent = maxNumberOfStudent;
	}
	
	public int getNumOfStudent() {
		return numOfStudent;
	}
	
	// this mwthod add student to the course by invoke (add) method from the linkedlist
	public void add(Student st) throws Exception {
		if (list.size() == maxNumberOfStudent) 
			throw new Exception("The Class is full");
		list.addSorted(st);
		numOfStudent++;
	}
	
	
	// this method remove students from the course by invoke delete method from linkedlist
	public void removeStudent(Student st) throws Exception{
		if (list.size() == 0) 
			throw new Exception("The Class is empty");
		list.delete(st);
	}
	
	
	//this method return the double value for course's start time
	public double startT() {
		String[] time = starTime.split(":");
		double st = Double.parseDouble(time[0]) + Double.parseDouble(time[1]) / 60;
		return st;
	}
	//this method retuen the double value for course's end time
	public double endT() {
		String[] time = endTime.split(":");
		double st = Double.parseDouble(time[0]) + Double.parseDouble(time[1]) / 60;
		return st;
	}
	
	
	//this method is for radix sort for students in each course
	public void sort() {
		for (int i = 0 ; i < arr.length ; i++)
			arr[i] = new LinkedList();
		
		for (int a = 0 ; a < list.size() ; a++)
			radixList.add(list.get(a));
		for (int i = 0 ; i < 7; i++) {			
			for (int j = 0 ; j < radixList.size() ; j++)  
					arr[getDigit(radixList.get(j).getId(), i)].add(radixList.get(j));

				radixList.clear();
			
			// this loop is to dump the bucket  and push them items to an array of student
			for (int k = 0 ; k < arr.length ; k++) 
				while (arr[k].getHead().getNext() != null) 					 
					radixList.add(arr[k].poll());		
		}		
	
	}
	
	//clear list that is radix
	public void clear() {
		radixList.clear();
	}
	
	public LinkedList getRadixList() {
		return radixList;
	}
	
	
	
	public int getDigit(int num , int index) {	
		for (int i = 0 ; i < index ; i++) 
			num = num / 10;		
		return num % 10;
	}

	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public int getYear() {
		return year;
	}
	public void setYear(int year) {
		this.year = year;
	}
	public String getStarTime() {
		return starTime;
	}
	public void setStarTime(String starTime) {
		this.starTime = starTime;
	}
	public String getEndTime() {
		return endTime;
	}
	public void setEndTime(String endTime) {
		this.endTime = endTime;
	}
	public int getMaxNumverOfStudent() {
		return maxNumberOfStudent;
	}
	public void setMaxNumberOfStudent(int numberOfStudent) {
		this.maxNumberOfStudent = numberOfStudent;
	}
	public LinkedList getStudentList() {
		return list;
	}

	@Override
	public String toString() {
		return "Course [name=" + name + ", id=" + id + ", year=" + year + ", starTime=" + starTime + ", endTime="
				+ endTime + ", numberOfStudent=" + maxNumberOfStudent + "]";
	}

}
