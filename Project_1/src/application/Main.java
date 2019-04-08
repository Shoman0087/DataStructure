package application;
	
import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.Arrays;
import java.util.Scanner;

import javax.swing.plaf.basic.BasicTabbedPaneUI.TabbedPaneLayout;

import javafx.application.Application;
import javafx.beans.property.ReadOnlyObjectWrapper;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;


public class Main extends Application {
	
	// array of courses that are allowen int he semester
	public Course[] courseList = new Course[50];
	public LinkedList studentList = new LinkedList();
	
	@Override
	public void start(Stage primaryStage) {
		try {
			// read course informations from a file and add them to an array of courses
			readCourses(courseList);
			sort(courseList);
			// read students informations from a file and add them to an array in sroted way
			System.out.println("sss");
			addStudents(courseList);
			
			
			// main pane 
			BorderPane root = new BorderPane();
			
			
			// this pane is for left side that contains the buttons
			VBox leftBox = new VBox(25);
			leftBox.setId("leftBox");
			leftBox.setPadding(new Insets(20,20,0,20));
			Button addST =  new Button("Add new Student");
			addST.setPrefWidth(200);
			Button addC =  new Button("Add new Course");
			addC.setPrefWidth(200);
			Button stList =  new Button("Student List");
			stList.setPrefWidth(200);
			Button stInAllC =  new Button("Student List in all Courses");
			stInAllC.setPrefWidth(200);
			Button stSched =  new Button("Student schedule");
			stSched.setPrefWidth(200);
			Button cList =  new Button("Courses List");
			cList.setPrefWidth(200);
			Button print = new Button("Print to a file");
			print.setPrefWidth(200);
			Button close =  new Button("Close");
			close.setPrefWidth(200);
			leftBox.getChildren().addAll(addST,addC,stList,stInAllC,stSched,cList,print,close);
			root.setLeft(leftBox);
			
			
			// this Label contain the backgound
			Label Background = new Label();
			Image image2 = new Image("background.jpg");
			ImageView view2 = new ImageView(image2);
			view2.setFitWidth(740);
			view2.setFitHeight(680);
			
			Background.setGraphic(view2);
			root.setCenter(Background);
			try {
			addST.setOnAction(e -> addNewStudent());
			addC.setOnAction( e-> addCourse());
			stList.setOnAction(e -> allStudentsTable());
			stInAllC.setOnAction(e -> studentsTable());
			stSched.setOnAction( e-> studentSched());
			cList.setOnAction(e -> coursesTable());
			print.setOnAction(e -> printToFile());
			close.setOnAction(e -> {
				primaryStage.hide();
			});
			
			} catch (Exception e) {
				Alert alert = new Alert(AlertType.INFORMATION);
				alert.setTitle("Information Dialog");
				alert.setHeaderText(null);
				alert.setContentText(e.getMessage());
				alert.showAndWait();
			}
			
			
			Scene scene = new Scene(root,980,650);
			primaryStage.setResizable(false);
			scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
			primaryStage.setScene(scene);
			primaryStage.show();
		} catch(Exception e) {
			Alert alert = new Alert(AlertType.INFORMATION);
			alert.setTitle("Information Dialog");
			alert.setHeaderText(null);
			alert.setContentText(e.getMessage());
			alert.showAndWait();
		}
	}
	
	// method that sort courses by year
	public void sort(Course[] arr) {
		Course temp;
		for (int i = 0 ; i < arr.length ; i++) {
			if (arr[i] == null) 
				break;
				for (int j = 0 ; j < arr.length - 1 ; j++) {
					if (arr[j+1] == null) 
						break;
						if (arr[j].getYear() < arr[j+1].getYear()) {
							temp = arr[j];
							arr[j] = arr[j+1];
							arr[j+1] = temp;
						}					
				}			
		}
	}
	
	
	// read the courses info from the file 
	public void readCourses(Course[] list) {
		try {
		File file = new File("inputFiles\\coursesFile.txt");
		Scanner scan = new Scanner(file);
		String[] courseInfo = new String[50];
		int i = 0;
		while (scan.hasNext()) {
			courseInfo[i++] = scan.nextLine();
		}
		String[] infos;
		
		// split the infos and initial the array of courses
		for (int j = 0 ; j < courseInfo.length ; j++) {
			if (courseInfo[j] == null) 
				break;
			infos = courseInfo[j].split("#");
			// to check if the course strts with COMP if not , the programe will increment j by 1 to prevent it moving to next j
			if (isComp(infos[1].trim())) 
			list[j] = new Course(infos[0].trim() , infos[1].trim() , Integer.parseInt(infos[2].trim()) , infos[3].trim() , infos[4].trim() , Integer.parseInt(infos[5].trim()));
			else  {
				j--;
				throw new Exception("The course" + infos[1] + " is not started with COMP");				
			}
		}				
		} catch(Exception e) {
			Alert alert = new Alert(AlertType.INFORMATION);
			alert.setTitle("Information Dialog");
			alert.setHeaderText(null);
			alert.setContentText(e.getMessage());
			alert.showAndWait();			
		}	
	}
	
