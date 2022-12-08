package Snakee;

import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Rectangle;

import java.util.ArrayList;
import java.util.List;

public class Food {
    private AnchorPane m_gameAnchorPane;
    private Rectangle m_snakeHead;
    private ArrayList<Rectangle> m_snakeBody;
    private List<Position> m_headPoints;
    private int m_themeNumber;
    private Rectangle m_foodObject = new Rectangle();

    public Food(AnchorPane gameAnchorPane, Rectangle snakeHead, ArrayList<Rectangle> snakeBody, List<Position> headPoints, int themeNumber){
        m_gameAnchorPane = gameAnchorPane;
        m_snakeHead = snakeHead;
        m_snakeBody = snakeBody;
        m_headPoints = headPoints;
        m_themeNumber = themeNumber;
    }


    /**
     * This method generates a random x and y number.
     * It checks that the genearated x and y ints are not within the snake.
     * If the x and y are the same as a snakebody point it reruns the function.
     * <p>
     * The objects x and y are then set along with the width and height.
     * setFill is then used on the rectangle with the image returned by the GenerateFoodImage method.
     */
    public void GenerateFood() {
        //Generates random x and y points for food to spawn
        int foodX = (int) (Math.random() * (870) - 25);
        int foodY = (int) (Math.random() * (560) - 25);

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

        //Sets food objects x and y to randomly generated number
        m_foodObject.setX(foodX);
        m_foodObject.setY(foodY);
        //Sets food objects size
        m_foodObject.setWidth(25);
        m_foodObject.setHeight(25);
        //Loads image to fill rectangle object
        m_foodObject.setFill(new ImagePattern(GenerateFoodImage()));
        m_gameAnchorPane.getChildren().add(m_foodObject);
    }

    /**
     * Method returns an image that is then used to setFill an object.
     * <p>
     * The image is set based on the themeNumber which is set by the player when they chose a theme.
     * Depending on the themeNumber different methods are called from the Theme class that return a randomly chosen image.
     *
     * @return image generated based on themeNumber
     */
    private Image GenerateFoodImage(){
        Image foodImage;
        switch(m_themeNumber){
            case 2 -> foodImage = Theme.GeneratePacmanFood();
            case 3 -> foodImage = Theme.GenerateInvaderFood();
            default -> foodImage = Theme.GenerateSnakeFood();
        }
        return foodImage;
    }

    /**
     * Method returns true if the snakeHead intersects the foodObject and returns false if not.
     * @return true or false based on if intersections occurs between objects
     */
    public boolean EatenFood(){
        if(m_snakeHead.getBoundsInParent().intersects(m_foodObject.getBoundsInParent())){
            System.out.println("FOOD EATEN");
            return true;
        }
        return false;
    }

    public void RemoveFood(){
        m_gameAnchorPane.getChildren().remove(m_foodObject);
    }
}
