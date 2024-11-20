<%--
  Created by IntelliJ IDEA.
  User: Emmanuel Paul
  Date: 11/19/2024
  Time: 1:37 AM
  To change this template use File | Settings | File Templates.
--%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Product Catalogue</title>
    <link rel="stylesheet" type="text/css" href="styles/catalogue.css">
</head>
<body>
<h1>Product Catalogue</h1>
<div class="product-list">

    <!-- Loop through all products -->
    <c:forEach var="product" items="${products}">
        <div class="product">
            <h2>${product.name}</h2>
            <p><strong>Price:</strong> $${product.price}</p>
            <p><strong>Description:</strong> ${product.description}</p>
            <p><strong>ProductId:</strong> ${product.product_id}</p>


            <!-- Add to Cart Button -->
            <form action="cart-servlet" method="post">
                <input type="hidden" name="action" value="addToCart">
                <input type="hidden" name="productId" value="${product.product_id}">
                <input type="hidden" name="quantity" value="1"> <!-- Default quantity to 1 -->
                <button type="submit">Add to Cart</button>
            </form>

            <!-- Add to Wishlist Button -->
            <form action="wishlist-servlet" method="post">
                <input type="hidden" name="action" value="addToWishlist">
                <input type="hidden" name="productId" value="${product.product_id}">
                <button type="submit">Add to Wishlist</button>
            </form>
        </div>
    </c:forEach>

</div>
<a href="user-dashboard-servlet" class="back">Back to Dashboard</a>
</body>
</html>

