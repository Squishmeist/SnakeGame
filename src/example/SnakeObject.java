package example;

import java.awt.*;

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