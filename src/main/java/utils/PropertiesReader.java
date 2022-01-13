package utils;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class PropertiesReader {
    private String baseUri;
    private String basePath;
    private  static PropertiesReader propertiesReader = null;

    private PropertiesReader(){

    }

    public static PropertiesReader getPropertiesReader(){
        if (propertiesReader == null){
            propertiesReader = new PropertiesReader();
            Properties prop = new Properties();
            try (FileInputStream inputStream = new FileInputStream("src/main/resources/config.properties")){
                prop.load(inputStream);
                propertiesReader.baseUri = prop.getProperty("BASE_URI");
                propertiesReader.basePath = prop.getProperty("BASE_PATH");
            } catch(IOException e){
                e.printStackTrace();
            }
        }
        return propertiesReader;
    }

    public String getBaseUri() {
        return baseUri;
    }

    public String getBasePath() {
        return basePath;
    }
}
