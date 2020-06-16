package com.kukhta.servlet;

import com.kukhta.database.AutomobileDao;
import com.kukhta.entities.AutoClass;
import com.kukhta.entities.Automobile;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.Date;
import java.sql.SQLException;

@WebServlet("/add_automobile")
public class AddAutomobileServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String name = request.getParameter("auto_name");
        int seats = Integer.parseInt(request.getParameter("seats"));
        Date date = Date.valueOf(request.getParameter("last_inspection_date"));
        AutoClass autoClass = AutoClass.valueOf(request.getParameter("auto_class"));

        try {
            AutomobileDao.insert(new Automobile(
                    0,
                    name,
                    seats,
                    date,
                    autoClass
            ));
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }

        response.sendRedirect("/dashboard");
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.getRequestDispatcher("/WEB-INF/add-automobile.jsp").forward(request, response);
    }
}
