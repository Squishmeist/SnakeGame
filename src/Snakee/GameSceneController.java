package Snakee;

import javafx.fxml.FXML;
import javafx.scene.control.Label;

public class GameSceneController {

    @FXML
    Label playernameLabel;

    public void displayName(String playerName) {
        playernameLabel.setText("PLAYER : " + playerName);
    }



}