package ecommerce.controller;

import ecommerce.model.UserSignup;
import ecommerce.services.UserService;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.List;

@WebServlet(name = "viewAllUsersServlet", value = "/view-all-users")
public class ViewAllUsers extends HttpServlet {
    private UserService userService = new UserService(); // Assuming this service fetches users from the DB

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        List<UserSignup> userList = userService.getAllUsers();
        request.setAttribute("userList", userList);
        request.getRequestDispatcher("viewAllUsers.jsp").forward(request, response);
    }
}

