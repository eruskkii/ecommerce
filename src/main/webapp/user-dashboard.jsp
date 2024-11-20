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
    <title>User Dashboard</title>
    <link rel="stylesheet" href="styles.css">
</head>
<body>
<h1>Welcome, ${sessionScope.userName}!</h1> <!-- Assuming userName is stored in session -->

<div class="dashboard-options">
    <ul>
        <li><a href="catalogue-servlet">Browse Catalogue</a></li>
        <li><a href="cart-servlet">View Cart</a></li>
        <li><a href="wishlist-servlet">View Wishlist</a></li>
        <li><a href="profile.jsp">Update Profile</a></li> <!-- Placeholder for future profile page -->
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

