package Controllers;

import Entities.Item;

import java.io.FileReader;
import java.io.IOException;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

public class ItemController {
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
    }
}
