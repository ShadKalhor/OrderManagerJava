package Controllers;

import Entities.*;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class OrderController {
    private final List<Order> orderList = new ArrayList<>();

    public void createOrder(
            String userId,
            String addressId,
            String driverId,
            Utilities.Status status,
            Utilities.DeliveryStatus deliveryStatus,
            List<OrderItem> items,
            double subTotal,
            double deliveryFee,
            double tax,
            String notes) {

        if (userId == null || userId.isEmpty()) {
            System.out.println("Invalid user ID.");
            return;
        }
        if (addressId == null || addressId.isEmpty()) {
            System.out.println("Invalid address ID.");
            return;
        }
        if (driverId == null || driverId.isEmpty()) {
            System.out.println("Invalid driver ID.");
            return;
        }
        if (items == null || items.isEmpty()) {
            System.out.println("Order must contain at least one item.");
            return;
        }
        if (subTotal < 0) {
            System.out.println("Subtotal cannot be negative.");
            return;
        }
        if (deliveryFee < 0) {
            System.out.println("Delivery fee cannot be negative.");
            return;
        }
        if (tax < 0) {
            System.out.println("Tax cannot be negative.");
            return;
        }

        double totalPrice = subTotal + deliveryFee + tax;
        Order newOrder = new Order(UUID.randomUUID(), userId, addressId, driverId, status, deliveryStatus, items, subTotal, deliveryFee, tax, totalPrice, notes);
        orderList.add(newOrder);
        System.out.println("Order created successfully!");
    }

    public Order readOrder(UUID orderId) {
        return orderList.stream()
                .filter(order -> order.id().equals(orderId))
                .findFirst()
                .orElse(null);
    }

    public void updateOrder(
            UUID orderId,
            String userId,
            String addressId,
            String driverId,
            Utilities.Status status,
            Utilities.DeliveryStatus deliveryStatus,
            List<OrderItem> items,
            double subTotal,
            double deliveryFee,
            double tax,
            String notes) {

        if (userId == null || userId.isEmpty()) {
            System.out.println("Invalid user ID.");
            return;
        }
        if (addressId == null || addressId.isEmpty()) {
            System.out.println("Invalid address ID.");
            return;
        }
        if (driverId == null || driverId.isEmpty()) {
            System.out.println("Invalid driver ID.");
            return;
        }
        if (items == null || items.isEmpty()) {
            System.out.println("Order must contain at least one item.");
            return;
        }
        if (subTotal < 0) {
            System.out.println("Subtotal cannot be negative.");
            return;
        }
        if (deliveryFee < 0) {
            System.out.println("Delivery fee cannot be negative.");
            return;
        }
        if (tax < 0) {
            System.out.println("Tax cannot be negative.");
            return;
        }

        for (int i = 0; i < orderList.size(); i++) {
            Order order = orderList.get(i);
            if (order.id().equals(orderId)) {
                double totalPrice = subTotal + deliveryFee + tax;
                Order updatedOrder = new Order(orderId, userId, addressId, driverId, status, deliveryStatus, items, subTotal, deliveryFee, tax, totalPrice, notes);
                orderList.set(i, updatedOrder);
                System.out.println("Order updated successfully!");
                return;
            }
        }
        System.out.println("Order not found.");
    }

    public void deleteOrder(UUID orderId) {
        boolean removed = orderList.removeIf(order -> order.id().equals(orderId));
        if (removed) {
            System.out.println("Order deleted successfully!");
        } else {
            System.out.println("Order not found.");
        }
    }

    public List<Order> listOrders() {
        if (orderList.isEmpty()) {
            System.out.println("No orders found.");
        }
        return new ArrayList<>(orderList);
    }
    public void setOrderList(List<Order> orderList) {
        this.orderList.clear();
        this.orderList.addAll(orderList);
    }

}
