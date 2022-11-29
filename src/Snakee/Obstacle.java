package Snakee;

import javafx.scene.image.Image;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Rectangle;

import java.util.ArrayList;
import java.util.List;

public class Obstacle {

    //Creates obstacle
    public static Rectangle GenerateObstacle(Rectangle obstacleObject, ArrayList<Rectangle> snakeBody, List<Position> headPoints, int themeNumber){
        int obstacleX = (int) (Math.random() * (860) + 0);
        int obstacleY = (int) (Math.random() * (540) + 0);
        Image obstacleImage;

        int size = headPoints.size() - 1;
        if (size > 2){
            for (int i = size - snakeBody.size(); i < size; i++) {
                if (obstacleX == (headPoints.get(i).getX())
                        && obstacleY == (headPoints.get(i).getY())) {
                    System.out.println("OBSTACLE SPAWNS IN BODY");
                    GenerateObstacle(obstacleObject, snakeBody, headPoints, themeNumber);
                }
            }
        }

        obstacleObject.setX(obstacleX);
        obstacleObject.setY(obstacleY);
        obstacleObject.setWidth(25);
        obstacleObject.setHeight(25);

        switch (themeNumber) {
            case 2 -> obstacleObject.setFill(new ImagePattern(Theme.GeneratePacmanObstacle()));
            case 3 -> obstacleObject.setFill(new ImagePattern(Theme.GenerateInvaderObstacle()));
            default -> {
                obstacleImage = new Image("Snakee/images/obstacle-one.png");
                obstacleObject.setFill(new ImagePattern(obstacleImage));
            }
        }
        return obstacleObject;
    }

    //Checks if snake hits obstacle
    static boolean HitObstacle(Rectangle snakeHead, Rectangle obstacleObject){
        if(snakeHead.getBoundsInParent().intersects(obstacleObject.getBoundsInParent())){
            System.out.println("OBSTACLE HIT");
            return true;
        }
        return false;
    }
}
