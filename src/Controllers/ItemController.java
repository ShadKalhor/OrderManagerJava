package Controllers;

import Entities.Item;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class ItemController {
    private final List<Item> itemList = new ArrayList<>();

    public void createItem(String name, String description, double price, String size, double discount, boolean isAvailable, int quantity) {
        if (name == null || name.isEmpty()) {
            System.out.println("Invalid name.");
            return;
        }
        if (description == null || description.isEmpty()) {
            System.out.println("Invalid description.");
            return;
        }
        if (price < 0) {
            System.out.println("Price cannot be negative.");
            return;
        }
        if (size == null || size.isEmpty()) {
            System.out.println("Invalid size.");
            return;
        }
        if (discount < 0 || discount > 1) {
            System.out.println("Discount must be between 0 and 1.");
            return;
        }
        if (quantity < 0) {
            System.out.println("Quantity cannot be negative.");
            return;
        }

        Item newItem = new Item(UUID.randomUUID(), name, description, price, size, discount, isAvailable, quantity);
        itemList.add(newItem);
        System.out.println("Item created successfully!");
    }

    public Item readItem(UUID itemId) {
        return itemList.stream().filter(item -> item.id().equals(itemId)).findFirst().orElse(null);
    }

    public void updateItem(UUID itemId, String name, String description, double price, String size, double discount, boolean isAvailable, int quantity) {
        if (name == null || name.isEmpty()) {
            System.out.println("Invalid name.");
            return;
        }
        if (description == null || description.isEmpty()) {
            System.out.println("Invalid description.");
            return;
        }
        if (price < 0) {
            System.out.println("Price cannot be negative.");
            return;
        }
        if (size == null || size.isEmpty()) {
            System.out.println("Invalid size.");
            return;
        }
        if (discount < 0 || discount > 1) {
            System.out.println("Discount must be between 0 and 1.");
            return;
        }
        if (quantity < 0) {
            System.out.println("Quantity cannot be negative.");
            return;
        }

        for (int i = 0; i < itemList.size(); i++) {
            Item item = itemList.get(i);
            if (item.id().equals(itemId)) {
                Item updatedItem = new Item(itemId, name, description, price, size, discount, isAvailable, quantity);
                itemList.set(i, updatedItem);
                System.out.println("Item updated successfully!");
                return;
            }
        }
        System.out.println("Item not found.");
    }

    public void deleteItem(UUID itemId) {
        boolean removed = itemList.removeIf(item -> item.id().equals(itemId));
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
