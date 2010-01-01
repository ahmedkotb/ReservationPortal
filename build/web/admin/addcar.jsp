<%-- 
    Document   : addcar
    Created on : Dec 31, 2009, 1:32:48 AM
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
        <title>Add a new Car</title>
    </head>
    <body>
        <h1>add a car</h1>
        <form action="admin" method="POST">
            <input type="hidden" name="req" value="addCar" />
            <table>
                <tr>
                    <td>Car Agency :</td>
                    <td>
                        <select name="carAgency">
                            <option>available Car Agencies</option>
                            <%
                                IAdminReservationItemManager manager = ReservationPortalSystem.getInstance().getItemManager();
                                Collection<CarAgency> agencies = manager.getAllCarAgencies();
                                for (CarAgency agency : agencies) {
                                    out.println("<option>" + agency.getName() + "</option>");
                                }
                            %>
                        </select>
                    </td>
                </tr>
                <tr>
                    <td>Car Type:</td>
                    <td>
                        <select name="carType">
                            <option>available Car Types</option>
                            <%
                                for (CarType ct : CarType.values()) {
                                    out.println("<option>" + ct + "</option>");
                                }
                            %>
                        </select>
                    </td>
                </tr>
                <tr>
                    <td>Car Model :</td>
                    <td><input type="text" name="carModel" value="" /></td>
                </tr>
                <tr>
                    <td>Quantity :</td>
                    <td><input type="text" name="quantity" value="1" /></td>
                </tr>
                <tr>
                    <td>Rent price per day :</td>
                    <td><input type="text" name="price" value="" /></td>
                </tr>
            </table>
            <input type="submit" value="AddCar" />
        </form>
    </body>
</html>
