package ecommerce.dao;

import ecommerce.model.Product;
import ecommerce.model.UserSignup;
import ecommerce.config.ConnectionUtils;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class UserDao {
    private Connection connection;


    public UserDao(Connection connection) {
        this.connection = ConnectionUtils.getConnection();
    }

    public void addUser(UserSignup user){
        try{
            PreparedStatement preparedStatement = connection.prepareStatement("INSERT INTO users (firstName, lastName, username, email, password, role) VALUES (?, ?, ?, ?, ?, ?)");
            preparedStatement.setString(1, user.getFirstName());
            preparedStatement.setString(2, user.getLastName());
            preparedStatement.setString(3, user.getUserName());
            preparedStatement.setString(4, user.getEmail());
            preparedStatement.setString(5, user.getPassword());
            preparedStatement.setString(6, user.getRole());
            preparedStatement.executeUpdate();

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public UserSignup getUserByEmail(String email) {
        UserSignup user = null;
        try {
            String sql = "SELECT * FROM users WHERE email = ?";
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, email);
            ResultSet resultSet = preparedStatement.executeQuery();

            if (resultSet.next()) {
                user = new UserSignup(
                        resultSet.getString("firstName"),
                        resultSet.getString("lastName"),
                        resultSet.getString("username"),
                        resultSet.getString("email"),
                        resultSet.getString("password"),
                        resultSet.getString("role")
                );
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return user; // Returns null if the user was not found
    }

    public UserSignup loginUser(String email, String password) {
        UserSignup user = null;
        try {
            String sql = "SELECT user_id, firstname, lastname, username, email, password, role FROM users WHERE email = ? AND password = ?";

            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, email);
            preparedStatement.setString(2, password);
            ResultSet resultSet = preparedStatement.executeQuery();

            if (resultSet.next()) {
                user = new UserSignup(
                        resultSet.getInt("user_id"),
                        resultSet.getString("firstName"),
                        resultSet.getString("lastName"),
                        resultSet.getString("username"),
                        resultSet.getString("email"),
                        resultSet.getString("password"),
                        resultSet.getString("role")
                );
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return user; // Will return null if the user doesn't exist or credentials are wrong
    }

    public List<UserSignup> getAllUsers() {
        List<UserSignup> users = new ArrayList<>();
        String query = "SELECT user_id, firstname, lastname, username, email, created_at, role FROM users";

        try (PreparedStatement preparedStatement = connection.prepareStatement(query);
             ResultSet resultSet = preparedStatement.executeQuery()) {

            while (resultSet.next()) {
                int id = resultSet.getInt("user_id");
                String firstName = resultSet.getString("firstname");
                String lastName = resultSet.getString("lastname");
                String userName = resultSet.getString("username");
                String email = resultSet.getString("email");
                Timestamp createdAt = resultSet.getTimestamp("created_at");
                String role = resultSet.getString("role");

                UserSignup user = new UserSignup(id, firstName, lastName, userName, email, createdAt, role);
                users.add(user);
            }

        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException("Error fetching all users", e);
        }

        return users;
    }

}
