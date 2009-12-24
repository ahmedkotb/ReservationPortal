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
        <h1>please login</h1>

        <form action="login" method="post">
            User Name :<input type="text" name="userName" value="" />
            Password : <input type="password" name="password" value="" />
            <input type="submit" value="login" />
        </form>

    </body>
</html>
