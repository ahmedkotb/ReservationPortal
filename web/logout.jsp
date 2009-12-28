<%-- 
    Document   : logout
    Created on : Dec 25, 2009, 2:42:28 AM
    Author     : ahmed
--%>

<%@page contentType="text/html" pageEncoding="UTF-8" import="reservationPortalSystem.ReservationPortalSystem , reservationPortalSystem.User"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
   "http://www.w3.org/TR/html4/loose.dtd">

<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        <%
            if ((User)session.getAttribute("user") != null)
                ReservationPortalSystem.getInstance().logout((User)session.getAttribute("user"));
            session.invalidate();
        %>
        <h1>hope to see u soon...</h1>
    </body>
</html>
