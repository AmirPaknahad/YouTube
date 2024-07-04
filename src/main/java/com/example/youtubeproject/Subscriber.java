package com.example.youtubeproject;

import java.util.UUID;

public class Subscriber {
    private UUID accountID;
    private UUID channelID;
    public Subscriber(UUID accountID, UUID channelID) {
        this.accountID = accountID;
        this.channelID = channelID;
    }

    public UUID getAccountID() {
        return accountID;
    }

    public UUID getChannelID() {
        return channelID;
    }

    public void setAccountID(UUID accountID) {
        this.accountID = accountID;
    }

    public void setChannelID(UUID channelID) {
        this.channelID = channelID;
    }
}
