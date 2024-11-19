<%@ page import="java.util.List" %><%--
  Created by IntelliJ IDEA.
  User: Emmanuel Paul
  Date: 11/18/2024
  Time: 2:52 AM
  To change this template use File | Settings | File Templates.
--%>
<%@ page import="java.util.List" %>
<%@ page import="ecommerce.model.Category" %>
<html>
<head>
    <title>Add Product</title>
</head>
<body>
<div align="center">
    <h1>Add Product</h1>
    <% if (request.getParameter("success") != null) { %>
    <div style="color: green;">Product added successfully!</div>
    <% } %>
    <form action="<%= request.getContextPath() %>/addProduct" method="post">
        <label for="productName">Product Name:</label>
        <input type="text" id="productName" name="name" required><br><br>

        <label for="description">Description:</label>
        <input type="text" id="description" name="description" required><br><br>

        <label for="price">Price:</label>
        <input type="number" id="price" name="price" step="0.01" required><br><br>

        <label for="quantity">Quantity:</label>
        <input type="number" id="quantity" name="quantity" required><br><br>

        <label for="category">Category:</label>
        <select id="category" name="categoryId" required>
            <%
                List<Category> categories = (List<Category>) request.getAttribute("categories");
                if (categories != null && !categories.isEmpty()) {
                    for (Category category : categories) {
            %>
            <option value="<%= category.getCategoryId() %>"><%= category.getCategoryName() %></option>
            <%
                }
            } else {
            %>
            <option value="" disabled>No categories available</option>
            <%
                }
            %>
        </select><br><br>

        <input type="submit" value="Add Product">
    </form>
</div>
</body>
</html>
