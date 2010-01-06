<%-- 
    Document   : ownerhome
    Created on : Jan 3, 2010, 4:56:54 AM
    Author     : ahmed
--%>

<%@page contentType="text/html" pageEncoding="UTF-8" import="java.util.* , reservationPortalSystem.Admin"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
   "http://www.w3.org/TR/html4/loose.dtd">

<html xmlns="http://www.w3.org/1999/xhtml">
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
            String mode = (String) request.getAttribute("mode");
            if (mode == null) {
                out.println("welcome");
            } else if (mode.equals("allAdmins") || mode.equals("newAdmins")) {
                out.println(mode + "<br>");
                Collection<Admin> admins = (Collection<Admin>) request.getAttribute("result");

                if (admins == null) {
                    out.println("nothing found");
                    return;
                }

                for (Admin admin : admins) {
            %>
            <div style="background-color:orange">
                <table>
                    <tr>
                        <td> Name : </td> <td> <%=admin.getName()%> </td>
                    </tr>
                    <tr>
                        <td> UserName : </td> <td> <%=admin.getUserName()%> </td>
                    </tr>
                    <tr>
                        <td> Address : </td> <td> <%=admin.getAddress()%> </td>
                    </tr>
                    <tr>
                        <td> Email : </td> <td> <%=admin.getEmail()%> </td>
                    </tr>
                    <tr>
                        <td> Phone Number : </td> <td> <%=admin.getPhoneNumber()%> </td>
                    </tr>

                    <tr>
                        <td> Qualifications : </td> <td> <%=admin.getQualifications()%> </td>
                    </tr>
                    <tr>
                        <td> lastLoginDate : </td> <td> <%=admin.getLastLoginDate()%> </td>
                    </tr>
                    <tr>
                        <% if (admin.isActivated()) {%>
                        <td><a href="owner?req=deactivate&userName=<%=admin.getUserName()%>">deactivate</a></td>
                        <% } else {%>
                        <td><a href="owner?req=activate&userName=<%=admin.getUserName()%>">activate</a></td>
                        <% }%>
                    </tr>

                </table>

            </div>
            <%  }
            }
            %>

    </div>
    <div id="sidebar">
      <div id="sidebartop"></div>
      <h2>options</h2>
      <ul>
        <li class="active"><a href="home.jsp">Home</a></li>
        <li><a href="owner?req=allAdmins">All Admins</a></li>
        <li><a href="owner?req=newAdmins">New Admins</a></li>
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
