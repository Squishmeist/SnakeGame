package Snakee;

import java.io.IOException;
import java.util.Objects;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class StartSceneController {
    private final String[] themeList = {"Snake", "Pacman", "SpaceInvader"};
    @FXML
    TextField nameTextField;
    @FXML
    ComboBox themeComboBox;

    @FXML
    private void initialize(){
        themeComboBox.getItems().addAll(themeList);
    }
    public int PlayerThemeChoice(){
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
    public void SwitchToGameScene(ActionEvent event) throws IOException {
        GameSceneController.playerName = nameTextField.getText();
        GameSceneController.themeNumber = PlayerThemeChoice();
        FXMLLoader loader = new FXMLLoader(getClass().getResource("GameScene.fxml"));
        Parent root = loader.load();
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }
    public void SwitchToLeaderboardScene(ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("LeaderboardScene.fxml"));
        Parent root = loader.load();
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }
}
