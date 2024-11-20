<%--
  Created by IntelliJ IDEA.
  User: Emmanuel Paul
  Date: 11/20/2024
  Time: 4:53 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html lang="en">
<head>
  <meta charset="UTF-8">
  <title>All Users</title>
  <link rel="stylesheet" href="styles.css"> <!-- Assuming a stylesheet is available -->
</head>
<body>
<h1>All Users</h1>

<c:if test="${not empty userList}">
  <table border="1">
    <thead>
    <tr>
      <th>User ID</th>
      <th>First Name</th>
      <th>Last Name</th>
      <th>Username</th>
      <th>Email</th>
      <th>Date Joined</th>
      <th>Role</th>
    </tr>
    </thead>
    <tbody>
    <c:forEach var="user" items="${userList}">
      <tr>
        <td>${user.id}</td>
        <td>${user.firstName}</td>
        <td>${user.lastName}</td>
        <td>${user.userName}</td>
        <td>${user.email}</td>
        <td>${user.createdAt}</td>
        <td>${user.role}</td>
      </tr>
    </c:forEach>
    </tbody>
  </table>
</c:if>
<a href="admin-dashboard" class="back">Back to Admin Dashboard</a>
</body>
</html>

