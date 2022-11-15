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

    private final Double snakeSize = 25.;
    private final Rectangle snakeHead = new Rectangle(250,250,snakeSize,snakeSize);
    Rectangle snakeFirstTail = new Rectangle(snakeHead.getX() - snakeSize,snakeHead.getY(),snakeSize,snakeSize);

    // snakeHead x coordinate
    double snakeX = snakeHead.getLayoutX();
    // snakeHead y coordinate
    double snakeY = snakeHead.getLayoutY();

    //Direction snake is moving at start
    private Snake.Direction direction = Snake.Direction.RIGHT;

    //List of all position of the snake head
    private final List<Position> positions = new ArrayList<>();

    //List of all snake body parts
    private final ArrayList<Rectangle> snakeBody = new ArrayList<>();

    //Number of times snakes moved
    private int gameTicks = 0;

    //food exists
    private boolean foodExists = false;

    //Creates food object
    Rectangle foodObject = new Rectangle();

    @FXML
    private AnchorPane GameScene;
    @FXML
    Label playernameLabel;

    //Runs game every 0.3 seconds
    Timeline timeline = new Timeline(new KeyFrame(Duration.seconds(0.2),e ->{
        positions.add(new Position(snakeHead.getX() + snakeX, snakeHead.getY() + snakeY));
        moveSnakeHead(snakeHead);
        for (int i = 1; i < snakeBody.size(); i++) {
            moveSnakeTail(snakeBody.get(i),i);
        }
        gameTicks++;

        //if snake is out of bounds run switchToEndScene method
        if(outOfBounds()){
            System.out.println("OUT OF BOUNDS");
            try {
                switchToEndScene();
            } catch (IOException ex) {
                throw new RuntimeException(ex);
            }
        }

        //if food does not exist generate food
        if (!foodExists){
            foodGenerate();
        }
        //if food does exist check if its eaten
        else if (foodExists){
            foodEaten();
        }


    }));


    //Called after stage loaded
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        snakeBody.add(snakeHead);
        Image snakeHeadImage = new Image("Snakee/images/snake-head-right.png");
        snakeHead.setFill(new ImagePattern(snakeHeadImage));

        timeline.setCycleCount(Animation.INDEFINITE);
        timeline.play();

        snakeBody.add(snakeFirstTail);
        Image snakeTailImage = new Image("Snakee/images/snake-body.png");
        snakeFirstTail.setFill(new ImagePattern(snakeTailImage));

        GameScene.getChildren().addAll(snakeHead,snakeFirstTail);
    }

    @FXML
    public void keyPressed(KeyEvent event){
        if(event.getCode() == KeyCode.W && direction != Snake.Direction.DOWN){
            System.out.println("Pressed W");
            direction = Snake.Direction.UP;
        }

        if(event.getCode() == KeyCode.S && direction != Snake.Direction.UP){
            System.out.println("Pressed S");
            direction = Snake.Direction.DOWN;
        }

        if(event.getCode() == KeyCode.A && direction != Snake.Direction.RIGHT){
            System.out.println("Pressed A");
            direction = Snake.Direction.LEFT;
        }

        if(event.getCode() == KeyCode.D && direction != Snake.Direction.LEFT){
            System.out.println("Pressed D");
            direction = Snake.Direction.RIGHT;
        }
    }

    //Called to moveSnakeHead
    private void moveSnakeHead(Rectangle snakeHead){
        if(direction == Snake.Direction.UP){
            snakeY = snakeY - snakeSize;
            snakeHead.setRotate(-90);
            snakeHead.setTranslateY(snakeY);
        }
        if(direction == Snake.Direction.DOWN){
            snakeY = snakeY + snakeSize;
            snakeHead.setRotate(90);
            snakeHead.setTranslateY(snakeY);
        }
        if(direction == Snake.Direction.LEFT){
            snakeX = snakeX - snakeSize;
            snakeHead.setRotate(-180);
            snakeHead.setTranslateX(snakeX);
        }
        if(direction == Snake.Direction.RIGHT){
            snakeX = snakeX + snakeSize;
            snakeHead.setRotate(0);
            snakeHead.setTranslateX(snakeX);
        }
    }

    //New snake tail is created and added to the snake and the game scene
    private void addSnakeTail(){
        //Rectangle rectangle = snakeBody.get(snakeBody.size() - 1);

        Rectangle snakeTail = new Rectangle(snakeBody.get(1).getX() + snakeX + snakeSize, snakeBody.get(1).getY() + snakeY, snakeSize,snakeSize);
        snakeBody.add(snakeTail);

        Image snakeTailImage = new Image("Snakee/images/snake-body.png");
        snakeTail.setFill(new ImagePattern(snakeTailImage));

        GameScene.getChildren().add(snakeTail);
    }

    //Moves tail to position of head x gameTicks after head was there
    private void moveSnakeTail(Rectangle snakeTail, int tailNumber){
        double y = positions.get(gameTicks - tailNumber + 1).getY() - snakeTail.getY();
        double x = positions.get(gameTicks - tailNumber + 1).getX() - snakeTail.getX();
        snakeTail.setTranslateX(x);
        snakeTail.setTranslateY(y);
    }

    private void foodGenerate(){
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

    public void foodEaten(){
        if(snakeHead.getBoundsInParent().intersects(foodObject.getBoundsInParent())){
            System.out.println("FOOD EATEN");
            foodExists = false;
            addSnakeTail();
        }
    }

    //Check if snake is outOfBounds
    public boolean outOfBounds(){
        boolean xOut = (snakeX < -250 || snakeX > 600);
        boolean yOut = (snakeY < -250 || snakeY > 290);
        if (xOut || yOut)
        {
            return true;
        }
        return false;
    }

    //Displays playerName in scene
    public void playerName(String playerName) {
        playernameLabel.setText("PLAYER : " + playerName);
    }

    //Switch to EndScene
    public void switchToEndScene() throws IOException {
        timeline.stop();
        Parent root = FXMLLoader.load(getClass().getResource("EndScene.fxml"));
        stage = (Stage) snakeHead.getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    //Switch to StartScene
    public void switchToStartScene(ActionEvent event) throws IOException {
        timeline.stop();
        Parent root = FXMLLoader.load(getClass().getResource("StartScene.fxml"));
        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

}
