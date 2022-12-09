package com.Snake.model;

import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Rectangle;

import java.util.ArrayList;
import java.util.List;

import static com.Snake.controller.GameSceneController.themeNumber;

public class Snake {

    private AnchorPane m_gameAnchorPane;
    private ArrayList<Rectangle> m_snakeBody;
    private Rectangle m_snakeHead;
    private Double m_snakeSize;


    public Snake(AnchorPane gameAnchorPane, ArrayList<Rectangle> snakeBody, Rectangle snakeHead, Double snakeSize){
        m_gameAnchorPane = gameAnchorPane;
        m_snakeBody = snakeBody;
        m_snakeHead = snakeHead;
        m_snakeSize = snakeSize;
    }

    //Check if snake is outOfBounds
    public boolean OutOfBounds(double snakeHeadX, double snakeHeadY){
        boolean xOut = (snakeHeadX < -250 || snakeHeadX > 600);
        boolean yOut = (snakeHeadY < -250 || snakeHeadY > 290);
        return xOut || yOut;
    }

    //Check if snake hits itself
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

    public void AddSnakeTail(){
        if (m_snakeBody.size() == 1) {
            Rectangle snakeFirstTail = new Rectangle(m_snakeHead.getX() - m_snakeSize, m_snakeHead.getY(), m_snakeSize, m_snakeSize);
            m_snakeBody.add(snakeFirstTail);
            Image snakeTailImage = Theme.GenerateSnakeTailImage(themeNumber);
            snakeFirstTail.setFill(new ImagePattern(snakeTailImage));
            m_gameAnchorPane.getChildren().add(snakeFirstTail);
        }

        else{
            double snakeTailX = m_snakeBody.get(1).getX() + m_snakeHead.getX() + m_snakeSize;
            double snakeTailY = m_snakeBody.get(1).getY() + m_snakeHead.getY();
            Rectangle snakeTail = new Rectangle(snakeTailX, snakeTailY, m_snakeSize, m_snakeSize);
            m_snakeBody.add(snakeTail);
            Image snakeTailImage = Theme.GenerateSnakeTailImage(themeNumber);
            snakeTail.setFill(new ImagePattern(snakeTailImage));
            m_gameAnchorPane.getChildren().add(snakeTail);
        }
    }

    public void RemoveSnakeTail(){
        int snakeBodySize = m_snakeBody.size();
        Rectangle snakeTailRemove = m_snakeBody.get(snakeBodySize-1);
        m_gameAnchorPane.getChildren().remove(snakeTailRemove);
    }
}
