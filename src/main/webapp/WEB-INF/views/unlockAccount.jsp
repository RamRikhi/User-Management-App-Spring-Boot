<%--
  Created by IntelliJ IDEA.
  User: rikhi
  Date: 23-07-2020
  Time: 13:22
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="frm"%>
<html>
<head>
    <title>Unlock | Account</title>
    <script>
        function passwordCheck() {
            document.getElementById("wrongPwd").innerHTML ="";
            let newPwd = document.getElementById("newPassword").value;
            let cnfPwd = document.getElementById("confirmPassword").value;
            if(newPwd === cnfPwd){
                return true;
            }
            document.getElementById("wrongPwd").innerHTML = "Ohh No!!! Password doesn't match..."
            return false;
        }
    </script>
</head>
<body>
    <h1>Ram-Liles Company</h1>

    <frm:form action="unlockAccDetails" modelAttribute="unlockUserAcc" method="post">
        <table>
            <tr>
                <td>Email::</td>
                <td><frm:input path="email" readonly="true"></frm:input></td>
            </tr>
            <tr>
                <td>Temporary Password::</td>
                <td><frm:password path="tempPwd"></frm:password></td>
            </tr>
            <tr>
                <td>New Password::</td>
                <td><frm:password path="newPwd" id="newPassword"></frm:password></td>
            </tr>
            <tr>
                <td>Confirm Password::</td>
                <td><frm:password path="confirmPwd" id="confirmPassword"></frm:password></td>
                <td><span style="color:red" id="wrongPwd"></span></td>
            </tr>
            <tr>
                <td colspan="2"><input type="submit" value="Change Password" onclick="return passwordCheck()"></td>
            </tr>
        </table>
    </frm:form>
</body>
</html>
