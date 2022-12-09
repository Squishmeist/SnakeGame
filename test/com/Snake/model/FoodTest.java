package com.Snake.model;

import javafx.scene.layout.AnchorPane;
import javafx.scene.shape.Rectangle;
import org.junit.Test;

import java.util.ArrayList;

import static org.junit.Assert.assertEquals;
import com.Snake.model.Theme;

public class FoodTest {

    AnchorPane m_gameAnchorPane = new AnchorPane();
    Rectangle m_snakeHead = new Rectangle(250, 250, 25, 25);
    ArrayList<Rectangle> m_snakeBody = new ArrayList<>();
    ArrayList<Position> m_headPoints = new ArrayList<>();
    int m_themeNumber = 1;
    @Test
    public void generateFood() {
    }

    @Test
    public void eatenFoodTrue() {
        Food m_food = new Food(m_gameAnchorPane, m_snakeHead, m_snakeBody, m_headPoints, m_themeNumber);
        m_food.m_foodObject = new Rectangle(250, 250, 25, 25);
        assertEquals(true, m_food.EatenFood());
    }

    @Test
    public void eatenFoodFalse() {
        Food m_food = new Food(m_gameAnchorPane, m_snakeHead, m_snakeBody, m_headPoints, m_themeNumber);
        m_food.m_foodObject = new Rectangle(400, 250, 25, 25);
        assertEquals(false, m_food.EatenFood());
    }

    @Test
    public void removeFood() {
    }
}