package com.Snake.model;

import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Rectangle;

import java.util.ArrayList;
import java.util.List;

import static com.Snake.controller.GameSceneController.gameTheme;

/**
 * @Project COMP2013-Coursework
 * @Description Snake Class
 * @Author Ainsley Lee
 */

public class Snake {
    private AnchorPane m_gameAnchorPane;
    private ArrayList<Rectangle> m_snakeBody;
    private Rectangle m_snakeHead;
    private Double m_snakeSize;

    public Snake(AnchorPane gameAnchorPane, Double snakeSize, Rectangle snakeHead, ArrayList<Rectangle> snakeBody ){
        m_gameAnchorPane = gameAnchorPane;
        m_snakeBody = snakeBody;
        m_snakeHead = snakeHead;
        m_snakeSize = snakeSize;
    }

    /**
     * Method checks that the snake head object is within the game scenes dimensions.
     * This method sets the xOut variable to be the result of if the snake head x coordinates
     * are bigger than -250 or 600. If xOut is true this means the snake head is out of bounds
     * and true is returned.
     *
     * @param snakeHeadX variable containing x position of the snake head object
     * @param snakeHeadY variable containing y position of the snake head object
     * @return false or true if either xOut or yOut are true
     */
    public boolean OutOfBounds(double snakeHeadX, double snakeHeadY){
        boolean xOut = (snakeHeadX < -250 || snakeHeadX > 600);
        boolean yOut = (snakeHeadY < -250 || snakeHeadY > 290);
        return xOut || yOut;
    }

    /**
     * Method checks if the snake head has intersected with a snake body object.
     * The method stores the size of the headPoints array in the size variable and checks
     * that it is bigger than 2. It then performs a for loop through the headPoints array list,
     * checking that none of the elements have the same coordinates.
     *
     * @param headPoints list storing all the head positions of the snake
     * @param snakeBody list storing all the rectangle snakeTail objects added
     * @return false or true if the snake head object intersects with a body part
     */
    public boolean BodyHit(List<Position> headPoints, ArrayList<Rectangle> snakeBody){
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

    /**
     * Method adds a snake tail to the snake body and scene.
     * The method first checks the size of the snakeBody array list.
     * If the size is 1 it runs the first process which adds the first snakeTail object to the snakeBody.
     * If the size of the snakeBody is not one the second process is run, getting the position of the
     * last snake tail object in the snakebody array list and adding another tail object to follow it.
     */
    public void AddSnakeTail(){
        if (m_snakeBody.size() == 1) {
            Rectangle snakeFirstTail = new Rectangle(m_snakeHead.getX() - m_snakeSize, m_snakeHead.getY(), m_snakeSize, m_snakeSize);
            m_snakeBody.add(snakeFirstTail);
            Image snakeTailImage = Theme.GenerateSnakeTailImage(gameTheme);
            snakeFirstTail.setFill(new ImagePattern(snakeTailImage));
            m_gameAnchorPane.getChildren().add(snakeFirstTail);
        }

        else{
            double snakeTailX = m_snakeBody.get(1).getX() + m_snakeHead.getX() + m_snakeSize;
            double snakeTailY = m_snakeBody.get(1).getY() + m_snakeHead.getY();
            Rectangle snakeTail = new Rectangle(snakeTailX, snakeTailY, m_snakeSize, m_snakeSize);
            m_snakeBody.add(snakeTail);
            Image snakeTailImage = Theme.GenerateSnakeTailImage(gameTheme);
            snakeTail.setFill(new ImagePattern(snakeTailImage));
            m_gameAnchorPane.getChildren().add(snakeTail);
        }
    }

    /**
     * Method removes a tail rectangle from the snake body and scene.
     * The method stores the size of the snake body array in the snakeBodySize variable.
     * This variable is then used when creating a new rectangle object snakeTailRemove by setting
     * it to be the last tail in the snakeBody.
     * The smakeTailRemove rectangle then removed from the game scenes anchor pane.
     */
    public void RemoveSnakeTail(){
        int snakeBodySize = m_snakeBody.size();
        Rectangle snakeTailRemove = m_snakeBody.get(snakeBodySize-1);
        m_gameAnchorPane.getChildren().remove(snakeTailRemove);
    }
}
