package company.registerform;

import java.util.Random;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.image.ImageView;
import javafx.scene.image.Image;
import javafx.scene.control.Alert.AlertType;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class loginController {

    @FXML
    private ResourceBundle resources;


    @FXML
    private Button generateCaptcha;

    @FXML
    private URL location;

    @FXML
    private Label errorMessage;

    @FXML
    private Button loginButton;

    @FXML
    private PasswordField password;

    @FXML
    private Button registerButton;

    @FXML
    private TextField username;

    @FXML
    private TextField registerUser;

    @FXML
    private PasswordField setPassword;

    @FXML
    private ImageView imageView;

    @FXML
    private Label captcha;

    Image image3 = new Image("https://isssource.com/wp-content/uploads/2020/08/081020smart-lock.png", 100, 0, false, false);

//    deleteAccount obj = new deleteAccount();



    private String newUser = null;
    private String newPassword = null;
    private Boolean proccede = false;

    Alert a = new Alert(AlertType.NONE);





    @FXML
    void login(ActionEvent event) throws IOException {
        logIn();
    }
    void logIn() throws IOException {
//        if(username.getText().equals("admin")
//        && password.getText().toString().equals("0000")
//        ){
//            proccede = true;
//        }

        if(username.getText().toString().equals(newUser)
                && password.getText().toString().equals(newPassword)
        ){
            proccede = true;
        }else{
            a.setAlertType(AlertType.ERROR);
            a.setContentText("Wrong Credentials");
            a.show();
            username.setText(null);
            password.setText(null);
//            errorMessage.setText("WRONG DATA/NO REGISTERED USER");
        }


        if(username.getText().isEmpty()
                && password.getText().isEmpty()
        ){
            a.setAlertType(AlertType.WARNING);
            a.setContentText("PLEASE ENTER USERNAME AND PASSWORD");
            a.show();
//            errorMessage.setText("PLEASE ENTER YOUR CREDENTIALS");
        }


        if(proccede == true){
            Main m = new Main();
            m.changeScene("afterLogin.fxml");
        }

    }

    @FXML
    void register()  {
        setNewAccount();
    }


    void setNewAccount() {
        if(registerUser.getText().isEmpty() || setPassword.getText().isEmpty()){
            a.setAlertType(AlertType.WARNING);
            a.setContentText("PLEASE ENTER USERNAME AND PASSWORD");
            //            errorMessage.setText("PLEASE PROVIDE CREDENTIALS");
        }else {
            newUser = registerUser.getText();
            newPassword = setPassword.getText();
            a.setAlertType(AlertType.CONFIRMATION);
            a.setContentText("SUCCESSFUL REGISTRATION");
            registerUser.setText(null);
            setPassword.setText(null);
            //            errorMessage.setText("SUCCESSFUL REGISTRATION");
        }
        a.show();

    }




    @FXML
    void initialize() {
        assert captcha != null : "fx:id=\"captcha\" was not injected: check your FXML file 'login.fxml'.";
        assert errorMessage != null : "fx:id=\"errorMessage\" was not injected: check your FXML file 'login.fxml'.";
        assert generateCaptcha != null : "fx:id=\"generateCaptcha\" was not injected: check your FXML file 'login.fxml'.";
        assert imageView != null : "fx:id=\"imageView\" was not injected: check your FXML file 'login.fxml'.";
        assert loginButton != null : "fx:id=\"loginButton\" was not injected: check your FXML file 'login.fxml'.";
        assert password != null : "fx:id=\"password\" was not injected: check your FXML file 'login.fxml'.";
        assert registerButton != null : "fx:id=\"registerButton\" was not injected: check your FXML file 'login.fxml'.";
        assert registerUser != null : "fx:id=\"registerUser\" was not injected: check your FXML file 'login.fxml'.";
        assert setPassword != null : "fx:id=\"setPassword\" was not injected: check your FXML file 'login.fxml'.";
        assert username != null : "fx:id=\"username\" was not injected: check your FXML file 'login.fxml'.";

    }
}