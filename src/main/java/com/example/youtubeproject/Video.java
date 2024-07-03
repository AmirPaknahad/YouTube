package com.example.youtubeproject;

import java.util.UUID;

public class Video {
    private UUID videoID;
    private UUID accountID;
    private String address;
    private String caption;
    public Video(UUID videoID, UUID accountID, String address, String caption) {
        this.videoID = videoID;
        this.accountID = accountID;
        this.address = address;
        this.caption = caption;
    }

    public UUID getVideoID() {
        return videoID;
    }

    public UUID getAccountID() {
        return accountID;
    }

    public String getAddress() {
        return address;
    }

    public String getCaption() {
        return caption;
    }

    public void setVideoID(UUID videoID) {
        this.videoID = videoID;
    }

    public void setAccountID(UUID accountID) {
        this.accountID = accountID;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public void setCaption(String caption) {
        this.caption = caption;
    }
}
