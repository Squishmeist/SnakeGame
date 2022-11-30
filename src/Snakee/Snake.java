package Snakee;

import javafx.scene.image.Image;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Rectangle;

import java.util.ArrayList;
import java.util.List;

import static Snakee.GameSceneController.themeNumber;

public class Snake {

    //Check if snake is outOfBounds
    static boolean OutOfBounds(double snakeHeadX, double snakeHeadY){
        boolean xOut = (snakeHeadX < -250 || snakeHeadX > 600);
        boolean yOut = (snakeHeadY < -250 || snakeHeadY > 290);
        return xOut || yOut;
    }

    //Check if snake hits itself
    static boolean BodyHit(List<Position> headPoints, ArrayList<Rectangle> snakeBody){
        int size = headPoints.size() - 1;
        if (size > 2){
            for (int i = size - snakeBody.size(); i < size; i++){
                if(headPoints.get(size).getX() == (headPoints.get(i).getX())
                        && headPoints.get(size).getY() == (headPoints.get(i).getY())){
                    System.out.println("BODY HIT");
                    return true;
                }
            }
        }
        return false;
    }

    static Rectangle AddSnakeTail(ArrayList<Rectangle> snakeBody, Rectangle snakeHead, Double snakeSize, double snakeHeadX, double snakeHeadY){
        if (snakeBody.size() == 1) {
            Rectangle snakeFirstTail = new Rectangle(snakeHead.getX() - snakeSize, snakeHead.getY(), snakeSize, snakeSize);
            snakeBody.add(snakeFirstTail);

            Image snakeTailImage = Theme.GenerateSnakeTailImage(themeNumber);
            snakeFirstTail.setFill(new ImagePattern(snakeTailImage));
            return snakeFirstTail;
        }

        else{
            double snakeTailX = snakeBody.get(1).getX() + snakeHeadX + snakeSize;
            double snakeTailY = snakeBody.get(1).getY() + snakeHeadY;
            Rectangle snakeTail = new Rectangle(snakeTailX, snakeTailY, snakeSize, snakeSize);
            snakeBody.add(snakeTail);

            Image snakeTailImage = Theme.GenerateSnakeTailImage(themeNumber);
            snakeTail.setFill(new ImagePattern(snakeTailImage));
            return snakeTail;
        }
    }

    static Rectangle RemoveSnakeTail(ArrayList<Rectangle> snakeBody, int snakebodySize){
        return snakeBody.get(snakebodySize-1);
    }
}
