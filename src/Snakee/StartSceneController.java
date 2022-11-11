package Snakee;

import java.io.IOException;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class StartSceneController {

    @FXML
    TextField nameTextField;

    private Stage stage;
    private Scene scene;
    private Parent root;

    public void switchToGameScene(ActionEvent event) throws IOException {

        String playername = nameTextField.getText();

        FXMLLoader loader = new FXMLLoader(getClass().getResource("GameScene.fxml"));
        root = loader.load();

        GameSceneController gameSceneController = loader.getController();
        gameSceneController.displayName(playername);

        //root = FXMLLoader.load(getClass().getResource("GameScene.fxml"));
        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();


    }
}