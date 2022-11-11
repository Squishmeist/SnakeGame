package Snakee;

import Snakee.SourceCode.*;

import java.awt.*;
import java.awt.event.KeyEvent;

public class GameStart extends GameFrame {
    private static final long serialVersionUID = -3641221053272056036L;

    public MySnake mySnake = new MySnake(100, 100);// x , y
    public Food food = new Food();
    public Image background = ImageUtil.images.get("UI-background");
    public Image fail = GameUtil.resizeImage(ImageUtil.images.get("end-scene"), 870, 560);

    @Override
    public void keyPressed(KeyEvent e)
    {
        super.keyPressed(e);
        mySnake.keyPressed(e);
    }

    @Override
    public void paint(Graphics g)
    {
        super.paint(g);
        g.drawImage(background, 0, 0, null);

        // Determine the state of the game.
        if (mySnake.l)
        {
            mySnake.draw(g);
            if (food.l)
            {
                food.draw(g);
                food.eaten(mySnake);
            } else
            {
                food = new Food();
            }
        } else
        {
            g.drawImage(fail, 0, 0, null);
        }
        drawScore(g);
    }

    public void drawScore(Graphics g)
    {
        g.setFont(new Font(Font.SANS_SERIF, Font.BOLD, 30));
        g.setColor(Color.MAGENTA);
        g.drawString("SCORE : " + mySnake.score, 20, 80);
    }

    public static void main(String[] args)
    {
        new Play().loadFrame();
        MusicPlayer.getMusicPlay("src/Snakee/sounds/frogger.mp3");
    }
}
