package com.example.youtubeproject;

import javafx.fxml.FXML;
import javafx.scene.chart.PieChart;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.media.MediaView;
import javafx.scene.text.Text;
import javafx.stage.FileChooser;

import java.io.File;
import java.sql.SQLException;
import java.util.UUID;

public class mediaPlayerController {
    Video video2;
    @FXML
    private MediaView video;
    @FXML
    private MediaPlayer videoPlayer;
    @FXML
    private ImageView profilePic;
    @FXML
    private Button playButton;
    @FXML
    private Button pauseButton;
    @FXML
    private Button likeButton;
    @FXML
    private Button disLikeButton;
    @FXML
    private Text videoCaption;
    @FXML
    private Label channelName;
    @FXML
    private Label videoName;
    @FXML
    private Label channelSubscribers;
    @FXML
    private Label likeNumber;
    @FXML
    private Label disLikeNumber;
    @FXML
    private Button newComment;
    @FXML
    private TextField commentText;



    @FXML
    protected void setData(Video video) throws SQLException {
        video2 = video;
        selectMedia();
        profilePic = new ImageView(DatabaseManager.getAccount(video.getAccountID()).getProfileImageAddress());
        videoCaption.setText(video.getCaption());
        channelName.setText(DatabaseManager.getAccount(video.getAccountID()).getUserName());
        videoName.setText(video.getVideoName());
        channelSubscribers.setText(String.valueOf(DatabaseManager.subscriberNumber(video.getAccountID())) + " subscribers");
    }
    @FXML
    protected void playButtonClick() {
        playButton.setVisible(false);
        pauseButton.setVisible(true);
        videoPlayer.play();
    }
    @FXML
    protected void pauseButtonClick() {
        pauseButton.setVisible(false);
        playButton.setVisible(true);
        videoPlayer.pause();
    }
    @FXML
    protected void likeButtonClick() throws SQLException {
        if (YouTubeController.isLogin)
         DatabaseManager.likeVideo(video2.getVideoID(), YouTubeController.loginAccount.getAccountID(), 1);
    }
    @FXML
    protected void disLikeButtonClick() throws SQLException {
        if (YouTubeController.isLogin)
            DatabaseManager.likeVideo(video2.getVideoID(), YouTubeController.loginAccount.getAccountID(), -1);
    }

    @FXML
    protected void selectMedia() {
//        String url = video2.getVideoAddress();
        String url = "file:/Users/ermiababaie/Documents/project/youTubeProject/src/main/resources/youTubeSample.mp4";
        Media media = new Media(url);
        videoPlayer = new MediaPlayer(media);
        video.setMediaPlayer(videoPlayer);
    }
    @FXML
    protected void newCommentClick() {
        if (!YouTubeController.isLogin)
            return;
        String comment = commentText.getText();
        String comment2 = "";
        int i = 0;
        for (; i < comment.length(); i++) {
            if (comment.charAt(i) != ' ')
                break;
        }
        for (; i < comment.length(); i++) {
            comment2 += comment.charAt(i);
        }
        if (comment2 != "") {
            DatabaseManager.newComment(UUID.randomUUID(), YouTubeController.loginAccount.getAccountID(), video2.getVideoID(), comment2, 0, 0);
        }
    }
}
