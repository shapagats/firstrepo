import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.*;
import javafx.scene.text.Font;
import javafx.scene.text.FontPosture;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class Shapes extends Application {

    @Override
    public void start(Stage primaryStage) {
        HBox shapesBox = new HBox(10);
        shapesBox.setPadding(new Insets(10));

       
        Circle circle = new Circle(40, Color.RED);
        Rectangle rectangle = new Rectangle(80, 80, Color.BLUE);
        Ellipse ellipse = new Ellipse(50, 30);
        ellipse.setFill(Color.GREEN);
        Polygon polygon = new Polygon();
        polygon.getPoints().addAll(new Double[]{
                50.0, 0.0,
                100.0, 100.0,
                0.0, 100.0 });
        polygon.setFill(Color.YELLOW);
        Line line = new Line(0, 0, 100, 100);
        line.setStroke(Color.ORANGE);

       
        Text text1 = new Text("Hello, World!");
        text1.setFont(Font.font("Verdana", FontWeight.BOLD, FontPosture.REGULAR, 12));
        text1.setFill(Color.GREEN);

        Text text2 = new Text("JavaFX Rocks!");
        text2.setFont(Font.font("Arial", FontWeight.NORMAL, FontPosture.ITALIC, 12));
        text2.setFill(Color.BLACK);

        Text text3 = new Text("Styled Text");
        text2.setFont(Font.font("Noto Sans", FontWeight.NORMAL, FontPosture.ITALIC, 12));
        text2.setFill(Color.BLACK);

        StackPane ellipsePane = new StackPane();
        ellipsePane.getChildren().addAll(ellipse, text3);

        StackPane polygonPane = new StackPane();
        polygonPane.getChildren().addAll(polygon, text2);

        StackPane linePane = new StackPane();
        linePane.getChildren().addAll(line, text1);

        
        shapesBox.getChildren().addAll(ellipsePane, polygonPane, linePane);

        
        Scene scene = new Scene(shapesBox, 600, 150);

        primaryStage.setTitle("Shapes");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    
}
