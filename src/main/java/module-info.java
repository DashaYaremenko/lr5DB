module com.example.lr5db {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.sql;


    opens com.example.lr5db to javafx.fxml;
    exports com.example.lr5db;
}