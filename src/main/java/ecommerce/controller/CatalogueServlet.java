package ecommerce.controller;

import ecommerce.dao.ProductDao;
import ecommerce.model.Product;
//import ecommerce.services.ProductService;

import ecommerce.services.ProductService;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

@WebServlet(name = "CatalogueServlet", value = "/catalogue-servlet")
public class CatalogueServlet extends HttpServlet {

//    private static final String URL = "jdbc:mysql://localhost:3306/ecommerce";
//    private static final String USER = "root";
//    private static final String PASSWORD = "Lion5$belt123";
    // Using ProductService to maintain consistency
    private ProductService productService = new ProductService();


    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // Get all products from ProductService
//        List<Product> products = productService.getAllProducts();
        List<Product> products = productService.getAllProducts();

        // Set products as a request attribute for the JSP page to access
        request.setAttribute("products", products);

        // Forward to catalogue.jsp to display the products
        request.getRequestDispatcher("catalogue.jsp").forward(request, response);
    }

//    @Override
//    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
//        ArrayList<Product> products = new ArrayList<>();
//
//        try {
//            // Load the database driver
//            Class.forName("com.mysql.cj.jdbc.Driver");
//
//            // Establish a connection
//            Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/ecommerce", "root", "Lion5$belt123");
//
//            // Execute SQL query
//            String sql = "SELECT * FROM Products";
//            PreparedStatement stmt = conn.prepareStatement(sql);
//            ResultSet rs = stmt.executeQuery();
//
//            System.out.println(sql);
//
//            // Populate product list
//            while (rs.next()) {
//                Product product = new Product();
//                product.setProduct_id(rs.getInt("product_id"));
//                product.setName(rs.getString("name"));
//                product.setDescription(rs.getString("description"));
//                product.setPrice(rs.getDouble("price"));
//                product.setQuantity(rs.getInt("quantity"));
////                product.setImageUrl(rs.getBytes("imageUrl"));
//                products.add(product);
//                System.out.println("Product list shown successfully");
//            }
//
//            conn.close();
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//
//        // Pass the product list to JSP
//        request.setAttribute("products", products);
//        request.getRequestDispatcher("catalogue.jsp").forward(request, response);
//    }
}

