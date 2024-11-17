package Controllers;

import com.google.gson.Gson;

import java.io.IOException;
import java.util.List;
import java.util.UUID;


import Entities.Order;
import Entities.OrderItem;
import com.google.gson.reflect.TypeToken;

import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;


public class OrderController {
    /*private List<Order> orderList = new ArrayList<>();

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
            */
    /*double totalPrice = order.subTotal() + order.deliveryFee() + order.tax();
            Order newOrder = new Order(UUID.randomUUID(), order.userId(), order.addressId(),
                    order.driverId(), order.status(), order.deliveryStatus(), order.items(),
                    order.subTotal(), order.deliveryFee(), order.tax(), totalPrice, order.notes());
            orderList.add(newOrder);
            DataPersistence.saveData(orderList, "orders.json");  // Save the user list to JSON

            System.out.println("Order created successfully!");*/
    /*
        }
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

    }*/
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

    private static final String BASE_URL = "http://localhost:8081/order";
    private final Gson gson = new Gson();

    // Create a new order
    public String createOrder(Order order) throws IOException {
        URL url = new URL(BASE_URL + "/create");
        HttpURLConnection connection = (HttpURLConnection) url.openConnection();
        connection.setRequestMethod("POST");
        connection.setRequestProperty("Content-Type", "application/json");
        connection.setDoOutput(true);

        try (OutputStream os = connection.getOutputStream()) {
            os.write(gson.toJson(order).getBytes());
            os.flush();
        }

        return readResponse(connection);
    }

    // Get an order by ID
    public Order getOrder(UUID orderId) throws IOException {
        URL url = new URL(BASE_URL + "/get?id=" + orderId);
        HttpURLConnection connection = (HttpURLConnection) url.openConnection();
        connection.setRequestMethod("GET");

        String response = readResponse(connection);
        return gson.fromJson(response, Order.class);
    }


    public List<Order> listOrders() throws IOException {
        URL url = new URL(BASE_URL + "/list"); // Define the endpoint for listing orders
        HttpURLConnection connection = (HttpURLConnection) url.openConnection();
        connection.setRequestMethod("GET");

        try (BufferedReader reader = new BufferedReader(new InputStreamReader(connection.getInputStream()))) {
            if (connection.getResponseCode() == HttpURLConnection.HTTP_OK) {
                StringBuilder response = new StringBuilder();
                String line;
                while ((line = reader.readLine()) != null) {
                    response.append(line);
                }

                // Parse the JSON response into a List<Order>
                return gson.fromJson(response.toString(), new TypeToken<List<Order>>() {}.getType());
            } else {
                throw new IOException("Failed to retrieve orders. HTTP Code: " + connection.getResponseCode());
            }
        } finally {
            connection.disconnect();
        }
    }
    // Update an existing order
    public String updateOrder(UUID orderId, Order updatedOrder) throws IOException {
        URL url = new URL(BASE_URL + "/update?id=" + orderId);
        HttpURLConnection connection = (HttpURLConnection) url.openConnection();
        connection.setRequestMethod("PUT");
        connection.setRequestProperty("Content-Type", "application/json");
        connection.setDoOutput(true);

        try (OutputStream os = connection.getOutputStream()) {
            os.write(gson.toJson(updatedOrder).getBytes());
            os.flush();
        }

        return readResponse(connection);
    }

    // Delete an order by ID
    public String deleteOrder(UUID orderId) throws IOException {
        URL url = new URL(BASE_URL + "/delete?id=" + orderId);
        HttpURLConnection connection = (HttpURLConnection) url.openConnection();
        connection.setRequestMethod("DELETE");

        return readResponse(connection);
    }

    // Add item to a pending order
    public String addItemToPendingOrder(UUID userId, List<OrderItem> items) throws IOException {
        URL url = new URL(BASE_URL + "/addItemToPendingOrder");
        HttpURLConnection connection = (HttpURLConnection) url.openConnection();
        connection.setRequestMethod("POST");
        connection.setRequestProperty("Content-Type", "application/json");
        connection.setDoOutput(true);

        String requestData = gson.toJson(new OrderRequest(userId, items));
        try (OutputStream os = connection.getOutputStream()) {
            os.write(requestData.getBytes());
            os.flush();
        }

        return readResponse(connection);
    }

    // Place a pending order
    public String placePendingOrder(UUID userId, List<OrderItem> items) throws IOException {
        URL url = new URL(BASE_URL + "/placePendingOrder");
        HttpURLConnection connection = (HttpURLConnection) url.openConnection();
        connection.setRequestMethod("POST");
        connection.setRequestProperty("Content-Type", "application/json");
        connection.setDoOutput(true);

        String requestData = gson.toJson(new OrderRequest(userId, items));
        try (OutputStream os = connection.getOutputStream()) {
            os.write(requestData.getBytes());
            os.flush();
        }

        return readResponse(connection);
    }

    // Print cart for a user
    public String printCart(UUID userId) throws IOException {
        URL url = new URL(BASE_URL + "/printCart?userId=" + userId);
        HttpURLConnection connection = (HttpURLConnection) url.openConnection();
        connection.setRequestMethod("GET");

        return readResponse(connection);
    }

    // Helper method to read the response from the server
    private String readResponse(HttpURLConnection connection) throws IOException {
        int responseCode = connection.getResponseCode();
        InputStream inputStream = (responseCode >= 200 && responseCode < 300)
                ? connection.getInputStream()
                : connection.getErrorStream();

        try (BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream))) {
            StringBuilder response = new StringBuilder();
            String line;

            while ((line = reader.readLine()) != null) {
                response.append(line);
            }

            return response.toString();
        } finally {
            connection.disconnect();
        }
    }

    // Helper class for request payloads
    static class OrderRequest {
        UUID userId;
        List<OrderItem> items;

        public OrderRequest(UUID userId, List<OrderItem> items) {
            this.userId = userId;
            this.items = items;
        }
    }


}
