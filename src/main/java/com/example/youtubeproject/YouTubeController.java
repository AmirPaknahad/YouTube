package com.example.youtubeproject;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.text.Text;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.sql.SQLException;
import java.util.UUID;

public class YouTubeController {
    Account loginAccount = new Account(UUID.randomUUID(), "", "", "", "", "");
    Boolean isLogin = false;
    //signIn page1 variables
    @FXML
    private AnchorPane signInPage1;
    @FXML
    private TextField sInPageEmail;
    @FXML
    private javafx.scene.text.Text sInPage1ErrorT;
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
    private javafx.scene.text.Text CreateAcc1ErrorT;
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
    private javafx.scene.text.Text CreateAcc2ErrorT;
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
    private javafx.scene.text.Text CreateAcc3ErrorT;
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
    protected void sInPage2NextClick() {
        String inputPassWord = sInPage2Pass.getText();
        if (loginAccount.getPassWord().equals(inputPassWord)) {
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


    //home page functions
    @FXML
    protected void showHomePage() {
        homePage.setVisible(true);
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