package Controllers;

import Entities.User;
import Entities.Utilities;
import Extensions.RegexFormats;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class UserController {
    private final List<User> userList = new ArrayList<>();

    public void CreateUser(User user) {
        if(isValidUser(user)) {
            User newUser = new User(UUID.randomUUID(), user.roleId(), user.name(), user.phone(), user.password(), user.gender());
            userList.add(newUser);
            System.out.println("User created successfully!");
        }
    }

    public User GetUser(UUID userId) {
        return userList.stream()
                .filter(user -> user.id().equals(userId))
                .findFirst()
                .orElse(null);
    }

    public void UpdateUser(UUID userId, User newUser) {


        if (isValidUser(newUser)){
            for (int i = 0; i < userList.size(); i++) {
                User user = userList.get(i);
                if (user.id().equals(userId)) {
                    User updatedUser = new User(userId, newUser.roleId(), newUser.name(),
                            newUser.phone(), newUser.password(), newUser.gender());
                    userList.set(i, updatedUser);

                    System.out.println("User updated successfully!");
                    return;
                }
            }
            System.out.println("User not found.");
        }
    }

    public void DeleteUser(UUID userId) {
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


    private boolean isValidUser(User user) {
        RegexFormats formats = new RegexFormats();

        if (user.roleId() == null || user.roleId().isEmpty()) {
            System.out.println("Invalid role ID.");
            return false;
        }

        //Validating Name
        if (user.name() == null || user.name().isEmpty() || !(user.name().matches(formats.NameRegex))) {
            System.out.println("Invalid name.");
            return false;
        }

        if (user.phone() == null || user.phone().isEmpty() || !(user.phone().matches(formats.PhoneRegex))) {
            System.out.println("Invalid phone number.");
            return false;
        }
        if (user.password() == null || user.password().isEmpty() || !(user.password().matches(formats.PasswordRegex))) {
            System.out.println("Invalid password.");
            return false;
        }

        return true;
    }

}
