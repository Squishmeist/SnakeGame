package Snakee;

import java.io.IOException;
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

    private final String[] themeList = {"One", "Two", "Three"};

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
        int themeNumber = 0;

        if(themeChoice == "One"){
            themeNumber = 1;
        } else if (themeChoice == "Two") {
            themeNumber = 2;
        }
        else if (themeChoice == "Three"){
            themeNumber = 3;
        }
        else{
            themeNumber = 0;
        }

        return themeNumber;
    }

    public void SwitchToGameScene(ActionEvent event) throws IOException {
        String playerName = nameTextField.getText();

        FXMLLoader loader = new FXMLLoader(getClass().getResource("GameScene.fxml"));
        Parent root = loader.load();

        GameSceneController.themeNumber = PlayerThemeChoice();

        GameSceneController gameSceneController = loader.getController();
        gameSceneController.PlayerName(playerName);

        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }
}