	// a method that check if the course start with COMP or not
	public Boolean isComp(String st) {
		if (st.charAt(0) == 'C' && st.charAt(1) == 'O' && st.charAt(2) == 'M' && st.charAt(3) == 'P')
			return true;
		return false;
	}
	
	
	// a method to read students informations from a file and check if they satisfy the conditions and add their courses
	public void addStudents(Course[] list) {
		String[] studentInfo = new String[1000];
		readStudents(studentInfo);
		
		String[] infos;
		// split the student infos and add them to the linked list for each course 
		for (int j = 0 ; j < studentInfo.length ; j++) {
			if (studentInfo[j] == null)
				break;
			
			infos = studentInfo[j].split("#");
			/*
			for (int l = 0 ; l < infos.length ; l++)
				System.out.println(infos[l]);
			*/
			for (int a = 0 ; a < infos.length ; a++)
				infos[a] = infos[a].trim();

		//	System.out.println(infos[1] + "    " + infos[0]);
			Student st = new Student(Integer.parseInt(infos[1]),infos[0]);
			//System.out.println(st.toString());
			int cout = 0;
			// this loop is start from the second index for each student line from the file and check if the courses is avaliable form him of not
			for (int s = 2 ; s < infos.length ; s++) {
				if (isAllowed(Integer.parseInt(infos[1]), infos[s] ,st) && cout != 5) {
					cout++;
					try {
					courseList[getIndex(infos[s])].add(st);
					st.addCourse(courseList[getIndex(infos[s])].getId());
					
					} catch(Exception e) {
						
					}
				}
			}
			
			// check if the student is added for the first time and add him to the student list in sorted way
			if (st.getNumOfCourses() > 0)
				studentList.addSorted(st);

		}
		
		
	}
	
	// get the index for any course that exist in the array of courses by its ID
	public int getIndex(String cName) {
		for (int i = 0 ; i < courseList.length ; i++) {
			if (courseList[i] == null)
				break;
			if (cName.equals(courseList[i].getId()))
				return i;
		}
		return -1;
	}
	
	// this method is check if the course is in the list and if it vaild for student's year and if it is not intersect with another course 
	public Boolean isAllowed(int id, String course , Student st) {
		for (int j = 0 ; j < courseList.length ;j++) {
			if (courseList[j] == null)
				return false;
			//System.out.println(j + courseList[j].getId());
			
			if (course.equals(courseList[j].getId())) {
				
				if ((id/10000) <= courseList[j].getYear()) {
					
					if (!isIntersection(st,courseList[getIndex(course)])) {
						
						return true;
					
					} else 
						return false;	
				}
				 else 
					return false;
			} 
				
		}
		
		return false;
		
	}
		
	// this method check if the course intersect with another course or not
	public Boolean isIntersection(Student st , Course course) {
		
		double start = course.startT();
		double end = course.endT();
		double start2;
		double end2;
		for (int i = 0 ; i < st.getNumOfCourses() ; i++) {

			start2 = courseList[getIndex(st.getCourses()[i])].startT();
			end2 = courseList[getIndex(st.getCourses()[i])].endT();
			if (start == start2 || end == end2)
				return true;
			else if (start == end2) 
				continue;
			else if (end == start2)
				continue;
			else if (end > start2 && end < end2)
				return true;
			else if (start < start2 && end > end2)
				return true;			
			else if (start > start2 && start < end2) 
				return true;			
		}
		return false;
	}
	
