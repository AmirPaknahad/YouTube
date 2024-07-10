module com.example.youtubeproject {
    requires javafx.controls;
    requires javafx.fxml;

    requires javafx.media;

    requires org.kordamp.bootstrapfx.core;
    requires java.sql;
    requires java.desktop;

    opens com.example.youtubeproject to javafx.fxml;
    exports com.example.youtubeproject;
}