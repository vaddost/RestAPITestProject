package utils;

import com.google.gson.Gson;

import java.io.FileInputStream;
import java.io.FileReader;
import java.io.IOException;

public class JsonToObjectParser <T>{
    public T parseJsonFileToObject(String filePath, Class<T> clazz){
        Gson gson = new Gson();
        T parsedJson = null;
        try (FileReader fileReader = new FileReader(filePath)){
            parsedJson = gson.fromJson(fileReader, clazz);
        } catch (IOException e){
            e.printStackTrace();
        }

        return parsedJson;
    }
}