	public void readStudents(String[] list) {
		//System.out.println("test");
		try {
			File file = new File("inputFiles\\studentFile.txt");
			Scanner scan = new Scanner(file);
			int i = 0 ;
			while (scan.hasNext()) {
				list[i++] = scan.nextLine();
			}
			
			
		} catch(Exception e) {
			Alert alert = new Alert(AlertType.INFORMATION);
			alert.setTitle("Information Dialog");
			alert.setHeaderText(null);
			alert.setContentText(e.getMessage());

			alert.showAndWait();
		}
	}
	
	// this method desplay the student in each course
	TableView<Student> table;
	public void allStudentsTable() {
		Stage stage1 = new Stage();
	
		ObservableList<String> comboList =  FXCollections.observableArrayList();
		
		BorderPane mainPane = new BorderPane();
		
		HBox topBox = new HBox(20);
		topBox.setAlignment(Pos.CENTER);
		for (int i = 0 ; i < courseList.length ; i++) {
			if (courseList[i] == null)
				break;
			comboList.add(courseList[i].getId());
		}
		
		ComboBox<String> courseBox = new ComboBox<>(comboList);
		courseBox.setValue(courseList[0].getId());
		Button show = new Button("Show Table");
		topBox.getChildren().addAll(courseBox,show);
		topBox.setPadding(new Insets(20,0,20,0));
		mainPane.setTop(topBox);		
		ScrollPane sPane = new ScrollPane();
		show.setOnAction(e -> {
			table = new TableView<>();
		
			ObservableList<Student> studentList =  FXCollections.observableArrayList();
			Course cr = courseList[getIndex(courseBox.getValue())];
			cr.clear();
			cr.sort();
			for (int i = 0 ; i < cr.getNumOfStudent() ; i++) {
				studentList.add(cr.getRadixList().get(i));
			}
			
			table.setMinWidth(330);
			TableColumn<Student, String> number = new TableColumn<>("#");
			number.setSortable(false);
			number.setCellValueFactory(column-> new ReadOnlyObjectWrapper(table.getItems().indexOf(column.getValue())+1));
			number.setMinWidth(30);
			TableColumn<Student, String> courseName = new TableColumn<>(cr.getName());
			courseName.setMinWidth(330);
			TableColumn<Student, String> studentName = new TableColumn<>("Name");
			studentName.setMinWidth(200);
			TableColumn<Student, String> studentId = new TableColumn<>("ID");
			studentId.setMinWidth(100);
			courseName.getColumns().addAll(number,studentName, studentId);
			table.getColumns().add(courseName);
			studentName.setCellValueFactory(new PropertyValueFactory<>("name"));
			studentId.setCellValueFactory(new PropertyValueFactory<>("id"));
			table.setItems(studentList);
			
			
			sPane.setContent(table);
			
		});
		
		mainPane.setLeft(new Label("                        "));
		mainPane.setRight(new Label("                         "));
		mainPane.setCenter(sPane);
		Scene scene = new Scene(mainPane,500,400);
		scene.getStylesheets().add(getClass().getResource("tableCss.css").toExternalForm());
		stage1.setScene(scene);
		stage1.show();
	}
	
