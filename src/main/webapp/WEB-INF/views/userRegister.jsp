<%--
  Created by IntelliJ IDEA.
  User: rikhi
  Date: 21-07-2020
  Time: 12:06
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="core"%>
<html>
<head>
    <title>Register</title>
    <script src="./js/app.js" language="JavaScript"></script>
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.2.1/jquery.min.js" language="JavaScript"></script>
    <style>
        body{
            background-color: black;
        }
        label{
            color: aliceblue;
        }
    </style>
</head>
<body>
    <div class="container ">
        <h1  style="text-align: center;font-family: Consolas;margin-top: 30px;color: aliceblue">Ram-Liles Company</h1>
    </div>
    <form:form modelAttribute="userAcc" method="post" action="saveUserDetails">
        <table>
            <tr>
                <td><label style="color: aliceblue">First Name::</label></td>
                <td>
                    <form:input path="firstName"></form:input>
                </td>
            </tr>
            <tr>
                <td><label style="color: aliceblue">Last Name::</label></td>
                <td>
                    <form:input path="lastName"/>
                </td>
            </tr>
            <tr>
                <td><label style="color: aliceblue">Email::</label></td>
                <td>
                    <form:input path="userEmail"/>
                </td>
                <td><span id="dupEmail" style="font-family: 'Arial Black';color: red"></span></td>
            </tr>
            <tr>
                <td><label style="color: aliceblue">Mobile::</label></td>
                <td>
                    <form:input path="userMobile"/>
                </td>
            </tr>
            <tr>
                <td><label style="color: aliceblue">Date Of Birth::</label></td>
                <td>
                    <form:input path="dob"/>
                </td>
            </tr>
            <tr>
                <td><label style="color: aliceblue">Gender::</label></td>
                <td>
                    <form:radiobutton path="gender" value="M" id="gender"/><label  style="color: aliceblue">Male</label>
                    <form:radiobutton path="gender"  value="F" id="gender"/><label style="color: aliceblue">Female</label>
                </td>
            </tr>
            <tr>
                <td><label style="color: aliceblue">Country::</label></td>
                <td>
                    <form:select path="countryId">
                        <form:option value="">Choose...</form:option>
                        <form:options items="${countries}"/>
                    </form:select>
                </td>
            </tr>
            <tr>
                <td><label style="color: aliceblue">State::</label></td>
                <td>
                    <form:select path="stateId">
                        <form:option value="">Choose...</form:option>
                    </form:select>
                </td>
            </tr>
            <tr>
                <td><label style="color: aliceblue">City::</label></td>
                <td>
                    <form:select path="cityId">
                        <form:option value="">Choose...</form:option>
                    </form:select>
                </td>
            </tr>
            <tr>
                <td>
                    <input type="submit" value="Register with us">
                </td>
                <td>&nbsp;&nbsp;&nbsp;
                    <input type="reset" value="Clear All">
                </td>
            </tr>
        </table>
    </form:form>

<script>

    $(document).ready(function(event) {

        $("#countryId").change(function() {
            $("#stateId").find('option').remove();
            $('<option>').val('').text('Choose...').appendTo("#stateId");

            $("#cityId").find('option').remove();
            $('<option>').val('').text('Choose...').appendTo("#cityId");

            let countryId = $("#countryId").val();
            $.ajax({
                type : "GET",
                url : "getStates?cid=" + countryId,
                success : function(res) {
                    $.each(res, function(stateId, stateName) {
                        $('<option>').val(stateId).text(stateName).appendTo("#stateId");
                    });
                }
            });
        });

        $("#stateId").change(function() {

            $("#cityId").find('option').remove();
            $('<option>').val('').text('Choose...').appendTo("#cityId");

            let stateId = $("#stateId").val();
            $.ajax({
                type : "GET",
                url : "getCities?sid=" + stateId,
                success : function(data) {
                    $.each(data, function(cityId, cityName) {
                        $('<option>').val(cityId).text(cityName).appendTo("#cityId");
                    });
                }
            });
        });

        $("#userEmail").blur(function (event) {
            $("#dupEmail").html("");
            let emailValidator = $("#userEmail").val();
            $.ajax({
                url : 'validateEmail?email='+ emailValidator,
                success :function (emailStatus) {
                    if (emailStatus === 'Duplicate'){
                        $("#dupEmail").html("Email is already registered");
                        $("#userEmail").focus();
                    }
                }
            });
        });
    });

</script>
</body>
</html>
