package com.example.youtubeproject;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.text.Text;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.sql.SQLException;
import java.util.UUID;

public class videoController {
    @FXML
    private ImageView coverImage;
    @FXML
    private ImageView profileImage;
    @FXML
    private Label videosName;
    @FXML
    private Label channel;
    @FXML
    private Label view;

    public void setDate(Video video) throws SQLException {
        coverImage = new ImageView(video.getVideoAddress());
        profileImage = new ImageView(DatabaseManager.getAccount(video.getAccountID()).getProfileImageAddress());
        videosName.setText(video.getVideoName());
        channel.setText(DatabaseManager.getAccount(video.getAccountID()).getUserName());
        view.setText(String.valueOf(DatabaseManager.numberOfView(video.getAccountID())));

    }

}
