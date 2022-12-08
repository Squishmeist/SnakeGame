package Snakee;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.stage.Stage;

import java.io.IOException;

import static Snakee.GameSceneController.playerScore;
import static Snakee.GameSceneController.levelNumber;

public class LevelSceneController {
    @FXML
    Label scoreLabel;
    @FXML
    Label levelLabel;

    public void initialize(){
        switch (levelNumber) {
            case 120 -> {
                levelLabel.setText("LEVEL : 1");
                levelNumber = 80;
            }
            case 80 -> {
                levelLabel.setText("LEVEL : 2");
                levelNumber = 40;
            }
            case 40 -> levelLabel.setText("LEVEL : 3");
        }
        scoreLabel.setText("SCORE : " + playerScore);
    }

    public void SwitchToGameScene(ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("fxmls/GameScene.fxml"));
        Parent root = loader.load();
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }
}
