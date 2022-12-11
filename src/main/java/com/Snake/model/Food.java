package com.Snake.model;

import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Rectangle;

import java.util.ArrayList;
import java.util.List;

/**
 * @Project COMP2013-Coursework
 * @Description Food Class
 * @Author Ainsley Lee
 */

public class Food {
    private AnchorPane m_gameAnchorPane;
    private Rectangle m_snakeHead;
    private ArrayList<Rectangle> m_snakeBody;
    private List<Position> m_headPoints;
    private int m_gameTheme;
    public Rectangle m_foodObject = new Rectangle();

    public Food(AnchorPane gameAnchorPane, Rectangle snakeHead, ArrayList<Rectangle> snakeBody, List<Position> headPoints, int gameTheme){
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
     * Depending on the gameTheme different methods are called from the Theme class that return a randomly chosen image from within that theme.
     *
     * @return image generated based on gameTheme
     */
    private Image GenerateFoodImage(){
        Image foodImage;
        switch(m_gameTheme){
            case 2 -> foodImage = Theme.GeneratePacmanFood();
            case 3 -> foodImage = Theme.GenerateInvaderFood();
            default -> foodImage = Theme.GenerateSnakeFood();
        }
        return foodImage;
    }

    /**
     * This method generates a random x and y number within the game scenes dimensions.
     * These values are then checked against the headPoints list which stores all the points of the snake.
     * If the combination of the generated numbers matches a snake point the function is rerun to ensure a
     * food object is not spawned within the snakes body.
     * <p>
     * The objects x and y coordinates are then set to the generated numbers along with the width and height
     * being set to 25.
     * The setFill function is then used to set the objects image to be one returned by the GenerateFoodImage method
     * in the Food class. This randomly selects a food image from the theme category picked by the player.
     * <p>
     * The food object is then added to the scene.
     */
    public void GenerateFood() {
        int foodX = (int) (Math.random() * (840) + 0);
        int foodY = (int) (Math.random() * (530) + 0);
        int size = m_headPoints.size() - 1;
        if (size > 2) {
            for (int i = size - m_snakeBody.size(); i < size; i++) {
                if (foodX == (m_headPoints.get(i).getX())
                        && foodY == (m_headPoints.get(i).getY())) {
                    System.out.println("FOOD SPAWNS IN BODY");
                    GenerateFood();
                }
            }
        }
        m_foodObject.setX(foodX);
        m_foodObject.setY(foodY);
        m_foodObject.setWidth(25);
        m_foodObject.setHeight(25);
        m_foodObject.setFill(new ImagePattern(GenerateFoodImage()));
        m_gameAnchorPane.getChildren().add(m_foodObject);
    }

    /**
     * Method returns true if the snake head intersects the food object and returns false if not.
     *
     * @return true or false based on if intersections occurs between objects
     */
    public boolean EatenFood(){
        if(m_snakeHead.getBoundsInParent().intersects(m_foodObject.getBoundsInParent())){
            System.out.println("FOOD EATEN");
            return true;
        }
        return false;
    }

    /**
     * Method removes the food object from the game scene.
     */
    public void RemoveFood(){
        m_gameAnchorPane.getChildren().remove(m_foodObject);
    }
}
