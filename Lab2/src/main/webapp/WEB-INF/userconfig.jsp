<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Please wait...</title>
</head>
<body>

<%
    Cookie userId = new Cookie("toch-user-id", request.getAttribute("user-id").toString());
    Cookie userRole = new Cookie("toch-user-role", request.getAttribute("user-role").toString());

    userId.setMaxAge(60*60*24);
    userRole.setMaxAge(60*60*24);

    response.addCookie(userId);
    response.addCookie(userRole);

    request.getRequestDispatcher("/WEB-INF/dashboard.jsp").forward(request, response);
%>

</body>
</html>
