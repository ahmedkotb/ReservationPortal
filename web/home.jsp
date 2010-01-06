<%-- 
    Document   : home
    Created on : Jan 3, 2010, 1:32:49 AM
    Author     : Ahmed Kotb
--%>

<%@page contentType="text/html" pageEncoding="UTF-8" import="reservationPortalSystem.* , items.* , java.util.*"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Strict//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-strict.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<title>ReservationPortal</title>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<link href="styles/style.css" rel="stylesheet" type="text/css" />
</head>
<body>
<div id="wrap">
  <div id="header">
    <h1 id="sitename"><a href="home.jsp"><span class="blue">Km</span>Portal</a><span class="caption">Reliable Reservation Portal</span></h1>
    <div id="nav">
        <div id="search">
            <% if (session.getAttribute("user")==null){ %>
                <h3><a href="home.jsp?req=login"> login</a></h3>
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
            String req = (String)request.getParameter("req");
            if (req == null) { %>

      <div id="featured">
        <div> ClearPixels is a free CSS Template released under the Creative Commons Attribution 2.5 License by RamblingSoul. You can download other quality css templates from my profile or website.</div>
        <span class="ftd_btm"><a href="home.jsp">Read More</a></span>
      </div>
      <div class="post">
        <h2>post 1<span class="description">a new post</span></h2>
        this is post 1
      </div>
      <%}else if (req.equals("login")){
         if (session.getAttribute("user") !=null){
             User user =(User) session.getAttribute("user");
             if (user instanceof Owner)
                 getServletContext().getRequestDispatcher("/owner/ownerhome.jsp").forward(request, response);
             else if (user instanceof Admin)
                 getServletContext().getRequestDispatcher("/admin/adminhome.jsp").forward(request, response);
             else
                 getServletContext().getRequestDispatcher("/customer/customerhome.jsp").forward(request, response);
         }else{
    %>
        <form action="login" method="post">
          <div>
            <label for="name">user name:</label>
            <input class="textfield" name="userName" id="name" type="text" />
            <label for="password">password:</label>
            <input class="textfield" id="password" name="password" type="password" />
            <label for="Submit"><span class="hide">Submit</span></label>
            <!--<input name="Submit" type="button" id="Submit" class="button" value="login" />-->
            <input name="Submit" type="submit" id="Submit"  value="login" />
          </div>
        </form>
        <%}}else if (req.equals("mostPopular")){
            ReservationItemManager manager = ReservationPortalSystem.getInstance().getItemManager();
            Collection<ReservationItem> items = manager.getTop10(Top_10.RESERVED_NUMBER);
            int i=0;
            out.print("<h2> Most Popular Items </h2>");
            for (ReservationItem item : items){
                Car car = (Car)item;
                i++;
        %>
        <div>
            <h3>#<%=i%></h3>
            <table>
                <tr><td>item id :</td><td><%=car.getId()%></td></tr>
                <tr><td>car Type :</td><td><%=car.getCarType()%></td></tr>
                <tr><td>car Model :</td><td><%=car.getCarModel()%></td></tr>
                <tr><td>rent price per day :</td><td><%=car.getRentPrice()%></td></tr>
                <tr><td>number of times reserved :</td><td><%=item.getReservedCount()%></td></tr>
                <tr><td>currently available :</td><td><%=car.getAvailableNumber()%> of <%=item.getQuantity()%> cars</td></tr>
                <tr><td>rating :</td><td><%=item.getRating()%></td></tr>
                <tr><td colspan="2"> <hr/></td></tr>
            </table>
        </div>
        
        
        <%}}else if (req.equals("topRated")){
            ReservationItemManager manager = ReservationPortalSystem.getInstance().getItemManager();
            Collection<ReservationItem> items = manager.getTop10(Top_10.RATING);
            int i=0;
            out.print("<h2> Top Rated Items </h2>");
            for (ReservationItem item : items){
                Car car = (Car)item;
                i++;
        %>

        <div>
            <h3>#<%=i%></h3>
            <table>
                <tr><td>item id :</td><td><%=car.getId()%></td></tr>
                <tr><td>car Type :</td><td><%=car.getCarType()%></td></tr>
                <tr><td>car Model :</td><td><%=car.getCarModel()%></td></tr>
                <tr><td>rent price per day :</td><td><%=car.getRentPrice()%></td></tr>
                <tr><td>number of times reserved :</td><td><%=item.getReservedCount()%></td></tr>
                <tr><td>currently available :</td><td><%=car.getAvailableNumber()%> of <%=item.getQuantity()%> cars</td></tr>
                <tr><td>rating :</td><td><%=item.getRating()%></td></tr>
                <tr><td colspan="2"> <hr/></td></tr>
            </table>
        </div>



        <%}}else if (req.equals("registerCustomer")){%>
            <h3>Register a New Customer</h3>


            <%if (request.getAttribute("error") != null){%>
                <div> error : <%=(String)request.getAttribute("error")%> </div>
            <%}%>
            <form action="register" method="POST">
                <jsp:include page="registeruser.jsp"/>
                <div>
                    <input type="hidden" name="req" value="registerCustomer" />
                    <input type="submit" value="register" />
                </div>
            </form>
        <%}else if (req.equals("registerAdmin")){%>
            <h3>join km-portal Admins ...</h3>

            <%if (request.getAttribute("error") != null){%>
                <div> error : <%=(String)request.getAttribute("error")%> </div>
            <%}%>

            <form action="register" method="POST">
                <jsp:include page="registeruser.jsp"/>
                <input type="hidden" name="req" value="registerAdmin" />
                <h3>Other Info</h3>
                Qualifications and Previous jobs: <br>
                <textarea class="textfield" name="qualifications" rows="4" cols="40"></textarea><br>
                <input type="submit" value="register" />
            </form>
        <%}%>
    </div>
    <div id="sidebar">
      <div id="sidebartop"></div>
      <h2>options</h2>
      <ul>
        <li class="active"><a href="home.jsp">Main</a></li>
        <li><a href="home.jsp?req=mostPopular">most popular items</a></li>
        <li><a href="home.jsp?req=topRated">top rated items</a></li>
        <li><a href="home.jsp?req=login">login</a></li>
        <li><a href="home.jsp?req=registerCustomer">Register</a></li>
      </ul>
      <h2>Links</h2>
      <ul>
        <li><a href="www.google.com">blah blah</a></li>
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
