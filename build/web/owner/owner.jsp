<%-- 
    Document   : owner
    Created on : Dec 28, 2009, 7:32:00 PM
    Author     : ahmed
--%>

<%@page contentType="text/html" pageEncoding="UTF-8" import="java.util.* , reservationPortalSystem.Admin"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
    "http://www.w3.org/TR/html4/loose.dtd">

<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Portal Owner Page</title>
    </head>
    <body>
        <jsp:include page="../header.jsp"></jsp:include>
        <a href="owner?req=allAdmins">Display All Admins</a> <br>
        <a href="owner?req=newAdmins">Display new Registered Admins</a>

        <div>
            <%
            String mode = (String) request.getAttribute("mode");
            if (mode == null) {
                out.println("welcome");
            } else if (mode.equals("allAdmins") || mode.equals("newAdmins")) {
                out.println(mode + "<br>");
                Collection<Admin> admins = (Collection<Admin>) request.getAttribute("result");

                if (admins == null) {
                    out.println("nothing found");
                    return;
                }

                for (Admin admin : admins) {
            %>
            <div style="background-color:orange">
                <table>
                    <tr>
                        <td> Name : </td> <td> <%=admin.getName()%> </td>
                    </tr>
                    <tr>
                        <td> UserName : </td> <td> <%=admin.getUserName()%> </td>
                    </tr>
                    <tr>
                        <td> Address : </td> <td> <%=admin.getAddress()%> </td>
                    </tr>
                    <tr>
                        <td> Email : </td> <td> <%=admin.getEmail()%> </td>
                    </tr>
                    <tr>
                        <td> Phone Number : </td> <td> <%=admin.getPhoneNumber()%> </td>
                    </tr>

                    <tr>
                        <td> Qualifications : </td> <td> <%=admin.getQualifications()%> </td>
                    </tr>
                    <tr>
                        <td> lastLoginDate : </td> <td> <%=admin.getLastLoginDate()%> </td>
                    </tr>
                    <tr>
                        <% if (admin.isActivated()) {%>
                        <td><a href="owner?req=deactivate&userName=<%=admin.getUserName()%>">deactivate</a></td>
                        <% } else {%>
                        <td><a href="owner?req=activate&userName=<%=admin.getUserName()%>">activate</a></td>
                        <% }%>
                    </tr>

                </table>

            </div>
            <%  }
            }
            %>

        </div>

    </body>
</html>
