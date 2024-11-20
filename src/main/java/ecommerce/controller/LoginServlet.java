package ecommerce.controller;

import ecommerce.model.UserSignup;
import ecommerce.services.UserService;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.*;
import java.sql.*;

@WebServlet(name = "loginServlet", value = "/login-servlet")
public class LoginServlet extends HttpServlet {
    private UserService userService = new UserService();

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws IOException {
        String email = request.getParameter("email");
        String password = request.getParameter("password");

        // Use UserService to authenticate user
        try {
            UserSignup user = userService.authenticateUser(email, password);

            if (user != null) {
                // Store user information in session
                request.getSession().setAttribute("loggedInUser", user);
                request.getSession().setAttribute("userName", user.getUserName());
                request.getSession().setAttribute("userId", user.getId()); // Store userId separately for easy access
                request.getSession().setAttribute("role", user.getRole()); // Store role separately for role management

                // Redirect based on role
                if ("admin".equals(user.getRole())) {
                    response.sendRedirect("admin-dashboard.jsp");
                } else {
                    response.sendRedirect("user-dashboard.jsp");
                }
            } else {
                response.sendRedirect("login.jsp?error=Invalid credentials");
            }
        } catch (RuntimeException e) {
            // Catch any runtime exceptions thrown during the login process (including database issues)
            e.printStackTrace(); // Log the error
            response.sendRedirect("signup-error.jsp");
        }
    }
}

