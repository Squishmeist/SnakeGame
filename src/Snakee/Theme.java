package Snakee;

import javafx.scene.image.Image;

public class Theme {

    public static Image GenerateSnakeTailImage(int themeNumber) {
        Image snakeTailImage = null;
        switch (themeNumber){
            case 1:
                snakeTailImage = new Image("Snakee/images/snake-body.png");
                break;
            case 2:
                snakeTailImage = new Image("Snakee/images/body-brown.png");
                break;
            case 3:
                snakeTailImage = new Image("Snakee/images/body-red.png");
                break;
            default:
                snakeTailImage = new Image("Snakee/images/snake-body.png");
        }
            return snakeTailImage;
    }

    public static Image GenerateSnakeHeadImage(int themeNumber) {
        Image snakeHeadImage = null;
        switch (themeNumber){
            case 1:
                snakeHeadImage = new Image("Snakee/images/snake-head-right.png");
                break;
            case 2:
                snakeHeadImage = new Image("Snakee/images/head-brown.png");
                break;
            case 3:
                snakeHeadImage = new Image("Snakee/images/head-red.png");
                break;
            default:
                snakeHeadImage = new Image("Snakee/images/snake-head-right.png");
        }
        return snakeHeadImage;
    }

}
