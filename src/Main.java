import Controllers.UserController;
import Entities.*;
import Repo.DataPersistence;
import com.google.gson.reflect.TypeToken;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.UUID;
import  Controllers.CartController;
import  Controllers.ItemController;
import  Controllers.OrderController;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    // Initialize some sample items for ordering
    private static final String USER_FILE = "users.json";
    private static final TypeToken<List<User>> USER_TYPE = new TypeToken<>() {};
    private static final UserController userController = new UserController();
    private static final ItemController itemController = new ItemController();
    private static final CartController cartController = new CartController();
    private static final OrderController orderController = new OrderController();
    private final List<User> userList = new ArrayList<>();
    private static User loggedInUser ;
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
        // Load items from JSON

        List<Item> items = DataPersistence.loadData("items.json", new TypeToken<List<Item>>() {});

        // Check if items is null or empty before proceeding
        if (items == null || items.isEmpty()) {
            // Populate default items if the file is empty or not found
            itemController.createItem(new Item(UUID.randomUUID(), "Laptop", "A high-performance laptop", 1200.00, "15 inch", 0.10, true, 10));
            itemController.createItem(new Item(UUID.randomUUID(), "Phone", "A smartphone with 5G connectivity", 800.00, "6 inch", 0.05, true, 20));

            // Save initialized items
            DataPersistence.saveData(itemController.listItems(), "items.json");
        } else {
            // Load existing items from the JSON file
            items.forEach(itemController::createItem); // Add loaded items to the controller

            // Print the loaded items for confirmation
            System.out.println("Loaded items from items.json:");
            items.forEach(item -> System.out.println(item));
        }
    }




    private static void loginUser() {

        System.out.println("\n=== User Login / Sign Up ===");
        System.out.println("Choose an option:");
        System.out.println("1. Log In");
        System.out.println("2. Sign Up");
        System.out.print("Enter your choice (1 or 2): ");
        int choice = scanner.nextInt();
        scanner.nextLine();  // Consume the newline character



        if(choice==1){  System.out.println("\n=== User Login ===");

            System.out.print("Enter your name: ");
            String name = scanner.nextLine();
            if (name == null || name.trim().isEmpty()) {
                System.out.println("Invalid name. Please try again.");
                return;
            }

            System.out.print("Enter your phone number: ");
            String phone = scanner.nextLine();
            if (!phone.matches("^07\\d{9}$")) { // Validates phone starts with "07" and has 11 digits
                System.out.println("Invalid phone number. Please try again.");
                return;
            }

            System.out.print("Enter your password: ");
            String password = scanner.nextLine();
            if (password.length() < 8 || !password.matches("^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d)(?=.*[@$!%*?&]).{8,}$")) {
                System.out.println("Invalid password. Password must be at least 8 characters, contain an uppercase letter, a lowercase letter, a digit, and a special character.");
                return;
            }

            // Check if the entered credentials match any existing user
            boolean userFound = false;
            User loggedInUser = null;
            for (User user : userController.listUsers()) {
                if (user.getName().equals(name) && user.getPhone().equals(phone)) {
                    if (user.getPassword().equals(password)) {
                        loggedInUser = user;
                        userFound = true;
                        System.out.println("Logged in as " + loggedInUser.getName());
                        break;
                    } else {
                        System.out.println("Invalid password.");
                        return;
                    }
                }
            }

            // Notify if no user is found
            if (!userFound) {
                System.out.println("Invalid name or phone number.");
            }

//                // Check if the name and phone match
//                if (user.name().equals(name) && user.phone().equals(phone)) {
//                    // Check if the password matches
//                    if (user.password().equals(password)) {
//                        loggedInUser = user; // Set the logged-in user
//                        userFound = true;
//                        System.out.println("Logged in as " + loggedInUser.name());
//                        break; // Exit the loop if the user is found
//                    } else {
//                        System.out.println("Invalid password.");
//                        return; // Exit if the password is invalid
//                    }
//                }
//            }

            // Notify if no user is found
                }else if (choice == 2) {
            System.out.print("Enter your name: ");
            String name = scanner.nextLine();
            System.out.print("Enter your phone number: ");
            String phone = scanner.nextLine();
            System.out.print("Enter your password: ");
            String password = scanner.nextLine();

            User newUser = new User(UUID.randomUUID(), "userRoleId", name, phone, password, Utilities.Genders.Male); // Assuming a default gender

            // Validate and add the new user
            List<String> validationErrors = userController.validateUser(newUser);
            if (validationErrors.isEmpty()) {
                // Save the new user
                userController.createUser(newUser);  // Optionally create user in the UserController
                loggedInUser = newUser;  // Set logged in user
                System.out.println("User signed up successfully!");
            } else {
                validationErrors.forEach(System.out::println);
            }
        }
    }

    private static void listAvailableItems() {
        System.out.println("\n=== Available Items ===");
        List<Item> items = itemController.listItems();
        if (items.isEmpty() ) {
            System.out.println("No items available.");
        } else {

            items.forEach(item -> System.out.println(item ));
        }
    }

    private static void addItemToCart() {
        System.out.println("\n=== Add Item to Cart ===");
        System.out.print("Enter item ID: ");
        String itemIdInput = scanner.nextLine();

        UUID itemId;
        try {
            itemId = UUID.fromString(itemIdInput);
        } catch (IllegalArgumentException e) {
            System.out.println("Invalid UUID format. Please enter a valid UUID.");
            return; // Exit the method if UUID is invalid
        }

        System.out.print("Enter quantity: ");
        int quantity = scanner.nextInt();
        scanner.nextLine();  // Consume newline

        Item item = itemController.readItem(itemId);
        if (item != null && item.isAvailable()) {
            double subTotal = item.getPrice() * quantity;
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
            orderController.createOrder(new Order(( UUID.randomUUID()),loggedInUser.getId().toString(), "addressId", "driverId", Utilities.Status.Pending, Utilities.DeliveryStatus.Pending, orderItems, totalPrice, 10, 0, 120,"No notes"));
            System.out.println("Order placed successfully. Total price: $" + totalPrice);
            cartController.clearCart(); // Clear the cart after placing the order
        }
    }
}