package application;
	

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.Arrays;
import java.util.Scanner;

import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Pos;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.*;


public class Main extends Application {
	
	// array to store all equations informations
	Equation[] allEquations = new Equation[200];
	
	@Override
	public void start(Stage primaryStage) {
		try {

			// array to store all equations from the file
			String[] equations = new String[200];
			
			readEquations(equations);
			
			int count = 0;
			
			// this loop goes through all equations and check their validity
			for (int i = 0 ; i < equations.length ; i += 2) {
				LinkedList postfix1 = new LinkedList();
				LinkedList postfix2 = new LinkedList();
				boolean validity1;
				boolean validity2;
				if (equations[i] == null)
					break;
				validity1 = checkValid(equations[i], postfix1);
				validity2 = checkValid(equations[i+1], postfix2);
				allEquations[count++] = new Equation(equations[i],equations[i+1],postfix1,postfix2,validity1,validity2);
			}
			
			// this loop goes through all equations and evaluate their postfix
			for (int i = 0 ; i < allEquations.length ; i++) {
				if (allEquations[i] == null)
					break;
				if (allEquations[i].isLHEValidity()) {
					try {
					allEquations[i].setLHEValue(evaluate(allEquations[i].getLHEPostfix()));
					} catch (Exception e) {
						allEquations[i].setLHEPostfixString(allEquations[i].getLHEPostfixString() + " | " +e.getMessage());
					}
				} 
				if (allEquations[i].isRHEValidity()) {
					try {
					allEquations[i].setREHValue(evaluate(allEquations[i].getRHEPostfix()));
					} catch (Exception e) {
						
						allEquations[i].setRHEPostfixString(allEquations[i].getRHEPostfixString() + " | " + e.getMessage());
					}
				}
			}
			
//			for (int i = 0 ; i < allEquations.length ; i++) {
//				if ((allEquations[i] == null))
//					break;
//				System.out.println(allEquations[i].toString());
//			}

			
			
		
			
			GridPane root = new GridPane();
			Button validFormat = new Button("Valid Format");
			validFormat.setPrefWidth(200);
			Button validEquation = new Button("Valid Equation");
			validEquation.setPrefWidth(200);
			Button toFile = new Button("Print To File");
			toFile.setPrefWidth(200);
			
			root.add(validFormat, 0, 0);
			root.add(validEquation, 0, 1);
			root.add(toFile, 0, 2);
			root.setAlignment(Pos.CENTER);
			root.setVgap(40);
			
			validFormat.setOnAction(e -> format1Action());
			validEquation.setOnAction(e -> format2Action());

			toFile.setOnAction(e -> printToFile());

			Scene scene = new Scene(root,400,400);
			scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
			primaryStage.setScene(scene);
			primaryStage.show();
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	
	// read equations from a file
	public void readEquations(String[] arr) throws FileNotFoundException {
		File file = new File("inputFile\\data2.txt");
		
		Scanner scan = new Scanner(file);
		
		String data ;
		
		int count = 0;
		while (scan.hasNext()) {
			data = scan.nextLine();
			String[] equs = data.split("=");
			if (equs.length == 2) {
			arr[count++] = equs[0];
			arr[count++] = equs[1];
			} 
		}
		
		

		
	}
	
	public boolean checkValid(String equ, LinkedList postfix) throws Exception {	
		LinkedList output = new LinkedList();
		Stack<Character> stack = new Stack<>();
		boolean op = false;
		
		for (int i = 0 ; i < equ.length() ; i++) {
			/*
			System.out.println("char > " + equ.charAt(i));
			System.out.println("op > " + op);
			System.out.println("stack > " + stack.toString());
			System.out.println("output > " + output.toString());
			System.out.println("-----------------------");
			*/
			if ((equ.charAt(i) == '*' || equ.charAt(i) == '/' || equ.charAt(i) == '%')  && op == false) {				
				return false; // if there was a multiblication or division or mod in wrong place that will occure an error 
			} else if ((equ.charAt(i) == '+' || equ.charAt(i) == '-') && op == false) { // if there was a signed operation this (if) will deal with it 
				if (stack.isEmpty()) { // if the satck were empty the sign will be pushed to the stack and we will put 0 in the output to deal with negative numbers
					output .add("0");
					stack.push(equ.charAt(i));
				} else if (stack.top() == '(' || stack.top() == '[' || stack.top() == '{') { // if there was a paratheses in the top of stack and we find a sign so we will do the same as the last comment
					output.add("0");
					stack.push(equ.charAt(i));
				}else if (stack.top() == '*' || stack.top() == '/' || stack.top() == '%') { // if there was a multiplication or division or mod in the stack and we found a sign so we will do the same as the last comment
					output.add("0");
					stack.push(equ.charAt(i));
				}
					
				else { // if there was more than two sign beside each other we will put one sign equal them all
					if (stack.top() == equ.charAt(i)) {
						stack.pop();
						stack.push('+');
					}
					else {
						stack.pop();
						stack.push('-');					
					}
				}
			} else if (op == false && (Character.isDigit(equ.charAt(i)) || equ.charAt(i) =='.')) { // if we find a character in a fine place we will add it to the output
				if (op == true) {					
					return false;
				}
				String str = equ.charAt(i) + "";
				for (int j = i + 1 ; j < equ.length() ; j++) { // this loop is to add number with more than one digit and it it was double
					if (Character.isDigit(equ.charAt(j)) || equ.charAt(j) == '.')
						str += equ.charAt(j);
					else 
						break;
					i = j;
				}
				output.add(str);
				op = true;
			} else if ((equ.charAt(i) == '(' || equ.charAt(i) == '{' || equ.charAt(i) == '[' ) && op == false) { // this loop is to deal with paratheses
				stack.push(equ.charAt(i));
			} else if ((equ.charAt(i) == '(' || equ.charAt(i) == '{' || equ.charAt(i) == '[' ) && i == equ.length()-1) {
				return false;
				
			} else if ((equ.charAt(i) == ')' || equ.charAt(i) == '}' || equ.charAt(i) == ']')) {				
				if (op == false) {
					return false;
				}
				else {
					if (equ.charAt(i) == ')') {						
						while (!stack.isEmpty()) {
							if (stack.top() == '(') {								
								stack.pop();
								break;
							} else if (stack.top() == '[' || stack.top() == '{') {								
								return false; // will return false if we find a wrong close to a paratheses
							}							
							else {
								output.add(stack.top() + "");
								stack.pop();
								if (stack.isEmpty()) {
									return false; // will return false if there was a close without open
								}								
							}													
						}
						
					} else if (equ.charAt(i) == ']') {
						while (!stack.isEmpty()) {
							if (stack.top() == '[') {
								stack.pop();
								break;
							} else if (stack.top() == '(' || stack.top() == '{')
								return false; // same as above
							else {
								output.add(stack.top() + "");
								stack.pop();
								if (stack.isEmpty()) {
									return false; // same as above
								}
							}
						}
					} else if (equ.charAt(i) == '}') {
						while (!stack.isEmpty()) {
							if (stack.top() == '{') {
								stack.pop();
								break;
							} else if (stack.top() == '(' || stack.top() == '[')
								return false; // same as above
							else {
								output.add(stack.top() + "");
								stack.pop();
								if (stack.isEmpty()) {
									return false; // same as above
								}
							}
						}
					}
				}
			} else if (op == true && (equ.charAt(i) == '+' || equ.charAt(i) == '-' || equ.charAt(i) == '*' || equ.charAt(i) == '/' || equ.charAt(i) == '%') ) { // if we find an operation in a fine (between two operands) place so we will deal with it
				if (equ.charAt(i) == '+' || equ.charAt(i) == '-') {
					if (!stack.isEmpty() && (stack.top() == '*' || stack.top() == '/' || stack.top() == '+' || stack.top() == '-' || stack.top() == '%')) {
						output.add(stack.top() + ""); // if we found + or - and there was * or / or % in the stack so we will pop the top of stack and put + or - insted
						stack.pop();						
						if (!stack.isEmpty() && (stack.top() == '+' || stack.top() == '-')) {
							output.add(stack.top() + "");
							stack.pop();						
							
						}
						stack.push(equ.charAt(i));
					} else {
						stack.push(equ.charAt(i));
					}
					op = false;
				} else if (equ.charAt(i) == '*' || equ.charAt(i) == '/' || equ.charAt(i) == '%') {
					
					if (!stack.isEmpty() && (stack.top() == '*' || stack.top() == '/' || stack.top() == '%')) {
						
						output.add(stack.top() + "");
						stack.pop();
						stack.push(equ.charAt(i));					
					} else  {						
						stack.push(equ.charAt(i));
						
						
					}
					op = false;
				}						
				op = false;
			} else if (Character.isDigit(equ.charAt(i)) && op == true) {
				return false;
			}
		}	
		
		
		if (stack.isEmpty() && op == true) {
			postfix.setHead(output.getHead());
			return true;
		}
		else if (op == false) {
			return false;
		}
		else { // to empty stack if it contains any thing
			while (!stack.isEmpty()) {
				if (stack.top() == '(' || stack.top() == '{' || stack.top() == '[') 
					return false;	// if the satck contains an open paratheses it will return false
				else 
					output.add(stack.top() + "");
				stack.pop();
			}
			postfix.setHead(output.getHead());
			//System.out.println(output);
			return true;
		}					
	}
	
	public double evaluate(LinkedList postFix) throws Exception { // evaluate the postfix
		
		Stack<Double> st = new Stack();
		char operator;
		double op1 , op2;
		double ans = 0;
		
		
		while (!postFix.isEmpty()) {
			String str = postFix.poll();
			if (isNumeric(str)) { // check if number
				st.push(Double.parseDouble(str));
			} else {
				
				operator = str.charAt(0); // operation
				op2 = st.top();  // operand2
				st.pop();
				op1 = st.top(); // operand1
				st.pop();
				
				ans = calc(op1 , op2 , operator); // return the value 
				st.push(ans);
			}
		}
		
		return st.top(); // return the result
		
	}
	
	public  double calc(double operand1 , double operand2 , char operator) throws Exception {
			
			switch (operator) {
			case '+':
				return operand1 + operand2;
			case '-' :
				return operand1 - operand2;
			case '*' :
				return operand1 * operand2;
			case '/' : 
				if (operand2 != 0)
					return operand1 / operand2;
				else 
					throw new Exception("Cann't deivide by zero"); // throw exception if operand2 was 0
			case '%' : 
				if (operand2 != 0 && isInteger(operand2))
					return operand1 % operand2;
				else 
					throw new Exception("Cann't find mod for zero or double"); // throw exception if operand2 eas 0 or float
	
			default:
				return 0;
			}
	}
	
	public boolean isInteger(double num) { // this method check if the number is not float
		
		if (num == (int)num) 
			return true;
		else 
			return false;
	}
	
	public  boolean isNumeric(String str)  
	{  
	  try  
	  {  
	    double d = Double.parseDouble(str);  
	  }  
	  catch(NumberFormatException nfe)  
	  {  
	    return false;  
	  }  
	  return true;  
	}
	
	public void format1Action() {
		Stage stage = new Stage();
		TableView<Equation> table = new TableView<Equation>();
		ObservableList<Equation> list = FXCollections.observableArrayList();
		list.clear();
		for (int i = 0 ; i < allEquations.length ; i++) {
			if (allEquations[i] == null)
				break;
			if (allEquations[i].isLHEValidity() && allEquations[i].isRHEValidity())
				list.add(allEquations[i]);
			
		}
		table.setMinWidth(600);
		
		TableColumn<Equation,String> lPostFix = new TableColumn<>("Left PostFix");
		lPostFix.setMinWidth(200);
		TableColumn<Equation,String> rPostFix = new TableColumn<>("Right PostFix");
		rPostFix.setMinWidth(200);
		TableColumn<Equation,Double> lValue = new TableColumn<>("Left PostFix Value");
		lValue.setMinWidth(50);
		TableColumn<Equation,Double> rValue = new TableColumn<>("Right PostFix Value");
		rValue.setMinWidth(50);
		TableColumn<Equation,Boolean> isEqual = new TableColumn<>("is Equal ?");
		isEqual.setMinWidth(100);
		TableColumn<Equation,Boolean> qq = new TableColumn<>(" ");
		qq.setMaxWidth(10);
		table.getColumns().addAll(lPostFix,lValue,qq,rPostFix,rValue,isEqual);
		qq.setStyle("-fx-background-color: rgb(199,189,189)");
		
		lPostFix.setCellValueFactory(new PropertyValueFactory<>("LHEPostfixString"));
		rPostFix.setCellValueFactory(new PropertyValueFactory<>("RHEPostfixString"));
		lValue.setCellValueFactory(new PropertyValueFactory<>("LHEValue"));
		rValue.setCellValueFactory(new PropertyValueFactory<>("RHEValue"));
		isEqual.setCellValueFactory(new PropertyValueFactory<>("equal"));
		table.setItems(list);
		VBox box = new VBox();
		box.getChildren().add(table);
		Scene scene = new Scene(box,740,400);
		scene.getStylesheets().add(getClass().getResource("tablesCss.css").toExternalForm());
		stage.setScene(scene);
		stage.setResizable(false);
		stage.show();
		
	}
	
	public void format2Action() {
		Stage stage = new Stage();
		TableView<Equation> table = new TableView<Equation>();
		ObservableList<Equation> list = FXCollections.observableArrayList();
		
		for (int i = 0 ; i < allEquations.length ; i++) {
			if (allEquations[i] == null)
				break;
			if (!(allEquations[i].isLHEValidity() && allEquations[i].isRHEValidity())) 
				list.add(allEquations[i]);
		}
		
		TableColumn<Equation,String> lEqu = new TableColumn<>("Left Equation");
		TableColumn<Equation,String> rEqu = new TableColumn<>("Right Equation");
		TableColumn<Equation,String> lEquValidity = new TableColumn<>("Validity");
		TableColumn<Equation,String> rEquValidity = new TableColumn<>("Validity");
		TableColumn<Equation,Double> lEquValue = new TableColumn<>("Value");
		TableColumn<Equation,Double> rEquValue = new TableColumn<>("Value");
		TableColumn<Equation,String> qq = new TableColumn<>(" ");
		qq.setStyle("-fx-background-color: rgb(199,189,189)");
		qq.setMaxWidth(10);
		
		table.getColumns().addAll(lEqu,lEquValidity,lEquValue,qq,rEqu,rEquValidity,rEquValue);
		table.setMinWidth(600);
		lEqu.setCellValueFactory(new PropertyValueFactory<>("LHE"));
		lEqu.setMinWidth(200);
		rEqu.setCellValueFactory(new PropertyValueFactory<>("RHE"));
		rEqu.setMinWidth(200);
		lEquValidity.setCellValueFactory(new PropertyValueFactory<>("lValid"));
		lEquValidity.setMinWidth(50);
		rEquValidity.setCellValueFactory(new PropertyValueFactory<>("rValid"));
		rEquValidity.setMinWidth(50);
		lEquValue.setCellValueFactory(new PropertyValueFactory<>("LHEValue"));
		lEquValue.setMinWidth(50);
		rEquValue.setCellValueFactory(new PropertyValueFactory<>("RHEValue"));
		rEquValue.setMinWidth(50);
		table.setItems(list);
		VBox box = new VBox();
		box.getChildren().add(table);
		Scene scene = new Scene(box,700,400);
		scene.getStylesheets().add(getClass().getResource("tablesCss.css").toExternalForm());
		stage.setScene(scene);
		stage.setResizable(false);
		stage.show();
	
	}
	
	
	public void printToFile()  {
		try {
		PrintWriter out = new PrintWriter("outputFile\\outoutData.txt");
		
		String st = String.format("%-50s %-60s %-10s %-5s", "Equztions","PostFix","Validity","Value");
		out.println(st);
		
		for (int i = 0 ; i < allEquations.length ; i++) {
			if (allEquations[i] == null)
				break;
			out.println("-------------------------------------------------------------------------------------------------------------------");
			String line = String.format("%-50s %-60s %-10s %-5s", allEquations[i].getLHE(),allEquations[i].getLHEPostfixString()
					,allEquations[i].getLValid(),allEquations[i].getLHEValue());
			out.println(line);
			out.println("-------------------------------------------------------------------------------------------------------------------");
			String line2 = String.format("%-50s %-60s %-10s %-5s", allEquations[i].getRHE(),allEquations[i].getRHEPostfixString()
					,allEquations[i].getRValid(),allEquations[i].getRHEValue());
			out.println(line2);
			
		}
		out.close();
		
	} catch(Exception e) {
		
	}
	}

	
	public static void main(String[] args) {
		launch(args);
	}
}
