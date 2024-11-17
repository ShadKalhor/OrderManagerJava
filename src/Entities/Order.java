package Entities;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class Order {
    private UUID id;
    private UUID userId;
    private UUID addressId;
    private UUID driverId;
    private Utilities.Status status;
    private Utilities.DeliveryStatus deliveryStatus;
    private List<OrderItem> items;
    private double subTotal;
    private double deliveryFee;
    private double tax;
    private double totalPrice;
    private String notes;

    public Order() {
        id = UUID.randomUUID();
        addressId = UUID.randomUUID();
        status = Utilities.Status.Pending;
    }
/*


    public Order(UUID id, String userId, String addressId, String driverId, Utilities.Status status,
                 Utilities.DeliveryStatus deliveryStatus, List<OrderItem> items, double subTotal,
                 double deliveryFee, double tax, double totalPrice, String notes) {
        this.id = id;
        this.userId = userId;
        this.addressId = addressId;
        this.driverId = driverId;
        this.status = status;
        this.deliveryStatus = deliveryStatus;
        this.items = items;
        this.subTotal = subTotal;
        this.deliveryFee = deliveryFee;
        this.tax = tax;
        this.totalPrice = totalPrice;
        this.notes = notes;
    }

*/
    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public UUID getUserId() {
        return userId;
    }

    public void setUserId(UUID userId) {
        this.userId = userId;
    }

    public UUID getAddressId() {
        return addressId;
    }

    public void setAddressId(UUID addressId) {
        this.addressId = addressId;
    }

    public UUID getDriverId() {
        return driverId;
    }

    public void setDriverId(UUID driverId) {
        this.driverId = driverId;
    }

    public Utilities.Status getStatus() {
        return status;
    }

    public void setStatus(Utilities.Status status) {
        this.status = status;
    }

    public Utilities.DeliveryStatus getDeliveryStatus() {
        return deliveryStatus;
    }

    public void setDeliveryStatus(Utilities.DeliveryStatus deliveryStatus) {
        this.deliveryStatus = deliveryStatus;
    }

    public List<OrderItem> getItems() {
        return items;
    }

    public void setItems(List<OrderItem> items) {
        this.items = items;
    }
    public void addItem(OrderItem item) {
        if(items == null)
            items = new ArrayList<>();
        this.items.add(item);
    }

    public double getSubTotal() {
        return subTotal;
    }

    public void setSubTotal(double subTotal) {
        this.subTotal = subTotal;
    }

    public double getDeliveryFee() {
        return deliveryFee;
    }

    public void setDeliveryFee(double deliveryFee) {
        this.deliveryFee = deliveryFee;
    }

    public double getTax() {
        return tax;
    }

    public void setTax(double tax) {
        this.tax = tax;
    }

    public double getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(double totalPrice) {
        this.totalPrice = totalPrice;
    }

    public String getNotes() {
        return notes;
    }

    public void setNotes(String notes) {
        this.notes = notes;
    }

    @Override
    public String toString() {StringBuilder sb = new StringBuilder();
        sb.append("Order Details:\n");
        sb.append("ID: ").append(id).append("\n");
        sb.append("User ID: ").append(userId).append("\n");
        sb.append("Address ID: ").append(addressId).append("\n");
        sb.append("Driver ID: ").append(driverId).append("\n");
        sb.append("Status: ").append(status).append("\n");
        sb.append("Delivery Status: ").append(deliveryStatus).append("\n");
        sb.append("SubTotal: ").append(subTotal).append("\n");
        sb.append("Delivery Fee: ").append(deliveryFee).append("\n");
        sb.append("Tax: ").append(tax).append("\n");
        sb.append("Total Price: ").append(totalPrice).append("\n");
        sb.append("Notes: ").append(notes).append("\n");

        sb.append("Items:\n");
        if (items != null && !items.isEmpty()) {
            for (OrderItem item : items) {
                sb.append("  ").append(item).append("\n");
            }
        } else {
            sb.append("  No items in this order.\n");
        }

        return sb.toString();
    }
}
