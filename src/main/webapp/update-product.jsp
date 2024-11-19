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
    <link rel="stylesheet" href="styles.css">
</head>
<body>
<h1>Update Product</h1>

<form action="admin-product-servlet" method="post">
    <input type="hidden" name="action" value="updateProduct">
    <label for="productId">Select Product:</label>
    <select name="productId" id="productId" required>
        <c:forEach var="product" items="${products}">
            <option value="${product.productId}">
                    ${product.name} - ${product.price} USD
            </option>
        </c:forEach>
    </select><br><br>

    <label for="name">Product Name:</label>
    <input type="text" name="name" id="name" value="${selectedProduct.name}" required><br><br>

    <label for="price">Price:</label>
    <input type="number" name="price" id="price" value="${selectedProduct.price}" step="0.01" required><br><br>

    <label for="quantity">Quantity:</label>
    <input type="number" name="quantity" id="quantity" value="${selectedProduct.quantity}" required><br><br>

    <button type="submit">Update Product</button>
</form>

<div class="back">
    <a href="admin-dashboard.jsp">Back to Admin Dashboard</a>
</div>
</body>
</html>

