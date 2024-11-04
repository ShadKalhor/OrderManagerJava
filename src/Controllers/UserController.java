package Controllers;

import Entities.User;
import Entities.Utilities;
import Extensions.RegexFormats;
import Repo.DataPersistence;

import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.UUID;

import com.google.gson.Gson;
import com.google.gson.JsonSyntaxException;
import com.google.gson.reflect.TypeToken;

public class UserController {
    private final List<User> userList = new ArrayList<>();

    public void createUser(User user) {
        if(validateUser(user)){
            saveUserToJson(user);
        }else{
            System.out.println("Invalid user Information");
        }
    }
    public void createUser(UUID id, String roleId, String name, String phone, String password, Utilities.Genders gender) {
        User user = new User(id, roleId, name, phone, password, gender);
        if(validateUser(user)){
            saveUserToJson(user);
        }
    }

    public List<User> loadUsers() {
        Gson gson = new Gson();
        List<User> users = new ArrayList<>();

        // Step 1: Read existing users from the JSON file
        try (FileReader reader = new FileReader("user.json")) {
            Type userListType = new TypeToken<List<User>>(){}.getType();
            users = gson.fromJson(reader, userListType);
            if (users == null) {
                users = new ArrayList<>(); // Initialize if the JSON was empty or not an array
            }
        } catch (JsonSyntaxException | IOException e) {
            System.out.println("File is either missing, empty, or not formatted as an array. Creating a new list.");
            users = new ArrayList<>(); // Initialize a new list if file is empty or incorrectly formatted
        }
        return users;
    }

    private boolean saveUserToJson(User user){
        Gson gson = new Gson();
        List<User> users = new ArrayList<>();

        users = loadUsers();
        users.add(user);

        try (FileWriter fileWriter = new FileWriter("user.json")) {
            gson.toJson(users, fileWriter);
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }
        return true;
    }
    public User getUser(UUID userId) {
        try (FileReader reader = new FileReader("user.json")) {
            Type userListType = new TypeToken<List<User>>(){}.getType();
            List<User> users = new Gson().fromJson(reader, userListType);

            User result = users.stream()
                    .filter(user -> user.getId().equals(userId))
                    .findFirst()
                    .orElse(null);

            if (result != null) {
                System.out.println("Found: " + result);
            } else {
                System.out.println("Person not found.");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        return null;
        /*    return userList.stream()
                .filter(user -> user.getId().equals(userId))
                .findFirst()
                .orElse(null);
    */}
    public User getUser(String phone) {
        User result = null;
        List<User> users = null;
        users = loadUsers();
            result = users.stream()
                    .filter(user -> user.getPhone().equals(phone))
                    .findFirst()
                    .orElse(null);


        return result;
        /*    return userList.stream()
                .filter(user -> user.getId().equals(userId))
                .findFirst()
                .orElse(null);
    */}


    public void updateUser(UUID userId, User newUser) {/*
        List<String> validationErrors = validateUser(newUser);*/
        if (validateUser(newUser)) {
            for (int i = 0; i < userList.size(); i++) {
                User user = userList.get(i);
                if (user.getId().equals(userId)) {
                    user.setRoleId(newUser.getRoleId());
                    user.setName(newUser.getName());
                    user.setPhone(newUser.getPhone());
                    user.setPassword(newUser.getPassword());
                    user.setGender(newUser.getGender());
                    System.out.println("User updated successfully!");
                    return;
                }
            }
            System.out.println("User not found.");
        } else {
            //validationErrors.forEach(System.out::println);
        }
    }

    public void deleteUser(UUID userId) {
        boolean removed = userList.removeIf(user -> user.getId().equals(userId));
        if (removed) {
            System.out.println("User deleted successfully!");
        } else {
            System.out.println("User not found.");
        }
    }

    public boolean validateUser(User user) {
        RegexFormats formats = new RegexFormats();


        if(isDublicatePhone(user.getPhone()))
            return false;
        if (user.getRoleId() == null || user.getRoleId().isEmpty()) {

            System.out.println("1");
            return false;
        }

        // Validating Name
        if (user.getName() == null || user.getName().isEmpty() || !(user.getName().matches(formats.NameRegex))) {

            System.out.println("2");
            return false;
        }

        // Validating Phone Number
        if (user.getPhone() == null || user.getPhone().isEmpty() || !(user.getPhone().matches(formats.PhoneRegex))) {

            System.out.println("3");
            return false;
        }

        // Validating Password
        if (user.getPassword() == null || user.getPassword().isEmpty() || !(user.getPassword().matches(formats.PasswordRegex))) {

            System.out.println("4");
            return false;
        }

        return true;
        //return errors;
    }

    public boolean IsValidLogin(String phoneNumber, String password){
        Gson gson = new Gson();
        List<User> users;
        users = loadUsers();
            if (!users.isEmpty()) {
                return users.stream()
                        .anyMatch(user -> phoneNumber.equals(user.getPhone()) && password.equals(user.getPassword()));

            }

            // Step 3: Check if a user exists with matching phone number and password

        return false;
    }

    private boolean isDublicatePhone(String phone) {
        Gson gson = new Gson();
        List<User> users;

        users = loadUsers();
        if(users.isEmpty()){
            return false;
        }

            // Check if any user has the same phone number
            return users.stream()
                    .anyMatch(user -> phone.equals(user.getPhone()));

    }
}
