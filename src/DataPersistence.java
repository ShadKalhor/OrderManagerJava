import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class DataPersistence {
    private static final Gson gson = new Gson();

    // Save data to a JSON file
    public static <T> void saveData(List<T> dataList, String filename) {
        try (FileWriter writer = new FileWriter(filename)) {
            gson.toJson(dataList, writer);
            System.out.println("Data saved to " + filename);
        } catch (IOException e) {
            System.out.println("Error saving data: " + e.getMessage());
        }
    }

    // Load data from a JSON file
    public static <T> List<T> loadData(String filename, TypeToken<List<T>> typeToken) {
        try (FileReader reader = new FileReader(filename)) {
            return gson.fromJson(reader, typeToken.getType());
        } catch (FileNotFoundException e) {
            System.out.println("File not found: " + filename + ". Starting with an empty list.");
            return new ArrayList<>();
        } catch (IOException e) {
            System.out.println("Error loading data: " + e.getMessage());
            return new ArrayList<>();
        }
    }
}
