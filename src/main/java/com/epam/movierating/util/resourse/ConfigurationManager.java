package com.epam.movierating.util.resourse;

import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.MissingResourceException;
import java.util.ResourceBundle;

public class ConfigurationManager {
    /*private static Logger logger = LogManager.getLogger(ConfigurationManager.class);*/

    private ConfigurationManager() {
    }

    public static String getProperty(String key) {
        String res;
        ResourceBundle resourceBundle;
        try {
            resourceBundle = ResourceBundle.getBundle("config");
        } catch (MissingResourceException e) {
            /*logger.log(Level.FATAL, "Exception during reading resourceBundle", e);*/
            throw new IllegalArgumentException("Exception during reading resourceBundle", e);
        }
        try {
            res = resourceBundle.getString(key);
        } catch (MissingResourceException | ExceptionInInitializerError e) {
            /*logger.log(Level.FATAL, "Exception during reading resourceBundle", e);*/
            throw new IllegalArgumentException("Exception during reading resourceBundle", e);
        }
        return res;
    }
}
