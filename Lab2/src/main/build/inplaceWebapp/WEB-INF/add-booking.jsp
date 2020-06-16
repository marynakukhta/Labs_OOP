<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Add Booking</title>
    <style>
        <%@include file="styles/style.css"%>
    </style>
</head>

<%@include file="header.html" %>

<body>

<form action="/add_booking" method="POST">

    <table>
        <tr>
            <th>Field</th>
            <th>Value</th>
        </tr>
        <tr>
            <td><label for="depart">Depart: </label></td>
            <td><input type="text" id="depart" name="depart"></td>
        </tr>
        <tr>
            <td><label for="destination">Destination: </label></td>
            <td><input type="text" id="destination" name="destination"></td>
        </tr>
        <tr>
            <td><label for="min_seats">Min Seats: </label></td>
            <td><input type="number" id="min_seats" name="min_seats"></td>
        </tr>
        <tr>
            <td><label for="min_class">Min Class: </label></td>
            <td>

                <select id="min_class" name="min_class">
                    <option value="A">A</option>
                    <option value="B">B</option>
                    <option value="C">C</option>
                    <option value="D" selected>D</option>
                </select>

            </td>
        </tr>
        <tr>
            <td><input type="submit" value="Add"></td>
            <td><input type="reset" value="Clear"></td>
        </tr>
    </table>

</form>

</body>
</html>
