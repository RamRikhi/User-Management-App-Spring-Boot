<%--
  Created by IntelliJ IDEA.
  User: rikhi
  Date: 24-07-2020
  Time: 21:45
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="frm"%>
<html>
<head>
    <title>Forgot Password | Ram-Liles</title>
</head>
<body>
    <frm:form action="recoverPassword" modelAttribute="forgotPazzword" method="post">
        <table>
            <tr>
                <td colspan="2">
                    <h2>Forgot Password</h2>
                </td>
            </tr>
            <tr>
                <td>Email::</td>
                <td><frm:input path="email"></frm:input></td>
            </tr>
            <tr>
                <td><input type="submit" value="Recover Account"> </td>
            </tr>
        </table>
    </frm:form>
    <h3 style="color: red;">${msg}</h3>
    <a href="login">Go Back</a>
</body>
</html>
