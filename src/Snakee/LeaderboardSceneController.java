package Snakee;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.ArrayList;

public class LeaderboardSceneController {

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

    public void initialize() {
        Leaderboard.ReadLeaderboardFile();

        ArrayList<String> playernameList = Leaderboard.playernameList;
        ArrayList<Integer> playerscoreList = Leaderboard.playerscoreList;

        ScoreArraySort(playerscoreList, playernameList);
    }

    public void ScoreArraySort(ArrayList<Integer> playerscoreList, ArrayList<String> playernameList) {

        for (int i = 0; i < playerscoreList.size() - 1; i++) {
            for (int j = 0; j < playerscoreList.size() - i - 1; j++)
                if (playerscoreList.get(j) < playerscoreList.get(j + 1)) {
                    int tempScore = playerscoreList.get(j);
                    playerscoreList.set(j, playerscoreList.get(j + 1));
                    playerscoreList.set(j + 1, tempScore);

                    String tempName = playernameList.get(j);
                    playernameList.set(j, playernameList.get(j+1));
                    playernameList.set(j + 1, tempName);
                }
        }

        firstnameLabel.setText(playernameList.get(0));
        firstscoreLabel.setText(String.valueOf(playerscoreList.get(0)));

        secondnameLabel.setText(playernameList.get(1));
        secondscoreLabel.setText(String.valueOf(playerscoreList.get(1)));

        thirdnameLabel.setText(playernameList.get(2));
        thirdscoreLabel.setText(String.valueOf(playerscoreList.get(2)));

        fourthnameLabel.setText(playernameList.get(3));
        fourthscoreLabel.setText(String.valueOf(playerscoreList.get(3)));

        fifthnameLabel.setText(playernameList.get(4));
        fifthscoreLabel.setText(String.valueOf(playerscoreList.get(4)));

    }

    public void SwitchToStartScene(ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("fxmls/StartScene.fxml"));
        Parent root = loader.load();
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }
}
