import javafx.application.Application;
import javafx.geometry.*;
import javafx.scene.*;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import java.util.ArrayList;
import java.util.List;

public class RecipeApp extends Application {
    private List<Recipe> recipes;
    private int currentRecipeIndex = 0;
    private Text recipeNameText;
    private TextArea ingredientsTextArea;
    private TextArea instructionsTextArea;

    @Override
    public void start(Stage primaryStage) {
      
        recipes = new ArrayList<>();
        recipes.add(new Recipe("Pasta Carbonara", "Pasta, Eggs, Bacon", "1. Cook pasta\n2. Fry bacon\n3. Mix eggs and cheese\n4. Combine everything"));
        recipes.add(new Recipe("Chicken Stir Fry", "Chicken, Vegetables, Soy Sauce", "1. Cook chicken\n2. Stir-fry vegetables\n3. Add soy sauce"));
        recipes.add(new Recipe("Zapikanka", "Meal, Vegetables, Cheese", "1. Cook meal\n2. Stir-fry vegetables\n3. Add cheese"));

        
        recipeNameText = new Text();
        ingredientsTextArea = new TextArea();
        instructionsTextArea = new TextArea();
        updateRecipeDisplay();

        StackPane recipeDisplayPane = new StackPane();
        recipeDisplayPane.getChildren().addAll(recipeNameText, createRecipeDetails());

        Button previousButton = new Button("Previous Recipe");
        previousButton.setOnAction(e -> showPreviousRecipe());

        Button nextButton = new Button("Next Recipe");
        nextButton.setOnAction(e -> showNextRecipe());

        HBox navigationButtons = new HBox(10, previousButton, nextButton);
        navigationButtons.setAlignment(Pos.CENTER);

        BorderPane mainPane = new BorderPane();
        mainPane.setTop(navigationButtons);
        mainPane.setCenter(recipeDisplayPane);

        Scene scene = new Scene(mainPane, 600, 400);
        primaryStage.setScene(scene);
        primaryStage.setTitle("Recipe App");
        primaryStage.show();
    }

    private void showNextRecipe() {
        currentRecipeIndex = (currentRecipeIndex + 1) % recipes.size();
        updateRecipeDisplay();
    }

    private void showPreviousRecipe() {
        currentRecipeIndex = (currentRecipeIndex - 1 + recipes.size()) % recipes.size();
        updateRecipeDisplay();
    }

    private void updateRecipeDisplay() {
        Recipe currentRecipe = recipes.get(currentRecipeIndex);
        recipeNameText.setText(currentRecipe.getName());
        ingredientsTextArea.setText(currentRecipe.getIngredients());
        instructionsTextArea.setText(currentRecipe.getInstructions());
    }

    private VBox createRecipeDetails() {
        VBox details = new VBox(10);
        details.setPadding(new Insets(10,10,10,10));
        details.getChildren().addAll(
                new Text("Ingredients:"),
                ingredientsTextArea,
                new Text("Instructions:"),
                instructionsTextArea
        );
        return details;
    }
}

class Recipe {
    private String name;
    private String ingredients;
    private String instructions;

    public Recipe(String name, String ingredients, String instructions) {
        this.name = name;
        this.ingredients = ingredients;
        this.instructions = instructions;
    }

    public String getName() {
        return name;
    }

    public String getIngredients() {
        return ingredients;
    }

    public String getInstructions() {
        return instructions;
    }
}