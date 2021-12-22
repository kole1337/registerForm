package company.registerform;

import javafx.event.ActionEvent;

import java.io.IOException;

public class AfterLogin {
    public void backToLogin(ActionEvent actionEvent) throws IOException {
        goBack();
    }

    void goBack() throws IOException {
        Main m = new Main();
        m.changeScene("login.fxml");
    }
}
