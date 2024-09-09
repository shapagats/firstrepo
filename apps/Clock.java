import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Line;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.util.Duration;
import java.time.LocalTime;

public class Clock extends Application {

    @Override
    public void start(Stage primaryStage) {
        ClockPane clock = new ClockPane();
        Scene scene = new Scene(clock, 400, 400);
        primaryStage.setScene(scene);
        primaryStage.setTitle("Analog Clock");
        primaryStage.show();
    }
}

class ClockPane extends Pane {
    private int hour;
    private int minute;
    private int second;

    public ClockPane() {
        setCurrentTime();
        Timeline animation = new Timeline(
                new KeyFrame(Duration.seconds(1), e -> {
                    setCurrentTime();
                    paintClock();
                })
        );
        animation.setCycleCount(Animation.INDEFINITE);
        animation.play();
        paintClock();
    }

    private void setCurrentTime() {
        LocalTime currentTime = LocalTime.now();
        hour = currentTime.getHour();
        minute = currentTime.getMinute();
        second = currentTime.getSecond();
    }

    private void paintClock() {
    double centerX = getWidth() / 2;
    double centerY = getHeight() / 2;
    double radius = Math.min(getWidth(), getHeight()) * 0.4;

    Circle circle = new Circle(centerX, centerY, radius);
    circle.setFill(Color.WHITE);
    circle.setStroke(Color.BLACK);

    
    Text t12 = new Text(centerX - 5, centerY - radius + 12, "12");
    Text t3 = new Text(centerX + radius - 10, centerY + 3, "3");
    Text t6 = new Text(centerX - 3, centerY + radius - 3, "6");
    Text t9 = new Text(centerX - radius + 3, centerY + 5, "9");

    
    double sLength = radius * 0.8;
    double secondX = centerX + sLength * Math.sin(second * (2 * Math.PI / 60));
    double secondY = centerY - sLength * Math.cos(second * (2 * Math.PI / 60));
    Line sLine = new Line(centerX, centerY, secondX, secondY);
    sLine.setStroke(Color.RED);

    double mLength = radius * 0.65;
    double minuteX = centerX + mLength * Math.sin(minute * (2 * Math.PI / 60));
    double minuteY = centerY - mLength * Math.cos(minute * (2 * Math.PI / 60));
    Line mLine = new Line(centerX, centerY, minuteX, minuteY);
    mLine.setStroke(Color.BLUE);

    double hLength = radius * 0.5;
    double hourX = centerX + hLength * Math.sin((hour % 12 + minute / 60.0) * (2 * Math.PI / 12));
    double hourY = centerY - hLength * Math.cos((hour % 12 + minute / 60.0) * (2 * Math.PI / 12));
    Line hLine = new Line(centerX, centerY, hourX, hourY);
    hLine.setStroke(Color.GREEN);

    getChildren().clear();
    getChildren().addAll(circle, sLine, mLine, hLine, t12, t3, t6, t9);
 }

}