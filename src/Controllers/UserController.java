package Controllers;

import Entities.User;
import Entities.Utilities;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class UserController {
    private final List<User> userList = new ArrayList<>();

    public void createUser(String roleId, String name, String phone, String password, Utilities.Genders gender) {
        if (roleId == null || roleId.isEmpty()) {
            System.out.println("Invalid role ID.");
            return;
        }
        if (name == null || name.isEmpty()) {
            System.out.println("Invalid name.");
            return;
        }
        if (phone == null || phone.isEmpty()) {
            System.out.println("Invalid phone number.");
            return;
        }
        if (password == null || password.isEmpty()) {
            System.out.println("Invalid password.");
            return;
        }

        User newUser = new User(UUID.randomUUID(), roleId, name, phone, password, gender);
        userList.add(newUser);
        System.out.println("User created successfully!");
    }

    public User readUser(UUID userId) {
        return userList.stream()
                .filter(user -> user.id().equals(userId))
                .findFirst()
                .orElse(null);
    }

    public void updateUser(UUID userId, String roleId, String name, String phone, String password, Utilities.Genders gender) {
        if (roleId == null || roleId.isEmpty()) {
            System.out.println("Invalid role ID.");
            return;
        }
        if (name == null || name.isEmpty()) {
            System.out.println("Invalid name.");
            return;
        }
        if (phone == null || phone.isEmpty()) {
            System.out.println("Invalid phone number.");
            return;
        }
        if (password == null || password.isEmpty()) {
            System.out.println("Invalid password.");
            return;
        }

        for (int i = 0; i < userList.size(); i++) {
            User user = userList.get(i);
            if (user.id().equals(userId)) {
                User updatedUser = new User(userId, roleId, name, phone, password, gender);
                userList.set(i, updatedUser);
                System.out.println("User updated successfully!");
                return;
            }
        }
        System.out.println("User not found.");
    }

    public void deleteUser(UUID userId) {
        boolean removed = userList.removeIf(user -> user.id().equals(userId));
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

}
