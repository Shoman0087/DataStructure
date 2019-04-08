package application;
	
import java.util.ArrayList;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.*;
import javafx.scene.shape.Cylinder;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;

public class Main extends Application
{

    int n = 0;
    int Num;
    ArrayList<Cylinder> c = new ArrayList<>();
    BorderPane root;
    VBox ColumnA;
    VBox ColumnB;
    VBox ColumnC;
    StackPane CA;
    StackPane CB;
    StackPane CC;
    Rectangle RA;
    Rectangle RB;
    Rectangle RC;
    HBox AllBoxes;
    Stage primaryStage = new Stage();
    Scene scene;

    public void Tower(int N, char A, char B, char C)
    {
        if (N > 0)
        {
            n++;

            Tower(N - 1, A, C, B);
            Platform.runLater(() ->
            {
                move(N, A, C);
            });
            try
            {
                Thread.sleep(500);
            } catch (InterruptedException ex)
            {
                Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
            }
            System.out.println("Move " + A + " to " + C);
            Tower(N - 1, B, A, C);
            //move(N,A,C);

        }
    }

    @Override
    public void start(Stage aa)
    {
        try
        {

            Scanner scan = new Scanner(System.in);

            root = new BorderPane();
            ColumnA = new VBox();
            ColumnA.setAlignment(Pos.BOTTOM_CENTER);
            ColumnB = new VBox();
            ColumnB.setAlignment(Pos.BOTTOM_CENTER);
            ColumnC = new VBox();
            ColumnC.setAlignment(Pos.BOTTOM_CENTER);

            CA = new StackPane();
            CA.setAlignment(Pos.BOTTOM_CENTER);
            CB = new StackPane();
            CB.setAlignment(Pos.BOTTOM_CENTER);
            CC = new StackPane();
            CC.setAlignment(Pos.BOTTOM_CENTER);

            RA = new Rectangle(10, 150);
            RB = new Rectangle(10, 150);
            RC = new Rectangle(10, 150);

            CA.getChildren().addAll(RA, ColumnA);
            CB.getChildren().addAll(RB, ColumnB);
            CC.getChildren().addAll(RC, ColumnC);

            AllBoxes = new HBox(150);
            AllBoxes.setAlignment(Pos.CENTER);
            AllBoxes.getChildren().addAll(CA, CB, CC);

            root.setCenter(AllBoxes);

            TextField nFields = new TextField();
            Button start = new Button("Start");

            HBox topBox = new HBox(20);
            topBox.setAlignment(Pos.CENTER);

            topBox.getChildren().addAll(nFields,start);
            root.setTop(topBox);

            start.setOnAction(e ->
            {
            	
            	Num = Integer.parseInt(nFields.getText());
                int radius = 10;
                for (int i = 0; i < Num; i++)
                {
                    c.add(new Cylinder(radius, 10));
                    radius += 10;
                }
                ColumnA.getChildren().addAll(c);
               
                new Thread(() ->
                {
                	 try {
                         Thread.sleep(2000);
                         } catch (Exception ex) {
                         	
                         }
                    Tower(Integer.parseInt(nFields.getText()), 'A', 'B', 'C');
                    System.out.println(n);
                }).start();
            });
            
            

           
            scene = new Scene(root, 600, 400);
            scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
            primaryStage.setScene(scene);
            primaryStage.show();

        } catch (Exception e)
        {
            e.printStackTrace();
        }
    }

    public void move(int N, char A, char C)
    {
       // System.out.println("in move");
        Cylinder temp;
        if (A == 'A' && C == 'B' && ColumnA.getChildren().size() != 0)
        {
            temp = (Cylinder) ColumnA.getChildren().get(0);
            ColumnA.getChildren().remove(0);
            ColumnB.getChildren().add(0,temp);
        } else if (A == 'A' && C == 'C' && ColumnA.getChildren().size() != 0)
        {
            temp = (Cylinder) ColumnA.getChildren().get(0);
            ColumnA.getChildren().remove(0);
            ColumnC.getChildren().add(0,temp);
        } else if (A == 'B' && C == 'A' && ColumnB.getChildren().size() != 0)
        {
            temp = (Cylinder) ColumnB.getChildren().get(0);
            ColumnB.getChildren().remove(0);
            ColumnA.getChildren().add(0,temp);
        } else if (A == 'B' && C == 'C' && ColumnB.getChildren().size() != 0)
        {
            temp = (Cylinder) ColumnB.getChildren().get(0);
            ColumnB.getChildren().remove(0);
            ColumnC.getChildren().add(0,temp);
        } else if (A == 'C' && C == 'A' && ColumnC.getChildren().size() != 0)
        {
            temp = (Cylinder) ColumnC.getChildren().get(0);
            ColumnC.getChildren().remove(0);
            ColumnA.getChildren().add(0,temp);
        } else if (A == 'C' && C == 'B' && ColumnC.getChildren().size() != 0)
        {
            temp = (Cylinder) ColumnC.getChildren().get(0);
            ColumnC.getChildren().remove(0);
            ColumnB.getChildren().add(0,temp);
        }
       
    }

    public static void main(String[] args)
    {
        launch(args);
    }
}
