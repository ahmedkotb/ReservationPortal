<%-- 
    Document   : registercustomer
    Created on : Dec 28, 2009, 1:59:12 PM
    Author     : ahmed
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
   "http://www.w3.org/TR/html4/loose.dtd">

<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Register Customer</title>
    </head>
    <body>
        <jsp:include page="header.jsp"/>
        <h3>Register a New Customer</h3>

        <form action="register" method="POST">
            <jsp:include page="registeruser.jsp"/>
            <input type="hidden" name="req" value="registerCustomer" />
            <input type="submit" value="register" />
        </form>
    </body>
</html>
