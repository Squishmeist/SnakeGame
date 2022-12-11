package com.Snake.model;

import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Rectangle;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * @Project COMP2013-Coursework
 * @Description Obstacle Class
 * @Author Ainsley Lee
 */

public class Obstacle {
    private AnchorPane m_gameAnchorPane;
    private Rectangle m_snakeHead;
    private ArrayList<Rectangle> m_snakeBody;
    private List<Position> m_headPoints;
    private int m_gameTheme;
    public Rectangle m_obstacleObject = new Rectangle();

    public Obstacle(AnchorPane gameAnchorPane, Rectangle snakeHead, ArrayList<Rectangle> snakeBody, List<Position> headPoints, int gameTheme){
        m_gameAnchorPane = gameAnchorPane;
        m_snakeHead = snakeHead;
        m_snakeBody = snakeBody;
        m_headPoints = headPoints;
        m_gameTheme = gameTheme;
    }

    /**
     * Method returns an image that is then used to setFill an object.
     * <p>
     * The image is set based on the gameTheme variable which is set by the ThemeSelection method in the StartSceneController.
     * Depending on the gameTheme different methods are called from the Theme class that return a randomly chosen image a randomly chosen image from within that theme.
     *
     * @return image generated based on gameTheme
     */
    private Image GenerateObstacleImage(){
        Image obstacleImage;
        switch (m_gameTheme) {
            case 2 -> obstacleImage = Theme.GeneratePacmanObstacle();
            case 3 -> obstacleImage = Theme.GenerateInvaderObstacle();
            default -> obstacleImage = new Image(Objects.requireNonNull(Theme.class.getResource("/com/Snake/images/snake/obstacle-one.png")).toString());
        }
        return obstacleImage;
    }

    /**
     * This method generates a random x and y number within the game scenes dimensions.
     * These values are then checked against the headPoints list which stores all the points of the snake.
     * If the combination of the generated numbers matches a snake point the function is rerun to ensure an
     * obstacle object is not spawned within the snakes body.
     * <p>
     * The objects x and y coordinates are then set to the generated numbers along with the width and height
     * being set to 25.
     * The setFill function is then used to set the objects image to be one returned by the GenerateObstacleImage method
     * in the Food class. This randomly selects an obstacle image from the theme category picked by the player.
     * <p>
     * The obstacle object is then added to the scene.
     */
    public void GenerateObstacle(){
        int obstacleX = (int) (Math.random() * (820) + 0);
        int obstacleY = (int) (Math.random() * (510) + 0);
        int size = m_headPoints.size() - 1;
        if (size > 2){
            for (int i = size - m_snakeBody.size(); i < size; i++) {
                if (obstacleX == (m_headPoints.get(i).getX())
                        && obstacleY == (m_headPoints.get(i).getY())) {
                    System.out.println("OBSTACLE SPAWNS IN BODY");
                    GenerateObstacle();
                }
            }
        }
        m_obstacleObject.setX(obstacleX);
        m_obstacleObject.setY(obstacleY);
        m_obstacleObject.setWidth(40);
        m_obstacleObject.setHeight(40);
        m_obstacleObject.setFill(new ImagePattern(GenerateObstacleImage()));
        m_gameAnchorPane.getChildren().add(m_obstacleObject);
    }

    /**
     * Method returns true if the snake head intersects the obstacle object and returns false if not.
     *
     * @return true or false based on if intersections occurs between objects
     */
    public boolean HitObstacle(){
        if(m_snakeHead.getBoundsInParent().intersects(m_obstacleObject.getBoundsInParent())){
            System.out.println("OBSTACLE HIT");
            return true;
        }
        return false;
    }

    /**
     * Method removes the obstacle object from the game scene.
     */
    public void RemoveObstacle(){
        m_gameAnchorPane.getChildren().remove(m_obstacleObject);
    }
}
