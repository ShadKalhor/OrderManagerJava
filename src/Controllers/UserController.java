package Controllers;

import Entities.User;
import Entities.Utilities;
import Extensions.RegexFormats;
import Repo.DataPersistence;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.UUID;
import com.google.gson.reflect.TypeToken;

public class UserController {
    private final List<User> userList = new ArrayList<>();

    public void createUser(User user) {
        List<String> validationErrors = validateUser(user);
        if (validationErrors.isEmpty()) {
            User newUser = new User(UUID.randomUUID(), user.getRoleId(), user.getName(), user.getPhone(), user.getPassword(), user.getGender());
            userList.add(newUser);
            DataPersistence.saveData(userList, "user.json");  // Save the user list to JSON

            System.out.println("User created successfully!");
        } else {
            validationErrors.forEach(System.out::println);
        }
    }

    public List<User> loadUsers() {
        return DataPersistence.loadData("user.json", new TypeToken<List<User>>() {});
    }

    public User getUser(UUID userId) {
        return userList.stream()
                .filter(user -> user.getId().equals(userId))
                .findFirst()
                .orElse(null);
    }

    public void updateUser(UUID userId, User newUser) {
        List<String> validationErrors = validateUser(newUser);
        if (validationErrors.isEmpty()) {
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
            validationErrors.forEach(System.out::println);
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

    public List<User> listUsers() {
        if (userList.isEmpty()) {
            System.out.println("No users found.");
            return new ArrayList<>();
        } else {
            return new ArrayList<>(userList);
        }
    }

    public void setUserList(List<User> userList) {
        this.userList.clear();
        this.userList.addAll(userList);
    }

    public List<String> validateUser(User user) {
        List<String> errors = new ArrayList<>();
        RegexFormats formats = new RegexFormats();

        if (user.getRoleId() == null || user.getRoleId().isEmpty()) {
            errors.add("Invalid role ID.");
        }

        // Validating Name
        if (user.getName() == null || user.getName().isEmpty()) {
            errors.add("Invalid name.");
        }

        // Validating Phone Number
        if (user.getPhone() == null || user.getPhone().isEmpty() || user.getPhone().length() < 11) {
            errors.add("Invalid phone number.");
        }

        // Validating Password
        if (user.getPassword() == null || user.getPassword().isEmpty() || user.getPassword().length() < 8) {
            errors.add("Invalid password.");
        }

        return errors;
    }
}
