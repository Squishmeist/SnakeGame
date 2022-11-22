package Snakee;

import javafx.scene.image.Image;

public class Food {

    private Image FoodImage() {
        int foodImageNumber = (int) (Math.random() * (18) + 0);

        Image foodImage = null;
        switch (foodImageNumber) {
            case 1:
                foodImage = new Image("Snakee/images/food-apple.png");
                break;
            case 2:
                // code block
                break;
            default:
                // code block
        }
        return foodImage;
    }
}
