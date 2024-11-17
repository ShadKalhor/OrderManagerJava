/*
package CLI;

import Controllers.AddressController;
import Controllers.ItemController;
import Controllers.OrderController;
import Controllers.UserController;
import Entities.OrderItem;
import Entities.User;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public  class AdminCLI {

    private static final Scanner scanner = new Scanner(System.in);
    private static User loggedInUser;
    private static List<OrderItem> cartItems = new ArrayList<>();
    // Controller Instances
    private static final UserController userController = new UserController();
    private static final AddressController addressController = new AddressController();
    private static final ItemController itemController = new ItemController();
    private static final OrderController orderController = new OrderController();



    private static void MainMenuUI(User currentUser) {
        loggedInUser = currentUser;
        System.out.println("\n==== Main Menu ====");
        System.out.println("1. Order Management");
        System.out.println("2. Driver Management");
        System.out.println("3. User Management");
        System.out.println("4. Inventory Management");
        System.out.println("5. Exit");
        System.out.print("Choose an option: ");
        try {
            int choice = scanner.nextInt();
            scanner.nextLine();  // Consume newline

            switch (choice) {
                case 1 -> orderManagementUI();
                case 2 -> driverManagementUI();
                case 3 -> userManagementUI();
                case 4 -> inventoryManagementUI();
                case 5 -> System.exit(0);
                default -> {
                    System.out.println("Invalid option. Try again.");
                    MainMenuUI(loggedInUser);
                }
            }
        } catch (Exception e) {
            System.out.println("Please Enter A valid Option");
            MainMenuUI(loggedInUser);
        }
    }

    private static void inventoryManagementUI() {
    }

    private static void userManagementUI() {
    }

    private static void driverManagementUI() {
        System.out.println("\n==== Driver Management Menu ====");
        System.out.println("1. View Drivers");
        System.out.println("2. Edit Driver By Id");
        System.out.println("3. Delete Driver");
        System.out.println("4. Go Back To Main Menu");
        System.out.println("5. Exit");
        System.out.print("Choose an option: ");
        try {
            int choice = scanner.nextInt();
            scanner.nextLine();  // Consume newline

            switch (choice) {
                case 1 -> viewDriversUI();
                case 2 -> editDriverByIdUI();
                case 3 -> deleteDriverUI();
                case 4 -> MainMenuUI(loggedInUser);
                case 5 -> System.exit(0);
                default -> {
                    System.out.println("Invalid option. Try again.");
                    driverManagementUI();
                }
            }
        } catch (Exception e) {
            System.out.println("Please Enter A valid Option");
            driverManagementUI();
        }


    }

    private static void deleteDriverUI() {
        // ask for the driver id then make sure that the user wants to delete it if so delete it via the client controller send a request




        //lastly go back to OrderManagement Menu
        System.out.println("Press Enter To Go Back");
        scanner.nextLine();
        driverManagementUI();
    }

    private static void editDriverByIdUI() {
        // ask for the driver id then show the driver information then have a series of scanners depending on the driver fields then make the new information a driver and send it with the id to the client controller to send the request




        //lastly go back to OrderManagement Menu
        System.out.println("Press Enter To Go Back");
        scanner.nextLine();
        driverManagementUI();
    }

    private static void viewDriversUI() {
        //show the list of drivers



        //lastly go back to OrderManagement Menu
        System.out.println("Press Enter To Go Back");
        scanner.nextLine();
        driverManagementUI();
    }

    private static void orderManagementUI() {
        System.out.println("\n==== Order Management Menu ====");
        System.out.println("1. View Orders");
        System.out.println("2. Edit Order By Id");
        System.out.println("3. Delete Order");
        System.out.println("4. Go Back To Main Menu");
        System.out.println("5. Exit");
        System.out.print("Choose an option: ");
        try {
            int choice = scanner.nextInt();
            scanner.nextLine();  // Consume newline

            switch (choice) {
                case 1 -> viewOrdersUI();
                case 2 -> editOrderByIdUI();
                case 3 -> deleteOrderUI();
                case 4 -> MainMenuUI(loggedInUser);
                case 5 -> System.exit(0);
                default -> {
                    System.out.println("Invalid option. Try again.");
                    orderManagementUI();
                }
            }
        } catch (Exception e) {
            System.out.println("Please Enter A valid Option");
            orderManagementUI();
        }



    }

    private static void deleteOrderUI() {
        //same as the deleteDriverUI just this one is for deleting order instead

        //lastly go back to OrderManagement Menu
        System.out.println("Press Enter To Go Back");
        scanner.nextLine();
        orderManagementUI();
    }

    private static void editOrderByIdUI() {
        //same as the editDriverUI just this one is for editing order instead



        //lastly go back to OrderManagement Menu
        System.out.println("Press Enter To Go Back");
        scanner.nextLine();
        orderManagementUI();
    }

    private static void viewOrdersUI() {
        //same as viewDriversUI just this one is for the list of orders

        //lastly go back to OrderManagement Menu
        System.out.println("Press Enter To Go Back");
        scanner.nextLine();
        orderManagementUI();
    }


}
*/


