package com.example.youtubeproject;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.sql.SQLException;
import java.util.UUID;
import java.util.function.Predicate;

public class ClientHandler implements Runnable{
    private Socket client;
    private DataInputStream in;
    private DataOutputStream out;
    public ClientHandler(Socket client) throws IOException {
        this.client = client;
        this.in = new DataInputStream(client.getInputStream());
        this.out = new DataOutputStream(client.getOutputStream());
    }
    public void run() {
        try {
            String request = "";
            while (true) {
                request = this.in.readUTF();
                if (request != null) {
                    if (request.startsWith("new_account:")) {
                        newUser(request);
                    }
                   else if (request.startsWith("checkConnection")) {
                        checkConnection();
                    }
                   else if (request.startsWith("get_user_by_user_name:")) {
                        getUserByUserName(request);
                    }
                    else if (request.startsWith("get_user_by_email:")) {
                        getUserByEmail(request);
                    }
                    else if (request.startsWith("new_comment:")) {
                        newComment(request);
                    }
                    else if (request.startsWith("new_video:")) {
                        newVideo(request);
                    }
                }
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            try {
                in.close();
                out.close();
                client.close();
            }catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
    public void newUser(String req) throws SQLException, IOException {
        String accountID = "";
        String firstName = "";
        String lastName = "";
        String email = "";
        String userName = "";
        String passWord = "";
        String profileAddress = "";
        int i = 0;
        for (; i < req.length(); i++) {
            if (req.charAt(i) == ' ')
                break;
        }
        i++;
        for (; i < req.length(); i++) {
            if (req.charAt(i) == '#')
                break;
            accountID += req.charAt(i);
        }
        i++;
        for (; i < req.length(); i++) {
            if (req.charAt(i) == '#')
                break;
            firstName += req.charAt(i);
        }
        i++;
        for (; i < req.length(); i++) {
            if (req.charAt(i) == '#')
                break;
            lastName += req.charAt(i);
        }
        i++;
        for (; i < req.length(); i++) {
            if (req.charAt(i) == '#')
                break;
            email += req.charAt(i);
        }
        i++;
        for (; i < req.length(); i++) {
            if (req.charAt(i) == '#')
                break;
            userName += req.charAt(i);
        }
        i++;
        for (; i < req.length(); i++) {
            if (req.charAt(i) == '#')
                break;
            passWord += req.charAt(i);
        }
        i++;
        for (; i < req.length(); i++) {
            if (req.charAt(i) == '#')
                break;
            profileAddress += req.charAt(i);
        }

        DatabaseManager.newAccount(UUID.fromString(accountID), firstName, lastName, email, userName, passWord, profileAddress);
    }
    public void getUserByUserName(String req) throws SQLException, IOException {
        String answer = "";
        String userName = "";
        int i = 0;
        for (; i < req.length(); i++) {
            if (req.charAt(i) == ' ')
                break;
        }
        i++;
        for (; i < req.length(); i++) {
            userName += req.charAt(i);
        }
        Account account = DatabaseManager.getAccountByUserName(userName);
        if (account == null) {
            out.writeUTF("user_not_found");
        }
        else {
            out.writeUTF(account.toString());
        }
    }
    public void getUserByEmail(String req) throws SQLException, IOException {
        String answer = "";
        String email = "";
        int i = 0;
        for (; i < req.length(); i++) {
            if (req.charAt(i) == ' ')
                break;
        }
        i++;
        for (; i < req.length(); i++) {
            email += req.charAt(i);
        }
        Account account = DatabaseManager.getAccountByEmail(email);

        if (account == null) {
            out.writeUTF("user_not_found");
        }
        else {
            out.writeUTF(account.toString());
        }
    }
    public void checkConnection() throws IOException {
        out.writeUTF("Ok.");
    }
    public void newComment(String req) {
        String commentID = "";
        String accountID = "";
        String videoID = "";
        String commentText = "";
        String likeCnt = "";
        String disLikeCnt = "";
        int i = 0;
        for (; i < req.length(); i++) {
            if (req.charAt(i) == ' ')
                break;
        }
        i++;
        for (; i < req.length(); i++) {
            if (req.charAt(i) == '#')
                break;
            commentID += req.charAt(i);
        }
        i++;
        for (; i < req.length(); i++) {
            if (req.charAt(i) == '#')
                break;
            accountID += req.charAt(i);
        }
        i++;
        for (; i < req.length(); i++) {
            if (req.charAt(i) == '#')
                break;
            videoID += req.charAt(i);
        }
        i++;
        for (; i < req.length(); i++) {
            if (req.charAt(i) == '#')
                break;
            commentText += req.charAt(i);
        }
        i++;
        for (; i < req.length(); i++) {
            if (req.charAt(i) == '#')
                break;
            likeCnt += req.charAt(i);
        }
        i++;
        for (; i < req.length(); i++) {
            if (req.charAt(i) == '#')
                break;
            disLikeCnt += req.charAt(i);
        }

        DatabaseManager.newComment(UUID.fromString(commentID), UUID.fromString(accountID), UUID.fromString(videoID), commentText, Integer.getInteger(likeCnt), Integer.getInteger(disLikeCnt));

    }
    public void newVideo(String req) {
        String videoID = "";
        String accountID = "";
        String videoAddress = "";
        String coverAddress = "";
        String caption = "";
        String videoName = "";
        String isHide = "";
        int i = 0;
        for (; i < req.length(); i++) {
            if (req.charAt(i) == ' ')
                break;
        }
        i++;
        for (; i < req.length(); i++) {
            if (req.charAt(i) == '#')
                break;
            videoID += req.charAt(i);
        }
        i++;
        for (; i < req.length(); i++) {
            if (req.charAt(i) == '#')
                break;
            accountID += req.charAt(i);
        }
        i++;
        for (; i < req.length(); i++) {
            if (req.charAt(i) == '#')
                break;
            videoAddress += req.charAt(i);
        }
        i++;
        for (; i < req.length(); i++) {
            if (req.charAt(i) == '#')
                break;
            coverAddress += req.charAt(i);
        }
        i++;
        for (; i < req.length(); i++) {
            if (req.charAt(i) == '#')
                break;
            caption += req.charAt(i);
        }
        i++;
        for (; i < req.length(); i++) {
            if (req.charAt(i) == '#')
                break;
            videoName += req.charAt(i);
        }
        i++;
        for (; i < req.length(); i++) {
            if (req.charAt(i) == '#')
                break;
            isHide += req.charAt(i);
        }

        DatabaseManager.newVideo(UUID.fromString(videoID), UUID.fromString(accountID), videoAddress, coverAddress, caption, videoName, Boolean.getBoolean(isHide));
    }
}
