package Snakee;

import javafx.scene.image.Image;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Rectangle;

import java.util.ArrayList;
import java.util.List;

public class Food {

    static Image GenerateFoodImage(int themeNumber){
        Image foodImage = null;

        switch(themeNumber){
            case 1 -> foodImage = Theme.GenerateSnakeFood();
            case 2 -> foodImage = Theme.GenerateSnakeFood();
            case 3 -> foodImage = Theme.GenerateInvaderFood();
            default -> {
            }
        }

        return foodImage;
    }

    public static Rectangle GenerateFood(Rectangle foodObject, ArrayList<Rectangle> snakeBody, List<Position> headPoints, int themeNumber) {
        //Generates random x and y points for food to spawn
        int foodX = (int) (Math.random() * (850) + 0);
        int foodY = (int) (Math.random() * (540) + 0);

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

    static boolean EatenFood(Rectangle snakeHead, Rectangle foodObject){
        if(snakeHead.getBoundsInParent().intersects(foodObject.getBoundsInParent())){
            System.out.println("FOOD EATEN");
            return true;
        }
        return false;
    }
}
