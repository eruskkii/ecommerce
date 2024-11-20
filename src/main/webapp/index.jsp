<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html lang="en">
<head>
  <meta charset="UTF-8">
  <title>Welcome to Our Store</title>
  <link rel="stylesheet" href="styles.css"> <!-- Assuming you have a stylesheet available -->
  <style>
    /* Inline styling for simplicity, you can use your main stylesheet */
    .container {
      text-align: center;
      padding-top: 50px;
    }
    .button-container {
      margin: 20px;
    }
    .button {
      padding: 10px 20px;
      margin: 10px;
      font-size: 16px;
      cursor: pointer;
    }
    .admin-signup {
      position: absolute;
      top: 20px;
      right: 20px;
      font-size: 14px;
    }
  </style>
</head>
<body>

<div class="container">
  <h1>Welcome to The Heavy Store!</h1>
  <p>Your one-stop shop for the best products around.</p>

  <div class="button-container">
    <form action="signup" method="get">
      <button type="submit" class="button">Sign Up</button>
    </form>

    <form action="login.jsp" method="get">
      <button type="submit" class="button">Login</button>
    </form>
  </div>
</div>

<div class="admin-signup">
  <form action="adminsignup.jsp" method="get">
    <button type="submit" class="button">Admin Sign Up</button>
  </form>
</div>

</body>
</html>
