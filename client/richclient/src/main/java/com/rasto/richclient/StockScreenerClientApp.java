package com.rasto.richclient;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.File;
import java.net.URL;


public class StockScreenerClientApp extends Application {

    @Override
    public void start(Stage stage) throws Exception {
        URL url = new File("richclient/src/main/resources/stock_screener.fxml").toURL();
        Parent root = FXMLLoader.load(url);
        Scene scene = new Scene(root);
        //scene.getStylesheets().add(new File("richclient/src/main/resources/styles.css").toURL().toExternalForm());
        stage.setTitle("Stock Screener");
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }

}
