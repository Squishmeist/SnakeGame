package com.Snake.model;

import java.nio.file.Paths;

import javafx.scene.media.MediaPlayer;
import javafx.scene.media.Media;

/**
 * @Project COMP2013-Coursework
 * @Description Music Class
 * @Author Ainsley Lee
 */

public class Music {
    /**
     * Method plays the file passed in when called.
     *
     * @param filename variable specifying path to desired sound to play
     */
    public void MusicPlayer(String filename) {
        Media media = new Media(Paths.get(filename).toUri().toString());
        MediaPlayer player = new MediaPlayer(media);
        player.play();
    }
}