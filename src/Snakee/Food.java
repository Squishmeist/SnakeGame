package Snakee;

import javafx.scene.image.Image;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Rectangle;

import java.util.ArrayList;
import java.util.List;

public class Food {

    static Image GenerateFoodImage(){
        int foodImageNumber = (int) (Math.random() * (17) + 1);
        Image foodImage = null;

        switch (foodImageNumber) {
            case 0 -> foodImage = new Image("Snakee/images/food-apple.png");
            case 1 -> foodImage = new Image("Snakee/images/food-banana.png");
            case 2 -> foodImage = new Image("Snakee/images/food-blueberry.png");
            case 3 -> foodImage = new Image("Snakee/images/food-cherry.png");
            case 4 -> foodImage = new Image("Snakee/images/food-durian.png");
            case 5 -> foodImage = new Image("Snakee/images/food-grape.png");
            case 6 -> foodImage = new Image("Snakee/images/food-grapefruit.png");
            case 7 -> foodImage = new Image("Snakee/images/food-kiwi.png");
            case 8 -> foodImage = new Image("Snakee/images/food-lemon.png");
            case 9 -> foodImage = new Image("Snakee/images/food-litchi.png");
            case 10 -> foodImage = new Image("Snakee/images/food-mango.png");
            case 11 -> foodImage = new Image("Snakee/images/food-orange.png");
            case 12 -> foodImage = new Image("Snakee/images/food-peach.png");
            case 13 -> foodImage = new Image("Snakee/images/food-pear.png");
            case 14 -> foodImage = new Image("Snakee/images/food-pineapple.png");
            case 15 -> foodImage = new Image("Snakee/images/food-pitaya.png");
            case 16 -> foodImage = new Image("Snakee/images/food-strawberry.png");
            case 17 -> foodImage = new Image("Snakee/images/food-watermelon.png");
            default -> {
            }
        }
        return foodImage;
    }

    public static Rectangle GenerateFood(Rectangle foodObject, ArrayList<Rectangle> snakeBody, List<Position> headPoints) {
        //Generates random x and y points for food to spawn
        int foodX = (int) (Math.random() * (850) + 0);
        int foodY = (int) (Math.random() * (540) + 0);

        int size = headPoints.size() - 1;

        if (size > 2) {
            for (int i = size - snakeBody.size(); i < size; i++) {
                if (foodX == (headPoints.get(i).getX())
                        && foodY == (headPoints.get(i).getY())) {
                    System.out.println("FOOD SPAWNS IN BODY");
                    GenerateFood(foodObject, snakeBody, headPoints);
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
        foodObject.setFill(new ImagePattern(Food.GenerateFoodImage()));
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
