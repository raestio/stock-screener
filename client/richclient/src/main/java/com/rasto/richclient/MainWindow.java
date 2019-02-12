package com.rasto.richclient;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;

/**
 * @author Rastislav Zlacky (rastislav.zlacky@inventi.cz) on 08.02.2019.
 */
public class MainWindow extends Stage {

    public MainWindow() {
        URL url = null;
        try {
            url = new File("../../projects/stock-screener/client/richclient/src/main/resources/stock_screener.fxml").toURL();
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }
        Parent root = null;
        try {
            FXMLLoader.setDefaultClassLoader(getClass().getClassLoader());
            root = FXMLLoader.load(url);
        } catch (IOException e) {
            e.printStackTrace();
        }
        Scene scene = new Scene(root);
        //scene.getStylesheets().add(new File("richclient/src/main/resources/styles.css").toURL().toExternalForm());
        setTitle("Stock Screener");
        setScene(scene);
        show();
    }
}