	public void studentsTable() {
		Stage stage1 = new Stage();
		TableView<Student> table = new TableView<>();
		HBox box = new HBox();
		ScrollPane finalPane = new ScrollPane();
		ObservableList<Student> list =  FXCollections.observableArrayList();
		for (int i = 0 ; i < studentList.size() ; i++) 
			list.add(studentList.get(i));
		
		TableColumn<Student, String> number = new TableColumn<>("#");
		number.setSortable(false);
		number.setCellValueFactory(column-> new ReadOnlyObjectWrapper(table.getItems().indexOf(column.getValue())+1));
		number.setMinWidth(30);
		
		TableColumn<Student,String> headView = new TableColumn<>("Students");
		headView.setMinWidth(400);
		TableColumn<Student,String> StName = new TableColumn<>("Name");
		StName.setMinWidth(270);
		TableColumn<Student,Integer> StId = new TableColumn<>("ID");
		StId.setMinWidth(130);
		headView.getColumns().addAll(number,StName, StId);
		
		StName.setCellValueFactory(new PropertyValueFactory<>("name"));
	    StId.setCellValueFactory(new PropertyValueFactory<>("id"));
	    table.getColumns().add(headView);
	    table.setItems(list);
	    table.setMinHeight(900);
	    box.getChildren().add(table);
	    finalPane.setContent(box);
	    Scene tableScene = new Scene(finalPane,400,400);
		stage1.setScene(tableScene);
		stage1.show();
		tableScene.getStylesheets().add(getClass().getResource("tableCss.css").toExternalForm());
		
	}
	Student stOb = null;
	int stId = 0;
	public void studentSched() {
		Stage stage1 = new Stage();
		BorderPane pane = new BorderPane();
		
		Label name = new Label("Search By ID ");
		name.setStyle("-fx-text-fill: rgb(255,255,255);");
		TextField ID = new TextField();
		Button search = new Button("Search");
		HBox topInBox = new HBox(20);
		topInBox.setStyle("-fx-background-color: rgb(0,0,0)");
		topInBox.setAlignment(Pos.CENTER);
		VBox topBox = new VBox(20);
		topBox.setStyle("-fx-background-color: rgb(0,0,0)");
		Label stName = new Label("");
		stName.setStyle("-fx-text-fill: rgb(255,255,255);");
		topBox.setAlignment(Pos.CENTER);
		topInBox.getChildren().addAll(name,ID,search);
		topBox.getChildren().addAll(topInBox,stName);
		pane.setTop(topBox);
		
		VBox leftPane = new VBox(40);
		leftPane.setId("leftPane");
		leftPane.setPadding(new Insets(90,0,0,0));
		Button add = new Button("Add new Course");
		leftPane.getChildren().addAll(add);
		
		
		TableView<Course> table = new TableView<>();
		
		TableColumn<Course,String> headView = new TableColumn<>("Courses");
		headView.setMinWidth(400);
		TableColumn<Course,String> courseId = new TableColumn<>("ID");
		courseId.setMinWidth(100);
		TableColumn<Course,String> courseName = new TableColumn<>("Name");
		courseName.setMinWidth(185);
		courseId.setCellValueFactory(new PropertyValueFactory<>("id"));
		courseName.setCellValueFactory(new PropertyValueFactory<>("name"));
		
		TableColumn<Course,String> startTime = new TableColumn<>("Start Time");
		startTime.setMinWidth(100);
		startTime.setCellValueFactory(new PropertyValueFactory<>("starTime"));
		
		TableColumn<Course,String> endTime = new TableColumn<>("End Time");
		endTime.setMinWidth(100);
		endTime.setCellValueFactory(new PropertyValueFactory<>("endTime"));
		
		headView.getColumns().addAll(courseId, courseName,startTime,endTime);
		table.getColumns().add(headView);
		ObservableList<Course> list =  FXCollections.observableArrayList();
		
		search.setOnAction(e -> {
			list.clear();
			int id = Integer.parseInt(ID.getText());
			Student st = null;
			for (int i = 0 ; i < studentList.size() ; i++) {
				if (id == studentList.get(i).getId()) {
					pane.setLeft(leftPane);
					st = studentList.get(i);
					stOb = st;
					stId = id;
					stName.setText(studentList.get(i).getName());
					break;
				}
			}
			if (st == null) {
				Alert alert = new Alert(AlertType.INFORMATION);
				alert.setTitle("Information Dialog");
				alert.setHeaderText(null);
				alert.setContentText(id + " is not found");
				alert.showAndWait();
				return ;
			}
			int index;
			for (int i = 0 ; i < st.getNumOfCourses() ; i++) {
				index = getIndex(st.getCourses()[i]);
				if (index != -1) {
					list.add(courseList[index]);
				}
			}
			table.setItems(list);
			
		});
		
		
		
		add.setOnAction(e -> {
			Stage primStage = new Stage();
			BorderPane primPane = new BorderPane();
			
			TableView<Course> courseTable = new TableView<>();
			TableColumn<Course,String> courseView = new TableColumn<>("Courses");
			courseView.setMinWidth(400);
			TableColumn<Course,String> Id = new TableColumn<>("ID");
			Id.setMinWidth(100);
			TableColumn<Course,String> Name = new TableColumn<>("Name");
			Name.setMinWidth(185);
			Id.setCellValueFactory(new PropertyValueFactory<>("id"));
			Name.setCellValueFactory(new PropertyValueFactory<>("name"));
			
			TableColumn<Course,String> sTime = new TableColumn<>("Start Time");
			sTime.setMinWidth(100);
			sTime.setCellValueFactory(new PropertyValueFactory<>("starTime"));
			
			TableColumn<Course,String> eTime = new TableColumn<>("End Time");
			eTime.setMinWidth(100);
			eTime.setCellValueFactory(new PropertyValueFactory<>("endTime"));
			
			courseView.getColumns().addAll(Id, Name,sTime,eTime);
			courseTable.getColumns().add(courseView);
			
			
			ObservableList<Course> list2 =  FXCollections.observableArrayList();
			ObservableList<String> comboList =  FXCollections.observableArrayList();
			
			for (int i = 0 ; i < courseList.length ; i++) {
				if (courseList[i] == null) 
					break;
				list2.add(courseList[i]);
				comboList.add(courseList[i].getId());
			}
			courseTable.setItems(list2);
			ScrollPane scPane = new ScrollPane();
			scPane.setContent(courseTable);
			primPane.setCenter(scPane);
			
			
			Label courseLabel = new Label("Choose Any course ");
			ComboBox<String> courseBox = new ComboBox<>(comboList);
			Button addCourse = new Button("Add");
			HBox topBorderBox = new HBox(20);
			topBorderBox.setAlignment(Pos.CENTER);
			topBorderBox.setPadding(new Insets(20,0,20,0));
			topBorderBox.getChildren().addAll(courseLabel,courseBox,addCourse);
			primPane.setTop(topBorderBox);
			addCourse.setOnAction(e1 -> {
				if (isAllowed(stId, courseBox.getValue(), stOb)) {
					
					try {
						
						courseList[getIndex(courseBox.getValue())].add(stOb);
						stOb.addCourse(courseBox.getValue());
						
					} catch (Exception e2) {
						// TODO Auto-generated catch block
						Alert alert = new Alert(AlertType.INFORMATION);
						alert.setTitle("Information Dialog");
						alert.setHeaderText(null);
						alert.setContentText("Student " + stOb.getId() + " Cann't regest more than 5 courses");
						alert.showAndWait();
					}
				} else {
					Alert alert = new Alert(AlertType.INFORMATION);
					alert.setTitle("Information Dialog");
					alert.setHeaderText(null);
					alert.setContentText(courseBox.getValue() + " conflict with other course");
					alert.showAndWait();
				}
			});
			
			Scene scene2 = new Scene(primPane,500,400);
			scene2.getStylesheets().add(getClass().getResource("addStudent.css").toExternalForm());
			primStage.setScene(scene2);
			primStage.show();
			
		});
		
		
		
		
		pane.setCenter(table);
		Scene tableScene = new Scene(pane,600,400);
		stage1.setScene(tableScene);
		stage1.show();
		tableScene.getStylesheets().add(getClass().getResource("tableCss.css").toExternalForm());
	}
	
