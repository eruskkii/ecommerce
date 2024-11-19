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
    <title>Delete Product</title>
    <link rel="stylesheet" href="styles.css">
</head>
<body>
<h1>Delete Product</h1>

<form action="admin-product-servlet" method="post">
    <input type="hidden" name="action" value="deleteProduct">
    <label for="productId">Select Product to Delete:</label>
    <select name="productId" id="productId" required>
        <c:forEach var="product" items="${products}">
            <option value="${product.productId}">
                    ${product.name} - ${product.price} USD
            </option>
        </c:forEach>
    </select><br><br>

    <label for="confirmation">Confirm Deletion:</label>
    <input type="checkbox" id="confirmation" name="confirmation" value="yes" required> Yes, delete this product<br><br>

    <button type="submit">Delete Product</button>
</form>

<div class="back">
    <a href="admin-dashboard.jsp">Back to Admin Dashboard</a>
</div>
</body>
</html>

