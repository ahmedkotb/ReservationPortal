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
        <script type="text/javascript" src="calender/calendar_us.js"></script>
        <link rel="stylesheet" href="calender/calendar.css"/>

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

            function pay(recordId){
                location.href="customer?req=payPage&id=" + recordId;
            }
        </script>
    </head>
    <body>
        <jsp:include page="../header.jsp"></jsp:include>
        <h1>Customer</h1>
        <a href="customer">home</a> <br>
        <a href="customer?req=searchCarPage">reserve a car</a> <br>
        <a href="customer?req=onHoldReservations">onhold reservations</a> <br>
        <a href="customer?req=historyPage">old reservations</a> <br>

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
                    <td>car model :</td><td><%=((Car) item).getCarModel()%></td>
                </tr>
                <tr>
                    <td>car type :</td><td><%=((Car) item).getCarType()%></td>
                </tr>
                <tr>
                    <td>car agency :</td><td><%=((Car) item).getMyAgency().getName()%></td>
                </tr>
                <tr>
                    <td><a href="customer?req=reserve&id=<%=item.getId()%>">Reserve</a></td><td>Reserve and pay</td>
                </tr>
                <tr><td colspan=" 2" align="center"><a href="customer?req=view&id=<%=item.getId()%>">rating and reviews</a></td></tr>
                <tr><td colspan="2"> <hr></td></tr>
            </table>


            <%
                }
            } else if (mode.equals("onHoldReservations")) {
                Collection<ReservationRecord> records = (Collection<ReservationRecord>) request.getAttribute("onHoldReservations");
                if (records.size() == 0) {
                    out.println("nothing found");
                    return;
                }
                out.print("found : " + records.size() + "<br>");
                int i = -1;
                for (ReservationRecord record : records) {
                    i++;
                    if (record instanceof CarReservation) {
            %>

            <input type="hidden" id="datefield<%=i%>"  value="<%=(record.getPurchaseDate().getTime() + (ReservationMonitor.getInstance().getON_HOLD_TIME() * 60 * 1000)) / 1000 - (new Date()).getTime() / 1000%>" />
            <table>
                <tr><td>item :</td> <td>Car</td></tr>
                <tr><td>Car Model :</td> <td><%=((Car) record.getMyReservationItem()).getCarModel()%></td></tr>
                <tr><td>purchase date :</td> <td><%=record.getPurchaseDate()%></td></tr>
                <tr><td>remaining Time till Cancelled :</td> <td id="rem<%=i%>"> </td></tr>
                <tr><td>pay :</td> <td> <input type="button" id="button<%=i%>" value="Pay" onclick='pay("<%=record.getReservationID()%>")'/> </td></tr>
            </table>
            <%}
                }
            } else if (mode.equals("payPage")) {%>
            <form action="customer" method="post">
                <input type="hidden" name="req" value="pay" />
                <jsp:include page="payment.jsp"></jsp:include>
                <input type="submit" value="Pay for item" />
            </form>
            <% } else if (mode.equals("historyPage")) {%>
            <form name ="historyForm" action="customer" method="post">
                <input type="hidden" name="req" value="history" />
                <jsp:include page="history.jsp"></jsp:include>
                <input type="submit" value="view" />
            </form>
            <%} else if (mode.equals("history")){
                Collection<ReservationRecord> records = (Collection<ReservationRecord>) request.getAttribute("result");
                if (records.size() == 0) {
                    out.println("nothing found");
                    return;
                }
                out.print("found : " + records.size() + "<br>");
                for (ReservationRecord record : records) {
             %>

             <table>
                 <tr><td>purchase Date : </td> <td><%=record.getPurchaseDate()%> </td></tr>
                 <tr><td>item : </td> <td><%if(record.getMyReservationItem() instanceof  Car ) out.print("car");%> </td></tr>
                 <tr><td>price : </td> <td><%=record.getPrice()%> </td></tr>
                 <tr><td>status : </td> <td><%=record.getStatus()%> </td></tr>
             </table>
             <%}}else if (mode.equals("view")){%>
                <jsp:include page="../itemview.jsp"></jsp:include>
             <%}%>
        </div>
    </body>
</html>
