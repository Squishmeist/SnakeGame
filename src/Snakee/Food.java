package Snakee;

import java.awt.Graphics;
import java.util.Random;

/**
 * @Project StartScreen
 * @Description Food
 * @Author Ainsley Lee
 * @version 3
 */


public class Food extends SnakeObject
{
	private static final long serialVersionUID = -3641221053272056036L;

	public Food()	{
		this.l = true;

		this.i = ImageUtil.images.get(String.valueOf(new Random().nextInt(10)));

		this.w = i.getWidth(null);
		this.h = i.getHeight(null);

		this.x = (int) (Math.random() * (870 - w + 10));
		this.y = (int) (Math.random() * (560 - h - 40));
	}

	/**
	 * @param mySnake
	 *
	 * calls getRectangle method in MyFrame class
	 * 		returns x y w h variables for mySnake
	 * 		returns x y w h variables for food
	 *
	 * checks that the variables for mySnake and food are the same
	 * and that l is true (meaning food exists)
	 * and mySnake.l is true (meaning snake exists)
	 *
	 * l = false removes the food image from frame
	 * calls changeLength method in MyFrame class
	 * 		calls getLength method in MyFrame class
	 * 			returns length
	 * 		increments length by 1
	 * 		updates mySnake length to updated length
	 *
	 * 	mySnake.score increments score variable by 521
	 */
	public void eaten(MySnake mySnake)	{

		if (mySnake.getRectangle().intersects(this.getRectangle()) && l && mySnake.l)		{
			this.l = false;
			MySnake.changeLength(mySnake.getLength() + 1);
			MySnake.score += 521;
		}
	}

	/**
	 * @param g
	 *
	 * i is random image from collection of food images (0 to 10)
	 * x y are random coordinates on the frame
	 *
	 * draws food image based on above variables
	 */
	@Override
	public void draw(Graphics g)
	{
		g.drawImage(i, x, y, null);
	}
}
