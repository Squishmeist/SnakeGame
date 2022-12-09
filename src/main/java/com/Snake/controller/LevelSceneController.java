package com.Snake.controller;

import com.Snake.model.SceneSwitch;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import java.io.IOException;

import static com.Snake.controller.GameSceneController.levelNumber;
import static com.Snake.controller.GameSceneController.playerScore;


public class LevelSceneController {
    SceneSwitch m_sceneSwitch = new SceneSwitch();

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

    public void SwitchToGameScene(ActionEvent event) throws IOException {
        String m_filename = "fxml/GameScene.fxml";
        m_sceneSwitch.SwitchScene(levelAnchorPane, m_filename);
    }
}
