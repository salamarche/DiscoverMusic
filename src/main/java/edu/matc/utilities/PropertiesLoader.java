package edu.matc.utilities;

import java.io.IOException;
import java.util.Properties;

/**
 * Properties Loader interface
 */
public interface PropertiesLoader {

    /**
     * Loads in a properties file and returns properties
     * @param propertiesFilePath file path location of properties
     * @return properties
     * @throws Exception
     */
    default Properties loadProperties(String propertiesFilePath) throws Exception {
        Properties properties = new Properties();
        try {
            properties.load(this.getClass().getResourceAsStream(propertiesFilePath));
        } catch (IOException ioException) {
            ioException.printStackTrace();
            throw ioException;
        } catch (Exception exception) {
            exception.printStackTrace();
            throw exception;
        }
        return properties;
    }

}
