package ecommerce.controller;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;

@WebServlet(name = "userDashboardServlet", value = "/user-dashboard-servlet")
public class UserDashboardServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        // Fetch userId from session
        HttpSession session = request.getSession();
        Integer userId = (Integer) session.getAttribute("userId");

        if (userId == null) {
            // No user logged in, redirect to login
            response.sendRedirect("login.jsp");
            return;
        }

        // User is logged in, set attributes for JSP
        String username = (String) session.getAttribute("username");
        String role = (String) session.getAttribute("role");

        request.setAttribute("username", username);
        request.setAttribute("role", role);

        // Forward to the user dashboard JSP
        request.getRequestDispatcher("user-dashboard.jsp").forward(request, response);
    }

}
