package Entities;

import java.util.List;
import java.util.UUID;

public class OrderItem extends AuditableEntity{
    //Removed Item Price Since It is stated in the items entity and there is a relation between them.
    private UUID id;
    private String orderId;
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

    public String getOrderId() {
        return orderId;
    }

    public void setOrderId(String orderId) {
        this.orderId = orderId;
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
