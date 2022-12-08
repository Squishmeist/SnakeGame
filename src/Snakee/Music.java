package Snakee;

import java.nio.file.Paths;

import javafx.scene.media.MediaPlayer;
import javafx.scene.media.Media;

public class Music {
    public void MusicPlayer(String filename) {
        Media media = new Media(Paths.get(filename).toUri().toString());
        MediaPlayer player = new MediaPlayer(media);
        player.play();
    }
}