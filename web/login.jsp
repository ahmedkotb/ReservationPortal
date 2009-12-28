<%-- 
    Document   : login
    Created on : Dec 24, 2009, 9:52:18 PM
    Author     : ahmed
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
   "http://www.w3.org/TR/html4/loose.dtd">

<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        <jsp:include page="header.jsp"/>
        <h2>Reservation Portal</h2>

        <form action="login" method="post">
            User Name :<input type="text" name="userName" value="" /> <br>
            Password : <input type="password" name="password" value="" /> <br>
            <input type="submit" value="login"/>
        </form>
        <a href="register?req=customer">register User</a> <br>
        <a href="register?req=admin">register Admin</a>
    </body>
</html>
