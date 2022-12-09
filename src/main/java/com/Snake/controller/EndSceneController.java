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
    @FXML
    AnchorPane startAnchorPane;
    SceneSwitch m_switchScene = new SceneSwitch();
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
     * Method loads the StartScene.fxml when an event occurs.
     */
    public void SwitchToStartScene(ActionEvent event) throws IOException {
        m_filename = "fxml/StartScene.fxml";
        m_switchScene.SwitchScene(startAnchorPane, m_filename);
    }

    /**
     * Method loads the LeaderboardScene.fxml when an event occurs.
     */
    public void SwitchToLeaderboardScene(ActionEvent event) throws IOException {
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
