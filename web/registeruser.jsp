<%-- 
    Document   : registeruser
    Created on : Dec 26, 2009, 2:58:51 AM
    Author     : ahmed
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
    "http://www.w3.org/TR/html4/loose.dtd">

<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
        <link href="styles/style.css" rel="stylesheet" type="text/css" />
        <script type="text/javascript">
            function passwordcheck()
            {
                var password=document.getElementById('pass').value;
                var best=/^.*(?=.{6,})(?=.*[A-Z])(?=.*[\d])(?=.*[\W]).*$/;
                var strong=/^[a-zA-Z\d\W_]*(?=[a-zA-Z\d\W_]{6,})(((?=[a-zA-Z\d\W_]*[A-Z])(?=[a-zA-Z\d\W_]*[\d]))|((?=[a-zA-Z\d\W_]*[A-Z])(?=[a-zA-Z\d\W_]*[\W_]))|((?=[a-zA-Z\d\W_]*[\d])(?=[a-zA-Z\d\W_]*[\W_])))[a-zA-Z\d\W_]*$/;
                var weak=/^[a-zA-Z\d\W_]*(?=[a-zA-Z\d\W_]{6,})(?=[a-zA-Z\d\W_]*[A-Z]|[a-zA-Z\d\W_]*[\d]|[a-zA-Z\d\W_]*[\W_])[a-zA-Z\d\W_]*$/;
                var bad=/^((^[a-z]{6,}$)|(^[A-Z]{6,}$)|(^[\d]{6,}$)|(^[\W_]{6,}$))$/;

       
                if(password == null || password =="" || password.length == 0)
                {
                    document.getElementBy("indicate").style.color="white";
                    document.getElementById("indicate").innerHTML="";
                }
                else if(password.length < 6)
                {
                    document.getElementById("indicate").style.color="orange";
                    document.getElementById("indicate").innerHTML=" Not enough";
                }
                else if(best.test(password))
                {
                    document.getElementById("indicate").style.color="green";
                    document.getElementById("indicate").innerHTML=" Best";
                }
                else if(strong.test(password))
                {
                    document.getElementById("indicate").style.color="blue";
                    document.getElementById("indicate").innerHTML=" Strong";
                }
                else if(weak.test(password))
                {
                    document.getElementById("indicate").style.color="yellow";
                    document.getElementById("indicate").innerHTML=" Weak";
                }
                else if(bad.test(password))
                {
                    document.getElementById("indicate").style.color="red";
                    document.getElementById("indicate").innerHTML=" Bad";
                }
            }
        </script>

    </head>
    <body>
        <h3>main info</h3>
        <label for="name">your full name :</label>
        <input class="textfield" id="name" type="text" name="name" value="" />
        <label for="uname">user name :</label>
        <input class="textfield" name="userName" id="uname" type="text" />

        <label for="password" id="password">password:</label>
        <input class="textfield" id="pass" name="password" type="password" onkeyup="passwordcheck();"/>
        <label for="indicator" id="indicate"></label>

        <label for="cpassword">confirm password:</label>
        <input class="textfield" id="cpassword" name="cpassword" type="password" />

        <label for="address">address :</label>
        <input class="textfield" id="address" name="address" type="text" />

        <label for="email">email:</label>
        <input class="textfield" id="email" name="email" type="text" />

        <label for="pnum">phone number:</label>
        <input class="textfield" id="pnum" name="phone" type="text" />

    </body>
</html>
