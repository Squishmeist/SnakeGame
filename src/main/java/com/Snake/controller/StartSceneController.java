package com.Snake.controller;

import java.io.IOException;
import java.util.Objects;

import com.Snake.model.SceneSwitch;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
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

    public int ThemeChoice(){
        String themeChoice = (String) themeComboBox.getValue();
        int themeNumber;
        if(Objects.equals(themeChoice, "Snake")){
            themeNumber = 1;
        } else if (Objects.equals(themeChoice, "Pacman")) {
            themeNumber = 2;
        }
        else if (Objects.equals(themeChoice, "SpaceInvader")){
            themeNumber = 3;
        }
        else{
            themeNumber = 0;
        }
        return themeNumber;
    }
    public int LevelChoice(){
        String levelChoice = (String) levelComboBox.getValue();
        int levelNumber;

        if(Objects.equals(levelChoice, "Easy")){
            levelNumber = 120;
        } else if (Objects.equals(levelChoice, "Medium")) {
            levelNumber = 80;
        }
        else if (Objects.equals(levelChoice, "Hard")){
            levelNumber = 40;
        }
        else{
            levelNumber = 120;
        }
        return levelNumber;
    }

    public void SwitchToGameScene(ActionEvent event) throws IOException {
        GameSceneController.playerName = nameTextField.getText();
        GameSceneController.themeNumber = ThemeChoice();
        GameSceneController.levelNumber = LevelChoice();
        m_filename = "fxml/GameScene.fxml";
        m_sceneSwitch.SwitchScene(startAnchorPane, m_filename);
    }

    public void SwitchToLeaderboardScene(ActionEvent event) throws IOException {
        String m_filename = "fxml/LeaderboardScene.fxml";
        m_sceneSwitch.SwitchScene(startAnchorPane, m_filename);
    }


}