	public void coursesTable() {
		Stage stage1 = new Stage();
		TableView<Course> table = new TableView<>();
		
		TableColumn<Course, String> number = new TableColumn<>("#");
		number.setSortable(false);
		number.setCellValueFactory(column-> new ReadOnlyObjectWrapper(table.getItems().indexOf(column.getValue())+1));
		number.setMinWidth(30);
		TableColumn<Course,String> courseId = new TableColumn<>("ID");
		TableColumn<Course,String> courseName = new TableColumn<>("Name");
		TableColumn<Course,String> numOfStudent = new TableColumn<>("Number of Student");
		
		courseId.setCellValueFactory(new PropertyValueFactory<>("id"));
		courseName.setCellValueFactory(new PropertyValueFactory<>("name"));
		numOfStudent.setCellValueFactory(new PropertyValueFactory<>("numOfStudent"));
		
		table.getColumns().addAll(number,courseId,courseName,numOfStudent);
		
		table.setItems(getList());
		
		HBox pane = new HBox();
		ScrollPane finalPane = new ScrollPane();
		//pane.setMinWidth(500);
		table.setMinWidth(570);
		 table.setMinHeight(900);
		pane.getChildren().add(table);
		finalPane.setContent(pane);
		
		Scene tableScene = new Scene(finalPane,550,400);
		stage1.setScene(tableScene);
		stage1.show();

		tableScene.getStylesheets().add(getClass().getResource("tableCss.css").toExternalForm());
	}
	
