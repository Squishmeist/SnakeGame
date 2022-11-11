package Snakee.sourcecode;

import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.*;

/**
 * 
 * @Project StartScene
 * @Description Load the game and refresh it constantly
 * @Author Ainsley Lee
 * @version 4
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
		loadFrame();
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
}
