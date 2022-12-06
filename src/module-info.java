module COMP2013.Coursework {
    requires java.desktop;
    opens Snakee;
    opens Snakee.resources.images;
    opens Snakee.resources.images.snake;
    opens Snakee.resources.images.pacman;
    opens Snakee.resources.images.spaceinvaders;
    opens Snakee.resources.sounds;

    requires javafx.fxml;
    requires javafx.controls;
    requires javafx.swing;
    requires javafx.media;
}