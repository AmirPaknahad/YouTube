package com.example.youtubeproject;

import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.media.MediaView;
import javafx.scene.text.Text;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.*;

public class YouTubeController implements Initializable {
    public static Account loginAccount = new Account(UUID.randomUUID(), "", "", "", "", "", "");
    public static Boolean isLogin = false;
    public static Video videoPlay;


    //signIn page1 variables
    @FXML
    private AnchorPane signInPage1;
    @FXML
    private TextField sInPageEmail;
    @FXML
    private Text sInPage1ErrorT;
    @FXML
    private ImageView sInPage1ErrorI;
    @FXML
    private Button sInPage1CreateAcc;
    @FXML
    private Button sInPage1Next;
    @FXML
    private ImageView sInPage1Back;



    //signIn page2 variables
    @FXML
    private AnchorPane signInPage2;
    @FXML
    private TextField sInPage2Pass;
    @FXML
    private Text sInPage2ErrorT;
    @FXML
    private ImageView sInPage2ErrorI;
    @FXML
    private Button sInPage2CreateAcc;
    @FXML
    private Button sInPage2Next;
    @FXML
    private ImageView sInPage2Back;



    //create account page1 variables
    @FXML
    private AnchorPane createAccPage1;
    @FXML
    private TextField firstNameCreateAcc;
    @FXML
    private TextField lastNameCreateAcc;
    @FXML
    private Text CreateAcc1ErrorT;
    @FXML
    private ImageView CreateAcc1ErrorI;
    @FXML
    private Button CreateAcc1Next;
    @FXML
    private ImageView CreateAcc1Back;



    //create account page2 variables
    @FXML
    private AnchorPane createAccPage2;
    @FXML
    private TextField UserNameCreateAcc;
    @FXML
    private TextField PassWordCreateAcc;
    @FXML
    private Text CreateAcc2ErrorT;
    @FXML
    private ImageView CreateAcc2ErrorI;
    @FXML
    private Button CreateAcc2Next;
    @FXML
    private ImageView CreateAcc2Back;



    //create account page3 variables
    @FXML
    private AnchorPane createAccPage3;
    @FXML
    private TextField EmailCreateAcc;
    @FXML
    private Text CreateAcc3ErrorT;
    @FXML
    private ImageView CreateAcc3ErrorI;
    @FXML
    private Button CreateAcc3Next;
    @FXML
    private ImageView CreateAcc3Back;



    // home page variables
    @FXML
    private AnchorPane homePage;
    @FXML
    private ImageView hpBurgerMenuO;
    @FXML
    private ImageView hpBurgerMenuC;
    @FXML
    private AnchorPane hpLeftMenu;
    @FXML
    private Button hpSignInButton;
    @FXML
    private VBox hpContents;
    @FXML
    private AnchorPane hpContentsPane;
    @FXML
    private Button hpSearchButton;
    @FXML
    private TextField hpSearchText;
    @FXML
    private ImageView signInButtonImage;
    @FXML
    private Button profileButton;


    //media player page
    @FXML
    private AnchorPane mediaPlayerPage;
    @FXML
    public  VBox mediaPlayerVbox;
    @FXML
    private Button homeButtonMP;
    @FXML
    private Button viewAccount;


    //search page
    @FXML
    private AnchorPane searchPage;
    @FXML
    private HBox searchResult;
    @FXML
    private Button backToHome;

    //profile functions
    @FXML
    private AnchorPane profilePage;
    @FXML
    private VBox profileContents;
    @FXML
    private Button homeButtonProfile;














