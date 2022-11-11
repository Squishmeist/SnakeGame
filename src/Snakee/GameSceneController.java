package Snakee;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.shape.Circle;
import javafx.stage.Stage;

import javax.swing.text.html.ImageView;
import java.io.IOException;

public class GameSceneController {
    private Stage stage;
    private Scene scene;
    private Parent root;

    @FXML
    Label playernameLabel;
    @FXML
    private Circle SnakeHeadCircle;
    private double x;
    private double y;

    public void playerName(String playerName) {
        playernameLabel.setText("PLAYER : " + playerName);
    }

    @FXML
    public void keyPressed(KeyEvent event){
        if(event.getCode() == KeyCode.W){
            System.out.println("Pressed W");
            SnakeHeadCircle.setCenterY(y-=5);
        }

        if(event.getCode() == KeyCode.S){
            System.out.println("Pressed S");
            SnakeHeadCircle.setCenterY(y+=5);
        }

        if(event.getCode() == KeyCode.A){
            System.out.println("Pressed A");
            SnakeHeadCircle.setCenterX(x-=5);
        }

        if(event.getCode() == KeyCode.D){
            System.out.println("Pressed D");
            SnakeHeadCircle.setCenterX(x+=5);
        }
    }

    public void switchToStartScene(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("StartScene.fxml"));
        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }
}
