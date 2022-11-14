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
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.Color;
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
    double x = snakeHead.getLayoutX();
    // snakeHead y coordinate
    double y = snakeHead.getLayoutY();

    //Direction snake is moving at start
    private Snake.Direction direction = Snake.Direction.RIGHT;

    //List of all position of the snake head
    private final List<Position> positions = new ArrayList<>();

    //List of all snake body parts
    private final ArrayList<Rectangle> snakeBody = new ArrayList<>();

    //Number of times snakes moved
    private int gameTicks = 0;

    @FXML
    private AnchorPane GameScene;
    @FXML
    Label playernameLabel;

    Button EndButton = new Button();

    //Runs game every 0.3 seconds
    Timeline timeline = new Timeline(new KeyFrame(Duration.seconds(0.3),e ->{
        positions.add(new Position(snakeHead.getX() + x, snakeHead.getY() + y));
        moveSnakeHead(snakeHead);
        for (int i = 1; i < snakeBody.size(); i++) {
            moveSnakeTail(snakeBody.get(i),i);
        }
        gameTicks++;
        if(outOfBounds(snakeHead)){
            System.out.println("OUT OF BOUNDS");
            try {
                switchToEndScene();
            } catch (IOException ex) {
                throw new RuntimeException(ex);
            }
        }
    }));


    //Called after stage loaded
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        //GameSceneImage.setVisible(false);

        snakeBody.add(snakeHead);
        //snakeHead.setFill(Color.GREEN);
        Image snakeHeadImage = new Image("Snakee/images/snake-head-right.png");
        snakeHead.setFill(new ImagePattern(snakeHeadImage));

        timeline.setCycleCount(Animation.INDEFINITE);
        timeline.play();

        snakeBody.add(snakeFirstTail);
        //snakeFirstTail.setFill(Color.DARKGREEN);
        Image snakeTailImage = new Image("Snakee/images/snake-body.png");
        snakeFirstTail.setFill(new ImagePattern(snakeTailImage));

        GameScene.getChildren().addAll(snakeHead,snakeFirstTail);
    }

    @FXML
    public void keyPressed(KeyEvent event){
        if(event.getCode() == KeyCode.W && direction != direction.DOWN){
            System.out.println("Pressed W");
            direction = direction.UP;
        }

        if(event.getCode() == KeyCode.S && direction != direction.UP){
            System.out.println("Pressed S");
            direction = direction.DOWN;
        }

        if(event.getCode() == KeyCode.A && direction != direction.RIGHT){
            System.out.println("Pressed A");
            direction = direction.LEFT;
        }

        if(event.getCode() == KeyCode.D && direction != direction.LEFT){
            System.out.println("Pressed D");
            direction = direction.RIGHT;
        }
    }

    //Called to moveSnakeHead
    private void moveSnakeHead(Rectangle snakeHead){
        if(direction == direction.UP){
            y = y - snakeSize;
            snakeHead.setTranslateY(y);
        }
        if(direction == direction.DOWN){
            y = y + snakeSize;
            snakeHead.setTranslateY(y);
        }
        if(direction == direction.LEFT){
            x = x - snakeSize;
            snakeHead.setTranslateX(x);
        }
        if(direction == direction.RIGHT){
            x = x + snakeSize;
            snakeHead.setTranslateX(x);
        }
    }

    //New snake tail is created and added to the snake and the game scene
    private void addSnakeTail(){
        Rectangle rectangle = snakeBody.get(snakeBody.size() - 1);
        Rectangle snakeTail = new Rectangle(
                snakeBody.get(1).getX() + x + snakeSize,
                snakeBody.get(1).getY() + y,
                snakeSize,snakeSize);
        snakeBody.add(snakeTail);
        GameScene.getChildren().add(snakeTail);
    }

    //Moves tail to position of head x gameTicks after head was there
    private void moveSnakeTail(Rectangle snakeTail, int tailNumber){
        double y = positions.get(gameTicks - tailNumber + 1).getY() - snakeTail.getY();
        double x = positions.get(gameTicks - tailNumber + 1).getX() - snakeTail.getX();
        snakeTail.setTranslateX(x);
        snakeTail.setTranslateY(y);
    }

    //Check if snake is outOfBounds
    public boolean outOfBounds(Rectangle snakeHead){
        boolean xOut = (x < -250 || x > 600);
        boolean yOut = (y < -250 || y > 290);
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
        Parent root = FXMLLoader.load(getClass().getResource("EndScene.fxml"));
        stage = (Stage) snakeHead.getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    //Switch to StartScene
    public void switchToStartScene(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("StartScene.fxml"));
        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

}