package CLI;

import Controllers.AddressController;
import Controllers.DriverController;
import Controllers.ItemController;
import Controllers.OrderController;
import Controllers.UserController;
import Entities.*;
import com.google.gson.Gson;

import java.io.IOException;
import java.util.List;
import java.util.Scanner;
import java.util.UUID;

public class AdminCLI {

    private static final Scanner scanner = new Scanner(System.in);
    private static User loggedInUser;
    private static List<OrderItem> cartItems;
    // Controller Instances
    private static final UserController userController = new UserController();
    private static final AddressController addressController = new AddressController();
    private static final ItemController itemController = new ItemController();
    private static final OrderController orderController = new OrderController();
    private static final DriverController driverController = new DriverController();

    public static void AdminMainAMenuUI(User currentUser) {
        loggedInUser = currentUser;
        System.out.println("\n==== Main Menu ====");
        System.out.println("1. Order Management");
        System.out.println("2. Driver Management");
        System.out.println("3. User Management");
        System.out.println("4. Inventory Management");
        System.out.println("5. Exit");
        System.out.print("Choose an option: ");
        try {
            int choice = scanner.nextInt();
            scanner.nextLine(); // Consume newline

            switch (choice) {
                case 1 -> orderManagementUI();
                case 2 -> driverManagementUI();
                case 3 -> userManagementUI();
                case 4 -> inventoryManagementUI();
                case 5 -> System.exit(0);
                default -> {
                    System.out.println("Invalid option. Try again.");
                    AdminMainAMenuUI(loggedInUser);
                }
            }
        } catch (Exception e) {
            System.out.println("Please Enter A valid Option");
            AdminMainAMenuUI(loggedInUser);
        }
    }

    private static void inventoryManagementUI() { System.out.println("\n==== User Management Menu ====");
        System.out.println("1. View Users");
        System.out.println("2. Edit User By Id");
        System.out.println("3. Delete User");
        System.out.println("4. Find User By Id");
        System.out.println("5. Find User By phone");
        System.out.println("6. Go Back To Main Menu");
        System.out.println("7. Exit");
        System.out.print("Choose an option: ");
        try {
            int choice = scanner.nextInt();
            scanner.nextLine(); // Consume newline

            switch (choice) {
                case 1 -> viewUsersUI();
                case 2 -> editUserByIdUI();
                case 3 -> deleteUserUI();
                case 4 -> findUserByIdUI();
                case 5 -> findUserByPhoneUI();
                case 6 -> AdminMainAMenuUI(loggedInUser);
                case 7 -> System.exit(0);
                default -> {
                    System.out.println("Invalid option. Try again.");
                    driverManagementUI();
                }
            }
        } catch (Exception e) {
            System.out.println("Please Enter A valid Option");
            driverManagementUI();
        }
        System.out.println("User management is not implemented yet.");
        AdminMainAMenuUI(loggedInUser);
    }

    private static void userManagementUI() {
        System.out.println("\n==== Inventory Management Menu ====");
        System.out.println("1. View Inventory Items");
        System.out.println("2. Edit Item By Id");
        System.out.println("3. Delete Item");
        System.out.println("4. Find Item By Id");
        System.out.println("5. Go Back To Main Menu");
        System.out.println("6. Exit");
        System.out.print("Choose an option: ");
        try {
            int choice = scanner.nextInt();
            scanner.nextLine(); // Consume newline

            switch (choice) {
                case 1 -> viewItemsUI();
                case 2 -> editItemByIdUI();
                case 3 -> deleteItemUI();
                case 4 -> findItemByIdUI();
                case 5 -> AdminMainAMenuUI(loggedInUser);
                case 6 -> System.exit(0);
                default -> {
                    System.out.println("Invalid option. Try again.");
                    driverManagementUI();
                }
            }
        } catch (Exception e) {
            System.out.println("Please Enter A valid Option");
            driverManagementUI();
        }
        System.out.println("User management is not implemented yet.");
        AdminMainAMenuUI(loggedInUser);
    }

