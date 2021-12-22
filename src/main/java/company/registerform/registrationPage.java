package company.registerform;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Random;

public class registrationPage {

    @FXML
    private TextField captcha;

    @FXML
    private Button backButton;

    @FXML
    private Label captchaString;

    @FXML
    private Button register;

    @FXML
    private PasswordField registerPassword;

    @FXML
    private TextField registerUser;

    Main m = new Main();
    Alert a = new Alert(Alert.AlertType.NONE);
    boolean terminate = false;

    public void registerNewUser(ActionEvent actionEvent) {
        getAcc();
        boolean cap = false;
        System.out.println(captcha.getText());
        if(captcha.getText().equals(captchaString.getText())){
            cap = true;

        }else if(captcha.getText().isEmpty()){
            a.setAlertType(Alert.AlertType.ERROR);
            a.setContentText("WRONG CAPTCHA!");
            a.show();
        }else {
            captcha.setText(null);
        a.setAlertType(Alert.AlertType.ERROR);
        a.setContentText("WRONG CAPTCHA!");
        a.show();
        }

        if(terminate == true){
        a.setAlertType(Alert.AlertType.ERROR);
        a.setContentText("THERE IS SUCH ACCOUNT!");
        a.show();

        registerUser.setText(null);
        registerPassword.setText(null);
        }else if(!terminate && cap == true){
        registration();
        }
    }

    void registration() {
        Connection con = DbConnection.connect();
        PreparedStatement ps = null;
        if (registerUser.getText().isEmpty() && registerPassword.getText().isEmpty()) {
            a.setAlertType(Alert.AlertType.WARNING);
            a.setContentText("PLEASE ENTER USERNAME AND PASSWORD");

        } else {

            try {
                String sql = "INSERT INTO user(username, password) VALUES(?, ?)";
                ps = con.prepareStatement(sql);
                ps.setString(1, registerUser.getText());
                ps.setString(2, registerPassword.getText());
                ps.execute();

                a.setAlertType(Alert.AlertType.INFORMATION);
                a.setContentText("Account successfully registered");
                a.show();
            } catch (SQLException e) {

            }
        }
    }

    void getAcc() {//check if there is such an account
        Connection con = DbConnection.connect();
        PreparedStatement ps = null;
        ResultSet rs = null;
        try {
            String sql = "Select * FROM user WHERE username = ?";
            ps = con.prepareStatement(sql);
            ps.setString(1, registerUser.getText());
            rs = ps.executeQuery();
            String user = rs.getString(1);
            terminate = true;

            System.out.println("user is registered: " + user);
        } catch (SQLException e) {
            System.out.println(e.toString());
            terminate = false;

        } finally {
            try {
                rs.close();
                ps.close();
                con.close();
            } catch (SQLException e) {
                System.out.println(e.toString());
            }
        }
    }

    public String RandomCaptcha() {

        // create a string of all characters
        String alphabet = "1234567890";

        // create random string builder
        StringBuilder sb = new StringBuilder();

        // create an object of Random class
        Random random = new Random();

        // specify length of random string
        int length = 7;

        for(int i = 0; i < length; i++) {

            // generate random index number
            int index = random.nextInt(alphabet.length());

            // get character specified by index
            // from the string
            char randomChar = alphabet.charAt(index);

            // append the character to string builder
            sb.append(randomChar);
        }

        String randomString = sb.toString();
        return randomString;
    }

    public void generateCaptcha(){

        captchaString.setText(RandomCaptcha());
    }


    public void goBackToMainPage(ActionEvent actionEvent) throws IOException {
        m.changeScene("mainPage.fxml");
    }
}
