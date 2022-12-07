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

    /**
     * Method ran when EndScene.fxml is loaded.
     * <p>
     * This method sets the playerscoreLabel to display the text "SCORE : " and the playerScore variable which stores the score achieved.
     * It also sets the playernameLabel to display the text "PLAYER : " and the playerName variable which stores the name entered from the player.
     */
    public void initialize(){
        playerscoreLabel.setText("SCORE : " + playerScore);
        playernameLabel.setText("PLAYER : " + playerName);
    }

    /**
     * Method loads the StartScene.fxml when an event occurs.
     * @param event
     * @throws IOException
     */
    public void SwitchToStartScene(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("fxmls/StartScene.fxml")));
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    /**
     * Method loads the LeaderboardScene.fxml when an event occurs.
     * @param event
     * @throws IOException
     */
    public void SwitchToLeaderboardScene(ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("fxmls/LeaderboardScene.fxml"));
        Parent root = loader.load();
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    /**
     * Method exits the game.
     *
     * @param event
     * @throws IOException
     */
    public void ExitGame(ActionEvent event) throws IOException{
        System.exit(0);
    }

}
