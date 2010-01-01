<%-- 
    Document   : header
    Created on : Dec 25, 2009, 2:12:26 AM
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
        <table>
            <tr>


                <td>
                    <img src="images/logo.png" style="float:left" />
                </td>
                <td width="90%">
                    <h1>km-reservation-portal</h1>
                </td>
                <td>

                    <%
                        if (session.getAttribute("user") == null) {
                            out.println("<a href='login.jsp'>login</a>");
                        } else {
                            out.println("<a href='logout.jsp'>logout</a>");
                        }
                    %>
                </td>
            </tr>
        </table>
        <hr>

    </body>
</html>
