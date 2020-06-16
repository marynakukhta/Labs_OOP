package com.kukhta.servlet;

import com.kukhta.database.AutomobileDao;
import com.kukhta.database.BookingDao;
import com.kukhta.database.RideDao;
import com.kukhta.database.UserDao;
import com.kukhta.entities.User;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;

@WebServlet("/data")
public class DataServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String role = request.getParameter("role");
        int id = Integer.parseInt(request.getParameter("id"));

        try {
            if (role.equals("ADMIN")) {
                request.setAttribute("got_param", "true");
                request.setAttribute("user-role", "ADMIN");
                request.setAttribute("user-id", id);
                request.setAttribute("drivers", UserDao.getUsers());
                request.setAttribute("automobiles", AutomobileDao.getAutomobiles());
                request.setAttribute("bookings", BookingDao.getBookings());
                request.setAttribute("rides", RideDao.getRides());

                RequestDispatcher requestDispatcher = request.getRequestDispatcher("/WEB-INF/dashboard.jsp");
                requestDispatcher.forward(request, response);
            } else {
                User user = UserDao.getById(id);
                request.setAttribute("got_param", "true");
                request.setAttribute("user-role", "DRIVER");
                request.setAttribute("user-id", id);
                request.setAttribute("user-automobile", AutomobileDao.getById(user.getCarId()));
                request.setAttribute("user-bookings", BookingDao.getBookingsForUser(user.getId()));
                request.setAttribute("user-earnings", RideDao.getEarningsForUser(user.getId()));

                RequestDispatcher requestDispatcher = request.getRequestDispatcher("/WEB-INF/dashboard.jsp");
                requestDispatcher.forward(request, response);
            }
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }
}
