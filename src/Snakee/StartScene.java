package Snakee;

import javafx.application.Application;
import javafx.application.Platform;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.stage.Stage;
import javafx.scene.text.Text;
import javafx.scene.image.Image;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;

import javax.swing.*;


public class StartScene extends Application {

    static String playerName;
    @Override
    public void start(Stage stage) {
        Platform.setImplicitExit(false);
        GridPane grid = new GridPane();
        //Creates a scene object
        Scene scene = new Scene(grid, 870, 560);


        grid.setAlignment(Pos.CENTER);
        grid.setHgap(10);
        grid.setVgap(10);
        grid.setPadding(new Insets(25, 25, 25, 25));

        //Creates text object
        Text scenetitle = new Text("Snakee");
        scenetitle.setId("title-text");
        grid.add(scenetitle, 0, 0, 2, 1);

        //Label
        Label playerName = new Label("Player Name:");
        grid.add(playerName, 0, 1);

        TextField playerTextField = new TextField();
        grid.add(playerTextField, 1, 1);

        Button startButton = new Button("Start");
        HBox hbBtn = new HBox(10);
        hbBtn.setAlignment(Pos.BOTTOM_RIGHT);
        hbBtn.getChildren().add(startButton);
        grid.add(hbBtn, 1, 4);

        final Text actiontarget = new Text();
        grid.add(actiontarget, 1, 6);

        startButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent e) {
                setPlayerName(playerTextField.getText());
                SwingUtilities.invokeLater(Play::new);
                stage.hide();
            }
        });


        //Sets stage title
        stage.setTitle("Snake Yipee");
        //Sets stage icon
        stage.getIcons().add(new Image(getClass().getResourceAsStream("images/snake-logo.png")));
        //Adds scene to the stage
        stage.setScene(scene);
        scene.getStylesheets().add
                (StartScene.class.getResource("Main.css").toExternalForm());
        //Displays the contents of the stage
        stage.show();
    }

    public void setPlayerName(String passedPlayerName){
        playerName = passedPlayerName;
    }

    public static String getPlayerName(){
        return playerName;
    }

    public static void main(String[] args) {
        launch(args);
    }
}