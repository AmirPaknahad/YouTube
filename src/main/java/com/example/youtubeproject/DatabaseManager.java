package com.example.youtubeproject;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class DatabaseManager {
    private static final String url = "jdbc:postgresql://localhost:5432/YouTube";
    private static final String username = "postgres";
    private static final String password = "1384";
    private static Connection connection;

    public static Connection connect() throws SQLException {
        try {
            Class.forName("org.postgresql.Driver");
            System.out.println("vasl shod");
        } catch (ClassNotFoundException e) {
            System.out.println(e.getMessage());
        }
        return DriverManager.getConnection(url, username, password);
    }

    public static void newAccount(UUID accountUUID, String firstName, String lastName, String email, String username, String password) {
        try (Connection connection2 = connect()) {
            String query = "INSERT INTO user_info (accountID, firstname, lastname, email, username, password) VALUES (?, ?, ?, ?, ?, ?)";
            PreparedStatement ps = connection2.prepareStatement(query);
            ps.setObject(1, accountUUID);
            ps.setString(2, firstName);
            ps.setString(3, lastName);
            ps.setString(4, email);
            ps.setString(5, username);
            ps.setString(6, password);
            ps.executeUpdate();
            ps.close();
            connection2.close();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    public static Account getAccount(UUID accountUUID) throws SQLException {
        Connection connection2 = connect();
        String query = "SELECT * FROM user_info WHERE accountID = ?";
        PreparedStatement ps = connection2.prepareStatement(query);
        ps.setObject(1, accountUUID);
        ResultSet rs = ps.executeQuery();
        connection2.close();
        if (rs.next()) {
            Account account = new Account((UUID)rs.getObject("accountID"), rs.getString("firstname"), rs.getString("lastname"), rs.getString("email"), rs.getString("username"), rs.getString("password"));
            return account;
        }
        return null;
    }
    public static Account getAccountByEmail(String email) throws SQLException {
        Connection connection2 = connect();
        String query = "SELECT * FROM user_info WHERE email = ?";
        PreparedStatement ps = connection2.prepareStatement(query);
        ps.setString(1, email);
        ResultSet rs = ps.executeQuery();
        connection2.close();
        if (rs.next()) {
            Account account = new Account((UUID)rs.getObject("accountID"), rs.getString("firstname"), rs.getString("lastname"), rs.getString("email"), rs.getString("username"), rs.getString("password"));
            return account;
        }
        return null;
    }
    public static Account getAccountByUserName(String userName) throws SQLException {
        Connection connection2 = connect();
        String query = "SELECT * FROM user_info WHERE username = ?";
        PreparedStatement ps = connection2.prepareStatement(query);
        ps.setString(1, userName);
        ResultSet rs = ps.executeQuery();
        connection2.close();
        if (rs.next()) {
            Account account = new Account((UUID) rs.getObject("accountID"), rs.getString("firstname"), rs.getString("lastname"), rs.getString("email"), rs.getString("username"), rs.getString("password"));
            return account;
        }
        return null;
    }

    public static void newComment(UUID commentID, UUID accountID, UUID videoID, String commentText, int likeCnt, int disLikeCnt) {
        try (Connection connection2 = connect()) {
            String query = "INSERT INTO comments (commentID, accountID, videoID, commentText, likeCnt, disLikeCnt) VALUES (?, ?, ?, ?, ?, ?)";
            PreparedStatement ps = connection2.prepareStatement(query);
            ps.setObject(1, commentID);
            ps.setObject(2, accountID);
            ps.setObject(3, videoID);
            ps.setString(4, commentText);
            ps.setInt(5, likeCnt);
            ps.setInt(6, disLikeCnt);
            ps.executeUpdate();
            ps.close();
            connection2.close();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    public static Comment getComment(UUID commentUUID) throws SQLException {
        Connection connection2 = connect();
        String query = "SELECT * FROM comments WHERE commentID = ?";
        PreparedStatement ps = connection2.prepareStatement(query);
        ps.setObject(1, commentUUID);
        ResultSet rs = ps.executeQuery();
        connection2.close();
        if (rs.next()) {
            Comment comment = new Comment((UUID)rs.getObject("commentID"), (UUID)rs.getObject("accountID"), (UUID)rs.getObject("videoID"), rs.getString("commentText"), rs.getInt("likeCnt"), rs.getInt("disLikeCnt"));
            return comment;
        }
        return null;
    }

    public static List<Comment> getVideoComments(UUID videoUUID) throws SQLException {
        Connection connection2 = connect();
        String query = "SELECT * FROM comments WHERE videoID = ?";
        PreparedStatement ps = connection2.prepareStatement(query);
        ps.setObject(1, videoUUID);
        ResultSet rs = ps.executeQuery();
        connection2.close();
        List<Comment> commentList = new ArrayList<>();
        while (rs.next()) {
            Comment comment = new Comment((UUID)rs.getObject("commentID"), (UUID)rs.getObject("accountID"), (UUID)rs.getObject("videoID"), rs.getString("commentText"), rs.getInt("likeCnt"), rs.getInt("disLikeCnt"));
            commentList.add(comment);
        }
        return commentList;
    }

    public static void newVideo(UUID videoID, UUID accountID, String address, String caption) {
        try (Connection connection2 = connect()) {
            String query = "INSERT INTO videos (videoID, accountID, address, caption) VALUES (?, ?, ?, ?)";
            PreparedStatement ps = connection2.prepareStatement(query);
            ps.setObject(1, videoID);
            ps.setObject(2, accountID);
            ps.setString(3, address);
            ps.setString(4, caption);
            ps.executeUpdate();
            ps.close();
            connection2.close();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    public static Video getVideo(UUID videoUUID) throws SQLException {
        Connection connection2 = connect();
        String query = "SELECT * FROM videos WHERE videoID = ?";
        PreparedStatement ps = connection2.prepareStatement(query);
        ps.setObject(1, videoUUID);
        ResultSet rs = ps.executeQuery();
        connection2.close();
        if (rs.next()) {
            Video video = new Video((UUID)rs.getObject("videoID"), (UUID)rs.getObject("accountID"), rs.getString("address"), rs.getString("caption"));
            return video;
        }
        return null;
    }

    public static List<Video> getAccountVideos(UUID accountUUID) throws SQLException {
        Connection connection2 = connect();
        String query = "SELECT * FROM videos WHERE accountID = ?";
        PreparedStatement ps = connection2.prepareStatement(query);
        ps.setObject(1, accountUUID);
        ResultSet rs = ps.executeQuery();
        connection2.close();
        List<Video> videoList = new ArrayList<>();
        while (rs.next()) {
            Video video = new Video((UUID)rs.getObject("videoID"), (UUID)rs.getObject("accountID"), rs.getString("address"), rs.getString("caption"));
            videoList.add(video);
        }
        return videoList;
    }

}