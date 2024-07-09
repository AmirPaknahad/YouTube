package com.example.youtubeproject;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.stage.FileChooser;

import java.io.File;
import java.sql.SQLException;
import java.util.UUID;

public class profileController {
    String videoUrl = "";
    String coverUrl = "";
    @FXML
    private ImageView profilePic;
    @FXML
    private Label userName;
    @FXML
    private Label subscribers;
    @FXML
    private Button signOut;
    @FXML
    private Button subscribe;
    @FXML
    private Button setting;
    @FXML
    private Button newVideo;
    @FXML
    private Button selectVideo;
    @FXML
    private Button selectCover;
    @FXML
    private Button newVideoOk;
    @FXML
    private Button settingOk;
    @FXML
    private TextField videoName;
    @FXML
    private TextField newPassWord;
    @FXML
    private TextField newUserName;
    @FXML
    private TextField caption;

    @FXML
    public void setData(Account account) throws SQLException {
        profilePic = new ImageView(account.getProfileImageAddress());
        userName.setText(account.getUserName());
        subscribers.setText(String.valueOf(DatabaseManager.subscriberNumber(account.getAccountID())) + " Subscribers");
        if (!account.getAccountID().equals(YouTubeController.loginAccount.getAccountID())) {
            signOut.setVisible(false);
            newVideo.setVisible(false);
            setting.setVisible(false);
            subscribe.setVisible(true);
        }
    }

    @FXML
    public void changeProfile() throws SQLException {
        FileChooser fileChooser = new FileChooser();
        File selectFile = fileChooser.showOpenDialog(null);
        if (selectFile != null)
            DatabaseManager.changeProfile(YouTubeController.loginAccount.getAccountID(), selectFile.toURI().toString());
    }
    @FXML
    public void uploadVideo() {
        selectVideo.setVisible(true);
        selectCover.setVisible(true);
        videoName.setVisible(true);
        newVideoOk.setVisible(true);
        caption.setVisible(true);
    }
    @FXML
    public void selectVideoClick() {
        FileChooser fileChooser = new FileChooser();
        File selectFile = fileChooser.showOpenDialog(null);
        if (selectFile != null)
            videoUrl = selectFile.toURI().toString();


    }
    @FXML
    public void selectCoverClick() {
        FileChooser fileChooser = new FileChooser();
        File selectFile = fileChooser.showOpenDialog(null);
        if (selectFile != null)
            coverUrl = selectFile.toURI().toString();

    }
    @FXML
    public void uploadOk() {
        if (videoName.getText().equals("") || videoUrl.equals("") || coverUrl.equals(""))
            return;
        DatabaseManager.newVideo(UUID.randomUUID(), YouTubeController.loginAccount.getAccountID(), videoUrl, coverUrl, caption.getText(), videoName.getText(), false);
        caption.setVisible(false);
        videoName.setVisible(false);
        selectCover.setVisible(false);
        selectVideo.setVisible(false);
        newVideoOk.setVisible(false);
    }
    @FXML
    public void settingClick() {

    }
    @FXML
    public void subscribeClick() {

    }
    @FXML
    public void signOutClick() {

    }

}
