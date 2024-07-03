module com.example.youtubeproject {
    requires javafx.controls;
    requires javafx.fxml;

    requires org.kordamp.bootstrapfx.core;
    requires java.sql;

    opens com.example.youtubeproject to javafx.fxml;
    exports com.example.youtubeproject;
}