    private static void findItemByIdUI() {
        try {
            System.out.print("Enter Item ID: ");
            UUID itemId = UUID.fromString(scanner.nextLine());

            Item item = itemController.getItem(itemId);
            if (item == null) {
                System.out.println("Item not found.");
            } else {
                System.out.println("Item Details: " + item);
            }
        } catch (Exception e) {
            System.out.println("Error finding item: " + e.getMessage());
        }

        // After everything
        System.out.println("Press Enter To Go Back");
        scanner.nextLine();
        driverManagementUI();
    }


    private static void deleteItemUI() {
        try {
            System.out.print("Enter Item ID to Delete: ");
            UUID itemId = UUID.fromString(scanner.nextLine());

            System.out.print("Are you sure you want to delete this item? (yes/no): ");
            String confirmation = scanner.nextLine();

            if (confirmation.equalsIgnoreCase("yes")) {
                String response = itemController.deleteItem(itemId);
                System.out.println("Server Response: " + response);
            } else {
                System.out.println("Deletion canceled.");
            }
        } catch (Exception e) {
            System.out.println("Error deleting item: " + e.getMessage());
        }

        // After everything
        System.out.println("Press Enter To Go Back");
        scanner.nextLine();
        driverManagementUI();
    }


    private static void editItemByIdUI() {
        try {
            System.out.print("Enter Item ID to Edit: ");
            UUID itemId = UUID.fromString(scanner.nextLine());

            Item item = itemController.getItem(itemId);
            if (item == null) {
                System.out.println("Item not found.");
                return;
            }

            System.out.println("Current Item Details: " + item);

            System.out.print("Enter New Name (or press Enter to keep current): ");
            String name = scanner.nextLine();
            name = name.isEmpty() ? item.getName() : name;

            System.out.print("Enter New Description (or press Enter to keep current): ");
            String description = scanner.nextLine();
            description = description.isEmpty() ? item.getDescription() : description;

            System.out.print("Enter New Price (or press Enter to keep current): ");
            double price = item.getPrice();
            try {
                String priceInput = scanner.nextLine();
                price = priceInput.isEmpty() ? item.getPrice() : Double.parseDouble(priceInput);
            } catch (Exception e) {
                System.out.println("please Enter a valid double. press enter to retry");
                scanner.nextLine();
                editItemByIdUI();
            }
            System.out.print("Enter New Size (or press Enter to keep current): ");
            String size = scanner.nextLine();
            size = size.isEmpty() ? item.getSize() : size;

            System.out.print("Enter New Discount (or press Enter to keep current): ");
            double discount = 0.0;
            try {
                String discountInput = scanner.nextLine();

                if(discountInput.isEmpty()){
                    discount = item.getDiscount();
                }else{
                    if(Double.parseDouble(discountInput) <= 1){
                        discount = Double.parseDouble(discountInput);
                    }else{
                        System.out.println("please enter a valid discount percentage, press enter to retry");
                        scanner.nextLine();
                        editItemByIdUI();
                    }
                }
            } catch (Exception e) {
                System.out.println("please Enter a valid double. press enter to retry");
                scanner.nextLine();
                editItemByIdUI();
            }
            System.out.print("Enter Availability (true/false, or press Enter to keep current): ");
            String availabilityInput = scanner.nextLine();
            boolean isAvailable = availabilityInput.isEmpty() ? item.isAvailable() : Boolean.parseBoolean(availabilityInput);

            System.out.print("Enter New Quantity (or press Enter to keep current): ");
            String quantityInput = scanner.nextLine();
            int quantity = quantityInput.isEmpty() ? item.getQuantity() : Integer.parseInt(quantityInput);

            String response = itemController.updateItem(itemId, name, description, price, size, discount, isAvailable, quantity);
            System.out.println("Server Response: " + response);

        } catch (Exception e) {
            System.out.println("Error editing item: " + e.getMessage());
        }

        // After everything
        System.out.println("Press Enter To Go Back");
        scanner.nextLine();
        driverManagementUI();
    }


