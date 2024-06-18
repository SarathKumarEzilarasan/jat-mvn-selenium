package utilities;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class QaEnvProps {

    // No object of QaEnvProps should be created
    // private constructor + static method to initialise an object -> singleton design pattern
    private static Properties properties;

    private QaEnvProps() {

    }

    public static void init(){
        if (properties == null) {
            properties = new Properties();
            try {
                properties.load(new FileInputStream("src/test/resources/env.properties"));
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
    }

    public static String getProperty(String key) {
        return properties.getProperty(key);
    }
}
