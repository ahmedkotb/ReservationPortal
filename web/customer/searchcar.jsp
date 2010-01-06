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
        <title>search for a car</title>
        <script type="text/javascript" src="calender/calendar_us.js"></script>
        <link rel="stylesheet" href="calender/calendar.css"/>
    </head>
    <body>
        <h2>search for a car</h2>
        <h4> you can leave some fields empty </h4>
            <%if (request.getAttribute("error") != null){%>
                <div style="background-color:#fff5c3"> Error : <%=(String)request.getAttribute("error")%> </div>
            <%}%>
        <table>
            <tr>
                <td>car Type :</td>
                <td>
                    <select name="carType">
                    <option></option>
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
                <td>
                    country : <input type="text" name="pickupCountry" value="" /><br>
                    city : <input type="text" name="pickupCity" value="" />   <br>
                    street : <input type="text" name="pickupStreet" value="" /> <br>
                </td>
            </tr>
            <tr>
                <td>return Location :</td>
                <td>
                    country : <input type="text" name="returnCountry" value="" /><br>
                    city : <input type="text" name="returnCity" value="" />   <br>
                    street : <input type="text" name="returnStreet" value="" /> <br>
                </td>

            </tr>
            <tr>
                <td>start date :</td>
                <td> <input id="sd" type="text" name="startDate" value ="1/1/2010" />
                     <script language="JavaScript">
                            A_TCALDEF['imgpath'] = 'calender/img/';
                            new tcal ({
                                // input name
                                'controlname': 'sd'
                            });
                     </script>
                </td>
            </tr>
            <tr>
                <td>end date :</td>
                <td> <input id ="ed" type="text" name="endDate" value ="6/1/2010" />
                     <script language="JavaScript">
                            A_TCALDEF['imgpath'] = 'calender/img/';
                            new tcal ({
                                // input name
                                'controlname': 'ed'
                            });
                     </script>
                </td>
            </tr>
        </table>

    </body>
</html>
