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
import java.util.Objects;

import static Snakee.GameSceneController.playerName;
import static Snakee.GameSceneController.playerScore;


public class EndSceneController {
    @FXML
    Label playerscoreLabel;
    @FXML
    Label playernameLabel;
    //Displays playername and playerscore
    public void initialize(){
        playerscoreLabel.setText("SCORE : " + playerScore);
        playernameLabel.setText("PLAYER : " + playerName);
    }
    //Switch to StartScene
    public void SwitchToStartScene(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("StartScene.fxml")));
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }
    //Switch to LeaderboardScene
    public void SwitchToLeaderboardScene(ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("LeaderboardScene.fxml"));
        Parent root = loader.load();
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }
    public void ExitGame(ActionEvent event) throws IOException{
        System.exit(0);
    }

}
