<%-- 
    Document   : payment
    Created on : Jan 5, 2010, 1:16:14 AM
    Author     : ahmed
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
    "http://www.w3.org/TR/html4/loose.dtd">

<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Payment Info</title>
        <script type="text/javascript" src="calender/calendar_us.js"></script>
        <link rel="stylesheet" href="calender/calendar.css"/>
    </head>
    <body>
        <h2>Payment Info</h2>
        <%if (request.getAttribute("error") != null) {%>
        <div style="background-color:#fff5c3"> Error : <%=(String) request.getAttribute("error")%> </div>
        <%}%>
        <script type="text/javascript" language="JavaScript">


            function test()
            {
                //credit card
                var c0 = document.getElementById("choose0");
                var c1 = document.getElementById("choose1");
                if(c0.checked == true){
                    document.getElementById("option0").style.display = 'block';
                    document.getElementById("option1").style.display = 'none';
                }else if(c1.checked == true){
                    document.getElementById("option1").style.display = 'block';
                    document.getElementById("option0").style.display = 'none';
                }

            }

            function checkDigit(digitNumber,target,indicatorLabel){
                var userInput;
                var numericExpression = /^[0-9]+$/;                
                var userInput = document.getElementById(target).value;
                var indicator = document.getElementById(indicatorLabel);
                if(userInput == null || userInput=="" || userInput.length==0){
                    indicator.style.color="white";
                    return false;
                }

                if(userInput.match(numericExpression) && userInput.length==digitNumber ){
                    indicator.innerHTML = "Correct";
                    indicator.style.color="green";
                    return true;
                }else if(userInput.length==digitNumber){
                    indicator.innerHTML = "input must be digits only";
                    indicator.style.color="red";
                    return false;
                }
                if(userInput.length!=digitNumber){
                    indicator.innerHTML = "Wrong input lenght";
                    indicator.style.color="red";
                    return false;
                }
                return true;
            }
            function checkall(){
                var c0 = document.getElementById("choose0");
                var c1 = document.getElementById("choose1");
                var check = true;
                if(c0.checked == true){
                    check = check &&  checkDigit(16,'in0_1','ind0_1');
                }else if(c1.checked == true){
                    check = check && checkDigit(9,'in1_1','ind1_1');
                    check = check && checkDigit(5,'in1_3','ind1_3');
                }
                if (!check){
                    alert("please recheck the invalid fields");
                }
                return check;
            }
        </script>
        <form action="customer" method="post" onsubmit="return checkall()">
            <input type="hidden" name="req" value="pay" />
            <div>
                <input type="hidden" name="req" value="pay" />
                <label for="choose0" >credit card </label>
                <input id ="choose0" type="radio" name="type" value="credit card" checked="true"  onclick="test();" />
                <label for="choose1">Bank draft </label>
                <input id ="choose1" type="radio" name="type" value="Bank draft" onclick="test();"  />


                <div id="option0">
                    Card Number :
                    <input name ="in0_1" type="text" value="0000000000000000" id="in0_1" onkeyup="checkDigit(16,'in0_1','ind0_1')"/>
                    <label id="ind0_1" ></label>
                    <br>

                    Expire Date :
                    <input name ="in0_2" type="text" value="" id="in0_2" />
                    <script language="JavaScript">
                        A_TCALDEF['imgpath'] = 'calender/img/';
                        new tcal ({
                            'controlname': 'in0_2'
                        });
                    </script>
                    <label id="ind0_2" ></label>
                    <br>
                    Billing Address:
                    <input name ="in0_3" type="text" value="" id="in0_3" />
                    <label id="ind0_3" ></label>
                </div>

                <div id ="option1" style="display:none">
                    Account Number  : <input name ="in1_1" type="text" value="000000000" id="in1_1" onkeyup="checkDigit(9,'in1_1','ind1_1')"/>
                    <label id="ind1_1" ></label>
                    <br>

                    BankName : <input name ="in1_2" type="text" value="" id="in1_2" />
                    <label id="ind1_2" ></label>
                    <br>

                    Routing Number :<input name ="in1_3" type="text" value="00000" id="in1_3" onkeyup="checkDigit(5,'in1_3','ind1_3')"/>
                    <label id="ind1_3" ></label>
                </div>
            </div>
            <input type="submit" value="Pay for item"/>
        </form>

    </body>
</html>
