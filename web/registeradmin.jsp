<%-- 
    Document   : registeradmin
    Created on : Dec 26, 2009, 2:29:02 AM
    Author     : Ahmed Kotb
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
        <h3>Register a New Admin</h3>

        <form action="register" method="POST">
            <jsp:include page="registeruser.jsp"/>
            <input type="hidden" name="req" value="registerAdmin" />
            <h3>Other Info</h3>
            Qualifications and Previous jobs: <br>
            <textarea name="qualifications" rows="4" cols="40"></textarea><br>
            <input type="submit" value="register" />
        </form>
    </body>
</html>
