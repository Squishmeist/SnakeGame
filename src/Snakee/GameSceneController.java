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

    private int speed_XY = 5;

    private static int playerScore;

    private final Double snakeSize = 25.;
    private final Rectangle snakeHead = new Rectangle(250,250,snakeSize,snakeSize);

    // snakeHead x coordinate
    double snakeHeadX = snakeHead.getLayoutX();
    // snakeHead y coordinate
    double snakeHeadY = snakeHead.getLayoutY();

    //List of all position of the snake head
    private final List<Position> headPoints = new ArrayList<>();

    //List of all snake body parts
    private final ArrayList<Rectangle> snakeBody = new ArrayList<>();

    private int num = snakeBody.size() / speed_XY;

    boolean UP, DOWN, LEFT, RIGHT;

    //food exists
    private boolean foodExists;

    //Creates food object
    Rectangle foodObject = new Rectangle();

    //Number of times snakes moved
    private int gameTicks;

    @FXML
    private AnchorPane GameScene;
    @FXML
    Label playernameLabel;
    @FXML
    Label playerscoreLabel;

    //Runs game every 0.3 seconds
    Timeline timeline = new Timeline(new KeyFrame(Duration.millis(20),e ->{
        headPoints.add(new Position(snakeHead.getX() + snakeHeadX, snakeHead.getY() + snakeHeadY));
        MoveSnakeHead(snakeHead);
        for (int i = 1; i < snakeBody.size(); i++) {
            MoveSnakeTail(snakeBody.get(i),i);
        }
        gameTicks++;

        //if snake is out of bounds run switchToEndScene method
        if(OutOfBounds()){
            System.out.println("OUT OF BOUNDS");
            try {
                SwitchToEndScene();
            } catch (IOException ex) {
                throw new RuntimeException(ex);
            }
        }

        //if food does not exist generate food
        if (!foodExists){
            FoodGenerate();
        }
        //if food does exist check if its eaten
        else if (foodExists){
            FoodEaten();
            PlayerScore(playerScore);
        }

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
            System.out.println("Pressed W");
            UP = true;
            DOWN = false;
            LEFT = false;
            RIGHT = false;

            snakeHead.setRotate(-90);
        }

        if(event.getCode() == KeyCode.S && !DOWN){
            System.out.println("Pressed S");
            UP = false;
            DOWN = true;
            LEFT = false;
            RIGHT = false;

            snakeHead.setRotate(90);
        }

        if(event.getCode() == KeyCode.A && !RIGHT){
            System.out.println("Pressed A");
            UP = false;
            DOWN = false;
            LEFT = true;
            RIGHT = false;

            snakeHead.setRotate(-180);
        }

        if(event.getCode() == KeyCode.D && !LEFT){
            System.out.println("Pressed D");
            UP = false;
            DOWN = false;
            LEFT = false;
            RIGHT = true;

            snakeHead.setRotate(0);
        }
    }

    //Called to moveSnakeHead
    private void MoveSnakeHead(Rectangle snakeHead){
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
        System.out.println("SIZE : " + snakeBody.size());

        if (snakeBody.size() == 1){
            Rectangle snakeFirstTail = new Rectangle(snakeHead.getX() - snakeSize,snakeHead.getY(),snakeSize,snakeSize);
            snakeBody.add(snakeFirstTail);

            Image snakeTailImage = new Image("Snakee/images/snake-body.png");
            snakeFirstTail.setFill(new ImagePattern(snakeTailImage));
            GameScene.getChildren().add(snakeFirstTail);
        }

        else{
            double snakeTailX = (snakeBody.get(1).getX() - (snakeSize * snakeBody.size()));
            double snakeTailY = (snakeBody.get(1).getY());

            Rectangle snakeTail = new Rectangle(snakeTailX, snakeTailY, snakeSize,snakeSize);
            snakeBody.add(snakeTail);

            Image snakeTailImage = new Image("Snakee/images/snake-body.png");
            snakeTail.setFill(new ImagePattern(snakeTailImage));

            GameScene.getChildren().add(snakeTail);
        }
    }

    private void MoveSnakeTail(Rectangle snakeTail, int tailNumber){
        double y = headPoints.get(gameTicks - tailNumber - 3).getY() - snakeTail.getY();
        double x = headPoints.get(gameTicks - tailNumber - 3).getX() - snakeTail.getX();
        snakeTail.setTranslateX(x);
        snakeTail.setTranslateY(y);
    }

    private void FoodGenerate(){
        //Generates random x and y points for food to spawn
        int xFood = (int)(Math.random() * (860) + 0 );
        int yFood = (int)(Math.random() * (550) + 0 );

        //Sets food objects x and y to randomly generated number
        foodObject.setX(xFood);
        foodObject.setY(yFood);
        //Sets food objects size
        foodObject.setWidth(snakeSize);
        foodObject.setHeight(snakeSize);

        //Loads image to fill rectangle object
        Image foodImage = new Image("Snakee/images/food-apple.png");
        foodObject.setFill(new ImagePattern(foodImage));

        //Sets food exists to equal true
        foodExists = true;
        //Adds object to the scene
        GameScene.getChildren().add(foodObject);
    }

    public void FoodEaten(){
        if(snakeHead.getBoundsInParent().intersects(foodObject.getBoundsInParent())){
            System.out.println("FOOD EATEN");

            foodExists = false;
            playerScore += 521;

            GameScene.getChildren().remove(foodObject);
            AddSnakeTail();
        }
    }

    //Check if snake is outOfBounds
    public boolean OutOfBounds(){
        boolean xOut = (snakeHeadX < -250 || snakeHeadX > 600);
        boolean yOut = (snakeHeadY < -250 || snakeHeadY > 290);
        if (xOut || yOut)
        {
            return true;
        }
        return false;
    }

    public void PlayerScore(int playerScore) {
        playerscoreLabel.setText("SCORE : " + playerScore);
    }

    //Displays playerName in scene
    public void PlayerName(String playerName) {
        playernameLabel.setText("PLAYER : " + playerName);
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
