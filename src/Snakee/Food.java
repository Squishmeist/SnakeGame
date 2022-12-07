package Snakee;

import javafx.scene.image.Image;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Rectangle;

import java.util.ArrayList;
import java.util.List;

public class Food {

    /**
     * Method returns an image that is then used to setFill an object.
     * <p>
     * The image is set based on the themeNumber which is set by the player when they chose a theme.
     * Depending on the themeNumber different methods are called from the Theme class that return a randomly chosen image.
     *
     * @param themeNumber   int based on players theme choice
     * @return image generated based on themeNumber
     */
    static Image GenerateFoodImage(int themeNumber){
        Image foodImage;

        switch(themeNumber){
            case 2 -> foodImage = Theme.GeneratePacmanFood();
            case 3 -> foodImage = Theme.GenerateInvaderFood();
            default -> foodImage = Theme.GenerateSnakeFood();
        }
        return foodImage;
    }

    /**
     * Method returns a rectangle object that is then used to increase the players score and body length.
     * <p>
     * This method generates a random x and y number.
     * It checks that the genearated x and y ints are not within the snake.
     * If the x and y are the same as a snakebody point it reruns the function.
     * <p>
     * The objects x and y are then set along with the width and height.
     * setFill is then used on the rectangle with the image returned by the GenerateFoodImage method.
     *
     * @param foodObject    rectangle object to return
     * @param snakeBody     list of all snake body parts
     * @param headPoints    list of all positions of the snake head
     * @param themeNumber   int based on players theme choice
     * @return rectangle object generated
     */
    public static Rectangle GenerateFood(Rectangle foodObject, ArrayList<Rectangle> snakeBody, List<Position> headPoints, int themeNumber) {
        //Generates random x and y points for food to spawn
        int foodX = (int) (Math.random() * (870) - 25);
        int foodY = (int) (Math.random() * (560) - 25);

        int size = headPoints.size() - 1;

        if (size > 2) {
            for (int i = size - snakeBody.size(); i < size; i++) {
                if (foodX == (headPoints.get(i).getX())
                        && foodY == (headPoints.get(i).getY())) {
                    System.out.println("FOOD SPAWNS IN BODY");
                    GenerateFood(foodObject, snakeBody, headPoints, themeNumber);
                }
            }
        }

        //Sets food objects x and y to randomly generated number
        foodObject.setX(foodX);
        foodObject.setY(foodY);
        //Sets food objects size
        foodObject.setWidth(25);
        foodObject.setHeight(25);

        //Loads image to fill rectangle object
        foodObject.setFill(new ImagePattern(Food.GenerateFoodImage(themeNumber)));
        return foodObject;
    }

    /**
     * Method returns true if the snakeHead intersects the foodObject and returns false if not.
     *
     * @param snakeHead     rectangle object snakeHead
     * @param foodObject    rectangle object foodObject
     * @return true or false based on if intersections occurs between objects
     */
    public static boolean EatenFood(Rectangle snakeHead, Rectangle foodObject){
        if(snakeHead.getBoundsInParent().intersects(foodObject.getBoundsInParent())){
            System.out.println("FOOD EATEN");
            return true;
        }
        return false;
    }
}
