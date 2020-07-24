<%--
  Created by IntelliJ IDEA.
  User: rikhi
  Date: 24-07-2020
  Time: 18:20
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="frm"%>
<html>
<head>
    <title>Home | Ram-Liles</title>
</head>
<body>
    <div>
        <frm:form action="checkUser" modelAttribute="login" method="post">
            <table>
                <tr>
                    <td colspan="3"><h3>Login Here</h3></td>
                </tr>
                <tr>
                    <td>Email::</td>
                    <td><frm:input path="userEmail"></frm:input></td>
                </tr>
                <tr>
                    <td>Password::</td>
                    <td><frm:password path="pazzword"></frm:password></td>
                </tr>
                <tr>
                    <td colspan="3"><input type="submit" value="Login"></td>
                </tr>
                <tr>
                    <td>
                        <a href="signUpForm">Sign up</a>
                    </td>
                    <td>
                        <a href="forgotPassword">Forgot Password?</a>
                    </td>
                </tr>
            </table>
        </frm:form>
    </div>
</body>
</html>
