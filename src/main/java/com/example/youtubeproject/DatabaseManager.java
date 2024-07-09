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

    public static void newAccount(UUID accountUUID, String firstName, String lastName, String email, String username, String password, String profileImageAddress) {
        try (Connection connection2 = connect()) {
            String query = "INSERT INTO user_info (accountID, firstname, lastname, email, username, password, profileAddress) VALUES (?, ?, ?, ?, ?, ?, ?)";
            PreparedStatement ps = connection2.prepareStatement(query);
            ps.setObject(1, accountUUID);
            ps.setString(2, firstName);
            ps.setString(3, lastName);
            ps.setString(4, email);
            ps.setString(5, username);
            ps.setString(6, password);
            ps.setString(7, profileImageAddress);
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
            Account account = new Account((UUID)rs.getObject("accountID"), rs.getString("firstname"), rs.getString("lastname"), rs.getString("email"), rs.getString("username"), rs.getString("password"), rs.getString("profileAddress"));
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
            Account account = new Account((UUID)rs.getObject("accountID"), rs.getString("firstname"), rs.getString("lastname"), rs.getString("email"), rs.getString("username"), rs.getString("password"), rs.getString("profileAddress"));
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
            Account account = new Account((UUID)rs.getObject("accountID"), rs.getString("firstname"), rs.getString("lastname"), rs.getString("email"), rs.getString("username"), rs.getString("password"), rs.getString("profileAddress"));
            return account;
        }
        return null;
    }
    public static void changeProfile(UUID accountID, String profileAddress) throws SQLException {
        Connection connection2 = connect();
        String query = "UPDATE user_info SET profileAddress = ? WHERE accountID = ?";
        PreparedStatement ps = connection2.prepareStatement(query);
        ps.setString(1, profileAddress);
        ps.setObject(2, accountID);
        ps.executeUpdate();
        ps.close();
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

    public static void newVideo(UUID videoID, UUID accountID, String videoAddress, String coverAddress, String caption, String videoName, boolean isHide) {
        try (Connection connection2 = connect()) {
            String query = "INSERT INTO videos (videoID, accountID, videoAddress, coverAddress, caption, videoName, isHide) VALUES (?, ?, ?, ?, ?, ?, ?)";
            PreparedStatement ps = connection2.prepareStatement(query);
            ps.setObject(1, videoID);
            ps.setObject(2, accountID);
            ps.setString(3, videoAddress);
            ps.setString(4, coverAddress);
            ps.setString(5, caption);
            ps.setString(6, videoName);
            ps.setBoolean(7, isHide);
            ps.executeUpdate();
            ps.close();
            System.out.println("ss");
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
            Video video = new Video((UUID)rs.getObject("videoID"), (UUID)rs.getObject("accountID"), rs.getString("videoAddress"), rs.getString("coverAddress"), rs.getString("caption"), rs.getString("videoName"), rs.getBoolean("isHide"));
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
            Video video = new Video((UUID)rs.getObject("videoID"), (UUID)rs.getObject("accountID"), rs.getString("videoAddress"), rs.getString("coverAddress"), rs.getString("caption"), rs.getString("videoName"), rs.getBoolean("isHide"));
            videoList.add(video);
        }
        return videoList;
    }

    public static List<Video> getAllVideos() throws SQLException {
        Connection connection2 = connect();
        String query = "SELECT * FROM videos";
        PreparedStatement ps = connection2.prepareStatement(query);
        ResultSet rs = ps.executeQuery();
        List<Video> videoList = new ArrayList<>();
        while (rs.next()) {
            Video video = new Video((UUID)rs.getObject("videoID"), (UUID)rs.getObject("accountID"), rs.getString("videoAddress"), rs.getString("coverAddress"), rs.getString("caption"), rs.getString("videoName"), rs.getBoolean("isHide"));
            videoList.add(video);
        }
        return videoList;
    }
    public static List<LikeVideo> getVideoLikes(UUID videoUUID) throws SQLException {
        Connection connection2 = connect();
        String query = "SELECT * FROM likeVideos WHERE videoID = ?";
        PreparedStatement ps = connection2.prepareStatement(query);
        ps.setObject(1, videoUUID);
        ResultSet rs = ps.executeQuery();
        connection2.close();
        List<LikeVideo> likeVideoList = new ArrayList<>();
        while (rs.next()) {
           LikeVideo likeVideo = new LikeVideo((UUID) rs.getObject("accountID"), (UUID) rs.getObject("videoID"), rs.getInt("likestatus"));
           likeVideoList.add(likeVideo);
        }
        return likeVideoList;
    }
    public static List<LikeComment> getCommentLikes(UUID commentUUID) throws SQLException {
        Connection connection2 = connect();
        String query = "SELECT * FROM likeComments WHERE commentID = ?";
        PreparedStatement ps = connection2.prepareStatement(query);
        ps.setObject(1, commentUUID);
        ResultSet rs = ps.executeQuery();
        connection2.close();
        List<LikeComment> likeCommentList = new ArrayList<>();
        while (rs.next()) {
            LikeComment likeComment = new LikeComment((UUID) rs.getObject("accountID"), (UUID) rs.getObject("commentID"), rs.getInt("likeStatus"));
            likeCommentList.add(likeComment);
        }
        return likeCommentList;
    }

    public static int getLikeVideo(UUID accountID, UUID videoID) throws SQLException {
        Connection connection2 = connect();
        String query = "SELECT * FROM likeComments WHERE accountID = ? AND videoID = ?";
        PreparedStatement ps = connection2.prepareStatement(query);
        ps.setObject(1, accountID);
        ps.setObject(2, videoID);
        ResultSet rs = ps.executeQuery();
        connection2.close();
        if (rs.next()) {
            return rs.getInt("likeStatus");
        }
        else {
            return 0;
        }
    }
    public static int getLikeComment(UUID accountID, UUID commentID) throws SQLException {
        Connection connection2 = connect();
        String query = "SELECT * FROM likeComments WHERE accountID = ? AND commentID = ?";
        PreparedStatement ps = connection2.prepareStatement(query);
        ps.setObject(1, accountID);
        ps.setObject(2, commentID);
        ResultSet rs = ps.executeQuery();
        connection2.close();
        if (rs.next()) {
            return rs.getInt("likeStatus");
        }
        else {
            return 0;
        }
    }

    public static void unLikeVideo(UUID accountUUID, UUID videoUUID) throws SQLException {
        Connection connection = connect();
        String query = "DELETE FROM likeVideos (accountID, videoID, likeStatus) VALUES (?, ?, ?)";
        PreparedStatement ps = connection.prepareStatement(query);
        ps.setObject(1, accountUUID);
        ps.setObject(2, videoUUID);
        ps.setInt(3, getLikeVideo(accountUUID, videoUUID));
        ps.executeUpdate();
        ps.close();
    }

    public static void likeVideo(UUID accountUUID, UUID videoUUID, int like) throws SQLException {
        unLikeVideo(accountUUID, videoUUID);
        Connection connection2 = connect();
        String query = "INSERT INTO likeVideos (accountID, videoID, likeStatus) VALUES (?, ?, ?)";
        PreparedStatement ps = connection2.prepareStatement(query);
        ps.setObject(1, accountUUID);
        ps.setObject(2, videoUUID);
        ps.setInt(3, like);
        ps.executeUpdate();
        ps.close();

    }

    public static void unLikeComment(UUID accountUUID, UUID commentUUID) throws SQLException {
        Connection connection = connect();
        String query = "DELETE FROM likeComments (accountID, commentID, likeStatus) VALUES (?, ?, ?)";
        PreparedStatement ps = connection.prepareStatement(query);
        ps.setObject(1, accountUUID);
        ps.setObject(2, commentUUID);
        ps.setInt(3, getLikeVideo(accountUUID, commentUUID));
        ps.executeUpdate();
        ps.close();
    }

    public static void likeComment(UUID accountUUID, UUID commentUUID, int like) throws SQLException {
        unLikeComment(accountUUID, commentUUID);
        Connection connection2 = connect();
        String query = "INSERT INTO likeComments (accountID, commentID, likeStatus) VALUES (?, ?, ?)";
        PreparedStatement ps = connection2.prepareStatement(query);
        ps.setObject(1, accountUUID);
        ps.setObject(2, commentUUID);
        ps.setInt(3, like);
        ps.executeUpdate();
        ps.close();

    }

    public static boolean subscribeStatus(UUID accountID, UUID channelID) throws SQLException {
        Connection connection2 = connect();
        String query = "SELECT * FROM subscribers WHERE accountID = ? AND channelID = ?";
        PreparedStatement ps = connection2.prepareStatement(query);
        ps.setObject(1, accountID);
        ps.setObject(2, channelID);
        ResultSet rs = ps.executeQuery();
        connection2.close();
        if (rs.next()) {
            return true;
        }
        else {
            return false;
        }
    }
    public static void subscribeChannel(UUID accountID, UUID channelID) throws SQLException {
        if (subscribeStatus(accountID, channelID))
            return;
        Connection connection2 = connect();
        String query = "INSERT INTO subscribers (accountID, channelID) VALUES (?, ?)";
        PreparedStatement ps = connection2.prepareStatement(query);
        ps.setObject(1, accountID);
        ps.setObject(2, channelID);
        ps.executeUpdate();
        ps.close();
    }
    public static void unSubscribeChannel(UUID accountUUID, UUID channelUUID) throws SQLException {
        if (!subscribeStatus(accountUUID, channelUUID))
            return;
        Connection connection = connect();
        String query = "DELETE FROM subscribers (accountID, channelID) VALUES (?, ?)";
        PreparedStatement ps = connection.prepareStatement(query);
        ps.setObject(1, accountUUID);
        ps.setObject(2, channelUUID);
        ps.executeUpdate();
        ps.close();
    }
    public static int subscriberNumber(UUID channelID) throws SQLException {
        Connection connection2 = connect();
        String query = "SELECT * FROM subscribers WHERE channelID = ?";
        PreparedStatement ps = connection2.prepareStatement(query);
        ps.setObject(1, channelID);
        ResultSet rs = ps.executeQuery();
        connection2.close();
        int cnt = 0;
        while (rs.next()) {
            cnt++;
        }
        return cnt;
    }
    public static void watchVideo(UUID accountID, UUID videoID) throws SQLException {
        Connection connection2 = connect();
        String query = "INSERT INTO videoView (accountID, videoID) VALUES (?, ?)";
        PreparedStatement ps = connection2.prepareStatement(query);
        ps.setObject(1, accountID);
        ps.setObject(2, videoID);
        ps.executeUpdate();
        ps.close();
    }
    public static int numberOfView(UUID channelID) throws SQLException {
        Connection connection2 = connect();
        String query = "SELECT * FROM videoView WHERE channelID = ?";
        PreparedStatement ps = connection2.prepareStatement(query);
        ps.setObject(1, channelID);
        ResultSet rs = ps.executeQuery();
        connection2.close();
        int cnt = 0;
        while (rs.next()) {
            cnt++;
        }
        return cnt;
    }
}