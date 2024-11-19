package ecommerce.model;

import java.sql.Timestamp;

public class Wishlist {
    private int wishlistId;
    private int userId;
    private int productId;
    private Timestamp addedAt;

    // Constructor for creating a new wishlist item (no need to pass addedAt)
    public Wishlist(int userId, int productId) {
        this.userId = userId;
        this.productId = productId;
    }

    public Wishlist(){

    }

    // Constructor for retrieving from DB (addedAt is included)
    public Wishlist(int wishlistId, int userId, int productId, Timestamp addedAt) {
        this.wishlistId = wishlistId;
        this.userId = userId;
        this.productId = productId;
        this.addedAt = addedAt;
    }

    // Getters and Setters
    public int getWishlistId() {
        return wishlistId;
    }

    public void setWishlistId(int wishlistId) {
        this.wishlistId = wishlistId;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public int getProductId() {
        return productId;
    }

    public void setProductId(int productId) {
        this.productId = productId;
    }

    public Timestamp getAddedAt() {
        return addedAt;
    }

    public void setAddedAt(Timestamp addedAt) {
        this.addedAt = addedAt;
    }
}
