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
                var rate =document.getElementById('rate').innerHTML;
                changeImage(0);     //refresh all stars
                changeImage(rate);
            }
            function setRate(rate){
                document.getElementById('rate').innerHTML = rate;
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
            <tr><td>car model :</td><td><%=car.getCarModel()%></td></tr>
            <tr><td>rent price per day :</td><td><%=car.getRentPrice()%></td></tr>
            <tr><td>number of reserved times :</td><td><%=item.getReservedCount()%></td></tr>
            <tr><td>currently available :</td><td><%=car.getAvailableNumber()%> of <%=item.getQuantity()%> cars</td></tr>
            <tr><td colspan="2"> <hr></td></tr>
        </table>
        <%}%>


        <%
            boolean review = Boolean.parseBoolean((String) request.getAttribute("enableReview"));
            if (review == true) {
        %>


        <h3>your review</h3>
        <textarea name="review" rows="4" cols="50">
        </textarea>
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
        Rating = <label id="rate" >0</label>
        <br>
        <%}%>



        <%
            ArrayList<Review> reviews = item.getReviews();
            if (reviews.size() == 0) {
                out.print("no reviews");
            }
            for (int i = 0; i < reviews.size(); i++) {
                out.println(reviews.get(i).getComment());
            }

        %>

    </body>
</html>
