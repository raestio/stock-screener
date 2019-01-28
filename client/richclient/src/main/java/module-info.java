module stockscreener.richclient {
    requires javafx.controls;
    requires javafx.fxml;
    requires stockscreener.restclient;
    
    opens com.rasto.richclient to javafx.fxml;
    exports com.rasto.richclient;
}
