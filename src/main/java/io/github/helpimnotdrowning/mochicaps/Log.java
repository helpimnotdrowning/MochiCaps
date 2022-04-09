package io.github.helpimnotdrowning.mochicaps;

import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;


public class Log {
    private final Logger logger;

    public Log(String name) {
        this.logger = LogManager.getLogger(name);
    }

    public Log(Class cls) {
        this.logger = LogManager.getLogger(cls.getSimpleName());
    }

    public void print(String message) {
        System.out.println(message);
    }

    public void debug(String message) {
        logger.log(Level.DEBUG, message);
    }

    public void info(String message) {
        logger.log(Level.INFO, message);
    }

    public void warn(String message) {
        logger.log(Level.WARN, message);
    }

    public void error(String message) {
        logger.log(Level.ERROR, message);
    }
}
