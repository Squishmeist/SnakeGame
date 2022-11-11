module COMP2013.Coursework {
    requires java.desktop;
    requires jlayer;
    opens Snakee;
    opens Snakee.images;
    opens Snakee.sounds;
    opens Snakee.SourceCode;

    requires javafx.fxml;
    requires javafx.controls;
    requires javafx.swing;
}