<%@ page import="com.kukhta.entities.Automobile" %>
<%@ page import="java.util.List" %>
<%@ page import="com.kukhta.entities.Booking" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Add Ride</title>
    <style>
        <%@include file="styles/style.css"%>
    </style>
</head>

<%@include file="header.html" %>

<body>

<%
    List<Automobile> automobiles = (List<Automobile>) request.getAttribute("automobiles");
    List<Booking> bookings = (List<Booking>) request.getAttribute("bookings");
%>

<form action="/add_ride" method="POST">

    <table>
        <tr>
            <th>Field</th>
            <th>Value</th>
        </tr>
        <tr>
            <td><label for="booking">Booking: </label></td>
            <td>

                <select name="booking" id="booking">

                    <%
                        for (Booking booking : bookings) {

                    %>
                    <option value="<%=booking.getId()%>">Min. class <%=booking.getMinClass()%>; Min.
                        seats <%=booking.getMinSeats()%>; <%=booking.getDepart()%> - <%=booking.getDestination()%>
                    </option>
                    <%

                        }
                    %>

                </select>

            </td>
        </tr>
        <tr>
            <td><label for="automobile">Automobile: </label></td>
            <td>

                <select name="automobile" id="automobile">

                    <%
                        for (Automobile automobile : automobiles) {

                    %>
                    <option value="<%=automobile.getId()%>">Class <%=automobile.getAutoClass()%>;
                        Seats <%=automobile.getSeats()%>; <%=automobile.getName()%>
                    </option>
                    <%

                        }
                    %>

                </select>

            </td>
        </tr>
        <tr>
            <td><label for="cost">Cost: </label></td>
            <td><input type="number" name="cost" id="cost"></td>
        </tr>
        <tr>
            <td><input type="submit" value="Add"></td>
            <td><input type="reset" value="Clear"></td>
        </tr>
    </table>

</form>

</body>
</html>
