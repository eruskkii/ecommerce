package ecommerce.controller;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;

@WebServlet(name = "logoutServlet", value = "/logout-servlet")
public class LogoutServlet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        // Invalidate the current session to log out the user
        HttpSession session = request.getSession(false); // Get session, do not create a new one
        if (session != null) {
            session.invalidate(); // Invalidate session if it exists
        }

        // Redirect to the homepage or login page after logout
        response.sendRedirect("index.jsp");
    }
}
