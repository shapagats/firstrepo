import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class TicketBooking extends Application {

    @Override
    public void start(Stage primaryStage) {
        primaryStage.setTitle("Movie Ticket Booking System");

        Label movieLabel = new Label("Select a Movie:");
        ComboBox<String> movieComboBox = new ComboBox<>();
        movieComboBox.getItems().addAll("Action Movie", "Romantic Comedy", "Sci-Fi Adventure");
        movieComboBox.setValue("Action Movie");


        Label ticketsLabel = new Label("Choose Number of Tickets:");
        Slider ticketsSlider = new Slider(1, 10, 1);
        ticketsSlider.setShowTickLabels(true);
        ticketsSlider.setShowTickMarks(true);
        ticketsSlider.setMajorTickUnit(1);


        Label seatLabel = new Label("Select Seat Preference:");
        ToggleGroup seatGroup = new ToggleGroup();
        RadioButton windowSeat = new RadioButton("Window Seat");
        RadioButton aisleSeat = new RadioButton("Aisle Seat");
        RadioButton centerSeat = new RadioButton("Center Seat");
        windowSeat.setToggleGroup(seatGroup);
        aisleSeat.setToggleGroup(seatGroup);
        centerSeat.setToggleGroup(seatGroup);
        windowSeat.setSelected(true);

        
        Label snacksLabel = new Label("Choose Snacks:");
        CheckBox popcornCheckBox = new CheckBox("Popcorn");
        CheckBox nachosCheckBox = new CheckBox("Nachos");
        CheckBox sodaCheckBox = new CheckBox("Soda");
        CheckBox candyCheckBox = new CheckBox("Candy");

     
        Button placeOrderButton = new Button("Place Order");
        TextArea orderSummaryArea = new TextArea();
        orderSummaryArea.setEditable(false);

        placeOrderButton.setOnAction(e -> {
            StringBuilder summary = new StringBuilder();
            summary.append("Movie: ").append(movieComboBox.getValue()).append("\n");
            summary.append("Number of Tickets: ").append((int) ticketsSlider.getValue()).append("\n");
            RadioButton selectedSeat = (RadioButton) seatGroup.getSelectedToggle();
            summary.append("Seat Preference: ").append(selectedSeat.getText()).append("\n");
            summary.append("Selected Snacks: ");
            if (popcornCheckBox.isSelected()) summary.append("Popcorn, ");
            if (nachosCheckBox.isSelected()) summary.append("Nachos, ");
            if (sodaCheckBox.isSelected()) summary.append("Soda, ");
            if (candyCheckBox.isSelected()) summary.append("Candy, ");
            orderSummaryArea.setText(summary.toString());
        });

        GridPane grid = new GridPane();
        grid.setPadding(new Insets(20));
        grid.setVgap(10);
        grid.setHgap(10);

        grid.add(movieLabel, 0, 0);
        grid.add(movieComboBox, 1, 0);
        grid.add(ticketsLabel, 0, 1);
        grid.add(ticketsSlider, 1, 1);
        grid.add(seatLabel, 0, 2);
        grid.add(windowSeat, 1, 2);
        grid.add(aisleSeat, 1, 3);
        grid.add(centerSeat, 1, 4);
        grid.add(snacksLabel, 0, 5);
        grid.add(popcornCheckBox, 1, 5);
        grid.add(nachosCheckBox, 1, 6);
        grid.add(sodaCheckBox, 1, 7);
        grid.add(candyCheckBox, 1, 8);
        grid.add(placeOrderButton, 0, 9);

        VBox vbox = new VBox(grid, orderSummaryArea);
        vbox.setSpacing(10);
        Scene scene = new Scene(vbox, 400, 400);

        primaryStage.setScene(scene);
        primaryStage.show();
    }

}
