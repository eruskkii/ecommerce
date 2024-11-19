package ecommerce.controller;

import ecommerce.model.Category;
import ecommerce.model.Product;
import ecommerce.services.ProductService;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.List;

@WebServlet(name = "addProductToServlet", value = "/addProduct")
public class ProductServlet extends HttpServlet {
    private ProductService productService = new ProductService();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        List<Category> categories = productService.getAllCategories();
        request.setAttribute("categories", categories);

        String successMessage = request.getParameter("success");
        if ("true".equals(successMessage)) {
            request.setAttribute("successMessage", "Product added successfully!");
        }

        request.getRequestDispatcher("/WEB-INF/addProduct.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String name = request.getParameter("name");
        String description = request.getParameter("description");
        double price = Double.parseDouble(request.getParameter("price"));
        int quantity = Integer.parseInt(request.getParameter("quantity"));
        int categoryId = Integer.parseInt(request.getParameter("categoryId"));

        Product product = new Product();
        product.setName(name);
        product.setDescription(description);
        product.setPrice(price);
        product.setQuantity(quantity);
        product.setCategory_id(categoryId);

        boolean productAdded = productService.addProduct(product);

        if (productAdded) {
            response.sendRedirect(request.getContextPath() + "/addProduct?success=true");
        } else {
            response.sendRedirect(request.getContextPath() + "/addProduct?success=false");
        }
    }
}
