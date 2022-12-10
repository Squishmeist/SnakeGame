package com.Snake.controller;

import com.Snake.model.Food;
import com.Snake.model.Leaderboard;
import com.Snake.model.Music;
import com.Snake.model.Obstacle;
import com.Snake.model.Position;
import com.Snake.model.SceneSwitch;
import com.Snake.model.Snake;
import com.Snake.model.Theme;

import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Rectangle;
import javafx.util.Duration;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

/**
 * @Project COMP2013-Coursework
 * @Description GameSceneController Class
 * @Author Ainsley Lee
 */

public class GameSceneController implements Initializable{
    static String playerName;
    static int playerScore;
    public static int themeNumber;
    static int levelNumber;

    private final Double m_snakeSize = 25.;
    private final Rectangle m_snakeHead = new Rectangle(250,250, m_snakeSize, m_snakeSize);
    private final List<Position> m_headPoints = new ArrayList<>();
    private final ArrayList<Rectangle> m_snakeBody = new ArrayList<>();
    private double m_snakeHeadX = m_snakeHead.getLayoutX();
    private double m_snakeHeadY = m_snakeHead.getLayoutY();
    boolean m_UP, m_DOWN, m_LEFT, m_RIGHT;
    private boolean m_foodExists, m_obstacleExists;
    private int m_gameTicks, m_obstacleTicks;
    Snake m_SnakeClass;
    Food m_FoodClass;
    Obstacle m_ObstacleClass;
    Leaderboard m_LeaderboardClass = new Leaderboard();
    Music m_MusicClass = new Music();
    SceneSwitch m_SceneSwitchClass = new SceneSwitch();
    @FXML
    private AnchorPane gameAnchorPane;
    @FXML
    private Pane gamePane1;
    @FXML
    Label playernameLabel;
    @FXML
    Label playerscoreLabel;
    @FXML
    Button backButton;

    //Depending on users level choice runs game every 120, 80 or 40 milliseconds
    Timeline timeline = new Timeline(new KeyFrame(Duration.millis(levelNumber),e ->{
        m_headPoints.add(new Position(m_snakeHead.getX() + m_snakeHeadX, m_snakeHead.getY() + m_snakeHeadY));
        MoveSnakeHead(m_snakeHead);
        for (int i = 1; i < m_snakeBody.size(); i++) {
            MoveSnakeTail(m_snakeBody.get(i),i);
        }
        m_gameTicks++;
        m_obstacleTicks++;

        //if snake is out of bounds or hits itself calls GameEnd method
        try {
            GameEnd();
            //removes obstacle after 40 ticks and generates a new one or decreases playerscore if obstacle hit
            GameObstacle();
        } catch (IOException ex) {
            throw new RuntimeException(ex);
        }

        //if food does not exist generate food
        //if food does exist checks if its eaten
        GameFood();
        PlayerScore(playerScore);

        /*if(playerScore ==  1042 && levelNumber == 120 || playerScore == 2084 && levelNumber == 80 || playerScore == 3117 && levelNumber == 40){
            try {
                levelPopup();
            } catch (IOException ex) {
                throw new RuntimeException(ex);
            }
        }
        */
    }));

    //Called after stage loaded
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        m_headPoints.clear();
        m_snakeBody.clear();
        m_RIGHT = true;
        playerScore = 0;
        m_foodExists = false;
        m_obstacleExists = false;
        m_gameTicks = 0;
        m_obstacleTicks = 0;

        m_SnakeClass = new Snake(gameAnchorPane, m_snakeSize, m_snakeHead, m_snakeBody);
        m_FoodClass = new Food(gameAnchorPane, m_snakeHead, m_snakeBody, m_headPoints, themeNumber);
        m_ObstacleClass = new Obstacle(gameAnchorPane, m_snakeHead, m_snakeBody, m_headPoints, themeNumber);

        PlayerName(playerName);
        PlayerScore(playerScore);

        gamePane1.setId(Theme.GenerateGameBackground(themeNumber));
        backButton.setId(Theme.GenerateBackButton(themeNumber));

        m_snakeBody.add(m_snakeHead);
        Image snakeHeadImage = Theme.GenerateSnakeHeadImage(themeNumber);
        m_snakeHead.setFill(new ImagePattern(snakeHeadImage));