	public ObservableList<Course> getList() {
		ObservableList<Course> list =  FXCollections.observableArrayList();
		list.clear();
		for (int i = 0 ; i < courseList.length ; i++) {
			if (courseList[i] != null) {
			list.add(courseList[i]);
			}
		}
		return list;
			
	}
	
	public ObservableList<Student> getStudents(Course course) {
		ObservableList<Student> list =  FXCollections.observableArrayList();
		course.clear();
	    course.sort();

	    System.out.println(course.getRadixList().size());
				for (int s = 0 ; s < course.getRadixList().size() ; s++) {					
					list.add(course.getRadixList().get(s));
				}

		return list;
			
	}
	
	public void addNewStudent() {
		
		Stage stage = new Stage();
		
		VBox courseBox = new VBox(10);
		
		VBox labelBox = new VBox();
		labelBox.setAlignment(Pos.TOP_CENTER);
		GridPane pane = new GridPane();
		pane.setId("pane");
		pane.setVgap(20);
		pane.setPadding(new Insets(40,0,0,0));
		Label name = new Label("Name : ");
		Label id = new Label("ID : ");
		Label courses = new Label("Courses : ");
		Button add = new Button("ADD");
		labelBox.getChildren().add(courses);
		
		pane.setAlignment(Pos.TOP_CENTER);
		pane.add(name, 0, 0);
		pane.add(id, 0, 1);
		pane.add(labelBox, 0, 2);
		
		TextField nameField = new TextField();
		TextField idField = new TextField();
		
		pane.add(nameField, 1, 0);
		pane.add(idField, 1, 1);
		pane.add(add, 1, 3);
		CheckBox[] cour = new CheckBox[50];
		HBox insideBox;
		
		for (int i = 0  ; i < courseList.length ; i++) {
			if (courseList[i] == null)
				break;
			
			cour[i] = new CheckBox(courseList[i].getId());
			Label inName = new Label(courseList[i].getName());
			insideBox = new HBox(10);
			insideBox.getChildren().addAll(cour[i],inName);
			courseBox.getChildren().add(insideBox);
		}
		
		ScrollPane insPane = new ScrollPane(courseBox);
		pane.add(insPane, 1, 2);
		add.setOnAction(e -> {
			try {
			int ID = Integer.parseInt(idField.getText());
			if (studentList.isExist(ID)) {
				Alert alert = new Alert(AlertType.INFORMATION);
				alert.setTitle("Information Dialog");
				alert.setHeaderText(null);
				alert.setContentText("Student " + ID + " is already exist ....");
				alert.showAndWait();
				
			} else {
			Student st = new Student();
			st.setName(nameField.getText());
			st.setId(ID);
			int count = 0;
			for (int i = 0 ; i < cour.length ; i++) {
				if (cour[i] == null)
					break;
				if (count == 5) {
					Alert alert = new Alert(AlertType.INFORMATION);
					alert.setTitle("Information Dialog");
					alert.setHeaderText(null);
					alert.setContentText("You Can't Regest more than 5 courses");
					alert.showAndWait();
					break;
				}
				if (cour[i].isSelected()) {
					if (isAllowed(ID, cour[i].getText(), st)) {
						count++;
						try {
							st.addCourse(cour[i].getText());
							courseList[i].add(st);
						} catch (Exception e1) {
							// TODO Auto-generated catch block
							Alert alert = new Alert(AlertType.INFORMATION);
							alert.setTitle("Information Dialog");
							alert.setHeaderText(null);
							alert.setContentText(e1.getMessage());
							alert.showAndWait();
							break;
						}
					}
				}
			}
			studentList.addSorted(st);
			
			}
			} catch (Exception e2) {
				Alert alert = new Alert(AlertType.INFORMATION);
				alert.setTitle("Information Dialog");
				alert.setHeaderText(null);
				alert.setContentText(e2.getMessage());
				alert.showAndWait();
			}
		});
		
		Scene scene = new Scene(pane,600,400);
		scene.getStylesheets().add(getClass().getResource("addStudent.css").toExternalForm());
		stage.setScene(scene);
		stage.show();		
	}
	
