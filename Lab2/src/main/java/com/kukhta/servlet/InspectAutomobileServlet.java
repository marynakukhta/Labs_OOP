package com.kukhta.servlet;

import com.kukhta.database.AutomobileDao;
import com.kukhta.database.BookingDao;
import com.kukhta.entities.Automobile;
import com.kukhta.entities.RideStatus;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;

@WebServlet("/inspect_automobile")
public class InspectAutomobileServlet extends HttpServlet {
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int automobileId = Integer.parseInt(request.getParameter("id"));

        try {
            AutomobileDao.inspect(automobileId);
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }

        response.sendRedirect("/dashboard");
    }
}
