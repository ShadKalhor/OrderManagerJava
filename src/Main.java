import Controllers.UserController;
import Entities.*;
import Controllers.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.UUID;
import Controllers.*;
//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    // Initialize some sample items for ordering

    private static final UserController userController = new UserController();
    private static final ItemController itemController = new ItemController();
    private static final CartController cartController = new CartController();
    private static final OrderController orderController = new OrderController();
    private final List<User> userList = new ArrayList<>();
    private static User loggedInUser;
    private static final Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        initializeItems(); // Create some sample items
        boolean running = true;

        while (running) {
            if (loggedInUser == null) {
                loginUser(); // Log in before accessing the system
            } else {
                System.out.println("\n==== Main Menu ====");
                System.out.println("1. View Available Items");
                System.out.println("2. Add Item to Cart");
                System.out.println("3. View Cart");
                System.out.println("4. Place Order");
                System.out.println("5. Exit");
                System.out.print("Choose an option: ");
                int choice = scanner.nextInt();
                scanner.nextLine();  // Consume newline

                if (choice == 1) {
                    listAvailableItems();
                } else if (choice == 2) {
                    addItemToCart();
                } else if (choice == 3) {
                    viewCart();
                } else if (choice == 4) {
                    placeOrder();
                } else if (choice == 5) {
                    running = false;
                } else {
                    System.out.println("Invalid option. Try again.");
                }
            }
        }
    }

    // Initialize some sample items for ordering
    // Initialize some sample items for ordering
    private static void initializeItems() {
        itemController.createItem("Laptop", "A high-performance laptop", 1200.00, "15 inch", 0.10, true, 10);
        itemController.createItem("Phone", "A smartphone with 5G connectivity", 800.00, "6 inch", 0.05, true, 20);
        itemController.createItem("Headphones", "Noise-cancelling wireless headphones", 200.00, "One size", 0.00, true, 15);
        itemController.createItem("Monitor", "27-inch 4K UHD monitor", 500.00, "27 inch", 0.15, true, 7);
        itemController.createItem("Keyboard", "Mechanical RGB keyboard", 100.00, "One size", 0.20, true, 12);
        itemController.createItem("Mouse", "Wireless ergonomic mouse", 50.00, "One size", 0.00, true, 30);
    }


    private static void loginUser() {
        System.out.println("\n=== User Login ===");
        System.out.print("Enter your name: ");
        String name = scanner.nextLine();
        System.out.print("Enter your phone number: ");
        String phone = scanner.nextLine();

        // Create a default user for simplicity
        loggedInUser = new User(UUID.randomUUID(), "userRoleId", name, phone, "password", Utilities.Genders.Male);
        System.out.println("Logged in as " + loggedInUser.name());
    }

    private static void listAvailableItems() {
        System.out.println("\n=== Available Items ===");
        List<Item> items = itemController.listItems();
        if (items.isEmpty()) {
            System.out.println("No items available.");
        } else {
            items.forEach(item -> System.out.println(item));
        }
    }

    private static void addItemToCart() {
        System.out.println("\n=== Add Item to Cart ===");
        System.out.print("Enter item ID: ");
        UUID itemId = UUID.fromString(scanner.nextLine());
        System.out.print("Enter quantity: ");
        int quantity = scanner.nextInt();
        scanner.nextLine();  // Consume newline

        Item item = itemController.readItem(itemId);
        if (item != null && item.isAvailable()) {
            double subTotal = item.price() * quantity;
            cartController.createCart(quantity, itemId.toString(), subTotal);
            System.out.println("Item added to cart.");
        } else {
            System.out.println("Item not found or unavailable.");
        }
    }

    private static void viewCart() {
        System.out.println("\n=== Cart ===");
        List<Cart> cartItems = cartController.listCarts();
        if (cartItems.isEmpty()) {
            System.out.println("Your cart is empty.");
        } else {
            cartItems.forEach(cart -> System.out.println(cart));
        }
    }

    private static void placeOrder() {
        System.out.println("\n=== Place Order ===");
        List<Cart> cartItems = cartController.listCarts();
        if (cartItems.isEmpty()) {
            System.out.println("Your cart is empty. Add items before placing an order.");
        } else {
            // Simulate placing an order
            double totalPrice = 0;
            List<OrderItem> orderItems = new ArrayList<>();
            for (Cart cart : cartItems) {
                totalPrice += cart.subTotal();
                orderItems.add(new OrderItem(UUID.randomUUID(), UUID.randomUUID().toString(), cart.itemId(), cart.quantity(), cart.subTotal()));
            }
            orderController.createOrder(loggedInUser.id().toString(), "addressId", "driverId", Utilities.Status.Pending, Utilities.DeliveryStatus.Pending, orderItems, totalPrice, 10, 0, "No notes");
            System.out.println("Order placed successfully. Total price: $" + totalPrice);
            cartController.clearCart(); // Clear the cart after placing the order
        }
    }
}