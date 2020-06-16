package com.kukhta.servlet;

import com.kukhta.database.AutomobileDao;
import com.kukhta.database.BookingDao;
import com.kukhta.database.RideDao;
import com.kukhta.entities.*;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

@WebServlet("/add_ride")
public class AddRideServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int automobileId = Integer.parseInt(request.getParameter("automobile"));
        int bookingId = Integer.parseInt(request.getParameter("booking"));
        int cost = Integer.parseInt(request.getParameter("cost"));

        try {
            RideDao.insert(new Ride(
                    0,
                    automobileId,
                    bookingId,
                    cost
            ));
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }

        response.sendRedirect("/dashboard");
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            List<Booking> bookings = BookingDao.getWaiting();
            List<Automobile> automobiles = AutomobileDao.getAutomobiles();

            RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/add-ride.jsp");
            request.setAttribute("automobiles", automobiles);
            request.setAttribute("bookings", bookings);
            dispatcher.forward(request, response);

        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }
}
