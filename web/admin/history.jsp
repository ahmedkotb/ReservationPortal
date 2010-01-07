<%-- 
    Document   : history
    Created on : Jan 7, 2010, 3:34:04 PM
    Author     : ahmed
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
   "http://www.w3.org/TR/html4/loose.dtd">

<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Old Reservations</title>
        <script type="text/javascript" src="calender/calendar_us.js"></script>
        <link rel="stylesheet" href="calender/calendar.css"/>

    </head>
    <body>
        <h2>Reservations Archieve</h2>
        <%if (request.getAttribute("error") != null){%>
                <div style="background-color:#fff5c3"> Error : <%=(String)request.getAttribute("error")%> </div>
        <%}%>
        <table border="0">
            <tbody>
                <tr>
                    <td>Start Date: <input type="text" name="startDate" id="sd" value="" />
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
                    <td>End Date: <input type="text" name="endDate" id="ed" value=""  />
                        <script language="JavaScript">
                            new tcal ({
                                'controlname': 'ed'
                            });
                        </script>
                    </td>
                </tr>
            </tbody>
        </table>
    </body>
</html>
