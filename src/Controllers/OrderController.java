package Controllers;

import Entities.*;
import Repo.DataPersistence;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

public class OrderController {
    private List<Order> orderList = new ArrayList<>();

    public void AddItemToPendingOrder(OrderItem orderItem, User loggedInUser){
        loadOrders();
            Order selectedOrder = orderList.stream()
                    .filter(e -> e.getStatus().equals(Utilities.Status.Pending)
                            && (e.getUserId().equals(loggedInUser.getId())))
                    .findFirst().orElse(null);
            if (selectedOrder == null) {
                selectedOrder = new Order();
                selectedOrder.setUserId(loggedInUser.getId());
                selectedOrder.addItem(orderItem);
                CreateOrder(selectedOrder);
            } else {
                selectedOrder.addItem(orderItem);
                UpdateOrder(selectedOrder);
            }
    }
    private Order getOrderByUser(User loggedInUser){
        loadOrders();
        return orderList.stream().filter(e -> e.getUserId().equals(loggedInUser.getId()) && e.getStatus().equals(Utilities.Status.Pending)).findFirst().orElse(null);
    }
    public void PrintCart(User loggedInUser){
        Order order = getOrderByUser(loggedInUser);
        if (order != null){
            List<OrderItem> items = order.getItems();
            if (items == null || items.isEmpty()) {
                System.out.println("Your cart is empty.");
                return;
            }

            System.out.println("Items in your cart:");
            for (OrderItem item : items) {
                System.out.println("Item ID: " + item.getItemId());
                System.out.println("Quantity: " + item.getQuantity());
                System.out.println("Total Price: $" + item.getTotalPrice());
                System.out.println("---------------------------");
            }
        }

    }

    public void UpdateOrder(Order selectedOrder) {
        loadOrders();
        orderList.stream()
                .filter(e -> e.getId().equals(selectedOrder.getId()))
                .findFirst()
                .ifPresent(order -> {
                    order.setUserId(selectedOrder.getUserId());
                    order.setAddressId(selectedOrder.getAddressId());
                    order.setDriverId(selectedOrder.getDriverId());
                    order.setStatus(selectedOrder.getStatus());
                    order.setDeliveryStatus(selectedOrder.getDeliveryStatus());
                    order.setItems(selectedOrder.getItems());
                    order.setSubTotal(selectedOrder.getSubTotal());
                    order.setDeliveryFee(selectedOrder.getDeliveryFee());
                    order.setTax(selectedOrder.getTax());
                    order.setTotalPrice(selectedOrder.getTotalPrice());
                    order.setNotes(selectedOrder.getNotes());
                });;
                saveOrderToJson(orderList);

    }

    private void loadOrders() {
        Gson gson = new Gson();
        try (FileReader reader = new FileReader("orders.json")) {
            // Define the collection type
            Type orderListType = new TypeToken<List<Order>>() {}.getType();

            // Deserialize JSON file to list of Item objects
            orderList = gson.fromJson(reader, orderListType);
            if(orderList == null)
                orderList = new ArrayList<>();

        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    public void CreateOrder(Order order) {
        saveOrderToJson(order);
        //if(isValidOrder(order)) {
            /*double totalPrice = order.subTotal() + order.deliveryFee() + order.tax();
            Order newOrder = new Order(UUID.randomUUID(), order.userId(), order.addressId(),
                    order.driverId(), order.status(), order.deliveryStatus(), order.items(),
                    order.subTotal(), order.deliveryFee(), order.tax(), totalPrice, order.notes());
            orderList.add(newOrder);
            DataPersistence.saveData(orderList, "orders.json");  // Save the user list to JSON

            System.out.println("Order created successfully!");*/
        //}
    }

    private boolean saveOrderToJson(Order order) {
        Gson gson = new Gson();
        List<Order> orders = new ArrayList<>();

        loadOrders();
        orders = orderList;
        orders.add(order);

        try (FileWriter fileWriter = new FileWriter("orders.json")) {
            gson.toJson(orders, fileWriter);
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }
        return true;

    }

    private boolean saveOrderToJson(List<Order> newOrders) {
        Gson gson = new Gson();
        List<Order> orders = null;

        loadOrders();
        orders = newOrders;

        try (FileWriter fileWriter = new FileWriter("orders.json")) {
            gson.toJson(orders, fileWriter);
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }
        return true;

    }

    public void PlacePendingOrder(User loggedInUser) {
        loadOrders();
        Order selectedOrder = orderList.stream()
                .filter(e -> e.getStatus().equals(Utilities.Status.Pending)
                        && (e.getUserId().equals(loggedInUser.getId())))
                .findFirst().orElse(null);
        if(selectedOrder == null){
            System.out.println("Cant place the order,The Cart is Empty");
            return;
        }else {
            selectedOrder.setStatus(Utilities.Status.Confirmed);
            UpdateOrder(selectedOrder);
        }

    }
/*

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
*/
/*

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

*/

}
