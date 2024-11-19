package ecommerce.dao;

import ecommerce.model.Cart;
import ecommerce.config.ConnectionUtils;
import ecommerce.model.Product;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class CartDao {
    private Connection connection;

    public CartDao(Connection connection) {
        this.connection = ConnectionUtils.getConnection();
    }

    // Method to add a product to the cart
    public boolean addToCart(Cart cart) {
        String insertSQL = "INSERT INTO cart (user_id, product_id, quantity) VALUES (?, ?, ?)";
        try (PreparedStatement preparedStatement = connection.prepareStatement(insertSQL)) {
            preparedStatement.setInt(1, cart.getUserId());
            preparedStatement.setInt(2, cart.getProductId());
            preparedStatement.setInt(3, cart.getQuantity());
            preparedStatement.executeUpdate();
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    // Method to update quantity in the cart
    public boolean updateCartQuantity(int cartId, int newQuantity) {
        String updateSQL = "UPDATE cart SET quantity = ? WHERE cart_id = ?";
        try (PreparedStatement preparedStatement = connection.prepareStatement(updateSQL)) {
            preparedStatement.setInt(1, newQuantity);
            preparedStatement.setInt(2, cartId);
            preparedStatement.executeUpdate();
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    // Method to remove a product from the cart
    public boolean removeFromCart(int cartId) {
        String deleteSQL = "DELETE FROM cart WHERE cart_id = ?";
        try (PreparedStatement preparedStatement = connection.prepareStatement(deleteSQL)) {
            preparedStatement.setInt(1, cartId);
            preparedStatement.executeUpdate();
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    public List<Cart> getCartItemsByUserId(int userId) {
        List<Cart> cartItems = new ArrayList<>();

        String query = "SELECT cart.cart_id, cart.user_id, cart.product_id, cart.quantity, cart.added_at, " +
                "products.name, products.price " +
                "FROM cart " +
                "JOIN products ON cart.product_id = products.product_id " +
                "WHERE cart.user_id = ?";

        try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.setInt(1, userId);

            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                while (resultSet.next()) {
                    int cartId = resultSet.getInt("cart_id");
                    int productId = resultSet.getInt("product_id");
                    int quantity = resultSet.getInt("quantity");
                    Timestamp addedAt = resultSet.getTimestamp("added_at");

                    // Product details
                    String productName = resultSet.getString("name");
                    double productPrice = resultSet.getDouble("price");

                    // Create a product instance
                    Product product = new Product();
                    product.setProduct_id(productId);
                    product.setName(productName);
                    product.setPrice(productPrice);

                    // Create a cart instance
                    Cart cartItem = new Cart();
                    cartItem.setCartId(cartId);
                    cartItem.setUserId(userId);
                    cartItem.setProduct(product);
                    cartItem.setQuantity(quantity);
                    cartItem.setAddedAt(addedAt);

                    // Add the cart item to the list
                    cartItems.add(cartItem);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException("Error fetching cart items for user " + userId, e);
        }

        return cartItems;
    }


    public double calculateTotalAmount(int userId) {
        double totalAmount = 0.0;
        String query = "SELECT SUM(products.price * cart.quantity) AS total_amount " +
                "FROM cart " +
                "JOIN products ON cart.product_id = products.product_id " +
                "WHERE cart.user_id = ?";

        try (Connection connection = ConnectionUtils.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(query)) {

            preparedStatement.setInt(1, userId);
            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                if (resultSet.next()) {
                    totalAmount = resultSet.getDouble("total_amount");
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException("Error calculating total cart amount", e);
        }

        return totalAmount;
    }



}