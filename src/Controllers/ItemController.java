package Controllers;

import Entities.Item;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class ItemController {
    private final List<Item> itemList = new ArrayList<>();

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

    public Item readItem(UUID itemId) {
        return itemList.stream().filter(item -> item.getId().equals(itemId)).findFirst().orElse(null);
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
        if (itemList.isEmpty()) {
            System.out.println("No items available.");
        }
        return new ArrayList<>(itemList);
    }

    public void setItemList(List<Item> itemList) {
        this.itemList.clear();
        this.itemList.addAll(itemList);
    }
}
