package Entities;

import java.util.List;
import java.util.UUID;

public class OrderItem extends AuditableEntity{

    private UUID id;
    private List<Item> Items;
    private String itemId;
    private int quantity;
    private double totalPrice;

    public OrderItem() {
    }

    // <editor-fold desc="Getters and Setters">

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }
    private List<Item> items;

    // Getter for Items
    public List<Item> getItems() {
        return items;
    }

    // Setter for Items
    public void setItems(List<Item> items) {
        this.items = items;
    }


    public String getItemId() {
        return itemId;
    }

    public void setItemId(String itemId) {
        this.itemId = itemId;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public double getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(double totalPrice) {
        this.totalPrice = totalPrice;
    }

    // </editor-fold>
}
