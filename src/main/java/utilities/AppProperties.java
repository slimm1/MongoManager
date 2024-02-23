package utilities;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.FormatStyle;
import java.util.Properties;

/**
 * @author Martin Ramonda
 */
public class AppProperties {

    private Properties properties;
    
    private static AppProperties instance;

    private AppProperties() {
        properties = new Properties();
        try {
            FileInputStream inputStream = new FileInputStream(AppConstants.PROPS_FILE_PATH+AppConstants.PROPS_FILE_NAME);
            properties.load(inputStream);
            inputStream.close();
            System.out.println("Cargada configuración de --> " + properties.getProperty("last_modified"));
        } catch (IOException e) {
            // Si no existe...
            FileManager.createFile(AppConstants.PROPS_FILE_PATH, AppConstants.PROPS_FILE_NAME);
            setDefaultProperties();
        }
    }

    private void setDefaultProperties() {
        properties.setProperty(AppConstants.PROP_HOST, "localhost");
        properties.setProperty(AppConstants.PROP_PORT, "57017");
        properties.setProperty(AppConstants.PROP_DB, "test");
        properties.setProperty(AppConstants.PROP_USER_COLLECTION, "user");
        properties.setProperty(AppConstants.PROP_SHOW_COLLECTION, "show");
        properties.setProperty(AppConstants.PROP_MODIFIED, LocalDateTime.now().format(DateTimeFormatter.ofLocalizedDateTime(FormatStyle.FULL, FormatStyle.MEDIUM)));
        saveProperties();
        System.out.println("Cargada configuración por defecto...");
    }

    public String getProperty(String key) {
        return properties.getProperty(key);
    }

    public void setProperty(String key, String value) {
        properties.setProperty(key, value);
        saveProperties();
    }

    private void saveProperties() {
        try {
            FileOutputStream outputStream = new FileOutputStream(AppConstants.PROPS_FILE_PATH+AppConstants.PROPS_FILE_NAME);
            properties.store(outputStream, null);
            
            outputStream.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    
    public static AppProperties getInstance(){
        if(instance == null){
            instance = new AppProperties();
        }
        return instance;
    }
}
