package ecommerce.controller;

import ecommerce.model.Product;
import ecommerce.services.ProductService;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.List;

@WebServlet(name = "CatalogueServlet", value = "/catalogue-servlet")
public class CatalogueServlet extends HttpServlet {
    // Using ProductService to maintain consistency
    private ProductService productService = new ProductService();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // Get all products from ProductService
        List<Product> products = productService.getAllProducts();

        // Set products as a request attribute for the JSP page to access
        request.setAttribute("products", products);

        // Forward to catalogue.jsp to display the products
        request.getRequestDispatcher("catalogue.jsp").forward(request, response);
    }
}

