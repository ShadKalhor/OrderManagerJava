package Entities;

import java.util.UUID;

public class UserRole extends AuditableEntity{

    private UUID id;
    private String roleName;

    public UserRole() {
    }

    // <editor-fold desc="Getters and Setters">

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public String getRoleName() {
        return roleName;
    }

    public void setRoleName(String roleName) {
        this.roleName = roleName;
    }

    // </editor-fold>
}
