import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.geometry.Pos;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class mouseEvent extends Application {
    private int clickCount = 0;

    @Override
    public void start(Stage primaryStage) {

        Button button = new Button("Click here");
        Text clickCounterText = new Text("Clicks: 0");
        button.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                clickCount++;
                clickCounterText.setText("Clicks: " + clickCount);
            }
        });


        Text clickPositionText = new Text();

        Rectangle rectangle = new Rectangle(100, 100, Color.BLUE);
        rectangle.setOnMouseEntered(e -> rectangle.setFill(Color.YELLOW));
        rectangle.setOnMouseExited(e -> rectangle.setFill(Color.BLACK));

        BorderPane pane = new BorderPane();  
        
        Scene scene = new Scene(pane, 400, 300);
        
        VBox vbox = new VBox(8);
        vbox.getChildren().addAll(button,clickCounterText,rectangle,clickPositionText);
        vbox.setAlignment(Pos.CENTER);

        scene.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                double x = event.getX();
                double y = event.getY();
                clickPositionText.setText("Clicked at: (" + x + ", " + y + ")");
            }
        });
        pane.setTop(vbox);

        primaryStage.setScene(scene);
        primaryStage.setTitle("Mouse Event");
        primaryStage.show();
    }

   
}
