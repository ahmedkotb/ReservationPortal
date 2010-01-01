<%-- 
    Document   : customer
    Created on : Dec 31, 2009, 11:51:00 PM
    Author     : ahmed
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
   "http://www.w3.org/TR/html4/loose.dtd">

<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Welcome</title>
    </head>
    <body>
        <h1>Customer</h1>


        <%
            String mode = (String)request.getAttribute("mode");
            if (mode == null){
                //home page
            }else if (mode.equals("searchCar")){%>
            
            <%}%>
    </body>
</html>
