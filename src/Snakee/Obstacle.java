package Snakee;

import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Rectangle;

import java.util.ArrayList;
import java.util.List;

public class Obstacle {
    private AnchorPane m_gameAnchorPane;
    private Rectangle m_snakeHead;
    private ArrayList<Rectangle> m_snakeBody;
    private List<Position> m_headPoints;
    private int m_themeNumber;
    private Rectangle m_obstacleObject = new Rectangle();

    public Obstacle(AnchorPane gameAnchorPane, Rectangle snakeHead, ArrayList<Rectangle> snakeBody, List<Position> headPoints, int themeNumber){
        m_gameAnchorPane = gameAnchorPane;
        m_snakeHead = snakeHead;
        m_snakeBody = snakeBody;
        m_headPoints = headPoints;
        m_themeNumber = themeNumber;
    }

    //Creates obstacle
    public void GenerateObstacle(){
        int obstacleX = (int) (Math.random() * (870) - 40);
        int obstacleY = (int) (Math.random() * (560) - 40);

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
     * Method returns an image that is then used to setFill an object.
     * <p>
     * The image is set based on the themeNumber which is set by the player when they chose a theme.
     * Depending on the themeNumber different methods are called from the Theme class that return a randomly chosen image.
     *
     * @return image generated based on themeNumber
     */
    private Image GenerateObstacleImage(){
        Image obstacleImage;
        switch (m_themeNumber) {
            case 2 -> obstacleImage = Theme.GeneratePacmanFood();
            case 3 -> obstacleImage = Theme.GeneratePacmanFood();
            default -> {
                obstacleImage = new Image("Snakee/resources/images/snake/obstacle-one.png");
            }
        }
        return obstacleImage;
    }


    /**
     * Method returns true if the snakeHead intersects the obstacleObject and returns false if not.
     * @return true or false based on if intersections occurs between objects
     */
    public boolean HitObstacle(){
        if(m_snakeHead.getBoundsInParent().intersects(m_obstacleObject.getBoundsInParent())){
            System.out.println("OBSTACLE HIT");
            return true;
        }
        return false;
    }

    public void RemoveObstacle(){
        m_gameAnchorPane.getChildren().remove(m_obstacleObject);
    }
}
