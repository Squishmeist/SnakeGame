package Snakee;

import java.nio.file.Paths;

import javafx.scene.media.MediaPlayer;
import javafx.scene.media.Media;

public class Music {
    Media media;
    public static MediaPlayer player;


    /**
     * creates new player object to access BufferedInputStream and play music
     */
    public static void Music() {
        String s = "src/Snakee/sounds/frogger.mp3";

        Media h = new Media(Paths.get(s).toUri().toString());
        player = new MediaPlayer(h);
        player.play();
    }

}