package ecommerce.controller;

import ecommerce.model.Cart;
import ecommerce.services.CartService;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.List;

@WebServlet(name = "cartServlet", value = "/cart-servlet")
public class CartServlet extends HttpServlet {
    private CartService cartService = new CartService();

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String action = request.getParameter("action");

        if ("addToCart".equals(action)) {
            int userId = Integer.parseInt(request.getParameter("userId"));
            int productId = Integer.parseInt(request.getParameter("productId"));
            int quantity = Integer.parseInt(request.getParameter("quantity"));

            // Create Cart object and call CartService to add product to the cart
            Cart cart = new Cart();
            cart.setUserId(userId);
            cart.setProductId(productId);
            cart.setQuantity(quantity);

            boolean success = cartService.addToCart(cart);

            if (success) {
                response.sendRedirect("cart.jsp?success=true");
            } else {
                response.sendRedirect("cart.jsp?error=addFailed");
            }
        } else if ("updateQuantity".equals(action)) {
            int cartId = Integer.parseInt(request.getParameter("cartId"));
            int quantity = Integer.parseInt(request.getParameter("quantity"));

            boolean success = cartService.updateCartQuantity(cartId, quantity);

            if (success) {
                response.sendRedirect("cart.jsp?success=updated");
            } else {
                response.sendRedirect("cart.jsp?error=updateFailed");
            }
        } else if ("removeFromCart".equals(action)) {
            int cartId = Integer.parseInt(request.getParameter("cartId"));

            boolean success = cartService.removeFromCart(cartId);

            if (success) {
                response.sendRedirect("cart.jsp?success=removed");
            } else {
                response.sendRedirect("cart.jsp?error=removeFailed");
            }
        }
    }

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

        // Forward to cart.jsp
        request.getRequestDispatcher("WEB-INF/cart.jsp").forward(request, response);
    }
}