    private static void viewItemsUI() {
        try {
            List<Item> items = itemController.listItems();

            if (items.isEmpty()) {
                System.out.println("No items found.");
            } else {
                System.out.println("List of Items:");
                for (Item item : items) {
                    System.out.println(item);
                }
            }
        } catch (Exception e) {
            System.out.println("Error retrieving items: " + e.getMessage());
        }

        // After everything
        System.out.println("Press Enter To Go Back");
        scanner.nextLine();
        driverManagementUI();
    }


    private static void findUserByPhoneUI() {
        try {
            System.out.print("Enter Phone Number: ");
            String phone = scanner.nextLine();

            User user = userController.getUserByPhone(phone);
            if (user == null) {
                System.out.println("User not found.");
            } else {
                System.out.println("User Details: " + user);
            }
        } catch (Exception e) {
            System.out.println("Error finding user by phone: " + e.getMessage());
        }

        // After everything
        System.out.println("Press Enter To Go Back");
        scanner.nextLine();
        driverManagementUI();
    }


    private static void findUserByIdUI() {
        try {
            System.out.print("Enter User ID: ");
            UUID userId = UUID.fromString(scanner.nextLine());

            User user = userController.getUserById(userId);
            if (user == null) {
                System.out.println("User not found.");
            } else {
                System.out.println("User Details: " + user);
            }
        } catch (Exception e) {
            System.out.println("Error finding user by ID: " + e.getMessage());
        }

        // After everything
        System.out.println("Press Enter To Go Back");
        scanner.nextLine();
        driverManagementUI();
    }


    private static void deleteUserUI() {
        try {
            System.out.print("Enter User ID to Delete: ");
            UUID userId = UUID.fromString(scanner.nextLine());

            System.out.print("Are you sure you want to delete this user? (yes/no): ");
            String confirmation = scanner.nextLine();

            if (confirmation.equalsIgnoreCase("yes")) {
                String response = userController.deleteUser(userId);
                System.out.println("Server Response: " + response);
            } else {
                System.out.println("Deletion canceled.");
            }
        } catch (Exception e) {
            System.out.println("Error deleting user: " + e.getMessage());
        }

        // After everything
        System.out.println("Press Enter To Go Back");
        scanner.nextLine();
        driverManagementUI();
    }

    private static void editUserByIdUI() {
        try {
            System.out.print("Enter User ID to Edit: ");
            UUID userId = UUID.fromString(scanner.nextLine());

            User user = userController.getUserById(userId);
            if (user == null) {
                System.out.println("User not found.");
                return;
            }

            System.out.println("Current User Details: " + user);

            System.out.print("Enter New Name (or press Enter to keep current): ");
            String name = scanner.nextLine();
            name = name.isEmpty() ? user.getName() : name;

            System.out.print("Enter New Phone (or press Enter to keep current): ");
            String phone = scanner.nextLine();
            phone = phone.isEmpty() ? user.getPhone() : phone;

            System.out.print("Enter New Password (or press Enter to keep current): ");
            String password = scanner.nextLine();
            password = password.isEmpty() ? user.getPassword() : password;

            System.out.print("Enter New Gender (Male/Female) (or press Enter to keep current): ");
            String gender = scanner.nextLine();
            if(gender.equalsIgnoreCase("Male") || gender.equalsIgnoreCase("Female") || gender.isEmpty()) {
                gender = gender.isEmpty() ? user.getGender().name() : gender;
            }else{
                System.out.println("Please Enter A Valid Gender, press enter to retry");
                scanner.nextLine();
                editUserByIdUI();
            }

            UUID roleId =UUID.fromString("45C4CAD3-D78D-4E76-93D2-7DA1C3900969");
            System.out.print("Enter New Role (or press Enter to keep current): \n press 1 for user \n press 2 for admin");
            try{
            int choice = scanner.nextInt();
            if(choice == 1){
                roleId = UUID.fromString("45C4CAD3-D78D-4E76-93D2-7DA1C3900969");
            } else if (choice == 2) {
                roleId = UUID.fromString("035E77AC-915C-4019-A972-EAC564424761");
            }else if(choice == 0){
                roleId = user.getRoleId();
            }
            else {
                System.out.println("Please Enter A Valid Option, press enter to retry");
                scanner.nextLine();
                editUserByIdUI();
            }
            } catch (Exception e) {
                throw new RuntimeException(e);
            }



            String response = userController.updateUser(userId, roleId, name, phone, password, gender);
            System.out.println("Server Response: " + response);

        } catch (Exception e) {
            System.out.println("Error editing user: " + e.getMessage());
        }

        // After everything
        System.out.println("Press Enter To Go Back");
        scanner.nextLine();
        driverManagementUI();
    }


