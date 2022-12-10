package com.Snake.controller;

import com.Snake.model.SceneSwitch;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;

import java.io.IOException;

import static com.Snake.controller.GameSceneController.levelNumber;
import static com.Snake.controller.GameSceneController.playerScore;

/**
 * @Project COMP2013-Coursework
 * @Description LevelSceneController Class
 * @Author Ainsley Lee
 */

public class LevelSceneController {
    SceneSwitch m_SceneSwitchClass = new SceneSwitch();
    @FXML
    AnchorPane levelAnchorPane;
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

    /**
     * Method called when nextButton is pressed on the Level scene.
     * The SwitchScene class is then called to load the Game scene, passing the current AnchorPane and desired fxml.
     */
    public void SwitchToGameScene() throws IOException {
        m_SceneSwitchClass.SwitchScene(levelAnchorPane, "fxml/GameScene.fxml");
    }
}
