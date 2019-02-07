package com.rasto.richclient;

import org.osgi.framework.BundleActivator;
import org.osgi.framework.BundleContext;

import java.util.logging.Logger;

/**
 * @author Rastislav Zlacky (rastislav.zlacky@inventi.cz) on 07.02.2019.
 */
public class Activator implements BundleActivator {
    private static final Logger LOG = Logger.getLogger(Activator.class.getName());

    @Override
    public void start(BundleContext bundleContext) throws Exception {
        LOG.info("Richclient starts");
        StockScreenerClientApp.main(new String[]{});
    }

    @Override
    public void stop(BundleContext bundleContext) throws Exception {
        LOG.info("Richclient stops");
    }
}
