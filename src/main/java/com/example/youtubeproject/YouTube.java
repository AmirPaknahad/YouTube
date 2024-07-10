package com.example.youtubeproject;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.*;
import java.net.Socket;
import java.sql.SQLException;
import java.util.UUID;

public class YouTube extends Application {
    public static Socket client;
    public static BufferedReader reader;

    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(YouTube.class.getResource("YouTube-view.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 1080, 720);
        stage.setTitle("You Tube");
        stage.setScene(scene);
        stage.show();
    }
    public static void main(String[] args) throws IOException, SQLException {
//        DatabaseManager.likeVideo(UUID.fromString("aa39a19d-67d1-4ae3-bb79-fffdfee70e9c"), UUID.fromString("ba532ae3-4f26-47a4-9eee-ea947c30ccdd"), 1);
        client = new Socket("127.0.0.1", 5000);
        launch();
    }
}