package example;

import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.image.BufferedImage;
import java.util.LinkedList;
import java.util.List;

import javax.swing.JFrame;
import javax.swing.JPanel;

/**
 * 
 * @Project Snakee
 * @Description Load the game and refresh it constantly
 * @Author Ainsley Lee
 * @version 1
 */ 


public class MyFrame extends JPanel implements KeyListener
{
	private static final long serialVersionUID = -3149926831770554380L;

	public JFrame jFrame = new JFrame();

	/**
	 * sets icon images to file - images/snake-logo.png
	 */
	public MyFrame()
	{
		jFrame.setIconImage(Toolkit.getDefaultToolkit().getImage(MyFrame.class.getResource("images/snake-logo.png")));
	}

	/**
	 * sets title name that appears at top of the window
	 * sets size of game window
	 * sets location of window to default position
	 * adds window listener interface to define events for opening and closing the window
	 * set visible shows window on screen
	 * new thread begins execution
	 */
	public void loadFrame()
	{
		/*
		 * Prevent the image from flashing.
		 */
		this.setDoubleBuffered(true);
		jFrame.add(this);
		jFrame.addKeyListener(this);

		jFrame.setTitle("Snakee Yipee");
		jFrame.setSize(870, 560);
		jFrame.setLocationRelativeTo(null);
		jFrame.addWindowListener(new WindowAdapter()// loka
		{
			@Override
			public void windowClosing(WindowEvent e)
			{
				super.windowClosing(e);
				System.exit(0);
			}
		});
		jFrame.setVisible(true);

		new MyThread().start();
	}

	/**
	 * runs loop while true
	 * repaint refreshes window
	 * sleep ceases execution for 30 milliseconds
	 *
	 * every 30 milliseconds repaints screen to show snake movement
	 * repaint function in Play class (paint method)
	 *
	 * catch (Exception) in other method and ends the program
	 */
	class MyThread extends Thread
	{
		@Override
		public void run()
		{
			super.run();
			while (true)
			{
				repaint();
				try
				{
					sleep(30);
				} catch (Exception e)
				{
					e.printStackTrace();
				}
			}
		}
	}

	@Override
	public void keyTyped(KeyEvent e)
	{
		// TODO Auto-generated method stub

	}

	@Override
	public void keyPressed(KeyEvent e)
	{
		// TODO Auto-generated method stub

	}

	@Override
	public void keyReleased(KeyEvent e)
	{
		// TODO Auto-generated method stub

	}

	/**
	 *
	 */
	public static class MySnake extends SnakeObject implements movable
	{
		// The game variable.
		private int speed_XY;
		private int length;
		private int num; // ?
		public int score = 0;

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
		public void changeLength(int length)
		{
			this.length = length;
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

	public abstract static class SnakeObject
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
}
