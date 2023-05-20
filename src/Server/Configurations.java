package Server;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.Properties;

public class Configurations {
    private static Configurations instance;
    Properties properties;

    /**
     * Private constructor to prevent instantiation from outside the class.
     * Initializes the configurations and stores them in a properties file.
     */
    private Configurations() {
        try {
            OutputStream outputStream = new FileOutputStream("resources\\config.properties");
            properties = new Properties();
            int size = Runtime.getRuntime().availableProcessors();
            properties.setProperty("threadPoolSize", String.valueOf(size));
            properties.setProperty("mazeGeneratingAlgorithm", "MyMazeGenerator");
            properties.setProperty("mazeSearchingAlgorithm", "BestFirstSearch");
            properties.store(outputStream, null);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Returns the singleton instance of the Configurations class.
     * @return the Configurations instance
     */
    public static Configurations getInstance() {
        if (instance == null) {
            // Create a new instance only if it doesn't exist
            instance = new Configurations();
        }
        return instance;
    }
}
