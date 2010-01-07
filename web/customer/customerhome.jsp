<%-- 
    Document   : customerhome
    Created on : Jan 6, 2010, 9:46:44 PM
    Author     : Ahmed Kotb
--%>

<%@page contentType="text/html" pageEncoding="UTF-8" import="reservationPortalSystem.* , java.util.* , items.* , records.*"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
    "http://www.w3.org/TR/html4/loose.dtd">

<html  xmlns="http://www.w3.org/1999/xhtml">
    <head>
        <title>Welcome At Reservation Portal</title>
        <meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
        <link href="styles/style.css" rel="stylesheet" type="text/css" />
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
        <div id="wrap">
            <div id="header">
                <h1 id="sitename"><a href="../home.jsp"><span class="blue">Km</span>Portal</a><span class="caption">Reliable Reservation Portal</span></h1>
                <div id="nav">
                    <div id="search">
                        <% if (session.getAttribute("user") == null) {%>
                        <h3> <a href="home.jsp?req=login"> login</a></h3>
                        <% } else {%>
                        <h3><a href="logout.jsp" > logout </a></h3>
                        <%}%>
                    </div>
                    <div id="topmenu">
                        <ul>
                              <li class="active"><a href="home.jsp">Home</a></li>
                              <li><a href="home.jsp?req=about">About</a></li>
                              <li><a href="home.jsp?req=contact">Contact us</a></li>
                        </ul>
                    </div>
                </div>
                <div class="clear"></div>
                <div id="breadcrumb">Home</div>
            </div>
            <div id="content">
                <div id="right">
                    <%
                        String mode = (String) request.getAttribute("mode");
                        if (mode == null) {
                            //home page
                            out.println("<h2>welcome " + ((User)session.getAttribute("user")).getName() + "</h2>");
                        } else if (mode.equals("searchCarPage")) {%>
                    <form action="customer" method="post">
                        <input type="hidden" name="req" value="searchCar" />
                        <jsp:include page="searchcar.jsp"></jsp:include>
                        <input type="submit" value="search" />
                    </form>
                    <%} else if (mode.equals("searchCar")) {
                            out.print("<h2> search Results : </h2>");
                            Collection<ReservationItem> items = (Collection<ReservationItem>) request.getAttribute("result");
                            if (items.size() == 0) {
                                out.println("nothing found");
                            }
                            out.print("<br> found : " + items.size());
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
                                <td colspan="2" align="center"><a href="customer?req=reserve&id=<%=item.getId()%>">Reserve</a></td>
                            </tr>
                            <tr><td colspan=" 2" align="center"><a href="customer?req=view&id=<%=item.getId()%>">rating and reviews</a></td></tr>
                            <tr><td colspan="2"> <hr></td></tr>
                        </table>
                        </div>

                        <%
                            }
                        } else if (mode.equals("onHoldReservations")) {
                            out.println("<h2>On Hold Reservations</h2>");
                            Collection<ReservationRecord> records = (Collection<ReservationRecord>) request.getAttribute("onHoldReservations");
                            if (records.size() == 0) {
                                out.println("nothing found");
                            }
                            out.print("<br>found : " + records.size() + "<br>");
                            int i = -1;
                            for (ReservationRecord record : records) {
                                i++;
                                if (record instanceof CarReservation) {
                        %>
                        <div>
                        <input type="hidden" id="datefield<%=i%>"  value="<%=(record.getPurchaseDate().getTime() + (ReservationMonitor.getInstance().getON_HOLD_TIME() * 60 * 1000)) / 1000 - (new Date()).getTime() / 1000%>" />
                        <table>
                            <tr><td>item :</td> <td>Car</td></tr>
                            <tr><td>Car Model :</td> <td><%=((Car) record.getMyReservationItem()).getCarModel()%></td></tr>
                            <tr><td>purchase date :</td> <td><%=record.getPurchaseDate()%></td></tr>
                            <tr><td>remaining Time till Cancelled :</td> <td id="rem<%=i%>"> </td></tr>
                            <tr><td>pay :</td> <td> <input type="button" id="button<%=i%>" value="Pay" onclick='pay("<%=record.getReservationID()%>")'/> </td></tr>
                            <tr><td colspan="2"><hr></td> </tr>
                        </table>
                        </div>
                        <%}
                            }
                        } else if (mode.equals("payPage")) {%>
                            <jsp:include page="payment.jsp"></jsp:include>
                        <% } else if (mode.equals("historyPage")) {%>
                        <form name ="historyForm" action="customer" method="post">
                            <input type="hidden" name="req" value="history" />
                            <jsp:include page="history.jsp"></jsp:include>
                            <input type="submit" value="view" />
                        </form>
                        <%} else if (mode.equals("history")) {
                            %>
                        <form name ="historyForm" action="customer" method="post">
                            <input type="hidden" name="req" value="history" />
                            <jsp:include page="history.jsp"></jsp:include>
                            <input type="submit" value="view" />
                        </form>
                            <hr/>
                    <%
                            Collection<ReservationRecord> records = (Collection<ReservationRecord>) request.getAttribute("result");
                            if (records.size() == 0) {
                                out.println("nothing found");
                            }
                            out.print("<br>found : " + records.size() + "<br>");
                            for (ReservationRecord record : records) {
                        %>

                        <table>
                            <tr><td>purchase Date : </td> <td><%=record.getPurchaseDate()%> </td></tr>
                            <tr><td>item : </td> <td><%if (record.getMyReservationItem() instanceof Car) {
                        out.print("car");
                    }%> </td></tr>
                            <tr><td>price : </td> <td><%=record.getPrice()%> </td></tr>
                            <tr><td>status : </td> <td><%=record.getStatus()%> </td></tr>
                            <tr><td colspan="2"><hr/></td></tr>
                        </table>
                        <%}
                    } else if (mode.equals("view")) {%>
                        <jsp:include page="../itemview.jsp"></jsp:include>
                        <%}%>
                    </div>
                    <div id="sidebar">
                        <div id="sidebartop"></div>
                        <h2>options</h2>
                        <ul>
                            <li class="active"><a href="home.jsp">Home</a></li>
                            <li><a href="customer?req=searchCarPage">Reserve a Car</a></li>
                            <li><a href="customer?req=onHoldReservations">My OnHold Reservations</a></li>
                            <li><a href="customer?req=historyPage">My old Reservations</a></li>
                        </ul>
                        <div id="sidebarbtm"></div>
                    </div>
                    <div class="clear"></div>
                    <div id="bottom">
                        <p>Copyright &copy; 2010 kotbcorp & A2ME</p>
                        <p><a href="home.jsp">Home</a> | <a href="home.jsp?req=about">About</a> | <a href="home.jsp?req=registerAdmin">Join !</a></p>
                    </div>
                </div>
                <div id="footer">
                    <div id="credits"> <a href="http://ramblingsoul.com">CSS Template</a> by RamblingSoul</div>
                </div>
            </div>
    </body>

</html>