        timeline.setCycleCount(Animation.INDEFINITE);
        timeline.play();
        gameAnchorPane.getChildren().add(m_snakeHead);
    }
    @FXML
    public void KeyPressed(KeyEvent event){
        if(event.getCode() == KeyCode.W && !m_DOWN){
            m_UP = true;
            m_DOWN = false;
            m_LEFT = false;
            m_RIGHT = false;
            m_snakeHead.setRotate(-90);
        }
        if(event.getCode() == KeyCode.S && !m_UP){
            m_UP = false;
            m_DOWN = true;
            m_LEFT = false;
            m_RIGHT = false;
            m_snakeHead.setRotate(90);
        }
        if(event.getCode() == KeyCode.A && !m_RIGHT){
            m_UP = false;
            m_DOWN = false;
            m_LEFT = true;
            m_RIGHT = false;
            m_snakeHead.setRotate(-180);
        }
        if(event.getCode() == KeyCode.D && !m_LEFT){
            m_UP = false;
            m_DOWN = false;
            m_LEFT = false;
            m_RIGHT = true;
            m_snakeHead.setRotate(0);
        }
    }
    //Called to moveSnakeHead
    private void MoveSnakeHead(Rectangle snakeHead){
        int speed_XY = 25;
        if(m_UP){
            m_snakeHeadY -= speed_XY;
            snakeHead.setTranslateY(m_snakeHeadY);
        }
        if(m_DOWN){
            m_snakeHeadY += speed_XY;
            snakeHead.setTranslateY(m_snakeHeadY);
        }
        if(m_LEFT){
            m_snakeHeadX -= speed_XY;
            snakeHead.setTranslateX(m_snakeHeadX);
        }
        if(m_RIGHT){
            m_snakeHeadX += speed_XY;
            snakeHead.setTranslateX(m_snakeHeadX);
        }
    }
    private void MoveSnakeTail(Rectangle snakeTail, int tailNumber){
        double y = m_headPoints.get(m_gameTicks - tailNumber + 1).getY() - snakeTail.getY();
        double x = m_headPoints.get(m_gameTicks - tailNumber + 1).getX() - snakeTail.getX();
        snakeTail.setTranslateX(x);
        snakeTail.setTranslateY(y);
    }
    private void GameEnd() throws IOException {
        if (m_SnakeClass.OutOfBounds(m_snakeHeadX, m_snakeHeadY) || m_SnakeClass.BodyHit(m_headPoints, m_snakeBody) || playerScore < 0){
            System.out.println("OUT OF BOUNDS or BODY HIT or SCORE BELOW 0");
            if(playerScore < 0){
                playerScore = 0;
            }
            m_LeaderboardClass.WriteLeaderboardFile(playerName, playerScore);
            timeline.stop();
            m_SceneSwitchClass.SwitchScene(gameAnchorPane, "fxml/EndScene.fxml");
        }
    }
    private void GameFood(){
        if (!m_foodExists){
            m_foodExists = true;
            m_FoodClass.GenerateFood();
        }else{
            if (m_FoodClass.EatenFood()){
                m_foodExists = false;
                playerScore += 521;
                m_MusicClass.MusicPlayer("src/main/resources/com/Snake/sounds/foodeaten-bleep.mp3");
                m_FoodClass.RemoveFood();
                m_SnakeClass.AddSnakeTail();
            }
        }
    }
    private void GameObstacle() throws IOException {
        if (m_obstacleTicks == 40){
            m_obstacleTicks = 0;
            m_obstacleExists = false;
            m_ObstacleClass.RemoveObstacle();
        }
        if (!m_obstacleExists){
            m_obstacleExists = true;
            m_ObstacleClass.GenerateObstacle();
        }else {
            if (m_ObstacleClass.HitObstacle()){
                int snakeBodySize = m_snakeBody.size();
                playerScore -= 521;
                GameEnd();
                m_MusicClass.MusicPlayer("src/main/resources/com/Snake/sounds/obstaclehit-bleep.mp3");
                m_obstacleExists = false;
                m_SnakeClass.RemoveSnakeTail();
                m_ObstacleClass.RemoveObstacle();
                m_snakeBody.remove(snakeBodySize-1);
            }
        }
    }

    //Displays playerScore in scene
    private void PlayerScore(int playerScore) {
        if(themeNumber == 2 || themeNumber == 3){
            playerscoreLabel.setTextFill(Color.WHITE);
        }
        playerscoreLabel.setText("SCORE : " + playerScore);
    }
    //Displays playerName in scene
    private void PlayerName(String playerName) {
        if(themeNumber == 2 || themeNumber == 3){
            playernameLabel.setTextFill(Color.WHITE);
        }
        playernameLabel.setText("PLAYER : " + playerName);
    }
    private void levelPopup() throws IOException{
        timeline.stop();
        String m_filename = "fxml/LevelScene.fxml";
        m_SceneSwitchClass.SwitchScene(gameAnchorPane, m_filename);
    }

    //Switch to StartScene
    public void SwitchToStartScene() throws IOException {
        timeline.stop();
        m_SceneSwitchClass.SwitchScene(gameAnchorPane, "fxml/StartScene.fxml");
    }

}
