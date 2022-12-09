package com.Snake.controller;

import com.Snake.model.SceneSwitch;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;

import java.io.IOException;

import static com.Snake.controller.GameSceneController.playerName;
import static com.Snake.controller.GameSceneController.playerScore;


public class EndSceneController {
    SceneSwitch m_switchScene = new SceneSwitch();
    @FXML
    AnchorPane startAnchorPane;
    private String m_filename;
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
     * Method called when startButton is pressed on the StartScene.
     * m_filename variable set the deseried fxml file path.
     * SwitchScene class called passing the current AnchorPane and desired path via m_filename variable.
     */
    public void SwitchToStartScene() throws IOException {
        m_filename = "fxml/StartScene.fxml";
        m_switchScene.SwitchScene(startAnchorPane, m_filename);
    }

    /**
     * Method called when leaderboardButton is pressed on the StartScene.
     * m_filename variable set the deseried fxml file path.
     * SwitchScene class called passing the current AnchorPane and desired path via m_filename variable.
     */
    public void SwitchToLeaderboardScene() throws IOException {
        m_filename = "fxml/LeaderboardScene.fxml";
        m_switchScene.SwitchScene(startAnchorPane, m_filename);
    }

    /**
     * Method exits the game.
     */
    public void ExitGame() {
        System.exit(0);
    }

}
