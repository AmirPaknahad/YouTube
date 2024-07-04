package com.example.youtubeproject;

import java.util.UUID;

public class LikeVideo {
    private UUID videoID;
    private UUID accountID;
    private int like;
    public LikeVideo(UUID videoID, UUID accountID, int like) {
        this.videoID = videoID;
        this.accountID = accountID;
        this.like = like;
    }

    public UUID getAccountID() {
        return accountID;
    }

    public UUID getVideoID() {
        return videoID;
    }

    public int getLike() {
        return like;
    }

    public void setVideoID(UUID videoID) {
        this.videoID = videoID;
    }

    public void setAccountID(UUID accountID) {
        this.accountID = accountID;
    }

    public void setLike(int like) {
        this.like = like;
    }
}
