package example;

import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.image.BufferedImage;
import java.util.LinkedList;
import java.util.List;

public class MySnake extends MyFrame.SnakeObject implements movable
{
    // The game variable.
    private int speed_XY;
    private static int length;
    private int num; // ?
    public static int score = 0;

    private static final BufferedImage IMG_SNAKE_HEAD = (BufferedImage) ImageUtil.images.get("snake-head-right");

    public static List<Point> bodyPoints = new LinkedList<>();

    private static BufferedImage newImgSnakeHead;
    boolean up, down, left, right = true;

    public MySnake(int x, int y)
    {
        this.l = true;
        this.x = x;
        this.y = y;
        this.i = ImageUtil.images.get("snake-body");
        this.w = i.getWidth(null);
        this.h = i.getHeight(null);

        this.speed_XY = 5;
        this.length = 1;

        /*
         * Attention : ?
         */
        this.num = w / speed_XY;
        newImgSnakeHead = IMG_SNAKE_HEAD;

    }

    /**
     * @return length
     */
    public int getLength()
    {
        return length;
    }

    /**
     * @param length
     */
    public static void changeLength(int length)
    {
        MySnake.length = length;
    }



    public void keyPressed(KeyEvent e)
    {

        // check the key
        switch (e.getKeyCode())
        {
            case KeyEvent.VK_UP:
                if (!down)
                {
                    up = true;
                    down = false;
                    left = false;
                    right = false;

                    newImgSnakeHead = (BufferedImage) GameUtil.rotateImage(IMG_SNAKE_HEAD, -90);
                }
                break;

            case KeyEvent.VK_DOWN:
                if (!up)
                {
                    up = false;
                    down = true;
                    left = false;
                    right = false;

                    newImgSnakeHead = (BufferedImage) GameUtil.rotateImage(IMG_SNAKE_HEAD, 90);
                }
                break;

            case KeyEvent.VK_LEFT:
                if (!right)
                {
                    up = false;
                    down = false;
                    left = true;
                    right = false;

                    newImgSnakeHead = (BufferedImage) GameUtil.rotateImage(IMG_SNAKE_HEAD, -180);

                }
                break;

            case KeyEvent.VK_RIGHT:
                if (!left)
                {
                    up = false;
                    down = false;
                    left = false;
                    right = true;

                    newImgSnakeHead = IMG_SNAKE_HEAD;
                }

            default:
                break;
        }
    }


    /**
     * checks which key is pressed
     * 		if up key is pressed
     *			sets y -= XY_speed(5) spaces
     *		if down key is pressed
     *			sets y += XY_speed(5) spaces
     *		if left key is pressed
     *      	sets x -= XY_speed(5) spaces
     *      if right key is pressed
     *         	sets x += XY_speed(5) spaces
     */
    public void move()
    {
        // make the swarm move
        if (up)
        {
            y -= speed_XY;
        } else if (down)
        {
            y += speed_XY;
        } else if (left)
        {
            x -= speed_XY;
        } else if (right)
        {
            x += speed_XY;
        }

    }

    /**
     * @param g
     *  calls outofBounds method - checks snake is within predefined window size
     *  calls eatBody method - checks if snake body is increasing in size
     *
     *  adds new point x and y to bodyPoints
     *  	if bodyPoints size is equal to current length + 1 * (width / speed)
     *		removes index 0 if snake reaches max size
     *		draws snake head
     *
     *  calls drawBody method in MyFrame Class - draws snake body
     *
     *  calls move method - checks which key is pressed
     */
    @Override
    public void draw(Graphics g)
    {
        outofBounds();
        eatBody();

        bodyPoints.add(new Point(x, y));

        if (bodyPoints.size() == (this.length + 1) * num)
        {
            bodyPoints.remove(0);
        }
        g.drawImage(newImgSnakeHead, x, y, null);
        drawBody(g);

        move();
    }

    /**
     * checks same value but not same object?
     */
    public void eatBody()
    {
        for (Point point : bodyPoints)
        {
            for (Point point2 : bodyPoints)
            {
                if (point.equals(point2) && point != point2)
                {
                    this.l = false;
                }
            }
        }
    }

    /**
     * @param g
     *
     * gets length of bodyPoints - 1 - (width/speed)
     * 		loops length number of times
     * 		gets x y of i point
     * 		draws snake body
     */
    public void drawBody(Graphics g)
    {
        int length = bodyPoints.size() - 1 - num;

        for (int i = length; i >= num; i -= num)
        {
            Point point = bodyPoints.get(i);
            g.drawImage(this.i, point.x, point.y, null);
        }
    }

    /**
     * if snake is outofBounds sets l to false
     * ends game
     */
    private void outofBounds()
    {
        boolean xOut = (x <= 0 || x >= (870 - w));
        boolean yOut = (y <= 40 || y >= (560 - h));
        if (xOut || yOut)
        {
            l = false;
        }
    }
}
