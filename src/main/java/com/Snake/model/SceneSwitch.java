package com.Snake.model;

import com.Snake.Main;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.Objects;

public class SceneSwitch {
    public void SwitchScene(AnchorPane currentAnchorPane, String filename) throws IOException {
        AnchorPane nextAnchorPane = FXMLLoader.load(Main.class.getResource(filename));
        currentAnchorPane.getChildren().removeAll();
        currentAnchorPane.getChildren().setAll(nextAnchorPane);
    }
}
