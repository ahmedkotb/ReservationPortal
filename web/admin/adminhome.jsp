<%-- 
    Document   : adminhome
    Created on : Jan 3, 2010, 11:18:17 PM
    Author     : Ahmed Kotb
--%>

<%@page contentType="text/html" pageEncoding="UTF-8" import="reservationPortalSystem.*"%>
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
                <a href="home.jsp?req=login"> login</a>
            <% }else {%>
                <a href="logout.jsp" > logout </a>
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
            }else if (mode.equals("addAgencyPage")){
         %>
            <jsp:include page="addagency.jsp"></jsp:include>
         <%}else if (mode.equals("addCarPage")){%>
            <jsp:include page="addcar.jsp"></jsp:include>
         <%}%>
    </div>
    <div id="sidebar">
      <div id="sidebartop"></div>
      <h2>options</h2>
      <ul>
        <li class="active"><a href="home.jsp">Home</a></li>
        <li><a href="admin?req=addAgencyPage">Add Car Agency</a></li>
        <li><a href="admin?req=addCarPage">Add New Car</a></li>
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
