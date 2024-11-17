package Controllers;

import Entities.Item;

import java.io.IOException;
import java.lang.reflect.Type;
import java.net.URL;
import java.util.List;
import java.util.UUID;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.io.*;
import java.net.HttpURLConnection;

public class ItemController {
    /*
    private List<Item> itemList = new ArrayList<>();

    public void createItem(Item item) {
        if (isValidItem(item)) {
            // Create a new item with a new UUID
            Item newItem = new Item(UUID.randomUUID(), item.getName(), item.getDescription(), item.getPrice(),
                    item.getSize(), item.getDiscount(), item.isAvailable(), item.getQuantity());
            itemList.add(newItem);
            System.out.println("Item created successfully!");
        }
    }

    private boolean isValidItem(Item item) {
        if (item.getName() == null || item.getName().isEmpty()) {
            System.out.println("Invalid name.");
            return false;
        }
        if (item.getDescription() == null || item.getDescription().isEmpty()) {
            System.out.println("Invalid description.");
            return false;
        }
        if (item.getPrice() < 0) {
            System.out.println("Price cannot be negative.");
            return false;
        }
        if (item.getSize() == null || item.getSize().isEmpty()) {
            System.out.println("Invalid size.");
            return false;
        }
        if (item.getDiscount() < 0 || item.getDiscount() > 1) {
            System.out.println("Discount must be between 0 and 1.");
            return false;
        }
        if (item.getQuantity() < 0) {
            System.out.println("Quantity cannot be negative.");
            return false;
        }
        return true;
    }

    public Item getItemById(UUID itemId) {
        loadItems();
        return itemList.stream().
                filter(item -> item.getId().equals(itemId))
                .findFirst()
                .orElse(null);
    }

    public void updateItem(UUID itemId, Item item) {
        if (isValidItem(item)) {
            for (int i = 0; i < itemList.size(); i++) {
                Item currentItem = itemList.get(i);
                if (currentItem.getId().equals(itemId)) {
                    // Create a new item with the existing UUID
                    Item updatedItem = new Item(itemId, item.getName(), item.getDescription(), item.getPrice(),
                            item.getSize(), item.getDiscount(), item.isAvailable(), item.getQuantity());
                    itemList.set(i, updatedItem);
                    System.out.println("Item updated successfully!");
                    return;
                }
            }
            System.out.println("Item not found.");
        }
    }

    public void deleteItem(UUID itemId) {
        boolean removed = itemList.removeIf(item -> item.getId().equals(itemId));
        if (removed) {
            System.out.println("Item deleted successfully!");
        } else {
            System.out.println("Item not found.");
        }
    }

    public List<Item> listItems() {
        loadItems();

        if (itemList == null || itemList.isEmpty()) {
            System.out.println("No items available.");
        }
        return new ArrayList<>(itemList);
    }

    private void loadItems() {
        Gson gson = new Gson();
        try (FileReader reader = new FileReader("items.json")) {
            // Define the collection type
            Type itemListType = new TypeToken<List<Item>>() {}.getType();

            // Deserialize JSON file to list of Item objects
            itemList = gson.fromJson(reader, itemListType);

            // Print each item using the overridden toString() method
            for (Item item : itemList) {
                System.out.println(item);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void setItemList(List<Item> itemList) {
        this.itemList.clear();
        this.itemList.addAll(itemList);
    }

    public void PrintItemList(List<Item> items){

    }

    public List<Item> LoadItemsById(List<UUID> itemIds) {
        loadItems();
        return itemList.stream()
                .filter(item -> itemIds.contains(item.getId()))
                .collect(Collectors.toList());
    }*/
    private static final String BASE_URL = "http://localhost:8081/item";
    private final Gson gson = new Gson();

    // Create a new item
    public String createItem(String name, String description, double price, String size, double discount, boolean isAvailable, int quantity) throws IOException {
        URL url = new URL(BASE_URL + "/create");
        HttpURLConnection connection = (HttpURLConnection) url.openConnection();
        connection.setRequestMethod("POST");
        connection.setRequestProperty("Content-Type", "text/plain");
        connection.setDoOutput(true);

        String requestData = String.join(",", name, description, String.valueOf(price), size, String.valueOf(discount), String.valueOf(isAvailable), String.valueOf(quantity));
        try (OutputStream os = connection.getOutputStream()) {
            os.write(requestData.getBytes());
            os.flush();
        }

        return readResponse(connection);
    }

    // Get an item by ID
    public Item getItem(UUID itemId) throws IOException {
        URL url = new URL(BASE_URL + "/get?id=" + itemId);
        HttpURLConnection connection = (HttpURLConnection) url.openConnection();
        connection.setRequestMethod("GET");

        String response = readResponse(connection);
        return gson.fromJson(response, Item.class);
    }

    // Update an existing item
    public String updateItem(UUID itemId, String name, String description, double price, String size, double discount, boolean isAvailable, int quantity) throws IOException {
        URL url = new URL(BASE_URL + "/update?id=" + itemId);
        HttpURLConnection connection = (HttpURLConnection) url.openConnection();
        connection.setRequestMethod("PUT");
        connection.setRequestProperty("Content-Type", "text/plain");
        connection.setDoOutput(true);

        String requestData = String.join(",", name, description, String.valueOf(price), size, String.valueOf(discount), String.valueOf(isAvailable), String.valueOf(quantity));
        try (OutputStream os = connection.getOutputStream()) {
            os.write(requestData.getBytes());
            os.flush();
        }

        return readResponse(connection);
    }

    // Delete an item by ID
    public String deleteItem(UUID itemId) throws IOException {
        URL url = new URL(BASE_URL + "/delete?id=" + itemId);
        HttpURLConnection connection = (HttpURLConnection) url.openConnection();
        connection.setRequestMethod("DELETE");

        return readResponse(connection);
    }

    // List all items
    public List<Item> listItems() throws IOException {
        URL url = new URL(BASE_URL + "/list");
        HttpURLConnection connection = (HttpURLConnection) url.openConnection();
        connection.setRequestMethod("GET");

        String response = readResponse(connection);
        Type listType = new TypeToken<List<Item>>() {}.getType();
        return gson.fromJson(response, listType);
    }

    // Helper method to read the response from the server
    private String readResponse(HttpURLConnection connection) throws IOException {
        int responseCode = connection.getResponseCode();
        InputStream inputStream = (responseCode >= 200 && responseCode < 300)
                ? connection.getInputStream()
                : connection.getErrorStream();

        try (BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream))) {
            StringBuilder response = new StringBuilder();
            String line;

            while ((line = reader.readLine()) != null) {
                response.append(line);
            }

            return response.toString();
        } finally {
            connection.disconnect();
        }
    }
}
