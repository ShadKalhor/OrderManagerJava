import Entities.*;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.io.*;
import java.net.HttpURLConnection;
import java.net.Socket;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.util.Scanner;
import java.util.UUID;
import java.util.*;

public class Main {

    private static final String SERVER_HOST = "localhost"; // Server IP if needed
    private static final int SERVER_PORT = 8081;
    private static final String SERVER_URL = "http://" + SERVER_HOST + ":" + SERVER_PORT;

    private static User loggedInUser;
    private static List<OrderItem> cartItems = new ArrayList<>();
    private static final Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        try {
            Socket socket = new Socket(SERVER_HOST, SERVER_PORT);
            socket.close();
            System.out.println("Connected to the server");
        } catch (IOException ex) {
            System.out.println("Unable to connect to server.");
            return;
        }

        loginUi();
    }

    private static void loginUi() {
        System.out.println("You Are Not Logged in");
        System.out.println("Press 1 to Login");
        System.out.println("Press 2 to Sign Up");
        int option = scanner.nextInt();
        scanner.nextLine();
        if (option == 1) login();
        else if (option == 2) signUp();
    }

    private static void login() {
        System.out.println("Enter Phone Number:");
        String phone = scanner.nextLine();
        System.out.println("Enter Password:");
        String password = scanner.nextLine();

        boolean validLogin = validateLogin(phone, password);
        if (validLogin) {
            System.out.println("Logged In");
            MainMenuUI();
        } else {
            System.out.println("Invalid Login. Press any key to retry.");
            scanner.nextLine();
            login();
        }
    }

    private static boolean validateLogin(String phone, String password) {
        try {
            // Validate login credentials
            URL loginUrl = new URL(SERVER_URL + "/user/validateLogin");
            HttpURLConnection loginConnection = (HttpURLConnection) loginUrl.openConnection();
            loginConnection.setRequestMethod("POST");
            loginConnection.setRequestProperty("Content-Type", "text/plain; charset=UTF-8");
            loginConnection.setDoOutput(true);

            // Send phone and password in the request body
            String loginRequestBody = phone + "," + password;
            try (OutputStream os = loginConnection.getOutputStream()) {
                byte[] input = loginRequestBody.getBytes("UTF-8");
                os.write(input, 0, input.length);
            }

            // Check if login was successful
            if (loginConnection.getResponseCode() == HttpURLConnection.HTTP_OK) {
                System.out.println("Login successful.");

                // Fetch user details by phone number
                URL userUrl = new URL(SERVER_URL + "/user/get?phone=" + phone);
                HttpURLConnection userConnection = (HttpURLConnection) userUrl.openConnection();
                userConnection.setRequestMethod("GET");

                // Read user details from the response
                if (userConnection.getResponseCode() == HttpURLConnection.HTTP_OK) {
                    try (BufferedReader reader = new BufferedReader(new InputStreamReader(userConnection.getInputStream()))) {
                        StringBuilder response = new StringBuilder();
                        String line;
                        while ((line = reader.readLine()) != null) {
                            response.append(line);
                        }

                        // Parse user data from JSON response
                        Gson gson = new Gson();
                        loggedInUser = gson.fromJson(response.toString(), User.class);
                        System.out.println("Logged-in user: " + loggedInUser);
                    }
                    return true;
                } else {
                    System.out.println("Failed to retrieve user details.");
                }
            } else {
                System.out.println("Login failed.");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return false;
    }
    private static void MainMenuUI() {
        System.out.println("\n==== Main Menu ====");
        System.out.println("1. View Available Items");
        System.out.println("2. Add Item to Cart");
        System.out.println("3. View Cart");
        System.out.println("4. Place Order");
        System.out.println("5. Exit");
        System.out.print("Choose an option: ");
        int choice = scanner.nextInt();
        scanner.nextLine();  // Consume newline

        switch (choice) {
            case 1 -> listAvailableItems();
            case 2 -> addItemsToCart();
            case 3 -> viewCart();
            case 4 -> placeOrder();
            case 5 -> System.exit(0);
            default -> {
                System.out.println("Invalid option. Try again.");
                MainMenuUI();
            }
        }
    }

    private static void listAvailableItems() {
        try {
            URL url = new URL(SERVER_URL + "/item/list"); // Adjust endpoint if necessary
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod("GET");

            if (connection.getResponseCode() == HttpURLConnection.HTTP_OK) {
                try (BufferedReader reader = new BufferedReader(new InputStreamReader(connection.getInputStream()))) {
                    StringBuilder response = new StringBuilder();
                    String line;
                    while ((line = reader.readLine()) != null) {
                        response.append(line);
                    }

                    // Parse JSON response to a list of Item objects
                    Gson gson = new Gson();
                    List<Item> items = gson.fromJson(response.toString(), new TypeToken<List<Item>>() {}.getType());

                    // Print each item
                    if (items != null && !items.isEmpty()) {
                        System.out.println("Available Items:");
                        for (Item item : items) {
                            System.out.println(item);
                        }
                    } else {
                        System.out.println("No items available.");
                    }
                }
            } else {
                System.out.println("Failed to retrieve items. Response Code: " + connection.getResponseCode());
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        MainMenuUI();
    }

    private static void addItemsToCart() {
        System.out.println("Press 1 to add an item to your cart");
        System.out.println("Press 2 to go back");
        int choice = scanner.nextInt();
        scanner.nextLine();

        if (choice == 1) {
            System.out.println("Enter the ID of the item:");
            UUID itemId = UUID.fromString(scanner.nextLine());
            Item item = getItemById(itemId);
            // Check if the item exists on the server
            if (item != null) {
                System.out.println("Enter the quantity:");
                int quantity = scanner.nextInt();
                scanner.nextLine();
                OrderItem orderItem = new OrderItem(itemId,quantity, item.getPrice());
                // Add item to the local list
                cartItems.add(orderItem);

                System.out.println("Item successfully added to the local cart.");
                addItemsToCart();
            } else {
                System.out.println("Item with this ID does not exist.");
            }
        } else if (choice == 2) {
            MainMenuUI();
        }
    }

    private static Item getItemById(UUID itemId) {
        try {
            // Use "/item" with the id query parameter, as expected by the server
            URL url = new URL(SERVER_URL + "/item/get?id=" + itemId);
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod("GET");

            // Check if the server returned HTTP OK
            if (connection.getResponseCode() == HttpURLConnection.HTTP_OK) {
                // Read the JSON response from the server
                try (BufferedReader reader = new BufferedReader(new InputStreamReader(connection.getInputStream()))) {
                    StringBuilder response = new StringBuilder();
                    String line;
                    while ((line = reader.readLine()) != null) {
                        response.append(line);
                    }

                    // Convert JSON response to an Item object
                    return new Gson().fromJson(response.toString(), Item.class);
                }
            } else {
                System.out.println("Item not found.");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null; // Return null if the item was not found or an error occurred
    }






    private static void viewCart() {
        if (cartItems.isEmpty()) {
            System.out.println("Your cart is empty.");
        } else {
            System.out.println("\n=== Your Cart ===");
            for (OrderItem item : cartItems) {
                System.out.println(item);
            }
        }
        MainMenuUI();
    }private static void placeOrder() {
        try {
            URL url = new URL(SERVER_URL + "/order/placePendingOrder");
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod("POST");
            connection.setRequestProperty("Content-Type", "application/json; charset=UTF-8");
            connection.setDoOutput(true);

            // Create JSON payload with userId and items
            OrderRequest orderRequest = new OrderRequest(loggedInUser.getId(), cartItems);
            String jsonPayload = new Gson().toJson(orderRequest);

            // Write the JSON payload to the request body
            try (OutputStream os = connection.getOutputStream()) {
                byte[] input = jsonPayload.getBytes("UTF-8");
                os.write(input, 0, input.length);
            }

            // Check if the order was placed successfully
            if (connection.getResponseCode() == HttpURLConnection.HTTP_OK) {
                System.out.println("Order placed successfully.");
            } else {
                System.out.println("Failed to place order. Response code: " + connection.getResponseCode());
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    // Helper class to construct the request payload
    static class OrderRequest {
        UUID userId;
        List<OrderItem> items;

        OrderRequest(UUID userId, List<OrderItem> items) {
            this.userId = userId;
            this.items = items;
        }
    }


    private static void signUp() {
        System.out.println("Enter Name:");
        String name = scanner.nextLine();
        System.out.println("Enter Phone Number:");
        String phone = scanner.nextLine();
        System.out.println("Enter Gender (1 for Male, 2 for Female):");
        int genderChoice = scanner.nextInt();
        scanner.nextLine();
        Utilities.Genders gender = genderChoice == 1 ? Utilities.Genders.Male : Utilities.Genders.Female;
        System.out.println("Enter Password:");
        String password = scanner.nextLine();

        String userData = "45C4CAD3-D78D-4E76-93D2-7DA1C3900969" + "," + name + "," + phone + "," + password + "," + gender;
        try {
            URL url = new URL(SERVER_URL + "/user");
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod("POST");
            connection.setRequestProperty("Content-Type", "text/plain; charset=UTF-8");
            connection.setDoOutput(true);

            try (OutputStream os = connection.getOutputStream()) {
                byte[] input = userData.getBytes(StandardCharsets.UTF_8);
                os.write(input, 0, input.length);
            }

            if (connection.getResponseCode() == HttpURLConnection.HTTP_CREATED) {
                System.out.println("User created successfully.");
                MainMenuUI();
            } else {
                System.out.println("Failed to create user.");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
