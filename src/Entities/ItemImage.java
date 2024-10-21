package Entities;

import java.time.LocalDateTime;
import java.util.UUID;

public class ItemImage {

    private UUID id;
    private String itemId;
    private String imageURL;
    private Boolean isPrimary;
    private LocalDateTime uploadedAt; //Note => check if its necessary since there is an auditable Entity.


    // <editor-fold desc="Getters and Setters">

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public String getItemId() {
        return itemId;
    }

    public void setItemId(String itemId) {
        this.itemId = itemId;
    }

    public String getImageURL() {
        return imageURL;
    }

    public void setImageURL(String imageURL) {
        this.imageURL = imageURL;
    }

    public Boolean getIsPrimary() {
        return isPrimary;
    }

    public void setIsPrimary(Boolean isPrimary) {
        this.isPrimary = isPrimary;
    }

    public LocalDateTime getUploadedAt() {
        return uploadedAt;
    }

    public void setUploadedAt(LocalDateTime uploadedAt) {
        this.uploadedAt = uploadedAt;
    }

    // </editor-fold>
}
