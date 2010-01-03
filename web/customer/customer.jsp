<%-- 
    Document   : customer
    Created on : Dec 31, 2009, 11:51:00 PM
    Author     : ahmed
--%>

<%@page contentType="text/html" pageEncoding="UTF-8" import="reservationPortalSystem.* , java.util.* , items.*"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
    "http://www.w3.org/TR/html4/loose.dtd">

<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Welcome</title>
    </head>
    <body>
        <jsp:include page="../header.jsp"></jsp:include>
        <h1>Customer</h1>
        <a href="customer">home</a> <br>
        <a href="customer?req=searchCarPage">reserve a car</a>

        <%
            String mode = (String) request.getAttribute("mode");
            if (mode == null) {
                //home page
            } else if (mode.equals("searchCarPage")) {%>
        <form action="customer" method="post">
            <input type="hidden" name="req" value="searchCar" />
            <jsp:include page="searchcar.jsp"></jsp:include>
            <input type="submit" value="search" />
        </form>
        <%} else if (mode.equals("searchCar")) {
                out.print("search Results : <br>");
                Collection<ReservationItem> items = (Collection<ReservationItem>) request.getAttribute("result");
                if (items.size() == 0) {
                    out.println("nothing found");
                    return;
                }
                out.print("found : " + items.size());
                for (ReservationItem item : items) {
        %>
        <br>
        <div>
            <table>
                    <tr>
                        <td>car model :</td>
                        <td><%=((Car) item).getCarModel()%></td>
                    </tr>
                    <tr>
                        <td>car type :</td>
                        <td><%=((Car) item).getCarType()%></td>
                    </tr>
                    <tr>
                        <td>car agency :</td>
                        <td><%=((Car) item).getMyAgency().getName()%></td>
                    </tr>
                    <tr>
                        <td>
                            <a href="customer?req=reserve&id=<%=item.getId()%>">Reserve</a>
                        </td>
                        <td>Reserve and pay</td>
                    </tr>
            </table>


            <%
                   }
               }
            %>
        </div>
    </body>
</html>
