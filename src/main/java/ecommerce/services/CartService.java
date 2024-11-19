package ecommerce.services;

import ecommerce.dao.CartDao;
import ecommerce.model.Cart;
import ecommerce.config.ConnectionUtils;

import java.sql.Connection;
import java.util.List;

public class CartService {

    private CartDao cartDao;

    // Constructor to initialize CartDao with a database connection
    public CartService() {
        Connection connection = ConnectionUtils.getConnection();
        this.cartDao = new CartDao(connection);
    }

    // Get all cart items by userId
    public List<Cart> getCartItemsByUserId(int userId) {
        return cartDao.getCartItemsByUserId(userId);
    }

    // Add item to the cart
    public boolean addToCart(Cart cart) {
        return cartDao.addToCart(cart);
    }

    // Remove item from the cart by cart ID
    public boolean removeFromCart(int cartId) {
        return cartDao.removeFromCart(cartId);
    }

    // Update quantity of a cart item by cart ID
    public boolean updateCartQuantity(int cartId, int newQuantity) {
        return cartDao.updateCartQuantity(cartId, newQuantity);
    }

    // Calculate total amount of items in the cart
    public double calculateTotalAmount(int userId) {
        return cartDao.calculateTotalAmount(userId);
    }

    // Get user cart by user ID (this was a duplicated method and is consolidated with getCartItemsByUserId)
    // public List<Cart> getUserCart(int userId) {
    //     return cartDao.getCartItemsByUserId(userId);
    // }
}
