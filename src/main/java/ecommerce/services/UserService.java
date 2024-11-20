package ecommerce.services;



import ecommerce.dao.UserDao;
import ecommerce.model.UserSignup;
import ecommerce.config.ConnectionUtils;

import java.util.List;

public class UserService {
    private UserDao userDao;

    public UserService() {
        this.userDao = new UserDao(ConnectionUtils.getConnection());
    }

    public boolean registerUser(UserSignup user) {
        // Check if the user already exists
        if (userDao.getUserByEmail(user.getEmail()) == null) {
            userDao.addUser(user);
            return true;  // Successfully registered
        }
        return false;  // User already exists
    }

    public UserSignup authenticateUser(String email, String password) {
        return userDao.loginUser(email, password);
    }

    public List<UserSignup> getAllUsers() {
        return userDao.getAllUsers();
    }
}

