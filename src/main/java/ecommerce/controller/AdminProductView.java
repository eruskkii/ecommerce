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

@WebServlet(name = "AdminProductView", value = "/view-all-products")
public class AdminProductView extends HttpServlet {


    private ProductService productService = new ProductService(); // Assuming this service fetches products from the DB

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        List<Product> productList = productService.getAllProducts();
        request.setAttribute("productList", productList);
        request.getRequestDispatcher("viewAllProducts.jsp").forward(request, response);

    }

}