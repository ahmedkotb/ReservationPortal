<%-- 
    Document   : admin
    Created on : Dec 31, 2009, 1:19:47 AM
    Author     : ahmed
--%>

<%@page contentType="text/html" pageEncoding="UTF-8" import="reservationPortalSystem.*"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
   "http://www.w3.org/TR/html4/loose.dtd">

<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Admin Control Panel</title>
    </head>
    <body>
        <jsp:include page="../header.jsp"></jsp:include>
        <h1>welcome <%=(((Admin)session.getAttribute("user")).getName())%></h1>
        <a href="admin">home</a>
        <br>
        <a href="admin?req=addAgencyPage">Add a new car agency to the system</a>
        <br>
        <a href="admin?req=addCarPage">Add a new car to the system</a>

        <%
            String mode = (String)request.getAttribute("mode");
            if (mode == null){
                //do nothing or display statistics....
            }else if (mode.equals("addAgencyPage")){
         %>
            <jsp:include page="addagency.jsp"></jsp:include>
         <%}else if (mode.equals("addCarPage")){%>
            <jsp:include page="addcar.jsp"></jsp:include>
         <%}%>

    </body>
</html>
