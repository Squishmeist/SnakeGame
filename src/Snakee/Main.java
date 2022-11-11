package Snakee;

import javafx.application.Application;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.scene.image.Image;
import javafx.scene.input.KeyEvent;
import javafx.stage.Stage;
import javafx.scene.Parent;
import javafx.scene.Scene;

public class Main extends Application {

    @Override
    public void start(Stage stage) {
        try {
            Parent root = FXMLLoader.load(getClass().getResource("StartScene.fxml"));
            //Creates a scene object
            Scene scene = new Scene(root, 870, 560);

            //Sets stage title
            stage.setTitle("Snake Yipee");
            //Sets stage icon
            stage.getIcons().add(new Image(getClass().getResourceAsStream("images/snake-logo.png")));
            //Adds scene to the stage
            stage.setScene(scene);
            //Displays the contents of the stage
            stage.show();

        } catch(Exception e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        launch(args);
    }
}