    private static void viewUsersUI() {
        try {
            List<User> users = userController.getAllUsers();

            if (users.isEmpty()) {
                System.out.println("No users found.");
            } else {
                System.out.println("List of Users:");
                for (User user : users) {
                    System.out.println(user);
                }
            }
        } catch (Exception e) {
            System.out.println("Error retrieving users: " + e.getMessage());
        }

        // After everything
        System.out.println("Press Enter To Go Back");
        scanner.nextLine();
        driverManagementUI();
    }

    private static void driverManagementUI() {
        System.out.println("\n==== Driver Management Menu ====");
        System.out.println("1. View Drivers");
        System.out.println("2. Edit Driver By Id");
        System.out.println("3. Delete Driver");
        System.out.println("4. Go Back To Main Menu");
        System.out.println("5. Exit");
        System.out.print("Choose an option: ");
        try {
            int choice = scanner.nextInt();
            scanner.nextLine(); // Consume newline

            switch (choice) {
                case 1 -> viewDriversUI();
                case 2 -> editDriverByIdUI();
                case 3 -> deleteDriverUI();
                case 4 -> AdminMainAMenuUI(loggedInUser);
                case 5 -> System.exit(0);
                default -> {
                    System.out.println("Invalid option. Try again.");
                    driverManagementUI();
                }
            }
        } catch (Exception e) {
            System.out.println("Please Enter A valid Option");
            driverManagementUI();
        }
    }

    private static void viewDriversUI() {
        try {
            List<Driver> drivers = driverController.listDrivers();
            if (drivers.isEmpty()) {
                System.out.println("No drivers available.");
            } else {
                System.out.println("\n==== Drivers List ====");
                for (Driver driver : drivers) {
                    System.out.println(driver);
                }
            }
        } catch (IOException e) {
            System.out.println("Error retrieving drivers: " + e.getMessage());
        }
        System.out.println("Press Enter To Go Back");
        scanner.nextLine();
        driverManagementUI();
    }

    private static void editDriverByIdUI() {
        try {
            System.out.print("Enter Driver ID: ");
            UUID driverId = UUID.fromString(scanner.nextLine());

            Driver driver = driverController.getDriver(driverId);
            if (driver == null) {
                System.out.println("Driver not found.");
            } else {
                System.out.print("Enter New Name: ");
                String name = scanner.nextLine();
                System.out.print("Enter New Phone: ");
                String phone = scanner.nextLine();
                System.out.print("Enter New Vehicle Number: ");
                String vehicleNumber = scanner.nextLine();
                System.out.print("Enter New Age: ");
                int age = scanner.nextInt();
                scanner.nextLine(); // Consume newline

                Driver updatedDriver = new Driver(driver.getId(), name, phone, vehicleNumber, age);
                driverController.updateDriver(driverId, updatedDriver);
                System.out.println("Driver updated successfully.");
            }
        } catch (Exception e) {
            System.out.println("Error updating driver: " + e.getMessage());
        }
        System.out.println("Press Enter To Go Back");
        scanner.nextLine();
        driverManagementUI();
    }

    private static void deleteDriverUI() {
        try {
            System.out.print("Enter Driver ID: ");
            UUID driverId = UUID.fromString(scanner.nextLine());

            System.out.print("Are you sure you want to delete this driver? (yes/no): ");
            String confirmation = scanner.nextLine();
            if (confirmation.equalsIgnoreCase("yes")) {
                driverController.deleteDriver(driverId);
                System.out.println("Driver deleted successfully.");
            } else {
                System.out.println("Deletion canceled.");
            }
        } catch (Exception e) {
            System.out.println("Error deleting driver: " + e.getMessage());
        }
        System.out.println("Press Enter To Go Back");
        scanner.nextLine();
        driverManagementUI();
    }

    private static void orderManagementUI() {
        System.out.println("\n==== Order Management Menu ====");
        System.out.println("1. View Orders");
        System.out.println("2. Edit Order By Id");
        System.out.println("3. Delete Order");
        System.out.println("4. Go Back To Main Menu");
        System.out.println("5. Exit");
        System.out.print("Choose an option: ");
        try {
            int choice = scanner.nextInt();
            scanner.nextLine(); // Consume newline

            switch (choice) {
                case 1 -> viewOrdersUI();
                case 2 -> editOrderByIdUI();
                case 3 -> deleteOrderUI();
                case 4 -> AdminMainAMenuUI(loggedInUser);
                case 5 -> System.exit(0);
                default -> {
                    System.out.println("Invalid option. Try again.");
                    orderManagementUI();
                }
            }
        } catch (Exception e) {
            System.out.println("Please Enter A valid Option");
            orderManagementUI();
        }
    }

