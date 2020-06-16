<%@ page import="com.kukhta.entities.Automobile" %>
<%@ page import="java.util.List" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Add User</title>
    <style>
        <%@include file="styles/style.css"%>
    </style>
</head>

<%@include file="header.html" %>

<body>

<%
    List<Automobile> freeAutomobiles = (List<Automobile>) request.getAttribute("free-automobiles");
%>

<form action="/add_user" method="POST">

    <table>
        <tr>
            <th>Field</th>
            <th>Value</th>
        </tr>
        <tr>
            <td><label for="email">Email: </label></td>
            <td><input type="email" name="email" id="email"></td>
        </tr>
        <tr>
            <td><label for="password">Password: </label></td>
            <td><input type="password" name="password" id="password"></td>
        </tr>
        <tr>
            <td><label for="user_auto">Auto: </label></td>
            <td>

                <select id="user_auto" name="user_auto">

                    <%
                        for (Automobile automobile : freeAutomobiles) {

                    %>
                    <option value="<%=automobile.getId()%>"><%=automobile.getName()%></option>
                    <%

                        }
                    %>

                </select>

            </td>
        </tr>
        <tr>
            <td><label for="user_name">Name: </label></td>
            <td><input type="text" id="user_name" name="user_name"></td>
        </tr>
        <tr>
            <td><label for="user_surname">Surname: </label></td>
            <td><input type="text" id="user_surname" name="user_surname"></td>
        </tr>
        <tr>
            <td><input type="submit" value="Add"></td>
            <td><input type="reset" value="Clear"></td>
        </tr>
    </table>

</form>

</body>
</html>
