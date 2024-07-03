package com.example.youtubeproject;

import java.util.UUID;

public class Account {
    private UUID accountID;
    private String firstName;
    private String lastName;
    private String email;
    private String userName;
    private String PassWord;
    public Account(UUID accountUUID, String firstName, String lastName, String email, String userName, String passWord) {
        this.accountID = accountUUID;
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.userName = userName;
        this.PassWord = passWord;
    }
    //get functions
    public UUID getAccountID() {
        return accountID;
    }

    public String getUserName() {
        return userName;
    }

    public String getPassWord() {
        return PassWord;
    }

    public String getEmail() {
        return email;
    }

    public String getLastName() {
        return lastName;
    }

    //set functions
    public void setAccountUUID(UUID accountUUID) {
        this.accountID = accountUUID;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public void setPassWord(String passWord) {
        PassWord = passWord;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }
}
