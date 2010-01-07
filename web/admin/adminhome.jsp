<%-- 
    Document   : adminhome
    Created on : Jan 3, 2010, 11:18:17 PM
    Author     : Ahmed Kotb
--%>

<%@page contentType="text/html" pageEncoding="UTF-8" import="reservationPortalSystem.* ,records.ReservationRecord,java.util.*"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
   "http://www.w3.org/TR/html4/loose.dtd">

<html  xmlns="http://www.w3.org/1999/xhtml">
    <head>
<title>ReservationPortal</title>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<link href="styles/style.css" rel="stylesheet" type="text/css" />
    </head>
<body>
<div id="wrap">
  <div id="header">
    <h1 id="sitename"><a href="../home.jsp"><span class="blue">Km</span>Portal</a><span class="caption">Reliable Reservation Portal</span></h1>
    <div id="nav">
        <div id="search">
            <% if (session.getAttribute("user")==null){ %>
                <h3> <a href="home.jsp?req=login"> login</a></h3>
            <% }else {%>
            <h3><a href="logout.jsp" > logout </a></h3>
            <%}%>
        </div>
      <div id="topmenu">
        <ul>
          <li class="active"><a href="home.jsp">Home</a></li>
          <li><a href="about.jsp">About</a></li>
          <li><a href="contact.jsp">Contact us</a></li>
        </ul>
      </div>
    </div>
    <div class="clear"></div>
    <div id="breadcrumb">Home</div>
  </div>
  <div id="content">
    <div id="right">
         <%
            String mode = (String)request.getAttribute("mode");
            if (mode == null){
                //do nothing or display statistics....
                out.println("<h2>welcome " + ((User)session.getAttribute("user")).getName() + "</h2>");
                
            }else if (mode.equals("addAgencyPage")){
         %>
            <jsp:include page="addagency.jsp"></jsp:include>
         <%}else if (mode.equals("addCarPage")){%>
            <jsp:include page="addcar.jsp"></jsp:include>
            <%}else if (mode.equals("unClearedRecords")){
                Collection <ReservationRecord> records = (Collection <ReservationRecord>) request.getAttribute("result");
                for (ReservationRecord record : records){
            %>
            <form action="admin" method="post">
                <input type="hidden" name="req" value="clear" />
                <input type="hidden" name="id" value="<%=record.getReservationID()%>" />
                <table>
                    <tr><td>id :</td><td><%=record.getReservationID()%></td></tr>
                    <tr><td>purchase date :</td><td><%=record.getPurchaseDate()%></td></tr>
                    <tr><td>item id :</td><td><%=record.getMyReservationItem().getId()%></td></tr>
                    <tr><td>customer :</td><td><%=record.getReserver().getUserName()%></td></tr>
                    <tr><td>price :</td><td><%=record.getPrice()%></td></tr>
                    <tr><td colspan="2" align="center"> <input type="submit" value="clear Record" /></td></tr>
                    <tr><td colspan="2"> <hr></td></tr>
                </table>
            </form>
           <%}}else if (mode.equals("overdueRecords")){
               Collection <ReservationRecord> records = (Collection <ReservationRecord>) request.getAttribute("result");
                for (ReservationRecord record : records){
           %>
                <table>
                    <tr><td>id :</td><td><%=record.getReservationID()%></td></tr>
                    <tr><td>purchase date :</td><td><%=record.getPurchaseDate()%></td></tr>
                    <tr><td>item id :</td><td><%=record.getMyReservationItem().getId()%></td></tr>
                    <tr><td>customer :</td><td><%=record.getReserver().getUserName()%></td></tr>
                    <tr><td>price :</td><td><%=record.getPrice()%></td></tr>
                    <tr><td colspan="2"> <hr></td></tr>
                </table>
           <%}  } %>


    </div>
    <div id="sidebar">
      <div id="sidebartop"></div>
      <h2>options</h2>
      <ul>
        <li class="active"><a href="home.jsp">Home</a></li>
        <li><a href="admin?req=addAgencyPage">Add Car Agency</a></li>
        <li><a href="admin?req=addCarPage">Add New Car</a></li>
        <li><a href="admin?req=unClearedRecords">Uncleared Records</a></li>
        <li><a href="admin?req=overdueRecords">overdue Records</a></li>
      </ul>
      <div id="sidebarbtm"></div>
    </div>
    <div class="clear"></div>
    <div id="bottom">
        <p>Copyright &copy; 2010 kotbcorp & A2ME</p>
      <p><a href="home.jsp">Home</a> | <a href="about.jsp">About</a> | <a href="home.jsp?req=registerAdmin">Join !</a></p>
    </div>
  </div>
  <div id="footer">
    <div id="credits"> <a href="http://ramblingsoul.com">CSS Template</a> by RamblingSoul</div>
  </div>
</div>
</body>

</html>
