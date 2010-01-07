<%-- 
    Document   : searchcar
    Created on : Jan 1, 2010, 2:57:49 AM
    Author     : ahmed
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"
        import="reservationPortalSystem.ReservationPortalSystem ,reservationPortalSystem.IAdminReservationItemManager
        , items.CarAgency , java.util.* , items.CarType"

        %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
    "http://www.w3.org/TR/html4/loose.dtd">

<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>search for a car</title>
        <script type="text/javascript" src="calender/calendar_us.js"></script>
        <link rel="stylesheet" href="calender/calendar.css"/>
        <script type="text/javascript">
            choosedCombo = 0;
            init =1;
            //ajax libs
            function GetXmlHttpObject(){
                var xmlHttp = null;
                try {
                    // Firefox, Opera 8.0+, Safari
                    xmlHttp = new XMLHttpRequest();
                }catch (e) {
                    // Internet Explorer
                    try {
                        xmlHttp = new ActiveXObject("Msxml2.XMLHTTP");
                    }
                    catch (e) {
                        xmlHttp = new ActiveXObject("Microsoft.XMLHTTP");
                    }
                }
                return xmlHttp;
            }

            function stateChanged(){
                if (xmlHttp.readyState == 4) {
                    //alert(xmlHttp.responseText);
                    if (choosedCombo == 0){
                        document.getElementById("country0").innerHTML = xmlHttp.responseText;
                        changeCountry(0);
                    }else if (choosedCombo ==1){
                        document.getElementById("city0").innerHTML = xmlHttp.responseText;
                        changeCity(0);
                    }else if (choosedCombo == 2){
                        document.getElementById("street0").innerHTML = xmlHttp.responseText;
                        if (init ==1){
                             choosedCombo =3;
                             run('location?req=countries');
                             init = 0;
                        }
                    }else if (choosedCombo == 3 ){
                        document.getElementById("country1").innerHTML = xmlHttp.responseText;
                        changeCountry(1);
                    }else if (choosedCombo == 4 ){
                        document.getElementById("city1").innerHTML = xmlHttp.responseText;
                        changeCity(1);
                    }else if (choosedCombo == 5 ){
                        document.getElementById("street1").innerHTML = xmlHttp.responseText;
                    }
                }
            }
            function run(scriptName){
                xmlHttp = GetXmlHttpObject();
                if (xmlHttp == null) {
                    alert("Your browser does not support AJAX!");
                    return;
                }
                var url = scriptName;
                xmlHttp.onreadystatechange = stateChanged;
                xmlHttp.open("GET", url, true);
                xmlHttp.send(null);
            }

            function loadCountries(){
                choosedCombo= 0;
                run('location?req=countries');
                document.getElementById("city0").innerHTML = "";
                document.getElementById("street0").innerHTML = "";
                document.getElementById("city1").innerHTML = "";
                document.getElementById("street1").innerHTML = "";
            }
            function changeCountry(c){
                if (c==0){
                    choosedCombo = 1;
                    run('location?req=cities&country=' + document.getElementById("country0").value);
                    document.getElementById("street0").innerHTML = "";
                }else{
                    choosedCombo = 4;
                    run('location?req=cities&country=' + document.getElementById("country1").value);
                    document.getElementById("street1").innerHTML = "";
                }
                
            }
            function changeCity(c){
                if (c==0){
                    choosedCombo = 2;
                    run('location?req=streets&country=' + document.getElementById("country0").value + "&city=" + document.getElementById("city0").value);
                }else{
                    choosedCombo = 5;
                    run('location?req=streets&country=' + document.getElementById("country1").value + "&city=" + document.getElementById("city1").value);
                }
            }

        </script>
    </head>
    <body onload="loadCountries()">
        <h2>search for a car</h2>
        <h4> you can leave some fields empty </h4>
            <%if (request.getAttribute("error") != null){%>
                <div style="background-color:#fff5c3"> Error : <%=(String)request.getAttribute("error")%> </div>
            <%}%>
        <table>
            <tr>
                <td>car Type :</td>
                <td>
                    <select name="carType">
                    <option></option>
                    <% 
                        for (CarType ct : CarType.values())
                            out.println("<option>" + ct + "</option>");
                    %>
                    </select>
                </td>
            </tr>
            <tr>
                <td>car Model</td>
                <td><input type="text" name="carModel" value="" /></td>
            </tr>
            <tr><td colspan="2"> <hr/></td></tr>
            <tr>
                <td>pickup Location</td>
                <td>
                    <table>
                           <tr><td>country : </td>  <td><select name="pickupCountry" id="country0" onchange="changeCountry(0)"></select></td> </tr>
                           <tr><td> city : </td>  <td><select name="pickupCity" id="city0" onchange="changeCity(0)" onclick="changeCity(0)"></select> </td> </tr>
                           <tr><td>street : </td>  <td><select name="pickupStreet" id="street0"></select></td> </tr>
                    </table>
                </td>
            </tr>
            <tr><td colspan="2"> <hr/></td></tr>
            <tr>
                <td >return Location</td>
                <td>
                    <table>
                        <tr><td>country : </td><td><select name="returnCountry" id="country1" onchange="changeCountry(1)"></select> </td></tr>
                        <tr><td>city :</td>  <td><select name="returnCity"    id="city1" onchange="changeCity(1)" onclick="changeCity(1)">   </select> </td> </tr>
                        <tr><td>street :</td>  <td><select name="returnStreet" id="street1">  </select> </td> </tr>                     
                    </table>
                </td>

            </tr>
            <tr><td colspan="2"> <hr/></td></tr>
            <tr>
                <td>start date :</td>
                <td> <input id="sd" type="text" name="startDate" value ="1/1/2010" />
                     <script language="JavaScript">
                            A_TCALDEF['imgpath'] = 'calender/img/';
                            new tcal ({
                                // input name
                                'controlname': 'sd'
                            });
                     </script>
                </td>
            </tr>
            <tr>
                <td>end date :</td>
                <td> <input id ="ed" type="text" name="endDate" value ="6/1/2010" />
                     <script language="JavaScript">
                            A_TCALDEF['imgpath'] = 'calender/img/';
                            new tcal ({
                                // input name
                                'controlname': 'ed'
                            });
                     </script>
                </td>
            </tr>
        </table>

    </body>
</html>
