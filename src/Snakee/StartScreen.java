package Snakee;

import javafx.application.Application;
import javafx.scene.image.Image;
import javafx.scene.layout.*;
import javafx.stage.Stage;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Button;

public class StartScreen extends Application {
    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) {
        StackPane root = new StackPane();
        root.setId("start-pane");

        Scene scene = new Scene(root, 870, 560);
        scene.getStylesheets().add(String.valueOf(this.getClass().getResource("Main.css")));


        primaryStage.setTitle("Snakee Yipee");
        primaryStage.getIcons().add(new Image(getClass().getResourceAsStream("images/snake-logo.png")));
        primaryStage.setScene(scene);
        primaryStage.show();
    }
}