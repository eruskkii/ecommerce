<%--
  Created by IntelliJ IDEA.
  User: Emmanuel Paul
  Date: 11/20/2024
  Time: 4:48 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html lang="en">
<head>
  <meta charset="UTF-8">
  <title>All Products</title>
  <link rel="stylesheet" href="styles.css"> <!-- Assuming a stylesheet is available -->
</head>
<body>
<h1>All Products</h1>

<!-- Add New Product Button -->
<div class="add-product">
  <a href="addProduct" class="button">Add New Product</a>
</div>

<div class="product-list-admin">
  <c:if test="${not empty productList}">
  <table border="1" class="product-table">
    <thead>
    <tr>
      <th>Product ID</th>
      <th>Name</th>
      <th>Description</th>
      <th>Price</th>
      <th>Quantity</th>
      <th>Actions</th>
    </tr>
    </thead>
    <tbody>
    <c:forEach var="product" items="${productList}">
      <tr>
        <td>${product.product_id}</td>
        <td>${product.name}</td>
        <td>${product.description}</td>
        <td>${product.price}</td>
        <td>${product.quantity}</td>
        <td>
          <!-- Update Product Button -->
          <a href="updateProductServlet?productId=${product.product_id}">
            <button class="button">Update Product</button>
          </a>
          <!-- Delete Product Button -->
          <form action="admin-dashboard" method="post" style="display:inline;">
            <input type="hidden" name="action" value="deleteProduct">
            <input type="hidden" name="productId" value="${product.product_id}">
            <input type="hidden" name="confirmation" value="yes">
            <button type="submit">Delete</button>
          </form>
        </td>
      </tr>
    </c:forEach>
    </tbody>
  </table>
  </c:if>
</div>

<a href="admin-dashboard" class="back">Back to Admin Dashboard</a>
</body>
</html>

