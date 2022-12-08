package Snakee;

import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
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
import javafx.stage.Stage;
import javafx.util.Duration;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.ResourceBundle;

public class GameSceneController implements Initializable{
    private Stage stage;
    private Scene scene;

    static String playerName;
    static int playerScore, themeNumber, levelNumber;
    private final Double snakeSize = 25.;
    private final Rectangle snakeHead = new Rectangle(250,250,snakeSize,snakeSize);
    //List of all position of the snake head
    private final List<Position> headPoints = new ArrayList<>();
    //List of all snake body parts
    private final ArrayList<Rectangle> snakeBody = new ArrayList<>();

    //Class specific variables
    private double m_snakeHeadX = snakeHead.getLayoutX();
    private double m_snakeHeadY = snakeHead.getLayoutY();
    boolean m_UP, m_DOWN, m_LEFT, m_RIGHT;
    private boolean m_foodExists, m_obstacleExists;
    Snake m_snake;
    Food m_food;
    Obstacle m_obstacle;
    Leaderboard m_leaderboard = new Leaderboard();
    Music m_music = new Music();
    SceneSwitch m_sceneSwitch = new SceneSwitch();
    //Number of times snakes moved
    private int m_gameTicks, m_obstacleTicks;

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
        headPoints.add(new Position(snakeHead.getX() + m_snakeHeadX, snakeHead.getY() + m_snakeHeadY));
        MoveSnakeHead(snakeHead);
        for (int i = 1; i < snakeBody.size(); i++) {
            MoveSnakeTail(snakeBody.get(i),i);
        }
        m_gameTicks++;
        m_obstacleTicks++;

        //if snake is out of bounds or hits itself run switchToEndScene method
        if(m_snake.OutOfBounds(m_snakeHeadX, m_snakeHeadY) || m_snake.BodyHit(headPoints, snakeBody) || playerScore < 0){
            System.out.println("OUT OF BOUNDS or BODY HIT or SCORE BELOW 0");
            try {
                m_gameTicks = -1;
                m_leaderboard.WriteLeaderboardFile(playerName, playerScore);
                SwitchToEndScene();
            } catch (IOException ex) {
                throw new RuntimeException(ex);
            }
        }

        //if food does not exist generate food
        if (!m_foodExists){
            m_foodExists = true;
            m_food.GenerateFood();
        }
        //if food does exist check if its eaten
        else {
            if(m_food.EatenFood()) {
                m_foodExists = false;
                playerScore += 521;
                m_music.MusicPlayer("src/Snakee/resources/sounds/foodeaten-bleep.mp3");
                m_food.RemoveFood();
                m_snake.AddSnakeTail();
            }
        }

        //removes obstacle and generates a new one
        if (m_obstacleTicks == 40){
            m_obstacleTicks = 0;
            m_obstacleExists = false;
            m_obstacle.RemoveObstacle();
        }

        if(!m_obstacleExists){
            m_obstacleExists = true;
            m_obstacle.GenerateObstacle();
        }
        else {
            if(m_obstacle.HitObstacle()){
                int snakebodySize = snakeBody.size();
                playerScore -= 521;
                if(playerScore < 0){
                    try {
                        SwitchToEndScene();
                    } catch (IOException ex) {
                        throw new RuntimeException(ex);
                    }
                }
                m_music.MusicPlayer("src/Snakee/resources/sounds/obstaclehit-bleep.mp3");
                m_obstacleExists = false;
                m_snake.RemoveSnakeTail();
                m_obstacle.RemoveObstacle();
                snakeBody.remove(snakebodySize-1);
            }
        }

        if(playerScore ==  1042 && levelNumber == 120 || playerScore == 2084 && levelNumber == 80 || playerScore == 3117 && levelNumber == 40){
            try {
                levelPopup();
            } catch (IOException ex) {
                throw new RuntimeException(ex);
            }
        }

        PlayerScore(playerScore);
    }));

    //Called after stage loaded
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        headPoints.clear();
        snakeBody.clear();
        m_RIGHT = true;
        playerScore = 0;
        m_foodExists = false;
        m_obstacleExists = false;
        m_gameTicks = 0;
        m_obstacleTicks = 0;

        m_snake = new Snake(gameAnchorPane, snakeBody, snakeHead, snakeSize);
        m_food = new Food(gameAnchorPane, snakeHead, snakeBody, headPoints, themeNumber);
        m_obstacle = new Obstacle(gameAnchorPane, snakeHead, snakeBody, headPoints, themeNumber);

        PlayerName(playerName);
        PlayerScore(playerScore);

        gamePane1.setId(Theme.GenerateGameBackground(themeNumber));
        backButton.setId(Theme.GenerateBackButton(themeNumber));

        snakeBody.add(snakeHead);
        Image snakeHeadImage = Theme.GenerateSnakeHeadImage(themeNumber);
        snakeHead.setFill(new ImagePattern(snakeHeadImage));

        timeline.setCycleCount(Animation.INDEFINITE);
        timeline.play();
        gameAnchorPane.getChildren().add(snakeHead);
    }
    @FXML
    public void KeyPressed(KeyEvent event){
        if(event.getCode() == KeyCode.W && !m_DOWN){
            m_UP = true;
            m_DOWN = false;
            m_LEFT = false;
            m_RIGHT = false;

            snakeHead.setRotate(-90);
        }

        if(event.getCode() == KeyCode.S && !m_UP){
            m_UP = false;
            m_DOWN = true;
            m_LEFT = false;
            m_RIGHT = false;

            snakeHead.setRotate(90);
        }

        if(event.getCode() == KeyCode.A && !m_RIGHT){
            m_UP = false;
            m_DOWN = false;
            m_LEFT = true;
            m_RIGHT = false;

            snakeHead.setRotate(-180);
        }

        if(event.getCode() == KeyCode.D && !m_LEFT){
            m_UP = false;
            m_DOWN = false;
            m_LEFT = false;
            m_RIGHT = true;

            snakeHead.setRotate(0);
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
        double y = headPoints.get(m_gameTicks - tailNumber + 1).getY() - snakeTail.getY();
        double x = headPoints.get(m_gameTicks - tailNumber + 1).getX() - snakeTail.getX();
        snakeTail.setTranslateX(x);
        snakeTail.setTranslateY(y);
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
        Parent root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("fxmls/LevelScene.fxml")));
        stage = (Stage) snakeHead.getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }
    //Switch to EndScene
    public void SwitchToEndScene() throws IOException {
        timeline.stop();
        Parent root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("fxmls/EndScene.fxml")));
        stage = (Stage) snakeHead.getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }
    //Switch to StartScene
    public void SwitchToStartScene(ActionEvent event) throws IOException {
        timeline.stop();
        String m_filename = "fxmls/StartScene.fxml";
        m_sceneSwitch.SwitchScene(event, m_filename);
    }

}
