<%-- 
    Document   : customer
    Created on : Dec 31, 2009, 11:51:00 PM
    Author     : ahmed
--%>

<%@page contentType="text/html" pageEncoding="UTF-8" import="reservationPortalSystem.* , java.util.* , items.* , records.*"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
    "http://www.w3.org/TR/html4/loose.dtd">

<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Welcome</title>
        <script type="text/javascript">
            var timer = false;
            setInterval("updateFields()",1000);
            function updateFields(){           
                var datefield;
                var remfield;
                var i=0;
                var time = 0;
                while (true){
                    datefield = document.getElementById("datefield" + i);
                    if (datefield ==null) break;
                    remfield = document.getElementById("rem" + i);
                    time = parseInt(datefield.value, 10);                    
                    if (isNaN(time)) {alert(time);i++;continue;}
                    if (time <= 0) {
                        remfield.innerHTML = "cancelled";
                        document.getElementById("button" + i).disabled = true;
                        i++;
                        continue;
                    }                    
                    time-=1;
                    var hours = Math.floor(time/60/60/1000);
                    var mins = Math.floor(time/60)%60 ;
                    var secs = time%60;
                    datefield.value = time;
                    remfield.innerHTML = (hours > 9 ? hours: "0" + hours) + ":" + (mins > 9 ? mins: "0" + mins) + ":" + (secs > 9 ? secs: "0" + secs);
                    i++;
                }
            }

            function pay(){
                alert("thank u");
            }
        </script>
    </head>
    <body>
        <jsp:include page="../header.jsp"></jsp:include>
        <h1>Customer</h1>
        <a href="customer">home</a> <br>
        <a href="customer?req=searchCarPage">reserve a car</a> <br>
        <a href="customer?req=onHoldReservations">onhold reservations</a> <br>

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
                out.print("<br> search Results : <br>");
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
               }else if (mode.equals("onHoldReservations")){
                   Collection<ReservationRecord> records = (Collection<ReservationRecord>) request.getAttribute("onHoldReservations");
                   if (records == null){
                       out.println("AAAA");return;
                       }
                    if (records.size() == 0) {
                        out.println("nothing found");
                        return;
                    }
                out.print("found : " + records.size() + "<br>");
                int i=-1;
                for (ReservationRecord record : records) {
                    i++;
                    if (record instanceof CarReservation){
            %>

            <input type="hidden" id="datefield<%=i%>"  value="<%=(record.getPurchaseDate().getTime()+ (ReservationMonitor.getInstance().getON_HOLD_TIME()*60*1000))/1000 - (new Date()).getTime()/1000 %>" />
            <table>
                <tr><td>item :</td> <td>Car</td></tr>
                <tr><td>Car Model :</td> <td><%=((Car)record.getMyReservationItem()).getCarModel()%></td></tr>
                <tr><td>purchase date :</td> <td><%=record.getPurchaseDate()%></td></tr>
                <tr><td>remaining Time till Cancelled :</td> <td id="rem<%=i%>"> </td></tr>
                <tr><td>pay :</td> <td> <input type="button" id="button<%=i%>" value="Pay" onclick="pay()"/> </td></tr>
            </table>
            <%
               }}}
            %>
        </div>
    </body>
</html>
