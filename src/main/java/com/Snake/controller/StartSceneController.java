package com.Snake.controller;

import com.Snake.model.SceneSwitch;
import javafx.fxml.FXML;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;

import java.io.IOException;
import java.util.Objects;

/**
 * @Project COMP2013-Coursework
 * @Description StartSceneController Class
 * @Author Ainsley Lee
 */

public class StartSceneController {
    private final String[] m_themeList = {"Snake", "Pacman", "SpaceInvader"};
    private final String[] m_levelList = {"Easy", "Medium", "Hard"};
    SceneSwitch m_SceneSwitchClass = new SceneSwitch();
    @FXML
    AnchorPane startAnchorPane;
    @FXML
    TextField nameTextField;
    @FXML
    ComboBox themeComboBox;
    @FXML
    ComboBox levelComboBox;

    /**
     * Method called when Start scene is initially loaded.
     * This method sets the theme combo box items to be those set in the m_themeList.
     * It also sets the level combo box items to be those set in the m_levelList.
     */
    @FXML
    private void initialize(){
        themeComboBox.getItems().addAll(m_themeList);
        levelComboBox.getItems().addAll(m_levelList);
    }

    /**
     * Method stores the players theme selection in the themeChoice variable which is then used to
     * compare with the three given options; Snake, Pacman and SpaceInvaders.
     * The themeNumber variable is then set depending on the players choice from the themeComboBox options.
     *
     * @return themeNumber variable depending on players choice.
     */
    public int ThemeSelected(){
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

    /**
     * Method stores the players level selection in the levelChoice variable which is then used to
     * compare with the three given options; Easy, Medium and Hard.
     * The levelNumber variable is then set depending on the players choice from the levelComboBox options.
     *
     * @return levelNumber variable depending on players choice
     */
    public int LevelSelected(){
        String levelChoice = (String) levelComboBox.getValue();
        int levelSpeed;

        if(Objects.equals(levelChoice, "Easy")){
            levelSpeed = 120;
        } else if (Objects.equals(levelChoice, "Medium")) {
            levelSpeed = 80;
        }
        else if (Objects.equals(levelChoice, "Hard")){
            levelSpeed = 40;
        }
        else{
            levelSpeed = 120;
        }
        return levelSpeed;
    }

    /**
     * Method called when startButton is pressed on the Start scene.
     * This method sets the playerName variable in the GameSceneController to be the text inputted by the player in the nameTextField.
     * It also sets the themeNumber and levelNumber variable to be the returned variables from the called methods.
     * The SwitchScene class is then called to load the Game scene, passing the current AnchorPane and desired fxml.
     */
    public void SwitchToGameScene() throws IOException {
        GameSceneController.playerName = nameTextField.getText();
        GameSceneController.themeNumber = ThemeSelected();
        GameSceneController.levelSpeed = LevelSelected();
        m_SceneSwitchClass.SwitchScene(startAnchorPane, "fxml/GameScene.fxml");
    }

    /**
     * Method called when leaderboardButton is pressed on the Start scene.
     * SwitchScene class called passing the current AnchorPane and desired fxml.
     */
    public void SwitchToLeaderboardScene() throws IOException {
        m_SceneSwitchClass.SwitchScene(startAnchorPane, "fxml/LeaderboardScene.fxml");
    }


}
