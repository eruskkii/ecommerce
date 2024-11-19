<%--
  Created by IntelliJ IDEA.
  User: Emmanuel Paul
  Date: 11/19/2024
  Time: 6:21 AM
  To change this template use File | Settings | File Templates.
--%>
<!-- Product Page Section -->
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
    <title>Product Details</title>
</head>
<body>
<!-- Displaying Product Details -->
<h1>${product.name}</h1>
<img src="${product.imageUrl}" alt="${product.name}" width="300" height="300"/>
<p><b>Description:</b> ${product.description}</p>
<p><b>Price:</b> $${product.price}</p>
<p><b>Available Quantity:</b> ${product.quantity}</p>

<!-- Form for Adding Product to Cart -->
<form action="/cart-servlet" method="post">
    <input type="hidden" name="action" value="addToCart">
    <input type="hidden" name="productId" value="${product.id}">
    <input type="hidden" name="userId" value="${sessionScope.userId}"> <!-- Assuming userId is stored in the session -->
    <label for="quantity">Quantity:</label>
    <input type="number" name="quantity" id="quantity" value="1" min="1" max="${product.quantity}" required>
    <button type="submit">Add to Cart</button>
</form>

<!-- Form for Adding Product to Wishlist -->
<form action="/wishlist-servlet" method="post">
    <input type="hidden" name="action" value="addToWishlist">
    <input type="hidden" name="productId" value="${product.id}">
    <input type="hidden" name="userId" value="${sessionScope.userId}">
    <button type="submit">Add to Wishlist</button>
</form>

<!-- Navigation Links -->
<a href="/catalogue.jsp">Back to Catalogue</a>

</body>
</html>

</html>

