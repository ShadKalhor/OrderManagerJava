package CLI;

import Controllers.AddressController;
import Controllers.ItemController;
import Controllers.OrderController;
import Controllers.UserController;
import Entities.Item;
import Entities.OrderItem;
import Entities.User;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.UUID;




public class UserCLI {
    private static final Scanner scanner = new Scanner(System.in);
    private static final UserController userController = new UserController();
    private static final AddressController addressController = new AddressController();
    private static final ItemController itemController = new ItemController();
    private static final OrderController orderController = new OrderController();
    private static User loggedInUser;
    private static List<OrderItem> cartItems = new ArrayList<>();



    public static void MainMenuUI(User currentUser) {
        loggedInUser = currentUser;
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
                MainMenuUI(loggedInUser);
            }
        }
    }

    private static void listAvailableItems() {
        try {
            List<Item> items = itemController.listItems();
            if (items != null && !items.isEmpty()) {
                System.out.println("Available Items:");
                for (Item item : items) {
                    System.out.println(item);
                }
            } else {
                System.out.println("No items available.");
            }
        } catch (IOException e) {
            System.out.println("Error fetching items: " + e.getMessage());
        }
        MainMenuUI(loggedInUser);
    }

    private static void addItemsToCart() {
        try {
            System.out.println("Enter the ID of the item:");
            UUID itemId = UUID.fromString(scanner.nextLine());
            Item item = itemController.getItem(itemId);
            if (item != null) {
                System.out.println("Enter the quantity:");
                int quantity = scanner.nextInt();
                scanner.nextLine();
                OrderItem orderItem = new OrderItem(itemId, quantity, item.getPrice());
                cartItems.add(orderItem);
                System.out.println("Item added to cart.");
            } else {
                System.out.println("Item not found.");
            }
        } catch (IOException e) {
            System.out.println("Error adding item to cart: " + e.getMessage());
        }
        MainMenuUI(loggedInUser);
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
        MainMenuUI(loggedInUser);
    }

    private static void placeOrder() {
        try {
            if (cartItems.isEmpty()) {
                System.out.println("Your cart is empty. Add items before placing an order.");
                return;
            }
            orderController.placePendingOrder(loggedInUser.getId(), cartItems);
            System.out.println("Order placed successfully.");
            cartItems.clear(); // Clear the cart after placing the order
        } catch (IOException e) {
            System.out.println("Error placing order: " + e.getMessage());
        }
        MainMenuUI(loggedInUser);
    }

}
