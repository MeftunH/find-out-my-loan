package com.findoutmyloan.application.log;
/* @author - Maftun Hashimli (maftunhashimli@gmail.com)) */

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class SingletonLogger {
    private static SingletonLogger instance;
    private final Logger logger;

    private SingletonLogger() {
        logger=LoggerFactory.getLogger(SingletonLogger.class);
    }

    public static SingletonLogger getInstance() {
        if (instance==null) {
            instance=new SingletonLogger();
        }
        return instance;
    }

    public void info(String message) {
        logger.info(message);
    }

    public void warn(String message) {
        logger.warn(message);
    }

    public void error(String message) {
        logger.error(message);
    }
}
