<%-- 
    Document   : searchcar
    Created on : Jan 1, 2010, 2:57:49 AM
    Author     : ahmed
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"
        import="reservationPortalSystem.ReservationPortalSystem ,reservationPortalSystem.IAdminReservationItemManager
        , items.CarAgency , java.util.* , items.CarType"

        %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
    "http://www.w3.org/TR/html4/loose.dtd">

<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        <h1>search for a car</h1>
        <h4> you can leave some fields empty </h4>

        <table>
            <tr>
                <td>car Type :</td>
                <td>
                    <select name="carType">
                    <option>available car models</option>
                    <% 
                        for (CarType ct : CarType.values())
                            out.println("<option>" + ct + "</option>");
                    %>
                    </select>
                </td>
            </tr>
            <tr>
                <td>car Model</td>
                <td><input type="text" name="carModel" value="" /></td>
            </tr>
            <tr>
                <td>pickup Location :</td>
                <td><input type="text" name="pickupLocation" value="" /></td>
            </tr>
            <tr>
                <td>return Location :</td>
                <td><input type="text" name="pickupLocation" value="" /></td>
            </tr>
            <tr>
                <td>start date :</td>
                <td> <input type="text" name="startDate" value ="1/1/2009" />  </td>
            </tr>
            <tr>
                <td>end date :</td>
                <td> <input type="text" name="endDate" value ="6/1/2009" /> </td>
            </tr>
        </table>

    </body>
</html>
