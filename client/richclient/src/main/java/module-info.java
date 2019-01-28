module stockscreener.main {
    requires javafx.controls;
    requires javafx.fxml;
    
    opens com.rasto to javafx.fxml;
    exports com.rasto;
}
