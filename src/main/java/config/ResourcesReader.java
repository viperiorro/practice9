package config;

import lombok.NoArgsConstructor;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

@NoArgsConstructor
public class ResourcesReader {
    public Properties loadPropertiesFile(String filePath) {

        Properties properties = new Properties();

        try (InputStream resourceAsStream = getClass().getClassLoader().getResourceAsStream(filePath)) {
            properties.load(resourceAsStream);
        } catch (IOException e) {
            System.out.println("Unable to load properties file : " + filePath);
        }
        return properties;
    }
}
