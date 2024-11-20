package ecommerce.controller;

import ecommerce.model.Cart;
import ecommerce.services.CartService;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;
import java.util.List;

@WebServlet(name = "cartServlet", value = "/cart-servlet")
public class CartServlet extends HttpServlet {
    private CartService cartService = new CartService();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        // Fetch userId from the session to maintain security
        Integer userId = (Integer) request.getSession().getAttribute("userId");

        if (userId == null) {
            // If userId is not found in the session, redirect to login
            response.sendRedirect("login.jsp");
            return;
        }

        // Get cart items and calculate the total amount using CartService
        List<Cart> cartItems = cartService.getCartItemsByUserId(userId);
        double totalAmount = cartService.calculateTotalAmount(userId);

        // Set cart items and total amount in request attributes for the JSP
        request.setAttribute("cartItems", cartItems);
        request.setAttribute("totalAmount", totalAmount);

        // Include any success or error messages in request attributes
        String successMessage = request.getParameter("success");
        String errorMessage = request.getParameter("error");
        if (successMessage != null) {
            request.setAttribute("success", successMessage);
        }
        if (errorMessage != null) {
            request.setAttribute("error", errorMessage);
        }

        // Forward to cart.jsp
        request.getRequestDispatcher("cart.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String action = request.getParameter("action");

        // Get userId from session for all actions
        HttpSession session = request.getSession();
        Integer userId = (Integer) session.getAttribute("userId");
        System.out.println("UserId from session: " + userId);

        if (userId == null) {
            // If user is not logged in, redirect to login page
            System.out.println("User ID is null. Redirecting to login page.");
            response.sendRedirect("login.jsp?error=unauthorized");
            return;
        }

        try {
            switch (action) {
                case "addToCart":
                    handleAddToCart(request, response, userId);
                    break;

                case "updateQuantity":
                    handleUpdateCartQuantity(request, response, userId);
                    break;

                case "removeFromCart":
                    handleRemoveFromCart(request, response, userId);
                    break;

                default:
                    response.sendRedirect("cart-servlet?error=invalidAction");
                    break;
            }
        } catch (NumberFormatException e) {
            e.printStackTrace();
            response.sendRedirect("catalogue-servlet?error=invalidProductOrQuantity");
        }
    }

    // Method to handle adding a product to the cart
    private void handleAddToCart(HttpServletRequest request, HttpServletResponse response, int userId)
            throws IOException {
        int productId = Integer.parseInt(request.getParameter("productId"));
        if (productId <= 0) {
            System.out.println("Product ID is invalid: " + productId);
            response.sendRedirect("catalogue.jsp?error=addToCartFailed");
            return;
        }

        int quantity = Integer.parseInt(request.getParameter("quantity"));

        // Create Cart object and add it
        Cart cart = new Cart();
        cart.setUserId(userId);
        cart.setProductId(productId);
        cart.setQuantity(quantity);

        boolean success = cartService.addToCart(cart);

        if (success) {
            response.sendRedirect("cart-servlet?success=addedToCart");
        } else {
            response.sendRedirect("catalogue-servlet?error=addToCartFailed");
        }
    }

    // Method to handle updating cart quantity
    private void handleUpdateCartQuantity(HttpServletRequest request, HttpServletResponse response, int userId)
            throws IOException {
        int cartId = Integer.parseInt(request.getParameter("cartId"));
        int newQuantity = Integer.parseInt(request.getParameter("quantity"));

        if (newQuantity <= 0) {
            System.out.println("New quantity is invalid: " + newQuantity);
            response.sendRedirect("cart-servlet?error=updateFailed");
            return;
        }

        boolean success = cartService.updateCartQuantity(cartId, newQuantity);

        if (success) {
            response.sendRedirect("cart-servlet?success=updated");
        } else {
            response.sendRedirect("cart-servlet?error=updateFailed");
        }
    }

    // Method to handle removing a product from the cart
    private void handleRemoveFromCart(HttpServletRequest request, HttpServletResponse response, int userId)
            throws IOException {
        int cartId = Integer.parseInt(request.getParameter("cartId"));

        boolean success = cartService.removeFromCart(cartId);

        if (success) {
            response.sendRedirect("cart-servlet?success=removed");
        } else {
            response.sendRedirect("cart-servlet?error=removeFailed");
        }
    }




}
