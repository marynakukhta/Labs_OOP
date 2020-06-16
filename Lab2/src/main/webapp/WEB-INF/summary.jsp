<%@ page import="com.kukhta.entities.User" %>
<%@ page import="java.util.List" %>
<%@ page import="com.kukhta.database.RideDao" %>
<%@ page import="com.kukhta.database.AutomobileDao" %>
<%@ page import="com.kukhta.database.BookingDao" %>
<%@ page import="com.kukhta.entities.RideStatus" %>
<%@ page import="com.kukhta.database.UserDao" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Summary</title>
    <style>
        <%@include file="styles/style.css"%>
    </style>
</head>

<%@include file="header.html" %>

<body>

<%
    String userRole = null;
    int userId = 0;

    Cookie[] cookies = request.getCookies();

    if (cookies != null) {
        for (int i = 0; i < cookies.length; i++) {
            System.out.println(cookies[i].getName() + " - " + cookies[i].getValue());
            if (cookies[i].getName().equals("toch-user-role"))
                userRole = cookies[i].getValue();
            if (cookies[i].getName().equals("toch-user-id"))
                userId = Integer.parseInt(cookies[i].getValue());
        }
    }

    if (userRole == null || !userRole.equals("ADMIN")) {
        response.sendRedirect("/error?message=\"Log In as Administrator to see summary of company\"");
    } else {
        int totalEarnings = (int) request.getAttribute("total-earnings");
        int uncompletedBookings = (int) request.getAttribute("uncompleted-bookings");
        int completedBookings = (int) request.getAttribute("completed-bookings");
        int rejectedBookings = (int) request.getAttribute("rejected-bookings");
        int unassignedBookings = (int) request.getAttribute("unassigned-count");
        List<User> users = (List<User>) request.getAttribute("users");


%>

<h1>Total earnings: $<%=totalEarnings%>
</h1>

<p>Completed Bookings: <%=completedBookings%>
</p>
<p>Waiting Bookings: <%=uncompletedBookings%>
</p>
<p>Unassigned Bookings: <%=unassignedBookings%>
</p>
<p>Rejected Bookings: <%=rejectedBookings%>
</p>

<h3>Driver Stats: </h3>
<table>
    <tr>
        <th>Name</th>
        <th>Surname</th>
        <th>Auto</th>
        <th>Earnings</th>
        <th>Completed</th>
        <th>Waiting</th>
        <th>Rejected</th>
    </tr>

    <%
        for (User user : users) {

    %>

    <tr>
        <td><%=user.getName()%>
        </td>
        <td><%=user.getSurname()%>
        </td>
        <td><%=AutomobileDao.getById(user.getCarId()).getName()%>
        </td>
        <td>$<%=RideDao.getEarningsForUser(user.getId())%>
        </td>
        <td><%=BookingDao.getBookingsByStatusForUser(user.getId(), RideStatus.COMPLETED)%>
        </td>
        <td><%=BookingDao.getBookingsByStatusForUser(user.getId(), RideStatus.WAITING)%>
        </td>
        <td><%=BookingDao.getBookingsByStatusForUser(user.getId(), RideStatus.REJECTED)%>
        </td>
    </tr>

    <%

        }

        int topDriverId = (int) request.getAttribute("top-driver-id");
        User user = UserDao.getById(topDriverId);
    %>

</table>

<h1>Top Driver: <%=user.getName()%> <%=user.getSurname()%>.
    Completed <%=BookingDao.getBookingsByStatusForUser(user.getId(), RideStatus.COMPLETED)%> rides and earned
    $<%=RideDao.getEarningsForUser(user.getId())%>
</h1>

<%

    }


%>

</body>
</html>
