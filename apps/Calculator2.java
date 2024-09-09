import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

public class Calculator2 extends Application {

    private TextField num1Field;
    private TextField num2Field;
    private TextField resultField;

    @Override
    public void start(Stage primaryStage) {
        primaryStage.setTitle("Calculator");

        GridPane grid = new GridPane();
        grid.setPadding(new Insets(10, 10, 10, 10));
        grid.setVgap(5);
        grid.setHgap(5);

        Label num1Label = new Label("Number 1:");
        GridPane.setConstraints(num1Label, 0, 0);
        num1Field = new TextField();
        GridPane.setConstraints(num1Field, 1, 0);

        Label num2Label = new Label("Number 2:");
        GridPane.setConstraints(num2Label, 0, 1);
        num2Field = new TextField();
        GridPane.setConstraints(num2Field, 1, 1);

        Button addButton = new Button("+");
        GridPane.setConstraints(addButton, 0, 2);
        addButton.setOnAction(this::addClicked);

        Button subButton = new Button("-");
        GridPane.setConstraints(subButton, 1, 2);
        subButton.setOnAction(this::subClicked);

        Button mulButton = new Button("*");
        GridPane.setConstraints(mulButton, 2, 2);
        mulButton.setOnAction(this::mulClicked);

        Button divButton = new Button("/");
        GridPane.setConstraints(divButton, 3, 2);
        divButton.setOnAction(this::divClicked);

        resultField = new TextField();
        resultField.setEditable(false);
        GridPane.setConstraints(resultField, 0, 3, 4, 1);

        grid.getChildren().addAll(num1Label, num1Field, num2Label, num2Field,
                addButton, subButton, mulButton, divButton, resultField);

        Scene scene = new Scene(grid, 300, 200);
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    private void addClicked(ActionEvent event) {
        double num1 = Double.parseDouble(num1Field.getText());
        double num2 = Double.parseDouble(num2Field.getText());
        double result = num1 + num2;
        resultField.setText(String.valueOf(result));
    }

    private void subClicked(ActionEvent event) {
        double num1 = Double.parseDouble(num1Field.getText());
        double num2 = Double.parseDouble(num2Field.getText());
        double result = num1 - num2;
        resultField.setText(String.valueOf(result));
    }

    private void mulClicked(ActionEvent event) {
        double num1 = Double.parseDouble(num1Field.getText());
        double num2 = Double.parseDouble(num2Field.getText());
        double result = num1 * num2;
        resultField.setText(String.valueOf(result));
    }

    private void divClicked(ActionEvent event) {
        double num1 = Double.parseDouble(num1Field.getText());
        double num2 = Double.parseDouble(num2Field.getText());
        if (num2 != 0) {
            double result = num1 / num2;
            resultField.setText(String.valueOf(result));
        } else {
            resultField.setText("Error: Division by zero");
        }
    }

}
