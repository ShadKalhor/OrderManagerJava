import Controllers.UserController;
import Entities.*;
import Repo.DataPersistence;
import com.google.gson.reflect.TypeToken;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.UUID;
import  Controllers.ItemController;
import  Controllers.OrderController;
import java.io.IOException;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {

    private static final String SERVER_HOST = "localhost"; // Replace with server IP if needed
    private static final int SERVER_PORT = 8081;

    // Initialize some sample items for ordering
    private static final String USER_FILE = "users.json";
    private static final TypeToken<List<User>> USER_TYPE = new TypeToken<>() {};
    private static final UserController userController = new UserController();
    private static final ItemController itemController = new ItemController();
    private static final OrderController orderController = new OrderController();
    private final List<User> userList = new ArrayList<>();
    private static User loggedInUser;
    private static final Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        User newUser = new User();
        loginUi();

    }
    private static void loginUi(){
        System.out.println("You Are Not Logged in");
        System.out.println("press 1 to Login");
        System.out.println("press 2 to signUp");
        int option = scanner.nextInt();
        scanner.nextLine();
        if(option == 1) login();
        else if(option == 2) signUp();
    }
    private static void login() {
        User newUser = new User();
        System.out.println("Enter Phone Number");
        String phone = scanner.nextLine();
        newUser.setPhone(phone);
        System.out.println("Enter Password");
        String password = scanner.nextLine();
        newUser.setPassword(password);

        boolean validLogin = userController.IsValidLogin(phone, password);
        if (validLogin) {
            System.out.println("LoggedIn");
            loggedInUser = userController.getUser(phone);
            MainMenuUI();
        } else {
            System.out.println("Invalid Login, Press any Key To Retry");
            scanner.nextLine();
            login();
        }
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


        if (choice == 1) {
            listAvailableItems();
        } else if (choice == 2) {
            addItemsToCart();
        } else if (choice == 3) {
            viewCart();
        } else if (choice == 4) {
            placeOrder();
        } else if (choice == 5) {
            System.exit(0);
        } else {
            System.out.println("Invalid option. Try again.");
        }

    }

    private static void placeOrder() {
        orderController.PlacePendingOrder(loggedInUser);
    }

    private static void viewCart() {
        orderController.PrintCart(loggedInUser);
        System.out.println("Press 1 To Go Back");
        int choice = scanner.nextInt();
        if(choice == 1)
            MainMenuUI();
        else{
            System.out.println("Invalid Command, Press any key to retry");
            scanner.nextLine();
            viewCart();
        }

    }

    private static void addItemsToCart() {
        itemController.listItems();
        UUID itemId = null;
        System.out.println("Press 1 to add an item to your cart");
        System.out.println("Press 2 to go back");
        int choice = scanner.nextInt();
        scanner.nextLine();
        if(choice == 1){
            System.out.println("Enter the id of the item");
            String input = scanner.nextLine();
            itemId = UUID.fromString(input);
            System.out.println("Enter The Quantity");
            int quantity = scanner.nextInt();
            scanner.nextLine();
            Item item = itemController.getItemById(itemId);
            OrderItem orderItem = new OrderItem(item.getId(),quantity,(item.getPrice()*quantity));
            orderController.AddItemToPendingOrder(orderItem, loggedInUser);
            System.out.println("Item Added\n Press 1 To Add Another Item \n Press 2 To Go Back ");
            int option = scanner.nextInt();
            scanner.nextLine();
            if (option == 1){
                addItemsToCart();
            }else {
                MainMenuUI();
            }
        }else if(choice == 2){
            MainMenuUI();
        }else{
            System.out.println("Invalid Command press any key to Try Again");
            scanner.nextLine();
            addItemsToCart();
        }
    }

    private static void listAvailableItems() {
        itemController.listItems();
        MainMenuUI();
    }

    private static void signUp(){

        User newUser = new User();
        System.out.println("Enter Name");
        String name = scanner.nextLine();
        newUser.setName(name);
        System.out.println("Enter Phone Number");
        String phone = scanner.nextLine();
        newUser.setPhone(phone);
        System.out.println("Enter Gender \n 1 For Male \n 2 for Female");
        int selectedGender = scanner.nextInt();
        scanner.nextLine();
        if(selectedGender == 1){
            Utilities.Genders gender = Utilities.Genders.Male;
        }
        else if(selectedGender == 2){
            Utilities.Genders gender = Utilities.Genders.Female;
        }
        else{
            System.out.println("Theres no such Command, Press any key to try again");
            scanner.nextLine();
            signUp();
        }
        System.out.println("Enter Password");
        String password = scanner.nextLine();
        newUser.setPassword(password);
        userController.createUser(newUser);
    }
}