package com.example.youtubeproject;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.chart.PieChart;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.Slider;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.media.MediaView;
import javafx.scene.text.Text;
import javafx.stage.FileChooser;
import javafx.util.Duration;

import java.awt.event.MouseEvent;
import java.io.File;
import java.io.IOException;
import java.sql.SQLException;
import java.util.List;
import java.util.UUID;

public class mediaPlayerController {
    Video video2;
    @FXML
    private Media media;
    @FXML
    private MediaView video;
    @FXML
    public static MediaPlayer videoPlayer;
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
    private Slider videoSlider;
    @FXML
    private Slider volumeSlider;
    @FXML
    private Slider speedSlider;
    @FXML
    private Button subscribe;


    @FXML
    protected void setData(Video video) throws SQLException {
        if (YouTubeController.loginAccount.getAccountID().equals(video.getAccountID()))
            subscribe.setVisible(false);
        else if (!DatabaseManager.subscribeStatus(YouTubeController.loginAccount.getAccountID(), video.getAccountID())) {
            subscribe.setText("Subscribe");
        }

        video2 = video;
        selectMedia();
        if (!DatabaseManager.getAccount(video.getAccountID()).getProfileImageAddress().equals(""))
            profilePic.setImage(new Image(DatabaseManager.getAccount(video.getAccountID()).getProfileImageAddress()));
        else
            profilePic.setImage(new Image("file:/Users/ermiababaie/Documents/project/youTubeProject/src/main/resources/profile_icon.jpg"));
        videoCaption.setText(video.getCaption());
        channelName.setText(DatabaseManager.getAccount(video.getAccountID()).getUserName());
        videoName.setText(video.getVideoName());
        channelSubscribers.setText(String.valueOf(DatabaseManager.subscriberNumber(video.getAccountID())) + " subscribers");
        int likeCnt = 0;
        int disLikeCnt = 0;
        List<LikeVideo> likeVideos = DatabaseManager.getVideoLikes(video.getVideoID());
        for (LikeVideo likeVideo: likeVideos){
            if (likeVideo.getLike() == 1)
                likeCnt++;
            if (likeVideo.getLike() == -1)
                disLikeCnt++;
        }
        likeNumber.setText(String.valueOf(likeCnt));
        disLikeNumber.setText(String.valueOf(disLikeCnt));

    }
    @FXML
    protected void playButtonClick() {
        playButton.setVisible(false);
        pauseButton.setVisible(true);
        videoPlayer.play();
        videoPlayer.currentTimeProperty().addListener(((observableValue,oldValue, newValue) -> {
            videoSlider.setValue(newValue.toSeconds());
        }));

        videoPlayer.setOnReady(() -> {
            Duration totalduration = media.getDuration();
            videoSlider.setMax(totalduration.toSeconds());
        });
    }
    @FXML
    protected void pauseButtonClick() {
        pauseButton.setVisible(false);
        playButton.setVisible(true);
        videoPlayer.pause();
    }
    @FXML
    protected void likeButtonClick() throws SQLException {
        if (YouTubeController.isLogin) {
            if (DatabaseManager.getLikeVideo(YouTubeController.loginAccount.getAccountID(), video2.getVideoID()) != 1)
                DatabaseManager.likeVideo(YouTubeController.loginAccount.getAccountID(), video2.getVideoID(),1);
            else {
                DatabaseManager.likeVideo(YouTubeController.loginAccount.getAccountID(), video2.getVideoID(),0);
            }
        }
        int likeCnt = 0;
        int disLikeCnt = 0;
        List<LikeVideo> likeVideos = DatabaseManager.getVideoLikes(video2.getVideoID());
        for (LikeVideo likeVideo: likeVideos){
            if (likeVideo.getLike() == 1)
                likeCnt++;
            if (likeVideo.getLike() == -1)
                disLikeCnt++;
        }
        System.out.println(likeCnt + " " + disLikeCnt);
        likeNumber.setText(String.valueOf(likeCnt));
        disLikeNumber.setText(String.valueOf(disLikeCnt));

    }
    @FXML
    protected void disLikeButtonClick() throws SQLException {
        if (YouTubeController.isLogin){
            if (DatabaseManager.getLikeVideo(YouTubeController.loginAccount.getAccountID(), video2.getVideoID()) != -1)
                DatabaseManager.likeVideo(YouTubeController.loginAccount.getAccountID(), video2.getVideoID(),-1);
            else {
                DatabaseManager.likeVideo(YouTubeController.loginAccount.getAccountID(), video2.getVideoID(), 0);
            }
        }
        int likeCnt = 0;
        int disLikeCnt = 0;
        List<LikeVideo> likeVideos = DatabaseManager.getVideoLikes(video2.getVideoID());
        for (LikeVideo likeVideo: likeVideos){
            if (likeVideo.getLike() == 1)
                likeCnt++;
            if (likeVideo.getLike() == -1)
                disLikeCnt++;
        }
        likeNumber.setText(String.valueOf(likeCnt));
        disLikeNumber.setText(String.valueOf(disLikeCnt));
    }

    @FXML
    protected void selectMedia() {
        String url = video2.getVideoAddress();
//        String url = "file:/Users/ermiababaie/Documents/project/youTubeProject/src/main/resources/youTubeSample.mp4";
        media = new Media(url);
        videoPlayer = new MediaPlayer(media);
        video.setMediaPlayer(videoPlayer);
        videoPlayer.currentTimeProperty().addListener((observable, oldValue, newValue) ->{
            videoSlider.setValue(100 * newValue.toSeconds() / media.getDuration().toSeconds());
        });
        videoPlayer.setOnReady(() -> {
            Duration totalDuration = media.getDuration();
            videoSlider.setValue(totalDuration.toSeconds());
        });
    }
    @FXML
    protected void newCommentClick() throws IOException, SQLException {
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
        if (!comment2.equals("")) {
            Comment commentt = new Comment(UUID.randomUUID(), YouTubeController.loginAccount.getAccountID(), video2.getVideoID(), comment2, 0, 0);
            DatabaseManager.newComment(commentt.getCommentID(), commentt.getAccountID(), video2.getVideoID(), comment2, 0, 0);
        }
    }
    @FXML
    protected void sliderPress(MouseEvent event) {
        videoPlayer.seek(Duration.seconds(videoSlider.getValue()));
    }
    @FXML
    protected void volumePress() {
        videoPlayer.setVolume(volumeSlider.getValue() / 100.0);
    }
    @FXML
    protected void speedPress() {
        if (speedSlider.getValue() < 50.0) {
            if (speedSlider.getValue() >= 10) {
                videoPlayer.setRate(speedSlider.getValue() / 50.0);
            }
            else {
                videoPlayer.setRate(1);
            }
        }
        else {
            videoPlayer.setRate(speedSlider.getValue() / 50.0);
        }
    }
    @FXML
    protected void subscribeClick() throws SQLException {
        if (!YouTubeController.isLogin)
            return;
        if (subscribe.getText().equals("Subscribe")) {
            subscribe.setText("Un Subscribe");
            DatabaseManager.subscribeChannel(YouTubeController.loginAccount.getAccountID(), video2.getAccountID());
        }
        else {
            subscribe.setText("Subscribe");
            DatabaseManager.unSubscribeChannel(YouTubeController.loginAccount.getAccountID(), video2.getAccountID());
        }
    }
}
