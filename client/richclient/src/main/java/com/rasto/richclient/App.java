package com.rasto.richclient;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.File;
import java.net.URL;

/**
 * @author Rastislav Zlacky (rastislav.zlacky@inventi.cz) on 08.02.2019.
 */
public class App extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception {
        URL url = new File("richclient/src/main/resources/stock_screener.fxml").toURL();
        Parent root = FXMLLoader.load(url);
        Scene scene = new Scene(root);
        //scene.getStylesheets().add(new File("richclient/src/main/resources/styles.css").toURL().toExternalForm());
        primaryStage.setTitle("Stock Screener");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    public void start() {
        launch();
    }

}
