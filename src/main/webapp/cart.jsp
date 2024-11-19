<%--
  Created by IntelliJ IDEA.
  User: Emmanuel Paul
  Date: 11/19/2024
  Time: 5:56 AM
  To change this template use File | Settings | File Templates.
--%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Your Cart</title>
    <link rel="stylesheet" href="styles.css"> <!-- Assuming a stylesheet is available -->
</head>
<body>
<h1>Your Shopping Cart</h1>

<!-- Display Success or Error Messages -->
<c:if test="${not empty param.success}">
    <div class="success-message">
        <c:choose>
            <c:when test="${param.success == 'true'}">Product added to cart successfully!</c:when>
            <c:when test="${param.success == 'updated'}">Cart updated successfully!</c:when>
            <c:when test="${param.success == 'removed'}">Item removed from cart successfully!</c:when>
        </c:choose>
    </div>
</c:if>

<c:if test="${not empty param.error}">
    <div class="error-message">
        <c:choose>
            <c:when test="${param.error == 'addFailed'}">Failed to add product to cart. Please try again.</c:when>
            <c:when test="${param.error == 'updateFailed'}">Failed to update cart item. Please try again.</c:when>
            <c:when test="${param.error == 'removeFailed'}">Failed to remove item from cart. Please try again.</c:when>
        </c:choose>
    </div>
</c:if>

<!-- Display Cart Items -->
<c:if test="${not empty cartItems}">
    <table border="1" class="cart-table">
        <thead>
        <tr>
            <th>Product</th>
            <th>Description</th>
            <th>Quantity</th>
            <th>Price (each)</th>
            <th>Total Price</th>
            <th>Actions</th>
        </tr>
        </thead>
        <tbody>
        <c:forEach var="item" items="${cartItems}">
            <tr>
                <td>${item.product.name}</td>
                <td>${item.product.description}</td>
                <td>
                    <!-- Quantity form for updating -->
                    <form action="cart-servlet" method="post">
                        <input type="hidden" name="action" value="updateQuantity">
                        <input type="hidden" name="cartId" value="${item.cartId}">
                        <input type="number" name="quantity" value="${item.quantity}" min="1">
                        <button type="submit">Update</button>
                    </form>
                </td>
                <td>${item.product.price}</td>
                <td>${item.product.price * item.quantity}</td>
                <td>
                    <!-- Remove item form -->
                    <form action="cart-servlet" method="post">
                        <input type="hidden" name="action" value="removeFromCart">
                        <input type="hidden" name="cartId" value="${item.cartId}">
                        <button type="submit">Remove</button>
                    </form>
                </td>
            </tr>
        </c:forEach>
        </tbody>
    </table>

    <!-- Display Total Amount -->
    <div class="total-amount">
        <h3>Total Amount: ${totalAmount}</h3>
    </div>
</c:if>

<!-- Empty Cart Message -->
<c:if test="${empty cartItems}">
    <div class="empty-cart">
        <p>Your cart is currently empty. Start shopping now!</p>
    </div>
</c:if>

<!-- Link to continue shopping -->
<div class="continue-shopping">
    <a href="products.jsp">Continue Shopping</a>
</div>
</body>
</html>


