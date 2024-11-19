package ecommerce.controller;

import ecommerce.dao.UserDao;
import ecommerce.model.UserSignup;
import ecommerce.config.ConnectionUtils;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

@WebServlet(name = "user", value = "/signup")
public class UserServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // Forward to signup.jsp form page
        request.getRequestDispatcher("signup.jsp").forward(request, response);
    }


    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String firstName = request.getParameter("firstName");
        String lastName = request.getParameter("lastName");
        String userName = request.getParameter("userName");
        String email = request.getParameter("email");
        String password = request.getParameter("password");
        String role = request.getParameter("role");

        UserSignup user = new UserSignup(firstName, lastName, userName, email, password, role);
        UserDao userDao = new UserDao(ConnectionUtils.getConnection());
        userDao.addUser(user);

        // Redirect to a success page or display a message
        response.sendRedirect("signup-success.jsp");
    }
}
