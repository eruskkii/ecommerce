<%--
  Created by IntelliJ IDEA.
  User: Emmanuel Paul
  Date: 11/18/2024
  Time: 4:10 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Add Category</title>
</head>
<body>
<div align="center">
    <h1>Add Category</h1>
    <% if (request.getParameter("success") != null) { %>
    <div style="color: green;">Category added successfully!</div>
    <% } %>
    <form action="<%= request.getContextPath() %>/addCategory" method="post">
        <label for="categoryName">Category Name:</label>
        <input type="text" id="categoryName" name="name" required><br><br>
        <input type="submit" value="Add Category">
    </form>
</div>
</body>
</html>

