<%--
  Created by IntelliJ IDEA.
  User: Emmanuel Paul
  Date: 11/19/2024
  Time: 7:58 AM
  To change this template use File | Settings | File Templates.
--%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page contentType="text/html; charset=UTF-8" %>

<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Update Product</title>
    <link rel="stylesheet" href="styles.css"> <!-- Assuming a stylesheet is available -->
</head>
<body>
<h1>Update Product</h1>

<form action="updateProductServlet" method="post">
    <input type="hidden" name="product_id" value="${product.product_id}">

    <label for="name">Product Name:</label>
    <input type="text" id="name" name="name" value="${product.name}" required>

    <label for="price">Price:</label>
    <input type="number" step="0.01" id="price" name="price" value="${product.price}" required>

    <label for="quantity">Quantity:</label>
    <input type="number" id="quantity" name="quantity" value="${product.quantity}" required>

    <button type="submit">Update Product</button>
</form>

<a href="view-all-products">Back to Product List</a>
</body>
</html>

