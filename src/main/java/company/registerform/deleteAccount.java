package company.registerform;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class deleteAccount {

    @FXML
    private Button backButton;

    @FXML
    private CheckBox captchaCheckBox;

    @FXML
    private PasswordField delPass;

    @FXML
    private TextField delUser;

    @FXML
    private Button deleteButton;

    Alert a = new Alert(Alert.AlertType.NONE);
    Main m = new Main();
    boolean terminate = false;

    public void deleteAccount(ActionEvent actionEvent) {
        if (delUser.getText().isEmpty() || delPass.getText().isEmpty()) {
            a.setAlertType(Alert.AlertType.WARNING);
            a.setContentText("PLEASE ENTER USERNAME AND PASSWORD!");
            a.show();
        }
        if(captchaCheckBox.isSelected() == false){
            a.setAlertType(Alert.AlertType.WARNING);
            a.setContentText("PLEASE MARK THE CHECKBOX!");
            a.show();
        }else{
        getAcc();
        deleteUser();
        }
    }

    void deleteUser () {//delete account if username and password are correct
        Connection con = DbConnection.connect();
        PreparedStatement ps = null;
        if (terminate == true) {
            try {
                String sql = "DELETE FROM user WHERE username = ? AND password = ?";
                ps = con.prepareStatement(sql);
                ps.setString(1, delUser.getText());
                ps.setString(2, delPass.getText());
                ps.execute();

                System.out.println("successfully deleted");

                a.setAlertType(Alert.AlertType.INFORMATION);
                a.setContentText("SUCCESSFULLY DELETED ACCOUNT!");
                a.show();

                delUser.setText(null);
                delPass.setText(null);
            } catch (SQLException e) {
                System.out.println(e.toString());
            } finally {
                try {
                    ps.close();
                    con.close();
                } catch (SQLException e) {
                    System.out.println(e.toString());
                }
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
                    ps.setString(1, delUser.getText());
                    rs = ps.executeQuery();
                    String user = rs.getString(1);
                    terminate = true;
                    System.out.println("user: " + user);
                } catch (SQLException e) {
                    System.out.println(e.toString());

                    a.setAlertType(Alert.AlertType.ERROR);
                    a.setContentText("THERE IS NO SUCH ACCOUNT!");
                    a.show();

                    delUser.setText(null);
                    delPass.setText(null);
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

    public void goToMainPage(ActionEvent actionEvent) throws IOException {
        m.changeScene("mainPage.fxml");
    }
}


