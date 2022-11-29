package Snakee;

import javafx.scene.image.Image;

public class Theme {

    public static String GenerateGameBackground(int themeNumber){
        String paneId;

        switch(themeNumber){
            case 1:
                paneId = "gamePane1";
                break;
            case 2:
                paneId = "gamePane2";
                break;
            case 3:
                paneId = "gamePane3";
                break;
            default:
                paneId = "gamePane1";
        }
        return paneId;
    }

    public static Image GenerateSnakeTailImage(int themeNumber) {
        return switch (themeNumber) {
            case 1 -> new Image("Snakee/images/snake-body.png");
            case 2 -> new Image("Snakee/images/body-brown.png");
            case 3 -> new Image("Snakee/images/body-red.png");
            default -> new Image("Snakee/images/snake-body.png");
        };
    }

    public static Image GenerateSnakeHeadImage(int themeNumber) {
        return switch (themeNumber) {
            case 1 -> new Image("Snakee/images/snake-head-right.png");
            case 2 -> new Image("Snakee/images/head-brown.png");
            case 3 -> new Image("Snakee/images/head-red.png");
            default -> new Image("Snakee/images/snake-head-right.png");
        };
    }

}
