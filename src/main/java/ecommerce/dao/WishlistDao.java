package ecommerce.dao;



import ecommerce.model.Wishlist;
import ecommerce.config.ConnectionUtils;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class WishlistDao {
    private Connection connection;

    public WishlistDao(Connection connection) {
        this.connection = ConnectionUtils.getConnection();
    }

    // Method to add a new wishlist item
    public boolean addWishlistItem(Wishlist wishlist) {
        String insertSQL = "INSERT INTO wishlist (user_id, product_id) VALUES (?, ?)";
        try (PreparedStatement preparedStatement = connection.prepareStatement(insertSQL)) {
            preparedStatement.setInt(1, wishlist.getUserId());
            preparedStatement.setInt(2, wishlist.getProductId());
            int rowsAffected = preparedStatement.executeUpdate();
            return rowsAffected > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException("Error adding item to wishlist", e);
        }
    }

    // Method to retrieve all wishlist items for a user
    public List<Wishlist> getWishlistByUserId(int userId) {
        List<Wishlist> wishlist = new ArrayList<>();
        String query = "SELECT wishlist_id, user_id, product_id, added_at FROM wishlist WHERE user_id = ?";
        try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.setInt(1, userId);
            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                while (resultSet.next()) {
                    int wishlistId = resultSet.getInt("wishlist_id");
                    int productId = resultSet.getInt("product_id");
                    Timestamp addedAt = resultSet.getTimestamp("added_at");
                    wishlist.add(new Wishlist(wishlistId, userId, productId, addedAt));
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException("Error fetching wishlist", e);
        }
        return wishlist;
    }

    public boolean removeWishlistItem(int wishlistId) {
        String deleteSQL = "DELETE FROM wishlist WHERE wishlist_id = ?";
        try (PreparedStatement preparedStatement = connection.prepareStatement(deleteSQL)) {
            preparedStatement.setInt(1, wishlistId);
            int rowsAffected = preparedStatement.executeUpdate();
            return rowsAffected > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException("Error removing item from wishlist", e);
        }
    }

}

