package Server;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.Properties;

public class Configurations {
    private static Configurations instance;
    Properties properties;

    // Private constructor to prevent instantiation from outside the class
    private Configurations() {
        try{
            OutputStream outputStream = new FileOutputStream("resources\\config.properties");
            properties = new Properties();
            int num = Runtime.getRuntime().availableProcessors();
            properties.setProperty("threadPoolSize", String.valueOf(num));
            properties.setProperty("mazeGeneratingAlgorithm", "MyMazeGenerator");
            properties.setProperty("mazeSearchingAlgorithm", "BestFirstSearch");
            properties.store(outputStream, null);

        } catch (IOException e){
            e.printStackTrace();
        }
    }

    // Public static method to get the singleton instance
    public static Configurations getInstance() {
        if (instance == null) {
            // Create a new instance only if it doesn't exist
            instance = new Configurations();
        }
        return instance;
    }

    // Other methods of the singleton class
    public void doSomething() {
        // Implementation
    }


}
