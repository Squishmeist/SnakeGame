package com.Snake.controller;

import com.Snake.model.Leaderboard;
import com.Snake.model.SceneSwitch;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;

import java.io.IOException;
import java.util.ArrayList;

/**
 * @Project COMP2013-Coursework
 * @Description LeaderboardSceneController Class
 * @Author Ainsley Lee
 */

public class LeaderboardSceneController {
    private ArrayList<String> m_playernameList;
    private ArrayList<Integer> m_playerscoreList;
    Leaderboard m_LeaderboardClass = new Leaderboard();
    SceneSwitch m_SceneSwitchClass = new SceneSwitch();
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

    /**
     * Method called when LeaderboardScene.fxml file is loaded.
     * The ReadLeaderboardFile method is called from the Leaderboard class and class array lists set to be
     * the array lists generated in the Leaderboard class.
     * The ScoreArraySort method is then called to sort and set the labels for top five player scores and associated names.
     */
    public void initialize() {
        m_LeaderboardClass.ReadLeaderboardFile();
        m_playernameList = m_LeaderboardClass.playernameList;
        m_playerscoreList = m_LeaderboardClass.playerscoreList;
        ScoreArraySort();
    }

    /**
     * Method performs a bubble sort, working through each element and swapping it with the next element if they are in the wrong order.
     * The sorted lists are then used to set the labels for the top 5 player scores and names.
     */
    public void ScoreArraySort() {
        for (int i = 0; i < m_playerscoreList.size() - 1; i++) {
            for (int j = 0; j < m_playerscoreList.size() - i - 1; j++)
                if (m_playerscoreList.get(j) < m_playerscoreList.get(j + 1)) {
                    int tempScore = m_playerscoreList.get(j);
                    m_playerscoreList.set(j, m_playerscoreList.get(j + 1));
                    m_playerscoreList.set(j + 1, tempScore);

                    String tempName = m_playernameList.get(j);
                    m_playernameList.set(j, m_playernameList.get(j+1));
                    m_playernameList.set(j + 1, tempName);
                }
        }
        firstnameLabel.setText(m_playernameList.get(0));
        secondnameLabel.setText(m_playernameList.get(1));
        thirdnameLabel.setText(m_playernameList.get(2));
        fourthnameLabel.setText(m_playernameList.get(3));
        fifthnameLabel.setText(m_playernameList.get(4));

        firstscoreLabel.setText(String.valueOf(m_playerscoreList.get(0)));
        secondscoreLabel.setText(String.valueOf(m_playerscoreList.get(1)));
        thirdscoreLabel.setText(String.valueOf(m_playerscoreList.get(2)));
        fourthscoreLabel.setText(String.valueOf(m_playerscoreList.get(3)));
        fifthscoreLabel.setText(String.valueOf(m_playerscoreList.get(4)));
    }


    /**
     * Method called when backButton is pressed on the Leaderboard scene.
     * The SwitchScene class is then called to load the Start scene, passing the current AnchorPane and desired fxml.
     */
    public void SwitchToStartScene() throws IOException {
        m_SceneSwitchClass.SwitchScene(leaderboardAnchorPane, "fxml/StartScene.fxml");
    }
}
