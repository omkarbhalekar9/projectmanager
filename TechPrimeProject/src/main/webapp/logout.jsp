<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ page import="jakarta.servlet.http.HttpSession" %>
<%@ page import="java.io.IOException" %>
<%@ page import="java.util.Date" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Logout</title>
</head>
<body>
<%
    HttpSession s = request.getSession(false);
    if (s != null) {
        s.invalidate();
    }
    response.sendRedirect("index.jsp");
%>
</body>
</html>
