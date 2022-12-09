package com.Snake.model;

import javafx.scene.image.Image;

public class Theme {

    public static String GenerateGameBackground(int themeNumber){
        return switch (themeNumber) {
            case 2 -> "gamePane2";
            case 3 -> "gamePane3";
            default -> "gamePane1";
        };
    }

    static Image GenerateSnakeTailImage(int themeNumber) {
        return switch (themeNumber) {
            case 2 -> new Image(Theme.class.getResource("/com/Snake/images/pacman/pacman-body.png").toString());
            case 3 -> new Image(Theme.class.getResource("/com/Snake/images/spaceinvaders/invader-body.png").toString());
            default -> new Image(Theme.class.getResource("/com/Snake/images/snake/snake-body.png").toString());
        };
    }

    public static Image GenerateSnakeHeadImage(int themeNumber) {
        return switch (themeNumber) {
            case 2 -> new Image(Theme.class.getResource("/com/Snake/images/pacman/pacman-head.png").toString());
            case 3 -> new Image(Theme.class.getResource("/com/Snake/images/spaceinvaders/invader-head.png").toString());
            default -> new Image(Theme.class.getResource("/com/Snake/images/snake/snake-head.png").toString());
        };
    }

    public static String GenerateBackButton(int themeNumber) {
        return switch (themeNumber) {
            case 2, 3 -> "darkbackButton";
            default -> "backButton";
        };
    }

    static Image GeneratePacmanObstacle(){
        int obstacleImageNumber = (int) (Math.random() * (4) + 1);
        Image obstacleImage = null;

        switch (obstacleImageNumber) {
            case 1 -> obstacleImage = new Image(Theme.class.getResource("/com/Snake/images/pacman/pacman-blinky.png").toString());
            case 2 -> obstacleImage = new Image(Theme.class.getResource("/com/Snake/images/pacman/pacman-clyde.png").toString());
            case 3 -> obstacleImage = new Image(Theme.class.getResource("/com/Snake/images/pacman/pacman-inky.png").toString());
            case 4 -> obstacleImage = new Image(Theme.class.getResource("/com/Snake/images/pacman/pacman-pinky.png").toString());
        }
        return obstacleImage;
    }

    static Image GenerateInvaderObstacle(){
        int obstacleImageNumber = (int) (Math.random() * (4) + 1);
        Image obstacleImage = null;

        switch (obstacleImageNumber) {
            case 1 -> obstacleImage = new Image(Theme.class.getResource("/com/Snake/images/spaceinvaders/invader-blue.png").toString());
            case 2 -> obstacleImage = new Image(Theme.class.getResource("/com/Snake/images/spaceinvaders/invader-green.png").toString());
            case 3 -> obstacleImage = new Image(Theme.class.getResource("/com/Snake/images/spaceinvaders/invader-orange.png").toString());
            case 4 -> obstacleImage = new Image(Theme.class.getResource("/com/Snake/images/spaceinvaders/invader-pink.png").toString());
        }
        return obstacleImage;
    }


    static Image GenerateSnakeFood(){
        int foodImageNumber = (int) (Math.random() * (18) + 1);
        Image foodImage = null;

        switch (foodImageNumber) {
            case 1 -> foodImage = new Image(Theme.class.getResource("/com/Snake/images/snake/snake-food-apple.png").toString());
            case 2 -> foodImage = new Image(Theme.class.getResource("/com/Snake/images/snake/snake-food-banana.png").toString());
            case 3 -> foodImage = new Image(Theme.class.getResource("/com/Snake/images/snake/snake-food-blueberry.png").toString());
            case 4 -> foodImage = new Image(Theme.class.getResource("/com/Snake/images/snake/snake-food-cherry.png").toString());
            case 5 -> foodImage = new Image(Theme.class.getResource("/com/Snake/images/snake/snake-food-durian.png").toString());
            case 6 -> foodImage = new Image(Theme.class.getResource("/com/Snake/images/snake/snake-food-grape.png").toString());
            case 7 -> foodImage = new Image(Theme.class.getResource("/com/Snake/images/snake/snake-food-grapefruit.png").toString());
            case 8 -> foodImage = new Image(Theme.class.getResource("/com/Snake/images/snake/snake-food-kiwi.png").toString());
            case 9 -> foodImage = new Image(Theme.class.getResource("/com/Snake/images/snake/snake-food-lemon.png").toString());
            case 10 -> foodImage = new Image(Theme.class.getResource("/com/Snake/images/snake/snake-food-litchi.png").toString());
            case 11 -> foodImage = new Image(Theme.class.getResource("/com/Snake/images/snake/snake-food-mango.png").toString());
            case 12 -> foodImage = new Image(Theme.class.getResource("/com/Snake/images/snake/snake-food-orange.png").toString());
            case 13 -> foodImage = new Image(Theme.class.getResource("/com/Snake/images/snake/snake-food-peach.png").toString());
            case 14 -> foodImage = new Image(Theme.class.getResource("/com/Snake/images/snake/snake-food-pear.png").toString());
            case 15 -> foodImage = new Image(Theme.class.getResource("/com/Snake/images/snake/snake-food-pineapple.png").toString());
            case 16 -> foodImage = new Image(Theme.class.getResource("/com/Snake/images/snake/snake-food-pitaya.png").toString());
            case 17 -> foodImage = new Image(Theme.class.getResource("/com/Snake/images/snake/snake-food-strawberry.png").toString());
            case 18 -> foodImage = new Image(Theme.class.getResource("/com/Snake/images/snake/snake-food-watermelon.png").toString());
            default -> {
            }
        }
        return foodImage;
    }

    static Image GeneratePacmanFood(){
        int foodImageNumber = (int) (Math.random() * (5) + 1);
        Image foodImage = null;

        switch (foodImageNumber) {
            case 1 -> foodImage = new Image(Theme.class.getResource("/com/Snake/images/pacman/pacman-food-apple.png").toString());
            case 2 -> foodImage = new Image(Theme.class.getResource("/com/Snake/images/pacman/pacman-food-cherry.png").toString());
            case 3 -> foodImage = new Image(Theme.class.getResource("/com/Snake/images/pacman/pacman-food-grape.png").toString());
            case 4 -> foodImage = new Image(Theme.class.getResource("/com/Snake/images/pacman/pacman-food-orange.png").toString());
            case 5 -> foodImage = new Image(Theme.class.getResource("/com/Snake/images/pacman/pacman-food-strawberry.png").toString());
        }
        return foodImage;
    }

    static Image GenerateInvaderFood(){
        int foodImageNumber = (int) (Math.random() * (6) + 1);
        Image foodImage = null;

        switch (foodImageNumber) {
            case 1 -> foodImage = new Image(Theme.class.getResource("/com/Snake/images/spaceinvaders/invader-food-one.png").toString());
            case 2 -> foodImage = new Image(Theme.class.getResource("/com/Snake/images/spaceinvaders/invader-food-two.png").toString());
            case 3 -> foodImage = new Image(Theme.class.getResource("/com/Snake/images/spaceinvaders/invader-food-three.png").toString());
            case 4 -> foodImage = new Image(Theme.class.getResource("/com/Snake/images/spaceinvaders/invader-food-four.png").toString());
            case 5 -> foodImage = new Image(Theme.class.getResource("/com/Snake/images/spaceinvaders/invader-food-five.png").toString());
            case 6 -> foodImage = new Image(Theme.class.getResource("/com/Snake/images/spaceinvaders/invader-food-six.png").toString());
        }
        return foodImage;
    }


}
