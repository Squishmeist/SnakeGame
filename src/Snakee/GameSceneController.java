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
import javafx.scene.image.Image;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;
import javafx.util.Duration;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

public class GameSceneController implements Initializable{
    private Stage stage;
    private Scene scene;
    private static int playerScore;

    static int themeNumber = 0;

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

    //Number of times snakes moved
    private int gameTicks;

    @FXML
    private AnchorPane GameScene;
    @FXML
    Label playernameLabel;
    @FXML
    Label playerscoreLabel;

    //Runs game every 80 milliseconds
    Timeline timeline = new Timeline(new KeyFrame(Duration.millis(80),e ->{
        headPoints.add(new Position(snakeHead.getX() + snakeHeadX, snakeHead.getY() + snakeHeadY));
        MoveSnakeHead(snakeHead);
        for (int i = 1; i < snakeBody.size(); i++) {
            MoveSnakeTail(snakeBody.get(i),i);
        }
        gameTicks++;

        //if snake is out of bounds run switchToEndScene method
        if(OutOfBounds() || BodyHit()){
            System.out.println("OUT OF BOUNDS or BODY HIT");
            try {
                SwitchToEndScene();
            } catch (IOException ex) {
                throw new RuntimeException(ex);
            }
        }

        //if food does not exist generate food
        if (!foodExists){
            foodObject = Food.GenerateFood(foodObject, snakeBody, headPoints);
            foodExists = true;
            GameScene.getChildren().add(foodObject);
        }
        //if food does exist check if its eaten
        else {
            if(Food.EatenFood(snakeHead, foodObject)) {
                foodExists = false;
                playerScore += 521;
                GameScene.getChildren().remove(foodObject);
                AddSnakeTail();
            }
        }

        if(!obstacleExists){
            obstacleObject = Obstacle.GenerateObstacle(obstacleObject, snakeBody, headPoints);
            obstacleExists = true;
            GameScene.getChildren().add(obstacleObject);
        }
        else {
            if(Obstacle.HitObstacle(snakeHead, obstacleObject)){
                playerScore -= 521;
                obstacleExists = false;
                GameScene.getChildren().remove(obstacleObject);
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
        gameTicks = 0;

        PlayerScore(playerScore);

        snakeBody.add(snakeHead);
        Image snakeHeadImage = new Image("Snakee/images/snake-head-right.png");
        snakeHead.setFill(new ImagePattern(snakeHeadImage));

        timeline.setCycleCount(Animation.INDEFINITE);
        timeline.play();

        GameScene.getChildren().add(snakeHead);
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

        if(event.getCode() == KeyCode.S && !DOWN){
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

    private void AddSnakeTail(){
        if (snakeBody.size() == 1) {
            Rectangle snakeFirstTail = new Rectangle(snakeHead.getX() - snakeSize, snakeHead.getY(), snakeSize, snakeSize);
            snakeBody.add(snakeFirstTail);

            Image snakeTailImage = new Image("Snakee/images/snake-body.png");
            snakeFirstTail.setFill(new ImagePattern(snakeTailImage));
            GameScene.getChildren().add(snakeFirstTail);
        }

        else{
            double snakeTailX = snakeBody.get(1).getX() + snakeHeadX + snakeSize;
            double snakeTailY = snakeBody.get(1).getY() + snakeHeadY;

            Rectangle snakeTail = new Rectangle(snakeTailX, snakeTailY, snakeSize, snakeSize);
            snakeBody.add(snakeTail);

            Image snakeTailImage = new Image("Snakee/images/snake-body.png");
            snakeTail.setFill(new ImagePattern(snakeTailImage));
            GameScene.getChildren().add(snakeTail);
        }
    }

    private void MoveSnakeTail(Rectangle snakeTail, int tailNumber){
        double y = headPoints.get(gameTicks - tailNumber + 1).getY() - snakeTail.getY();
        double x = headPoints.get(gameTicks - tailNumber + 1).getX() - snakeTail.getX();
        snakeTail.setTranslateX(x);
        snakeTail.setTranslateY(y);
    }

    //Check if snake hits itself
    private boolean BodyHit(){
        int size = headPoints.size() - 1;

        if (size > 2){
            for (int i = size - snakeBody.size(); i < size; i++){
                if(headPoints.get(size).getX() == (headPoints.get(i).getX())
                && headPoints.get(size).getY() == (headPoints.get(i).getY())){
                    System.out.println("BODY HIT");
                    return true;
                }
            }
        }
        return false;
    }

    //Check if snake is outOfBounds
    private boolean OutOfBounds(){
        boolean xOut = (snakeHeadX < -250 || snakeHeadX > 600);
        boolean yOut = (snakeHeadY < -250 || snakeHeadY > 290);
        if (xOut || yOut)
        {
            return true;
        }
        return false;
    }

    //Displays playerScore in scene
    public void PlayerScore(int playerScore) {
        playerscoreLabel.setText("SCORE : " + playerScore);
    }

    //Displays playerName in scene
    public void PlayerName(String playerName) {
        playernameLabel.setText("PLAYER : " + playerName);
        System.out.println("THEME : " + themeNumber);
    }

    //Switch to EndScene
    public void SwitchToEndScene() throws IOException {
        timeline.stop();
        Parent root = FXMLLoader.load(getClass().getResource("EndScene.fxml"));
        stage = (Stage) snakeHead.getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    //Switch to StartScene
    public void SwitchToStartScene(ActionEvent event) throws IOException {
        timeline.stop();
        Parent root = FXMLLoader.load(getClass().getResource("StartScene.fxml"));
        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

}
