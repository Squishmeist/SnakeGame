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
import javafx.animation.PauseTransition;
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
    public static String playerName;
    public static int playerScore;
    public static int gameTheme;
    public static int gameSpeed;

    private final Double m_snakeSize = 25.;
    private final Rectangle m_snakeHead = new Rectangle(250,250, m_snakeSize, m_snakeSize);
    private final List<Position> m_headPoints = new ArrayList<>();
    private final ArrayList<Rectangle> m_snakeBody = new ArrayList<>();
    private double m_snakeHeadX = m_snakeHead.getLayoutX();
    private double m_snakeHeadY = m_snakeHead.getLayoutY();
    boolean m_UP, m_DOWN, m_LEFT, m_RIGHT;
    private boolean m_foodExists, m_obstacleExists, m_nextLevelPossible;
    private int m_gameTicks, m_obstacleTicks, m_foodTicks, m_levelNumber, m_obstacleSpeed = 55, m_foodSpeed = 65;
    Snake m_SnakeClass;
    Food m_FoodClass;
    Obstacle m_ObstacleClass;
    Leaderboard m_LeaderboardClass = new Leaderboard();
    @FXML
    private AnchorPane gameAnchorPane;
    @FXML
    private Pane gamePane1;
    @FXML
    private Pane levelPane;
    @FXML
    Label levelLabel;
    @FXML
    Label scoreLabel;
    @FXML
    Label playernameLabel;
    @FXML
    Label playerscoreLabel;
    @FXML
    Button backButton;

    //Depending on users level choice runs game every 120, 80 or 40 milliseconds
    Timeline timeline = new Timeline(new KeyFrame(Duration.millis(gameSpeed), e ->{
        m_headPoints.add(new Position(m_snakeHead.getX() + m_snakeHeadX, m_snakeHead.getY() + m_snakeHeadY));
        MoveSnakeHead(m_snakeHead);
        for (int i = 1; i < m_snakeBody.size(); i++) {
            MoveSnakeTail(m_snakeBody.get(i),i);
        }
        m_gameTicks++;
        m_obstacleTicks++;
        m_foodTicks++;

        try {
            //check if snake object out of bounds, has hit itself or score is below 0
            GameEnd();
            //generates obstacle object, checks if it has been hit - decreasing score + snake length and regenerates a new obstacle object after 40 ticks (snake movements)
            GameObstacle();
        } catch (IOException ex) {
            throw new RuntimeException(ex);
        }

        //check if food exists, if not a food object is generated and if it does exist, checks object has been hit by the snake and increases score
        GameFood();

        //check if boolean is true (food has been eaten since last level) and call LevelPopup method
        if (m_nextLevelPossible){
            LevelPopup();
        }

        //updates players score
        PlayerScore(playerScore);
    }));

    /**
     * Method called when Game scene is loaded.
     * This method sets the starting values for various variables within the class.
     * Initialises Class objects by passing the necessary variables.
     * Calls the PlayerName and PlayerScore methods to set labels to display the players name and score.
     * Sets the background and backButton ID by calling the method in the Theme class.
     * Adds the created snake head rectangle object to the snake body array list.
     * Sets the snake head object fill to be the return image from the Theme class.
     * Starts the timeline and adds all children to the game scene Anchor Pane.
     *
     * @param url
     * @param resourceBundle
     */
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        m_headPoints.clear();
        m_snakeBody.clear();
        m_RIGHT = true;
        playerScore = 0;
        m_foodExists = false;
        m_obstacleExists = false;
        m_nextLevelPossible = true;
        m_gameTicks = 0;
        m_obstacleTicks = 0;

        m_SnakeClass = new Snake(gameAnchorPane, m_snakeSize, m_snakeHead, m_snakeBody);
        m_FoodClass = new Food(gameAnchorPane, m_snakeHead, m_snakeBody, m_headPoints, gameTheme);
        m_ObstacleClass = new Obstacle(gameAnchorPane, m_snakeHead, m_snakeBody, m_headPoints, gameTheme);

        PlayerName(playerName);
        PlayerScore(playerScore);

        gamePane1.setId(Theme.GenerateGameBackground(gameTheme));
        backButton.setId(Theme.GenerateBackButton(gameTheme));

        m_snakeBody.add(m_snakeHead);
        Image snakeHeadImage = Theme.GenerateSnakeHeadImage(gameTheme);
        m_snakeHead.setFill(new ImagePattern(snakeHeadImage));

        timeline.setCycleCount(Animation.INDEFINITE);
        timeline.play();
        gameAnchorPane.getChildren().add(m_snakeHead);
    }

    /**
     * Method called when a key is pressed by the player.
     * This method check if the key press is "WASD" and that the opposite key
     * is false. This ensures that the snake does not go in on itself.
     * The associated variable is then set to true and the rest false.
     * It also rotates the snake head accordingly in the direction
     * of the key press.
     *
     * @param event key pressed by the player
     */
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

    /**
     * Method called at the start of the timeline to move the snake head object.
     * This method checks the m_UP, m_DOWN, m_LEFT, m_RIGHT variables to see which is true.
     * Whichever boolean is true dictates the direction the snake head needs to move.
     * The snake head object is then moved accordingly in that direction.
     *
     * @param snakeHead rectangle object at the start of the snake body
     */
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

    /**
     * Method called at the start of the timeline to move the objects following the snake head object.
     * The snake tail objects x and y are then set, moving the tail to the heads previous movement based
     * on its position within the body.
     *
     * @param snakeTail rectangle object for the snake tail
     * @param tailNumber number associate with passed rectangle object
     */
    private void MoveSnakeTail(Rectangle snakeTail, int tailNumber){
        double y = m_headPoints.get(m_gameTicks - tailNumber + 1).getY() - snakeTail.getY();
        double x = m_headPoints.get(m_gameTicks - tailNumber + 1).getX() - snakeTail.getX();
        snakeTail.setTranslateX(x);
        snakeTail.setTranslateY(y);
    }

    /**
     * Method called from within the timeline to check if certain criteria is met to switch to the end scene.
     * The first if statement checks if the snake is out of bounds, has hit itself or the score is below 0.
     * If any of the called methods return true a second if statements checks the score is below 0 and sets it to 0.
     * The WriteLeaderboardFile method is called from the Leaderboard class to stores the players name and score to the
     * text file and the timeline is stopped. The SwitchScene method is then called from the SceneSwitch class to
     * change the scene to the end scene.
     *
     * @throws IOException
     */
    private void GameEnd() throws IOException {
        if (m_SnakeClass.OutOfBounds(m_snakeHeadX, m_snakeHeadY) || m_SnakeClass.BodyHit(m_headPoints, m_snakeBody) || playerScore < 0){
            if(playerScore < 0){
                playerScore = 0;
            }
            m_LeaderboardClass.WriteLeaderboardFile(playerName, playerScore);
            timeline.stop();
            new SceneSwitch(gameAnchorPane, "fxml/EndScene.fxml");
        }
    }

    /**
     * Method called from within the timeline.
     * This method first checks if the m_foodTicks variable matches the m_foodSpeed variable.
     * If this is the case the food ticks are set to 0, food exists is false and the food object is removed
     * from by calling the RemoveFood method from Food Class.
     * <p>
     * The next if statement checks and runs the GenerateFood method from the Food Class if the food object
     * does not exist i.e. m_foodExists is false.
     * <p>
     * If the food object does exist, from within the else state an if statement checks if the food is eaten by
     * calling the EatenFood method from within the Food Class. If this class returns true the players score
     * is increased, a sound is played by the Music class MusicPlayer method and the food object is removed
     * from the scene. The AddSnakeTail method is also called from the Snake Class to add a snake tail.
     */
    private void GameFood(){
        if (m_foodTicks == m_foodSpeed){
            m_foodTicks = 0;
            m_foodExists = false;
            m_FoodClass.RemoveFood();
        }
        if (!m_foodExists){
            m_foodExists = true;
            m_FoodClass.GenerateFood();
        }else{
            if (m_FoodClass.EatenFood()){
                m_foodExists = false;
                m_nextLevelPossible = true;
                playerScore += 521;
                new Music("src/main/resources/com/Snake/sounds/foodeaten-bleep.mp3", false);
                m_FoodClass.RemoveFood();
                m_SnakeClass.AddSnakeTail();
            }
        }
    }

    /**
     * Method called from within the timeline.
     * This method first checks if the m_obstacleTicks variable matches the m_obstacleSpeed variable.
     * If this is the case the obstacle ticks are set to 0, obstacle exists is false and the obstacle object is removed
     * from by calling the RemoveObstacle method from Obstacle Class.
     * <p>
     * The next if statement checks and runs the GenerateObstacle method from the Obstacle Class if the obstacle object
     * does not exist i.e. m_obstacleExists is false.
     * <p>
     * If the obstacle object does exist, from within the else state an if statement checks if the obstacle is hit by
     * calling the ObstacleHit method from within the Obstacle Class. If this class returns true the players score
     * is decreases, a sound is played by the Music class MusicPlayer method and the obstacle object is removed
     * from the scene. The RemoveSnakeTail method is also called from the Snake Class to remove a snake tail.
     *
     * @throws IOException
     */
    private void GameObstacle() throws IOException {
        if (m_obstacleTicks == m_obstacleSpeed){
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
                new Music("src/main/resources/com/Snake/sounds/obstaclehit-bleep.mp3", false);
                m_obstacleExists = false;
                m_SnakeClass.RemoveSnakeTail();
                m_ObstacleClass.RemoveObstacle();
                m_snakeBody.remove(snakeBodySize-1);
            }
        }
    }

    /**
     * Method called from within the timeline if m_nextLevelPossible is true.
     * <p>
     * This method checks the players score against 4 possible values, if any of these values
     * are met the timeline is paused, the levelPane is set to visible and the two labels
     * display the level completed and level score.
     * <p>
     * The food and obstacles speed are decreased (this in turn speeds up the respawn of objects),
     * the pause timeline is set to a duration of 2 seconds and turns the levelPane invisible afterwards.
     * <p>
     * The game timeline is then set to play after 2 seconds as well, meaning the player can continue
     * from the point they were before leveling up.
     */
    private void LevelPopup(){
        if(playerScore ==  1042 || playerScore == 2084 || playerScore == 3126 || playerScore == 4168){
            timeline.pause();
            m_nextLevelPossible = false;
            levelPane.toFront();
            levelPane.setVisible(true);
            m_levelNumber += 1;
            levelLabel.setText("LEVEL COMPLETE : " + m_levelNumber);
            scoreLabel.setText("LEVEL SCORE : " + playerScore);
            m_obstacleSpeed -= 10;
            m_foodSpeed -= 10;
            m_obstacleTicks = 0;
            m_foodTicks = 0;
            PauseTransition pause = new PauseTransition(Duration.seconds(2));
            pause.setOnFinished(f -> levelPane.setVisible(false));
            PauseTransition player = new PauseTransition(Duration.seconds(2));
            player.setOnFinished(f -> timeline.play());
            pause.play();
            player.play();
        }
    }

    /**
     * Method sets the playerscoreLabel text to be white if theme 2 or 3 is chosen as the background is darker.
     * This method also sets the playerscoreLabel to display the players score.
     *
     * @param playerScore variable containing players score
     */
    private void PlayerScore(int playerScore) {
        if(gameTheme == 2 || gameTheme == 3){
            playerscoreLabel.setTextFill(Color.WHITE);
        }
        playerscoreLabel.setText("SCORE : " + playerScore);
    }

    /**
     * Method sets the playernameLabel text to be white if theme 2 or 3 is chosen as the background is darker.
     * This method also sets the playernameLabel to display the players name.
     *
     * @param playerName variable containing player inputted name
     */
    private void PlayerName(String playerName) {
        if(gameTheme == 2 || gameTheme == 3){
            playernameLabel.setTextFill(Color.WHITE);
        }
        playernameLabel.setText("PLAYER : " + playerName);
    }

    /**
     * Method called when backButton is pressed on the Game scene.
     * Timeline is stopped and SwitchScene class called passing the current AnchorPane and desired fxml.
     */
    public void SwitchToStartScene() throws IOException {
        timeline.stop();
        new SceneSwitch(gameAnchorPane, "fxml/StartScene.fxml");
    }
}
