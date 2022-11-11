package Snakee;

import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.shape.Circle;
import javafx.stage.Stage;
import javafx.util.Duration;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class GameSceneController implements Initializable {
    private Stage stage;
    private Scene scene;
    private Parent root;

    @FXML
    Label playernameLabel;

    @FXML
    private Circle snakeHead;

    //x coordinates of snake
    private double x;

    //y coordinates of snake
    private double y;

    private boolean up, down, left, right;

    //Direction snake is moving at start
    //private SnakeDirection direction = SnakeDirection.RIGHT;

    //Number of times the snakes moved
    private int gameTicks;

    Timeline timeline = new Timeline(new KeyFrame(Duration.millis(10), e -> {
        moveSnakeHead(snakeHead);
        //boolean canChangeDirection = true;
        //System.out.println((xPos + snakeHead.getX()) + "-----" + (yPos + snakeHead.getY()));
        gameTicks++;
    }));

    @FXML
    public void keyPressed(KeyEvent event){
        if(event.getCode() == KeyCode.W){
            System.out.println("Pressed W");
            up = true;
            down = false;
            left = false;
            right = false;
            //snakeHead.setCenterY(y-=5);
        }

        if(event.getCode() == KeyCode.S){
            System.out.println("Pressed S");
            up = false;
            down = true;
            left = false;
            right = false;
            //snakeHead.setCenterY(y+=5);
        }

        if(event.getCode() == KeyCode.A){
            System.out.println("Pressed A");
            up = false;
            down = false;
            left = true;
            right = false;
            //snakeHead.setCenterX(x-=5);
        }

        if(event.getCode() == KeyCode.D){
            System.out.println("Pressed D");
            up = false;
            down = false;
            left = false;
            right = true;
            //snakeHead.setCenterX(x+=5);
        }
    }

    //Snake head is moved in the direction specified
    private void moveSnakeHead(Circle snakeHead) {
        if (up == true) {
            y = y - 2;
            snakeHead.setTranslateY(y);
        } else if (down == true) {
            y = y + 2;
            snakeHead.setTranslateY(y);
        } else if (left == true) {
            x = x - 2;
            snakeHead.setTranslateX(x);
        } else if (right == true) {
            x = x + 2;
            snakeHead.setTranslateX(x);
        }
    }

    //Displays playerName in scene
    public void playerName(String playerName) {
        playernameLabel.setText("PLAYER : " + playerName);
    }

    public void switchToStartScene(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("StartScene.fxml"));
        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    //Method called after the stage is loaded
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        timeline.setCycleCount(Animation.INDEFINITE);
        timeline.play();
    }
}
