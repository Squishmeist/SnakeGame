package com.Snake.model;

import javafx.scene.layout.AnchorPane;
import javafx.scene.shape.Rectangle;
import org.junit.Test;

import java.util.ArrayList;

import static org.junit.Assert.*;

public class SnakeTest {

    AnchorPane m_gameAnchorPane = new AnchorPane();
    ArrayList<Rectangle> m_snakeBody = new ArrayList<>();
    ArrayList<Position> m_headPoints = new ArrayList<>();
    double m_snakeSize = 25.;
    Rectangle m_snakeHead = new Rectangle(250, 250, 25, 25);
    double m_snakeHeadX;
    double m_snakeHeadY;
    Snake m_SnakeClass = new Snake(m_gameAnchorPane, m_snakeSize, m_snakeHead, m_snakeBody);

    @Test
    public void outOfBoundsTrueHighXY() {
        m_snakeHeadX = 601;
        m_snakeHeadY = 291;
        assertTrue(m_SnakeClass.OutOfBounds(m_snakeHeadX, m_snakeHeadY));
    }

    @Test
    public void outOfBoundsTrueLowXY() {
        m_snakeHeadX = -251;
        m_snakeHeadY = -251;
        assertTrue(m_SnakeClass.OutOfBounds(m_snakeHeadX, m_snakeHeadY));
    }

    @Test
    public void outOfBoundsFalseHighXY() {
        m_snakeHeadX = 575;
        m_snakeHeadY = 265;
        assertFalse(m_SnakeClass.OutOfBounds(m_snakeHeadX, m_snakeHeadY));
    }

    @Test
    public void outOfBoundsFalseLowXY() {
        m_snakeHeadX = -225;
        m_snakeHeadY = -225;
        assertFalse(m_SnakeClass.OutOfBounds(m_snakeHeadX, m_snakeHeadY));
    }

    @Test
    public void bodyHitFalse() {
        Rectangle snakeFirstTail = new Rectangle(m_snakeHead.getX() - m_snakeSize, m_snakeHead.getY(), m_snakeSize, m_snakeSize);
        m_snakeBody.add(snakeFirstTail);
        m_headPoints.add(new Position(m_snakeHead.getX() + m_snakeHeadX, m_snakeHead.getY() + m_snakeHeadY));
        assertFalse(m_SnakeClass.BodyHit(m_headPoints, m_snakeBody));
    }


    @Test
    public void addSnakeTail() {
    }

    @Test
    public void removeSnakeTail() {
    }
}