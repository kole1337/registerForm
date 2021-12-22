module company.registerform {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.sql;

    opens company.registerform to javafx.fxml;
    exports company.registerform;
}