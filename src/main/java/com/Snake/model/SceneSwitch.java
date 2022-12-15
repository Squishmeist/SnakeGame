package com.Snake.model;

import com.Snake.Main;
import javafx.fxml.FXMLLoader;
import javafx.scene.layout.AnchorPane;

import java.io.IOException;
import java.util.Objects;

/**
 * @Project COMP2013-Coursework
 * @Description SceneSwitch Class
 * @Author Ainsley Lee
 */

public class SceneSwitch {
    /**
     * Method loads desired scene by storing the passed fxml Anchor Pane to nextAnchorPane.
     * The method then removes all the children from the current scene and adds all the children
     * from the new scene using the nextAnchorPane variable.
     *
     * @param currentAnchorPane variable passed from scene loading new scene
     * @param fxml variable storing path to desired fxml file
     * @throws IOException
     */
    public SceneSwitch(AnchorPane currentAnchorPane, String fxml) throws IOException {
        AnchorPane nextAnchorPane = FXMLLoader.load(Objects.requireNonNull(Main.class.getResource(fxml)));
        currentAnchorPane.getChildren().removeAll();
        currentAnchorPane.getChildren().setAll(nextAnchorPane);
    }
}
