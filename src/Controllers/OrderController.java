package Controllers;

import Entities.*;
import Repo.DataPersistence;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class OrderController {
    private final List<Order> orderList = new ArrayList<>();

    public void createOrder(Order order) {

        if(isValidOrder(order)) {
            double totalPrice = order.subTotal() + order.deliveryFee() + order.tax();
            Order newOrder = new Order(UUID.randomUUID(), order.userId(), order.addressId(),
                    order.driverId(), order.status(), order.deliveryStatus(), order.items(),
                    order.subTotal(), order.deliveryFee(), order.tax(), totalPrice, order.notes());
            orderList.add(newOrder);
            DataPersistence.saveData(orderList, "orders.json");  // Save the user list to JSON

            System.out.println("Order created successfully!");
        }
    }

    private boolean isValidOrder(Order order) {

        if (order.userId() == null || order.userId().isEmpty()) {
            System.out.println("Invalid user ID.");
            return false;
        }
        if (order.addressId() == null || order.addressId().isEmpty()) {
            System.out.println("Invalid address ID.");
            return false;
        }
        if (order.driverId() == null || order.driverId().isEmpty()) {
            System.out.println("Invalid driver ID.");
            return false;
        }
        if (order.items() == null || order.items().isEmpty()) {
            System.out.println("Order must contain at least one item.");
            return false;
        }
        if (order.subTotal() < 0) {
            System.out.println("Subtotal cannot be negative.");
            return false;
        }
        if (order.deliveryFee() < 0) {
            System.out.println("Delivery fee cannot be negative.");
            return false;
        }
        if (order.tax() < 0) {
            System.out.println("Tax cannot be negative.");
            return false;
        }
        return true;
    }

    public Order ReadOrder(UUID orderId) {
        return orderList.stream()
                .filter(order -> order.id().equals(orderId))
                .findFirst()
                .orElse(null);
    }

    public void UpdateOrder(UUID id, Order order) {
        if(isValidOrder(order)) {
            for (int i = 0; i < orderList.size(); i++) {
                Order currentOrder = orderList.get(i);
                if (currentOrder.id().equals(order.id())) {

                    double totalPrice = order.subTotal() + order.deliveryFee() + order.tax();
                    Order updatedOrder = new Order(UUID.randomUUID(), order.userId(), order.addressId(),
                            order.driverId(), order.status(), order.deliveryStatus(), order.items(),
                            order.subTotal(), order.deliveryFee(), order.tax(), totalPrice, order.notes());
                    orderList.set(i, updatedOrder);

                    System.out.println("Order updated successfully!");
                    return;
                }
            }
        }
        System.out.println("Order not found.");
    }

    public void DeleteOrder(UUID orderId) {
        boolean removed = orderList.removeIf(order -> order.id().equals(orderId));
        if (removed) {
            System.out.println("Order deleted successfully!");
        } else {
            System.out.println("Order not found.");
        }
    }

    public List<Order> ListOrders() {
        if (orderList.isEmpty()) {
            System.out.println("No orders found.");
        }
        return new ArrayList<>(orderList);
    }
    public void SetOrderList(List<Order> orderList) {
        this.orderList.clear();
        this.orderList.addAll(orderList);
    }

}
