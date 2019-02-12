package com.rasto.richclient;

import javafx.application.Platform;
import org.osgi.framework.BundleActivator;
import org.osgi.framework.BundleContext;

import java.util.logging.Logger;


public class StockScreenerClientApp implements BundleActivator {
    private static final Logger LOG = Logger.getLogger(StockScreenerClientApp.class.getName());

    @Override
    public void start(BundleContext bundleContext) throws Exception {
        LOG.info("Richclient starts");
        Platform.startup(new Runnable() {
            @Override
            public void run() {
                LOG.info("Richclient window open");
                new MainWindow();
            }
        });
    }

    @Override
    public void stop(BundleContext bundleContext) throws Exception {
        LOG.info("Richclient stops");
    }
}
