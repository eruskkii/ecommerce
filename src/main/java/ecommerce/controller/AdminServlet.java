package ecommerce.controller;

import ecommerce.model.Product;
import ecommerce.services.ProductService;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;

@WebServlet(name = "AdminServlet", value = "/admin-dashboard")
public class AdminServlet extends HttpServlet {
    private ProductService productService = new ProductService();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // Check if the user is an admin
        HttpSession session = request.getSession();
        String role = (String) session.getAttribute("role");

        if (role == null || !role.equals("admin")) {
            // If the user is not an admin, redirect to login page or error page
            response.sendRedirect("login.jsp?error=unauthorized");
            return;
        }

        // If user is an admin, forward to the admin dashboard JSP
        request.getRequestDispatcher("WEB-INF/admin-dashboard.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // Since admins might have several actions (like adding products, managing categories), the `action` parameter can be used to decide
        String action = request.getParameter("action");

        // Depending on the action, delegate to specific methods or servlets
        if ("addCategory".equals(action)) {
            // Redirect to category addition form
            response.sendRedirect("addCategory.jsp");
        } else if ("addProduct".equals(action)) {
            // Redirect to product addition form
            response.sendRedirect("addProduct.jsp");
        } else if ("updateProduct".equals(action)) {
            // Handle update product form submission
            int productId = Integer.parseInt(request.getParameter("productId"));
            String name = request.getParameter("name");
            double price = Double.parseDouble(request.getParameter("price"));
            int quantity = Integer.parseInt(request.getParameter("quantity"));

            Product updatedProduct = new Product();
            updatedProduct.setProduct_id(productId);
            updatedProduct.setName(name);
            updatedProduct.setPrice(price);
            updatedProduct.setQuantity(quantity);

            // Assume productService is already instantiated
            boolean success = productService.updateProduct(updatedProduct);

            if (success) {
                response.sendRedirect("admin-dashboard.jsp?success=updateSuccess");
            } else {
                response.sendRedirect("admin-dashboard.jsp?error=updateFailed");
            }
        } else if ("deleteProduct".equals(action)) {
            // Handle delete product action
            int productId = Integer.parseInt(request.getParameter("productId"));
            boolean confirmation = "yes".equals(request.getParameter("confirmation"));

            if (confirmation) {
                boolean success = productService.deleteProduct(productId);

                if (success) {
                    response.sendRedirect("admin-dashboard.jsp?success=deleteSuccess");
                } else {
                    response.sendRedirect("admin-dashboard.jsp?error=deleteFailed");
                }
            } else {
                response.sendRedirect("admin-dashboard.jsp?error=deleteNotConfirmed");
            }
        } else {
            // Default action: Display the admin dashboard
            response.sendRedirect("admin-dashboard.jsp");
        }
    }
}


