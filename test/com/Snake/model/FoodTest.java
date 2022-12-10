package com.Snake.model;

import javafx.scene.layout.AnchorPane;
import javafx.scene.shape.Rectangle;
import org.junit.Test;

import java.util.ArrayList;

import static org.junit.Assert.*;

public class FoodTest {
    AnchorPane m_gameAnchorPane = new AnchorPane();
    Rectangle m_snakeHead = new Rectangle(250, 250, 25, 25);
    ArrayList<Rectangle> m_snakeBody = new ArrayList<>();
    ArrayList<Position> m_headPoints = new ArrayList<>();
    int m_themeNumber = 1;
    Food m_FoodClass = new Food(m_gameAnchorPane, m_snakeHead, m_snakeBody, m_headPoints, m_themeNumber);

    @Test
    public void generateFood() {
    }

    @Test
    public void eatenFoodTrue() {
        m_FoodClass.m_foodObject = new Rectangle(250, 250, 25, 25);
        assertTrue(m_FoodClass.EatenFood());
    }

    @Test
    public void eatenFoodFalse() {
        m_FoodClass.m_foodObject = new Rectangle(400, 250, 25, 25);
        assertFalse(m_FoodClass.EatenFood());
    }

    @Test
    public void removeFood() {
    }
}