package company.registerform;

import javafx.event.ActionEvent;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;

import java.io.IOException;

public class mainPage {

    Main m = new Main();

    public void goToLogin(ActionEvent actionEvent) throws IOException {
        m.changeScene("login.fxml");
    }

    public void goToRegister(ActionEvent actionEvent) throws IOException {
        m.changeScene("registrationPage.fxml");

    }

    public void goToDeleteAcc(ActionEvent actionEvent) throws IOException {
        Alert a = new Alert(Alert.AlertType.CONFIRMATION);
        a.setTitle("Confirm Account Termination!");
        a.setHeaderText("Please Confirm!");
        a.setContentText("Deleting your account is permanent and cannot be reversed!");
        a.showAndWait();

        if(a.getResult() == ButtonType.OK){
            m.changeScene("deleteAccount.fxml");
        }
    }
}

