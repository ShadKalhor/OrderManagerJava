package Entities;

import java.util.UUID;

public class User extends AuditableEntity{
    private UUID id;
    private String roleId;
    private String name;
    private String phone;
    private String password;
    private Utilities.Genders gender;

    // Constructor
    public User() {
        // Default constructor
    }

    // <editor-fold desc="Getters and Setters">

    public UUID GetId() {
        return id;
    }

    public void SetId(UUID id) {
        this.id = id;
    }

    public String GetRoleId() {
        return roleId;
    }

    public void SetRoleId(String roleId) {
        this.roleId = roleId;
    }

    public String GetName() {
        return name;
    }

    public void SetName(String fullName) {
        this.name = fullName;
    }

    public String GetPhone() {
        return phone;
    }

    public void SetPhone(String phone) {
        this.phone = phone;
    }

    public String GetPassword() {
        return password;
    }

    public void SetPassword(String password) {
        this.password = password;
    }

    public Utilities.Genders GetGender() {
        return gender;
    }

    public void SetGender(Utilities.Genders gender) {
        this.gender = gender;
    }

    // </editor-fold>
}