    private static void viewOrdersUI() {
        try {
            List<Order> orders = orderController.listOrders();
            if (orders.isEmpty()) {
                System.out.println("No orders available.");
            } else {
                System.out.println("\n==== Orders List ====");
                for (Order order : orders) {
                    System.out.println(order);
                }
            }
        } catch (IOException e) {
            System.out.println("Error retrieving orders: " + e.getMessage());
        }
        System.out.println("Press Enter To Go Back");
        scanner.nextLine();
        orderManagementUI();
    }

    private static void editOrderByIdUI() {
        try {
            System.out.print("Enter Order ID: ");
            UUID orderId = UUID.fromString(scanner.nextLine());

            Order order = orderController.getOrder(orderId);
            if (order == null) {
                System.out.println("Order not found.");
            } else {
                System.out.println("Current Order Information: " + order);

                System.out.print("Enter New UserId: ");
                String userId = scanner.nextLine();
                if(userController.getUserById(UUID.fromString(userId)) != null)
                    order.setUserId(UUID.fromString(userId));
                else {
                    System.out.println("please Enter a Valid user Id, Press Enter To Retry");
                    scanner.nextLine();
                    editOrderByIdUI();
                }
                System.out.print("Enter New AddressId: ");
                String addressId = scanner.nextLine();
                if(addressController.getAddressById(UUID.fromString(addressId)) != null)
                    order.setAddressId(UUID.fromString(addressId));
                else {
                    System.out.println("please Enter a Valid Address Id, Press Enter To Retry");
                    scanner.nextLine();
                    editOrderByIdUI();
                }
                System.out.print("Enter New Notes: ");
                String driverId = scanner.nextLine();


                if(driverController.getDriver(UUID.fromString(driverId)) != null)
                    order.setAddressId(UUID.fromString(driverId));
                else {
                    System.out.println("please Enter a Valid driver Id, Press Enter To Retry");
                    scanner.nextLine();
                    editOrderByIdUI();
                }


                System.out.print("Enter New Status: ");
                String status = scanner.nextLine();
                if(isValidEnum(Utilities.Status.class, status))
                    order.setStatus(Utilities.Status.valueOf(status));
                else{
                    System.out.println("Please Enter A Valid Status, Press Enter To Retry");
                    scanner.nextLine();
                    editOrderByIdUI();
                }
                System.out.print("Enter New Delivery Status: ");
                String deliveryStatus = scanner.nextLine();
                if(isValidEnum(Utilities.DeliveryStatus.class, deliveryStatus))
                    order.setStatus(Utilities.Status.valueOf(deliveryStatus));
                else{
                    System.out.println("Please Enter A Valid Delivery Status, Press Enter To Retry");
                    scanner.nextLine();
                    editOrderByIdUI();
                }
                System.out.print("Enter New Notes: ");
                String notes = scanner.nextLine();
                order.setNotes(notes);

                orderController.updateOrder(orderId, order);
                System.out.println("Order updated successfully.");
            }
        } catch (Exception e) {
            System.out.println("Error updating order: " + e.getMessage());
        }
        System.out.println("Press Enter To Go Back");
        scanner.nextLine();
        orderManagementUI();
    }
    private static <T extends Enum<T>> boolean isValidEnum(Class<T> enumClass, String value) {
        try {
            Enum.valueOf(enumClass, value);
            return true;
        } catch (IllegalArgumentException e) {
            return false;
        }
    }
    private static void deleteOrderUI() {
        try {
            System.out.print("Enter Order ID: ");
            UUID orderId = UUID.fromString(scanner.nextLine());

            System.out.print("Are you sure you want to delete this order? (yes/no): ");
            String confirmation = scanner.nextLine();
            if (confirmation.equalsIgnoreCase("yes")) {
                orderController.deleteOrder(orderId);
                System.out.println("Order deleted successfully.");
            } else {
                System.out.println("Deletion canceled.");
            }
        } catch (Exception e) {
            System.out.println("Error deleting order: " + e.getMessage());
        }
        System.out.println("Press Enter To Go Back");
        scanner.nextLine();
        orderManagementUI();
    }
}
