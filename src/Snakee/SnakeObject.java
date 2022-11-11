package Snakee;

import java.awt.*;

/**
 * @Project StartScene
 * @Description Snake Object
 * @Author Ainsley Lee
 * @version 2
 */


public abstract class SnakeObject
{
    int x;
    int y;
    Image i;
    int w;
    int h;

    public boolean l;


    public abstract void draw(Graphics g);

    public Rectangle getRectangle()
    {
        return new Rectangle(x, y, w, h);
    }
}