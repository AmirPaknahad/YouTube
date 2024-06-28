module com.example.youtubeproject {
    requires javafx.controls;
    requires javafx.fxml;

    requires org.kordamp.bootstrapfx.core;

    opens com.example.youtubeproject to javafx.fxml;
    exports com.example.youtubeproject;
}