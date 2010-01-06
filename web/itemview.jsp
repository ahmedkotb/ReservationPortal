<%-- 
    Document   : itemview
    Created on : Jan 6, 2010, 2:54:12 AM
    Author     : Ahmed Kotb
--%>

<%@page contentType="text/html" pageEncoding="UTF-8" import="items.* , java.util.*" %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
    "http://www.w3.org/TR/html4/loose.dtd">

<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>item view</title>
        <script type="text/javascript">
            function changeImage(starNumber){
                for( i=1 ;i<=starNumber;i++)
                    document.images["star"+i].src= "on.png";
                for( i=starNumber+1 ;i<=5;i++)
                    document.images["star"+i].src= "off.png";
            }
            function changeImageBack(){
                var rate = document.getElementById('rateLabel').innerHTML;
                if (isNaN(rate)){
                    changeImage(0);
                }else{
                    changeImage(0);     //refresh all stars
                    changeImage(rate);
                }
            }
            function setRate(rate){
                document.getElementById('rateLabel').innerHTML = rate;
                document.getElementById('rateInput').value = rate;
            }
            function showHide(){
                if (document.getElementById("newReview").style.display == 'none'){
                    document.getElementById("newReview").style.display = 'block';
                }
                else{
                    document.getElementById("newReview").style.display = 'none';
                }
            }
        </script>
    </head>
    <body>
        <% ReservationItem item = (ReservationItem) request.getAttribute("item");
            if (item instanceof Car) {
                Car car = (Car) item;
        %>
        <h3> Car View </h3>
        <table>
            <tr><td>item id :</td><td><%=car.getId()%></td></tr>
            <tr><td>car Type :</td><td><%=car.getCarType()%></td></tr>
            <tr><td>car Model :</td><td><%=car.getCarModel()%></td></tr>
            <tr><td>rent price per day :</td><td><%=car.getRentPrice()%></td></tr>
            <tr><td>number of times reserved :</td><td><%=item.getReservedCount()%></td></tr>
            <tr><td>currently available :</td><td><%=car.getAvailableNumber()%> of <%=item.getQuantity()%> cars</td></tr>
            <tr><td>rating :</td><td><%=item.getRating()%></td></tr>
            <tr><td colspan="2"> <hr></td></tr>
        </table>
        <%}%>


        <%
            boolean review = Boolean.parseBoolean((String) request.getAttribute("enableReview"));
            if (review == true) {
        %>
        <div id="newReview">
        <form action="customer" method="post">
            <input type="hidden" name="req" value="rate" />
            <input type="hidden" name="id" value="<%=(String)request.getParameter("id")%>" />
            <h3>your review</h3>            
            <table border="0">
                <tr>
                    <td align="center">
                        <A href="#"
                           onMouseOver=" changeImage(1)"
                           onMouseOut= " changeImageBack()"
                           onclick="setRate(1)"
                           >
                            <img name="star1" src="off.png" width="30" height="30" border="0" alt="Poor">
                        </A>
                    </td>
                    <td align="center">
                        <A href="#"
                           onMouseOver=" changeImage(2)"
                           onMouseOut= " changeImageBack()"
                           onclick="setRate(2)">
                            <img name="star2" src="off.png" width="30" height="30" border="0" alt="Nothing special">
                        </A>
                    </td>
                    <td align="center">
                        <A href="#"
                           onMouseOver=" changeImage(3)"
                           onMouseOut= " changeImageBack()"
                           onclick="setRate(3)">
                            <img name="star3" src="off.png" width="30" height="30" border="0" alt="Good">
                        </A>
                    </td>
                    <td align="center">
                        <A href="#"
                           onMouseOver=" changeImage(4)"
                           onMouseOut= " changeImageBack()"
                           onclick="setRate(4)">
                            <img name="star4" src="off.png" width="30" height="30" border="0" alt="Pretty cool">
                        </A>
                    </td>
                    <td align="center">
                        <A href="#"
                           onMouseOver=" changeImage(5)"
                           onMouseOut= " changeImageBack()"
                           onclick="setRate(5)">
                            <img name="star5" src="off.png" width="30" height="30" border="0" alt="Awesome">
                        </A>
                    </td>
                </tr>
            </table>
            
            Rating = <label id="rateLabel" >not rated yet</label>
            <br>
            <textarea name="review" rows="6" cols="30">
            </textarea>
            <input type="hidden" name="rateInput" id="rateInput" value =""/>
            <br>
            <input type="submit" value="Add Review" />
        </form>
        <br>
        <%}%>
        <hr>
        </div>


        <%
            ArrayList<Review> reviews = item.getReviews();
            if (reviews.size() == 0) {
                out.print("no reviews for this item");
            }
            out.print("<h3>other reviews</h3>");
            for (int i = 0; i < reviews.size(); i++) {
        %>
        <div style="float:left">
            <h4>#<%=i+1%></h4>
        </div>
        <table>
            <tr><td>name :</td> <td> <%=reviews.get(i).getUser().getName()%> </td> </tr>
            <tr><td>date :</td> <td> <%=reviews.get(i).getDate() %> </td> </tr>
            <tr><td valign="top">review :</td> <td> <%=reviews.get(i).getComment().replaceAll("\n", "<br>") %> </td> </tr>
            <tr><td colspan="2"><hr></td> </tr>
        </table>
       <%} %>

    </body>
</html>
