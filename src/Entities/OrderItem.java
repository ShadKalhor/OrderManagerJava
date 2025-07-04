package Entities;

import java.util.UUID;

public class OrderItem {
    private UUID id;
    private UUID itemId;
    private int quantity;
    private double totalPrice;

    public OrderItem() {}
    public OrderItem(UUID itemId, int quantity, double price){
        this.id = UUID.randomUUID();
        this.itemId = itemId;
        this.quantity = quantity;
        this.totalPrice = calculateTotalPrice(price);
    }

    private double calculateTotalPrice(double price) {
        return quantity*price;
    }


    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public UUID getItemId() {
        return itemId;
    }

    public void setItemId(UUID itemId) {
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

    @Override
    public String toString() {
        return
                "id=" + id +
                        ", itemId='" + itemId + '\'' +
                        ", quantity='" + quantity + '\'' +
                        ", totalPrice=" + totalPrice;

    }
}
