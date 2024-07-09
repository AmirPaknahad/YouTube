package com.example.youtubeproject;

import java.util.UUID;

public class Video {
    private UUID videoID;
    private UUID accountID;
    private String videoAddress;
    private String coverAddress;
    private String caption;
    private String videoName;
    private boolean hide;

    public Video(UUID videoID, UUID accountID, String videoAddress, String coverAddress, String caption, String videoName, boolean hide) {
        this.videoID = videoID;
        this.accountID = accountID;
        this.videoAddress = videoAddress;
        this.coverAddress = coverAddress;
        this.caption = caption;
        this.videoName = videoName;
        this.hide = hide;
    }

    public UUID getVideoID() {
        return videoID;
    }

    public UUID getAccountID() {
        return accountID;
    }

    public String getVideoAddress() {
        return videoAddress;
    }

    public String getCoverAddress() {
        return coverAddress;
    }

    public String getCaption() {
        return caption;
    }

    public String getVideoName() {
        return videoName;
    }

    public boolean isHide() {
        return hide;
    }

    public void setVideoID(UUID videoID) {
        this.videoID = videoID;
    }

    public void setAccountID(UUID accountID) {
        this.accountID = accountID;
    }

    public void setVideoAddress(String videoAddress) {
        this.videoAddress = videoAddress;
    }

    public void setCoverAddress(String coverAddress) {
        this.coverAddress = coverAddress;
    }

    public void setCaption(String caption) {
        this.caption = caption;
    }

    public void setVideoName(String videoName) {
        this.videoName = videoName;
    }
}
