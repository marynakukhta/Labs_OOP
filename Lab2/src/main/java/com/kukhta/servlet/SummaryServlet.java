package com.kukhta.servlet;

import com.kukhta.database.BookingDao;
import com.kukhta.database.RideDao;
import com.kukhta.database.UserDao;
import com.kukhta.entities.Booking;
import com.kukhta.entities.RideStatus;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;

@WebServlet("/summary")
public class SummaryServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            request.setAttribute("total-earnings", RideDao.getTotalEarnings());
            request.setAttribute("users", UserDao.getUsers());
            request.setAttribute("uncompleted-bookings", BookingDao.getForStatus(RideStatus.WAITING));
            request.setAttribute("completed-bookings", BookingDao.getForStatus(RideStatus.COMPLETED));
            request.setAttribute("rejected-bookings", BookingDao.getForStatus(RideStatus.REJECTED));
            request.setAttribute("top-driver-id", UserDao.getBest());
            request.setAttribute("unassigned-count", BookingDao.getUnassignedCount());
            RequestDispatcher requestDispatcher = request.getRequestDispatcher("/WEB-INF/summary.jsp");
            requestDispatcher.forward(request, response);
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }
}
