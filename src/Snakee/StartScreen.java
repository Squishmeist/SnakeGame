package Snakee;

import javafx.application.Application;
import javafx.application.Platform;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import javafx.scene.text.Text;
import javafx.scene.image.Image;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;

import javax.swing.*;


public class StartScreen extends Application {
    @Override
    public void start(Stage stage) {
        Platform.setImplicitExit(false);

        //BUTTON CREATED
        Button startButton = new Button("Start");
        startButton.setOnAction(e -> {
            SwingUtilities.invokeLater(Play::new);
            stage.hide();
        });

        StackPane root = new StackPane(startButton);
        //Creates a scene object
        Scene scene = new Scene(root, 870, 560);
        //Sets stage title
        stage.setTitle("Snake Yipee");
        //Sets stage icon
        stage.getIcons().add(new Image(getClass().getResourceAsStream("images/snake-logo.png")));
        //Adds scene to the stage
        stage.setScene(scene);
        scene.getStylesheets().add
                (StartScreen.class.getResource("Main.css").toExternalForm());
        //Displays the contents of the stage
        stage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}