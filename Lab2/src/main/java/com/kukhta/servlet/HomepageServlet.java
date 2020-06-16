package com.kukhta.servlet;

import com.kukhta.database.UserDao;
import com.kukhta.entities.User;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;

@WebServlet({ "/", "/home"})
public class HomepageServlet extends HttpServlet {
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.getRequestDispatcher("/WEB-INF/homepage.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String email = request.getParameter("email");
        String password = request.getParameter("password");

        System.out.println("---------------------------------------------");
        System.out.println("email is " + email);
        System.out.println("password is " + password);
        System.out.println("---------------------------------------------");

        User user = null;

        try {
            user = UserDao.getByLogin(email, password);
            if (user == null) {
                response.sendRedirect("/error?message=\"Invalid email or password!\"");
            } else {
                System.out.println("Prepared to send user data: " + user.getId() + " and " + user.getRole());
                response.sendRedirect("/config?role=" + user.getRole() + "&id=" + user.getId());
                System.out.println(user.getId() + " : " + user.getRole());
            }
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }
}
