module stockscreener.richclient {
    requires javafx.controls;
    requires javafx.fxml;
    
    opens com.rasto.richclient to javafx.fxml;
    exports com.rasto.richclient;
}
