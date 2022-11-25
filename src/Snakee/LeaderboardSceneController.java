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

    public void ScoreArraySort(ArrayList<Integer> playerscoreList, ArrayList<String> playernameList){

        int score = 0, firstScore = 0, secondScore = 0, thirdScore = 0, fourthScore = 0, fifthScore = 0;
        String name = "", firstName = "", secondName = "", thirdName = "", fourthName = "", fifthName = "";

        for(int i = 0; i < playerscoreList.size(); i++){
            name = playernameList.get(i);
            score = playerscoreList.get(i);

            if(score > firstScore){
                fifthScore = fourthScore;
                fifthName = fourthName;
                fourthScore = thirdScore;
                fourthName = thirdName;
                thirdScore = secondScore;
                thirdName = secondName;
                secondScore = firstScore;
                secondName = firstName;
                firstScore = score;
                firstName = name;
            }if (score > secondScore && name != firstName){
                fifthScore = fourthScore;
                fifthName = fourthName;
                fourthScore = thirdScore;
                fourthName = thirdName;
                thirdScore = secondScore;
                thirdName = secondName;
                secondScore = score;
                secondName = name;
            }if (score > thirdScore && name != secondName && name != firstName){
                fifthScore = fourthScore;
                fifthName = fourthName;
                fourthScore = thirdScore;
                fourthName = thirdName;
                thirdScore = score;
                thirdName = name;
            }if (score > fourthScore && name != thirdName && name != secondName && name != firstName){
                fifthScore = fourthScore;
                fifthName = fourthName;
                fourthScore = score;
                fourthName = name;
            }if (score > fifthScore && name != fourthName && name != thirdName && name != secondName && name != firstName){
                fifthScore = score;
                fifthName = name;
            }
        }

        firstnameLabel.setText(firstName);
        firstscoreLabel.setText(String.valueOf(firstScore));

        secondnameLabel.setText(secondName);
        secondscoreLabel.setText(String.valueOf(secondScore));

        thirdnameLabel.setText(thirdName);
        thirdscoreLabel.setText(String.valueOf(thirdScore));

        fourthnameLabel.setText(fourthName);
        fourthscoreLabel.setText(String.valueOf(fourthScore));

        fifthnameLabel.setText(fifthName);
        fifthscoreLabel.setText(String.valueOf(fifthScore));

    }

    public void SwitchToStartScene(ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("StartScene.fxml"));
        Parent root = loader.load();
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }
}
