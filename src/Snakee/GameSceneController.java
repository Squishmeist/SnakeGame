package Snakee;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.stage.Stage;

import java.io.IOException;

public class GameSceneController {
    private Stage stage;
    private Scene scene;
    private Parent root;

    @FXML
    Label playernameLabel;

    public void playerName(String playerName) {
        playernameLabel.setText("PLAYER : " + playerName);
    }

    public void switchToStartScene(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("StartScene.fxml"));
        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }


    @FXML
    public void keyPressed(KeyEvent event){
        if(event.getCode() == KeyCode.W){
            System.out.println("Pressed W");
        }

        if(event.getCode() == KeyCode.S){
            System.out.println("Pressed S");
        }

        if(event.getCode() == KeyCode.A){
            System.out.println("Pressed A");
        }

        if(event.getCode() == KeyCode.D){
            System.out.println("Pressed D");
        }



    }
}
