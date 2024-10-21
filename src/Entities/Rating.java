package Entities;

import java.util.UUID;

public class Rating {
    // Note => No Need for Created At Field Since there is an Auditable Entity;
    // Note => There should be user id field so that each user has its own rating on an item.
    private UUID id;
    private String itemId;
    private double rating;


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

    public double getRating() {
        return rating;
    }

    public void setRating(double rating) {
        this.rating = rating;
    }

    // </editor-fold>
}
