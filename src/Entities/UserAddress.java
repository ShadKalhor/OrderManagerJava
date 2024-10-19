package Entities;

import java.util.UUID;

public class UserAddress extends AuditableEntity{

    private UUID id;
    private String userId;
    private String city;
    private String description;
    private String type;
    private String street;
    private String residentialNo;

    public UserAddress() {
    }

    // <editor-fold desc="Getters and Setters">

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getStreet() {
        return street;
    }

    public void setStreet(String street) {
        this.street = street;
    }

    public String getResidentialNo() {
        return residentialNo;
    }

    public void setResidentialNo(String residentialNo) {
        this.residentialNo = residentialNo;
    }

    // </editor-fold>
}
