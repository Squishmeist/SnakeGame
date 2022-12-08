package com.Snake.controller;

import java.io.IOException;
import java.util.Objects;

import com.Snake.Main;
import com.Snake.model.SceneSwitch;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;

public class StartSceneController {

    @FXML
    AnchorPane startAnchorPane;
    private final String[] m_themeList = {"Snake", "Pacman", "SpaceInvader"};
    private final String[] m_levelList = {"Easy", "Medium", "Hard"};
    private String m_filename;
    SceneSwitch m_sceneSwitch = new SceneSwitch();
    @FXML
    TextField nameTextField;
    @FXML
    ComboBox themeComboBox;
    @FXML
    ComboBox levelComboBox;

    @FXML
    private void initialize(){
        themeComboBox.getItems().addAll(m_themeList);
        levelComboBox.getItems().addAll(m_levelList);
    }

    public void SwitchToLeaderboardScene(ActionEvent event) throws IOException {
        String m_filename = "fxml/LeaderboardScene.fxml";
        m_sceneSwitch.SwitchScene(startAnchorPane, m_filename);
    }


}
