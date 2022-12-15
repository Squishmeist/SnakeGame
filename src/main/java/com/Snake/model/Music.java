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
    MediaPlayer player;


    /**
     * Method plays the file passed in when called.
     * This method also repeats the sound playing if true is passed,
     * setting the CycleCount to be indefinite.
     *
     * @param filename variable specifying path to desired sound to play
     */
    public Music(String filename, boolean repeat) {
        Media media = new Media(Paths.get(filename).toUri().toString());
        player = new MediaPlayer(media);
        if(repeat){
            player.setCycleCount(MediaPlayer.INDEFINITE);
        }
        player.play();
    }

    /**
     * Method stops the music player.
     */
    public void MusicStop(){
        player.stop();
    }
}