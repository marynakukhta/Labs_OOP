package com.kukhta.servlet;

import com.kukhta.database.BookingDao;
import com.kukhta.entities.AutoClass;
import com.kukhta.entities.Booking;
import com.kukhta.entities.RideStatus;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;

@WebServlet("/add_booking")
public class AddBookingServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String depart = request.getParameter("depart");
        String destination = request.getParameter("destination");
        AutoClass minClass = AutoClass.valueOf(request.getParameter("min_class"));
        int minSeats = Integer.parseInt(request.getParameter("min_seats"));

        try {
            BookingDao.insert(new Booking(
                    0,
                    minClass,
                    depart,
                    destination,
                    minSeats,
                    RideStatus.WAITING
            ));
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }

        response.sendRedirect("/dashboard");
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.getRequestDispatcher("/WEB-INF/add-booking.jsp").forward(request, response);
    }
}
