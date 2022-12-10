package com.Snake.model;

import javafx.scene.layout.AnchorPane;
import javafx.scene.shape.Rectangle;
import org.junit.Test;

import java.util.ArrayList;

import static org.junit.Assert.*;

public class ObstacleTest {
    AnchorPane m_gameAnchorPane = new AnchorPane();
    Rectangle m_snakeHead = new Rectangle(250, 250, 25, 25);
    ArrayList<Rectangle> m_snakeBody = new ArrayList<>();
    ArrayList<Position> m_headPoints = new ArrayList<>();
    int m_themeNumber = 1;
    Obstacle m_ObstacleClass = new Obstacle(m_gameAnchorPane, m_snakeHead, m_snakeBody, m_headPoints, m_themeNumber);

    @Test
    public void generateObstacle() {
    }

    @Test
    public void hitObstacleTrue() {
        m_ObstacleClass.m_obstacleObject = new Rectangle(250, 250, 40, 40);
        assertTrue(m_ObstacleClass.HitObstacle());
    }
    @Test
    public void hitObstacleFalse() {
        m_ObstacleClass.m_obstacleObject = new Rectangle(400, 250, 40, 40);
        assertFalse(m_ObstacleClass.HitObstacle());
    }



    @Test
    public void removeObstacle() {
    }
}