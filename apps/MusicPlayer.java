import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.media.MediaView;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

import java.io.File;

public class MusicPlayer extends Application {

    private MediaPlayer mediaPlayer;
    private MediaView mediaView;
    private FileChooser fileChooser;

    @Override
    public void start(Stage primaryStage) {
        BorderPane root = new BorderPane();

        MenuBar menuBar = new MenuBar();
        Menu fileMenu = new Menu("File");
        MenuItem openMenuItem = new MenuItem("Open");
        openMenuItem.setOnAction(event -> openFile(primaryStage));
        fileMenu.getItems().add(openMenuItem);
        menuBar.getMenus().add(fileMenu);

        mediaView = new MediaView();

        Button playButton = new Button("Play");
        Button pauseButton = new Button("Pause");
        Button stopButton = new Button("Stop");

        playButton.setOnAction(event -> playMedia());
        pauseButton.setOnAction(event -> pauseMedia());
        stopButton.setOnAction(event -> stopMedia());

        fileChooser = new FileChooser();
        fileChooser.getExtensionFilters().addAll(new FileChooser.ExtensionFilter("audio Files", "*.mp3"));
       
        HBox buttonBar = new HBox(playButton, pauseButton, stopButton);
        root.setTop(menuBar);
        root.setCenter(buttonBar);
     

        Scene scene = new Scene(root, 400, 300);
        primaryStage.setScene(scene);
        primaryStage.setTitle("Audio Player");
        primaryStage.show();
    }

    private void openFile(Stage primaryStage) {
        File file = fileChooser.showOpenDialog(primaryStage);
        if (file != null) {
            Media media = new Media(file.toURI().toString());
            mediaPlayer = new MediaPlayer(media);
            mediaView.setMediaPlayer(mediaPlayer);
        }
    }

    private void playMedia() {
        if (mediaPlayer != null) {
            mediaPlayer.play();
        }
    }

    private void pauseMedia() {
        if (mediaPlayer != null) {
            mediaPlayer.pause();
        }
    }

    private void stopMedia() {
        if (mediaPlayer != null) {
            mediaPlayer.stop();
        }
    }


}