    @Override
    public void initialize(URL location, ResourceBundle resources) {
        try {
            showHomePage();
        } catch (IOException e) {
            throw new RuntimeException(e);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }














    //signIn page1 functions
    @FXML
    protected void sInPage1CreateAccClick() {
        loginAccount = new Account(UUID.randomUUID(), "", "", "", "", "", "");
        sInPageEmail.clear();
        signInPage1.setVisible(false);
        createAccPage1.setVisible(true);
    }
    @FXML
    protected void sInPage1NextClick() throws SQLException, IOException {
        String inputEmail = sInPageEmail.getText();
        Account accountSIngPage = getUserByUserEmail(inputEmail);
        if (accountSIngPage != null) {
            loginAccount = accountSIngPage;
            sInPage1ErrorT.setVisible(false);
            sInPage1ErrorI.setVisible(false);
            signInPage1.setVisible(false);
            signInPage2.setVisible(true);
        }
        else {
            sInPage1ErrorT.setVisible(true);
            sInPage1ErrorI.setVisible(true);
        }
    }
    @FXML
    protected void sInPage1BackClick() {
        sInPageEmail.clear();
        signInPage1.setVisible(false);
        homePage.setVisible(true);
    }









    //signIn page2 functions
    @FXML
    protected void sInPage2CreateAccClick() {
        loginAccount = new Account(UUID.randomUUID(), "", "", "", "", "", "");
        sInPageEmail.clear();
        sInPage2Pass.clear();
        signInPage2.setVisible(false);
        createAccPage1.setVisible(true);
    }
    @FXML
    protected void sInPage2NextClick() throws SQLException, IOException {
        String inputPassWord = sInPage2Pass.getText();
        if (loginAccount.getPassWord().equals(hash(inputPassWord))) {
            isLogin = true;
            signInPage2.setVisible(false);
            showHomePage();
        }
        else {
            sInPage2ErrorT.setVisible(true);
            sInPage2ErrorI.setVisible(true);
        }
    }
    @FXML
    protected void sInPage2BackClick() {
        firstNameCreateAcc.clear();
        lastNameCreateAcc.clear();
        sInPage2Pass.clear();
        signInPage2.setVisible(false);
        signInPage1.setVisible(true);
    }









    //create account page1 functions
    @FXML
    protected void CreateAcc1NextClick() {
        String fName = firstNameCreateAcc.getText();
        String lName = lastNameCreateAcc.getText();
        if (fName.equals("")) {
            CreateAcc1ErrorI.setVisible(true);
            CreateAcc1ErrorT.setVisible(true);
        }
        else {
            loginAccount.setFirstName(fName);
            loginAccount.setLastName(lName);
            loginAccount.setAccountUUID(UUID.randomUUID());
            CreateAcc1ErrorI.setVisible(false);
            CreateAcc1ErrorT.setVisible(false);
            createAccPage1.setVisible(false);
            createAccPage2.setVisible(true);
        }
    }
    @FXML
    protected void CreateAcc1BackClick() {
        firstNameCreateAcc.clear();
        lastNameCreateAcc.clear();
        createAccPage1.setVisible(false);
        signInPage1.setVisible(true);
    }










    //create account page2 functions
    @FXML
    protected void CreateAcc2NextClick() throws SQLException, IOException {
        String inputUserName = UserNameCreateAcc.getText();
        String inputPassWord = PassWordCreateAcc.getText();
        Account accountByCreateAcc = getUserByUserName(inputUserName);

        if (!inputPassWord.equals("") && !inputUserName.equals("")) {
            if (accountByCreateAcc != null) {
                CreateAcc2ErrorI.setVisible(true);
                CreateAcc2ErrorT.setVisible(true);
            } else {
                loginAccount.setUserName(inputUserName);
                loginAccount.setPassWord(hash(inputPassWord));
                createAccPage2.setVisible(false);
                createAccPage3.setVisible(true);
            }
        }
    }
    @FXML
    protected void CreateAcc2BackClick() {
        createAccPage2.setVisible(false);
        createAccPage1.setVisible(true);
    }








    //create account page2 functions
    @FXML
    protected void CreateAcc3NextClick() throws SQLException, IOException {
       String inputEmail = EmailCreateAcc.getText();
       if (!inputEmail.equals("")) {
           if (validEmail(inputEmail)) {
               loginAccount.setEmail(inputEmail);
               newUser(loginAccount);
               isLogin = true;
               createAccPage3.setVisible(false);

               EmailCreateAcc.clear();
               UserNameCreateAcc.clear();
               firstNameCreateAcc.clear();
               lastNameCreateAcc.clear();
               PassWordCreateAcc.clear();
               showHomePage();
           }
           else {
               CreateAcc3ErrorI.setVisible(true);
               CreateAcc3ErrorT.setVisible(true);
           }
       }
    }
    @FXML
    protected void CreateAcc3BackClick() {
        createAccPage3.setVisible(false);
        createAccPage3.setVisible(true);
    }





    //profile functions
    @FXML
    protected void showProfile(Account account) throws SQLException, IOException {
        System.out.println(account.getAccountID());
        profileContents.getChildren().clear();
        profilePage.setVisible(true);
        FXMLLoader fxmlPage = new FXMLLoader();
        fxmlPage.setLocation(getClass().getResource("profileTasks.fxml"));
        AnchorPane profileTask = fxmlPage.load();
        profileController profileController = fxmlPage.getController();
        profileController.setData(account);
        profileContents.getChildren().add(profileTask);
        List<Video> videos = DatabaseManager.getAccountVideos(account.getAccountID());
        System.out.println(videos.size());
        if (videos.size() % 3 != 0)
            videos.add(videos.get(0));
        if (videos.size() % 3 != 0)
            videos.add(videos.get(0));
        for (int i = 0; i < videos.size() - 2; i += 3) {
            System.out.println(i);
            HBox hBox = new HBox();
            FXMLLoader fxmlLoader = new FXMLLoader();
            fxmlLoader.setLocation(getClass().getResource("Video.fxml"));
            AnchorPane anchorPane = fxmlLoader.load();
            videoController videoController = fxmlLoader.getController();
            videoController.setDate(videos.get(i));
            Video video = videos.get(i);
            anchorPane.setOnMouseClicked(new EventHandler<MouseEvent>() {
                @Override
                public void handle(MouseEvent event) {
                    try {
                        playMedia(video);
                    } catch (SQLException e) {
                        throw new RuntimeException(e);
                    } catch (IOException e) {
                        throw new RuntimeException(e);
                    }
                }
            });
            hBox.getChildren().add(anchorPane);
            FXMLLoader fxmlLoader2 = new FXMLLoader();
            fxmlLoader2.setLocation(getClass().getResource("Video.fxml"));
            AnchorPane anchorPane2 = fxmlLoader2.load();
            videoController videoController2 = fxmlLoader2.getController();
            videoController2.setDate(videos.get(i + 1));
            Video video2 = videos.get(i + 1);
            anchorPane2.setOnMouseClicked(new EventHandler<MouseEvent>() {
                @Override
                public void handle(MouseEvent event) {
                    try {
                        playMedia(video2);
                    } catch (SQLException e) {
                        throw new RuntimeException(e);
                    } catch (IOException e) {
                        throw new RuntimeException(e);
                    }
                }
            });
            hBox.getChildren().add(anchorPane2);
            FXMLLoader fxmlLoader3 = new FXMLLoader();
            fxmlLoader3.setLocation(getClass().getResource("Video.fxml"));
            AnchorPane anchorPane3 = fxmlLoader3.load();
            videoController videoController3 = fxmlLoader3.getController();
            videoController3.setDate(videos.get(i + 2));
            Video video3 = videos.get(i + 2);
            anchorPane.setOnMouseClicked(new EventHandler<MouseEvent>() {
                @Override
                public void handle(MouseEvent event) {
                    try {
                        playMedia(video3);
                    } catch (SQLException e) {
                        throw new RuntimeException(e);
                    } catch (IOException e) {
                        throw new RuntimeException(e);
                    }
                }
            });
            hBox.getChildren().add(anchorPane3);
            profileContents.getChildren().add(hBox);
        }
    }
    @FXML
    protected void gotoProfile() throws SQLException, IOException {
        homePage.setVisible(false);
        showProfile(loginAccount);
    }
    @FXML
    protected void gotoHomePageFromProfile() throws SQLException, IOException {
        profilePage.setVisible(false);
        showHomePage();
    }


    //home page functions
    @FXML
    protected void showHomePage() throws IOException, SQLException {
        hpContents.getChildren().clear();
        if (isLogin) {
            hpSignInButton.setVisible(false);
            signInButtonImage.setVisible(false);
            profileButton.setVisible(true);
        }
        else {
            hpSignInButton.setVisible(true);
            signInButtonImage.setVisible(true);
        }
        homePage.setVisible(true);
        List<Video> videos = getVideosForHomePages();
        for (int i = 0; i < videos.size() - 2; i += 3) {
            HBox hBox = new HBox();
            FXMLLoader fxmlLoader = new FXMLLoader();
            fxmlLoader.setLocation(getClass().getResource("Video.fxml"));
            AnchorPane anchorPane = fxmlLoader.load();
            videoController videoController = fxmlLoader.getController();
            videoController.setDate(videos.get(i));
            Video video = videos.get(i);
            anchorPane.setOnMouseClicked(new EventHandler<MouseEvent>() {
                @Override
                public void handle(MouseEvent event) {
                    try {
                        playMedia(video);
                    } catch (SQLException | IOException e) {
                        throw new RuntimeException(e);
                    }
                }
            });
            hBox.getChildren().add(anchorPane);

            FXMLLoader fxmlLoader2 = new FXMLLoader();
            fxmlLoader2.setLocation(getClass().getResource("Video.fxml"));
            AnchorPane anchorPane2 = fxmlLoader2.load();
            videoController videoController2 = fxmlLoader2.getController();
            videoController2.setDate(videos.get(i + 1));
            Video video2 = videos.get(i + 1);
            anchorPane2.setOnMouseClicked(new EventHandler<MouseEvent>() {
                @Override
                public void handle(MouseEvent event) {
                    try {
                        playMedia(video2);
                    } catch (SQLException e) {
                        throw new RuntimeException(e);
                    } catch (IOException e) {
                        throw new RuntimeException(e);
                    }
                }
            });
            hBox.getChildren().add(anchorPane2);

            FXMLLoader fxmlLoader3 = new FXMLLoader();
            fxmlLoader3.setLocation(getClass().getResource("Video.fxml"));
            AnchorPane anchorPane3 = fxmlLoader3.load();
            videoController videoController3 = fxmlLoader3.getController();
            videoController3.setDate(videos.get(i + 2));
            Video video3 = videos.get(i + 2);
            anchorPane3.setOnMouseClicked(new EventHandler<MouseEvent>() {
                @Override
                public void handle(MouseEvent event) {
                    try {
                        playMedia(video3);
                    } catch (SQLException e) {
                        throw new RuntimeException(e);
                    } catch (IOException e) {
                        throw new RuntimeException(e);
                    }
                }
            });
            hBox.getChildren().add(anchorPane3);



            hpContents.getChildren().add(hBox);
        }

    }
    @FXML
    protected void hpBurgerMenuOClick() {
        hpLeftMenu.setVisible(true);
    }
    @FXML
    protected void hpBurgerMenuCClick() {
        hpLeftMenu.setVisible(false);
    }
    @FXML
    protected void hpSignInButtonClick() {
        homePage.setVisible(false);
        signInPage1.setVisible(true);
    }
    @FXML
    protected void HpSearchButtonClick() throws SQLException, IOException {
        String save = hpSearchText.getText();
        hpSearchText.clear();
        homePage.setVisible(false);
        showSearchPage(save);
    }




    //search page
    @FXML
    protected void searchPageBackToHome() throws SQLException, IOException {
        searchResult.getChildren().clear();
        searchPage.setVisible(false);
//        homePage.setVisible(true);
        showHomePage();
    }
    @FXML
    protected void showSearchPage(String videoName) throws SQLException, IOException {
        searchPage.setVisible(true);
        searchResult.getChildren().clear();
        List<Video> videoList = DatabaseManager.getAllVideos();
        List<Video> selected = new ArrayList<>();
        if (videoName.charAt(0) != '#') {
            for (Video video : videoList) {
                if (strInStr(videoName, video.getVideoName()))
                    selected.add(video);
            }
        }
        else {
            for (Video video : videoList) {
                if (strInStr(videoName, video.getCaption()))
                    selected.add(video);
            }
        }
        for (Video video: selected) {
            FXMLLoader fxmlLoader = new FXMLLoader();
            fxmlLoader.setLocation(getClass().getResource("Video.fxml"));
            AnchorPane anchorPane = fxmlLoader.load();
            videoController videoController = fxmlLoader.getController();
            videoController.setDate(video);
            Video video1 = video;
            anchorPane.setOnMouseClicked(new EventHandler<MouseEvent>() {
                @Override
                public void handle(MouseEvent event) {
                    try {
                        playMedia(video1);
                    } catch (SQLException e) {
                        throw new RuntimeException(e);
                    } catch (IOException e) {
                        throw new RuntimeException(e);
                    }
                }
            });
            searchResult.getChildren().add(anchorPane);
        }
    }



    //play media
    @FXML
    protected void viewAccountClick() throws SQLException, IOException {
        mediaPlayerPage.setVisible(false);
        mediaPlayerController.videoPlayer.pause();
        showProfile(DatabaseManager.getAccount(videoPlay.getAccountID()));
    }
    @FXML
    protected void gotoHomePageFromMP() throws SQLException, IOException {
        mediaPlayerPage.setVisible(false);
        mediaPlayerVbox.getChildren().clear();
        mediaPlayerController.videoPlayer.pause();
        showHomePage();
    }
    public void addToMpVbox() {

    }
    @FXML
    protected void playMedia(Video video) throws SQLException, IOException {
        videoPlay = video;
        homePage.setVisible(false);
        if (!video.getAccountID().equals(loginAccount.getAccountID()) && isLogin) {
            DatabaseManager.watchVideo(loginAccount.getAccountID(), video.getVideoID());
        }

        mediaPlayerPage.setVisible(true);
        mediaPlayerVbox.getChildren().clear();
        FXMLLoader fxmlLoader2 = new FXMLLoader();
        fxmlLoader2.setLocation(getClass().getResource("mediaPlayer.fxml"));
        AnchorPane mediaPlayer = fxmlLoader2.load();
        mediaPlayerController mediaPlayerController = fxmlLoader2.getController();
        mediaPlayerController.setData(video);
        mediaPlayerVbox.getChildren().add(mediaPlayer);


        List<Comment> commentList = DatabaseManager.getVideoComments(video.getVideoID());
        System.out.println(commentList.size());
        for (Comment comment: commentList) {
            FXMLLoader fxmlLoader = new FXMLLoader();
            fxmlLoader.setLocation(getClass().getResource("Comment.fxml"));
            AnchorPane anchorPane = fxmlLoader.load();
            CommentController commentController = fxmlLoader.getController();
            commentController.setData(comment);
            mediaPlayerVbox.getChildren().add(anchorPane);
        }
    }














    //important functions
    protected String hash(String pas) {
        String pass = pas + "This Is Salt For My hash";
        long mod = 1000000007, mod2 = 1000000009, mabna = 457, mabna2 = 701;
        long ans = 0, ans2 = 0, pow = 1, pow2 = 1;
        for (int i = 0; i < pass.length(); i++) {
            int save = pass.charAt(i) - ' ';
            ans = (ans + (pow * save)) % mod;
            pow = (pow * mabna) % mod;
            ans2 = (ans2 + (pow2 * save)) % mod2;
            pow2 = (pow2 * mabna2) % mod2;

        }
        return String.valueOf(ans) + "%" + String.valueOf(ans2);
    }
    protected Boolean validEmail(String Email) throws SQLException, IOException {
        Boolean checkEmailReg = true;
        if (getUserByUserEmail(Email) != null || checkEmailReg == false) {
            return false;
        }
        else {
            return true;
        }
    }
    protected Boolean strInStr(String s1, String s2) {
        for (int i = 0; i < (s2.length() - s1.length() + 1); i++) {
            boolean check = true;
            for (int j = 0; j < s1.length(); j++) {
                if (s1.charAt(j) != s2.charAt(j + i))
                    check = false;
            }
            if (check)
                return true;
        }
        return false;
    }

    protected List<Video> getVideosForHomePages() throws SQLException {
        List<Video> videos = DatabaseManager.getAllVideos();
        Collections.reverse(videos);
        List<Video> finalVideos = new ArrayList<>();
        for (Video video: videos) {
            if (finalVideos.size() >= 21)
                break;
            if (DatabaseManager.subscribeStatus(loginAccount.getAccountID(), video.getAccountID())) {
                finalVideos.add(video);
            }
        }
        for (Video video: finalVideos)
            videos.remove(video);
        while (finalVideos.size() < 21 && !videos.isEmpty()) {
            Video video = videos.get(0);
            finalVideos.add(video);
            videos.remove(0);
        }
        return finalVideos;
    }

































    //server connection:
    @FXML
    protected void testDataBase() throws IOException {
        DataOutputStream outputStream = new DataOutputStream(YouTube.client.getOutputStream());
        outputStream.writeUTF("checkConnection");
    }
    protected void newUser(Account account) throws IOException {
        DataOutputStream outputStream = new DataOutputStream(YouTube.client.getOutputStream());
        outputStream.writeUTF("new_account: " + account.toString());
    }
    protected Account getUserByUserName(String userName1) throws IOException {
        DataOutputStream outputStream = new DataOutputStream(YouTube.client.getOutputStream());
        DataInputStream inputStream = new DataInputStream(YouTube.client.getInputStream());
        outputStream.writeUTF("get_user_by_user_name: " + userName1);
        String answer = inputStream.readUTF();
        System.out.println(answer);
        if (answer.equals("user_not_found"))
            return null;
        String accountID = "";
        String firstName = "";
        String lastName = "";
        String email = "";
        String userName = "";
        String passWord = "";
        String profileAddress = "";
        int i = 0;
        for (; i < answer.length(); i++) {
            if (answer.charAt(i) == '#')
                break;
            accountID += answer.charAt(i);
        }
        i++;
        for (; i < answer.length(); i++) {
            if (answer.charAt(i) == '#')
                break;
            firstName += answer.charAt(i);
        }
        i++;
        for (; i < answer.length(); i++) {
            if (answer.charAt(i) == '#')
                break;
            lastName += answer.charAt(i);
        }
        i++;
        for (; i < answer.length(); i++) {
            if (answer.charAt(i) == '#')
                break;
            email += answer.charAt(i);
        }
        i++;
        for (; i < answer.length(); i++) {
            if (answer.charAt(i) == '#')
                break;
            userName += answer.charAt(i);
        }
        i++;
        for (; i < answer.length(); i++) {
            if (answer.charAt(i) == '#')
                break;
            passWord += answer.charAt(i);
        }
        i++;
        for (; i < answer.length(); i++) {
            if (answer.charAt(i) == '#')
                break;
            profileAddress += answer.charAt(i);
        }
        Account account1 = new Account(UUID.fromString(accountID), firstName, lastName, email, userName, passWord, profileAddress);
        return account1;
    }
    protected Account getUserByUserEmail(String email1) throws IOException {
        DataOutputStream outputStream = new DataOutputStream(YouTube.client.getOutputStream());
        DataInputStream inputStream = new DataInputStream(YouTube.client.getInputStream());
        outputStream.writeUTF("get_user_by_email: " + email1);
        String answer = inputStream.readUTF();
        System.out.println(answer);
        if (answer.equals("user_not_found"))
            return null;
        String accountID = "";
        String firstName = "";
        String lastName = "";
        String email = "";
        String userName = "";
        String passWord = "";
        String profileAddress = "";
        int i = 0;
        for (; i < answer.length(); i++) {
            if (answer.charAt(i) == '#')
                break;
            accountID += answer.charAt(i);
        }
        i++;
        for (; i < answer.length(); i++) {
            if (answer.charAt(i) == '#')
                break;
            firstName += answer.charAt(i);
        }
        i++;
        for (; i < answer.length(); i++) {
            if (answer.charAt(i) == '#')
                break;
            lastName += answer.charAt(i);
        }
        i++;
        for (; i < answer.length(); i++) {
            if (answer.charAt(i) == '#')
                break;
            email += answer.charAt(i);
        }
        i++;
        for (; i < answer.length(); i++) {
            if (answer.charAt(i) == '#')
                break;
            userName += answer.charAt(i);
        }
        i++;
        for (; i < answer.length(); i++) {
            if (answer.charAt(i) == '#')
                break;
            passWord += answer.charAt(i);
        }
        i++;
        for (; i < answer.length(); i++) {
            if (answer.charAt(i) == '#')
                break;
            profileAddress += answer.charAt(i);
        }
        Account account1 = new Account(UUID.fromString(accountID), firstName, lastName, email, userName, passWord, profileAddress);
        return account1;
    }

}