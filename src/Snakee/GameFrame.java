package Snakee;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

/**
 * 
 * @Project StartScene
 * @Description Load the game and refresh it constantly
 * @Author Ainsley Lee
 * @version 4
 */ 


public class GameFrame extends JPanel implements KeyListener
{
	private static final long serialVersionUID = -3149926831770554380L;

	public JFrame jFrame = new JFrame();

	/**
	 * sets icon images to file - images/snake-logo.png
	 */
	public GameFrame()
	{
		jFrame.setIconImage(Toolkit.getDefaultToolkit().getImage(GameFrame.class.getResource("images/snake-logo.png")));
		loadFrame();
	}

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
