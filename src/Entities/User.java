package Entities;

import java.util.UUID;

public class User extends AuditableEntity{

    private UUID id;
    private String roleId;
    private String fullName;
    private String phone;
    private String password;
    private Utilities.Genders gender;

    // Constructor
    public User() {
        // Default constructor
    }

    // <editor-fold desc="Getters and Setters">

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public String getRoleId() {
        return roleId;
    }

    public void setRoleId(String roleId) {
        this.roleId = roleId;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Utilities.Genders getGender() {
        return gender;
    }

    public void setGender(Utilities.Genders gender) {
        this.gender = gender;
    }

    // </editor-fold>
}
