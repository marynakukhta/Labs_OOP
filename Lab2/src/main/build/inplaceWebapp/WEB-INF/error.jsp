<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Something went wrong!</title>
    <style>
        <%@include file="styles/style.css"%>
    </style>
</head>

<%@include file="header.html" %>

<body>
    <h3><%=(String)request.getAttribute("message")%></h3>
</body>
</html>
