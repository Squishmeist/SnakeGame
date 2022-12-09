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

    /**
     * Method called when LeaderboardScene.fxml file is loaded.
     * Sets class arraylist variables to be the Leaderboard classes arrays.
     * Calls ScoreArraySort function to sort and set labels for top five player score and associated names.
     */
    public void initialize() {
        m_leaderboard.ReadLeaderboardFile();
        m_playernameList = m_leaderboard.playernameList;
        m_playerscoreList = m_leaderboard.playerscoreList;
        ScoreArraySort();
    }

    /**
     * Method
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
     * m_filename variable set the deseried fxml file path.
     * SwitchScene class called passing the current AnchorPane and desired path via m_filename variable.
     */
    public void SwitchToStartScene() throws IOException {
        String m_filename = "fxml/StartScene.fxml";
        m_sceneSwitch.SwitchScene(leaderboardAnchorPane, m_filename);
    }
}
