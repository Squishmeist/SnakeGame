package Snakee;

import javafx.scene.image.Image;

public class Theme {

    static String GenerateGameBackground(int themeNumber){
        return switch (themeNumber) {
            case 2 -> "gamePane2";
            case 3 -> "gamePane3";
            default -> "gamePane1";
        };
    }

    static Image GenerateSnakeTailImage(int themeNumber) {
        return switch (themeNumber) {
            case 2 -> new Image("Snakee/resources/images/pacman/pacman-body.png");
            case 3 -> new Image("Snakee/resources/images/spaceinvaders/invader-body.png");
            default -> new Image("Snakee/resources/images/snake/snake-body.png");
        };
    }

    static Image GenerateSnakeHeadImage(int themeNumber) {
        return switch (themeNumber) {
            case 2 -> new Image("Snakee/resources/images/pacman/pacman-head.png");
            case 3 -> new Image("Snakee/resources/images/spaceinvaders/invader-head.png");
            default -> new Image("Snakee/resources/images/snake/snake-head.png");
        };
    }

    static String GenerateBackButton(int themeNumber) {
        return switch (themeNumber) {
            case 2, 3 -> "darkbackButton";
            default -> "backButton";
        };
    }

    static Image GeneratePacmanObstacle(){
        int obstacleImageNumber = (int) (Math.random() * (4) + 1);
        Image obstacleImage = null;

        switch (obstacleImageNumber) {
            case 1 -> obstacleImage = new Image("Snakee/resources/images/pacman/pacman-blinky.png");
            case 2 -> obstacleImage = new Image("Snakee/resources/images/pacman/pacman-clyde.png");
            case 3 -> obstacleImage = new Image("Snakee/resources/images/pacman/pacman-inky.png");
            case 4 -> obstacleImage = new Image("Snakee/resources/images/pacman/pacman-pinky.png");
        }
        return obstacleImage;
    }

    static Image GenerateInvaderObstacle(){
        int obstacleImageNumber = (int) (Math.random() * (4) + 1);
        Image obstacleImage = null;

        switch (obstacleImageNumber) {
            case 1 -> obstacleImage = new Image("Snakee/resources/images/spaceinvaders/invader-blue.png");
            case 2 -> obstacleImage = new Image("Snakee/resources/images/spaceinvaders/invader-green.png");
            case 3 -> obstacleImage = new Image("Snakee/resources/images/spaceinvaders/invader-orange.png");
            case 4 -> obstacleImage = new Image("Snakee/resources/images/spaceinvaders/invader-pink.png");
        }
        return obstacleImage;
    }

    static Image GenerateSnakeFood(){
        int foodImageNumber = (int) (Math.random() * (18) + 1);
        Image foodImage = null;

        switch (foodImageNumber) {
            case 1 -> foodImage = new Image("Snakee/resources/images/snake/snake-food-apple.png");
            case 2 -> foodImage = new Image("Snakee/resources/images/snake/snake-food-banana.png");
            case 3 -> foodImage = new Image("Snakee/resources/images/snake/snake-food-blueberry.png");
            case 4 -> foodImage = new Image("Snakee/resources/images/snake/snake-food-cherry.png");
            case 5 -> foodImage = new Image("Snakee/resources/images/snake/snake-food-durian.png");
            case 6 -> foodImage = new Image("Snakee/resources/images/snake/snake-food-grape.png");
            case 7 -> foodImage = new Image("Snakee/resources/images/snake/snake-food-grapefruit.png");
            case 8 -> foodImage = new Image("Snakee/resources/images/snake/snake-food-kiwi.png");
            case 9 -> foodImage = new Image("Snakee/resources/images/snake/snake-food-lemon.png");
            case 10 -> foodImage = new Image("Snakee/resources/images/snake/snake-food-litchi.png");
            case 11 -> foodImage = new Image("Snakee/resources/images/snake/snake-food-mango.png");
            case 12 -> foodImage = new Image("Snakee/resources/images/snake/snake-food-orange.png");
            case 13 -> foodImage = new Image("Snakee/resources/images/snake/snake-food-peach.png");
            case 14 -> foodImage = new Image("Snakee/resources/images/snake/snake-food-pear.png");
            case 15 -> foodImage = new Image("Snakee/resources/images/snake/snake-food-pineapple.png");
            case 16 -> foodImage = new Image("Snakee/resources/images/snake/snake-food-pitaya.png");
            case 17 -> foodImage = new Image("Snakee/resources/images/snake/snake-food-strawberry.png");
            case 18 -> foodImage = new Image("Snakee/resources/images/snake/snake-food-watermelon.png");
            default -> {
            }
        }
        return foodImage;
    }

    static Image GeneratePacmanFood(){
        int foodImageNumber = (int) (Math.random() * (5) + 1);
        Image foodImage = null;

        switch (foodImageNumber) {
            case 1 -> foodImage = new Image("Snakee/resources/images/pacman/pacman-food-apple.png");
            case 2 -> foodImage = new Image("Snakee/resources/images/pacman/pacman-food-cherry.png");
            case 3 -> foodImage = new Image("Snakee/resources/images/pacman/pacman-food-grape.png");
            case 4 -> foodImage = new Image("Snakee/resources/images/pacman/pacman-food-orange.png");
            case 5 -> foodImage = new Image("Snakee/resources/images/pacman/pacman-food-strawberry.png");
        }
        return foodImage;
    }

    static Image GenerateInvaderFood(){
        int foodImageNumber = (int) (Math.random() * (6) + 1);
        Image foodImage = null;

        switch (foodImageNumber) {
            case 1 -> foodImage = new Image("Snakee/resources/images/spaceinvaders/invader-food-one.png");
            case 2 -> foodImage = new Image("Snakee/resources/images/spaceinvaders/invader-food-two.png");
            case 3 -> foodImage = new Image("Snakee/resources/images/spaceinvaders/invader-food-three.png");
            case 4 -> foodImage = new Image("Snakee/resources/images/spaceinvaders/invader-food-four.png");
            case 5 -> foodImage = new Image("Snakee/resources/images/spaceinvaders/invader-food-five.png");
            case 6 -> foodImage = new Image("Snakee/resources/images/spaceinvaders/invader-food-six.png");
        }
        return foodImage;
    }

}
