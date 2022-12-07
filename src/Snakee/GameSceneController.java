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
    static int playerScore;
    static String playerName;
    static int themeNumber;
    static int levelNumber;
    private final Double snakeSize = 25.;
    private final Rectangle snakeHead = new Rectangle(250,250,snakeSize,snakeSize);
    double snakeHeadX = snakeHead.getLayoutX();
    double snakeHeadY = snakeHead.getLayoutY();
    //List of all position of the snake head
    private final List<Position> headPoints = new ArrayList<>();
    //List of all snake body parts
    private final ArrayList<Rectangle> snakeBody = new ArrayList<>();
    boolean UP, DOWN, LEFT, RIGHT;
    private boolean foodExists;
    Rectangle foodObject = new Rectangle();
    private boolean obstacleExists;
    Rectangle obstacleObject = new Rectangle();
    String filename;

    //Number of times snakes moved
    private int gameTicks;
    private int obstacleTicks;
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
        headPoints.add(new Position(snakeHead.getX() + snakeHeadX, snakeHead.getY() + snakeHeadY));
        MoveSnakeHead(snakeHead);
        for (int i = 1; i < snakeBody.size(); i++) {
            MoveSnakeTail(snakeBody.get(i),i);
        }
        gameTicks++;
        obstacleTicks++;

        //if snake is out of bounds or hits itself run switchToEndScene method
        if(Snake.OutOfBounds(snakeHeadX, snakeHeadY) || Snake.BodyHit(headPoints, snakeBody) || playerScore < 0){
            System.out.println("OUT OF BOUNDS or BODY HIT or SCORE BELOW 0");
            try {
                gameTicks = -1;
                Leaderboard.WriteLeaderboardFile(playerName, playerScore);
                SwitchToEndScene();
            } catch (IOException ex) {
                throw new RuntimeException(ex);
            }
        }

        //if food does not exist generate food
        if (!foodExists){
            foodObject = Food.GenerateFood(foodObject, snakeBody, headPoints, themeNumber);
            foodExists = true;
            gameAnchorPane.getChildren().add(foodObject);
        }
        //if food does exist check if its eaten
        else {
            if(Food.EatenFood(snakeHead, foodObject)) {
                foodExists = false;
                playerScore += 521;
                filename = "src/Snakee/resources/sounds/foodeaten-bleep.mp3";
                Music.MusicPlayer(filename);
                gameAnchorPane.getChildren().remove(foodObject);
                Rectangle snakeTail = Snake.AddSnakeTail(snakeBody, snakeHead, snakeSize, snakeHeadX, snakeHeadY);
                gameAnchorPane.getChildren().add(snakeTail);
            }
        }

        //removes obstacle and generates a new one
        if (obstacleTicks == 40){
            obstacleTicks = 0;
            obstacleExists = false;
            gameAnchorPane.getChildren().remove(obstacleObject);
        }

        if(!obstacleExists){
            obstacleObject = Obstacle.GenerateObstacle(obstacleObject, snakeBody, headPoints, themeNumber);
            obstacleExists = true;
            gameAnchorPane.getChildren().add(obstacleObject);
        }
        else {
            if(Obstacle.HitObstacle(snakeHead, obstacleObject)){
                int snakebodySize = snakeBody.size();
                playerScore -= 521;
                if(playerScore < 0){
                    try {
                        SwitchToEndScene();
                    } catch (IOException ex) {
                        throw new RuntimeException(ex);
                    }
                }
                filename = "src/Snakee/resources/sounds/obstaclehit-bleep.mp3";
                Music.MusicPlayer(filename);
                obstacleExists = false;
                gameAnchorPane.getChildren().remove(Snake.RemoveSnakeTail(snakeBody, snakebodySize));
                gameAnchorPane.getChildren().remove(obstacleObject);
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
        RIGHT = true;
        playerScore = 0;
        foodExists = false;
        obstacleExists = false;
        gameTicks = 0;
        obstacleTicks = 0;

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
        if(event.getCode() == KeyCode.W && !DOWN){
            UP = true;
            DOWN = false;
            LEFT = false;
            RIGHT = false;

            snakeHead.setRotate(-90);
        }

        if(event.getCode() == KeyCode.S && !UP){
            UP = false;
            DOWN = true;
            LEFT = false;
            RIGHT = false;

            snakeHead.setRotate(90);
        }

        if(event.getCode() == KeyCode.A && !RIGHT){
            UP = false;
            DOWN = false;
            LEFT = true;
            RIGHT = false;

            snakeHead.setRotate(-180);
        }

        if(event.getCode() == KeyCode.D && !LEFT){
            UP = false;
            DOWN = false;
            LEFT = false;
            RIGHT = true;

            snakeHead.setRotate(0);
        }
    }

    //Called to moveSnakeHead
    private void MoveSnakeHead(Rectangle snakeHead){
        int speed_XY = 25;
        if(UP){
            snakeHeadY -= speed_XY;
            snakeHead.setTranslateY(snakeHeadY);
        }
        if(DOWN){
            snakeHeadY += speed_XY;
            snakeHead.setTranslateY(snakeHeadY);
        }
        if(LEFT){
            snakeHeadX -= speed_XY;
            snakeHead.setTranslateX(snakeHeadX);
        }
        if(RIGHT){
            snakeHeadX += speed_XY;
            snakeHead.setTranslateX(snakeHeadX);
        }
    }

    private void MoveSnakeTail(Rectangle snakeTail, int tailNumber){
        double y = headPoints.get(gameTicks - tailNumber + 1).getY() - snakeTail.getY();
        double x = headPoints.get(gameTicks - tailNumber + 1).getX() - snakeTail.getX();
        snakeTail.setTranslateX(x);
        snakeTail.setTranslateY(y);
    }

    //Displays playerScore in scene
    public void PlayerScore(int playerScore) {
        if(themeNumber == 2 || themeNumber == 3){
            playerscoreLabel.setTextFill(Color.WHITE);
        }
        playerscoreLabel.setText("SCORE : " + playerScore);
    }

    //Displays playerName in scene
    public void PlayerName(String playerName) {
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
        Parent root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("fxmls/StartScene.fxml")));
        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

}
