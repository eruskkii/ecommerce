package ecommerce.services;



import ecommerce.dao.WishlistDao;
import ecommerce.model.Wishlist;
import ecommerce.config.ConnectionUtils;

import java.sql.Connection;
import java.util.List;

public class WishlistService {
    private WishlistDao wishlistDao;

    public WishlistService() {
        Connection connection = ConnectionUtils.getConnection();
        this.wishlistDao = new WishlistDao(connection);
    }

    public WishlistService(WishlistDao wishlistDao) {
        this.wishlistDao = wishlistDao;
    }

    // Add a new wishlist item
    public boolean addWishlistItem(int userId, int productId) {
        Wishlist wishlist = new Wishlist(userId, productId);
        return wishlistDao.addWishlistItem(wishlist);
    }

    // Retrieve all wishlist items for a specific user
    public List<Wishlist> getWishlistForUser(int userId) {
        return wishlistDao.getWishlistByUserId(userId);
    }

    public boolean removeWishlistItem(int wishlistId) {
        return wishlistDao.removeWishlistItem(wishlistId);
    }
}
