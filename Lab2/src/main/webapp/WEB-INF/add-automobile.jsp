<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Add Automobile</title>
    <style>
        <%@include file="styles/style.css"%>
    </style>
</head>

<%@include file="header.html" %>

<body>

<form action="/add_automobile" method="POST">

    <table>
        <tr>
            <th>Field</th>
            <th>Value</th>
        </tr>
        <tr>
            <td><label for="auto_name">Name: </label></td>
            <td><input type="text" id="auto_name" name="auto_name"></td>
        </tr>
        <tr>
            <td><label for="seats">Seats: </label></td>
            <td><input type="number" id="seats" name="seats"></td>
        </tr>
        <tr>
            <td><label for="last_inspection_date">Insp. Date</label></td>
            <td><input type="date" id="last_inspection_date" name="last_inspection_date"></td>
        </tr>
        <tr>
            <td><label for="auto_class">Class: </label></td>
            <td>
                <select id="auto_class" name="auto_class">
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
