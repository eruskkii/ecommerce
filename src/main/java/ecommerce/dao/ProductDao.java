package ecommerce.dao;

import ecommerce.config.ConnectionUtils;
import ecommerce.model.Product;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ProductDao {
    private Connection connection;

    public ProductDao(Connection connection) {
        this.connection = ConnectionUtils.getConnection();
    }

    private static final String URL = "jdbc:mysql://localhost:3306/ecommerce";
    private static final String USER = "root";
    private static final String PASSWORD = "Lion5$belt123";


//     Add product to the database and return success status
    public boolean addProducts(Product product) {
        String sql = "INSERT INTO products (name, description, price, quantity, category_id) VALUES (?, ?, ?, ?, ?)";
        try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            preparedStatement.setString(1, product.getName());
            preparedStatement.setString(2, product.getDescription());
            preparedStatement.setDouble(3, product.getPrice());
            preparedStatement.setInt(4, product.getQuantity());
            preparedStatement.setInt(5, product.getCategory_id());

            // Execute the update and check if a row was inserted
            int rowsInserted = preparedStatement.executeUpdate();
            return rowsInserted > 0; // Return true if the product was successfully added
        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException("Database error while adding product", e);
        }
    }

    public double getProductPriceById(int productId) {
        double price = 0.0;
        String query = "SELECT price FROM products WHERE product_id = ?";

        try (Connection connection = ConnectionUtils.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(query)) {

            preparedStatement.setInt(1, productId);
            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                if (resultSet.next()) {
                    price = resultSet.getDouble("price");
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException("Error fetching product price", e);
        }

        return price;
    }

    public boolean updateProduct(Product product) {
        String updateSQL = "UPDATE products SET name = ?, price = ?, quantity = ? WHERE product_id = ?";
        try (PreparedStatement preparedStatement = connection.prepareStatement(updateSQL)) {
            preparedStatement.setString(1, product.getName());
            preparedStatement.setDouble(2, product.getPrice());
            preparedStatement.setInt(3, product.getQuantity());
            preparedStatement.setInt(4, product.getProduct_id());
            preparedStatement.executeUpdate();
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    public boolean deleteProduct(int productId) {
        String deleteSQL = "DELETE FROM products WHERE product_id = ?";
        try (PreparedStatement preparedStatement = connection.prepareStatement(deleteSQL)) {
            preparedStatement.setInt(1, productId);
            preparedStatement.executeUpdate();
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    public List<Product> getAllProducts() {
        List<Product> products = new ArrayList<>();
        String query = "SELECT product_id, name, description, price, quantity FROM products";

        try (PreparedStatement preparedStatement = connection.prepareStatement(query);
             ResultSet resultSet = preparedStatement.executeQuery()) {

            while (resultSet.next()) {
                int product_id = resultSet.getInt("product_id");
                String name = resultSet.getString("name");
                String description = resultSet.getString("description");
                double price = resultSet.getDouble("price");
                int quantity = resultSet.getInt("quantity");

                Product product = new Product(product_id, name, description, price, quantity);
                products.add(product);
            }

        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException("Error fetching all products", e);
        }

        return products;
    }

//    public static List<Product> getAllProducts() {
//        List<Product> products = new ArrayList<>();
//        try (Connection conn = DriverManager.getConnection(URL, USER, PASSWORD)) {
//            String sql = "SELECT * FROM products";
//            PreparedStatement stmt = conn.prepareStatement(sql);
//            ResultSet rs = stmt.executeQuery();
//
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
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//        return products;
//    }





}
