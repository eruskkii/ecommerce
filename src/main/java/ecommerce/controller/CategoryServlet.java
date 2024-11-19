package ecommerce.controller;

import ecommerce.dao.CategoryDao;
import ecommerce.model.Category;
import ecommerce.config.ConnectionUtils;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

@WebServlet(name = "addCategoryToServlet", value = "/addCategory")
public class CategoryServlet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String name = request.getParameter("name");

        // Create a new Category object
        Category category = new Category();
        category.setCategoryName(name);

        // Add the category to the database and get the generated ID
        CategoryDao categoryDao = new CategoryDao(ConnectionUtils.getConnection());
        int categoryId = categoryDao.addCategory(category);

        // Redirect with success message to display on the JSP page
        response.sendRedirect(request.getContextPath() + "/addCategory.jsp?success=true");
    }
}
