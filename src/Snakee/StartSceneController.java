package Snakee;

import java.io.IOException;

import javafx.beans.Observable;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class StartSceneController {

    ObservableList <String> themeList = FXCollections.observableArrayList("One", "Two", "Three");

    @FXML
    TextField nameTextField;
    @FXML
    ComboBox themeComboBox;

    @FXML
    private void initialize(){
        themeComboBox.setItems(themeList);
    }


    public void SwitchToGameScene(ActionEvent event) throws IOException {
        String playername = nameTextField.getText();

        FXMLLoader loader = new FXMLLoader(getClass().getResource("GameScene.fxml"));
        Parent root = loader.load();

        GameSceneController gameSceneController = loader.getController();
        gameSceneController.PlayerName(playername);

        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }
}
