package utils;


import java.util.Properties;
import java.io.*;

// Tutorial for using properties found at: https://www.javatpoint.com/properties-class-in-java

public class DroneConfigurationLoader {

    private Properties droneProperties;
    private InputStream propFile;

    public DroneConfigurationLoader(String propertiesFilePath) {
        try {
            propFile = DroneConfigurationLoader.class.getClassLoader().getResourceAsStream(propertiesFilePath);
        }
        catch (Exception e) {
            System.out.println("ERROR: properties file not found: " + e.getMessage());
        }
        droneProperties = new Properties();
    }

    public void loadProperties() {
        try {
            droneProperties.load(propFile);
        }
        catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    public Properties getProperties() {
        return droneProperties;
    }

}
