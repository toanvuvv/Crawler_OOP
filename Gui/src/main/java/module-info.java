module com.example.test {
    requires javafx.controls;
    requires javafx.fxml;
    requires com.fasterxml.jackson.annotation;
    requires com.google.gson;
    requires com.fasterxml.jackson.databind;


    opens com.example.App to javafx.fxml;
    exports com.example.App;
    exports com.example.controll;
    opens com.example.controll to javafx.fxml;
}