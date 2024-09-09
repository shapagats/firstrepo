import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Stage;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.text.*;
import javafx.scene.image.Image;
 import javafx.scene.image.ImageView;
 import javafx.geometry.Insets;
 import javafx.scene.shape.*;

public class JavaFx extends Application{
	public void start(Stage primaryStage){
        Pane pane = new Pane();

Arc arc1 = new Arc(150, 100, 80, 80, 30, 35); // Create an arc
 arc1.setFill(Color.RED); // Set fill color
 arc1.setType(ArcType.ROUND); // Set arc type
 pane.getChildren().add(new Text(210, 40, "arc1: round"));
 pane.getChildren().add(arc1); // Add arc to pane

 Arc arc2 = new Arc(150, 100, 80, 80, 30 + 90, 35);
 arc2.setFill(Color.WHITE); 
 arc2.setType(ArcType.OPEN); 
 arc2.setStroke(Color.BLACK);
 pane.getChildren().add(new Text(20, 40, "arc2: open"));
 pane.getChildren().add(arc2); 

 Arc arc3 = new Arc(150, 100, 80, 80, 30 + 180, 35);
 arc3.setFill(Color.WHITE); 
 arc3.setType(ArcType.CHORD); 
 arc3.setStroke(Color.BLACK);
 pane.getChildren().add(new Text(20, 170, "arc3: chord"));
 pane.getChildren().add(arc3);
 
 Scene scene = new Scene(pane); 
 primaryStage.setTitle("ShowHBoxVBox"); 
 primaryStage.setScene(scene); 
 primaryStage.show(); // Display the stage
 }

 
 }
 
