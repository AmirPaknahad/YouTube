package com.example.youtubeproject;

import java.net.URL;
import java.util.UUID;

public class Comment {
    private UUID commentID;
    private UUID accountID;
    private UUID videoID;
    private String commentText;
    private int likeCnt;
    private int disLikeCnt;
    public Comment(UUID commentID, UUID accountID, UUID videoID, String commentText, int likeCnt, int disLikeCnt) {
        this.commentID = commentID;
        this.accountID = accountID;
        this.videoID = videoID;
        this.commentText = commentText;
        this.likeCnt = likeCnt;
        this.disLikeCnt = disLikeCnt;
    }

    public UUID getCommentID() {
        return commentID;
    }

    public UUID getAccountID() {
        return accountID;
    }

    public UUID getVideoID() {
        return videoID;
    }

    public String getText() {
        return commentText;
    }

    public int getLikeCnt() {
        return likeCnt;
    }

    public int getDisLikeCnt() {
        return disLikeCnt;
    }


    public void setCommentID(UUID commentID) {
        this.commentID = commentID;
    }

    public void setAccountID(UUID userID) {
        this.accountID = userID;
    }

    public void setVideoID(UUID videoID) {
        this.videoID = videoID;
    }

    public void setText(String text) {
        this.commentText = text;
    }

    public void setLikeCnt(int likeCnt) {
        this.likeCnt = likeCnt;
    }

    public void setDisLikeCnt(int disLikeCnt) {
        this.disLikeCnt = disLikeCnt;
    }
}
