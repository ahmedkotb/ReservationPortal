<%-- 
    Document   : registeruser
    Created on : Dec 26, 2009, 2:58:51 AM
    Author     : ahmed
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
   "http://www.w3.org/TR/html4/loose.dtd">

<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
        <link href="styles/style.css" rel="stylesheet" type="text/css" />
    </head>
    <body>
        <h3>main info</h3>
            <label for="name">your full name :</label>
            <input class="textfield" id="name" type="text" name="name" value="" />
            <label for="uname">user name :</label>
            <input class="textfield" name="userName" id="uname" type="text" />

            <label for="password">password:</label>
            <input class="textfield" id="password" name="password" type="password" />

            <label for="cpassword">confirm password:</label>
            <input class="textfield" id="cpassword" type="password" />

            <label for="address">address :</label>
            <input class="textfield" id="address" name="address" type="text" />

            <label for="email">email:</label>
            <input class="textfield" id="email" name="email" type="text" />

            <label for="pnum">phone number:</label>
            <input class="textfield" id="pnum" name="phone" type="text" />

    </body>
</html>
