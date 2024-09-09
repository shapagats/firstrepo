import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

public class Calculator extends Application {
    private TextField numField1;
    private TextField numField2;
    private ComboBox<String> operationComboBox;
    private Button calculateButton;
    private Label resultLabel;

    @Override
    public void start(Stage primaryStage) {
        primaryStage.setTitle("Simple Calculator");

        GridPane gridPane = new GridPane();
        gridPane.setAlignment(Pos.CENTER);
        gridPane.setHgap(10);
        gridPane.setVgap(10);
        gridPane.setPadding(new Insets(20));

        numField1 = new TextField();
        numField1.setPromptText("Enter number 1");
        gridPane.add(numField1, 0, 0);

        numField2 = new TextField();
        numField2.setPromptText("Enter number 2");
        gridPane.add(numField2, 0, 1);

        operationComboBox = new ComboBox<>();
        operationComboBox.getItems().addAll("Addition", "Subtraction", "Multiplication", "Division");
        operationComboBox.setValue("Addition");
        gridPane.add(operationComboBox, 1, 0);

        calculateButton = new Button("Calculate");
        calculateButton.setOnAction(e -> calculate());
        gridPane.add(calculateButton, 1, 1);

        resultLabel = new Label();
        gridPane.add(resultLabel, 0, 2, 2, 1);

        Scene scene = new Scene(gridPane, 400, 200);
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    private void calculate() {
        try {
            double num1 = Double.parseDouble(numField1.getText());
            double num2 = Double.parseDouble(numField2.getText());
            String operation = operationComboBox.getValue();

            double result;
            switch (operation) {
                case "Addition":
                    result = num1 + num2;
                    break;
                case "Subtraction":
                    result = num1 - num2;
                    break;
                case "Multiplication":
                    result = num1 * num2;
                    break;
                case "Division":
                    if (num2 == 0) {
                        resultLabel.setText("Error: Division by zero is not allowed.");
                        return;
                    }
                    result = num1 / num2;
                    break;
                default:
                    resultLabel.setText("Error: Invalid operation.");
                    return;
            }

            resultLabel.setText("Result: " + result);
        } catch (NumberFormatException ex) {
            resultLabel.setText("Error: Invalid input. Please enter valid numbers.");
        }
    }

}
