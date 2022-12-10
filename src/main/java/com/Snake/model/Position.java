package com.Snake.model;

/**
 * @Project COMP2013-Coursework
 * @Description Position Class
 * @Author Ainsley Lee
 */

public class Position {
    private final double x;
    private final double y;

    /**
     * Method stores parameter to local variables.
     *
     * @param x variable stores x coordinate
     * @param y variable stores y coordinate
     */
    public Position(double x, double y){
        this.x = x;
        this.y = y;
    }

    /**
     * Method returns x.
     *
     * @return x variable that stores x coordinate
     */
    public double getX(){
        return x;
    }

    /**
     * Method returns y.
     *
     * @return y variable that stores y coordinate
     */
    public double getY(){
        return y;
    }
}
