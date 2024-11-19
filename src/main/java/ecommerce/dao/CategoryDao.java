package ecommerce.dao;

import ecommerce.model.Category;
import ecommerce.config.ConnectionUtils;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class CategoryDao {
    private Connection connection;

    public CategoryDao(Connection connection) {
        this.connection = ConnectionUtils.getConnection();
    }

    // Add a new category and return the generated category ID
    public int addCategory(Category category) {
        String insertSQL = "INSERT INTO categories (category_name) VALUES (?)";
        try (PreparedStatement preparedStatement = connection.prepareStatement(insertSQL, Statement.RETURN_GENERATED_KEYS)) {
            preparedStatement.setString(1, category.getCategoryName());
            preparedStatement.executeUpdate();

            // Retrieve the generated category ID
            try (ResultSet generatedKeys = preparedStatement.getGeneratedKeys()) {
                if (generatedKeys.next()) {
                    int categoryId = generatedKeys.getInt(1);
                    category.setCategoryId(categoryId);
                    return categoryId; // Return the generated ID
                } else {
                    throw new SQLException("Creating category failed, no ID obtained.");
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
    }

    // Retrieve all categories from the database
    public List<Category> getAllCategories() {
        List<Category> categories = new ArrayList<>();
        String query = "SELECT category_id, category_name FROM categories";
        try (Connection connection = ConnectionUtils.getConnection();
             Statement statement = connection.createStatement();
             ResultSet resultSet = statement.executeQuery(query)) {

            while (resultSet.next()) {
                int id = resultSet.getInt("category_id");
                String name = resultSet.getString("category_name");
                categories.add(new Category(id, name));
            }
        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException("Error fetching categories", e);
        }
        return categories;
    }
}
