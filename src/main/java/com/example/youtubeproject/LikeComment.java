package com.example.youtubeproject;

import java.util.List;
import java.util.UUID;

public class LikeComment {
    private UUID commentID;
    private UUID accountID;
    private int like;
    public LikeComment(UUID commentID, UUID accountID, int like) {
        this.commentID = commentID;
        this.accountID = accountID;
        this.like = like;
    }

    public UUID getAccountID() {
        return accountID;
    }

    public UUID getCommentID() {
        return commentID;
    }

    public int getLike() {
        return like;
    }

    public void setCommentID(UUID commentID) {
        this.commentID = commentID;
    }

    public void setAccountID(UUID accountID) {
        this.accountID = accountID;
    }

    public void setLike(int like) {
        this.like = like;
    }
}
