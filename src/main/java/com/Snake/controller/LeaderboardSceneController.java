package com.Snake.controller;

import com.Snake.model.Leaderboard;
import com.Snake.model.SceneSwitch;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;

import java.io.IOException;
import java.util.ArrayList;

public class LeaderboardSceneController {
    private ArrayList<String> m_playernameList;
    private ArrayList<Integer> m_playerscoreList;
    Leaderboard m_leaderboard = new Leaderboard();
    SceneSwitch m_sceneSwitch = new SceneSwitch();

    @FXML
    AnchorPane leaderboardAnchorPane;
    @FXML
    Label firstnameLabel;
    @FXML
    Label firstscoreLabel;
    @FXML
    Label secondnameLabel;
    @FXML
    Label secondscoreLabel;
    @FXML
    Label thirdnameLabel;
    @FXML
    Label thirdscoreLabel;
    @FXML
    Label fourthnameLabel;
    @FXML
    Label fourthscoreLabel;
    @FXML
    Label fifthnameLabel;
    @FXML
    Label fifthscoreLabel;


    public void SwitchToStartScene(ActionEvent event) throws IOException {
        String m_filename = "fxml/StartScene.fxml";
        m_sceneSwitch.SwitchScene(leaderboardAnchorPane, m_filename);
    }
}
