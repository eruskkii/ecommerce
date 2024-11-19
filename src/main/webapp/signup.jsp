<%--
  Created by IntelliJ IDEA.
  User: Emmanuel Paul
  Date: 11/18/2024
  Time: 2:52 AM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Signup Form</title>
</head>
<body>
<div align="center">
    <h1>Signup Form</h1>
    <form action="/signup" method="post">
        First Name : <input type="text" name="firstName" /><br /><br />
        Last Name : <input type="text" name="lastName" /><br /><br />
        User Name : <input type="text" name="userName" /><br /><br />
        Email : <input type="email" name="email" /><br /><br />
        Password : <input type="password" name="password" /><br /><br />
        <input type="submit" value="Signup">
    </form>
</div>
</body>
</html>
