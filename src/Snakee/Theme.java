package Snakee;

import javafx.scene.image.Image;

public class Theme {

    static String GenerateGameBackground(int themeNumber){
        String paneId = switch (themeNumber) {
            case 2 -> "gamePane2";
            case 3 -> "gamePane3";
            default -> "gamePane1";
        };

        return paneId;
    }

    static Image GenerateSnakeTailImage(int themeNumber) {
        return switch (themeNumber) {
            case 2 -> new Image("Snakee/images/pacman-body.png");
            case 3 -> new Image("Snakee/images/invader-body.png");
            default -> new Image("Snakee/images/snake-body.png");
        };
    }

    static Image GenerateSnakeHeadImage(int themeNumber) {
        return switch (themeNumber) {
            case 2 -> new Image("Snakee/images/pacman-head.png");
            case 3 -> new Image("Snakee/images/invader-head.png");
            default -> new Image("Snakee/images/snake-head-right.png");
        };
    }

    static Image GeneratePacmanObstacle(){
        int obstacleImageNumber = (int) (Math.random() * (3) + 1);
        Image obstacleImage = null;

        switch (obstacleImageNumber) {
            case 0 -> obstacleImage = new Image("Snakee/images/pacman-blinky.png");
            case 1 -> obstacleImage = new Image("Snakee/images/pacman-clyde.png");
            case 2 -> obstacleImage = new Image("Snakee/images/pacman-inky.png");
            case 3 -> obstacleImage = new Image("Snakee/images/pacman-pinky.png");
            default -> {
            }
        }
        return obstacleImage;
    }

    static Image GenerateInvaderObstacle(){
        int obstacleImageNumber = (int) (Math.random() * (3) + 1);
        Image obstacleImage = null;

        switch (obstacleImageNumber) {
            case 0 -> obstacleImage = new Image("Snakee/images/invader-blue.png");
            case 1 -> obstacleImage = new Image("Snakee/images/invader-green.png");
            case 2 -> obstacleImage = new Image("Snakee/images/invader-orange.png");
            case 3 -> obstacleImage = new Image("Snakee/images/invader-pink.png");
            default -> {
            }
        }
        return obstacleImage;
    }
}
