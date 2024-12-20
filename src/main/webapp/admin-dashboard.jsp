<%--
  Created by IntelliJ IDEA.
  User: Emmanuel Paul
  Date: 11/19/2024
  Time: 5:35 AM
  To change this template use File | Settings | File Templates.
--%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Admin Dashboard</title>
    <link rel="stylesheet" href="styles.css">
</head>
<body>
<h1>Welcome, Admin!</h1>

<div class="dashboard-options">
    <ul>
        <li><a href="addCategory.jsp">Add New Category</a></li>
        <li><a href="addProduct">Add New Product</a></li>
<%--        <li><a href="update-product.jsp">Update Product</a></li> <!-- Update Product Option -->--%>
<%--        <li><a href="deleteProduct.jsp">Delete Product</a></li> <!-- Delete Product Option -->--%>
        <li><a href="view-all-products">View All Products</a></li> <!-- View Products Option -->
        <li><a href="view-all-users">View Users</a></li> <!-- Placeholder for user management -->
        <li><a href="viewOrders.jsp">View Orders (Coming soon)</a></li> <!-- Placeholder for order management -->
    </ul>
</div>

<!-- Logout option -->
<div class="logout">
    <form action="logout-servlet" method="post">
        <button type="submit">Logout</button>
    </form>
</div>
</body>
</html>

