package ecommerce.controller;

import ecommerce.model.Product;
import ecommerce.model.UserSignup;
import ecommerce.services.ProductService;
import ecommerce.services.UserService;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;
import java.util.List;

@WebServlet(name = "AdminServlet", value = "/admin-dashboard")
public class AdminServlet extends HttpServlet {
    private ProductService productService = new ProductService();
    private UserService userService = new UserService();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // Check if the user is an admin
        HttpSession session = request.getSession();
        UserSignup loggedInUser = (UserSignup) session.getAttribute("loggedInUser");

        if (loggedInUser == null || !"admin".equals(loggedInUser.getRole())) {
            // If the user is not an admin, redirect to login page or error page
            response.sendRedirect("login.jsp?error=unauthorized");
            return;
        }

        // If user is an admin, forward to the admin dashboard JSP
        request.getRequestDispatcher("admin-dashboard.jsp").forward(request, response);


    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        // Determine which action the admin is trying to perform
        String action = request.getParameter("action");

        try {
            switch (action) {
                case "addCategory":
                    // Redirect to category addition form
                    response.sendRedirect("addCategory.jsp");
                    break;

                case "addProduct":
                    // Redirect to product addition form
                    response.sendRedirect("addProduct");
                    break;

                case "updateProduct":
                    handleUpdateProduct(request, response);
                    break;

                case "deleteProduct":
                    handleDeleteProduct(request, response);
                    break;

//                case "viewAllProducts":
//                    handleViewAllProducts(request, response);
//                    break;

//                case "viewAllUsers":
//                    handleViewAllUsers(request, response);
//                    break;

                default:
                    // If no valid action is found, redirect to admin dashboard
                    response.sendRedirect("admin-dashboard");
                    break;
            }
        } catch (NumberFormatException e) {
            e.printStackTrace();
            response.sendRedirect("admin-dashboard?error=invalidInput");
        }
    }

    // Method to handle updating a product
    private void handleUpdateProduct(HttpServletRequest request, HttpServletResponse response)
            throws IOException {
        int productId = Integer.parseInt(request.getParameter("product_id"));
        String name = request.getParameter("name");
        double price = Double.parseDouble(request.getParameter("price"));
        int quantity = Integer.parseInt(request.getParameter("quantity"));

        Product updatedProduct = new Product();
        updatedProduct.setProduct_id(productId);
        updatedProduct.setName(name);
        updatedProduct.setPrice(price);
        updatedProduct.setQuantity(quantity);

        boolean success = productService.updateProduct(updatedProduct);

        if (success) {
            response.sendRedirect("admin-dashboard?success=updateSuccess");
        } else {
            response.sendRedirect("admin-dashboard?error=updateFailed");
        }
    }

    // Method to handle deleting a product
    private void handleDeleteProduct(HttpServletRequest request, HttpServletResponse response)
            throws IOException {
        int productId = Integer.parseInt(request.getParameter("productId"));
        boolean confirmation = "yes".equals(request.getParameter("confirmation"));

        if (confirmation) {
            boolean success = productService.deleteProduct(productId);

            if (success) {
                response.sendRedirect("admin-dashboard?success=deleteSuccess");
            } else {
                response.sendRedirect("admin-dashboard?error=deleteFailed");
            }
        } else {
            response.sendRedirect("admin-dashboard?error=deleteNotConfirmed");
        }
    }

    // Method to handle viewing all products
    private void handleViewAllProducts(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        List<Product> productList = productService.getAllProducts();
        request.setAttribute("productList", productList);

        // Forward the list to the view all products JSP
        request.getRequestDispatcher("viewAllProducts.jsp").forward(request, response);
    }

    // Method to handle viewing all users
    private void handleViewAllUsers(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        List<UserSignup> userList = userService.getAllUsers();
        request.setAttribute("userList", userList);

        // Forward the list to the view all users JSP
        request.getRequestDispatcher("viewAllUsers.jsp").forward(request, response);
    }

}


