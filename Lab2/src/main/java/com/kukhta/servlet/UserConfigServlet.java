package com.kukhta.servlet;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/config")
public class UserConfigServlet extends HttpServlet {
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/userconfig.jsp");
        System.out.println("Got parameters " + request.getParameter("id") + " and " + request.getParameter("role"));
        request.setAttribute("user-id", request.getParameter("id"));
        request.setAttribute("user-role", request.getParameter("role"));
        dispatcher.forward(request, response);
    }
}
