package ecommerce.controller;

import ecommerce.config.ConnectionUtils;
import ecommerce.dao.WishlistDao;
import ecommerce.services.WishlistService;
import ecommerce.model.Wishlist;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

@WebServlet(name = "wishlistServlet", value = "/wishlist")
public class WishlistServlet extends HttpServlet {
    private WishlistService wishlistService = new WishlistService();

    public void init() throws ServletException {
        this.wishlistService = new WishlistService(new WishlistDao(ConnectionUtils.getConnection()));
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        // Identify the action from the request
        String action = request.getParameter("action");

        try {
            if ("add".equals(action)) {
                // Handle adding an item to the wishlist
                int userId = Integer.parseInt(request.getParameter("userId"));
                int productId = Integer.parseInt(request.getParameter("productId"));

                boolean success = wishlistService.addWishlistItem(userId, productId);
                if (success) {
                    request.setAttribute("message", "Item added to wishlist successfully.");
                } else {
                    request.setAttribute("message", "Failed to add item to wishlist.");
                }
            } else if ("remove".equals(action)) {
                // Handle removing an item from the wishlist
                int wishlistId = Integer.parseInt(request.getParameter("wishlistId"));

                boolean success = wishlistService.removeWishlistItem(wishlistId);
                if (success) {
                    request.setAttribute("message", "Item removed from wishlist successfully.");
                } else {
                    request.setAttribute("message", "Failed to remove item from wishlist.");
                }
            }

            // Forward to wishlist.jsp to display the updated list
            request.getRequestDispatcher("wishlist.jsp").forward(request, response);

        } catch (NumberFormatException e) {
            e.printStackTrace();
            request.setAttribute("message", "An error occurred while processing your request.");
            request.getRequestDispatcher("wishlist.jsp").forward(request, response);
        }
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        // Retrieve userId from session or request
        int userId = (int) request.getSession().getAttribute("userId");
        List<Wishlist> wishlist = wishlistService.getWishlistForUser(userId);

        // Forward the wishlist to the JSP for display
        request.setAttribute("wishlist", wishlist);
        request.getRequestDispatcher("wishlist.jsp").forward(request, response);
    }



}

