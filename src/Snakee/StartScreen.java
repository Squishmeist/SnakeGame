package Snakee;

import javafx.application.Application;
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


public class StartScreen extends Application {
    @Override
    public void start(Stage stage) {
        GridPane grid = new GridPane();

        grid.setAlignment(Pos.CENTER);
        grid.setHgap(10);
        grid.setVgap(10);

        grid.setPadding(new Insets(25, 25, 25, 25));

        Text scenetitle = new Text("Snakee");
        scenetitle.setId("title-text");
        grid.add(scenetitle, 0, 0, 2, 1);

        Label playerNameLabel = new Label("Player Name:");
        grid.add(playerNameLabel, 0, 1);

        TextField playerNameField = new TextField();
        grid.add(playerNameField, 1, 1);

        //BUTTON CREATED
        Button startButton = new Button("Start");
        HBox hbBtn = new HBox(10);

        hbBtn.setAlignment(Pos.BOTTOM_RIGHT);
        hbBtn.getChildren().add(startButton);

        grid.add(hbBtn, 1, 4);

        //BUTTON EVENT HANDLER DISPLAY MESSAGE
        final Text actiontarget = new Text();
        grid.add(actiontarget, 1, 6);
        actiontarget.setId("actiontarget");

        //BUTTON EVENT HANDLER
        startButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent e) {
                actiontarget.setFill(Color.FIREBRICK);
                actiontarget.setText("Start Button Pressed");
            }
        });



        //Creates a scene object
        Scene scene = new Scene(grid, 870, 560);
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