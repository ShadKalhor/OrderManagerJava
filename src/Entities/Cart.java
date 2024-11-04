/*
package Entities;

import java.util.List;
import java.util.UUID;

import java.util.List;
import java.util.UUID;

public class Cart {
    private UUID id;
    private UUID userId;
    private int quantity;
    private List<UUID> itemIds;
    private double subTotal;
    // Default constructor
    public Cart() {
    }

    // Parameterized constructor
    public Cart(UUID id, UUID userId, int quantity, UUID itemId, double subTotal) {
        this.id = id;
        this.userId = userId;
        this.quantity = quantity;
        this.itemIds.add(itemId);
        this.subTotal = subTotal;
    }
    // Parameterized constructor
    public Cart( UUID userId , int quantity, UUID itemId) {
        this.id = UUID.randomUUID();
        this.userId = userId;
        this.quantity = quantity;
        this.itemIds.add(itemId);
        this.subTotal = subTotal;
    }

    public Cart(UUID uuid, int quantity, UUID itemId, double subTotal) {
        id = uuid;
        this.quantity = quantity;

    }

    // Getters
    public UUID getId() {
        return id;
    }

    public UUID getUserId() {
        return userId;
    }

    public int getQuantity() {
        return quantity;
    }

    public List<UUID> getItemIds() {
        return itemIds;
    }

    public double getSubTotal() {
        return subTotal;
    }

    // Setters
    public void setId(UUID id) {
        this.id = id;
    }

    public void setUserId(UUID userId) {
        this.userId = userId;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public void addToItemIdList(UUID itemId) {
        this.itemIds.add(itemId);
    }

    public void setSubTotal(double subTotal) {
        this.subTotal = subTotal;
    }

    @Override
    public String toString() {
        return "Cart{" +
                "id=" + id +
                ", userId=" + userId +
                ", quantity=" + quantity +
                ", itemId=" + listItems() +
                ", subTotal=" + subTotal +
                '}';
    }

    private String listItems() {
        return "";
    }

    public void CalculateSubtotal() {

    }
}

*/
