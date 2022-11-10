package Snakee;

import java.io.BufferedInputStream;
import java.io.FileInputStream;

import javazoom.jl.player.Player;

/**
 * @Project Snakee
 * @Description Music Player
 * @Author Ainsley Lee
 * @version 2
 */


public class MusicPlayer extends Thread
{
	private String filename;
	public Player player;

	public MusicPlayer(String filename)
	{
		this.filename = filename;
	}

	/**
	 * creates new player object to access BufferedInputStream and play music
	 */
	public void play()
	{
		new Thread()
		{
			@Override
			public void run()
			{
				super.run();
				try
				{
					//BufferedInputStream buffer = new BufferedInputStream(new FileInputStream(filename));
					player = new Player(new BufferedInputStream(new FileInputStream(filename)));
					player.play();

				} catch (Exception e)
				{
					System.out.println(e);
				}
			}
		}.start();
	}

	/**
	 * @param filename
	 * creates new object musicPlayer passing filename
	 * calls play method from MusicPlayer class
	 */
	public static void getMusicPlay(String filename)
	{
		MusicPlayer musicPlayer = new MusicPlayer(filename);
		musicPlayer.play();
	}
}
