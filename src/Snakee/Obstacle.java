package Snakee;

import javafx.scene.image.Image;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Rectangle;

import java.util.ArrayList;
import java.util.List;

public class Obstacle {

    //Creates obstacle
    public static Rectangle GenerateObstacle(Rectangle obstacleObject, ArrayList<Rectangle> snakeBody, List<Position> headPoints){
        int obstacleX = (int) (Math.random() * (860) + 0);
        int obstacleY = (int) (Math.random() * (540) + 0);

        int size = headPoints.size() - 1;
        if (size > 2){
            for (int i = size - snakeBody.size(); i < size; i++) {
                if (obstacleX == (headPoints.get(i).getX())
                        && obstacleY == (headPoints.get(i).getY())) {
                    System.out.println("OBSTACLE SPAWNS IN BODY");
                    GenerateObstacle(obstacleObject, snakeBody, headPoints);
                }
            }
        }

        obstacleObject.setX(obstacleX);
        obstacleObject.setY(obstacleY);
        obstacleObject.setWidth(50);
        obstacleObject.setHeight(50);

        Image obstacleImage = new Image("Snakee/images/obstacle-beartrap.png");
        obstacleObject.setFill(new ImagePattern(obstacleImage));

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
