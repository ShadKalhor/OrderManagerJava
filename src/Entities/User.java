package Entities;

import java.util.UUID;

public class User {
    private final UUID id;
    private String roleId;
    private String name;
    private String phone;
    private String password;
    private Utilities.Genders gender;

    // Constructor
    public User(UUID id, String roleId, String name, String phone, String password, Utilities.Genders gender) {
        this.id = id;
        this.roleId = roleId;
        this.name = name;
        this.phone = phone;
        this.password = password;
        this.gender = gender;
    }

    // Getters
    public UUID getId() {
        return id;
    }

    public String getRoleId() {
        return roleId;
    }

    public String getName() {
        return name;
    }

    public String getPhone() {
        return phone;
    }

    public String getPassword() {
        return password;
    }

    public Utilities.Genders getGender() {
        return gender;
    }

    // Setters
    public void setRoleId(String roleId) {
        this.roleId = roleId;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setGender(Utilities.Genders gender) {
        this.gender = gender;
    }

    // Optional toString method for better output
    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", roleId='" + roleId + '\'' +
                ", name='" + name + '\'' +
                ", phone='" + phone + '\'' +
                ", gender=" + gender +
                '}';
    }
}
