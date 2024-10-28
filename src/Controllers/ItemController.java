package Controllers;

import Entities.Item;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class ItemController {
    private final List<Item> itemList = new ArrayList<>();

    public void CreateItem(Item item) {
        if(isValidItem(item)) {
            Item newItem = new Item(UUID.randomUUID(), item.name(), item.description(), item.price(), item.size(), item.discount(),
                    item.isAvailable(), item.quantity());
            itemList.add(newItem);
            System.out.println("Item created successfully!");
        }
    }

    private boolean isValidItem(Item item) {
        if (item.name() == null || item.name().isEmpty()) {
            System.out.println("Invalid name.");
            return false;
        }
        if (item.description() == null || item.description().isEmpty()) {
            System.out.println("Invalid description.");
            return false;
        }
        if (item.price() < 0) {
            System.out.println("Price cannot be negative.");
            return false;
        }
        if (item.size() == null || item.size().isEmpty()) {
            System.out.println("Invalid size.");
            return false;
        }
        if (item.discount() < 0 || item.discount() > 1) {
            System.out.println("Discount must be between 0 and 1.");
            return false;
        }
        if (item.quantity() < 0) {
            System.out.println("Quantity cannot be negative.");
            return false;
        }
        return true;
    }

    public Item ReadItem(UUID itemId) {
        return itemList.stream().filter(item -> item.id().equals(itemId)).findFirst().orElse(null);
    }

    public void UpdateItem(UUID itemId,Item item) {

        if(isValidItem(item)) {
            for (int i = 0; i < itemList.size(); i++) {
                Item currentItem = itemList.get(i);
                if (item.id().equals(itemId)) {
                    Item updatedItem = new Item(UUID.randomUUID(), item.name(), item.description(), item.price(), item.size(), item.discount(),
                            item.isAvailable(), item.quantity());
                    itemList.set(i, updatedItem);
                    System.out.println("Item updated successfully!");
                    return;
                }
            }
            System.out.println("Item not found.");
        }
    }

    public void DeleteItem(UUID itemId) {
        boolean removed = itemList.removeIf(item -> item.id().equals(itemId));
        if (removed) {
            System.out.println("Item deleted successfully!");
        } else {
            System.out.println("Item not found.");
        }
    }

    public List<Item> ListItems() {
        if (itemList.isEmpty()) {
            System.out.println("No items available.");
        }
        return new ArrayList<>(itemList);
    }
    public void SetItemList(List<Item> itemList) {
        this.itemList.clear();
        this.itemList.addAll(itemList);
    }

}
