package ecommerce.controller;

import ecommerce.model.Product;
import ecommerce.services.ProductService;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

@WebServlet(name = "updateProductServlet", value = "/updateProductServlet")
public class UpdateProductServlet extends HttpServlet {
    private ProductService productService = new ProductService();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        // Get productId from the request
        int productId = Integer.parseInt(request.getParameter("productId"));

        // Fetch product details from the database
        Product product = productService.getProductById(productId);

        // Set product details in the request to be used in the JSP
        request.setAttribute("product", product);

        // Forward to updateProduct.jsp
        request.getRequestDispatcher("update-product.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // Get parameters from the form submission
        int productId = Integer.parseInt(request.getParameter("product_id"));
        String name = request.getParameter("name");
        String description = request.getParameter("description");
        double price = Double.parseDouble(request.getParameter("price"));
        int quantity = Integer.parseInt(request.getParameter("quantity"));

        // Update the product object
        Product product = new Product();
        product.setProduct_id(productId);
        product.setName(name);
        product.setDescription(description);
        product.setPrice(price);
        product.setQuantity(quantity);

        // Use ProductService to update the product
        boolean success = productService.updateProduct(product);

        // Redirect based on update status
        if (success) {
            response.sendRedirect("view-all-products?success=updateSuccess");
        } else {
            response.sendRedirect("updateProductServlet?productId=" + productId + "&error=updateFailed");
        }
    }
}