	public void printToFile() {
		try {
		PrintWriter out = new PrintWriter("outputFiles\\Table1.txt");
		
		for (int i = 0 ; i < courseList.length ; i++) {
			if (courseList[i] == null)
				break;
				courseList[i].sort();
				out.println(courseList[i].getName());
				String str = String.format("%-30s %-10s", "Student Name","ID");
				out.println(str);
			for (int j = 0 ; j < courseList[i].getStudentList().size() ; j++) {
				String line = String.format("%-30s %-10d", courseList[i].getRadixList().get(j).getName(),courseList[i].getRadixList().get(j).getId());
				out.println(line);
			}
			
			out.println("#################################################################");
		}
		out.close();
		
		PrintWriter out2 = new PrintWriter("outputFiles\\Table2.txt");
		String str = String.format("%-30s %-10s", "Student Name","ID");
		out2.println(str);
		for (int i = 0 ; i < studentList.size() ; i++) {
			String line = String.format("%-30s %-10d", studentList.get(i).getName(),studentList.get(i).getId());
			out2.println(line);
		}
		out2.close();
		
		PrintWriter out3 = new PrintWriter("outputFiles\\Table3.txt");
		String str2 = String.format("%-60s %-20s %-20s %-20s %-25s %-20s", "Course Name","ID","Start Time","End Time","Max Number of Students","Current Number");
		
		out3.println(str2);
		
		for (int i = 0 ; i < courseList.length ; i++) {
			if (courseList[i] == null)
				break;
			String line = String.format("%-60s %-20s %-20s %-20s %-25d %-20d", courseList[i].getName(),courseList[i].getId(),courseList[i].getStarTime(),
					courseList[i].getEndTime(),courseList[i].getMaxNumverOfStudent(),courseList[i].getNumOfStudent());
			out3.println(line);
		}
		out3.close();
		

		}catch (Exception e) {
			
		}
	}
	
	public void addCourse() {
		Stage stage = new Stage();
		GridPane pane = new GridPane();
		pane.setAlignment(Pos.CENTER);
		pane.setVgap(10);
		Label nameLabel = new Label("Enter the name of the Course");
		Label idLabel = new Label("Enter the ID of the course");
		Label yearLabel = new Label("Year of the Course");
		Label numOfStudentLabel = new Label("Max number of Students");
		Label startTimeLabel = new Label("Start Time");
		Label endTimeLabel = new Label("End Time");
		
		TextField nameField = new TextField();
		TextField idField = new TextField();
		TextField yearField = new TextField();
		
		Spinner spinner = new Spinner();
		spinner.setValueFactory(new SpinnerValueFactory.IntegerSpinnerValueFactory(0, 45,Integer.parseInt("30")));
        spinner.setEditable(true);
        
        TextField startTimeField = new TextField();
        TextField endTimeField = new TextField();
        Button add = new Button("Add");
        
        pane.add(nameLabel, 0, 0);
        pane.add(idLabel, 0, 1);
        pane.add(yearLabel, 0, 2);
        pane.add(numOfStudentLabel, 0, 3);
        pane.add(startTimeLabel, 0, 4);
        pane.add(endTimeLabel, 0, 5);
        pane.add(nameField, 1, 0);
        pane.add(idField, 1, 1);
        pane.add(yearField, 1 ,2);
        pane.add(spinner, 1, 3);
        pane.add(startTimeField, 1, 4);
        pane.add(endTimeField, 1, 5);
        pane.add(add, 1, 6);

        
        add.setOnAction( e -> {
        	if (getIndex(idField.getText()) == -1) {
        	try {
        		Course cr = new Course(nameField.getText(), idField.getText(), 
        				Integer.parseInt(yearField.getText()), startTimeField.getText(), 
        				endTimeField.getText(),(Integer)spinner.getValue());
        		
        		
        		for (int i = 0 ; i < courseList.length ; i++) {
        			if (courseList[i] == null) {
        				courseList[i] = cr;
        				break;
        			}
        		}
        		
        	} catch (Exception err) {
        		
        	}
        	} else { 
        		Alert alert = new Alert(AlertType.INFORMATION);
				alert.setTitle("Information Dialog");
				alert.setHeaderText(null);
				alert.setContentText("this course is already exist");
				alert.showAndWait();
        	}
        });
        
        
        Scene scene = new Scene(pane,400,400);
        scene.getStylesheets().add(getClass().getResource("addStudent.css").toExternalForm());
        stage.setScene(scene);
        stage.show();
        

	}
	
	public static void main(String[] args) {
		launch(args);
	}
}





