package com.rasto.stockscreener.util;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public interface HasLogger {
    default Logger getLogger(Class clazz) {
        return LoggerFactory.getLogger(clazz.getSimpleName());
    }
}
