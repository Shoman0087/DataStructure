package application;
	
import java.io.File;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Scanner;



import javafx.application.Application;
import javafx.beans.property.ReadOnlyObjectWrapper;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.stage.Stage;
import javafx.util.Duration;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.effect.Lighting;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Text;
import javafx.animation.*;


public class Main extends Application {
	
	Tree tree = new Tree();
	HashTable table;
	BorderPane root;
	
	@Override
	public void start(Stage primaryStage) {
		
		
		
		try {
			readData();
			 root = new BorderPane();
			 ArrayList<Button> list1 = new ArrayList<>();
			 
			 Button printCountries = new Button("Print Counties");
			 printCountries.setPrefWidth(200);
			 Button search = new Button("Search for a Country");
			 search.setPrefWidth(200);
			 Button addCountry = new Button("Add Countries");
			 addCountry.setPrefWidth(200);
			 Button deleteCountry = new Button("Delete Contry");
			 deleteCountry.setPrefWidth(200);
			 Button getHeight = new Button("Height of Tree");
			 getHeight.setPrefWidth(200);
			 Button hashButtons = new Button("Show Hash Buttons");
			 hashButtons.setPrefWidth(200);
			 list1.add(printCountries);
			 list1.add(search);
			 list1.add(addCountry);
			 list1.add(deleteCountry);
			 list1.add(getHeight);
			 list1.add(hashButtons);
			 
			 
			 ArrayList<Button> list2 = new ArrayList<>();
			 Button printHash = new Button("Print Hash Table");
			 printHash.setPrefWidth(200);
			 Button tableSize = new Button("Table Size");
			 tableSize.setPrefWidth(200);
			 Button hashFunctions = new Button("Hash Functions");
			 hashFunctions.setPrefWidth(200);
			 Button addElement = new Button("Add new Element");
			 addElement.setPrefWidth(200);
			 Button searchElement = new Button("Serach for a City");
			 searchElement.setPrefWidth(200);
			 Button deleteElement = new Button("Delete Country");
			 deleteElement.setPrefWidth(200);
			 Button printToFile = new Button("Print To a File");
			 printToFile.setPrefWidth(200);
			 Button treeButtons = new Button("Show Tree Buttons");
			 treeButtons.setPrefWidth(200);
			 list2.add(printHash);
			 list2.add(tableSize);
			 list2.add(hashFunctions);
			 list2.add(addElement);
			 list2.add(searchElement);
			 list2.add(deleteElement);
			 list2.add(printToFile);
			 list2.add(treeButtons);
			 
			 
			 VBox box = new VBox();
			 box.setAlignment(Pos.CENTER);
			 VBox leftBox = new VBox(30);
			 box.getChildren().add(leftBox);
			 box.setId("leftBox");
			 leftBox.setAlignment(Pos.CENTER);
			 leftBox.getChildren().addAll(printCountries,search,addCountry,deleteCountry,getHeight,hashButtons);
			 root.setLeft(box);
			
			 hashButtons.setOnAction(e -> {
				 changeButtons(list2, leftBox);
				 
			 });
			 
			 treeButtons.setOnAction(e -> {
				 changeButtons(list1, leftBox);
			 });
			 
			 printCountries.setOnAction(e -> printCountriesAction());
			 search.setOnAction(e -> searchAction());
			 addCountry.setOnAction(e -> addCountryAction());
			 deleteCountry.setOnAction( e -> deleteCountryAction());
			 getHeight.setOnAction(e -> getHeightAction());
			 
			 printHash.setOnAction(e -> printHashAction());
			 tableSize.setOnAction(e -> tableSizeAction());
			 deleteElement.setOnAction(e -> deleteCountryHashAction());
			 searchElement.setOnAction(e -> searchHashAction());
			 printToFile.setOnAction(e -> printToFile());
			 addElement.setOnAction(e -> addCityAction());
			 hashFunctions.setOnAction(e -> hashFunctionAction());
			
			Scene scene = new Scene(root,900,700);
			scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
			primaryStage.setScene(scene);
			primaryStage.show();
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	
	
	int co = 0;
	public void readData() {
		try {
			File file = new File("files\\data.txt");
			Scanner scan = new Scanner(file);
			
			
			City c;
			Node country;
			String[] infos;
			while (scan.hasNext()) {
				co++;
				String str = scan.nextLine();
				infos = str.split("\\*");
				c = new City(infos[1],Integer.parseInt(infos[0]),(int)(Double.parseDouble(infos[3])*1000000));
				country = tree.find(infos[2], tree.root);
				
				if (country != null) {
					country.getData().getCities().add(c);
					country.getData().setNumberOfCities(country.getData().getNumberOfCities()+1);
					country.getData().setNumberOfAllTourists(country.getData().getNumberOfAllTourists() + c.getNumerOfTourists());
				} else {
					Country con = new Country(infos[2]);
					con.getCities().add(c);
					con.setNumberOfCities(con.getNumberOfCities()+1);
					con.setNumberOfAllTourists(con.getNumberOfAllTourists() + c.getNumerOfTourists());
					tree.root = tree.insert(con, tree.root);				
				}	
			}
			table = tree.transferToTheHashTable(co);
			
		} catch (Exception e) {
			// TODO: handle exception
			System.out.println(e.getMessage());
		}
	}
	
	
	public void changeButtons(ArrayList<Button> list,VBox box) {
		
		
		FadeTransition ft = new FadeTransition(Duration.seconds(1), box);	
		ft.setFromValue(1);
		ft.setToValue(0);
		ft.play();
		
		ft.setOnFinished(e ->{
			box.getChildren().clear();

			for (int i = 0; i < list.size(); i++) {
				box.getChildren().add(list.get(i));
			}
			box.setOpacity(0);
		//	root.setLeft(box);
			FadeTransition ft2 = new FadeTransition(Duration.seconds(1.5), box);
			ft2.setFromValue(0);
			ft2.setToValue(1);
			ft2.play();
			
		});

	}
	
	public void printCountriesAction() {
		ObservableList<Country> list ;
		list = tree.readDataFromTree();
		TableView<Country> table = new TableView<>();  
		TableColumn<Country, String> number = new TableColumn<>("   #   ");
		number.setSortable(false);
		number.setCellValueFactory(column-> new ReadOnlyObjectWrapper(table.getItems().indexOf(column.getValue())+1));
		TableColumn<Country,String> name = new TableColumn<>("Country Name");
		name.setPrefWidth(200);
		TableColumn<Country,Integer> numOfCities = new TableColumn<>("Number Of Cities");
		numOfCities.setPrefWidth(200);
		TableColumn<Country,Integer> numOfTurists = new TableColumn<>("Number Of Turists");
		numOfTurists.setPrefWidth(200);
		table.getColumns().addAll(number,name,numOfCities,numOfTurists);
		name.setCellValueFactory(new PropertyValueFactory<>("country"));
		numOfCities.setCellValueFactory(new PropertyValueFactory<>("numberOfCities"));
		numOfTurists.setCellValueFactory(new PropertyValueFactory<>("numberOfAllTourists"));
		
		table.setItems(list);
		root.setCenter(table);
	}
	
	public void searchAction() {
		Pane pane = new Pane();
		
		
		TextField searchField = new TextField();
		
		Button searchButton = new Button("Search");
		
		searchButton.setLayoutX(400);
		searchButton.setLayoutY(80);
		pane.getChildren().add(searchField);
		pane.getChildren().add(searchButton);
		searchField.setLayoutX(220);
		searchField.setLayoutY(80);
		root.setCenter(pane);
		
		
		searchButton.setOnAction(e -> {
			pane.getChildren().clear();
			searchButton.setLayoutX(400);
			searchButton.setLayoutY(80);
			pane.getChildren().add(searchField);
			pane.getChildren().add(searchButton);
			searchField.setLayoutX(220);
			searchField.setLayoutY(80);
			
			String name = searchField.getText();
			Node node = tree.find(name, tree.root);
			if (node != null) {
			Country country = node.getData();
			
			Label countryName = new Label("Number Of Turists City is : " + country.getNumberOfCities());
			
			pane.getChildren().add(countryName);
			countryName.setLayoutX(220);
			countryName.setLayoutY(150);
			
			LinkedList list = country.getCities();
			
			for (int i = 0 ; i < country.getNumberOfCities() ; i++) {
				Text t = new Text(list.get(i).getName());
				t.setStyle("-fx-fill: radial-gradient( focus-angle 0.0deg, focus-distance 0.0%, center 50.0% 50.0%, radius 100.0%, rgb(128,77,128) 0.0, rgb(51,0,51) 100.0 );"
						+ "-fx-effect: dropshadow( gaussian , rgba(128,179,179,0.5) , 0,0,0,1 );"
						+ "-fx-font-weight: bold;"
						+ "  -fx-font-family : \"Trebuchet MS\", \"Lucida Grande\", \"Lucida Sans Unicode\", \"Lucida Sans\", \"Tahoma, sans-serif\";"
						+ "font-size: 24px;"
						+ "font-style: normal;"
						+ "font-variant: normal;"
						+ "line-height: 26.4px;"
						);
				t.setLayoutX(300);
				t.setLayoutY(600);
				pane.getChildren().add(t);
				TranslateTransition t1 = new TranslateTransition(Duration.seconds(2), t);
				ScaleTransition t2 = new ScaleTransition(Duration.seconds(2),t);
				t2.setToX(3);
				t2.setToY(3);
				//t1.setToX(((10 / country.getNumberOfCities()) * 20 + i * 70) - 280);
				t1.setToX(-20);
				t1.setToY(-350 + i*40);
				t1.play();
				t2.play();
				
			}
			
			} else {
				System.out.println("error");
			}
			
		});
	}
	Node contry;
	boolean isAdded = false;
	public void addCountryAction() {
		
		GridPane pane = new GridPane();
		pane.setAlignment(Pos.CENTER);
		Label countryName = new Label("Country Name");
		TextField countryField = new TextField();
//		Label cityName = new Label("City Name");
//		TextField cityField = new TextField();
//		Label numOfTurists = new Label("Number of turists");
//		TextField numOfTuristsField = new TextField();
		Button addCountry = new Button("Add");
//		Button addCity = new Button("Add");
		pane.add(countryName, 0, 0);
		pane.add(countryField, 1, 0);
		pane.add(addCountry, 2, 0);
//		pane.add(cityName, 0, 1);
//		pane.add(cityField, 1, 1);
//		pane.add(numOfTurists, 2, 1);
//		pane.add(numOfTuristsField, 3, 1);
//		pane.add(addCity, 4, 1);
		root.setCenter(pane);
		
		
		addCountry.setOnAction(e -> {
			isAdded = false;
			if (countryField.getText() != null) {
				 contry = tree.find(countryField.getText(), tree.root);
				if (contry == null) {
					tree.insert(new Country(countryField.getText()), tree.root);
					Alert alert = new Alert(AlertType.INFORMATION);
					alert.setTitle("Information Dialog");
					alert.setHeaderText(null);
					alert.setContentText(countryField.getText() + " Has been Added you can add cities");

					alert.showAndWait();
					isAdded = true;
					
					
				} else {
					Alert alert = new Alert(AlertType.INFORMATION);
					alert.setTitle("Information Dialog");
					alert.setHeaderText(null);
					alert.setContentText(countryField.getText() + " is already exist");

					alert.showAndWait();
				}
			}
			
			/*
			addCity.setOnAction(e2 -> {
				if (isAdded) {
					if (&cityField.getText() != null && numOfTuristsField.getText() != null) {
						contry.getData().getCities().add(new City(cityField.getText(),
								++co, Integer.parseInt(numOfTuristsField.getText())));
						Alert alert = new Alert(AlertType.INFORMATION);
						alert.setTitle("Information Dialog");
						alert.setHeaderText(null);
						alert.setContentText(cityName.getText() + " has been added");

						alert.showAndWait();
					} else {
						Alert alert = new Alert(AlertType.INFORMATION);
						alert.setTitle("Information Dialog");
						alert.setHeaderText(null);
						alert.setContentText("Wrong inputs");

						alert.showAndWait();
					}
				} else {
					Alert alert = new Alert(AlertType.INFORMATION);
					alert.setTitle("Information Dialog");
					alert.setHeaderText(null);
					alert.setContentText("There is no country added");

					alert.showAndWait();
				}
			});
			*/
		});
		
		
	}
	
	public void deleteCountryAction() {
		Pane pane = new Pane();

		TextField searchField = new TextField();
		Button deleteButton = new Button("Delete");
		
		deleteButton.setLayoutX(400);
		deleteButton.setLayoutY(80);
		pane.getChildren().add(searchField);
		pane.getChildren().add(deleteButton);
		searchField.setLayoutX(220);
		searchField.setLayoutY(80);
		root.setCenter(pane);
		deleteButton.setOnAction(e -> {
			String name = searchField.getText();
			Node node = tree.find(name, tree.root);
			if (node != null) {
				tree.delete(node.getData(), tree.root);
				Alert alert = new Alert(AlertType.INFORMATION);
				alert.setTitle("Information Dialog");
				alert.setHeaderText(null);
				alert.setContentText(name + " Has been deleted");

				alert.showAndWait();
			} else {
				Alert alert = new Alert(AlertType.INFORMATION);
				alert.setTitle("Information Dialog");
				alert.setHeaderText(null);
				alert.setContentText(name + " Cann't be found");

				alert.showAndWait();
			}
		});
	}
	
	public void getHeightAction() {
		Pane pane = new Pane();
		Label t = new Label(tree.height(tree.root) +"");
		
		
		pane.getChildren().add(t);
		t.setLayoutX(300);
		t.setLayoutY(250);
		root.setCenter(pane);
		RotateTransition t1 = new RotateTransition(Duration.seconds(2), t);
		ScaleTransition t2 = new ScaleTransition(Duration.seconds(3),t);
		t1.setByAngle(360);
		t2.setToX(18);
		t2.setToY(18);
		t1.play();
		t2.play();
		
	}
	
	public void printHashAction() {
		ObservableList<City> list ;
		list = table.readHash();
		
		TableView<City> table = new TableView<>();  
		TableColumn<City, String> number = new TableColumn<>("   index   ");
		number.setSortable(false);
		number.setCellValueFactory(column-> new ReadOnlyObjectWrapper(table.getItems().indexOf(column.getValue())));
		
		TableColumn<City,String> name = new TableColumn<>("City Name");
		name.setPrefWidth(200);
		
		TableColumn<City,Integer> numberOfCities = new TableColumn<>("Rank");
		numberOfCities.setPrefWidth(200);
		
		TableColumn<City,Integer> numOfTurists = new TableColumn<>("Number Of Turists");
		numOfTurists.setPrefWidth(200);
		table.getColumns().addAll(number,name,numberOfCities,numOfTurists);
		name.setCellValueFactory(new PropertyValueFactory<>("name"));
		numberOfCities.setCellValueFactory(new PropertyValueFactory<>("rank"));
		numOfTurists.setCellValueFactory(new PropertyValueFactory<>("numerOfTourists"));
		
		table.setItems(list);
		root.setCenter(table);
		
	}
	
	public void tableSizeAction() {
		Pane pane = new Pane();
		Label t = new Label(table.table.length +"");
		
		pane.getChildren().add(t);
		t.setLayoutX(300);
		t.setLayoutY(250);
		root.setCenter(pane);
		RotateTransition t1 = new RotateTransition(Duration.seconds(2), t);
		ScaleTransition t2 = new ScaleTransition(Duration.seconds(3),t);
		t1.setByAngle(360);
		t2.setToX(15);
		t2.setToY(15);
		t1.play();
		t2.play();
	}
	
	public void hashFunctionAction() {
		Pane pane = new Pane();
		Label t = new Label("key = H(key) + i^2 (quadratic)");
		t.setId("text");
		
		Label t1 = new Label("hash = ((hash << 3) + hash + s.charAt(i)) % table.length");
		t1.setId("text");
		t.setLayoutX(50);
		t.setLayoutY(100);
		t1.setLayoutX(50);
		t1.setLayoutY(150);
		pane.getChildren().addAll(t,t1);
		root.setCenter(pane);
	}
	
	public void searchHashAction() {
		Pane pane = new Pane();
		
		
		TextField searchField = new TextField();
		
		Button searchButton = new Button("Search");
		
		searchButton.setLayoutX(400);
		searchButton.setLayoutY(80);
		pane.getChildren().add(searchField);
		pane.getChildren().add(searchButton);
		searchField.setLayoutX(220);
		searchField.setLayoutY(80);
		root.setCenter(pane);
		
		searchButton.setOnAction(e -> {
			String name = searchField.getText();
			City city = table.get(name);
			if (city != null ) {
			Label data = new Label("Rank = " + city.getRank() + "  Number of Turists = " + city.getNumerOfTourists());
			data.setLayoutX(200);
			data.setLayoutY(130);
			pane.getChildren().add(data);
			} else {
				System.out.println("Null Value");
			}
		});
	
	}
	public void printToFile() {
		try {
		PrintWriter out = new PrintWriter("Files\\out.txt");
		
		String line = String.format("%-30s %-25s %-25s", "City Name" , "Rank" , "Number Of Turists");
		out.println(line);
		for (int i = 0 ; i < table.table.length ; i++) {
			if (table.table[i] != null) {
			String str = String.format("%-30s %-25d %-25d", table.table[i].getData().getName() , 
					table.table[i].getData().getRank() , table.table[i].getData().getNumerOfTourists());
			out.println(str);
			} else {
				String str = String.format("%-30s %-25d %-25d","null" , 0 , 0);
				out.println(str);
			}
		}
		out.close();
		
		} catch (Exception e) {
			
		}
	}
	
	
	public void deleteCountryHashAction() {
		Pane pane = new Pane();

		TextField searchField = new TextField();
		Button deleteButton = new Button("Delete");
		
		deleteButton.setLayoutX(400);
		deleteButton.setLayoutY(80);
		pane.getChildren().add(searchField);
		pane.getChildren().add(deleteButton);
		searchField.setLayoutX(220);
		searchField.setLayoutY(80);
		root.setCenter(pane);
		deleteButton.setOnAction(e -> {
			String name = searchField.getText();
			City city = table.get(name);
			if (city != null) {
				table.delete(city);
				Alert alert = new Alert(AlertType.INFORMATION);
				alert.setTitle("Information Dialog");
				alert.setHeaderText(null);
				alert.setContentText(name + " Has been deleted");
				alert.showAndWait();
			} else {
				Alert alert = new Alert(AlertType.INFORMATION);
				alert.setTitle("Information Dialog");
				alert.setHeaderText(null);
				alert.setContentText(name + " Cann't be found");

				alert.showAndWait();
			}
		});
	}
	
	
	public void addCityAction() {
		
		GridPane pane = new GridPane();
		pane.setAlignment(Pos.CENTER);

		Label cityName = new Label("City Name");
		TextField cityField = new TextField();
		Label numOfTurists = new Label("Number of turists");
		TextField numOfTuristsField = new TextField();
		Button addCity = new Button("Add");

		pane.add(cityName, 0, 0);
		pane.add(cityField, 1, 0);
		pane.add(numOfTurists, 0, 1);
		pane.add(numOfTuristsField, 1, 1);
		pane.add(addCity, 1, 2);
		root.setCenter(pane);
		
		addCity.setOnAction(e2 -> {
			
				if (cityField.getText() != null && numOfTuristsField.getText() != null) {
					HashEntry index = table.get2(cityField.getText());
					if (index == null) {
						table.add(new City(cityField.getText(),++co,0));
						Alert alert = new Alert(AlertType.INFORMATION);
						alert.setTitle("Information Dialog");
						alert.setHeaderText(null);
						alert.setContentText(cityName.getText() + " has been added");
	
						alert.showAndWait();
					} else {
						Alert alert = new Alert(AlertType.INFORMATION);
						alert.setTitle("Information Dialog");
						alert.setHeaderText(null);
						alert.setContentText(cityField.getText() + "is Already Exist");

						alert.showAndWait();
					}
						
				} else {
					Alert alert = new Alert(AlertType.INFORMATION);
					alert.setTitle("Information Dialog");
					alert.setHeaderText(null);
					alert.setContentText("Wrong inputs");

					alert.showAndWait();
				}
			
		});
		
		
	}
	
	
	
	
	public static void main(String[] args) {
		launch(args);
	}
}
