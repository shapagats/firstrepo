import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import java.util.Random;
import javafx.stage.Stage;

import java.util.HashMap;
import java.util.Map;

public class movieticket extends Application {

    private static final Map<String, Movie> movies = new HashMap<>();


    private static final Map<String, Snack> snacks = new HashMap<>();

    private ComboBox<String> movieComboBox;
    private Label movieDescriptionLabel;
    private ImageView movieImageView;
    private ListView<String> snackListView;
    private ImageView snackImageView;
    private Label totalCostLabel;
    private Random random = new Random();

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) {

        initializeMovies();
        initializeSnacks();


        movieComboBox = new ComboBox<>();
        movieComboBox.getItems().addAll(movies.keySet());
        movieComboBox.setPromptText("movies");
        movieComboBox.setOnAction(event -> {
            String selectedMovie = movieComboBox.getValue();
            if (selectedMovie != null) {
                Movie movie = movies.get(selectedMovie);
                movieDescriptionLabel.setText(movie.getDescription());
                movieImageView.setImage(new Image(movie.getImagePath()));
            }
        });

        movieDescriptionLabel = new Label();
        movieImageView = new ImageView();
        movieImageView.setFitHeight(200);
        movieImageView.setPreserveRatio(true);

        VBox movieVBox = new VBox(10, new Label("Select Movie:"), movieComboBox, movieDescriptionLabel, movieImageView);
        movieVBox.setPadding(new Insets(10));
        

        snackListView = new ListView<>();
        snackListView.getItems().addAll(snacks.keySet());
        snackListView.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
            if (newValue != null) {
                Snack snack = snacks.get(newValue);
                snackImageView.setImage(new Image(snack.getImagePath()));
            }
        });

        snackImageView = new ImageView();
        snackImageView.setFitHeight(100);
        snackImageView.setPreserveRatio(true);

        VBox snackVBox = new VBox(10, new Label("Select Snack:"), snackListView, snackImageView);
        snackVBox.setPadding(new Insets(10));


        Button placeOrderButton = new Button("Place Order");
        totalCostLabel = new Label();

        VBox orderSummaryVBox = new VBox(10, placeOrderButton, totalCostLabel);
        orderSummaryVBox.setPadding(new Insets(10));

        placeOrderButton.setOnAction(event -> calculateTotalCost());

       
        HBox root = new HBox(20, movieVBox, snackVBox, orderSummaryVBox);
        root.setPadding(new Insets(20));

        Scene scene = new Scene(root, 800, 400);
        primaryStage.setScene(scene);
        primaryStage.setTitle("Movie Ticket Booking System");
        primaryStage.show();
    }

    private void calculateTotalCost() {

        double movieCost =  10 + random.nextInt(11);
        int numTickets = 1; 
        double snackCost = 5 * snackListView.getSelectionModel().getSelectedItems().size();

        double totalCost = movieCost * numTickets + snackCost;
        totalCostLabel.setText("Total Cost: $" + totalCost);
    }

    private void initializeMovies() {
        movies.put("Movie 1", new Movie("Movie 1", "Description", "movie1.jpg"));
        movies.put("Movie 2", new Movie("Movie 2", "Description", "movie2.jpg"));
    }


    private void initializeSnacks() {
        snacks.put("Popcorn", new Snack("Popcorn", "popcorn.jpg"));
        snacks.put("Soda", new Snack("Soda", "soda.jpg"));
        snacks.put("Candy", new Snack("Candy", "candy.jpg"));
    }

    private static class Movie {
        private final String title;
        private final String description;
        private final String imagePath;

        public Movie(String title, String description, String imagePath) {
            this.title = title;
            this.description = description;
            this.imagePath = imagePath;
        }

        public String getDescription() {
            return description;
        }

        public String getImagePath() {
            return imagePath;
        }
    }


    private static class Snack {
        private final String name;
        private final String imagePath;

        public Snack(String name, String imagePath) {
            this.name = name;
            this.imagePath = imagePath;
        }

        public String getImagePath() {
            return imagePath;
        }
    }
}
