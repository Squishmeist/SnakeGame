package Snakee;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.KeyEvent;

/**
 * @Project StartScreen
 * @Description Play the game
 * @Author Ainsley Lee
 * @version 4
 */ 

public class Play extends MyFrame
{

	private static final long serialVersionUID = -3641221053272056036L;

	public MySnake mySnake = new MySnake(100, 100);// x , y
	public Food food = new Food();

	public Image background = ImageUtil.images.get("UI-background");
	//public Image fail = ImageUtil.images.get("end-scene");

	public Image fail = GameUtil.resizeImage(ImageUtil.images.get("end-scene"), 870, 560);

	@Override
	public void keyPressed(KeyEvent e)
	{
		super.keyPressed(e);
		mySnake.keyPressed(e);
	}

	/**
	 * @param g the <code>Graphics</code> context in which to paint
	 *
	 * displays background image on window
	 * determines state of the game
	 *
	 * snake.l is true
	 *
	 * mySnake.draw - called draw method in MyFrame class
	 * 	 	snake.l = true
	 *
	 * 	        calls draw method in MyFrame - draws mySnake
	 *
	 * 	        if food.l = true
	 * 	        	calls draw method in Food class
	 * 	        	calls eaten method in Food class
	 *
	 * 	    	else food.l = false
	 * 	        	creates new object
	 *
	 *		snake.l = false
	 *	        outputs fail image to screen
	 *
	 *	calls drawScore method in Play class - sets font, colour and displays score on screen in x y coordinates
	 */
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

	/**
	 * @param g
	 *  sets font
	 * 	sets colour
	 * 	displays mySnake.score variable in x y coordinates on screen
	 */
	public void drawScore(Graphics g)
	{
		String playerName = StartScreen.getPlayerName();
		g.setFont(new Font(Font.SANS_SERIF, Font.BOLD, 30));
		g.setColor(Color.MAGENTA);
		g.drawString("PLAYER : " + playerName, 20, 40);
		g.drawString("SCORE : " + mySnake.score, 20, 80);
	}

	/**
	 * @param args
	 * creates new play object using loadFrame method in MyFrame class
	 * runs getMusicPlay method from MusicPlayer class passing filename "frogger.mp3"
	 */
	public static void main(String[] args)
	{
		new Play().loadFrame();
		MusicPlayer.getMusicPlay("src/Snakee/sounds/frogger.mp3");
	}

/*	
	public static void main(String[] args)
	{
		JFrame frame = new JFrame();
		// frame.setSize(400,600);
		frame.setBounds(450, 200, 920, 600);
		// frame.setResizable(false);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		SnakePanel panel = new SnakePanel();
		frame.add(panel);

		frame.setVisible(true);

		// Play the background music.
		MusicPlayer.getMusicPlay("resource\\music\\background.mp3");
	} 
*/
}
