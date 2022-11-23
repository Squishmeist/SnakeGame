package Snakee;

import javafx.scene.image.Image;

public class Food {

    static Image GenerateFoodImage(Image foodImage){
        int foodImageNumber = (int) (Math.random() * (17) + 1);

        switch (foodImageNumber) {
            case 0:
                foodImage = new Image("Snakee/images/food-apple.png");
                break;
            case 1:
                foodImage = new Image("Snakee/images/food-banana.png");
                break;
            case 2:
                foodImage = new Image("Snakee/images/food-blueberry.png");
                break;
            case 3:
                foodImage = new Image("Snakee/images/food-cherry.png");
                break;
            case 4:
                foodImage = new Image("Snakee/images/food-durian.png");
                break;
            case 5:
                foodImage = new Image("Snakee/images/food-grape.png");
                break;
            case 6:
                foodImage = new Image("Snakee/images/food-grapefruit.png");
                break;
            case 7:
                foodImage = new Image("Snakee/images/food-kiwi.png");
                break;
            case 8:
                foodImage = new Image("Snakee/images/food-lemon.png");
                break;
            case 9:
                foodImage = new Image("Snakee/images/food-litchi.png");
                break;
            case 10:
                foodImage = new Image("Snakee/images/food-mango.png");
                break;
            case 11:
                foodImage = new Image("Snakee/images/food-orange.png");
                break;
            case 12:
                foodImage = new Image("Snakee/images/food-peach.png");
                break;
            case 13:
                foodImage = new Image("Snakee/images/food-pear.png");
                break;
            case 14:
                foodImage = new Image("Snakee/images/food-pineapple.png");
                break;
            case 15:
                foodImage = new Image("Snakee/images/food-pitaya.png");
                break;
            case 16:
                foodImage = new Image("Snakee/images/food-strawberry.png");
                break;
            case 17:
                foodImage = new Image("Snakee/images/food-watermelon.png");
                break;
            default:
                // code block
        }
        return foodImage;
    }
}
