package com.Snake.controller;

import com.Snake.model.SceneSwitch;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;

import java.io.IOException;

import static com.Snake.controller.GameSceneController.playerName;
import static com.Snake.controller.GameSceneController.playerScore;

/**
 * @Project COMP2013-Coursework
 * @Description EndSceneController Class
 * @Author Ainsley Lee
 */


public class EndSceneController {
    SceneSwitch m_SwitchSceneClass = new SceneSwitch();
    @FXML
    AnchorPane startAnchorPane;
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
     * Method called when startButton is pressed on the EndScene.
     * The SwitchScene class is then called to load the Start scene, passing the current AnchorPane and desired fxml.
     */
    public void SwitchToStartScene() throws IOException {
        m_SwitchSceneClass.SwitchScene(startAnchorPane, "fxml/StartScene.fxml");
    }

    /**
     * Method called when leaderboardButton is pressed on the EndScene.
     * The SwitchScene class is then called to load the Leaderboard scene, passing the current AnchorPane and desired fxml.
     */
    public void SwitchToLeaderboardScene() throws IOException {
        m_SwitchSceneClass.SwitchScene(startAnchorPane, "fxml/LeaderboardScene.fxml");
    }

    /**
     * Method called when exitButton is pressed on the EndScene.
     * Method exits game and closes game window.
     */
    public void ExitGame() {
        System.exit(0);
    }
}
