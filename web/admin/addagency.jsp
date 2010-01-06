<%-- 
    Document   : addagency
    Created on : Dec 31, 2009, 1:35:20 AM
    Author     : Ahmed Kotb
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
    "http://www.w3.org/TR/html4/loose.dtd">

<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Add a new agency</title>
        <script type="text/javascript">
            rowIDs = 0;
            function addLocation(){
                var row = document.getElementById("locationsTable").insertRow(1);
                rowIDs+=1;
                row.id = rowIDs;
                var cell1 = row.insertCell(0);
                var cell2 = row.insertCell(1);
                cell1.innerHTML = document.getElementById("country").value + "," + document.getElementById("city").value + "," + document.getElementById("street").value;
                cell2.innerHTML = "<input type=\"button\" value=\"remove\" onclick=\"remove("+ row.id  + ")\" />";
            }
            function showHide(){
                if (document.getElementById("newLocationDiv").style.display == 'none')
                    document.getElementById("newLocationDiv").style.display = 'block';
                else
                    document.getElementById("newLocationDiv").style.display = 'none';               
            }
            function remove(index){
                document.getElementById("locationsTable").deleteRow(document.getElementById(index).rowIndex);
            }
            function addAgency(){
                var tbl = document.getElementById("locationsTable");
                var form = document.getElementById("addAgencyForm");
                var namefield = document.getElementById("name");
                var descriptionField = document.getElementById("description");
                var fields = "<input type='hidden' name='req' value='addAgency' />";
                
                fields += "<input type='hidden' name='name' value='" + namefield.value + "' /> ";
                fields += "<input type='hidden' name='description' value='" + descriptionField.value + "' /> ";

                fields += "<input type='hidden' name='numberOfLocations' value='" + (tbl.rows.length-1) + "' /> ";
                for (var i=1;i< tbl.rows.length;i++)
                    fields +="\n<input type='hidden' name='loc" +i + "' value='" +tbl.rows[i].cells[0].innerHTML + "' /> ";
                form.innerHTML = fields;
                setTimeout("submitForm()", 100);
            }
            function submitForm(){
                document.getElementById("addAgencyForm").submit();
            }
        </script>
    </head>
    <body>
        <h2>Add a new car agency</h2>
        <%if (request.getAttribute("error") != null){%>
            <div style="background-color:#fff5c3"> Error : <%=(String)request.getAttribute("error")%> </div>
        <%}%>

        <form id="addAgencyForm" action="admin" method="POST">
            <input type="hidden" name="req" value="addAgency" />

            <table border="0">
                <tbody>
                    <tr>
                        <td>Agency Name :</td>
                        <td><input id="name" type="text" name="name" value="" /> </td>
                    </tr>
                    <tr>
                        <td>Agency Description :</td>
                        <td><textarea id ="description" name="description" rows="4" cols="20"></textarea></td>
                    </tr>
                </tbody>
            </table>
        </form>
        <br>
        Supported Locations : <input type="button" value="Add Location" onclick="showHide()"/>
        <div id="newLocationDiv" style="display:none" >
            <table border="0">
                <tr>
                    <td>country :</td>
                    <td><input type="text" id="country" value="" /></td>
                </tr>
                <tr>
                    <td>city :</td>
                    <td><input type="text" id="city" value="" /></td>
                </tr>
                <tr>
                    <td>street :</td>
                    <td><input type="text" id="street" value="" /></td>
                </tr>
                <tr>
                    <td colspan="2" align="center">
                        <input type="button" value="Add" onclick="addLocation()" />
                    </td>
                </tr>
            </table>
            

        </div>

        <table border="1" id="locationsTable">
            <thead>
                <tr>
                    <th>Location</th>
                    <th>remove</th>
                </tr>
            </thead>
        </table>

        <div align="center">
            <input type="button" value="ADD AGENCY" onclick="addAgency()"/>
        </div>
    </body>
</html>
