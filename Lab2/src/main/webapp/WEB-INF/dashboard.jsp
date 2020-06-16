<%@ page import="com.kukhta.entities.User" %>
<%@ page import="java.util.List" %>
<%@ page import="com.kukhta.entities.Automobile" %>
<%@ page import="com.kukhta.entities.Ride" %>
<%@ page import="com.kukhta.entities.Booking" %>
<%@ page import="com.kukhta.database.RideDao" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Dashboard</title>
    <style>
        <%@include file="styles/style.css"%>
    </style>
</head>

<%@include file="header.html" %>

<body>

<%
    if (request.getAttribute("got_param") == null) {
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

        if (userRole == null) {
            response.sendRedirect("/home");
        } else {
            response.sendRedirect("/data?role=" + userRole + "&id=" + userId);
        }
    } else {
        String userRole = (String) request.getAttribute("user-role");
        int userId = (int) request.getAttribute("user-id");

        if (userRole.equals("ADMIN")) {
            List<User> users = (List<User>) request.getAttribute("drivers");
            List<Automobile> automobiles = (List<Automobile>) request.getAttribute("automobiles");
            List<Ride> rides = (List<Ride>) request.getAttribute("rides");
            List<Booking> bookings = (List<Booking>) request.getAttribute("bookings");

            // USERS ----------------------------------------------------------------------------------

            if (users != null) { %>
<h1>Users <a href="/add_user">add</a></h1>
<table>
    <tr>
        <th>id</th>
        <th>email</th>
        <th>password</th>
        <th>car id</th>
        <th>name</th>
        <th>surname</th>
        <th>role</th>
    </tr>

    <%

        for (User user : users) {
    %>

    <tr>
        <td><%=user.getId()%>
        </td>
        <td><%=user.getEmail()%>
        </td>
        <td><%=user.getPassword()%>
        </td>
        <td><%=user.getCarId()%>
        </td>
        <td><%=user.getName()%>
        </td>
        <td><%=user.getSurname()%>
        </td>
        <td><%=user.getRole()%>
        </td>
    </tr>

    <%
        }

    %>
</table>
<%
    }


    // Automobiles ---------------------------------------------------------------------------

    if (automobiles != null) { %>
<h1>Automobiles <a href="/add_automobile">add</a></h1>
<table>
    <tr>
        <th>id</th>
        <th>name</th>
        <th>seats</th>
        <th>last insp. date</th>
        <th>class</th>
        <th>Action</th>
    </tr>

    <%

        for (Automobile automobile : automobiles) {
    %>

    <tr>
        <td><%=automobile.getId()%>
        </td>
        <td><%=automobile.getName()%>
        </td>
        <td><%=automobile.getSeats()%>
        </td>
        <td><%=automobile.getLastInspectionDate().toString()%>
        </td>
        <td><%=automobile.getAutoClass().toString()%>
        </td>
        <td><a href="/inspect_automobile?id=<%=automobile.getId()%>">Inspect</a></td>
    </tr>

    <%
        }

    %>
</table>
<%
    }


    // Rides ---------------------------------------------------------------------------

    if (rides != null) { %>
<h1>Rides <a href="/add_ride">add</a></h1>
<table>
    <tr>
        <th>id</th>
        <th>car id</th>
        <th>booking id</th>
        <th>cost</th>
    </tr>

    <%

        for (Ride ride : rides) {
    %>

    <tr>
        <td><%=ride.getId()%>
        </td>
        <td><%=ride.getCarId()%>
        </td>
        <td><%=ride.getBookingId()%>
        </td>
        <td><%=ride.getCost()%>
        </td>
    </tr>

    <%
        }

    %>
</table>
<%
    }

    // Booking ---------------------------------------------------------------------------

    if (bookings != null) { %>
<h1>Bookings <a href="/add_booking">add</a></h1>
<table>
    <tr>
        <th>id</th>
        <th>min class</th>
        <th>depart</th>
        <th>destination</th>
        <th>min seats</th>
        <th>status</th>
    </tr>

    <%

        for (Booking booking : bookings) {
    %>

    <tr>
        <td><%=booking.getId()%>
        </td>
        <td><%=booking.getMinClass()%>
        </td>
        <td><%=booking.getDepart()%>
        </td>
        <td><%=booking.getDestination()%>
        </td>
        <td><%=booking.getMinSeats()%>
        </td>
        <td><%=booking.getStatus()%>
        </td>
    </tr>

    <%
        }

    %>
</table>
<%
    }
} else if (userRole.equals("DRIVER")) {
    List<Booking> bookings = (List<Booking>) request.getAttribute("user-bookings");
    Automobile userAutomobile = (Automobile) request.getAttribute("user-automobile");
    int earnings = (int) request.getAttribute("user-earnings");

%>

<h3>Total earnings: $<%=earnings%>
</h3>
<h5>Your auto: <%=userAutomobile.getName()%> of class <%=userAutomobile.getAutoClass()%>. Last inspection date was
    on <%=userAutomobile.getLastInspectionDate().toString()%>
</h5>

<h3>Your bookings:</h3>

<table>
    <tr>
        <th>Departure</th>
        <th>Destination</th>
        <th>Total cost</th>
        <th>Status</th>
        <th colspan="2">Change status</th>
    </tr>

    <%

        for (Booking booking : bookings) {

    %>

    <tr>
        <td><%=booking.getDepart()%>
        </td>
        <td><%=booking.getDestination()%>
        </td>
        <td><%=RideDao.getCostForBooking(booking.getId())%>
        </td>
        <td><%=booking.getStatus()%>
        </td>

        <%
            if (booking.getStatus().toString().equals("WAITING")) {
        %>

        <td><a href="/booking?id=<%=booking.getId()%>&status=COMPLETED">COMPLETE</a></td>
        <td><a href="/booking?id=<%=booking.getId()%>&status=REJECTED">REJECT</a></td>

        <%
        } else {
        %>

        <td></td>
        <td></td>

        <%
            }
        %>
    </tr>

    <%

        }

    %>

</table>
<%
        }
    }


%>

</body>
</html>
