<%--
  Created by IntelliJ IDEA.
  User: Emmanuel Paul
  Date: 11/19/2024
  Time: 5:55 AM
  To change this template use File | Settings | File Templates.
--%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
  <title>My Wishlist</title>
</head>
<body>
<h1>My Wishlist</h1>

<!-- Display Success Message -->
<c:if test="${param.success == 'true'}">
  <p style="color:green;">Product added to your wishlist successfully!</p>
</c:if>
<c:if test="${param.success == 'false'}">
  <p style="color:red;">Failed to add product to wishlist. Try again.</p>
</c:if>

<c:if test="${empty wishlist}">
  <p>Your wishlist is currently empty. Go explore and add some products!</p>
</c:if>

<!-- Display Wishlist Items -->
<c:forEach var="item" items="${wishlist}">
  <div style="border: 1px solid #000; margin-bottom: 10px; padding: 10px;">
    <p>Product Name: ${item.productName}</p>
    <p>Added At: ${item.addedAt}</p>
    <!-- Action buttons -->
    <form action="cart" method="post">
      <input type="hidden" name="productId" value="${item.productId}">
      <button type="submit">Add to Cart</button>
    </form>
    <form action="removeFromWishlist" method="post">
      <input type="hidden" name="wishlistId" value="${item.wishlistId}">
      <button type="submit">Remove</button>
    </form>
  </div>
</c:forEach>
</body>
</html>

